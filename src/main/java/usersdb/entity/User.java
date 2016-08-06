package usersdb.entity;

import java.time.LocalDate;

public class User {
    private String id;
    private String name;
    private LocalDate dateOfBirth;

    public User(String id, String name, LocalDate dateOfBirth) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
}
