package com.basmaonline.com.basmaonline.service;

import com.basmaonline.com.basmaonline.exceptions.CategoryNotFoundException;
import com.basmaonline.com.basmaonline.model.Category;

import java.util.List;
import java.util.Optional;


public interface CategoryService {
    public List<Category> fetchAllCategories();
    public Optional<Category> fetchCategoryById(Long categoryId) throws CategoryNotFoundException;
    public Category addCategory(Category category);
    public void deleteCategory(Long categoryId) throws CategoryNotFoundException;
}
