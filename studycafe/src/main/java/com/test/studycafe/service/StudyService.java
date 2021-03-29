package com.test.studycafe.service;

import com.test.studycafe.domain.Account;
import com.test.studycafe.domain.Study;
import com.test.studycafe.domain.Tag;
import com.test.studycafe.domain.Zone;
import com.test.studycafe.dto.*;
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

    public Study getStudyToUpdateTag(Account account,String path){
        Study study = studyRepository.findAccountWithTagByPath(path);
        return study;
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


    public void updateZone(Study study, ZoneForm zoneForm) {
        study.getZones().add(Zone.builder().province(zoneForm.getProvince())
        .localName(zoneForm.getLocalname()).city(zoneForm.getCity()).build());

        //studyRepository.save(study);
    }

    public void removeTag(Study study, Tag tag) {
        study.getTags().remove(tag);
    }

    public void publish(Study study) {
        study.publish();
    }

    public void close(Study study) {
        study.close();
    }
}
