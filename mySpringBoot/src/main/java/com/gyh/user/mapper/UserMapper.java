package com.gyh.user.mapper;

import com.gyh.user.bean.GyhUser;
import com.gyh.user.bean.TOrg;
import org.apache.ibatis.annotations.Insert;
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

    @Select("SELECT id, org_name  AS orgName FROM manager_org_budget WHERE `dt` = '2020-04-30'")
    List<TOrg> testTorg();

    //@Select("SELECT id,username name,birthday from t_user  WHERE  username = #{name}")
    //使用xml 定义sql
    List<GyhUser> getUsersByName(String name);


    @Insert("insert into t_user(username,birthday) values (#{name},#{birthday})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void saveUser(GyhUser user);

}
