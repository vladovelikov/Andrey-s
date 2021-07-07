package andreys.service;
import andreys.model.entity.Category;
import andreys.model.entity.CategoryName;

public interface CategoryService {

    void initCategories();
    Category findByCategoryName(CategoryName categoryName);
}
