package SpringBootJDBCTemplate.utils;


import SpringBootJDBCTemplate.models.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        Person person = new Person();
        person.setId(rs.getInt("id"));
        person.setName(rs.getString("username"));
        person.setAge(rs.getInt("age"));

        return person;
    }
}