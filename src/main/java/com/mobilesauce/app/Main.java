package com.mobilesauce.app;

import com.mobilesauce.domain.user.UserLogin;
import com.mobilesauce.service.validation.PasswordValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

/**
 * This class is just to show how to use the API.
 * Usually having the unit test is sufficient.
 *
 * @author sso
 *
 */
@Component
public class Main {
    @Autowired
    private PasswordValidationService passwordValidationService;

    public static void main(String[] args) {
        ApplicationContext ctx =
                new AnnotationConfigApplicationContext("package"); // Use annotated beans from the specified package

        Main main = ctx.getBean(Main.class);

        UserLogin userLogin = new UserLogin();
        userLogin.setUsername("sso");
        userLogin.setPassword(args[0]);

        Errors errors = new BeanPropertyBindingResult(userLogin, "userLogin");

        main.passwordValidationService.validate(userLogin, errors);

        if (errors.hasErrors()) {
            for (FieldError fieldError : errors.getFieldErrors()) {
                System.out.println(fieldError.getDefaultMessage());
            }
        } else {
            System.out.println("Password is valid.");
        }
    }

}
