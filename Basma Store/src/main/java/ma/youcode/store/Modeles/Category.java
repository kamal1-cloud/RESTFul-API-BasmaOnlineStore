package com.basmaonline.com.basmaonline.model;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="category")
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="categoryId")
    private Long categoryId;

    @Column(name = "categoryName", nullable = false, length = 55)
    private String categoryName;

    @Column(name = "categoryDescription", nullable = false, length = 250)
    private String categoryDescription; // MIN

    @Column(name = "categoryStatus", columnDefinition = "BOOLEAN DEFAULT true")
    private boolean categoryStatus; // true

    @Column(name = "categoryImage")
    private String categoryImageUrl;

    public Category() {
    }

    public Category(String categoryName, String categoryDescription, String categoryImageUrl) {
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
        this.categoryImageUrl = categoryImageUrl;
    }

//    public Category(String categoryName, String categoryDescription) {
//        this.categoryName = categoryName;
//        this.categoryDescription = categoryDescription;
//    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public String getCategoryImageUrl() {
        return categoryImageUrl;
    }

    public void setCategoryImageUrl(String categoryImageUrl) {
        this.categoryImageUrl = categoryImageUrl;
    }

    public boolean isCategoryStatus() {
        return categoryStatus;
    }

    public void setCategoryStatus(boolean categoryStatus) {
        this.categoryStatus = categoryStatus;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", categoryDescription='" + categoryDescription + '\'' +
                ", categoryStatus=" + categoryStatus +
                ", categoryImageUrl='" + categoryImageUrl + '\'' +
                '}';
    }
}
