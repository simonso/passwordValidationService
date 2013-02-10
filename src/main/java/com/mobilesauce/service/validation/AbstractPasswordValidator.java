package com.mobilesauce.service.validation;

import com.mobilesauce.common.PortalConstants;
import com.mobilesauce.domain.user.UserLogin;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * This class provides the base implementation of the password validation.
 *
 * @author sso
 */
public abstract class AbstractPasswordValidator implements Validator {

    @Override
    public boolean supports(Class clazz) {
        return UserLogin.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        // base validation rule - must not be blank.
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, PortalConstants.USERNAME, PortalConstants.FIELD_REQUIRED);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, PortalConstants.PASSWORD, PortalConstants.FIELD_REQUIRED);

        UserLogin login = (UserLogin) target;

        final String passwordUnderValidation = login.getPassword();

        doValidate(passwordUnderValidation, errors);
    }

    /**
     * Performs the validation.
     * Template method to be implemented by specific subclass.
     *
     * @param password password under validation
     * @param errors errors object to be propagated, if error occurs
     *
     */
    abstract void doValidate(final String password, Errors errors);

}
