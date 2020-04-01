package de.example.userservice;

import lombok.Getter;

@Getter
public enum UserOrderBy {
    LAST_NAME("lastName"),
    FIRST_NAME("firstName"),
    AGE("age");

    private String value;

    UserOrderBy(String value) {
        this.value = value;
    }
}
