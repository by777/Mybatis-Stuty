package com.bai.dao;

import com.bai.pojo.Student;
import com.bai.pojo.Teacher;
import com.bai.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class TestTS {

    @Test
    public void testGetTeacher() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        TeacherMapper teacherMapper = sqlSession.getMapper(TeacherMapper.class);
        Teacher teacher1 = teacherMapper.getTeacher(1);
        System.out.println(teacher1);
        sqlSession.close();
    }

    @Test
    public void TestStudent() {
        /*
         * 查询所有的学生对应的老师
         * 思路：
         *  1. 查询所有的学生信息
         *  2. 根据查询出来的学生的tid对应寻找对应的老师（子查询，按照查询嵌套处理）
         *      or 按照结果嵌套处理：getStudent2
         *
         * */
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> studentList = studentMapper.getStudent();
        for (Student student : studentList) {
            System.out.println(student);
        }

        List<Student> studentList1 = studentMapper.getStudent2();
        for (Student student : studentList1) {
            System.out.println(student);
        }

        sqlSession.close();

    }
}
