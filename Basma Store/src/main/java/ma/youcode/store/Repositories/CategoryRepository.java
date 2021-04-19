package com.basmaonline.com.basmaonline.repositories;

import com.basmaonline.com.basmaonline.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
