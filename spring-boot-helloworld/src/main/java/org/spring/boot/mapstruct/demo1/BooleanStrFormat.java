package org.spring.boot.mapstruct.demo1;

/**
 * @author zhangzhigang
 * @date 2022-01-29 10:29
 */
public class BooleanStrFormat {
    public static String toStr(Boolean isDisable) {
        return isDisable ? "Y" : "N";
    }

    public static Boolean toBoolean(String str) {
        return "Y".equals(str) ? true : false;
    }
}
