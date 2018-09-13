package com.gyh.user.service.impl;

import com.gyh.user.bean.GyhUser;
import com.gyh.user.mapper.UserMapper;
import com.gyh.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * service
 *
 * @author gyh
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<GyhUser> getUsersByName(String name) {
        return userMapper.getUsersByName(name);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public GyhUser saveUser(GyhUser user) {
        userMapper.saveUser(user);
        return user;
    }

}
