package com.bai.dao;

import com.bai.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    @Select("select * from mybatis.user")
    List<User> getUsers();

    // 方法存在多个参数，所有参数前面必须加上@Param注解:(@Param("id") int id, @Param("name") String name
    @Select("select * from mybatis.user where id = #{id}")
    User getUserById(@Param("id") int id);

    @Insert("insert into mybatis.user (id, name, pwd) values (#{id}, #{name}, #{pwd})")
    int addUser(User user);

    @Update("update mybatis.user set name=#{name}, pwd=#{pwd} where id=#{id}")
    int updateUser(User user);

    @Delete("delete from mybatis.user where id = #{uid}")
    int deleteUser(@Param("uid") int id);
}
