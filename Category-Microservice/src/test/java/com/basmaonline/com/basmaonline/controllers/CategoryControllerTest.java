package com.basmaonline.com.basmaonline.controllers;

import com.basmaonline.com.basmaonline.model.Category;
import com.basmaonline.com.basmaonline.service.CategoryService;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CategoryControllerTest {

    @MockBean
    private CategoryService categoryService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET /categories success")
    void findAllTesting() throws Exception{

        // Setup our mocked service
        Category category1 = new Category(1L, "Category1 Name", "Description category1", true, "category1.png");
        Category category2 = new Category(2L, "Category2 Name", "Description category2", true, "category2.png");

        doReturn(Lists.newArrayList(category1, category2)).when(categoryService).fetchAllCategories();

        // Execute the GET request
        mockMvc.perform(get("/categories"))
                // Validate the response code and content type
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                // Validate headers
                .andExpect(header().string(HttpHeaders.LOCATION, "/categories"))

                // Validate the returned fields
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].categoryId", is(1)))
                .andExpect(jsonPath("$[0].categoryName", is("Category1 Name")))
                .andExpect(jsonPath("$[0].categoryDescription", is("Description category1")))
                .andExpect(jsonPath("$[0].categoryStatus", is(true)))
                .andExpect(jsonPath("$[0].categoryImageUrl", is("category1.png")))
                .andExpect(jsonPath("$[1].categoryId", is(2)))
                .andExpect(jsonPath("$[1].categoryName", is("Category2 Name")))
                .andExpect(jsonPath("$[1].categoryDescription", is("Description category2")))
                .andExpect(jsonPath("$[1].categoryStatus", is(true)))
                .andExpect(jsonPath("$[1].categoryImageUrl", is("category2.png")));


    }
}