package org.spring.boot.mapstruct.demo4;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.spring.boot.mapstruct.demo1.SexEnum;

@Mapper
public interface StudentMapper2 {
    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    @Mappings({
            @Mapping(source = "name1", target = "name2",defaultValue = "某某人"),
            @Mapping(target = "age2", expression = "java(java.lang.Integer.valueOf(student1.getAge1()))"),
            @Mapping(source = "createTime1", target = "createTime2", dateFormat = "yyyy-MM-dd HH:mm:ss:SSS"),
            @Mapping(source = "weight1", target = "weight2", numberFormat = "0.00"),
            @Mapping(target = "bloodType2", constant = "A 型血"),
            @Mapping(source = "sex1",target = "sex2",qualifiedByName = {"getBySexEnum"})
    })
    Student2 toStudent2(Student1 student1);

    /**
     * InheritConfiguration用来指定引用的映射规则
     * 注意: 入参类型和返回类型要和原映射一致
     * @param student1
     * @param student2
     */
    @InheritConfiguration(name = "toStudent2")
    void updateStudent2(Student1 student1, @MappingTarget Student2 student2);

    @Named("getBySexEnum")
    default Integer getBySexEnum(SexEnum sexEnum){
        return sexEnum.getCode();
    }
}