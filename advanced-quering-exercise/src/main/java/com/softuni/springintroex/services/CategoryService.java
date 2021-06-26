package com.softuni.springintroex.services;
import com.softuni.springintroex.entities.Category;
import java.io.IOException;

public interface CategoryService {

    void seedCategoriesInDb() throws IOException;
    int getCategoriesCount();
    Category getCategoryById(Long categoryId);
}
