package SpringBootJpaHibernate.DAO;

import SpringBootJpaHibernate.models.Person;

import java.util.List;

public interface PersonDAO {
    List<Person> findAll();
    Person findById(int id);
    void create(Person person);
    void update(int id, Person updatedPerson);
    void delete(int id);
}
