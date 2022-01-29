package org.spring.boot.mapstruct.demo4;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.spring.boot.mapstruct.demo1.SexEnum;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
class Student1 {
    //主键
    private Long id;
    //名称
    private String name1;
    //年龄
    private String age1;
    //创建时间
    private Date createTime1;
    //体重
    private Double weight1;
    //身高
    private String bloodType1;
    //性别
    private SexEnum sex1;
}