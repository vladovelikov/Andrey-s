package springdataexercise.services.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springdataexercise.constants.GlobalConstants;
import springdataexercise.entities.Category;
import springdataexercise.repositories.CategoryRepository;
import springdataexercise.services.CategoryService;
import springdataexercise.utils.FileUtil;
import java.io.IOException;
import java.util.Arrays;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final FileUtil fileUtil;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, FileUtil fileUtil) {
        this.categoryRepository = categoryRepository;
        this.fileUtil = fileUtil;
    }

    @Override
    public void seedCategories() throws IOException {
        String[] fileContent = this.fileUtil.readFileContent(GlobalConstants.CATEGORIES_PATH);
        Arrays.stream(fileContent).forEach(row -> {Category category = new Category(row);
        this.categoryRepository.saveAndFlush(category);
        });

    }

    @Override
    public Category getCategoryById(Long id) {
        return this.categoryRepository.getOne(id);
    }

    @Override
    public int getCategoriesCount() {
        return (int) this.categoryRepository.count();
    }
}
