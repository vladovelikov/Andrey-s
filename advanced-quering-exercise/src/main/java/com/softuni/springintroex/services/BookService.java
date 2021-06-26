package com.softuni.springintroex.services;
import com.softuni.springintroex.services.models.BookInfo;
import java.io.IOException;

public interface BookService {

    void seedBooksInDb() throws IOException;
    void printAllBooksByAgeRestriction(String input);
    void printAllByEditionTypeAndCopiesLessThan();
    void printAllByPriceInBounds();
    void printAllByReleaseDate(String input);
    void printAllByReleaseDateBefore(String date);

    void printAllBooksByAuthorLastNameStartingWith(String input);

    void printCountOfBooksWithGivenLength(String length);

    BookInfo findBookByGivenTitle (String title);

    void printUpdatedCopiesCount(String date, int copies);
}
