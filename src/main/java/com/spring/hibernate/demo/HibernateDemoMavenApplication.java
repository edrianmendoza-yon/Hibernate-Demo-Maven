package com.spring.hibernate.demo;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.Bean;

import com.spring.hibernate.demo.dao.AppDao;
import com.spring.hibernate.demo.dao.StudentDao;
import com.spring.hibernate.demo.entity.Course;
import com.spring.hibernate.demo.entity.Instructor;
import com.spring.hibernate.demo.entity.InstructorDetail;
import com.spring.hibernate.demo.entity.Review;
import com.spring.hibernate.demo.entity.Student;

@SpringBootApplication
@EntityScan(basePackages = "com.spring.hibernate.demo.entity")
public class HibernateDemoMavenApplication {

	public static void main(String[] args) {
		SpringApplication.run(HibernateDemoMavenApplication.class, args);
	}

    @Bean
    CommandLineRunner commandLineRunner(AppDao appDao) {
        return runner -> {
            //System.out.println("Hello World");
            //createStudent(studentDao);
            //readStudent(studentDao);
            //queryStudents(studentDao);
            //queryStudentsLastName(studentDao);
            //updateStudent(studentDao);
            //deleteStudent(studentDao);
            //deleteAllStudents(studentDao);
            //createInstructor(appDao);
            //findInstructorById(appDao);
            //deleteInstructorById(appDao);
            //findInstructorDetailById(appDao);
            //deleteInstructorDetailById(appDao);
            //createInstructorWithCourses(appDao);
            //findInstructorWithCourses(appDao);
            //findCoursesForInstructorId(appDao);
            //findInstructorByJoinFetch(appDao);
            //updateInstructor(appDao);
            //deleteInstructorOnly(appDao);
            //createCourseAndReview(appDao);
            //retrieveCourseAndReviews(appDao);
            //deleteCourseAndReviews(appDao);
            //createCourseAndStudents(appDao);
            //findCourseAndStudents(appDao);
            //findStudentAndCourses(appDao);
            //addMoreCoursesForStudent(appDao);
            //deleteCourseById(appDao);
        };
    }
    
    public void createCourseAndReview(AppDao appDao) {
        System.out.println("Creating new course object...");
        Course course = new Course("Boxing");

        System.out.println("Adding reviews...");
        course.addReview(new Review("Great course!"));
        course.addReview(new Review("Very informative."));
        course.addReview(new Review("I learned a lot."));
        //System.out.println("Reviews: " + course.getReviews());
        
        System.out.println("Saving the course...");
        appDao.save(course);
        System.out.println("Saved course. Generated id: " + course.getId());
    }
    
    public void retrieveCourseAndReviews(AppDao appDao) {
        int courseId = 5;
        System.out.println(
                "Retrieving course and reviews for course id: " + courseId);
        Course course = appDao.findCourseAndReviewsById(courseId);

        System.out.println("Found course: " + course);
        System.out.println("Associated reviews: " + course.getReviews());
    }
    
    public void deleteCourseAndReviews(AppDao appDao) {
        int courseId = 5;
        System.out.println(
                "Deleting course and reviews for course id: " + courseId);
        appDao.deleteCourseById(courseId);

        System.out.println(
                "Deleted course and reviews for course id: " + courseId);
    }
    
    public void createCourseAndStudents(AppDao appDao)
    {
        Course course = new Course("Muay Thai");
        
        Student studentA = new Student("Heart", "Fuents", "dalion@fuentes.com");
        Student studentB = new Student("Ed", "Lalim", "deep@sea.com");
        
        course.addStudent(studentA);
        course.addStudent(studentB);
        
        appDao.save(course);
        
        System.out.println("Successfully Enrolled!");
    }
    
    public void findCourseAndStudents(AppDao appDao)
    {
        int courseId = 6;
        Course course = appDao.findCourseAndStudentBy(courseId);
        
        System.out.println("Course: " + course);
        System.out.println("Students: " + course.getStudents());
    }
    
    public void findStudentAndCourses(AppDao appDao) {
        int studentId = 22;
        Student student = appDao.findStudentAndCourseBy(studentId);
        
        System.out.println("Student: " + student);
        System.out.println("Courses: " + student.getCourses());
    }
    
    public void addMoreCoursesForStudent(AppDao appDao)
    {
        int studentId = 23;
        Student student = appDao.findStudentAndCourseBy(studentId);
        
        Course restapiCourse = new Course("REST API");
        Course dockerCourse = new Course("Docker");
        Course postgreCourse = new Course("Postgre SQL");
        
        student.addCourse(postgreCourse);
        student.addCourse(dockerCourse);
        student.addCourse(restapiCourse);
        
        System.out.println("Successfully enrolled to new courses");
        
        appDao.update(student);
    }
    
    private void deleteCourseById(AppDao appDao)
    {
        int id = 22;
        System.out.println("Delete Course with ID: " + id);
        
        appDao.deleteCourseById(id);
        System.out.println("Deleted Successfully!");
    }
    
    public void createInstructor(AppDao appDao) {
        System.out.println("Creating new instructor object...");
        Instructor instructor = new Instructor ("Under", "Taker", "undertaker@coffin.com");
        InstructorDetail instructorDetail = new InstructorDetail("Graveyard Maintenance", "Shoveling");
        
        instructor.setInstructorDetail(instructorDetail);
        System.out.println("Saving the instructor...");
        appDao.save(instructor);
        System.out.println("Saved instructor. Generated id: " + instructor.getId());
    }
    
