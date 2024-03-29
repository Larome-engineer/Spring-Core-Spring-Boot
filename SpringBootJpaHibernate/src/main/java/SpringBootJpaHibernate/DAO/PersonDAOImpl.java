package SpringBootJpaHibernate.DAO;

import SpringBootJpaHibernate.models.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PersonDAOImpl implements PersonDAO {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public List<Person> findAll() {
        return entityManager.createQuery("select p from Person p", Person.class)
                .getResultList();
    }

    @Override
    public Person findById(int id) {
        return entityManager.find(Person.class, id);
    }

    @Override
    @Transactional
    public void create(Person person) {
        entityManager.persist(person);
    }

    @Override
    @Transactional
    public void update(int id, Person updatedPerson) {
        Person personUpdate = entityManager.find(Person.class, id);

        personUpdate.setName(updatedPerson.getName());
        personUpdate.setAge(updatedPerson.getAge());

        entityManager.merge(personUpdate);
    }

    @Override
    @Transactional
    public void delete(int id) {
        entityManager.remove(entityManager.find(Person.class, id));
    }
}
