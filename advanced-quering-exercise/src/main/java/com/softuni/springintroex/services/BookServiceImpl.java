package com.softuni.springintroex.services;
import com.softuni.springintroex.constants.GlobalConstants;
import com.softuni.springintroex.entities.*;
import com.softuni.springintroex.repositories.BookRepository;
import com.softuni.springintroex.services.models.BookInfo;
import com.softuni.springintroex.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final FileUtil fileUtil;
    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    @Autowired
    public BookServiceImpl(FileUtil fileUtil, BookRepository bookRepository, AuthorService authorService, CategoryService categoryService) {
        this.fileUtil = fileUtil;
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @Override
    public void seedBooksInDb() throws IOException {
        String[] fileContent = this.fileUtil.readFileContent(GlobalConstants.BOOKS_FILE_PATH);
        Arrays.stream(fileContent).forEach(row -> {
            String[] params = row.split("\\s+");
            Author author = this.getRandomAuthor();
            EditionType editionType = EditionType.values()[Integer.parseInt(params[0])];
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
            LocalDate releaseDate = LocalDate.parse(params[1], formatter);
            int copies = Integer.parseInt(params[2]);
            BigDecimal price = new BigDecimal(params[3]);
            AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(params[4])];
            StringBuilder sb = new StringBuilder();
            for (int i = 5; i < params.length; i++) {
                sb.append(params[i]).append(" ");
            }
            String title = sb.toString().trim();
            Set<Category> categories = this.getRandomCategories();
            Book book = new Book();
            book.setAuthor(author);
            book.setEditionType(editionType);
            book.setReleaseDate(releaseDate);
            book.setCopies(copies);
            book.setPrice(price);
            book.setAgeRestriction(ageRestriction);
            book.setTitle(title);
            book.setCategories(categories);
            this.bookRepository.saveAndFlush(book);
        });

    }

    @Override
    public void printAllBooksByAgeRestriction(String input) {
        this.bookRepository.findAllByAgeRestriction(AgeRestriction.valueOf(input.toUpperCase()))
                .forEach(b -> System.out.println(b.getTitle()));
    }

    @Override
    public void printAllByEditionTypeAndCopiesLessThan() {
        this.bookRepository.findAllByEditionTypeAndCopiesLessThan(EditionType.GOLD, 5000)
                .forEach(b -> System.out.println(b.getTitle()));
    }

    @Override
    public void printAllByPriceInBounds() {
        this.bookRepository.findAllByPriceLessThanOrPriceGreaterThan(BigDecimal.valueOf(5), BigDecimal.valueOf(40))
                .forEach(b -> System.out.printf("%s - %s%n", b.getTitle(), b.getPrice()));
    }

    @Override
    public void printAllByReleaseDate(String input) {
        this.bookRepository.findAllByReleaseDateNotInYear(input)
        .forEach(b -> System.out.println(b.getTitle()));
    }

    @Override
    public void printAllByReleaseDateBefore(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy");
        LocalDate dateBefore = LocalDate.parse(date, formatter);

        this.bookRepository.findAllByReleaseDateBefore(dateBefore)
                .forEach(b -> System.out.printf("%s %s %s%n", b.getTitle(), b.getEditionType(), b.getPrice()));
    }

    @Override
    public void printAllBooksByAuthorLastNameStartingWith(String input) {
        this.bookRepository.findAllByAuthorLastNameStartingWith(input)
                .forEach(b -> System.out.printf("%s (%s %s)%n",
                        b.getTitle(), b.getAuthor().getFirstName(), b.getAuthor().getLastName()));
    }

    @Override
    public void printCountOfBooksWithGivenLength(String input) {
        System.out.println(this.bookRepository.numberOfBooksWithTitleLength(Integer.parseInt(input)));
    }

    @Override
    public BookInfo findBookByGivenTitle (String title) {
        Book foundBook = this.bookRepository.findByTitle(title);
        BookInfo bookInfo = new BookInfo(foundBook.getTitle(), foundBook.getPrice(), foundBook.getCopies());
        return bookInfo;
    }

    @Override
    public void printUpdatedCopiesCount(String date, int copies) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(date, formatter);
        int updatedRows = this.bookRepository.updateCopies(copies, localDate);
        System.out.println(updatedRows * copies);
    }

    private Set<Category> getRandomCategories() {
        Set<Category> result = new HashSet<>();
        Random random = new Random();
        int categoryRandomId = random.nextInt(this.categoryService.getCategoriesCount()) + 1;

        for (int i = 1; i <= categoryRandomId; i++) {
            result.add(this.categoryService.getCategoryById((long) i));
        }
        return result;
    }

    private Author getRandomAuthor() {
        Random random = new Random();
        int randomId = random.nextInt(this.authorService.getAllAuthorsCount()) + 1;
        return this.authorService.findAuthorById((long) randomId);
    }




}
