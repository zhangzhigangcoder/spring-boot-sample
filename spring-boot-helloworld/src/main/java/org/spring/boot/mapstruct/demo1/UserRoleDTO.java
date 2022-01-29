package org.spring.boot.mapstruct.demo1;

import lombok.Data;

import java.util.Date;

@Data
public class UserRoleDTO {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户名
     */
    private String name;

    private String nickname;

    private Integer sexCode;

    /**
     * 姓名长度
     */
    private Integer nameLength;

    /**
     * 角色名
     */
    private String roleName;

    private String disable;

    private String registerTimeStr;

    private Date registerTime;

    private Integer identifyTypeCode;

}

