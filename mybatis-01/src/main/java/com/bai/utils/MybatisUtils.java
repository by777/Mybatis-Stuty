package com.bai.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ResourceBundle;

// 教程链接：https://mybatis.org/mybatis-3/zh/index.html
public class MybatisUtils {
    private static SqlSessionFactory sqlSessionFactory;

    static {
        // 1.一初始就加载
        try {
            // 获取sqlSessionFactory对象
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            // sqlSession是用来执行数据库的所有方法
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 2. 获取sqlSession
    public static SqlSession getSqlSession() {
        
        return sqlSessionFactory.openSession();
    }
}
