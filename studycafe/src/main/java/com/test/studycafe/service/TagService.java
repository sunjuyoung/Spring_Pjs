package com.test.studycafe.service;

import com.test.studycafe.domain.Tag;
import com.test.studycafe.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;


    public Tag findtagTitle(String tagTitle) {
        Tag tag = tagRepository.findByTitle(tagTitle);
        return tag;
    }
}
