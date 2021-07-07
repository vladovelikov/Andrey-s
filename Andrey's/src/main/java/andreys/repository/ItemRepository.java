package andreys.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import andreys.model.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, String> {
}
