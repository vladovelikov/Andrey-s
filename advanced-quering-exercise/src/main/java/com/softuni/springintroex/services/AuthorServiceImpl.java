package com.softuni.springintroex.services;
import com.softuni.springintroex.constants.GlobalConstants;
import com.softuni.springintroex.entities.Author;
import com.softuni.springintroex.entities.Book;
import com.softuni.springintroex.repositories.AuthorRepository;
import com.softuni.springintroex.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.*;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final FileUtil fileUtil;
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(FileUtil fileUtil, AuthorRepository authorRepository) {
        this.fileUtil = fileUtil;
        this.authorRepository = authorRepository;
    }

    @Override
    public void seedAuthorsInDb() throws IOException {
        String[] lines = fileUtil.readFileContent(GlobalConstants.AUTHORS_FILE_PATH);
        for (String line : lines) {
            String[] names = line.split(" ");
            Author author = new Author(names[0], names[1]);
            this.authorRepository.saveAndFlush(author);
        }
    }

    @Override
    public int getAllAuthorsCount() {
        return (int) this.authorRepository.count();
    }

    @Override
    public Author findAuthorById(Long id) {
        return this.authorRepository.getOne(id);
    }

    @Override
    public void printAllByFirstNameEndingWith(String input) {
        this.authorRepository.findAllByFirstNameEndingWith(input).forEach(a -> System.out.printf("%s %s%n", a.getFirstName(), a.getLastName()));
    }

    @Override
    public void printAllAuthorsByBookCopies() {
        List<Author> authors = this.authorRepository.findAll();
        Map<String, Integer> authorCopies = new HashMap<>();

        authors.forEach(a -> {
            int copies = a.getBooks().stream().mapToInt(Book::getCopies).sum();
            authorCopies.put(a.getFirstName() + " " + a.getLastName(), copies);
        });

        authorCopies.entrySet().stream()
                .sorted((f, s) -> Integer.compare(s.getValue(), f.getValue()))
        .forEach(a -> System.out.printf("%s - %d%n", a.getKey(), a.getValue()));


    }


}
