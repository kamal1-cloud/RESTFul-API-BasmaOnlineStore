package com.basmaonline.com.basmaonline.service;

import com.basmaonline.com.basmaonline.model.Category;
import com.basmaonline.com.basmaonline.repositories.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

class CategoryServiceImplTest {

    /* Annotation used to inject the mocked objects */
    @InjectMocks
    CategoryServiceImpl categoryService;

    /*
    *   We are creating a mock object here
    *   Fake class which we can instantiate the return results of it's   *   method by giving desire input and output
    *  */
    @Mock
    CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() throws Exception{
        MockitoAnnotations.openMocks(this);
    }

    /*
    *   when a method called findById this method accepts any Long
    *   It should return a specific object
    * */
    @Test
    final void fetchCategoryById() {
        Category films = new Category();
        films.setCategoryId(1L);
        films.setCategoryName("Films");
        films.setCategoryDescription("This is films category");
        films.setCategoryImageUrl("films.png");
        films.setCategoryStatus(true);
        when(categoryRepository.findById(anyLong())).thenReturn(java.util.Optional.of(films));

        Optional<Category> categorytest = categoryService.fetchCategoryById(1L);

        assertNotNull(categorytest);
        assertEquals("Films", films.getCategoryName());
    }

    @Test
    @DisplayName("Test save category")
    void testSave() {
        // Setup our mock repository
        Category category = new Category(3L, "Watches", "this is a watches categorie", true, "watches.png");

        doReturn(category).when(categoryRepository).save(any());

        // Execute the service call
        Category returnedCategory = categoryService.addCategory(category);

        // Assert the response
        Assertions.assertNotNull(returnedCategory, "The saved category should not be null");
        Assertions.assertEquals("Watches", returnedCategory.getCategoryName(), "Category Found");
    }

    @Test
    @DisplayName("Test findById Not Found")
    void testFindByIdNotFound() {
        // Setup our mock repository
        doReturn(Optional.empty()).when(categoryRepository).findById(1L);

        // Execute the service call
        Optional<Category> returnedCategory = categoryService.fetchCategoryById(1L);

        // Assert the response
        Assertions.assertFalse(returnedCategory.isPresent(), "Category should not be found");
    }

    @Test
    @DisplayName("Test findAll")
    void testFindAll() {
        // Setup our mock repository
        Category category1 = new Category("Category1 Name", "Description category1", "category1.png");
        Category category2 = new Category("Category2 Name", "Description category2", "category2.png");
        doReturn(Arrays.asList(category1, category2)).when(categoryRepository).findAll();

        // Execute the service call
        List<Category> categories = categoryService.fetchAllCategories();

        // Assert the response
        Assertions.assertEquals(2, categories.size(), "findAll should return 2 categories");
    }
}