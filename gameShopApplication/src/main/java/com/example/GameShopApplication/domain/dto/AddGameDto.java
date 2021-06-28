package com.example.GameShopApplication.domain.dto;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.LocalDate;

public class AddGameDto {

    @Pattern(regexp = "[A-Z]+[\\w]*", message = "Title has to begin with uppercase letter")
    @Length(min = 3, max = 100, message = "Title must be between 3 and 100 symbols")
    private String title;
    @DecimalMin(value = "0", message = "Price must be a positive number")
    private BigDecimal price;
    @Min(value = 0, message = "Size must be a positive number")
    private double size;
    @Length(min = 11, max = 11, message = "Trailer must should be 11 symbols")
    private String trailer;
    @Pattern(regexp = "(https|http)[:\\/\\/].+", message = "Image thumbnail is not valid")
    private String imageThumbnail;
    @Length(min = 20, message = "Description length must be at least 20 symbols")
    private String description;
    private LocalDate releaseDate;

    public AddGameDto() {
    }

    public AddGameDto(String title, BigDecimal price, double size, String trailer, String imageThumbnail, String description, LocalDate releaseDate) {
        this.title = title;
        this.price = price;
        this.size = size;
        this.trailer = trailer;
        this.imageThumbnail = imageThumbnail;
        this.description = description;
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
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
}
