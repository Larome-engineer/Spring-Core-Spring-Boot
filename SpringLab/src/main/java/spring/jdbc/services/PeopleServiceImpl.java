package spring.jdbc.services;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import spring.jdbc.dao.PersonDAOImpl;
import spring.jdbc.models.Person;

import java.util.List;

@Service("peopleService")
@RequiredArgsConstructor
public class PeopleServiceImpl implements PeopleService{

    private final String service = String.valueOf(getClass());
    private final Logger logger;

    private final PersonDAOImpl personDAO;

    @Override
    public List<Person> findAll() {
        logger.info("Вызов метода 'findAll'");
        return personDAO.findAll();
    }

    @Override
    public Person findById(int id) {
        logger.info("Вызов метода 'findById' по id: " + id + ". Из сервиса: " + service);
        return personDAO.findById(id);
    }

    @Override
    public Person findByName(String name) {
        logger.info("Вызов метода 'findAByName' человека с именем: " + name + ". Из сервиса: " + service);
        return personDAO.findByName(name);
    }
}
