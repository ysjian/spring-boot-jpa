package org.yood.springboot.jpa.service;


import org.springframework.security.core.userdetails.UserDetailsService;
import org.yood.springboot.jpa.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService extends UserDetailsService{

    void add(User user) throws SQLException;

    void update(User user) throws SQLException;

    List<User> getAll() throws SQLException;

    User getLoginUser(String username, String password);
}
