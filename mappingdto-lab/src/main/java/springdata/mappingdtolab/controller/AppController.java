package springdata.mappingdtolab.controller;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import springdata.mappingdtolab.dto.EmployeeDto;
import springdata.mappingdtolab.dto.ManagerDto;
import springdata.mappingdtolab.entities.Address;
import springdata.mappingdtolab.entities.Employee;
import springdata.mappingdtolab.services.AddressService;
import springdata.mappingdtolab.services.EmployeeService;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AppController implements CommandLineRunner {

    private final AddressService addressService;
    private final EmployeeService employeeService;

    @Autowired
    public AppController(AddressService addressService, EmployeeService employeeService) {
        this.addressService = addressService;
        this.employeeService = employeeService;
    }

    @Override
    public void run(String... args) throws Exception {
        //ModelMapper modelMapper = new ModelMapper();
        //Address address1 = new Address("Bulgaria", "Sofia", "Graph Ignatieph 50");
        //addressService.addAddress(address1);
        //Employee employee1 = new Employee("Trayan", "Iliev", 2000,
        //        LocalDate.of(1981, 5, 12), address1);
        //employee1 = employeeService.addEmployee(employee1);
        //EmployeeDto employeeDto = modelMapper.map(employee1, EmployeeDto.class);
        //System.out.printf("EmployeeDto: %s%n", employeeDto.toString());

        //TypeMap mapping addresses and employees to ManagerDto with EmployeeDtos' like subordinates

        List<Address> addresses = List.of(
                new Address("Bulgaria", "Sofia", "ul. G.S. Rakovski 50"),
                new Address("Bulgaria", "Sofia", "bul.Dondukov 45"),
                new Address("Bulgaria", "Sofia", "ul. Hristo Smirnenski 46"),
                new Address("Bulgaria", "Sofia", "bul. Alexandar Malinov 45"),
                new Address("Bulgaria", "Sofia", "bul. Slivnitsa 50"),
                new Address("Bulgaria", "Plovdiv", "ul. Angel Kanchev 34")
        );

        addresses = addresses.stream().map(addressService::addAddress).collect(Collectors.toList());

        List<Employee> employees = List.of(
                new Employee("Steve", "Adams", 5000,
                        LocalDate.of(1981, 5, 12), addresses.get(0)),
                new Employee("Stephen", "Petrov", 2768,
                        LocalDate.of(1982, 6, 23), addresses.get(1)),
                new Employee("Hristina", "Petrova", 3000,
                        LocalDate.of(1985, 5, 17), addresses.get(1)),
                new Employee("Diana", "Atanasova", 5000,
                        LocalDate.of(1983, 2, 15), addresses.get(2)),
                new Employee("Samuil", "Atanasov", 4000,
                        LocalDate.of(1984, 3, 12), addresses.get(3)),
                new Employee("Ivan", "Petrov", 3768,
                        LocalDate.of(1986, 4, 11), addresses.get(4)),
                new Employee("Ivan", "Petrov", 3988,
                        LocalDate.of(1987, 6, 17), addresses.get(5))
        );

        List<Employee> created = employees.stream().map(employeeService::addEmployee).collect(Collectors.toList());

        // Add managers and updated employees

        created.get(1).setManager(created.get(0));
        created.get(2).setManager(created.get(0));
        created.get(4).setManager(created.get(3));
        created.get(5).setManager(created.get(3));
        created.get(6).setManager(created.get(3));

        List<Employee> updated = created.stream().map(employeeService::updateEmployee).collect(Collectors.toList());

        //Fetch all managers and map them to ManagerDto

        ModelMapper modelMapper = new ModelMapper();
        TypeMap<Employee, ManagerDto> managerTypeMap = modelMapper.createTypeMap(Employee.class, ManagerDto.class);
        managerTypeMap.addMappings(m -> {
            m.map(Employee::getSubordinates, ManagerDto::setEmployees);
            m.map(src -> src.getAddress().getCity(), ManagerDto::setCity);
        });

        TypeMap<Employee, EmployeeDto> employeeDtoTypeMap = modelMapper.createTypeMap(Employee.class, EmployeeDto.class);
        employeeDtoTypeMap
                .addMappings(m -> m.map(src -> src.getAddress().getCity(), EmployeeDto::setCity));

        updated.forEach(employeeDtoTypeMap::map);

        List<Employee> managers = employeeService.getAllManagers();

        List<ManagerDto> managerDtos = managers.stream().map(managerTypeMap::map).collect(Collectors.toList());

        //managerTypeMap.validate();

        //managerDtos.forEach(System.out::println);

        // Retrieve employees born before 1990 and transform them to EmployeeDtos

        List<Employee> employeesBefore1990 = employeeService.getAllEmployeesByBirthday("01/01/1990");

        employeeDtoTypeMap.addMappings(m -> m.map(src -> src.getManager().getLastName(), EmployeeDto::setManagerLastName));

        List<EmployeeDto> employeeDtos = employeesBefore1990.stream().map(employeeDtoTypeMap::map).collect(Collectors.toList());

        employeeDtos.forEach(System.out::println);


    }
}
