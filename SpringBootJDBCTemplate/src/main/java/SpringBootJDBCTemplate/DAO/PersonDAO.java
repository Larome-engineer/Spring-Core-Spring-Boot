package SpringBootJDBCTemplate.DAO;

import SpringBootJDBCTemplate.models.Person;

import java.util.List;
import java.util.Optional;

public interface PersonDAO {
    List<Person> findAll();
    Person findById(int id);
    void create(Person person);
    void update(int id, Person person);
    void delete(int id);

}
