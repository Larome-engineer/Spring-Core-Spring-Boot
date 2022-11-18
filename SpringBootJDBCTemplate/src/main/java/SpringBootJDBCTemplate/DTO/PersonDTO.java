package SpringBootJDBCTemplate.DTO;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PersonDTO {
    private String name;
    private int age;
}
