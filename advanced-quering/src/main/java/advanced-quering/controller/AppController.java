package springdataadvancedqueringlab.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import springdataadvancedqueringlab.repositories.IngredientRepository;
import springdataadvancedqueringlab.repositories.LabelRepository;
import springdataadvancedqueringlab.repositories.ShampooRepository;
import java.util.Set;

@Component
@Transactional
public class AppController implements CommandLineRunner {

    private final ShampooRepository shampooRepository;
    private final IngredientRepository ingredientRepository;
    private final LabelRepository labelRepository;

    @Autowired
    public AppController(ShampooRepository shampooRepository, IngredientRepository ingredientRepository, LabelRepository labelRepository) {
        this.shampooRepository = shampooRepository;
        this.ingredientRepository = ingredientRepository;
        this.labelRepository = labelRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        //List<Shampoo> mediumShampoos = shampooRepository.findBySizeOrderById(MEDIUM);
        //mediumShampoos.forEach(shampoo -> System.out.println(shampoo.toString()));

        //shampooRepository.findByIngredientsIn(Set.of("Berry", "Mineral-Collagen")).forEach(s -> System.out.println(s.getBrand()));

       //shampooRepository.findBySizeOrLabelOrderByPrice(MEDIUM, labelRepository.findByTitle("Power Volume").get())
       //         .forEach(System.out::println);

        //shampooRepository.findByPriceBetweenOrderByPriceDesc(5.0, 100)
       // .forEach(System.out::println);

       // ingredientRepository.findByNameInOrderByPriceAsc(List.of("Lavender", "Herbs", "Apple"))
       // .forEach(System.out::println);

       // int size = shampooRepository.findByPriceLessThan(8.50).size();
       // System.out.println(size);

       // shampooRepository.findByIngredientsLessThan(2).forEach(System.out::println);

        //DeleteIngredientsByName
        //Ingredient ingredientToDelete = ingredientRepository.findByName("Macadamia Oil");
        //List<Shampoo> shampoosByIngredient = shampooRepository.findByIngredient(ingredientToDelete);
        //shampoosByIngredient.forEach(shampoo -> shampoo.getIngredients().remove(ingredientToDelete));
        //System.out.printf("Number of deleted ingredients: %d%n", ingredientRepository.deleteByName("Macadamia Oil"));

        System.out.println(
                ingredientRepository.updatePriceOfIngredientsInList(Set.of("Lavender", "Herbs", "Apple"), 1.10));




    }
}
