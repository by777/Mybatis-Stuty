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
        Teacher teacher2 = teacherMapper.getTeacher2(1);
//        Teacher(id=1, name=白老师,
//        students=[Student(id=1, name=小明, tid=1),
//        Student(id=2, name=小红, tid=1),
//        Student(id=3, name=小张, tid=1),
//        Student(id=4, name=小李, tid=1),
//        Student(id=5, name=小王, tid=1)])
        System.out.println(teacher);
        System.out.println(teacher2);
        sqlSession.close();
    }

}
