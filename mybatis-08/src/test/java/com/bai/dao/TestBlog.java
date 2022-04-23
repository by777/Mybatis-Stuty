package com.bai.dao;

import com.bai.pojo.Blog;
import com.bai.utils.IDUtils;
import com.bai.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.Date;
import java.util.Map;

public class TestBlog {


    @Test
    public void testBlogInsert() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);

        Blog blog = new Blog();
        blog.setId(IDUtils.genID());
        blog.setCreate_time(new Date());
        blog.setTitle("Mybatis 如此简单");
        blog.setViews(10000);
        blog.setAuthor("Xu Bai");
        blogMapper.addBlog(blog);

        blog.setId(IDUtils.genID());
        blog.setCreate_time(new Date());
        blog.setTitle("Java 如此简单");
        blog.setViews(2000);
        blog.setAuthor("Vu Bai");
        blogMapper.addBlog(blog);

        blog.setId(IDUtils.genID());
        blog.setCreate_time(new Date());
        blog.setTitle("Spring 如此简单");
        blog.setViews(1000);
        blog.setAuthor("Vu Vai");
        blogMapper.addBlog(blog);

        blog.setId(IDUtils.genID());
        blog.setCreate_time(new Date());
        blog.setTitle("微服务 如此简单");
        blog.setViews(9999);
        blog.setAuthor("Vuu Vai");
        blogMapper.addBlog(blog);

        sqlSession.commit();

        sqlSession.close();
    }
}
