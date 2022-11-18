package SpringBootAndShell.bootAndShell.services.servicesInterface;

import SpringBootAndShell.bootAndShell.models.Person;
import java.util.List;
import java.util.Optional;


public interface PeopleService {
    List<Person> findAll();
    Person findById(int id);
    Person findByName(String name);


}


