package net.okres.springsecurutyapp.service;

import net.okres.springsecurutyapp.dao.UserDao;
import net.okres.springsecurutyapp.model.Role;
import net.okres.springsecurutyapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Alex on 17.07.2017.
 */
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional (readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Создаем пользователя которого ищем по имени пользователя в нашей БД.
        User user = userDao.findByUsername(username);

        // GrantedAuthority отражает разрешения выданные доверителю в масштабе всего приложения.
        // grantedAuthorities - это разрешения
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        //добавление в разрешение для данного пользователя все роли которые хранятся у него в БД
        for (Role role : user.getRoles()){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
}
