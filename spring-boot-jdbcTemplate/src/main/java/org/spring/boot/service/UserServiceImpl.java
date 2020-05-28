package org.spring.boot.service;

import java.util.Map;

import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户业务逻辑实现类
 * https://www.cnblogs.com/cgfpx/p/11707486.html
 * @author zhangzhigang
 */
@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private IUser2Dao user2Dao;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/********************** required *******************************/
	
	@Transactional
	@Override
	public void notTransaction__required_not_transaction_exception() {
		// 会回滚
		user2Dao.insertException("李四");
		// 不会回滚
//		try {
//			user2Dao.insertException("李四");
//		} catch (Exception e) {
//			System.out.println("方法回滚");
//		}
	}
	
	/**
	 * 张三、李四都可以插入
	 * 外围方法未开启事务，内部两个方法都在自己的事务中独立运行，外围方法异常不影响内部方法独立的事务
	 */
	@Override
	public void notTransaction_exception_required_required() {
		userDao.insertRequired("张三");
		user2Dao.insertRequired("李四");
		throw new IllegalArgumentException();
	}

	/**
	 * 张三可以插入， 李四不能插入
	 * 外围方法没有事务，内部方法都在自己的事务中独立运行，所以方法2抛出异常只会回滚自己的事务，不会影响方法1的事务
	 */
	@Override
	public void notTransaction_required_required_exception() {
		userDao.insertRequired("张三");
		user2Dao.insertRequiredException("李四");
	}

	/**
	 * 张三、李四都未插入
	 * 外围方法开启事务，内部方法加入外围方法事务，外围方法回滚，内部方法事务也会回滚
	 */
	@Transactional
	@Override
	public void transaction_exception_required_required() {
		userDao.insertRequired("张三");
		user2Dao.insertRequired("李四");
		throw new IllegalArgumentException();
	}

	/**
	 * 张三，李四均未插入
	 * 外部方法开启事务，内部方法加入外部方法事务，内部方法抛出异常，外部方法感知到异常导致整体事务回滚
	 */
	@Transactional
	@Override
	public void transaction_required_required_exception() {
		userDao.insertRequired("张三");
		user2Dao.insertRequiredException("李四");
	}

	/**
	 * 张三，李四均未插入
	 * 外部方法开启事务，内部方法加入外部方法事务，内部方法抛出异常会标记当前事务为rollback-only,
	 * 即使异常在外部方法中被catch了，外部方法事务不会检测到异常，但会检测到rollback-only，从而导致整体事务回滚
	 */
	@Transactional
	@Override
	public void transaction_required_required_exception_try() {
		userDao.insertRequired("张三");
		try {
			user2Dao.insertRequiredException("李四");
		} catch (Exception e) {
			System.out.println("方法回滚");
		}
	}

	/********************** requires_new *******************************/
	
	/**
	 * 张三，李四均插入
	 * 外部方法没有事务，内部方法都在自己的事务中独立运行，外部方法抛出异常不会影响内部方法
	 */
	@Override
	public void notTransaction_exception_requiresNew_requiresNew() {
		userDao.insertRequiresNew("张三");
		user2Dao.insertRequiresNew("李四");
		throw new IllegalArgumentException();
	}

	/**
	 * 张三插入，李四未插入
	 * 外部方法没有开始事务，内部方法运行在自己独立事务内，插入李四方法抛出异常回滚，其它事务不受影响
	 */
	@Override
	public void notTransaction_requiresNew_requiresNew_exception() {
		userDao.insertRequiresNew("张三");
		user2Dao.insertRequiresNewException("李四");
	}

	/**
	 * 张三未插入，李四插入，王五插入
	 * 外部方法开启事务，插入张三方法使用外部方法事务，插入李四和王五的方法都运行在各自独立事务内，
	 * 外部方法抛异常回滚只会回滚同一事务的方法，所以，插入张三的方法会一同回滚
	 */
	@Transactional
	@Override
	public void transaction_exception_required_requiresNew_requiresNew() {
		userDao.insertRequired("张三");
		user2Dao.insertRequiresNew("李四");
		user2Dao.insertRequiresNew("王五");
		throw new IllegalArgumentException();
	}

	/**
	 * 张三未插入，李四插入，王五未插入
	 * 外部方法开启事务，插入张三方法使用外部方法事务，插入李四和王五的方法都运行在各自独立事务内，
	 * 插入王五方法抛出异常，首先自己的事务会回滚，随后，异常继续抛出被外部方法感知，外部方法事务会被回滚，
	 * 古插入张三的方法也会随之回滚
	 */
	@Transactional
	@Override
	public void transaction_required_requiresNew_requiresNew_exception() {
		userDao.insertRequired("张三");
		user2Dao.insertRequiresNew("李四");
		user2Dao.insertRequiresNewException("王五");
	}

	/**
	 * 张三插入，李四插入，王五未插入
	 * 外部方法开启事务，插入张三方法和外部方法共用同一个事务，插入李四和王五的方法都运行在各自独立事务内，
	 *  插入王五方法抛出异常，首先自己的事务会回滚，随后，异常外部方法catch，不会被外围事务感知到，
	 *  所以，外部方法事务不会回滚，插入张三方法会插入成功
	 */
	@Transactional
	@Override
	public void transaction_required_requiresNew_requiresNew_exception_try() {
		userDao.insertRequired("张三");
		user2Dao.insertRequiresNew("李四");
		try {
			user2Dao.insertRequiresNewException("王五");
		} catch (Exception e) {
			System.out.println("事务回滚");
		}
	}

	/********************** nested *******************************/
	
	/**
	 * 张三，李四均插入
	 * 外围方法未开启事务，内部方法均运行在各自独立事务中，外部方法异常不影响内部方法事务
	 */
	@Override
	public void notTransaction_exception_nested_nested() {
		userDao.insertNested("张三");
		user2Dao.insertNested("李四");
		throw new IllegalArgumentException();
	}

	/**
	 * 张三插入，李四未插入
	 * 外部方法未开启事务，内部方法均运行在各自独立事务中，插入李四方法抛出异常，只会让自己事务回滚，
	 * 虽然向外部方法抛出了异常，但不会影响插入张三方法事务
	 */
	@Override
	public void notTransaction_nested_nested_exception() {
		userDao.insertNested("张三");
		user2Dao.insertNestedException("李四");
	}

	/**
	 * 张三，李四均未插入
	 * 外部方法开启事务，内部方法事务是外部事务的子事务(savePoint)，外部方法事务回滚，子事务也会随之回滚
	 */
	@Transactional
	@Override
	public void transaction_exception_nested_nested() {
		userDao.insertNested("张三");
		user2Dao.insertNested("李四");
		throw new IllegalArgumentException();
	}

	/**
	 * 张三，李四均未插入
	 * 外围方法开启事务，内部方法事务是外部事务的子事务，内部方法抛异常回滚，且外部事务感知到异常会导致整体事务的回滚
	 */
	@Transactional
	@Override
	public void transaction_nested_nested_exception() {
		userDao.insertNested("张三");
		user2Dao.insertNestedException("李四");
	}

	/**
	 * 张三插入，李四未插入
	 * 外部方法开启事务，内部事务为外部事务子事务，插入李四方法抛异常回滚，且异常被外部方法catch，不会被外部事务感知，所以整体事务不会回滚
	 */
	@Transactional
	@Override
	public void transaction_nested_nested_exception_try() {
		userDao.insertNested("张三");
		try {
			user2Dao.insertNestedException("李四");
		} catch (Exception e) {
			System.out.println("事务回滚");
		}
	}
	
	/**
	 * 张三插入，李四插入(事务回滚失效)
	 * 外部方法开启事务，插入李四方法为外部事务子事务，这个方法抛出异常，按道理是应该回滚的，但实际并非如此，和transaction_nested_nested_exception_try比较一下
	 */
	@Transactional
	@Override
	public void transaction_fail() {
		user2Dao.insertRequired("张三");
		try {
			// 该方法不会被事务代理
			insertNestedException("李四");
		} catch (Exception e) {
			System.out.println("事务回滚");
		}
	}
	
	/**
	 * 张三插入，李四未插入(事务回滚生效)
	 * 根本原因和事务没有关系，是动态代理导致的
	 */
	@Transactional
	@Override
	public void transaction_success() {
		user2Dao.insertRequired("张三");
		try {
			// 该用法需要@EnableAspectJAutoProxy(exposeProxy=true)注解支持
			((IUserService)AopContext.currentProxy()).insertNestedException("李四");
		} catch (Exception e) {
			System.out.println("事务回滚");
		}
	}
	
	@Transactional(propagation=Propagation.NESTED)
	@Override
	public int insertNestedException(String name) {
		jdbcTemplate.update("insert into user2(name) values(?)", name);
		throw new IllegalArgumentException();
	}

	@Transactional
	@Override
	public void outTransactionNotCommitAndOtherConnectRead() {
		user2Dao.insertRequired("李四5");
		// 当不开启事务或propagation为REQUIRED/NESTED可以获取到，REQUIRES_NEW获取不到
		// 本质上看和上面的事务是不是在同一个事务中
		Map<String, Object> user = user2Dao.getUser("李四5");
		if (null != user) {
			System.out.println(user.get("id") + ": " + user.get("name"));
		}
	}
	
	
}
