package SpringBootJDBCTemplate.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class Person {
    private int id;
    private String name;
    private int age;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdWho;
}
