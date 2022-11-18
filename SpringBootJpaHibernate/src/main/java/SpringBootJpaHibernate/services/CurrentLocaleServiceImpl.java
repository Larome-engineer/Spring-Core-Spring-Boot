package SpringBootJpaHibernate.services;

import SpringBootJpaHibernate.aop.annotation.MET;
import SpringBootJpaHibernate.exception.LocaleNotSupportedException;
import SpringBootJpaHibernate.services.serviceInterface.CurrentLocaleService;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Map;

@Service
public class CurrentLocaleServiceImpl implements CurrentLocaleService {
    private final static Map<String, Locale> SUPPORTED = Map.of(
            "en", Locale.forLanguageTag("en"),
            "fr", Locale.forLanguageTag("fr")
    );

    private Locale current = SUPPORTED.get("en");

    @MET
    @Override
    public void set(String locale) {
        var loc = SUPPORTED.get(locale);
        if (loc == null) {
            throw new LocaleNotSupportedException("locale-not-supported", locale);
        }

        current = loc;
    }

    @MET
    @Override
    public Locale get() {
        return current;
    }
}