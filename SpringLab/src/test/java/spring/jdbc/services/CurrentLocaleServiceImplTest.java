package spring.jdbc.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CurrentLocaleServiceImplTest {

    private static final Map<String, ResourceBundle> supportLocale = Map.of(
            "en", ResourceBundle.getBundle("bundle", new Locale("en")),
            "fr", ResourceBundle.getBundle("bundle", new Locale("fr"))
    );

    ResourceBundle current = supportLocale.get("en");

    @Test
    void set() {
        current = supportLocale.get("fr");
        assertNotNull(current);
        assertEquals(current, ResourceBundle.getBundle("bundle", new Locale("fr")));
        assertTrue(supportLocale.containsKey("fr"));

        var notSupportedLocale = supportLocale.get("de");
        assertFalse(supportLocale.containsKey("de"));
        assertNull(notSupportedLocale);
    }

    @Test
    void get() {
        assertNotNull(current);
        assertEquals(current, supportLocale.get("en"));
    }
}