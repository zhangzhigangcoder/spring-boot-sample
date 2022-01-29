package org.spring.boot.mapstruct.demo1;

import lombok.Getter;

/**
 * @author zhangzhigang
 * @date 2022-01-29 13:48
 */
public enum SexEnum {

    WOMAN(0, "女"),
    MAN(1, "男");

    @Getter
    private Integer code;

    @Getter
    private String desc;

    SexEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
