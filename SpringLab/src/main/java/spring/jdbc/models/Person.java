package spring.jdbc.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Person {
    private int id;
    private String name;
    private int age;
}
