package net.okres.springsecurutyapp.validator;

import net.okres.springsecurutyapp.model.User;
import net.okres.springsecurutyapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by Alex on 17.07.2017.
 */
@Component
public class UserValidator implements Validator{

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        //если ничего не ввели
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"username", "Required");
        //проверка длинны username
        if (user.getUsername().length() < 8 || user.getUsername().length() > 32){
            errors.rejectValue("username", "Size.userForm.username");
        }
        //проверка на дупликаты
        if (userService.findByUsername(user.getUsername()) != null){
            errors.rejectValue("username", "Duplicate.userForm.username");
        }
        //if no password
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Required");

        //check length of password
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32){
            errors.rejectValue("password", "Size.userForm.password");
        }
        //check if password same
        if (!user.getConfirmPassword().equals(user.getPassword())){
            errors.rejectValue("confirmPassword", "Different.userForm.password");
        }
    }
}
