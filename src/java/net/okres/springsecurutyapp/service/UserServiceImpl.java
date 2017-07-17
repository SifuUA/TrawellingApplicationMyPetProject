package net.okres.springsecurutyapp.service;

import net.okres.springsecurutyapp.dao.RoleDao;
import net.okres.springsecurutyapp.dao.UserDao;
import net.okres.springsecurutyapp.model.Role;
import net.okres.springsecurutyapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Alex on 17.07.2017.
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        // закриптовали и установили пользователю пароль
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        // присвоение одной роли 1 - user
        roles.add(roleDao.getOne(1L));
        // установление роли для пользователя
        user.setRoles(roles);
        //сохранение пользователя
        userDao.save(user);
    }

    @Override
    public User findByUsername(String username) {
        // получение пользователя с БД по его имени
        return userDao.findByUsername(username);
    }
}
