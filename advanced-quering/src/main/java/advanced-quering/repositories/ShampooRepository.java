package springdataadvancedqueringlab.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import springdataadvancedqueringlab.entities.Ingredient;
import springdataadvancedqueringlab.entities.Label;
import springdataadvancedqueringlab.entities.Shampoo;
import springdataadvancedqueringlab.entities.Size;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ShampooRepository extends JpaRepository<Shampoo, Long> {

    List<Shampoo> findBySizeOrderById(Size size);

    @Query("SELECT s FROM Shampoo AS s JOIN s.ingredients AS i WHERE i.name IN :ingredient_names")
    List<Shampoo> findByIngredientsIn(@Param("ingredient_names") Set<String> ingredient_names);

    List<Shampoo> findBySizeOrLabelOrderByPrice(Size size, Label s);

    List<Shampoo> findByPriceBetweenOrderByPriceDesc(double lowestPrice, double highestPrice);

    List<Shampoo> findByPriceLessThan(double price);

    @Query("SELECT s FROM Shampoo AS s, IN(s.ingredients) AS i WHERE i = :ingredient")
    List<Shampoo> findByIngredient(Ingredient ingredient);

    @Query("SELECT s FROM Shampoo AS s WHERE s.ingredients.size < :count")
    List<Shampoo> findByIngredientsLessThan(@Param("count") int count);
}
