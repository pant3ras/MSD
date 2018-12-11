package org.app.service.entities;

import java.io.Serializable;
import java.lang.String;
import java.sql.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity implementation class for Entity: Intern
 *
 */
@XmlRootElement(name="benefits")
@XmlAccessorType(XmlAccessType.NONE)
@Entity
@Table(name="Intern")

public class Intern implements Serializable {

	@Id @GeneratedValue
	private int studentID;
	
	@Column(name="Stud_Int_Start_date")
	private Date stud_int_start_date;
	
	@Column(name="Stud_Int_End_date")
	private Date stud_int_end_date;
	
	@Column(name="Student_Surname")
	private String studentSurname;
	
	private String studentName;

	//---------------------------------------
	//Relationship between Intern and Benefits.

	@OneToMany(mappedBy="intern")
	private List<Benefits> studName;
	
	@XmlElementWrapper(name = "benefit")
	@XmlElement(name = "benefit")
	public List<Benefits> getStudName() {
		return studName;
	}
	public void setStudName(List<Benefits> studName) {
		this.studName = studName;
	}
//-----------------------	
	//--------------------------------------
	//Relationship between intern and manager
	@ManyToOne
	@JoinColumn(name="students_int", nullable=false)
	private Intern students_int;
	
	//--------------------------------------
	//Relationship between intern and Internship
	@ManyToOne
	@JoinColumn(name="No_Of_Intern", nullable=false)
	private Intern noOfIntern;
	
	//--------------------------------------
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="projectID")
	private Project project;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "internshipID")
	private Internship internship;

	
	private static final long serialVersionUID = 1L;
	
	public Intern() {
		super();
	}   
	
	@XmlElement
	public int getStudentID() {
		return this.studentID;
	}

	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}  
	@XmlElement
	public Date getStud_int_start_date() {
		return this.stud_int_start_date;
	}

	public void setStud_int_start_date(Date stud_int_start_date) {
		this.stud_int_start_date = stud_int_start_date;
	} 
	@XmlElement
	public Date getStud_int_end_date() {
		return this.stud_int_end_date;
	}

	public void setStud_int_end_date(Date stud_int_end_date) {
		this.stud_int_end_date = stud_int_end_date;
	} 
	@XmlElement
	public String getStudentName() {
		return this.studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}   
	@XmlElement
	public String getStudentSurname() {
		return this.studentSurname;
	}

	public void setStudentSurname(String studentSurname) {
		this.studentSurname = studentSurname;
	}
	
	@XmlElementWrapper(name = "internship")
	@XmlElement(name = "internship")
	public Internship getInternship(){
		return internship;
	}
	@XmlElementWrapper(name = "project")
	@XmlElement(name = "project")
	public Project getProject() {
		return project;
	}
	
	/*Rest resurces
	 *Sprint 4 - part 3 
	 */
	 public static String BASE_URL = Benefits.BASE_URL;
	 @XmlElement(name = "link")
	 public AtomLink getLink() throws Exception {
		 String restUrl = BASE_URL 
				 + this.getStudName()
				 + "/intern/"
				 +this.getStudentID();
		 return new AtomLink(restUrl, "get-benefit");
	 }
   
}
