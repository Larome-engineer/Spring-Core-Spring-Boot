package SpringBootMVCREST.services;

import SpringBootMVCREST.aop.annotation.MET;
import SpringBootMVCREST.repositories.PeopleRepository;
import SpringBootMVCREST.utils.exceptions.PersonNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import SpringBootMVCREST.models.Person;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PeopleService {

    private final PeopleRepository peopleRepository;

    @MET
    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    @MET
    public Person findById(int id) {
        Optional<Person> foundPerson = peopleRepository.findById(id);
        return foundPerson.orElseThrow(PersonNotFoundException::new);
    }

    @MET
    @Transactional
    @Deprecated // Повесил для проверки отрабатывания WARNING на "Deprecated-методе" при создании человека
    public void create(Person person) {
        enrichPerson(person);
        peopleRepository.save(person);
    }

    @MET
    @Transactional
    public void update(Person person, int id) {
        person.setId(id);
        enrichPerson(person);
        peopleRepository.save(person);
    }

    @MET
    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }

    private void enrichPerson(Person person) {
        person.setCreatedAt(LocalDateTime.now());
        person.setUpdatedAt(LocalDateTime.now());
        person.setCreatedWho("ADMIN");
    }

}
