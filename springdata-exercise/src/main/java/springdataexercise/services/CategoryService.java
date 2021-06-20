package springdataexercise.services;
import springdataexercise.entities.Category;
import java.io.IOException;

public interface CategoryService {

    void seedCategories() throws IOException;
    Category getCategoryById(Long id);
    int getCategoriesCount();
}
