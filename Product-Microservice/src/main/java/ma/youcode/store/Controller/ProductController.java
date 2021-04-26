package ma.youcode.store.Controller;

import ma.youcode.store.Model.Images;
import ma.youcode.store.Model.Products;
import ma.youcode.store.Services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/product")
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

//    Find user by id

    @GetMapping("/{id}")
    public ResponseEntity<Products> get(@PathVariable Long id) {
        try {
            Products user = productServices.getById(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

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

}
