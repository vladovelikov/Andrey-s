package com.softuni.springintroex;
import com.softuni.springintroex.services.AuthorService;
import com.softuni.springintroex.services.BookService;
import com.softuni.springintroex.services.CategoryService;
import com.softuni.springintroex.services.models.BookInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
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
        //seedData();

        //BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //bookService.printAllBooksByAgeRestriction(reader.readLine());

        //bookService.printAllByEditionTypeAndCopiesLessThan();

        //bookService.printAllByPriceInBounds();

        //BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //bookService.printAllByReleaseDate(reader.readLine());

        //BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //bookService.printAllByReleaseDateBefore(reader.readLine());

        //BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //authorService.printAllByFirstNameEndingWith(reader.readLine());

        //BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //bookService.printAllBooksByAuthorLastNameStartingWith(reader.readLine());

        //BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //bookService.printCountOfBooksWithGivenLength(reader.readLine());

        //authorService.printAllAuthorsByBookCopies();

        //BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //BookInfo bookInfo = bookService.findBookByGivenTitle(reader.readLine());
        //System.out.println(bookInfo.getTitle() + " " + bookInfo.getPrice() + " " + bookInfo.getCopies());

        bookService.printUpdatedCopiesCount("06-06-2013", 100);


    }

    private void seedData() throws IOException {
        categoryService.seedCategoriesInDb();
        authorService.seedAuthorsInDb();
        bookService.seedBooksInDb();
    }
}
