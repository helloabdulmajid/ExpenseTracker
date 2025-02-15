package com.abdulmajid.expensetracker.controller;

import com.abdulmajid.expensetracker.dto.DebtRequest;
import com.abdulmajid.expensetracker.dto.DebtResponse;
import com.abdulmajid.expensetracker.service.DebtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("debt")
public class DebtController {

    @Autowired
    private DebtService debtService;

    @PostMapping("/create/{userId}")
    public DebtResponse createDebtForUser(@PathVariable Integer userId,
                                          @RequestBody DebtRequest debtRequest) {
        return debtService.createDebtForUser(userId, debtRequest);
    }

    @GetMapping("/user/{userId}")
    public List<DebtResponse> getAllDebtForUser(@PathVariable Integer userId) {
        return debtService.getAllDebtForUser(userId);
    }

    @GetMapping("/{debtId}")
    public DebtResponse getDebt(@PathVariable Integer debtId) {
        return debtService.getDebt(debtId);
    }

    @GetMapping("/all")
    public List<DebtResponse> getAllDebt() {
        return debtService.getAllDebt();
    }

    @PutMapping("/update/user/{userId}/debt/{debtId}")
    public String updateDebt(
            @PathVariable Integer userId,
            @PathVariable Integer debtId,
            @RequestBody DebtRequest debtRequest) {

        return debtService.updateDebt(userId, debtId, debtRequest);

    }

    @DeleteMapping("/delete/user/{userId}/debt/{debtId}")
    public String deleteDebt(@PathVariable Integer userId,
                             @PathVariable Integer debtId) {
        return debtService.deleteDebt(userId, debtId);
    }

}
