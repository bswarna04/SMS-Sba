package jpa.entitymodels;

import javax.persistence.*;
import java.util.Objects;

import static java.lang.System.out;

@Entity
@Table(name ="course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cId;
    private String cName;
    private String cInstructor;

    public Course(int cId, String cName, String cInstructor) {
        this.cId = cId;
        this.cName = cName;
        this.cInstructor = cInstructor;
    }

    public Course() {
    }

    public int getCId() {
        return cId;
    }

    public void setCId(int cld) {
        this.cId = cId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcInstructor() {
        return cInstructor;
    }

    public void setcInstructor(String cInstructor) {
        this.cInstructor = cInstructor;
    }

    @Override
    public String toString() {
       return cId+"\t"+cName+"\t\t"+cInstructor;


    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return cId == course.cId && Objects.equals(cName, course.cName) && Objects.equals(cInstructor, course.cInstructor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cId, cName, cInstructor);
    }
}
