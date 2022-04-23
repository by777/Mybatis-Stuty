package com.bai.dao;

import com.bai.pojo.Blog;
import com.bai.utils.IDUtils;
import com.bai.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
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

    @Test
    public void testQueryBlogIF() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);

        HashMap map = new HashMap();

//        List<Blog> blogList = blogMapper.queryBlogIF(map);
//        for (Blog blog : blogList) {
//            System.out.println(blog);
//        }

        map.put("title", "Java 如此简单");
//        List<Blog> blogList1 = blogMapper.queryBlogIF(map);
//        for (Blog blog : blogList1) {
//            System.out.println(blog);
//        }

        map.put("author", "Vu Bai");
//        List<Blog> blogList2 = blogMapper.queryBlogIF(map);
//        for (Blog blog : blogList2) {
//            System.out.println(blog);
//        }
        map.put("views", 1000);
        List<Blog> blogList3 = blogMapper.queryBlogChoose(map);
        for (Blog blog : blogList3) {
            System.out.println(blog);
        }
        sqlSession.close();
    }

    @Test
    public void testUpdateSet() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
        HashMap map = new HashMap();
        map.put("author", "BBBBB");
        map.put("id", "5d390ae750ff45399e985d15b77767f1");
        map.put("title", "如此简单");
        int res = blogMapper.updateBlog(map);
        System.out.println(res);
    }
}
