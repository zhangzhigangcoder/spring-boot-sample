package org.spring.boot.mapstruct.demo1;

/**
 * @author zhangzhigang
 * @date 2022-01-29 15:05
 */
public class IdentifyTypeCodeConverter {

    @IdentifyTypeAnno
    public static Integer getIdentifyTypeCode(IdentifyTypeEnum identifyTypeEnum) {
        return null != identifyTypeEnum ? identifyTypeEnum.getCode() : null;
    }
}
