package com.gyh.mapper;

import com.gyh.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * mapper
 * @author gyh
 */
@Mapper
public interface UserMapper {

    @Select("SELECT id,username name,birthday from t_user  WHERE  username = #{id}")
    public User getUserById(Long id) throws Exception;

    //@Select("SELECT id,username name,birthday from t_user  WHERE  username = #{name}")
    //使用xml 定义sql
    public List<User> getUsersByName(String name) throws Exception;


    @Insert("insert into t_user(username,birthday) values (#{name},#{birthday})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    public void saveUser(User user) throws Exception;
}
