package springdataexercise.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springdataexercise.entities.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

}
