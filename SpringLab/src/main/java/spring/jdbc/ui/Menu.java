package spring.jdbc.ui;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import spring.jdbc.services.CurrentLocaleService;
import spring.jdbc.services.IOService;
import spring.jdbc.services.PeopleService;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

@Component("menu")
@RequiredArgsConstructor
public class Menu {
    private final PeopleService peopleService;
    private final CurrentLocaleService localeService;
    private final IOService ioService;
    private final Scanner scanner;

    public void menu() {

        var message = ResourceBundle.getBundle("bundle", new Locale("en"));
        boolean x = true;

        while (x) {
            ioService.outputMessageByLocale(message, "input-command");
            switch (scanner.next()) {
                case "-findAll", "1" -> {
                    if (peopleService.findAll().isEmpty()) {
                        ioService.outputMessageByLocale(message, "no-people");
                    }
                    ioService.outputMessageByLocale(message, "all-people");
                    ioService.printPeople(peopleService.findAll());
                }
                case "-findById", "2" -> {
                    ioService.outputMessageByLocale(message, "input-id");
                    int id = scanner.nextInt();

                    if (peopleService.findById(id).getName() == null) {
                        ioService.outputMessageByLocale(message, "no-person-by-id");
                    } else {
                        ioService.outputMessageByLocale(message, "person-by-id");
                        ioService.printPeople(peopleService.findById(id));
                    }
                }
                case "-findByName", "3" -> {
                    ioService.outputMessageByLocale(message, "input-name");
                    var name = scanner.next();

                    if (peopleService.findByName(name).getName() == null) {
                        ioService.outputMessageByLocale(message, "no-person-by-name");
                    } else {
                        ioService.outputMessageByLocale(message, "person-by-name");
                        ioService.printPeople(peopleService.findByName(name));
                    }
                }
                case "-cLang", "4" -> {
                    ioService.outputMessageByLocale(message, "input-locale");
                    var lang = scanner.next();
                    message = localeService.set(lang, message);
                }
                case "-exit", "5" -> {
                    ioService.outputMessageByLocale(message, "bye-bye");
                    x = false;
                }
                default -> ioService.outputMessageByLocale(message, "dont-under");
            }
        }
    }
}


