package org.app.service.entities;

import java.io.Serializable;
import java.lang.String;
import java.util.Set;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Manager
 *
 */
@Entity
@Table(name="Manager")

public class Manager implements Serializable {

	   
	@Id @GeneratedValue
	private Integer managerID;
	private String managerName;
	private String managerEmail;
	//-----------------------------------------
	//Relationship between manager and benefits
	@OneToMany(mappedBy="manager")
	private Set<Benefits> mngName;

	public Set<Benefits> getMngNames() {
		return mngName;
	}
	public void setStudName(Set<Benefits> mngName) {
		this.mngName = mngName;
	}
	//--------------------------------------
	@OneToOne private Project project;
	@OneToOne private Internship internship;
	
	//--------------------------------------
	//Relationship between intern and manager
	
	@OneToMany(mappedBy="students_int")
	private Set<Intern> students_int;

	public Set<Intern> getStudents_ints() {
		return students_int;
	}
	public void setStudents_ints(Set<Intern> students_int) {
		this.students_int = students_int;
	}	
	//--------------------------------------
	
	private static final long serialVersionUID = 1L;

	public Manager() {
		super();
	}   
	public int getManagerID() {
		return this.managerID;
	}

	public void setManagerID(int managerID) {
		this.managerID = managerID;
	}   
	public String getManagerName() {
		return this.managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}   
	public String getManagerEmail() {
		return this.managerEmail;
	}

	public void setManagerEmail(String managerEmail) {
		this.managerEmail = managerEmail;
	}
   
}
