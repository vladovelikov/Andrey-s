package com.example.GameShopApplication.domain.entities;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "games")
public class Game extends BaseEntity {

    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "trailer", nullable = false)
    private String trailer;
    @Column(name = "image_thumbnail", nullable = false)
    private String imageThumbnail;
    @Column(name = "size", nullable = false)
    private double size;
    @Column(name = "price", nullable = false)
    private BigDecimal price;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "release_date", nullable = false)
    private LocalDate releaseDate;
    @ManyToMany(mappedBy = "games", targetEntity = User.class)
    private Set<User> users;

    public Game() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getImageThumbnail() {
        return imageThumbnail;
    }

    public void setImageThumbnail(String imageThumbnail) {
        this.imageThumbnail = imageThumbnail;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
