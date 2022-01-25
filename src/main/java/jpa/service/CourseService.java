package jpa.service;

import jpa.dao.CourseDAO;
import jpa.entitymodels.Course;
import jpa.entitymodels.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class CourseService implements CourseDAO {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("studentcoursepersist");
    EntityManager em = emf.createEntityManager();

    @Override
    public List<Course> getAllCourses() {
        try {
            String sql = "SELECT c FROM Course c";
            TypedQuery<Course> query = em.createQuery(sql, Course.class);
            List<Course> courseList = query.getResultList();

            return courseList;

        } catch (Exception e) {
            return null;

        }

    }

    public Course getCourseById(int cId){
        try {
            String sql = "SELECT c FROM Course c where cId = :cid";

            // set this to use the correct entity
            TypedQuery<Course> query = em.createQuery(sql, Course.class);
            query.setParameter("cid", cId);

            Course course = query.getSingleResult();

            return course;
        } catch (Exception e) {
            System.out.println("findByEmail : " + e.getMessage());
            return null;
        }
    }
}
