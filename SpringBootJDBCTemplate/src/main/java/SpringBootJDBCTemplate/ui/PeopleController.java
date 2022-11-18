package SpringBootJDBCTemplate.ui;

import SpringBootJDBCTemplate.AppException;
import SpringBootJDBCTemplate.DTO.PersonDTO;
import SpringBootJDBCTemplate.models.Person;
import SpringBootJDBCTemplate.services.serviceInterface.CurrentLocaleService;
import SpringBootJDBCTemplate.services.serviceInterface.PeopleService;
import SpringBootJDBCTemplate.exception.PersonNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;

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

    @ShellMethod(value = "show all people in the table", key = "all")
    @ShellMethodAvailability("availableInMainMenu")
    public List<PersonDTO> showAllPeople(){
        var people = peopleService.findAll().stream()
                .map(this::convertToPersonDTO)
                .collect(Collectors.toList());

        if(people.isEmpty()) {
            io.interPrintln("no-people");
            throw new PersonNotFoundException("People doesn't exist");
        } else {
            io.interPrintln("all-people");
            return people;
        }
    }

    @ShellMethod(value = "show person by id", key = "byId")
    public PersonDTO showPersonById(int id){
        var person = convertToPersonDTO(peopleService.findById(id));
        if(person.getName() == null) {
            io.interPrintln("no-person-by-id");
            throw new PersonNotFoundException("The person was not found");
        } else {
            io.interPrintln("person-by-id");
            return person;
        }
    }

    @ShellMethod(key = "create", value = "Create new user in database")
    public void createNewPerson(@ShellOption("--name") String name,
                             @ShellOption("--age") int age){
        Person person = new Person();
        person.setName(name); person.setAge(age);

        peopleService.createPerson(person);
        io.interPrintln("create-successful");
    }

    @ShellMethod(key = "update", value = "update the user in database by entered ID")
    public void updatePerson(@ShellOption("--id") int id,
                             @ShellOption("--name") String name,
                             @ShellOption("--age") int age){
        Person updatePerson = new Person();
        updatePerson.setId(id); updatePerson.setName(name); updatePerson.setAge(age);

        peopleService.updatePerson(id, updatePerson);
        io.interPrintln("update-successful");
    }

    @ShellMethod(key = "delete", value = "delete the user in database by entered ID")
    public void deletePerson(@ShellOption("--id") int id) {
        peopleService.deletePerson(id);
        io.interPrintln("delete-successful");
    }


    private PersonDTO convertToPersonDTO(Person person) {
        return modelMapper.map(person, PersonDTO.class);
    }

}
