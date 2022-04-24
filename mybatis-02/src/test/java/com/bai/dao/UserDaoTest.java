package com.bai.dao;

import com.bai.pojo.User;
import com.bai.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDaoTest {
    @Test
    public void test() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        try {
            // 方式1：getMapper
            //UserDao userDao = sqlSession.getMapper(UserDao.class);
            UserMapper userDao = sqlSession.getMapper(UserMapper.class); // 也要在核心配置文件绑定
            User user1 = userDao.getUserById(1);
            // User{id=1, name='hdehe', password='null'}
            // 这是因为sql语句实际上是select id, name, pwd from mybatis.user where id = 1;
            // 所以可以起别名：select id, name, pwd as password from mybatis.user where id = 1;
            // 或者 通过ResultMap来解决
            System.out.println(user1);

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            // 关闭session
            sqlSession.close();
        }


    }


}
