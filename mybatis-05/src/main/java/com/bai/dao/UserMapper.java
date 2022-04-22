package com.bai.dao;

import com.bai.pojo.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    @Select("select * from mybatis.user")
    List<User> getUsers();
}
