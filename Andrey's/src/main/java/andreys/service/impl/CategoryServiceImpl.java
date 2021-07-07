package andreys.service.impl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import andreys.model.entity.Category;
import andreys.model.entity.CategoryName;
import andreys.repository.CategoryRepository;
import andreys.service.CategoryService;

import java.util.Arrays;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void initCategories() {
        if (this.categoryRepository.count() == 0) {
            Arrays.stream(CategoryName.values())
                    .forEach(categoryName ->
                            this.categoryRepository.saveAndFlush(new Category
                                    (categoryName, String.format("Description for %s", categoryName.name()))));
        }
    }

    @Override
    public Category findByCategoryName(CategoryName categoryName) {
        return this.categoryRepository.findByName(categoryName)
                .orElse(null);
    }
}
