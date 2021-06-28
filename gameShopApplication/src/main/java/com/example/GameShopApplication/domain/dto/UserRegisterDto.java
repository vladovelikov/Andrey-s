package com.example.GameShopApplication.domain.dto;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.Pattern;

public class UserRegisterDto {

    @Pattern(regexp = "[\\w]*[@][\\w]*[.][\\w]*", message = "Email must contain @ sign and a period")
    private String email;
    @Length(min = 6, message = "Password length must be at least 6 symbols")
    @Pattern(regexp = "[A-z]+[0-9]+", message = "Password must contain at least 1 uppercase, 1 lowercase letter and 1 digit")
    private String password;
    private String confirmPassword;
    private String fullName;

    public UserRegisterDto() {
    }

    public UserRegisterDto(String email, String password, String confirmPassword, String fullName) {
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
