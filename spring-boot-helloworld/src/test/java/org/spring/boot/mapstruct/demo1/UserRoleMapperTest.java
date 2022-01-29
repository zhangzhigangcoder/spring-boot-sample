package org.spring.boot.mapstruct.demo1;


import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class UserRoleMapperTest {
    User user = null;
    Role role = null;

    /**
     * 模拟从数据库中查出user对象
     */
    @Before
    public void before() {
       role  = new Role(2L, "administrator", "超级管理员");
       user  = new User(1L, "zhang", SexEnum.MAN,"12345", "17677778888", "123@qq.com", true, "2022-01-29 13:20:00", new Date(), IdentifyTypeEnum.IDENTITY_CARD, role);
    }

    /**
     * 模拟把user对象转换成UserRoleDto对象
     */
    @Test
    public void test1() {
        UserRoleDTO userRoleDto = new UserRoleDTO();
        userRoleDto.setUserId(user.getId());
        userRoleDto.setName(user.getUsername());
        userRoleDto.setRoleName(user.getRole().getRoleName());
        System.out.println(userRoleDto);
    }

    @Test
    public void test2() {
        UserRoleDTO userRoleDTO = UserRoleMapper.INSTANCE.toUserRoleDTO(role.getRoleName());
        System.out.println(userRoleDTO);
    }

    @Test
    public void test3() {
        UserRoleDTO userRoleDTO = UserRoleMapper.INSTANCE.toUserRoleDTO(user);
        System.out.println(userRoleDTO);
    }

    @Test
    public void test4() {
        UserRoleDTO userRoleDTO = UserRoleMapper.INSTANCE.toUserRoleDTO(user, role);
        System.out.println(userRoleDTO);
    }

    @Test
    public void test5() {
        UserRoleDTO userRoleDTO = UserRoleMapper.INSTANCE.toUserRoleDTO(user, role.getRoleName());
        System.out.println(userRoleDTO);
    }

    @Test
    public void test6() {
        UserRoleDTO userRoleDTO = new UserRoleDTO();
        userRoleDTO.setUserId(111L);
        UserRoleMapper.INSTANCE.updateUser(user, userRoleDTO);
        System.out.println(user);
    }
}

