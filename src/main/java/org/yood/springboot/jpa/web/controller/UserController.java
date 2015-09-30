package org.yood.springboot.jpa.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.yood.springboot.jpa.entity.User;
import org.yood.springboot.jpa.service.UserService;
import org.yood.springboot.jpa.web.exception.UnAuthorizedException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "users",
                    method = RequestMethod.GET)
    public List<User> getAll() throws IOException, SQLException {
        return userService.getAll();
    }

    @RequestMapping(value = "users",
                    method = RequestMethod.PUT)
    public ResponseEntity<?> update(@RequestBody User user) throws UnAuthorizedException, SQLException {
        userService.update(user);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "users",
                    method = RequestMethod.POST)
    public ResponseEntity<?> add(@RequestBody User user) throws SQLException, UnAuthorizedException {
        if (StringUtils.isEmpty(user.getName()) || StringUtils.isEmpty(user.getSex())) {
            throw UnAuthorizedException.newInstance();
        }
        userService.add(user);
        return ResponseEntity.ok().build();
    }
}