package com.gyh.service.impl;

import com.gyh.bean.User;

import java.util.List;

public interface UserService {
    /***
     * 根据名称获取用户
     * @param name
     * @return
     * @throws Exception
     */
    public List<User> getUsersByName(String name) throws Exception;

    /***
     * 新增用户
     * @param user
     * @return
     * @throws Exception
     */
    public User saveUser(User user) throws Exception;

}
