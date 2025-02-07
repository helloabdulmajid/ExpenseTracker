package com.abdulmajid.expensetracker.service.impl;

import com.abdulmajid.expensetracker.dto.IncomeCategoryRequest;
import com.abdulmajid.expensetracker.dto.IncomeCategoryResponse;
import com.abdulmajid.expensetracker.exception.custom.CategoryNotFoundException;
import com.abdulmajid.expensetracker.exception.custom.UserNotFoundException;
import com.abdulmajid.expensetracker.model.IncomeCategory;
import com.abdulmajid.expensetracker.model.User;
import com.abdulmajid.expensetracker.repository.IncomeCategoryRepository;
import com.abdulmajid.expensetracker.repository.IncomeRepository;
import com.abdulmajid.expensetracker.repository.UserRepository;
import com.abdulmajid.expensetracker.service.IncomeCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IncomeCategoryServiceImpl implements IncomeCategoryService {
    @Autowired
    private IncomeRepository incomeRepository;

    @Autowired
    private IncomeCategoryRepository incomeCategoryRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public List<IncomeCategory> getIncomeCategoriesForUser(Integer userId) {
        User existsUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));

        return incomeCategoryRepository.findByUserId(userId);


    }

    @Override
    public void createIncomeCategoryForUser(Integer userId, IncomeCategoryRequest incomeCategoryRequest) {
        // Check if user exists
        User existsUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));

        // Check if the category already exists for the user
//        if (incomeCategoryRepository.existsByCategoryName(incomeCategoryRequest.getCategoryName()) ||
//                incomeCategoryRepository.existsByCategoryNameAndUser(incomeCategoryRequest.getCategoryName(), existsUser)) {
//            throw new CategoryNotFoundException("Income Category already exists for this user");
//        }
        if (incomeCategoryRepository.existsByCategoryNameAndIsDefaultCategory(incomeCategoryRequest.getCategoryName(), true) ||
                incomeCategoryRepository.existsByCategoryNameAndUser(incomeCategoryRequest.getCategoryName(), existsUser)) {
            throw new CategoryNotFoundException("Income Category already exists for this user");
        }
        //Create new Income for the user

        IncomeCategory incomeCategory = new IncomeCategory();
        incomeCategory.setCategoryName(incomeCategoryRequest.getCategoryName().toUpperCase());
        incomeCategory.setDefaultCategory(false);
        incomeCategory.setUser(existsUser);
        incomeCategoryRepository.save(incomeCategory);

    }

    @Override
    public List<IncomeCategoryResponse> getAllCategories() {
        List<IncomeCategory> incomeCategoryList = incomeCategoryRepository.findAll();

        // Convert each IncomeCategory entity to IncomeCategoryResponse DTO
        ArrayList<IncomeCategoryResponse> responseList = new ArrayList<>();

        for (IncomeCategory incomeCategory : incomeCategoryList) {
            IncomeCategoryResponse response = new IncomeCategoryResponse(
                    incomeCategory.getId(), incomeCategory.getCategoryName(),
                    incomeCategory.isDefaultCategory()
            );
            responseList.add(response);

        }
        return responseList;
    }
}
