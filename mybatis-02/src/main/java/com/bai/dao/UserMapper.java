package com.bai.dao;

import com.bai.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    // 根据id查询用户
    User getUserById(int id);
    // 写完接口本来应该去写实现类，在mybatis中补充一个标签就可以了


}
