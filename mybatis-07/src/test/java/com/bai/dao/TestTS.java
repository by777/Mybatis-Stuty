package com.bai.dao;

import com.bai.dao.StudentMapper;
import com.bai.dao.TeacherMapper;
import com.bai.pojo.Student;
import com.bai.pojo.Teacher;
import com.bai.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class TestTS {
//    @Test
//    public void testTeacher() {
//        SqlSession sqlSession = MybatisUtils.getSqlSession();
//        TeacherMapper teacherMapper = sqlSession.getMapper(TeacherMapper.class);
//        List<Teacher> teachers = teacherMapper.getTeacher(1);
//        for (Teacher teacher : teachers) {
//            System.out.println(teacher);
//        }
//        sqlSession.close();
//    }

    @Test
    public void testTeacher2() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        TeacherMapper teacherMapper = sqlSession.getMapper(TeacherMapper.class);
        Teacher teacher = teacherMapper.getTeacher(1);
        System.out.println();
        sqlSession.close();
    }

}
