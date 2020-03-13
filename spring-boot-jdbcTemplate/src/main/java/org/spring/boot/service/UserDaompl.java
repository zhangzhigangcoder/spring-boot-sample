package org.spring.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户业务逻辑实现类
 * 
 * @author zhangzhigang
 */
@Repository
public class UserDaompl implements IUserDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Transactional
	@Override
	public int insertRequired(String name) {
		return jdbcTemplate.update("insert into user(name) values(?)", name);
	}

	@Transactional(propagation=Propagation.REQUIRES_NEW)
	@Override
	public int insertRequiresNew(String name) {
		return jdbcTemplate.update("insert into user(name) values(?)", name);
	}

	@Transactional(propagation=Propagation.NESTED)
	@Override
	public int insertNested(String name) {
		return jdbcTemplate.update("insert into user(name) values(?)", name);
	}
}
