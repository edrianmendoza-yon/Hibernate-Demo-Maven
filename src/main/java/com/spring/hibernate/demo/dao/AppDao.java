package com.spring.hibernate.demo.dao;

import java.util.List;

import com.spring.hibernate.demo.entity.Course;
import com.spring.hibernate.demo.entity.Instructor;
import com.spring.hibernate.demo.entity.InstructorDetail;
import com.spring.hibernate.demo.entity.Student;

public interface AppDao {
    void save(Instructor instructor);
    Instructor findInstructorById(int id);
    void deleteInstructorById(int id);
    InstructorDetail findInstructorDetailById(int id);
    void deleteInstructorDetailById(int id);
    List<Course> findCoursesByInstructorId(int instructorId);
    Instructor findInstructorByJoinFetch(int instructorId);
    void update(Instructor instructor);
    //void addReview(Review review);
    void save(Course course);
    Course findCourseAndReviewsById(int courseId);
    void deleteCourseById(int courseId); 
    Course findCourseAndStudentBy(int courseId);
    Student findStudentAndCourseBy(int studentId);
    void update(Student student);
}
