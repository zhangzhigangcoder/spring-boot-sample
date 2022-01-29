package org.spring.boot.mapstruct.demo1;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@AllArgsConstructor
@Data
public class User {
    private Long id;
    private String username;
    private SexEnum sex;
    private String password;
    private String phoneNum;
    private String email;
    private Boolean isDisable;
    private String registerTimeStr;
    private Date registerTime;
    private IdentifyTypeEnum identifyType;
    private Role role;
}
