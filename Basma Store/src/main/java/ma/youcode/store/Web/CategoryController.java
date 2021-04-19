package com.basmaonline.com.basmaonline.controllers;

import com.basmaonline.com.basmaonline.exceptions.CategoryNotAddedException;
import com.basmaonline.com.basmaonline.exceptions.CategoryNotFoundException;
import com.basmaonline.com.basmaonline.model.Category;
import com.basmaonline.com.basmaonline.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
//@RequestMapping("/admin")
public class CategoryController {

    private static final String ENTITY_NAME = "Category";

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/newCategory")
    @ResponseStatus(HttpStatus.CREATED)
    public Category createNewCategory(@RequestBody Category category) throws CategoryNotAddedException {
        if(category.getCategoryId() == 0L) {
            throw new CategoryNotAddedException("A new category cannot be added ID " + ENTITY_NAME + " id already exists");
        }

        return categoryService.addCategory(category);
    }

    @PutMapping("/editCategory")
    @ResponseStatus(HttpStatus.OK)
    public Category updateCategory(@RequestBody Category category) throws CategoryNotFoundException {
        if(category.getCategoryId() == 0L) {
            throw new CategoryNotFoundException("Invalid id " + ENTITY_NAME + " or idnull");
        }

        return categoryService.addCategory(category);
    }

    @GetMapping("/categories")
    public List<Category> findAll() {
        return categoryService.fetchAllCategories();
    }

    @GetMapping("/category/{categoryId}")
    public Optional<Category> getCategory(@PathVariable("categoryId") Long categoryId){

        return categoryService.fetchCategoryById(categoryId);
    }

    //You can change this on late
    // Return a string or ResponseEntity
    @DeleteMapping("/category/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCategory(@PathVariable("categoryId") Long categoryId) {
        categoryService.deleteCategory(categoryId);
    }

}
