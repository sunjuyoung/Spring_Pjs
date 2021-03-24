package com.test.studycafe.service;

import com.test.studycafe.domain.Account;
import com.test.studycafe.domain.Study;
import com.test.studycafe.dto.DescriptionForm;
import com.test.studycafe.dto.StudyForm;
import com.test.studycafe.repository.StudyRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class StudyService {

    private final StudyRepository studyRepository;
    private final ModelMapper modelMapper;


    public Study createNewStudy(Account account, StudyForm studyForm) {
      Study newStudy  =  studyRepository.save(modelMapper.map(studyForm,Study.class));
      newStudy.addManager(account);

      return newStudy;
    }

    public void updateDescription(Study study, DescriptionForm descriptionForm) {
     /*   study.setFullDescription(descriptionForm.getFullDescription());
        study.setShortDescription(descriptionForm.getShortDescription());*/
        modelMapper.map(descriptionForm,study);
    }


    public Study getStudyByPath(String path) {
        Optional<Study> study = studyRepository.findByPath(path);
        return study.get();
    }

    public void banner(Study study){
        study.banner();
    }
}
