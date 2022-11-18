package SpringBootAndShell.bootAndShell.services.servicesInterface;

import java.util.Locale;

public interface CurrentLocaleService {
    void set(String locale);
    Locale get();
}
