package com.test.studycafe.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class PasswordForm {

    @Length(min = 8,max=40)
    private String newPassword;

    @Length(min = 8,max=40)
    private String newPasswordConfirm;
}
