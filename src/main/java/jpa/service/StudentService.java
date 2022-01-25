package jpa.service;

import jpa.dao.StudentDAO;
import jpa.entitymodels.Course;
import jpa.entitymodels.Student;
import jpa.entitymodels.StudentCourses;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class StudentService implements StudentDAO {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("studentcoursepersist");
    EntityManager em = emf.createEntityManager();

    @Override
    public List<Student> getAllStudents() {
        try {
            String sql = "SELECT s FROM Student s";
            TypedQuery<Student> query = em.createQuery(sql, Student.class);
            List<Student> student = query.getResultList();
            return student;

        } catch (Exception e) {
            return null;

        }
    }

    @Override
    public Student getStudentByEmail(String sEmail) {
        try {
            String sql = "SELECT s FROM Student s where sEmail = :email";

            // set this to use the correct entity
            TypedQuery<Student> query = em.createQuery(sql, Student.class);
            query.setParameter("email", sEmail);

            Student student = query.getSingleResult();

            return student;
        } catch (Exception e) {
            System.out.println("findByEmail : " + e.getMessage());
            return null;
        }

    }

    @Override
    public boolean validateStudent(String sEmail, String sPassword) {
        try {
            String sql = "SELECT s FROM Student s where sEmail = :email and sPass = :password";
            TypedQuery<Student> query = em.createQuery(sql, Student.class);
            query.setParameter("email", sEmail);
            query.setParameter("password", sPassword);
            Student student = query.getSingleResult();
            if(student !=null){
                return true;
            }else
                return false;

        } catch (Exception e) {
            System.out.println("ValidateStudent : " + e.getMessage());
            return false;

        }

    }

    @Override
    public void registerStudentToCourse(String sEmail, int cId) {
        try {
            em.getTransaction().begin();

            StudentCourses s1=new StudentCourses();
            s1.seteMail(sEmail);
            s1.setCourseID(cId);

            em.persist(s1);

            em.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("findByEmail : " + e.getMessage());
        }

    }

    @Override
    public List<Course> getStudentCourses(String sEmail) {
        //retrieves list of courses the student is registered to based on email passed.
        List<StudentCourses> studentcourses = em.createNamedQuery("CoursesByStudent", StudentCourses.class).setParameter("email",sEmail).getResultList();
        List<Course> courselist = new ArrayList<>();
        for (StudentCourses sc: studentcourses) //for each studentcourse add the course to the list
              {
                  courselist.add(sc.getCourse());
        }
        return courselist;
    }
}
