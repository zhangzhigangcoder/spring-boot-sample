package org.spring.boot.service;

/**
 * 用户业务逻辑接口类
 * 
 * @author zhangzhigang
 */
public interface IUserService {
	
	/********************** required *******************************/
	
	void notTransaction__required_not_transaction_exception();
	
	void notTransaction_exception_required_required();
	
	void notTransaction_required_required_exception();
	
	void transaction_exception_required_required();
	
	void transaction_required_required_exception();
	
	void transaction_required_required_exception_try();
	
	/********************** requires_new *******************************/
	
	void notTransaction_exception_requiresNew_requiresNew();
	
	void notTransaction_requiresNew_requiresNew_exception();
	
	void transaction_exception_required_requiresNew_requiresNew();
	
	void transaction_required_requiresNew_requiresNew_exception();
	
	void transaction_required_requiresNew_requiresNew_exception_try();
	
	/********************** nested *******************************/

	void notTransaction_exception_nested_nested();
	
	void notTransaction_nested_nested_exception();
	
	void transaction_exception_nested_nested();
	
	void transaction_nested_nested_exception();
	
	void transaction_nested_nested_exception_try();
	
	void transaction_fail();
	
	void transaction_success();
	
	int insertNestedException(String name);
	
	void outTransactionNotCommitAndOtherConnectRead();

}
