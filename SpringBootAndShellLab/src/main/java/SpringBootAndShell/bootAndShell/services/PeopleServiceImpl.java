package SpringBootAndShell.bootAndShell.services;

import SpringBootAndShell.bootAndShell.aop.annotation.MET;
import SpringBootAndShell.bootAndShell.dao.PersonDAO;
import SpringBootAndShell.bootAndShell.models.Person;
import SpringBootAndShell.bootAndShell.services.servicesInterface.PeopleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    public Person findByName(String name) {
        return personDAO.findByName(name);
    }
}
