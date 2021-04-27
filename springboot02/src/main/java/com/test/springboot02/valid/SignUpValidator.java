package com.test.springboot02.valid;

import com.test.springboot02.dto.SignUpDTO;
import com.test.springboot02.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class SignUpValidator implements Validator {

    private final MemberRepository memberRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(SignUpDTO.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        SignUpDTO signUpDTO = (SignUpDTO)o;
        if(memberRepository.existsByNickname(signUpDTO.getNickname())){
            errors.rejectValue("nickname","invalid nickname","이미 사용중인 닉네임입니다");
        }

        if(memberRepository.existsByEmail(signUpDTO.getEmail())){
            errors.rejectValue("email","invalid email","이미 사용중인 이메일입니다");
        }

    }
}
