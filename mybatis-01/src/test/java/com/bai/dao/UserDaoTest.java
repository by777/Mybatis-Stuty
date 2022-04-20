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

    @Test
    public void getUserById() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user1 = userMapper.getUserById(1);
        System.out.println(user1);
        sqlSession.close();
    }

    @Test
    public void addUser() {
        // 增删改需要提交事务
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        int res = userMapper.addUser(new User(5, "哈哈", "12123"));
        if (res > 0) {
            System.out.println("插入成功");
        }
        // 提交事务
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void updateUser() {
        // 增删改需要提交事务
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        int res = userMapper.updateUser(new User(2, "哈哈", "213"));


        // 提交事务
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void deleteUser() {
        // 增删改需要提交事务
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        int res = userMapper.deleteUser(4);


        // 提交事务
        sqlSession.commit();
        sqlSession.close();
    }

}
