package com.test.studycafe.valid;

import com.test.studycafe.dto.StudyForm;
import com.test.studycafe.repository.StudyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class StudyFormValidator implements Validator {

    private final StudyRepository studyRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return StudyForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        StudyForm studyForm = (StudyForm)target;
        if(studyRepository.existsByPath(studyForm.getPath())){
            errors.rejectValue("path","wrong path","중복된 URL입니다.");
        }

    }
}
