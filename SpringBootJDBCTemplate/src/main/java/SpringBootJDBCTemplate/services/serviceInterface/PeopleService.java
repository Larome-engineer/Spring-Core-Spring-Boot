package SpringBootJDBCTemplate.services.serviceInterface;

import SpringBootJDBCTemplate.models.Person;

import java.util.List;

public interface PeopleService {
    List<Person> findAll();
    Person findById(int id);
    void createPerson(Person person);
    void updatePerson(int id, Person person);
    void deletePerson(int id);
}
