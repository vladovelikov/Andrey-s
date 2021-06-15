package springdata.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springdata.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
}
