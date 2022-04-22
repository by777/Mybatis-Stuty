package com.bai.dao;

import com.bai.pojo.User;

import java.util.List;

public interface UserMapper {
    // 查询全部用户
    List<User> getUserList();

    // 根据id查询用户
    User getUserById(int id);
    // 写完接口本来应该去写实现类，在mybatis中补充一个标签就可以了

    // 增加用户
    int addUser(User user);

    // 修改用户
    int updateUser(User user);

    // 删除用户
    int deleteUser(int id);

}
