package com.bai.dao;

import com.bai.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    // 根据查询指定的用户
    User queryUserById(@Param("id") int id);

    // 修改用户信息
    int updateUser(User user);

}
