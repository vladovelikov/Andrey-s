package springdata.mappingdtolab.dto;
import java.time.LocalDate;
import java.util.Objects;

public class EmployeeDto {

    private Long id;
    private String firstName;
    private String lastName;
    private double salary;
    private LocalDate birthday;
    private String city;
    private String managerLastName;

    public EmployeeDto() {

    }

    public EmployeeDto(String firstName, String lastName, double salary, LocalDate birthday, String city) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.birthday = birthday;
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getManagerLastName() {
        return managerLastName;
    }

    public void setManagerLastName(String managerLastName) {
        this.managerLastName = managerLastName;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("- ").append(firstName).append(" ")
                .append(lastName).append(" ")
                .append(salary).append(" ")
        .append(city).append(" ")
        .append(birthday).append(" ")
        .append(managerLastName);
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeeDto)) return false;
        EmployeeDto that = (EmployeeDto) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
