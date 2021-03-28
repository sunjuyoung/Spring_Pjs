package com.test.studycafe.service;

import com.test.studycafe.domain.Account;
import com.test.studycafe.domain.Study;
import com.test.studycafe.domain.Tag;
import com.test.studycafe.domain.Zone;
import com.test.studycafe.dto.BannerImageForm;
import com.test.studycafe.dto.DescriptionForm;
import com.test.studycafe.dto.StudyForm;
import com.test.studycafe.dto.TagForm;
import com.test.studycafe.repository.StudyRepository;
import com.test.studycafe.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class StudyService {

    private final StudyRepository studyRepository;
    private final ModelMapper modelMapper;
    private final TagRepository tagRepository;


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

    public Study updateBannerImage(String path, String image) {
        Optional<Study> study = studyRepository.findByPath(path);
        study.get().setImage(image);
        return study.get();
    }

    public void updateTag(Study study, TagForm tagForm) {
        Tag tag = tagRepository.findByTitle(tagForm.getTagTitle());
        if(tag == null){
            tagRepository.save(Tag.builder().title(tagForm.getTagTitle()).build());
        }
       study.getTags().add(tag);

    }


}