    private void findInstructorById(AppDao appDao) {
        int id = 1;
        System.out.println("Finding instructor with id: " + id);
        Instructor instructor = appDao.findInstructorById(id);

        System.out.println("Found instructor: " + instructor);
    }
    
    private void deleteInstructorById(AppDao appDao) {
        int id = 4;
        System.out.println("Deleting instructor with id: " + id);
        appDao.deleteInstructorById(id);

        System.out.println("Deleted instructor with id: " + id);
    }
    
    private void findInstructorDetailById(AppDao appDao) {
        int id = 5;
        System.out.println("Finding instructor detail with id: " + id);
        InstructorDetail instructorDetail = appDao.findInstructorDetailById(id);

        System.out.println("Found instructor detail: " + instructorDetail);
        System.out.println("Associated instructor: " + instructorDetail.getInstructor());
    }
    
    private void deleteInstructorDetailById(AppDao appDao) {
        int id = 7;
        System.out.println("Deleting instructor detail with id: " + id);
        
        
        appDao.deleteInstructorDetailById(id);
        System.out.println("Deleted instructor detail with id: " + id);
    }
    
    private void createInstructorWithCourses(AppDao appDao) {
        System.out.println("Creating new instructor object...");
        Instructor instructor = new Instructor("Julia", "Tabaco",
                "julia.tabaco@example.com");
        InstructorDetail instructorDetail = new InstructorDetail("Mathematics",
                "Reading");
        
        instructor.setInstructorDetail(instructorDetail);
        
        Course javaCourse = new Course("Java Programming");
        Course javascriptCourse = new Course("JavaScript Programming");
        
        instructor.add(javaCourse);
        instructor.add(javascriptCourse);
        System.out.println("Saving the instructor and courses...");
        appDao.save(instructor);
        System.out.println("Saved instructor and courses. Generated instructor id: " + instructor.getId() + " Courses: " + instructor.getCourses());
        }
    
    private void findInstructorWithCourses(AppDao appDao) {
        int id = 6;
        System.out.println("Finding instructor with courses for id: " + id);
        Instructor instructor = appDao.findInstructorById(id);

        System.out.println("Found instructor: " + instructor);
        System.out.println("Associated courses: " + instructor.getCourses());
    }
    
    private void findCoursesForInstructorId(AppDao appDao) {
        int instructorId = 6;
        System.out
                .println("Finding courses for instructor id: " + instructorId);
        Instructor instructor = appDao.findInstructorById(instructorId);
        System.out.println("Instructor: " + instructor);
        
        System.out.println("Finding courses by instructor id...");
        List<Course> courses = appDao.findCoursesByInstructorId(instructorId);
        System.out.println("Found courses: " + courses);
        
        instructor.setCourses(courses);
        System.out.println("Instructor with courses: " + instructor.getCourses());
    }
    
    private void findInstructorByJoinFetch(AppDao appDao) {
        int instructorId = 6;
        System.out.println(
                "Finding instructor by join fetch for id: " + instructorId);
        Instructor instructor = appDao.findInstructorByJoinFetch(instructorId);

        System.out.println("Found instructor: " + instructor);
        System.out.println("Associated courses: " + instructor.getCourses());
    }
    
    private void deleteInstructorOnly(AppDao appDao)
    {
        int instructorId = 7;
        System.out.println("Deleting instructor only for id: " + instructorId);
        appDao.deleteInstructorById(instructorId);

        System.out.println("Deleted instructor only for id: " + instructorId);
    }
    
    private void createStudent(StudentDao studentDao) {
        System.out.println("Creating new student object...");
        Student student = new Student("John", "Doe", "johndoe@email.com");
        
        System.out.println("Saving the student...");
        studentDao.save(student);
        
        System.out.println("Saved student. Generated id: " + student.getId());
    }
    
    private void updateInstructor(AppDao appDao) {
        int id = 1;
        System.out.println("Retrieving instructor with id: " + id);
        Instructor instructor = appDao.findInstructorById(id);

        System.out.println("Updating instructor...");
        instructor.setLastName("Smith");
        appDao.update(instructor);

        System.out.println("Updated instructor: " + instructor);
    }
    
    private void readStudent(StudentDao studentDao) {
        System.out.println("Retrieving student with id...");
        Student student = studentDao.findById(1);
        
        System.out.println("Retrieved student: " + student);
    }
    
    private void queryStudents(StudentDao studentDao) {
        List<Student> students = studentDao.findAll();
        for (Student student : students) {
            System.out.println(student);
        }
    }
    
    private void queryStudentsLastName(StudentDao studentDao) {
        List<Student> students = studentDao.findByLastName("Do");
        for (Student student : students) {
            System.out.println(student);
        }
    }
    
    private void updateStudent(StudentDao studentDao) {
        int id = 11;
        System.out.println("Retrieving student with id: " + id);
        Student student = studentDao.findById(id);
        
        System.out.println("Updating student...");
        student.setLastName("Do");
        studentDao.update(student);
        
        System.out.println("Updated student: " + student);
    }
    
    private void deleteStudent(StudentDao studentDao) {
        int id = 10;
        System.out.println("Deleting student with id: " + id);
        studentDao.delete(id);

        System.out.println("Deleted student with id: " + id);
    }
    
    private void deleteAllStudents(StudentDao studentDao) {
        System.out.println("Deleting all students...");
        int rowsDeleted = studentDao.deleteAll();

        System.out.println("Deleted all students. " + rowsDeleted + " rows deleted.");
    }
}
