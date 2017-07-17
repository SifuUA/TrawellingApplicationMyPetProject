package net.okres.springsecurutyapp.service;

import net.okres.springsecurutyapp.model.User;

/**
 * Created by Alex on 17.07.2017.
 */
public interface UserService {

    void save(User user);

    User findByUsername(String username);
}
