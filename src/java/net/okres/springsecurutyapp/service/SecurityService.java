package net.okres.springsecurutyapp.service;

/**
 * Created by Alex on 17.07.2017.
 */
public interface SecurityService {

    String findLoggedInUsername();

    void autoLogin(String username, String password);


}
