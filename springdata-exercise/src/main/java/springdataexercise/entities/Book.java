package springdataexercise.entities;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "books")
public class Book extends BaseEntity {

    @Column(length = 50, nullable = false)
    private String title;
    @Column(length = 1000)
    private String description;
    @Column(name = "edition_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private EditionType editionType;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(name = "release_date")
    private LocalDate releaseDate;
    @Column(name = "copies", nullable = false)
    private int copies;
    @Column(name = "age_restriction", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private AgeRestriction ageRestriction;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Author author;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Category> categories;

    public Book() { }

    public Book(String title, String description, EditionType editionType, BigDecimal price,
                LocalDate releaseDate, int copies, AgeRestriction ageRestriction, Author author, Set<Category> categories) {
        this.title = title;
        this.description = description;
        this.editionType = editionType;
        this.price = price;
        this.releaseDate = releaseDate;
        this.copies = copies;
        this.ageRestriction = ageRestriction;
        this.author = author;
        this.categories = categories;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EditionType getEditionType() {
        return editionType;
    }

    public void setEditionType(EditionType editionType) {
        this.editionType = editionType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public AgeRestriction getAgeRestriction() {
        return ageRestriction;
    }

    public void setAgeRestriction(AgeRestriction ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return this.getId().equals(book.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + this.getId() +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", editionType=" + editionType +
                ", price=" + price +
                ", releaseDate=" + releaseDate +
                ", copies=" + copies +
                ", ageRestriction=" + ageRestriction +
                ", author=" + author +
                ", categories=" + categories +
                '}';
    }
}
