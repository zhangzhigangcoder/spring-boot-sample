package org.spring.boot.mapstruct.demo1;

import lombok.Getter;

/**
 * @author zhangzhigang
 * @date 2022-01-29 13:48
 */
public enum IdentifyTypeEnum {

    IDENTITY_CARD(1, "身份证"),
    PASSPORT(2, "护照");

    @Getter
    private Integer code;

    @Getter
    private String desc;

    IdentifyTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
