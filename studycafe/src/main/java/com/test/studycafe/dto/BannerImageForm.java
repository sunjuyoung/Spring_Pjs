package com.test.studycafe.dto;

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Lob;

@Data
public class BannerImageForm {

    @Lob
    @Basic
    private String image;

}
