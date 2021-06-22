package springdataadvancedqueringlab.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import springdataadvancedqueringlab.entities.Ingredient;
import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    List<Ingredient> findByNameInOrderByPriceAsc(List<String> names);

    int deleteByName(String name);

    Ingredient findByName(String name);

    @Modifying
    @Query("UPDATE Ingredient AS i SET i.price = i.price * :percentage WHERE i.name IN :ingredient_names")
    int updatePriceOfIngredientsInList(@Param("ingredient_names") Iterable<String> ingredientNames,
                                       @Param("percentage") double percentage);
}
