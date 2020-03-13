package org.spring.boot.service;

import javax.annotation.Resource;

import org.spring.boot.dao.cluster.ICityDao;
import org.spring.boot.dao.master.IUserDao;
import org.spring.boot.entity.City;
import org.spring.boot.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 城市业务逻辑实现类
 * 
 * @author zhangzhigang
 */
@Service
public class UserServiceImpl implements IUserService{

	@Resource
	private IUserDao userDao; // 主数据源
	
	// 从数据源
	@Resource
	private ICityDao cityDao;

	@Override
	public User findByName(String userName) {
		User user = userDao.findByName(userName);
		City city = cityDao.findByName("温岭市");
		user.setCity(city);
		return user;
	}

	// 多数据源要指定事务管理器，主数据源可以不加
	@Transactional(value="masterTransactionManager", rollbackFor=Exception.class)
	@Override
	public void addUser(int id, String userName) {
		userDao.addUser(id, userName);
//		throw new IllegalArgumentException();
	}
	


}
