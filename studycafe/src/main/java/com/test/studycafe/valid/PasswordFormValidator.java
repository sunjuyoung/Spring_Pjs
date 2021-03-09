package com.test.studycafe.valid;

import com.test.studycafe.dto.PasswordForm;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class PasswordFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return PasswordForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PasswordForm passwordForm = (PasswordForm)target;
        if(!passwordForm.getNewPasswordConfirm().equals(passwordForm.getNewPassword())){
            errors.rejectValue("newPassword","wrong value","입력한 패스워드가 일치하지않습니다");
        }

    }
}
