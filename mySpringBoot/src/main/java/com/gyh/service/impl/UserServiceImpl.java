package com.gyh.service.impl;

import com.gyh.bean.User;
import com.gyh.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * service
 * @author gyh
 */
@Service
public class UserServiceImpl implements  UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getUsersByName(String name) throws Exception {
        return userMapper.getUsersByName(name);
    }

    @Override
    @Transactional
    public User saveUser(User user) throws Exception {
        userMapper.saveUser(user);
        return user;
    }
}
