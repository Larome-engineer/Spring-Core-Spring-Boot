package spring.jdbc.dao;

import spring.jdbc.models.Person;

import java.util.List;

public interface PersonDAO {
    List<Person> findAll();
    Person findById(int id);
    Person findByName(String name);
}
