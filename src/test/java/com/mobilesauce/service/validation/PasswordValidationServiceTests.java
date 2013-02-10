package com.mobilesauce.service.validation;

import com.mobilesauce.domain.user.UserLogin;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.validation.*;

import static com.mobilesauce.common.PortalConstants.*;


/**
 * Unit test for testing password validation service.
 *
 * @author sso
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/resources/spring/applicationContext.xml")
public class PasswordValidationServiceTests {

    @Autowired
    PasswordValidationService passwordValidationService;

    @Autowired
    PasswordLengthValidator passwordLengthValidator;

    @Autowired
    PasswordRegularExpressionValidator passwordCharacterSequenceValidator;

    @Autowired
    PasswordRegularExpressionValidator passwordCharacterMixtureValidator;

    @Test
    public void testCorrectLengthPassword() {
        UserLogin userLogin = new UserLogin();

        userLogin.setUsername("nonempty");
        userLogin.setPassword("1122aa");

        Errors errors = new BeanPropertyBindingResult(userLogin, "userLogin");

        passwordLengthValidator.validate(userLogin, errors);

        // should not have errors!
        assert(!errors.hasErrors());
    }

    @Test
    public void testShortPassword() {

        UserLogin userLogin = new UserLogin();

        userLogin.setPassword("2222");

        Errors errors = new BeanPropertyBindingResult(userLogin, "userLogin");

        passwordLengthValidator.validate(userLogin, errors);

        assert(errors.hasErrors());

        final FieldError fieldError = errors.getFieldError(PASSWORD);

        assert(fieldError != null && PASSWORD_MIN_LENGTH.equals(fieldError.getCode()));

    }

    @Test
    public void testLongPassword() {
        UserLogin userLogin = new UserLogin();

        userLogin.setPassword("11112222333344445");

        Errors errors = new BeanPropertyBindingResult(userLogin, "userLogin");

        passwordLengthValidator.validate(userLogin, errors);

        assert(errors.hasErrors());

        final FieldError fieldError = errors.getFieldError(PASSWORD);

        assert(fieldError != null && PASSWORD_MAX_LENGTH.equals(fieldError.getCode()));
    }

    @Test
    public void testCorrectCharacterMixture() {
        UserLogin userLogin = new UserLogin();

        userLogin.setUsername("non-empty");
        userLogin.setPassword("16ab37");

        Errors errors = new BeanPropertyBindingResult(userLogin, "userLogin");

        passwordCharacterMixtureValidator.validate(userLogin, errors);

        assert(!errors.hasErrors());

    }

    @Test
    public void testNoLowerCaseCharacterMixture() {
        UserLogin userLogin = new UserLogin();

        userLogin.setUsername("non-empty");
        userLogin.setPassword("110033");

        Errors errors = new BeanPropertyBindingResult(userLogin, "userLogin");

        passwordCharacterMixtureValidator.validate(userLogin, errors);

        assert(errors.hasErrors());

        final FieldError fieldError = errors.getFieldError(PASSWORD);

        assert(fieldError != null && PASSWORD_CHARACTER_MIXTURE.equals(fieldError.getCode()));

    }

    @Test
    public void testNoDigitCaseCharacterMixture() {
        UserLogin userLogin = new UserLogin();

        userLogin.setUsername("non-empty");
        userLogin.setPassword("ABCDEF");

        Errors errors = new BeanPropertyBindingResult(userLogin, "userLogin");

        passwordCharacterMixtureValidator.validate(userLogin, errors);

        assert(errors.hasErrors());

        final FieldError fieldError = errors.getFieldError(PASSWORD);

        assert(fieldError != null && PASSWORD_CHARACTER_MIXTURE.equals(fieldError.getCode()));

    }

    @Test
    public void testRepeatingCharacterSequence() {
        UserLogin userLogin = new UserLogin();

        userLogin.setUsername("non-empty");
        userLogin.setPassword("sevenseven");

        Errors errors = new BeanPropertyBindingResult(userLogin, "userLogin");

        passwordCharacterSequenceValidator.validate(userLogin, errors);

        assert(errors.hasErrors());

        final FieldError fieldError = errors.getFieldError(PASSWORD);

        assert(fieldError != null && PASSWORD_CHARACTER_SEQUENCE.equals(fieldError.getCode()));
    }

    @Test
    public void testNoRepeatingCharacterSequence() {
        UserLogin userLogin = new UserLogin();

        userLogin.setUsername("non-empty");
        userLogin.setPassword("1a501a");

        Errors errors = new BeanPropertyBindingResult(userLogin, "userLogin");

        passwordCharacterSequenceValidator.validate(userLogin, errors);

        assert(!errors.hasErrors());
    }

}
