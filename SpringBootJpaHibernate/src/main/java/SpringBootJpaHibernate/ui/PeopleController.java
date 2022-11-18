package SpringBootJpaHibernate.ui;

import SpringBootJpaHibernate.AppException;
import SpringBootJpaHibernate.DTO.PersonDTO;
import SpringBootJpaHibernate.models.Person;
import SpringBootJpaHibernate.services.serviceInterface.CurrentLocaleService;
import SpringBootJpaHibernate.services.serviceInterface.PeopleService;
import SpringBootJpaHibernate.exception.PersonNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

import java.util.List;
import java.util.stream.Collectors;

@ShellComponent
@RequiredArgsConstructor
public class PeopleController {
    private final PeopleService peopleService;
    private final IO io;
    private final CurrentLocaleService currentLocaleService;
    private final ModelMapper modelMapper;

    public enum State {
        MAIN_MENU("main menu");
        @Getter
        private final String title;

        State(String title) {
            this.title = title;
        }
    }

    @Getter
    private final State state = State.MAIN_MENU;

    @ShellMethod(value = "change current language", key = {"language", "lang"})
    @ShellMethodAvailability("availableInMainMenu")
    public void setLanguage(String lang) {
        try {
            currentLocaleService.set(lang.strip().toLowerCase());
        }
        catch (AppException e) {
            io.interPrintln(e.getMessage(), e.getParams());
        }
    }

    @ShellMethod(key = "findAll")
    public List<PersonDTO> getAll(){
        var people = peopleService.all().stream()
                .map(this::convertToPersonDTO)
                .collect(Collectors.toList());

        if (people.isEmpty()) {
            io.interPrintln("no-people");
            throw new PersonNotFoundException("The list is empty");
        } else {
            io.interPrintln("all-people");
            return people;
        }
    }

    @ShellMethod(key = "findById")
    public PersonDTO getById(int id) {
        var person = convertToPersonDTO(peopleService.byId(id));
        if (person == null) {
            io.interPrintln("no-person-by-id");
            throw new PersonNotFoundException("The person was not found");
        } else {
            io.interPrintln("person-by-id");
            return person;
        }
    }

    @ShellMethod(key = "create")
    public void createPerson(String name, int age) {
        Person person = new Person();
        person.setName(name); person.setAge(age);
        peopleService.create(person);
        io.interPrintln("create-successful");
    }

    @ShellMethod(key = "update")
    public void updatePerson(int id, String name, int age) {
        Person person = new Person();
        person.setId(id);person.setName(name); person.setAge(age);
        peopleService.update(id, person);
        io.interPrintln("update-successful");
    }

    @ShellMethod(key = "delete")
    public void deletePerson(int id) {
        peopleService.delete(id);
        io.interPrintln("delete-successful");
    }

    private PersonDTO convertToPersonDTO(Person person) {
        return modelMapper.map(person, PersonDTO.class);
    }

}