package org.spring.boot.entity.secondary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 用户实体类
 * 
 * @author zhangzhigang
 */
@Entity
public class User {

    /**
     * 编号
     */
	@Id
	@GeneratedValue
    private Long id;


    /**
     * 姓名
     */
	@Column(name = "user_name")
    private String userName;

    /**
     * 描述
     */
	@Column(name = "description")
    private String description;
	
	public User() {}
    
	public User(String userName) {
		this.userName = userName;
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
