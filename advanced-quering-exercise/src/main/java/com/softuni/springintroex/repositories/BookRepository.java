package com.softuni.springintroex.repositories;
import com.softuni.springintroex.entities.AgeRestriction;
import com.softuni.springintroex.entities.Book;
import com.softuni.springintroex.entities.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Set<Book> findAllByAgeRestriction(AgeRestriction ageRestriction);
    Set<Book> findAllByEditionTypeAndCopiesLessThan(EditionType editionType, int copies);
    Set<Book> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal lowerBound, BigDecimal upperBound);

    @Query("SELECT b FROM Book AS b WHERE substring(b.releaseDate, 0, 4) not like :year")
    Set<Book> findAllByReleaseDateNotInYear(String year);

    Set<Book> findAllByReleaseDateBefore(LocalDate date);

    Set<Book> findAllByAuthorLastNameStartingWith(String input);

    @Query("SELECT count(b) FROM Book AS b WHERE length(b.title) > :length")
    int numberOfBooksWithTitleLength(int length);

    Book findByTitle(String title);

    @Modifying
    @Query("UPDATE Book AS b SET b.copies = b.copies + :copies WHERE b.releaseDate > :date")
    int updateCopies(int copies, LocalDate date);

}
