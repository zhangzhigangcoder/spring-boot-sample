package org.spring.boot.service;

/**
 * 用户DAO层
 * 
 * @author zhangzhigang
 */
public interface IUserDao {

	/**
	 * 添加用户
	 * @param name
	 * @return
	 */
	int insertRequired(String name);
	
	int insertRequiresNew(String name);
	
	int insertNested(String name);
	
}
