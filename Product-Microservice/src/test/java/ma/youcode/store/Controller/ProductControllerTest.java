package ma.youcode.store.Controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.web.annotation.WebEndpoint;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductController productController;


    @Test
    void listProducts() throws Exception {


        RequestBuilder request = MockMvcRequestBuilders.get("/product")
                .accept(MediaType.APPLICATION_JSON);
                 mockMvc.perform(request)
                         .andExpect( MockMvcResultMatchers.status().is( HttpStatus.OK.value() ) )
                         .andDo(MockMvcResultHandlers.print())
                ;


    }

    @Test
    void newProduct() {

    }

    @Test
    void update() {
    }

    @Test
    void deleteUser() {
    }

    @Test
    void getProductWithCatgory() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .get("/product/2").accept(MediaType.APPLICATION_JSON)).andReturn();

    }
}