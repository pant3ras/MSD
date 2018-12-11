package org.app.service.entities;

import java.io.Serializable;
import java.lang.String;
import java.sql.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Internship
 *
 */
@Entity
@Table(name="Internship")
public class Internship implements Serializable {

	   
	@Id @GeneratedValue
	@Column(name="Int_ID")
	private int internshipID;
	@Column(name="Internship_name")
	private String internshipName;
	@Column(name="Internship_Description")
	private String internshipDescription;
	@Column(name="Int_Start_date")
	private Date int_start_date;
	@Column(name="Int_End_Date")
	private Date int_end_date;;
	
	
	//Relationship between Internship and Manager.

	@OneToOne private Manager manager;
	
	//----------------------------------
	//Relationship between Internships and Intern

	@OneToMany(mappedBy="noOfIntern")
	private Set<Intern> noOfIntern;

	public Set<Intern> getNoOfInterns() {
		return noOfIntern;
	}
	public void setNoOfInterns(Set<Intern> noOfIntern) {
		this.noOfIntern = noOfIntern;
	}
	
	//------------------------------
	//Relationship between Internships and Project
	@ManyToOne
	@JoinColumn(name="project_name", nullable=false)
	private Project project_name;
	//------------------------------
	
	
	private static final long serialVersionUID = 1L;
/* auto-generated.
	public Internship(Object object, String string, Date date, Benefits benefit) {
		super();
	}   
*/
	public int getInternshipID() {
		return this.internshipID;
	}

	public void setInternshipID(int internshipID) {
		this.internshipID = internshipID;
	}   
	public String getInternshipName() {
		return this.internshipName;
	}

	public void setInternshipName(String internshipName) {
		this.internshipName = internshipName;
	}   
	public String getInternshipDescription() {
		return this.internshipDescription;
	}

	public void setInternshipDescription(String internshipDescription) {
		this.internshipDescription = internshipDescription;
	}   
	public Date getInt_start_date() {
		return this.int_start_date;
	}

	public void setInt_start_date(Date int_start_date) {
		this.int_start_date = int_start_date;
	}   
	public Date getInt_end_date() {
		return this.int_end_date;
	}

	public void setInt_end_date(Date int_end_date) {
		this.int_end_date = int_end_date;
	}
	public void setInternships(List<Project> releasesInt) {
		// TODO Auto-generated method stub
		
	}   
   
}
