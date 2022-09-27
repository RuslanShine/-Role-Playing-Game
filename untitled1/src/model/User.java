package model;

import java.time.LocalDate;

public class User {
    private static long totalUsers = 1;
    private long id;
    private String name;
    private String surname;
    private LocalDate birthday;

    public User(String name, String surname, LocalDate birthday) {
        id = totalUsers++;
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
    }

    public LocalDate getBirthday() {
        return birthday;
    }
}
