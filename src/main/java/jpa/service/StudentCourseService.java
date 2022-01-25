package jpa.service;

import jpa.entitymodels.Course;
import jpa.entitymodels.StudentCourses;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class StudentCourseService {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("studentcoursepersist");
    EntityManager em = emf.createEntityManager();
     // used in main
    public List<Course> getAllStudentCourses(String sEmail){
        List<StudentCourses> studentcourses = em.createNamedQuery("CoursesByStudent", StudentCourses.class).setParameter("email",sEmail).getResultList();
        List<Course> courselist = new ArrayList<>();
        for (StudentCourses sc: studentcourses)
        {
            courselist.add(sc.getCourse());
        }
        return courselist;
    }
}
