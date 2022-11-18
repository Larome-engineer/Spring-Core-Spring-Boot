package SpringBootJpaHibernate.services;

import SpringBootJpaHibernate.DAO.PersonDAO;
import SpringBootJpaHibernate.aop.annotation.MET;
import SpringBootJpaHibernate.models.Person;
import SpringBootJpaHibernate.services.serviceInterface.PeopleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PeopleServiceImpl implements PeopleService {

    private final PersonDAO personDAO;

    @MET
    @Override
    public List<Person> all() {
        return personDAO.findAll();
    }

    @MET
    @Override
    public Person byId(int id) {
        return personDAO.findById(id);
    }

    @MET
    @Override
    public void create(Person person) {
        enrichPerson(person);
        personDAO.create(person);
    }

    @MET
    @Override
    public void update(int id, Person person) {
        enrichPerson(person);
        personDAO.update(id, person);
    }
    @MET
    @Override
    public void delete(int id) {
        personDAO.delete(id);
    }

    private void enrichPerson(Person person) {
        person.setCreatedAt(LocalDateTime.now());
        person.setUpdatedAt(LocalDateTime.now());
        person.setCreatedWho("ADMIN");
    }

}
