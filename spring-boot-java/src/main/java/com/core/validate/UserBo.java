package com.core.validate;

import javax.validation.constraints.Pattern;

/**
 * 登录用户
 * @author zhangzhigang
 */
public class UserBo {
	
//	@NotEmpty(message="登录账号不能为空")
    private String account;
	
//	@NotNull(message="deviceCode不能为空")
    private String deviceCode;
	
//	@NotNull(message="deviceType不能为空")
    private Integer deviceType;
	
//	@NotEmpty(message="手机号不能为空")
//	@Pattern(regexp="^1[0-9]{10}$",message="手机号格式不正确")
	@Pattern(regexp="^(\\d{4}-\\d{8})|(1[0-9]{10})$",message="手机号格式不正确")
	private String mobile;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}

	public int getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(int deviceType) {
		this.deviceType = deviceType;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setDeviceType(Integer deviceType) {
		this.deviceType = deviceType;
	}
    
	
    
}