package net.okres.springsecurutyapp.controller;

import net.okres.springsecurutyapp.model.User;
import net.okres.springsecurutyapp.service.SecurityService;
import net.okres.springsecurutyapp.service.UserService;
import net.okres.springsecurutyapp.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Alex on 17.07.2017.
 */

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = "registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }


//Заполняем формочку регистрации
    @RequestMapping(value = "registration" , method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm")User userForm, BindingResult bindingResult, Model model){
        //валидируем введенные данные
        userValidator.validate(userForm, bindingResult);

        //если были ошибки то перенаправляем на страницу регистрации
        if (bindingResult.hasErrors()){
            return "registration";
        }

        //если все ок, сохраняем
        userService.save(userForm);

        securityService.autoLogin(userForm.getUsername(), userForm.getConfirmPassword());

        return "redirect:/welcome";
    }
}
