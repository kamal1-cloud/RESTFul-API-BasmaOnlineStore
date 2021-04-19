package com.basmaonline.com.basmaonline.service;

import com.basmaonline.com.basmaonline.exceptions.CategoryNotFoundException;
import com.basmaonline.com.basmaonline.model.Category;
import com.basmaonline.com.basmaonline.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    CategoryRepository categoryRepository;


    @Override
    public List<Category> fetchAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> fetchCategoryById(Long categoryId) throws CategoryNotFoundException {
        return categoryRepository.findById(categoryId);
    }

    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long categoryId) throws CategoryNotFoundException {
        categoryRepository.deleteById(categoryId);
    }
}
