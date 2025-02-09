package com.abdulmajid.expensetracker.service.impl;

import com.abdulmajid.expensetracker.dto.DebtRequest;
import com.abdulmajid.expensetracker.dto.DebtResponse;
import com.abdulmajid.expensetracker.exception.custom.UserNotFoundException;
import com.abdulmajid.expensetracker.model.Debt;
import com.abdulmajid.expensetracker.model.User;
import com.abdulmajid.expensetracker.repository.DebtRepository;
import com.abdulmajid.expensetracker.repository.UserRepository;
import com.abdulmajid.expensetracker.service.DebtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
                debtRequest.isRecurring(), debtRequest.getCategory(),
                debtRequest.getNote(), debtRequest.getDay(), debtRequest.getDate(),
                debtRequest.getCreatedAt(), debtRequest.getUpdatedAt(), existsUser);

        Debt saveDebt = debtRepository.save(debt);

        return new DebtResponse(saveDebt.getId(), saveDebt.getAmount(), saveDebt.getCreditor(),
                saveDebt.getCreditorName(), saveDebt.getDebtor(), saveDebt.getDebtorName(),
                saveDebt.getDueDate(), saveDebt.getStatus(), saveDebt.getPriority(),
                saveDebt.isRecurring(), saveDebt.getCategory(), saveDebt.getNote(),
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
                    debt.isRecurring(), debt.getCategory(), debt.getNote(),
                    debt.getDay(), debt.getDate(), debt.getCreatedAt(),
                    debt.getUpdatedAt(), debt.getUser());

            responseList.add(response);

        }
        return responseList;

    }
}
