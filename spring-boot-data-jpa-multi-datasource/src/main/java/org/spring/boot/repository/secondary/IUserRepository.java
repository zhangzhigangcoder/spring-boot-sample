package org.spring.boot.repository.secondary;

import org.spring.boot.entity.secondary.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository  extends JpaRepository<User, Long>{
}