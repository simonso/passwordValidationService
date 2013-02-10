package com.mobilesauce.service.validation;

import org.apache.log4j.Logger;
import org.springframework.validation.Errors;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.mobilesauce.common.PortalConstants.PASSWORD;

/**
 * This class is responsible for validating the password with a regular expression.
 *
 *
 * @author sso
 */
public class PasswordRegularExpressionValidator extends AbstractPasswordValidator {

    private static final Logger logger = Logger.getLogger(PasswordRegularExpressionValidator.class);

    private String regularExpression;

    private String validationCriteria;

    private String validationCriteriaMessage;

    private boolean errorIfMatch;

    public PasswordRegularExpressionValidator() {
    }

    /**
     * Validates a password with a regular expression.
     *
     * @param password password under validation
     * @param errors errors object to be propagated, if error occurs
     */
    public void doValidate(String password, Errors errors) {
        Pattern pattern = Pattern.compile(regularExpression);
        Matcher matcher = pattern.matcher(password);

        if (errorIfMatch) {
            if (matcher.matches()) {
                errors.rejectValue(PASSWORD, validationCriteria, new Object[] { }, validationCriteriaMessage);
            }
        } else {
            if (!matcher.matches()) {
                errors.rejectValue(PASSWORD, validationCriteria, new Object[] { }, validationCriteriaMessage);
            }
        }
    }

    public String getRegularExpression() {
        return regularExpression;
    }

    public void setRegularExpression(String regularExpression) {
        this.regularExpression = regularExpression;
    }

    public String getValidationCriteria() {
        return validationCriteria;
    }

    public void setValidationCriteria(String validationCriteria) {
        this.validationCriteria = validationCriteria;
    }

    public String getValidationCriteriaMessage() {
        return validationCriteriaMessage;
    }

    public void setValidationCriteriaMessage(String validationCriteriaMessage) {
        this.validationCriteriaMessage = validationCriteriaMessage;
    }

    public boolean isErrorIfMatch() {
        return errorIfMatch;
    }

    public void setErrorIfMatch(boolean errorIfMatch) {
        this.errorIfMatch = errorIfMatch;
    }
}
