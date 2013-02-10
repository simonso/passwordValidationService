package com.mobilesauce.service.validation;

import com.mobilesauce.domain.user.UserLogin;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * This class is responsible for validating the password.
 *
 * This service provides the following checks:
 *
 * @author sso
 */
public class PasswordValidationService {

    private final static Logger logger = Logger.getLogger(PasswordValidationService.class);

    @Autowired
    Validator[] validators;

    public PasswordValidationService(Validator[] validators) {
        this.validators = validators;
    }

    public void validate(UserLogin userLogin, Errors errors) {
        logger.debug("Start validating password ...");
        for (Validator validator : validators) {
            validator.validate(userLogin, errors);
        }
        logger.debug("Finish validating password ...");
    }
}
