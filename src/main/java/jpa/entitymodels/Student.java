package jpa.entitymodels;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "student")
public class Student {
    @Id
    private String sEmail;
    private String sName;
    private String sPass;

    // One student can have many courses
    @OneToMany(mappedBy = "student")
    private List<StudentCourses> sCourses;

    public Student(String sEmail, String sName, String sPass, List<StudentCourses> sCourses) {
        this.sEmail = sEmail;
        this.sName = sName;
        this.sPass = sPass;
        this.sCourses = sCourses;
    }

    public Student() {
    }

    public String getsEmail() {
        return sEmail;
    }

    public void setsEmail(String sEmail) {
        this.sEmail = sEmail;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsPass() {
        return sPass;
    }

    public void setsPass(String sPass) {
        this.sPass = sPass;
    }

    public List<StudentCourses> getsCourses() {
        return sCourses;
    }

    public void setsCourses(List<StudentCourses> sCourses) {
        this.sCourses = sCourses;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sEmail='" + sEmail + '\'' +
                ", sName='" + sName + '\'' +
                ", sPass='" + sPass + '\'' +
                ", sCourses=" + sCourses +
                '}';
    }
}
