package com.test.studycafe.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class DescriptionForm {

    @NotBlank
    @Length(max = 80)
    private String shortDescription;

    @NotBlank
    private String fullDescription;
}
