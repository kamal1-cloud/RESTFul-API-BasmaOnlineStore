package ma.youcode.store.Services;

import ma.youcode.store.Model.Images;
import ma.youcode.store.Model.Products;
import ma.youcode.store.Repository.ImagesRepository;
import ma.youcode.store.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImagesServices {
    @Autowired
    ImagesRepository imagesRepository;

    public List<Images> listAll() {
        return imagesRepository.findAll();
    }
    public Images save(Images image){
        imagesRepository.save(image);
        return image;
    }
    public Images getById(Long id){
        return imagesRepository.findById(id).get();
    }
    public void delete(Long id){
        imagesRepository.deleteById(id);
    }
}
