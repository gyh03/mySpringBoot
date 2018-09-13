package com.gyh.user.service;

import com.gyh.user.bean.GyhUser;

import java.util.List;

public interface UserService {
    /***
     * 根据名称获取用户
     * @param name
     * @return
     * @throws Exception
     */
    List<GyhUser> getUsersByName(String name);

    /***
     * 新增用户
     * @param user
     * @return
     * @throws Exception
     */
    GyhUser saveUser(GyhUser user);

}
