package SpringBootAndShell.bootAndShell.dao;

import SpringBootAndShell.bootAndShell.models.Person;
import java.util.List;

public interface PersonDAO {
    List<Person> findAll();
    Person findById(int id);
    Person findByName(String name);
}
