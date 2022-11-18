package SpringBootDataJPA.services.serviceInterfaces;

import SpringBootDataJPA.models.Person;

import java.util.List;
import java.util.Optional;

public interface PeopleService {
    List<Person> findAll();
    Person findById(int id);
    void createPerson(Person person);
    void updatePerson(int id, Person person);
    void deletePerson(int id);

}
