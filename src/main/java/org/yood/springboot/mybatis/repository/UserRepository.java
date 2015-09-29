package org.yood.springboot.mybatis.repository;

import org.springframework.data.repository.CrudRepository;
import org.yood.springboot.mybatis.entity.User;

public interface UserRepository extends CrudRepository<User, String> {

    User findByName(String username);
}
