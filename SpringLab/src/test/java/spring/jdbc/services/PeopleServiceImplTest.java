package spring.jdbc.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import spring.jdbc.dao.PersonDAOImpl;
import spring.jdbc.models.Person;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PeopleServiceImplTest {

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
    void findPersonByName() {
        Person person = mock(Person.class);
        when(personDAO.findByName("Roman")).thenReturn(person);

        Person actual = peopleService.findByName("Roman");

        assertNotNull(actual);
        assertEquals(person, actual);

        verify(personDAO).findByName("Roman");
    }
}
