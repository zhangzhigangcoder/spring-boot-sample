package org.spring.boot.mapstruct.demo1;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

/**
 * 接口或抽象类都可以
 *  自定义映射规则
 * @author zhangzhigang
 * @date 2022-01-28 11:21
 */
@Mapper(uses = {BooleanStrFormat.class, IdentifyTypeCodeConverter.class})
public interface UserRoleMapper {

    UserRoleMapper INSTANCE = Mappers.getMapper(UserRoleMapper.class);

    /**
     * 单字段映射
     *
     * @param roleName
     * @return
     */
    @Mappings({
            @Mapping(source = "roleName", target = "roleName")
    })
    UserRoleDTO toUserRoleDTO(String roleName);

    /**
     * 单对象转换
     *
     * @param user
     * @return
     */
    @Mappings({
            @Mapping(source = "id", target = "userId"),
            // 当username为空情况下，将name设置为somebody
            @Mapping(source = "username", target = "name", defaultValue = "somebody"),
            // 将nickname设置为常量haha
            @Mapping(constant = "haha", target = "nickname"),
            // 自定义转换规则
            @Mapping(source = "sex", target = "sexCode", qualifiedByName = {"getBySexEnum"}),
            @Mapping(source = "identifyType", target = "identifyTypeCode", qualifiedBy = {IdentifyTypeAnno.class}),
            @Mapping(source = "isDisable", target = "disable"),
            // 指定日期转换格式
            @Mapping(source = "registerTimeStr", target = "registerTime", dateFormat = "yyyy-MM-dd hh:mm:ss"),
            // 表示不对target中registerTimeStr字段进行映射
            @Mapping(source = "registerTime", target = "registerTimeStr", dateFormat = "yyyy-MM-dd hh:mm:ss", ignore = true),
            // 使用表达式, 必须用user访问
            @Mapping(expression = "java(user.getUsername().length())", target = "nameLength"),
            @Mapping(source = "role.roleName", target = "roleName")
    })
    UserRoleDTO toUserRoleDTO(User user);

    /**
     * 多对象映射
     * source必须指定具体对象
     *
     * @param user
     * @param role
     * @return
     */
    @Mappings({
            @Mapping(source = "user.id", target = "userId"),
            @Mapping(source = "user.username", target = "name"),
            @Mapping(source = "role.roleName", target = "roleName")
    })
    UserRoleDTO toUserRoleDTO(User user, Role role);

    /**
     * 对象 + 字段 映射
     * source必须指定具体对象
     *
     * @param user
     * @param roleName
     * @return
     */
    @Mappings({
            @Mapping(source = "user.id", target = "userId"),
            @Mapping(source = "user.username", target = "name"),
            @Mapping(source = "roleName", target = "roleName")
    })
    UserRoleDTO toUserRoleDTO(User user, String roleName);

    /**
     * 单对象更新
     *
     * @param user
     * @param userRoleDTO
     * @return
     */
    @Mappings({
            @Mapping(source = "userId", target = "id"),
            @Mapping(source = "name", target = "username"),
            @Mapping(source = "roleName", target = "role.roleName")
    })
    void updateUser(@MappingTarget User user, UserRoleDTO userRoleDTO);

    @Named("getBySexEnum")
    default Integer getBySexEnum(SexEnum sexEnum) {
        return null != sexEnum ? sexEnum.getCode() : null;
    }

}
