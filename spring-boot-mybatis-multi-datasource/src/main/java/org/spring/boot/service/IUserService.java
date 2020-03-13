package org.spring.boot.service;

import org.apache.ibatis.annotations.Param;
import org.spring.boot.entity.User;

/**
 * 用户逻辑接口类
 * 
 * @author zhangzhigang
 */
public interface IUserService {

	/**
	 * 根据用户名获取用户信息，包括从库的地址信息
	 * @param cityName
	 * @return
	 */
	User findByName(String userName);
	
	void addUser(int id, String userName);
}
