package SpringBootAndShell.bootAndShell.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionManager {

    private static final String URL_ = "spring.datasource.url";
    private static final String USERNAME_ = "spring.datasource.username";
    private static final String PASSWORD_ = "spring.datasource.password";

    static {
        loadDriver();
    }

    private ConnectionManager(){}

    public static Connection open(){
        try {
            return DriverManager.getConnection(
                    PropertiesUtil.get(URL_),
                    PropertiesUtil.get(USERNAME_),
                    PropertiesUtil.get(PASSWORD_)
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void loadDriver() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}