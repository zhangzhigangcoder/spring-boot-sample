package org.spring.boot.fast.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;


@Data
public class FastJsonBean {

    /**
     * 在 bean -> jsonStr, name -> n
     * 在 jsonStr -> bean, n -> name
     */
    @JSONField(name = "n")
    private String name;

    /**
     * 在 bean -> jsonStr, age -> age
     * 在 jsonStr -> bean, a | g | e -> age
     */
    @JSONField(alternateNames = {"a", "g", "e"})
    private int age;

}


