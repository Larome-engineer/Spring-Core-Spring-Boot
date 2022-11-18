package spring.jdbc.services;

import java.util.ResourceBundle;

public interface CurrentLocaleService {
    ResourceBundle set(String lang, ResourceBundle message);
}
