package com.whosaidmeow.restfulwebservices.service;

import com.whosaidmeow.restfulwebservices.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.emptyList;

@Component
public class UserService {

    private static final List<User> USERS = new ArrayList<>();
    private static int COUNTER = 0;

    static {
        USERS.add(new User(++COUNTER, "Joe", LocalDate.now().minusYears(30), emptyList()));
        USERS.add(new User(++COUNTER, "Rick", LocalDate.now().minusYears(18), emptyList()));
        USERS.add(new User(++COUNTER, "Chris", LocalDate.now().minusYears(44), emptyList()));
    }

    public List<User> findAll() {
        return USERS;
    }

    public User findOne(long id) {
        return USERS.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public User save(User user) {
        user.setId(++COUNTER);
        USERS.add(user);
        return user;
    }

    public void deleteUser(int id) {
        USERS.removeIf(user -> user.getId() == id);
    }
}
