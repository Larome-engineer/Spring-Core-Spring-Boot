package spring.jdbc.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;
@Service("localeService")
@RequiredArgsConstructor
public class CurrentLocaleServiceImpl implements CurrentLocaleService {

    private final String service = String.valueOf(getClass());

    private final Logger log;

    private final IOService ioService;

    private static final Map<String, ResourceBundle> supportLocale = Map.of(
            "en", ResourceBundle.getBundle("bundle", new Locale("en")),
            "fr", ResourceBundle.getBundle("bundle", new Locale("fr"))
    );

    @Override
    public ResourceBundle set(String lang, ResourceBundle message) {

        if (supportLocale.containsKey(lang)) {
            ioService.outputMessageByLocale(supportLocale.get(lang), "locale-success");
            log.info("Смена локали на: " + lang + ". Из сервиса: " + service);
            return supportLocale.get(lang);
        }
        else {
            ioService.outputMessageByLocale(message, "no-locale");
            log.info("Ошибка смены локали на: " + lang + ". Данная локаль не поддерживается" + ". Из сервиса: " + service);
            return message;
        }
    }
}
