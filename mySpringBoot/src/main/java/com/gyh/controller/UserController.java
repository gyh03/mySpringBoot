package com.gyh.controller;

import com.gyh.aspect.OpeLogInfo;
import com.gyh.bean.User;
import com.gyh.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * @param name
     * @return
     */
    @RequestMapping(value = "user",method = RequestMethod.GET)
    public Object getUsersByName(String name) throws Exception {
        Map<String,Object> res =new HashMap<>();
        if(name == null){
            res.put("msg","name can not be null");
            return  res;
        }
        List<User> list = userService.getUsersByName(name);
        res.put("data",list);
        return res;
    }

    /**
     * rest api,post method, save one User
     * @param user
     * @return
     */
    @OpeLogInfo(node = "新建用户")
    @RequestMapping(value = "user",method = RequestMethod.POST)
    public Object saveUser(User user) throws Exception {
        Map<String,Object> res =new HashMap<>();
        if(user == null){
            res.put("msg","binding error");
            return  res;
        }
        if(user.getName() == null){
            res.put("msg","name can not be null");
            return  res;
        }
        userService.saveUser(user);
        res.put("data",user);

        return res;
    }
}