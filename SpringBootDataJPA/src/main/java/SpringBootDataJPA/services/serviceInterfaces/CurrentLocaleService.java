package SpringBootDataJPA.services.serviceInterfaces;

import java.util.Locale;

public interface CurrentLocaleService {
    void set(String locale);
    Locale get();
}
