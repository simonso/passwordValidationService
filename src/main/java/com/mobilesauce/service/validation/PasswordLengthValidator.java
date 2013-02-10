package com.mobilesauce.service.validation;

import com.mobilesauce.common.PortalConstants;
import org.springframework.validation.Errors;

/**
 * This class is responsible for validating the password length.
 *
 * @author sso
 */
public class PasswordLengthValidator extends AbstractPasswordValidator {

    private Integer minimumPasswordLength;

    private Integer maximumPasswordLength;

    /**
     * Validates the password on the password length.
     *
     * @param password password to be validated
     * @param errors errors object to be set
     * @see Errors
     */
    public void doValidate(final String password, Errors errors) {

        final int length = password.length();

        if (length < minimumPasswordLength) {
            errors.rejectValue(PortalConstants.PASSWORD, PortalConstants.PASSWORD_MIN_LENGTH, new Object[] { minimumPasswordLength },
                    String.format("The password must be at least [%s] characters in length", minimumPasswordLength));
        }

        if (length > maximumPasswordLength) {
            errors.rejectValue(PortalConstants.PASSWORD, PortalConstants.PASSWORD_MAX_LENGTH, new Object[] { maximumPasswordLength },
                    String.format("The password must be at most [%s] characters in length", maximumPasswordLength));
        }
    }

    public Integer getMinimumPasswordLength() {
        return minimumPasswordLength;
    }

    public void setMinimumPasswordLength(Integer minimumPasswordLength) {
        this.minimumPasswordLength = minimumPasswordLength;
    }

    public Integer getMaximumPasswordLength() {
        return maximumPasswordLength;
    }

    public void setMaximumPasswordLength(Integer maximumPasswordLength) {
        this.maximumPasswordLength = maximumPasswordLength;
    }
}
