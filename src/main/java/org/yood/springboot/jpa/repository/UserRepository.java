package org.yood.springboot.jpa.repository;

import org.springframework.data.repository.CrudRepository;
import org.yood.springboot.jpa.entity.User;

public interface UserRepository extends CrudRepository<User, String> {

    User findByName(String username);
}
