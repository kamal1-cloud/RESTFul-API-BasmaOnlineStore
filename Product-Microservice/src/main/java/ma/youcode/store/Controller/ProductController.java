package ma.youcode.store.Controller;


import lombok.extern.slf4j.Slf4j;
import ma.youcode.store.Model.Products;
import ma.youcode.store.Services.ProductServices;
import ma.youcode.store.VO.ResponseTemplateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/product")
@Slf4j
public class ProductController {
    @Autowired
    ProductServices productServices;

    // Fetch all Products records
    @GetMapping
    public List<Products> listProducts() {
        return productServices.listAll();
    }

    // Insert Product record
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Products newProduct(@RequestBody Products product) {

        return productServices.save(product);
    }

//    Find Product by id

//    @GetMapping("/{id}")
//    public ResponseEntity<Products> get(@PathVariable Long id) {
//        try {
//            Products product = productServices.getById(id);
//            return new ResponseEntity<>(product, HttpStatus.OK);
//        } catch (NoSuchElementException e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

    // Update Product record
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Products product, @PathVariable Long id) {
        try {
            Products existProduct = productServices.getById(id);
            productServices.save(product);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    // Delete Product
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        try {
            productServices.delete(id);
            return new ResponseEntity<String>(HttpStatus.OK);
        }catch(RuntimeException ex){
            // log the error message
            System.out.println(ex.getMessage());
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
    }

    //    Find Product by id and category

    @GetMapping("/{id}")
   public ResponseTemplateVO getProductWithCatgory (@PathVariable("id") Long productId){
//        log.info("Inside getProductWithCatgory of ProductController");
        return productServices.getProductWithCatgory(productId);
   }

}
