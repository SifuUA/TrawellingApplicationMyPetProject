package net.okres.springsecurutyapp.dao;

import net.okres.springsecurutyapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Alex on 17.07.2017.
 */
public interface UserDao extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
