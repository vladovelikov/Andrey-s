package springdata.mappingdtolab.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springdata.mappingdtolab.entities.Employee;
import springdata.mappingdtolab.exceptions.NonExistingEntityException;
import springdata.mappingdtolab.repositories.EmployeeRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return this.employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return this.employeeRepository.findById(id).orElseThrow(
                () -> new NonExistingEntityException(String.format("Employee with ID - %s does not exist.", id)));
    }

    @Override
    @Transactional
    public Employee addEmployee(Employee employee) {
        employee.setId(null);
        if(employee.getManager() != null) {
            employee.getManager().getSubordinates().add(employee);
        }
        return this.employeeRepository.save(employee);
    }

    @Override
    @Transactional
    public Employee updateEmployee(Employee employee) {
        Employee existing = this.getEmployeeById(employee.getId());
        Employee updated = this.employeeRepository.save(employee);
        if(existing.getManager() != null && !existing.getManager().equals(updated.getManager())) {  // ako employee ima nov manager trqbva da go premahnem kato pod4inen na stariq manager
            existing.getManager().getSubordinates().remove(existing);
        }
        if(updated.getManager() != null && !updated.getManager().equals(existing.getManager())) {  // ako employee ima nov manager trqbva da dobavim employee kato pod4inen na noviq manager
            updated.getManager().getSubordinates().add(updated);
        }
        return updated;
    }

    @Override
    @Transactional
    public Employee deleteEmployee(Long id) {
        Employee employee = getEmployeeById(id);
        if (employee.getManager() != null) {
            employee.getManager().getSubordinates().remove(employee);
        }
        this.employeeRepository.delete(employee);
        return employee;
    }

    @Override
    public long getEmployeesCount() {
        return this.employeeRepository.count();
    }

    @Override
    public List<Employee> getAllManagers() {
        return this.employeeRepository.getManagers();
    }

    @Override
    public List<Employee> getAllEmployeesByBirthday(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        LocalDate localDate = LocalDate.parse(date, formatter);
        return this.employeeRepository.findAllByBirthdayBeforeOrderBySalaryDesc(localDate);
    }
}
