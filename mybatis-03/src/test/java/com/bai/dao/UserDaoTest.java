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
            List<User> userList = userDao.getUserList();
            for (User user : userList) {
                System.out.println(user);
            }
            // 方式2：不推荐
//            List<User> userList1 = sqlSession.selectList("com.bai.dao.UserDao.getUserList");
//            for (User user : userList1) {
//                System.out.println(user);
//            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            // 关闭session
            sqlSession.close();
        }


    }


}
