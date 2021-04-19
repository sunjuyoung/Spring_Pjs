package com.test.studycafe.service;

import ch.qos.logback.classic.spi.IThrowableProxy;
import com.test.studycafe.domain.Account;
import com.test.studycafe.domain.Study;
import com.test.studycafe.domain.Tag;
import com.test.studycafe.domain.Zone;
import com.test.studycafe.dto.*;
import com.test.studycafe.event.StudyCreateEvent;
import com.test.studycafe.repository.StudyRepository;
import com.test.studycafe.repository.TagRepository;
import com.test.studycafe.repository.ZoneRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

import static com.test.studycafe.dto.StudyForm.PATH_PATTERN;


@Service
@RequiredArgsConstructor
@Transactional
public class StudyService {

    private final StudyRepository studyRepository;
    private final ModelMapper modelMapper;
    private final TagRepository tagRepository;
    private final ZoneRepository zoneRepository;
    private final ApplicationEventPublisher eventPublisher;


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
        Study study = studyRepository.findStudyWithTagByPath(path);
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
        Zone zone=  zoneRepository.findByCityAndProvince(zoneForm.getCity(),zoneForm.getProvince());
        study.getZones().add(zone);
        //studyRepository.save(study);
    }

    public void removeTag(Study study, Tag tag) {
        study.getTags().remove(tag);
    }

    public void publish(Study study) {
        study.publish();
        this.eventPublisher.publishEvent(new StudyCreateEvent(study));
    }

    public void close(Study study) {
        study.close();
    }

    public void startRecruit(Study study){
        study.startRecruit();
    }
    public void stopRecruit(Study study) {
        study.stopRecruit();
    }

    public Study getStudyWithManagersByPath(String path) {
        return studyRepository.findStudyWithManagersByPath(path);
    }

    public Study getStudyOnlyByPath(String path) {
        return studyRepository.findStudyOnlyByPath(path);
    }

    public void updatePath(Study study, String newPath) {
        study.setPath(newPath);
    }

    public boolean existsByPath(String newPath) {
        if(!newPath.matches(PATH_PATTERN)){
            return false;
        }
        return !studyRepository.existsByPath(newPath);
    }

    public boolean isValidTitle(String newTitle) {
        return newTitle.length() <=50;
    }

    public void updateTitle(Study study, String newTitle) {
        study.setTitle(newTitle);
    }

    public void removeStudy(Study study) {
        if(study.isRemovable()){
            studyRepository.delete(study);
        }else{
            throw new IllegalArgumentException("스터디를 삭제할 수 없습니다.");
        }
    }

    public Study getStudyToUpdateStatus(Account account, String path){
        Study study = studyRepository.findStudyWithManagersByPath(path);
        checkManager(account,study);
        return study;
    }

    public void checkManager(Account account, Study study) {
        if (!study.isManagerBy(account)){
            throw new AccessDeniedException("해당 기능을 사용할 수 없습니다.");
        }
    }

    public Study getStudyWithMembersByPath(String path,Account account) {
        Study study = studyRepository.findStudyWithMembersByPath(path);
        return study;
    }


    public void addMember(Study study, Account account){
        study.getMembers().add(account);
        study.addMember();
    }


    public void removeMember(Study study, Account account){
        study.getMembers().remove(account);
        study.removeMember();
    }
}
