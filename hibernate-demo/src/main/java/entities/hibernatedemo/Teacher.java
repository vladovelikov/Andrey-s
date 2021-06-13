package entities.hibernatedemo;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "teachers")
public class Teacher extends Person {

    @Column(name = "speciality")
    private String speciality;
    @OneToMany(mappedBy = "teacher", targetEntity = Course.class)     //edin uchitel mnogo kursove
    private Set<Course> courses;

    public Teacher() {
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
}
