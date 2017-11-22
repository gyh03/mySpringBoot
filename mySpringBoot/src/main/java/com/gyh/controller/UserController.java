package com.gyh.controller;

import com.gyh.bean.User;
import com.gyh.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Object getUsersByName(String name){
        Map<String,Object> res =new HashMap<>();
        try {
            if(name == null){
                res.put("msg","name can not be null");
                return  res;
            }
            List<User> list = userService.getUsersByName(name);
            res.put("data",list);
        } catch (Exception e) {
            e.printStackTrace();
            res.put("msg","error:"+e.getMessage());
        }
        return res;
    }

    /**
     * rest api,post method, save one User
     * @param user
     * @return
     */
    @RequestMapping(value = "user",method = RequestMethod.POST)
    public Object saveUser(User user){
        Map<String,Object> res =new HashMap<>();
        try {
            if(user == null){
                res.put("msg","binding error");
                return  res;
            }
            if(user.getName() == null){
                res.put("msg","name can not be null");
                return  res;
            }
            user.setCreatetime(new Date());
            userService.saveUser(user);
            res.put("data",user);
        } catch (Exception e) {
            e.printStackTrace();
            res.put("msg","error:"+e.getMessage());
        }
        return res;
    }
}
