package springdata.mappingdtolab.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springdata.mappingdtolab.entities.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
