package com.cns.restfulws.user;

import com.cns.restfulws.exceptions.CustomNotFoundException;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class UserDaoService {

    private static List<User> users = new ArrayList<>();

    private static int userCount = 3;

    static {
        users.add(new User(1, "Adam", new Date()));
        users.add(new User(2, "Emma", new Date()));
        users.add(new User(3, "Erica", new Date()));
    }

    public List<User> findAll() {
        return users;
    }

    public User findOne(int id) {
        return users.stream()
                .filter(u -> u.getId() == id).findFirst()
                .orElseThrow(()->new CustomNotFoundException("id: "+id));
    }

    public List<User> deleteOne(int id) {
        User user = users.stream()
                .filter(u -> u.getId() == id).findFirst()
                .orElseThrow(() -> new CustomNotFoundException("id: " + id));
        users.remove(user);
        return users;
    }

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(++userCount);
        }
        users.add(user);
        return user;
    }
}
