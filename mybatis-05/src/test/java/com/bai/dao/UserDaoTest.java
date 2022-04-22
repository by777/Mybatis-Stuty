package com.bai.dao;

import com.bai.pojo.User;
import com.bai.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class UserDaoTest {
    @Test
    public void test() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        // 底层主要应用反射
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = mapper.getUsers();
        System.out.println("使用注解看起来很简洁，但是需要使用ResultSet时就力不从心了");
        for (User user : userList) {
            System.out.println(user);
        }
        sqlSession.close();
    }
}
