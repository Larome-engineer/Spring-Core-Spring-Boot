package spring.jdbc.services;

import java.util.ResourceBundle;

public interface IOService {
    void outputMessageByLocale(ResourceBundle message, String key);
    void printPeople(Object o);
}
