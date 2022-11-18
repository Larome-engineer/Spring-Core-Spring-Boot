package SpringBootDataJPA.services;

import SpringBootDataJPA.aop.annotation.MET;
import SpringBootDataJPA.aop.annotation.MyDeprecated;
import SpringBootDataJPA.models.Person;
import SpringBootDataJPA.repositories.PeopleRepository;
import SpringBootDataJPA.services.serviceInterfaces.PeopleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

//  @MET - Measuring the Execution Time (MET)
//  @MyDeprecated - My exact counterpart of the Deprecated annotation

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PeopleServiceImpl implements PeopleService {

    private final PeopleRepository peopleRepository;

    @MET
    @Override
    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    @MET
    @Override
    public Person findById(int id) {
        Optional<Person> foundPerson = peopleRepository.findById(id);
        return foundPerson.orElse(null);
    }

    @MET
    @Override
    @Transactional
    public void createPerson(Person person) {
        enrichPerson(person);
        peopleRepository.save(person);
    }

    @MET
    @Override
    @Transactional
    public void updatePerson(int id, Person person) {
        enrichPerson(person);
        peopleRepository.save(person);
    }

    @MET
    @Override
    @Transactional
    public void deletePerson(int id) {
        peopleRepository.deleteById(id);
    }

    private void enrichPerson(Person person){
        person.setCreatedAt(LocalDateTime.now());
        person.setUpdatedAt(LocalDateTime.now());
        person.setCreatedWho("ADMIN");
    }
}
