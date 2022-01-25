
package jpa.mainrunner;

import static java.lang.System.out;
import java.util.List;
import java.util.Scanner;
import jpa.entitymodels.Course;
import jpa.entitymodels.Student;
import jpa.service.CourseService;
import jpa.service.StudentCourseService;
import jpa.service.StudentService;


public class SMSRunner {

	private Scanner sin;
	private StringBuilder sb;

	private CourseService courseService;
	private StudentService studentService;
	private Student currentStudent;

	public SMSRunner() {
		sin = new Scanner(System.in);
		sb = new StringBuilder();
		courseService = new CourseService();
		studentService = new StudentService();
	}


	public static void main(String[] args) {

		SMSRunner sms = new SMSRunner();
		sms.run();
	}

	private void run() {
		// Login or quit
		switch (menu1()) {
		case 1:
			if (studentLogin()) {
				registerMenu();
			}
			break;
		case 2:
			out.println("Goodbye!");
			break;

		default:

		}
	}

	private int menu1() {
		sb.append("\n1.Student Login\n2. Quit Application\nPlease Enter Selection: ");
		out.print(sb.toString());
		sb.delete(0, sb.length());

		return sin.nextInt();
	}

	private boolean studentLogin() {
		boolean retValue = false;
		out.print("Enter your email address: ");
		String email = sin.next();
		out.print("Enter your password: ");
		String password = sin.next();

		Student students = studentService.getStudentByEmail(email);
		if (students != null) {
			currentStudent = students;
		}

		if (currentStudent != null & currentStudent.getsPass().equals(password)) {
			List<Course> courses = studentService.getStudentCourses(email);
			out.println("My Classes:");
			out.println("-----------------");
			out.println("#\tCOURSE NAME\t\t NAME");
			for (Course course : courses) {
				out.println(course);
			}
			retValue = true;
		} else {
			out.println("User Validation failed. GoodBye!");
		}
		return retValue;
	}

	private void registerMenu() {
		sb.append("\n1.Register a class\n2. Logout\nPlease Enter Selection: ");
		out.print(sb.toString());
		sb.delete(0, sb.length());
		boolean registered = false;

		switch (sin.nextInt()) {
		case 1:
			List<Course> allCourses = courseService.getAllCourses();

			List<Course> studentCourses = studentService.getStudentCourses(currentStudent.getsEmail());

			for (Course course : studentCourses) {
				allCourses.remove(course);
			}
			out.printf("%5s%15S%15s\n", "ID", "Course", "Instructor");
			out.println("-----------------------------------------------");
			for (Course course : allCourses) {
				out.println(course);
			}
			out.println();
			out.print("Enter Course Number: ");
			int number = sin.nextInt();
			Course newCourse = courseService.getCourseById(number);
			for (Course course : studentCourses) {
				if (course.getCId() == number) {
					out.println("You are already registered in that course!");
					registered = true;
					break;
				}
			}
			if (newCourse != null && registered == false) {
				studentService.registerStudentToCourse(currentStudent.getsEmail(), newCourse.getCId());
				Student temp = studentService.getStudentByEmail(currentStudent.getsEmail());
				
				StudentCourseService scService = new StudentCourseService();
				List<Course> sCourses = scService.getAllStudentCourses(temp.getsEmail());
				

				out.println("My Classes:");
				out.println("--------------------");
				for (Course course : sCourses) {
					out.println(course);
				}
			}
			out.println("You have been signed out.");
			break;
		case 2:
		default:
			out.println("Goodbye!");
		}
	}
}
