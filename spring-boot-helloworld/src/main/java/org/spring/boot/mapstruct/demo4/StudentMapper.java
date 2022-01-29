package org.spring.boot.mapstruct.demo4;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.spring.boot.mapstruct.demo1.SexEnum;

@Mapper
public interface StudentMapper {
    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    @Mappings({
            @Mapping(source = "name1", target = "name2", defaultValue = "某某人"),
            @Mapping(target = "age2", expression = "java(java.lang.Integer.valueOf(student1.getAge1()))"),
            @Mapping(source = "createTime1", target = "createTime2", dateFormat = "yyyy-MM-dd HH:mm:ss:SSS"),
            @Mapping(source = "weight1", target = "weight2", numberFormat = "0.00"),
            @Mapping(target = "bloodType2", constant = "A 型血"),
            @Mapping(source = "sex1", target = "sex2", qualifiedByName = {"getBySexEnum"})
    })
    Student2 toStudent2(Student1 student1);

    /**
     * InheritInverseConfiguration用来指定反向引用的映射
     * 注意：
     *  1. 新映射和老映射的入参类型和返回类型要相反
     *  2.如果原映射的Mapping中没有source(比如使用了: expression, constant等),要重新再列一遍
     * @param student2
     * @return
     */
    @InheritInverseConfiguration(name = "toStudent2")
    @Mappings({
            @Mapping(target = "age1", expression = "java(java.lang.String.valueOf(student2.getAge2()))"),
            @Mapping(target = "bloodType1", constant = "YY 型血")
    })
    Student1 toStudent1(Student2 student2);

    @Named("getByCode")
    default SexEnum getByCode(Integer code) {
        SexEnum[] sexEnums = SexEnum.values();
        for (SexEnum item : sexEnums) {
            if (item.getCode().equals(code)) {
                return item;
            }
        }
        return null;
    }

    @Named("getBySexEnum")
    default Integer getBySexEnum(SexEnum sexEnum) {
        return sexEnum.getCode();
    }
}