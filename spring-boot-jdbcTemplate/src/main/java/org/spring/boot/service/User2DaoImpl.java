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
public class User2DaoImpl implements IUser2Dao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public int insertException(String name) {
		jdbcTemplate.update("insert into user2(name) values(?)", name);
		throw new IllegalArgumentException();
	}
	
	@Transactional
	@Override
	public int insertRequired(String name) {
		return jdbcTemplate.update("insert into user2(name) values(?)", name);
	}

	@Transactional
	@Override
	public int insertRequiredException(String name) {
		jdbcTemplate.update("insert into user2(name) values(?)", name);
		throw new IllegalArgumentException();
	}

	@Transactional(propagation=Propagation.REQUIRES_NEW)
	@Override
	public int insertRequiresNew(String name) {
		return jdbcTemplate.update("insert into user2(name) values(?)", name);
	}

	@Transactional(propagation=Propagation.REQUIRES_NEW)
	@Override
	public int insertRequiresNewException(String name) {
		jdbcTemplate.update("insert into user2(name) values(?)", name);
		throw new IllegalArgumentException();
	}

	@Transactional(propagation=Propagation.NESTED)
	@Override
	public int insertNested(String name) {
		return jdbcTemplate.update("insert into user2(name) values(?)", name);
	}

	@Transactional(propagation=Propagation.NESTED)
	@Override
	public int insertNestedException(String name) {
		jdbcTemplate.update("insert into user2(name) values(?)", name);
		throw new IllegalArgumentException();
	}

}
