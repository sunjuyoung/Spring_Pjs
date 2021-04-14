package com.test.studycafe.event;

import com.test.studycafe.domain.Study;
import lombok.Data;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class StudyCreateEvent{

    private Study study;

    public StudyCreateEvent(Study study){
        this.study = study;
    }

}
