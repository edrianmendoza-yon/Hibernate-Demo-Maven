package com.spring.hibernate.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.hibernate.demo.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class StudentDaoImplement implements StudentDao {

    public EntityManager entityManager;
    
    public StudentDaoImplement(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    @Override
    @Transactional
    public void save(Student student) {
        // TODO Auto-generated method stub
        entityManager.persist(student);
    }

    @Override
    //@Transactional
    public Student findById(int id) {
        // TODO Auto-generated method stub
        return entityManager.find(Student.class, id);
    }
    
    

    @Override
    @Transactional
    public void update(Student student) {
        // TODO Auto-generated method stub
        entityManager.merge(student);
    }

    @Override
    @Transactional
    public void delete(int id) {
        // TODO Auto-generated method stub
        Student student = entityManager.find(Student.class, id);
        entityManager.remove(student);
    }
    
    public List<Student> findAll() {
        // TODO Auto-generated method stub
        TypedQuery<Student> query = entityManager.createQuery("from Student order by lastName", Student.class);
        return query.getResultList();
    }
    
    public List<Student> findByLastName(String lastName)
    {
        TypedQuery<Student> query = entityManager.createQuery(
                "from Student where lastName=:lastName", Student.class);
        query.setParameter("lastName", lastName);
        return query.getResultList();
    }
    
    @Override
    @Transactional
    public int deleteAll() {
        // TODO Auto-generated method stub
        int numRows = entityManager.createQuery("delete from Student").executeUpdate();
        return numRows;
    }
}
