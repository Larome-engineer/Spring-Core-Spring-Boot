package spring.jdbc.services;

import spring.jdbc.models.Person;
import java.util.List;

public interface PeopleService {

    List<Person> findAll();
    Person findById(int id);
    Person findByName(String name);
}
