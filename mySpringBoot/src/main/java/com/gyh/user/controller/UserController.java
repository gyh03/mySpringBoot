package com.gyh.user.controller;

import com.gyh.base.GyhResult;
import com.gyh.base.aop.OpeLogInfo;
import com.gyh.user.bean.GyhUser;
import com.gyh.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/***
 * @RestController 等价于 @Controller+@
 * @author gyh
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * rest api ,get method, get Users By Name
     *
     * @param name
     * @return
     */
    @RequestMapping(value = "user", method = RequestMethod.GET)
    @OpeLogInfo(node = "查询用户")
    public Object getUsersByName(String name) throws Exception {
        throw new Exception("demoMsg");
//        GyhResult res = new GyhResult();
//        if (name == null) {
//            res.setSuccess(false);
//            res.setMsg("name can not be null");
//            return res;
//        }
//        List<GyhUser> list = userService.getUsersByName(name);
//        res.setData(list);
//        return res;
    }

    /**
     * rest api,post method, save one User
     *
     * @param user
     * @return
     */
    @OpeLogInfo(node = "新建用户")
    @RequestMapping(value = "user", method = RequestMethod.POST)
    public Object saveUser(@Validated GyhUser user) {
        GyhResult res = new GyhResult();
        if (user == null) {
            res.setSuccess(false);
            res.setMsg("binding error");
            return res;
        }
        if (user.getName() == null) {
            res.setSuccess(false);
            res.setMsg("name can not be null");
            return res;
        }
        userService.saveUser(user);
        res.setData(user);
        return res;
    }

    @OpeLogInfo(node = "新建用户")
    @RequestMapping(value = "user2", method = RequestMethod.POST)
    public Object saveUser2(@Validated @RequestBody GyhUser user) {
        GyhResult res = new GyhResult();
        if (user == null) {
            res.setSuccess(false);
            res.setMsg("binding error");
            return res;
        }
        if (user.getName() == null) {
            res.setSuccess(false);
            res.setMsg("name can not be null");
            return res;
        }
        userService.saveUser(user);
        res.setData(user);
        return res;
    }
}
