package com.gyh.user.mapper;

import com.gyh.user.bean.GyhUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * mapper
 *
 * @author gyh
 */
//@Mapper
public interface UserMapper {

    @Select("SELECT id,username name,birthday from t_user  WHERE  username = #{id}")
    GyhUser getUserById(Long id);

    //@Select("SELECT id,username name,birthday from t_user  WHERE  username = #{name}")
    //使用xml 定义sql
    List<GyhUser> getUsersByName(String name);


    @Insert("insert into t_user(username,birthday) values (#{name},#{birthday})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void saveUser(GyhUser user);

}
