package SpringBootAndShell.bootAndShell.ui;

import SpringBootAndShell.AppException;
import SpringBootAndShell.bootAndShell.DTO.PersonDTO;
import SpringBootAndShell.bootAndShell.models.Person;
import SpringBootAndShell.bootAndShell.services.servicesInterface.CurrentLocaleService;
import SpringBootAndShell.bootAndShell.services.servicesInterface.PeopleService;
import SpringBootAndShell.bootAndShell.exception.PersonNotFoundException;
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
    public PersonDTO getById(int id) {
        var person = convertToPersonDTO(peopleService.findById(id));
        if (person.getName() == null) {
            io.interPrintln("no-person-by-id");
            throw new PersonNotFoundException("The person was not found");
        } else {
            io.interPrintln("person-by-id");
            return person;
        }
    }

    @ShellMethod(value = "show person by name", key = "byName")
    public PersonDTO showPersonByName(String name){
        var person = convertToPersonDTO(peopleService.findByName(name));

        if(person.getName() == null) {
            io.interPrintln("no-person-by-name");
            throw new PersonNotFoundException("The person was not found");

        } else {
            io.interPrintln("person-by-name");
            return person;
        }
    }

    private PersonDTO convertToPersonDTO(Person person) {
        return modelMapper.map(person, PersonDTO.class);
    }

}
