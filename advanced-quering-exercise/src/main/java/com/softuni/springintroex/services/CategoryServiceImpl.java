package com.softuni.springintroex.services;
import com.softuni.springintroex.constants.GlobalConstants;
import com.softuni.springintroex.entities.Category;
import com.softuni.springintroex.repositories.CategoryRepository;
import com.softuni.springintroex.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final FileUtil fileUtil;
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(FileUtil fileUtil, CategoryRepository categoryRepository) {
        this.fileUtil = fileUtil;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void seedCategoriesInDb() throws IOException {
        String[] lines = fileUtil.readFileContent(GlobalConstants.CATEGORIES_FILE_PATH);
        for (String line : lines) {
            Category category = new Category(line);
            this.categoryRepository.saveAndFlush(category);
        }
    }

    @Override
    public int getCategoriesCount() {
        return (int) this.categoryRepository.count();
    }

    @Override
    public Category getCategoryById(Long categoryId) {
        return this.categoryRepository.getOne(categoryId);
    }
}
