package springdata.mappingdtolab.services;
import springdata.mappingdtolab.entities.Address;
import springdata.mappingdtolab.entities.Employee;
import java.util.List;

public interface AddressService {

    List<Address> getAllAddresses();
    Address getAddressById(Long id);
    Address addAddress(Address address);
    Address updateAddress(Address address);
    Address deleteAddress(Long id);
    long getAddressesCount();
}
