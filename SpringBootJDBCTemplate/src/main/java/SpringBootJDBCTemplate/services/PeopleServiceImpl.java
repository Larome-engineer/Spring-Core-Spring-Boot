package SpringBootJDBCTemplate.services;

import SpringBootJDBCTemplate.DAO.PersonDAO;
import SpringBootJDBCTemplate.aop.annotation.MET;
import SpringBootJDBCTemplate.models.Person;
import SpringBootJDBCTemplate.services.serviceInterface.PeopleService;
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
    public List<Person> findAll() {
        return personDAO.findAll();
    }

    @MET
    @Override
    public Person findById(int id) {
        return personDAO.findById(id);
    }

    @MET
    @Override
    public void createPerson(Person person) {
        enrichPerson(person);
        personDAO.create(person);
    }

    @MET
    @Override
    public void updatePerson(int id, Person person) {
        enrichPerson(person);
        personDAO.update(id, person);
    }

    @MET
    @Override
    public void deletePerson(int id) {
        personDAO.delete(id);
    }

    private void enrichPerson(Person person) {
        person.setCreatedAt(LocalDateTime.now());
        person.setUpdatedAt(LocalDateTime.now());
        person.setCreatedWho("ADMIN");
    }

}
