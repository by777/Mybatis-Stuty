package com.bai.dao;

import com.bai.pojo.User;
import com.bai.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class UserDaoTest {

    @Test
    public void testGetUserById() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        System.out.println("1.========================");
        User user1 = userMapper.queryUserById(1);
//        System.out.println(user1);

        User user2 = userMapper.queryUserById(1);
//        System.out.println(user2);
        System.out.println("观察log，两次的查询了同一个用户，是在一级缓存里的，SQL走了一次");
        System.out.println(user1 == user2);

        System.out.println("2.******************");
        userMapper.queryUserById(2);
        userMapper.queryUserById(3);
        System.out.println("观察log，两次的查询了不同用户，SQL走了2次");

        System.out.println("3.-----------------");
        userMapper.queryUserById(1);
        userMapper.updateUser(new User(2, "name", "pwd"));
        userMapper.queryUserById(1);
        System.out.println("查询1号用户，此时修改了2号用户，虽然与1号用户无关，但是观察到还是走了2次sql");

        System.out.println("4.###############");
        userMapper.queryUserById(2);//A
        sqlSession.clearCache();
        userMapper.queryUserById(2);//B
        System.out.println("<?手动清除缓存，（应当？）必定引起缓存刷新>");
        System.out.println("（非常奇怪，如果AB处参数填1，还是只走一次SQL：" +
                "https://cloud.tencent.com/developer/article/1522959）");

        sqlSession.close();
    }

    @Test
    public void testTwoSqlSession() {
        SqlSession sqlSession1 = MybatisUtils.getSqlSession();
        SqlSession sqlSession2 = MybatisUtils.getSqlSession();

        UserMapper userMapper1 = sqlSession1.getMapper(UserMapper.class);
        UserMapper userMapper2 = sqlSession2.getMapper(UserMapper.class);

        System.out.println(sqlSession1 == sqlSession2);
        System.out.println("====session1和session2是两个不一样的对象====");

        User user1 = userMapper1.queryUserById(1);
        System.out.println(user1);

        // 此时另一级缓存失效，由于二级缓存开启，sqlSession1中的内容被二级缓存继承，
        // 因此，接下来查询相同的内容，依旧只执行sql一次
        sqlSession1.close();

        User user2 = userMapper2.queryUserById(1);
        System.out.println(user2);

        System.out.println("mapper.xml中尝试增加/删除cache标签，可以观察到sql语句分别走了1/2次");
        System.out.println(user1 == user2);
        sqlSession2.close();
    }
}
