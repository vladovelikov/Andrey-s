package springdata.mappingdtolab.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springdata.mappingdtolab.entities.Address;
import springdata.mappingdtolab.exceptions.NonExistingEntityException;
import springdata.mappingdtolab.repositories.AddressRepository;
import java.util.List;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public List<Address> getAllAddresses() {
        return this.addressRepository.findAll();
    }

    @Override
    public Address getAddressById(Long id) {
        return this.addressRepository.findById(id).orElseThrow(
                () -> new NonExistingEntityException(String.format("Address with ID - %s does not exist.", id))
        );
    }

    @Override
    public Address addAddress(Address address) {
        address.setId(null);
        return this.addressRepository.saveAndFlush(address);
    }

    @Override
    public Address updateAddress(Address address) {
        this.getAddressById(address.getId());
        return this.addressRepository.save(address);
    }

    @Override
    public Address deleteAddress(Long id) {
        Address address = this.getAddressById(id);
        this.addressRepository.delete(address);
        return address;
    }

    @Override
    public long getAddressesCount() {
        return this.addressRepository.count();
    }
}
