package ma.youcode.store.Services;

import ma.youcode.store.Model.Products;
import ma.youcode.store.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServices {
    @Autowired
    ProductRepository productRepository;

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
}
