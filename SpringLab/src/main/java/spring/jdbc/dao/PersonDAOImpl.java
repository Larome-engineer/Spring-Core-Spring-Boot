package spring.jdbc.dao;

import org.springframework.stereotype.Component;
import spring.jdbc.utils.ConnectionManager;
import spring.jdbc.models.Person;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component("personDao")
public class PersonDAOImpl implements PersonDAO {
    Connection connection = ConnectionManager.open();

    @Override
    @Deprecated
    public List<Person> findAll() {

        List<Person> people = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from Person");

            while (rs.next()) {
                Person person = new Person();
                person.setId(rs.getInt("id"));
                person.setName(rs.getString("username"));
                person.setAge(rs.getInt("age"));

                people.add(person);
            }

        } catch (SQLException sql) {
            sql.printStackTrace();
        }
        return people;
    }

    @Override
    public Person findById(int id) {
        Person person = null;

        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from Person where id=?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();

            person = new Person();
            person.setId(rs.getInt("id"));
            person.setName(rs.getString("username"));
            person.setAge(rs.getInt("age"));

        } catch (SQLException sql) {
            System.out.println("ID DOES NOT EXIST: " + sql);
        }
        return person;
    }

    @Override
    public Person findByName(String name) {
        Person person = null;

        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from Person where username=?");
            preparedStatement.setString(1, name);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();

            person = new Person();
            person.setId(rs.getInt("id"));
            person.setName(rs.getString("username"));
            person.setAge(rs.getInt("age"));

        } catch (SQLException sql) {
            System.out.println("NAME DOES NOT EXIST: " + sql);
        }
        return person;
    }
}

