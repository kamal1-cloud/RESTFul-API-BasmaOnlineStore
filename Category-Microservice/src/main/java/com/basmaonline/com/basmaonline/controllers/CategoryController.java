package com.basmaonline.com.basmaonline.controllers;

import com.basmaonline.com.basmaonline.exceptions.CategoryNotAddedException;
import com.basmaonline.com.basmaonline.exceptions.CategoryNotFoundException;
import com.basmaonline.com.basmaonline.model.Category;
import com.basmaonline.com.basmaonline.service.CategoryService;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
//@RequestMapping("/admin")
public class CategoryController {

    private final Logger log = LoggerFactory.getLogger(Category.class);

    private static final String ENTITY_NAME = "Category";

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @PostMapping("/newCategory")
    public ResponseEntity<Category> createNewCategory(@RequestBody Category category) throws CategoryNotAddedException {
        log.debug("REST request to save Category: {}", category);
        if(category.getCategoryId() != null) {
            throw new CategoryNotAddedException("A new category cannot be added ID " + ENTITY_NAME + " id already exists");
        }

        Category result = categoryService.addCategory(category);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping("/editCategory")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category) throws CategoryNotFoundException {
        log.debug("REST request to update Category : {}", category);
        if(category.getCategoryId() == null) {
            throw new CategoryNotFoundException("Invalid id " + ENTITY_NAME + " or idnull");
        }
        Category result = categoryService.addCategory(category);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> findAll() {
        log.debug("REST request to get all Categories");

        List<Category> list = categoryService.fetchAllCategories();

        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<Category> getCategory(@PathVariable("categoryId") Long categoryId){
        log.debug("REST request to get Category : {}", categoryId);
        Optional<Category> category = categoryService.fetchCategoryById(categoryId);

        return ResponseUtil.wrapOrNotFound(category);
    }

    @DeleteMapping("/category/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> deleteCategory(@PathVariable("categoryId") Long categoryId) {
        log.debug("REST request to delete Category : {}", categoryId);
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.noContent().build();
    }

}
