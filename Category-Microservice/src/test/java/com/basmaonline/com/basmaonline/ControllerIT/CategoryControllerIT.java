package com.basmaonline.com.basmaonline.ControllerIT;

import com.basmaonline.com.basmaonline.model.Category;
import com.basmaonline.com.basmaonline.repositories.CategoryRepository;
import com.basmaonline.com.basmaonline.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
public class CategoryControllerIT {

    private static final String DEFAULT_NAME = "FIMLS";
    private static final String UPDATE_NAME = "PHONES";

    private static final String DEFAULT_DESCRIPTION = "THIS IS FILMS CATEGORY";
    private static final String UPDATE_DESCRIPTION = "THIS IS PHONES CATEGORY";

    private static final String DEFAULT_IMG = "films.jpg";
    private static final String UPDATE_IMG = "phones.jpg";

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private EntityManager em;

    private MockMvc mockMvc;

    private Category category;

    public static Category createEntity(EntityManager em){
        Category category = new Category();
        category.setCategoryName(DEFAULT_NAME);
        category.setCategoryDescription(DEFAULT_DESCRIPTION);
        category.setCategoryImageUrl(DEFAULT_IMG);

        return category;
    }

    public static Category createUpdatedEntity(EntityManager em){
        Category category = new Category();
        category.setCategoryName(UPDATE_NAME);
        category.setCategoryDescription(UPDATE_DESCRIPTION);
        category.setCategoryImageUrl(UPDATE_IMG);

        return category;
    }

    @BeforeEach
    public void initTest() {
        category = createEntity(em);
    }

    @Test
    @Transactional
    public void createCategory() throws Exception {
        int databaseSizeBeforeCreate = categoryRepository.findAll().size();

        //Create the category
        mockMvc.perform(post("/newCategory")
                .accept(MediaType.APPLICATION_JSON)
                .content(TestUtil)
        )


    }
}

