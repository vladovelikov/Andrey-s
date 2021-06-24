package springdata.mappingdtolab.dto;
import java.util.List;
import java.util.stream.Collectors;

public class ManagerDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String city;
    private List<EmployeeDto> employees;

    public ManagerDto() {
    }

    public ManagerDto(String firstName, String lastName, String city, List<EmployeeDto> subordinates) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.employees = subordinates;
    }

    private int getSubordinatesNumber() {
        return this.employees.size();
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<EmployeeDto> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeDto> subordinates) {
        this.employees = subordinates;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(id).append(": ");
        sb.append(firstName).append(" ");
        sb.append(lastName);
        sb.append(", employees = ").append(this.getSubordinatesNumber());
        sb.append(System.lineSeparator());
        sb.append(employees.stream().map(EmployeeDto::toString).collect(Collectors.joining("\n")));
        return sb.toString();
    }
}
