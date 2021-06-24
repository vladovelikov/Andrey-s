package springdata.mappingdtolab.services;
import org.springframework.stereotype.Service;
import springdata.mappingdtolab.entities.Employee;
import java.util.List;

@Service
public interface EmployeeService {

    List<Employee> getAllEmployees();
    Employee getEmployeeById(Long id);
    Employee addEmployee(Employee employee);
    Employee updateEmployee(Employee employee);
    Employee deleteEmployee(Long id);
    long getEmployeesCount();
    List<Employee> getAllManagers();
    List<Employee> getAllEmployeesByBirthday(String date);

}
