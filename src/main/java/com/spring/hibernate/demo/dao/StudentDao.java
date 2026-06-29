package com.spring.hibernate.demo.dao;

import java.util.List;

import com.spring.hibernate.demo.entity.Student;

public interface StudentDao {

    void save(Student student);

    Student findById(int id);

    void update(Student student);

    void delete(int id);
    
    List<Student> findAll();
    
    List<Student> findByLastName(String lastName);
    
    int deleteAll();
}
