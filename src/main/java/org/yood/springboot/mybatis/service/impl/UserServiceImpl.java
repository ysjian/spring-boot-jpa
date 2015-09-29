package org.yood.springboot.mybatis.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yood.springboot.mybatis.entity.User;
import org.yood.springboot.mybatis.repository.AuthorityRepository;
import org.yood.springboot.mybatis.repository.UserRepository;
import org.yood.springboot.mybatis.service.UserService;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public void add(User user) throws SQLException {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void update(User user) throws SQLException {
    }


    @Override
    public List<User> getAll() throws SQLException {
        return null;
    }

    @Override
    public User getLoginUser(String username, String password) {
        User loginUser = new User(username);
        return loginUser;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOGGER.info("username = {}", username);
        try {
            User user = userRepository.findByName(username);
            List<GrantedAuthority> roles = authorityRepository.findByUserName(username)
                    .stream()
                    .map(authority -> new SimpleGrantedAuthority(authority.getRole().name()))
                    .collect(Collectors.toList());
            return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), roles);
        } catch (SQLException e) {
            throw new UsernameNotFoundException("SQLException", e);
        }
    }
}
