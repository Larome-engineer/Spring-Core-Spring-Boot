package SpringBootJDBCTemplate.DAO;

import SpringBootJDBCTemplate.models.Person;
import SpringBootJDBCTemplate.utils.PersonMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PersonDAOImpl implements PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Person> findAll() {
        return jdbcTemplate.query("select * from person", new PersonMapper());
    }

    @Override
    public Person findById(int id) {
        Optional<Person> foundPerson = jdbcTemplate.query("select * from person where id = ?",
                new PersonMapper(), id).stream().findAny();
        return foundPerson.orElse(null);
    }

    @Override
    public void create(Person person) {
        jdbcTemplate.update("insert into person (username, age, created_at, updated_at, created_who) " +
                        "values (?, ?, ?, ?, ?)",
                person.getName(), person.getAge(), person.getCreatedAt(), person.getUpdatedAt(), person.getCreatedWho());
    }
    @Override
    public void update(int id, Person update) {
        jdbcTemplate.update("update person set username=?, age=?, created_at=?, updated_at=?, created_who=? where id=?",
                update.getName(),
                update.getAge(),
                update.getCreatedAt(),
                update.getUpdatedAt(),
                update.getCreatedWho(),
                id);
    }
    @Override
    public void delete(int id) {
        jdbcTemplate.update("delete from person where id =?", id);
    }
}
