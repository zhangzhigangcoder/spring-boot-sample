package org.spring.boot.dao.master;

import org.apache.ibatis.annotations.Param;
import org.spring.boot.entity.User;

//@Mapper
public interface IUserDao {
	
	User findByName(@Param("userName") String userName);

	void addUser(@Param("id") int id, @Param("userName") String userName);
}
