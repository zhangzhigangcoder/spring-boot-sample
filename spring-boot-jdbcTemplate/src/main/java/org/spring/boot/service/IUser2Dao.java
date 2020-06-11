package org.spring.boot.service;

import java.util.Map;

/**
 * 用户DAO层
 * 
 * @author zhangzhigang
 */
public interface IUser2Dao {

	int insertException(String name);
	
	int insertRequired(String name);
	
	int insertRequiredException(String name);
	
	int insertRequiresNew(String name);
	
	int insertRequiresNewException(String name);
	
	int insertNested(String name);
	
	int insertNestedException(String name);
	
	Map<String, Object> getUser(String name);
}
