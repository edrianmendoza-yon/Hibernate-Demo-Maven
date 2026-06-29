package com.spring.hibernate.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.hibernate.demo.entity.Course;
import com.spring.hibernate.demo.entity.Instructor;
import com.spring.hibernate.demo.entity.InstructorDetail;
import com.spring.hibernate.demo.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class AppDaoImplement implements AppDao {
    
    private EntityManager entityManager;
    
    public AppDaoImplement(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    @Override
    @Transactional  
    public void save(Instructor instructor) {
        // Implementation code to save the instructor entity to the database
        entityManager.persist(instructor);
    }
    
    @Override
    public Instructor findInstructorById(int id) {
        // Implementation code to find an instructor by ID
        return entityManager.find(Instructor.class, id);
    }
    
    @Override
    @Transactional
    public void deleteInstructorById(int id) {
        // Implementation code to delete an instructor by ID
        Instructor instructor = entityManager.find(Instructor.class, id);
        List<Course> courses = instructor.getCourses();
        if (!courses.isEmpty()) {
            for (Course course : courses) {
                System.out.println("Unassigning course: " + course);
                course.setInstructor(null);
            }
        }
        
        if (instructor != null) {
            entityManager.remove(instructor);
        }
    }
    
    @Override
    public InstructorDetail findInstructorDetailById(int id) {
        // Implementation code to find an instructor detail by ID
        return entityManager.find(InstructorDetail.class, id);
    }
    
    @Override
    @Transactional
    public void deleteInstructorDetailById(int id) {
        // Implementation code to delete an instructor detail by ID
        InstructorDetail instructorDetail = entityManager
                .find(InstructorDetail.class, id);
        instructorDetail.getInstructor().setInstructorDetail(null); // Break the bi-directional link
        if (instructorDetail != null) {
            entityManager.remove(instructorDetail);
        }
    }
    
    @Override
    public List<Course> findCoursesByInstructorId(int instructorId) {
        // Implementation code to find courses by instructor ID
        TypedQuery<Course> query = entityManager.createQuery(
                "from Course where instructor.id=:instructorId", Course.class);
        query.setParameter("instructorId", instructorId);
        return query.getResultList();
    }
    
    @Override
    public Instructor findInstructorByJoinFetch(int instructorId) {
        // Implementation code to find an instructor by ID using a join fetch
        TypedQuery<Instructor> query = entityManager.createQuery(
                "select i from Instructor i " 
                        + "JOIN FETCH i.courses "
                        + "JOIN FETCH i.instructorDetail "
                        + "where i.id=:instructorId",
                Instructor.class);
        query.setParameter("instructorId", instructorId);
        return query.getSingleResult();
    }
    
    @Override
    @Transactional
    public void update(Instructor instructor) {
        // Implementation code to update an instructor entity
        entityManager.merge(instructor);
    }
    
    @Override
    @Transactional
    public void save(Course course) {
        // Implementation code to save a course entity
        entityManager.persist(course);
    }
    
    public Course findCourseAndReviewsById(int courseId) {
        // Implementation code to find a course and its reviews by ID
        TypedQuery<Course> query = entityManager
                .createQuery("select c from Course c " + "JOIN FETCH c.reviews "
                        + "where c.id=:courseId", Course.class);
        query.setParameter("courseId", courseId);
        return query.getSingleResult();
    }
    
    @Override
    @Transactional
    public void deleteCourseById(int courseId) {
        // Implementation code to delete a course by ID
        Course course = entityManager.find(Course.class, courseId);
        if (course != null) {
            entityManager.remove(course);
        }
    }
    
    @Override
    public Course findCourseAndStudentBy(int courseId)
    {
        TypedQuery<Course> query = entityManager.createQuery("Select c from Course c "
                + "JOIN FETCH c.students "
                + "WHERE c.id=:courseId", Course.class);
        query.setParameter("courseId", courseId);
        return query.getSingleResult();
    }
    
    public Student findStudentAndCourseBy(int studentId)
    {
        TypedQuery<Student> query = entityManager.createQuery("Select s from Student s "
                + "JOIN FETCH s.courses "
                + "WHERE s.id=:studentId ", Student.class);
        query.setParameter("studentId", studentId);
        return query.getSingleResult();
    }
    
    @Override
    @Transactional
    public void update(Student student)
    {
        entityManager.merge(student);
    }
}
