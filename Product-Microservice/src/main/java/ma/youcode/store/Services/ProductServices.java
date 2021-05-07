package ma.youcode.store.Services;

import lombok.extern.slf4j.Slf4j;
import ma.youcode.store.Model.Products;
import ma.youcode.store.Repository.ProductRepository;
import ma.youcode.store.VO.Category;
import ma.youcode.store.VO.ResponseTemplateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@Service
@Slf4j
public class ProductServices {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private RestTemplate restTemplate;

    public List<Products> listAll() {
        return productRepository.findAll();
    }
    public Products save(Products product){
        productRepository.save(product);
        return product;
    }
    public Products getById(Long id){
          return productRepository.findById(id).get();
    }
    public void delete(Long id){
       productRepository.deleteById(id);
    }

    public ResponseTemplateVO getProductWithCatgory(Long productId) {
//        log.info("Inside getProductWithCatgory of ProductService");
        ResponseTemplateVO vo = new ResponseTemplateVO();
        Products product = productRepository.findById(productId).get();
        Category category =
                restTemplate.getForObject("http://MICROSERVICE-CATEGORY/category/" + product.getCategoryId(),
                        Category.class);
        vo.setProducts(product);
        vo.setCategory(category);
        return vo;
    }
}
