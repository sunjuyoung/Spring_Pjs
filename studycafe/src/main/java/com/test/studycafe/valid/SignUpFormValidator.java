package com.test.studycafe.valid;

import com.test.studycafe.dto.SignUpForm;
import com.test.studycafe.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class SignUpFormValidator implements Validator {

    private final AccountRepository accountRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(SignUpForm.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        //email, nickname;
        SignUpForm signUpForm = (SignUpForm) o;
        if(accountRepository.existsByEmail(signUpForm.getEmail())){
            errors.rejectValue("email","invalid email",new Object[]{signUpForm.getEmail()},"이미사용중인 이메일");
        }
        if(accountRepository.existsByNickname(signUpForm.getNickname())){
            errors.rejectValue("nickname","invalid nickname",new Object[]{signUpForm.getNickname()},"이미사용중인 닉네임입니다.");
        }
    }
}
