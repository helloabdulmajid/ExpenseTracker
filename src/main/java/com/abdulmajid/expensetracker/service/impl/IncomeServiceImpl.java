package com.abdulmajid.expensetracker.service.impl;

import com.abdulmajid.expensetracker.dto.IncomeRequest;
import com.abdulmajid.expensetracker.dto.IncomeResponse;
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

        // System.out.printf(dbExpenseCategory + "fhf");

        // save source as a string in source income table
        Income income = new Income(incomeRequest.getAmount(), incomeRequest.getSource(),
                incomeRequest.getReceiveMode(), incomeRequest.getNote(), incomeRequest.getDay(),
                incomeRequest.getDate(), existsUser);

        Income saveIncome = incomeRepository.save(income);

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
}
