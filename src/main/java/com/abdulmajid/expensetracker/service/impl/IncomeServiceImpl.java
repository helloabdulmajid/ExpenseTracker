package com.abdulmajid.expensetracker.service.impl;

import com.abdulmajid.expensetracker.dto.IncomeRequest;
import com.abdulmajid.expensetracker.dto.IncomeResponse;
import com.abdulmajid.expensetracker.exception.custom.IncomeNotFoundException;
import com.abdulmajid.expensetracker.exception.custom.UserNotFoundException;
import com.abdulmajid.expensetracker.model.ExpenseCategory;
import com.abdulmajid.expensetracker.model.Income;
import com.abdulmajid.expensetracker.model.User;
import com.abdulmajid.expensetracker.repository.ExpenseCategoryRepository;
import com.abdulmajid.expensetracker.repository.IncomeRepository;
import com.abdulmajid.expensetracker.repository.UserRepository;
import com.abdulmajid.expensetracker.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class IncomeServiceImpl implements IncomeService {
    @Autowired
    private IncomeRepository incomeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExpenseCategoryRepository expenseCategoryRepository;

    @Override
    public IncomeResponse createIncomeForUser(Integer userId, IncomeRequest incomeRequest) {

        // get user from userId(param)
        User existsUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));


        //get category from source
        ExpenseCategory dbExpenseCategory = expenseCategoryRepository.findByCategoryName(incomeRequest.getSource());


        // save source as a string in source income table
        Income income = new Income(incomeRequest.getAmount(), incomeRequest.getSource(),
                incomeRequest.getReceiveMode(), incomeRequest.getNote(), incomeRequest.getDay(),
                incomeRequest.getDate(), existsUser);

        Income saveIncome = incomeRepository.save(income);

        //get Total income from user table
        BigDecimal oldTotalIncome = existsUser.getIncome();
        BigDecimal newTotalIncome = oldTotalIncome.add(incomeRequest.getAmount());

        //add newTotalIncome in user table

        existsUser.setIncome(newTotalIncome);
        existsUser.setUpdatedAt(new Date());

        userRepository.save(existsUser);

        return new IncomeResponse(saveIncome.getId(), saveIncome.getAmount(),
                saveIncome.getSourceCategory(), saveIncome.getReceiveMode(), saveIncome.getNote(),
                saveIncome.getDay(), saveIncome.getDate(), saveIncome.getCreatedAt(),
                saveIncome.getUpdatedAt(), saveIncome.getUser());

    }

    @Override
    public List<Income> getIncomeForUser(Integer userId) {

        // get user from userId(param)
        User existsUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));


        return incomeRepository.findByUserId(userId);
    }

    @Override
    public IncomeResponse getIncome(Integer incomeId) {
        Income existingIncome = incomeRepository.findById(incomeId)
                .orElseThrow(() -> new IncomeNotFoundException("Income Not found with Income Id:  " + incomeId));

        return new IncomeResponse(existingIncome.getId(), existingIncome.getAmount(),
                existingIncome.getSourceCategory(), existingIncome.getReceiveMode(), existingIncome.getNote(),
                existingIncome.getDay(), existingIncome.getDate(), existingIncome.getCreatedAt(),
                existingIncome.getUpdatedAt());

    }

    @Override
    public List<IncomeResponse> getAllIncome() {
        List<Income> allIncome = incomeRepository.findAll();
        ArrayList<IncomeResponse> incomeResponses = new ArrayList<>();
        for (Income income : allIncome) {
            IncomeResponse incomeResponse = new IncomeResponse(income.getId(), income.getAmount(), income.getSourceCategory(),
                    income.getReceiveMode(), income.getNote(), income.getDay(), income.getDate(),
                    income.getCreatedAt(), income.getUpdatedAt());
            incomeResponses.add(incomeResponse);
        }
        return incomeResponses;
    }


    @Override
    public String updateIncome(Integer userId, Integer incomeId, IncomeRequest incomeRequest) {

        // get user from userId(param)
        User existsUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));


        //get income from incomeId
        Income existsIncome = incomeRepository.findById(incomeId)
                .orElseThrow(() -> new IncomeNotFoundException("Income Not found with Income Id:  " + incomeId));

        BigDecimal oldAmount = existsIncome.getAmount();
        BigDecimal newAmount = incomeRequest.getAmount();

        // If new amount is the same as old, just update the income all records and return
        if (newAmount.compareTo(oldAmount) == 0) {
            existsIncome.setSourceCategory(incomeRequest.getSource());
            existsIncome.setNote(incomeRequest.getNote());
            existsIncome.setUpdatedAt(new Date());

            incomeRepository.save(existsIncome);
            return "Income Updated SuccessFully , Income Id :" + incomeId; //  Exit the function early (no need to update user's income)
        }

        // if NewAmount is greater than old Amount → Add the difference to user table

        if (newAmount.compareTo(oldAmount) > 0) {
            BigDecimal difference = newAmount.subtract(oldAmount);
            existsUser.setIncome(existsUser.getIncome().add(difference));
        } else {
            // if New amount is smaller → Subtract the difference
            BigDecimal difference = oldAmount.subtract(newAmount);

            if (existsUser.getIncome().subtract(difference).compareTo(BigDecimal.ZERO) < 0) {
                throw new IncomeNotFoundException("Not enough balance to update this income");
            }

            existsUser.setIncome(existsUser.getIncome().subtract(difference));
        }

        // Update the income record
        existsIncome.setAmount(newAmount);
        existsIncome.setSourceCategory(incomeRequest.getSource());
        existsIncome.setNote(incomeRequest.getNote());
        existsIncome.setUpdatedAt(new Date());

        // Save updates
        incomeRepository.save(existsIncome);
        existsUser.setUpdatedAt(new Date());
        userRepository.save(existsUser);

        return "Income Updated SuccessFully , Income Id :" + incomeId;
    }

    @Override
    public String deleteIncome(Integer userId, Integer incomeId) {

        User existsUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));

        Income existsIncome = incomeRepository.findById(incomeId)
                .orElseThrow(() -> new IncomeNotFoundException("Income Not found with Income Id:  " + incomeId));

        BigDecimal incomeAmount = existsIncome.getAmount();

        try {
            existsUser.setIncome(existsUser.getIncome().subtract(incomeAmount));

            // Remove income record
            incomeRepository.delete(existsIncome);
            userRepository.save(existsUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Deleted Income Id : -> " + incomeId;
    }


}
