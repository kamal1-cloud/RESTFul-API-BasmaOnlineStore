package com.basmaonline.com.basmaonline.util;

import com.basmaonline.com.basmaonline.model.Category;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class AbstractControllerTest {
    public static String asJsonString(Category category) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(category);
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}
