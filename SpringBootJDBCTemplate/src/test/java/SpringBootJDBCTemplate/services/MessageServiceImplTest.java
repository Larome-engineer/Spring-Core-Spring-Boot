package SpringBootJDBCTemplate.services;

import SpringBootJDBCTemplate.DAO.PersonDAOImpl;
import SpringBootJDBCTemplate.models.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MessageServiceImplTest {
    @Mock
    private PersonDAOImpl personDAO;

    @InjectMocks
    private PeopleServiceImpl peopleService;

    @Test
    void findAllPeople() {
        var people = new ArrayList<Person>();
        when(personDAO.findAll()).thenReturn(people);

        List<Person> actual = peopleService.findAll();

        assertNotNull(actual);
        assertEquals(people, actual);

        verify(personDAO).findAll();

    }

    @Test
    void findPersonById() {
        Person person = mock(Person.class);
        when(personDAO.findById(2)).thenReturn(person);

        Person actual = peopleService.findById(2);

        assertNotNull(actual);
        assertEquals(person, actual);

        verify(personDAO).findById(2);
    }

    @Test
    void createNewPerson() {
        Person person = mock(Person.class);

        peopleService.createPerson(person);

        verify(personDAO).create(person);
    }

    @Test
    void updatePerson() {
        Person updatedPerson = mock(Person.class);

        peopleService.updatePerson(6, updatedPerson);

        verify(personDAO).update(6, updatedPerson);
    }

    @Test
    void deletePerson() {
        peopleService.deletePerson(4);

        verify(personDAO).delete(4);
    }

}