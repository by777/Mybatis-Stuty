package com.bai.dao;

import com.bai.pojo.Student;

import java.util.List;

public interface StudentMapper {
    // 查询所有的学生信息，以及对应的老师信息
    // select s.id, s.name, t.name  from mybatis.student s, mybatis.teacher t where s.tid = t.id;
    public List<Student> getStudent();

    public List<Student> getStudent2();

}
