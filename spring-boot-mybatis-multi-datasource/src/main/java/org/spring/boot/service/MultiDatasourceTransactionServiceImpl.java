package org.spring.boot.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *测试多数据源事务
 * 
 * @author zhangzhigang
 */
@Service
public class MultiDatasourceTransactionServiceImpl implements IMultiDatasourceTransationService {

	@Autowired
	private IUserService userService;
	
	@Autowired
	private ICityService cityService;
	
	/**
	 * 添加城市方法抛出异常，从数据源会回滚
	 * 外层方法感知到异常，会将主数据源回滚
	 */
	@Transactional(value="masterTransactionManager", rollbackFor=Exception.class)
	@Override
	public void multiDatasourceRollback() {
		userService.addUser(new Random().nextInt(1000000000), "zhangsan");
		cityService.addCity(111, "苏州");
	}


}
