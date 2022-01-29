package org.spring.boot.mapstruct.demo4;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
class Student2 {
    //主键
    private Long id;
    //名称
    private String name2;
    //年龄
    private Integer age2;
    //创建时间
    private String createTime2;
    //体重
    private String weight2;
    //身高
    private String bloodType2;
    //性别(0：女，1：男)
    private Integer sex2;
}