package SpringBootJpaHibernate.services.serviceInterface;

import SpringBootJpaHibernate.models.Person;

import java.util.List;

public interface PeopleService {
    List<Person> all();
    Person byId(int id);
    void create(Person person);
    void update(int id, Person person);
    void delete(int id);
}
