package com.abdulmajid.expensetracker.service.impl;

import com.abdulmajid.expensetracker.dto.DebtRequest;
import com.abdulmajid.expensetracker.dto.DebtResponse;
import com.abdulmajid.expensetracker.exception.custom.DebtNotFoundException;
import com.abdulmajid.expensetracker.exception.custom.UserNotFoundException;
import com.abdulmajid.expensetracker.model.Debt;
import com.abdulmajid.expensetracker.model.User;
import com.abdulmajid.expensetracker.repository.DebtRepository;
import com.abdulmajid.expensetracker.repository.UserRepository;
import com.abdulmajid.expensetracker.service.DebtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DebtServiceImpl implements DebtService {
    @Autowired
    private DebtRepository debtRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public DebtResponse createDebtForUser(Integer userId, DebtRequest debtRequest) {

        // get user from userId(param)
        User existsUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));

        Debt debt = new Debt(debtRequest.getAmount(), debtRequest.getCreditor(),
                debtRequest.getCreditorName(), debtRequest.getDebtor(),
                debtRequest.getDebtorName(), debtRequest.getDueDate(),
                debtRequest.getStatus(), debtRequest.getPriority(),
                debtRequest.getRecurring(), debtRequest.getCategory(),
                debtRequest.getNote(), debtRequest.getDay(), debtRequest.getDate(), existsUser);

        Debt saveDebt = debtRepository.save(debt);

        //get Total Debt from user table
        BigDecimal oldTotalDebt = existsUser.getDebt();
        BigDecimal newTotalDebt = oldTotalDebt.add(debtRequest.getAmount());

        //add newTotalDebt in user table

        existsUser.setDebt(newTotalDebt);
        existsUser.setUpdatedAt(new Date());
        userRepository.save(existsUser);

        return new DebtResponse(saveDebt.getId(), saveDebt.getAmount(), saveDebt.getCreditor(),
                saveDebt.getCreditorName(), saveDebt.getDebtor(), saveDebt.getDebtorName(),
                saveDebt.getDueDate(), saveDebt.getStatus(), saveDebt.getPriority(),
                saveDebt.getRecurring(), saveDebt.getCategory(), saveDebt.getNote(),
                saveDebt.getDay(), saveDebt.getDate(), saveDebt.getCreatedAt(),
                saveDebt.getUpdatedAt(), saveDebt.getUser());

    }

    @Override
    public List<DebtResponse> getAllDebtForUser(Integer userId) {
        // get user from userId
        User existsUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));

        List<Debt> listDebt = debtRepository.findByUserId(userId);
        ArrayList<DebtResponse> responseList = new ArrayList<>();

        for (Debt debt : listDebt) {
            DebtResponse response = new DebtResponse(debt.getId(), debt.getAmount(), debt.getCreditor(),
                    debt.getCreditorName(), debt.getDebtor(), debt.getDebtorName(),
                    debt.getDueDate(), debt.getStatus(), debt.getPriority(),
                    debt.getRecurring(), debt.getCategory(), debt.getNote(),
                    debt.getDay(), debt.getDate(), debt.getCreatedAt(),
                    debt.getUpdatedAt(), debt.getUser());

            responseList.add(response);

        }
        return responseList;

    }

    @Override
    public DebtResponse getDebt(Integer debtId) {

        Debt existsDebt = debtRepository.findById(debtId)
                .orElseThrow(() -> new DebtNotFoundException("Debt Not found with Debt Id:  " + debtId));

        return new DebtResponse(existsDebt.getId(), existsDebt.getAmount(), existsDebt.getCreditor(), existsDebt.getCreditorName(),
                existsDebt.getDebtor(), existsDebt.getDebtorName(), existsDebt.getDueDate(), existsDebt.getStatus(),
                existsDebt.getPriority(), existsDebt.getRecurring(), existsDebt.getCategory(), existsDebt.getNote(),
                existsDebt.getDay(), existsDebt.getDate(), existsDebt.getCreatedAt(), existsDebt.getUpdatedAt(), existsDebt.getUser());

    }

    @Override
    public List<DebtResponse> getAllDebt() {

        List<Debt> allDebt = debtRepository.findAll();
        ArrayList<DebtResponse> debtResponses = new ArrayList<>();
        for (Debt debt : allDebt) {
            DebtResponse debtResponse = new DebtResponse(
                    debt.getId(), debt.getAmount(), debt.getCreditor(), debt.getCreditorName(),
                    debt.getDebtor(), debt.getDebtorName(), debt.getDueDate(), debt.getStatus(),
                    debt.getPriority(), debt.getRecurring(), debt.getCategory(), debt.getNote(),
                    debt.getDay(), debt.getDate(), debt.getCreatedAt(), debt.getUpdatedAt()
            );
            debtResponses.add(debtResponse);

            // if we need user details also then use this one
//            DebtResponse debtResponse = new DebtResponse(
//                    debt.getId(), debt.getAmount(), debt.getCreditor(), debt.getCreditorName(),
//                    debt.getDebtor(), debt.getDebtorName(), debt.getDueDate(), debt.getStatus(),
//                    debt.getPriority(), debt.isRecurring(), debt.getCategory(), debt.getNote(),
//                    debt.getDay(), debt.getDate(), debt.getCreatedAt(), debt.getUpdatedAt(),debt.getUser()
//            );
//            debtResponses.add(debtResponse);
        }
        return debtResponses;
    }

    @Override
    public String updateDebt(Integer userId, Integer debtId, DebtRequest debtRequest) {

        // get user from userId(param)
        User existsUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));


        //get Debt from debtId
        Debt existsDebt = debtRepository.findById(debtId)
                .orElseThrow(() -> new DebtNotFoundException("Debt Not found with Debt Id:  " + debtId));

        BigDecimal oldAmount = existsDebt.getAmount();
        BigDecimal newAmount = debtRequest.getAmount();

        // If new amount is the same as old, just update the Debt all records and return
        if (newAmount.compareTo(oldAmount) == 0) {
            existsDebt.setAmount(debtRequest.getAmount());
            existsDebt.setCreditor(debtRequest.getCreditor());
            existsDebt.setCreditorName(debtRequest.getCreditorName());
            existsDebt.setDebtor(debtRequest.getDebtor());
            existsDebt.setDebtorName(debtRequest.getDebtorName());
            existsDebt.setDueDate(debtRequest.getDueDate());
            existsDebt.setStatus(debtRequest.getStatus());
            existsDebt.setPriority(debtRequest.getPriority());
            existsDebt.setRecurring(debtRequest.getRecurring());
            existsDebt.setCategory(debtRequest.getCategory());
            existsDebt.setNote(debtRequest.getNote());
            existsDebt.setDay(existsDebt.getDay());
            existsDebt.setDate(existsDebt.getDate());
            existsDebt.setUpdatedAt(new Date());

            debtRepository.save(existsDebt);
            return "Debt Updated SuccessFully , Debt Id :" + debtId; //  Exit the function early (no need to update user's Debt)
        }

        // if NewAmount is greater than old Amount → Add the difference to user table

        if (newAmount.compareTo(oldAmount) > 0) {
            BigDecimal difference = newAmount.subtract(oldAmount);
            existsUser.setDebt(existsUser.getDebt().add(difference));
        } else {
            // if New amount is smaller → Subtract the difference
            BigDecimal difference = oldAmount.subtract(newAmount);

            if (existsUser.getDebt().subtract(difference).compareTo(BigDecimal.ZERO) < 0) {
                throw new DebtNotFoundException("Not enough balance to update this Debt");
            }

            existsUser.setDebt(existsUser.getDebt().subtract(difference));
        }

        // Update the Debt record
        existsDebt.setAmount(newAmount);

        existsDebt.setCreditor(debtRequest.getCreditor());
        existsDebt.setCreditorName(debtRequest.getCreditorName());
        existsDebt.setDebtor(debtRequest.getDebtor());
        existsDebt.setDebtorName(debtRequest.getDebtorName());
        existsDebt.setDueDate(debtRequest.getDueDate());
        existsDebt.setStatus(debtRequest.getStatus());
        existsDebt.setPriority(debtRequest.getPriority());
        existsDebt.setRecurring(debtRequest.getRecurring());
        existsDebt.setCategory(debtRequest.getCategory());
        existsDebt.setNote(debtRequest.getNote());
        existsDebt.setDay(existsDebt.getDay());
        existsDebt.setDate(existsDebt.getDate());
        existsDebt.setUpdatedAt(new Date());

        // Save updates
        debtRepository.save(existsDebt);
        existsUser.setUpdatedAt(new Date());
        userRepository.save(existsUser);

        return "Debt Updated SuccessFully , Debt Id :" + debtId;
    }

    @Override
    public String deleteDebt(Integer userId, Integer debtId) {
        User existsUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));

        Debt existsDebt = debtRepository.findById(debtId)
                .orElseThrow(() -> new DebtNotFoundException("Debt Not found with Debt Id:  " + debtId));

        BigDecimal debtAmount = existsDebt.getAmount();

        try {
            existsUser.setDebt(existsUser.getDebt().subtract(debtAmount));

            // Remove Debt record
            debtRepository.delete(existsDebt);
            existsUser.setUpdatedAt(new Date());
            userRepository.save(existsUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Deleted Debt Id : -> " + debtId;
    }
}
