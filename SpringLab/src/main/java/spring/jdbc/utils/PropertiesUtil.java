package spring.jdbc.utils;

import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {

    public static final Properties properties = new Properties();

    static {
        loadProperties();
    }

    private PropertiesUtil(){}

    public static String get(String key) {
        return properties.getProperty(key);
    }

    private static void loadProperties(){
        try(var inputStream= PropertiesUtil.class.getClassLoader().getResourceAsStream("db.properties")){
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
