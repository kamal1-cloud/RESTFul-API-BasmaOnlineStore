package com.basmaonline.com.basmaonline.service;

import com.basmaonline.com.basmaonline.model.Category;
import com.basmaonline.com.basmaonline.repositories.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
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
}