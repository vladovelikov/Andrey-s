package com.softuni.springintroex.services;
import com.softuni.springintroex.entities.Author;
import java.io.IOException;

public interface AuthorService {

    void seedAuthorsInDb() throws IOException;
    int getAllAuthorsCount();
    Author findAuthorById(Long id);
    void printAllByFirstNameEndingWith(String input);

    void printAllAuthorsByBookCopies();
}
