package org.spring.boot.service;

/**
 * 跨数据源事务管理
 * 
 * @author zhangzhigang
 */
public interface IMultiDatasourceTransationService {
	void multiDatasourceRollback();
}
