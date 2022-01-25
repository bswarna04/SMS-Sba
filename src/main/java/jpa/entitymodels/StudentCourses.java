
package jpa.entitymodels;

import javax.persistence.*;

@Entity
@Table( name="courses") // join table which holds email and courseid
@IdClass( StudentCoursesID.class) // Creates connecction to the composite key class
@NamedQueries({
	@NamedQuery( name="CoursesByStudent", query="Select c from StudentCourses c where c.eMail = :email") // retrieves courses based on email passed fro courses table
     })
     public class StudentCourses {
	@Id
	@Column(name="student_email")
	private String eMail;

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	//Joins student and courses table based on student email
	@ManyToOne
	@JoinColumn(name = "student_email", insertable = false, updatable = false)
	Student student;
	
	public StudentCourses() {}

	public StudentCourses(String eMail, int courseID) {
		this.eMail = eMail;
		this.courseID = courseID;
	}

	@Id
	@Column(name="course_id")
	private int courseID;

	//Joins course and courses(student-course) table based on courseId
	@ManyToOne
	@JoinColumn(name = "course_id", insertable = false, updatable = false)
	Course course;

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}


	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}


	public int getCourseID() {
		return courseID;
	}


	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + courseID;
		result = prime * result + ((eMail == null) ? 0 : eMail.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentCourses other = (StudentCourses) obj;
		if (courseID != other.courseID)
			return false;
		if (eMail == null) {
			if (other.eMail != null)
				return false;
		} else if (!eMail.equals(other.eMail))
			return false;
		return true;
	}

}
