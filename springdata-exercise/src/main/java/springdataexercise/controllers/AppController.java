package springdataexercise.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import springdataexercise.services.AuthorService;
import springdataexercise.services.BookService;
import springdataexercise.services.CategoryService;

@Controller
public class AppController implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;

    @Autowired
    public AppController(CategoryService categoryService, AuthorService authorService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.categoryService.seedCategories();
        this.authorService.seedAuthors();
        this.bookService.seedBooks();

        //List<Book> booksAfter2000 = this.bookService.getAllBooksAfter2000();
        //booksAfter2000.forEach(b -> System.out.println(b.toString()));

    }
}
