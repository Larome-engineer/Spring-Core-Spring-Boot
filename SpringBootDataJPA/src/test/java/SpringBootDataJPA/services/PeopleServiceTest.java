package SpringBootDataJPA.services;

import SpringBootDataJPA.models.Person;
import SpringBootDataJPA.repositories.PeopleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PeopleServiceTest {

    @Mock
    private PeopleRepository peopleRepository;

    @InjectMocks
    private PeopleServiceImpl peopleService;

    @Test
    void findAllPeople() {
        var people = new ArrayList<Person>();
        when(peopleRepository.findAll()).thenReturn(people);

        List<Person> actual = peopleService.findAll();

        assertNotNull(actual);
        assertEquals(people, actual);

        verify(peopleRepository).findAll();

    }

    @Test
    void findPersonById() {
        Person person = mock(Person.class);
        when(peopleRepository.findById(2)).thenReturn(Optional.ofNullable(person));

        Person actual = peopleService.findById(2);

        assertNotNull(actual);
        assertEquals(person, actual);

        verify(peopleRepository).findById(2);
    }

    @Test
    void createNewPerson() {
        Person person = mock(Person.class);

        peopleService.createPerson(person);

        verify(peopleRepository).save(person);
    }

    @Test
    void updatePerson() {
        Person updatedPerson = mock(Person.class);

        peopleService.updatePerson(6, updatedPerson);

        verify(peopleRepository).save(updatedPerson);
    }

    @Test
    void deletePerson() {
        peopleService.deletePerson(4);

        verify(peopleRepository).deleteById(4);
    }

}