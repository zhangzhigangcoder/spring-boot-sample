package org.spring.boot.lombok;

import lombok.*;

/**
 * @Data: 相当于 @Getter @Setter @NoArgsConstructor @EqualsAndHashCode @ToString
 * @Getter: get
 * @Setter: set
 * @EqualsAndHashCode equals hashCode
 * @NoArgsConstructor: 空参构造
 * @ToString: toString
 * @AllArgsConstructor: 全参构造
 * 注意：以上都不包含static变量，只有@EqualsAndHashCode不包含transient变量
 */
@Getter
public class LombokBean {

    private String name;

    private int age = 1;

    private static int sex;

    private transient  int weight;

    public static void main(String[] args) {
//        LombokBean bean = new LombokBean();
//        bean.setName("张三");
//        System.out.println(bean.toString());
    }
}


