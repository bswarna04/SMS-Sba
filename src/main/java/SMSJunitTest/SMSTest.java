package SMSJunitTest;

import jpa.entitymodels.Student;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import jpa.service.StudentService;

public class SMSTest {

    @Test
    public void getStudentByEmailTest() {



        StudentService studentService = new StudentService();
        Student actual = studentService.getStudentByEmail("aiannitti7@is.gd");
        String actualEmail=actual.getsEmail();
        String actualName= actual.getsName();
        String actualPassword=actual.getsPass();

        Assert.assertEquals("Alexandra Iannitti",actualName);
        Assert.assertEquals("aiannitti7@is.gd",actualEmail);
        Assert.assertEquals("TWP4hf5j",actualPassword);
        System.out.println("Expected and actual results matched. Test passed!");


    }
}

