package org.app.service.entities;

import java.io.Serializable;
import java.lang.String;
import java.sql.Date;
import java.util.Set;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Project
 *
 */
@Entity

public class Project implements Serializable {

	   
	@Id @GeneratedValue
	@Column(name="Project_ID")
	private int projectID;
	@Column(name="Project_Name")
	private String projectName;
	@Column(name="Project_Description")
	private String projectDescription;

	@Column(name="Project_StartDate")
	private Date project_start_date;
	
	@Column(name="Project_EndDate")
	private Date project_end_date;
	
	//Relationship between Project and Manager.
	@OneToOne
	private Manager manager;
	
	//Relationship between Project and Internship.
	@OneToMany(mappedBy="project_name")
	private Set<Internship> project_name;

	public Set<Internship> getStudName() {
		return project_name;
	}
	public void setStudName(Set<Internship> project_name) {
		this.project_name = project_name;
	}
//-----------------------
	
	
	private static final long serialVersionUID = 1L;

	public Project() {
		super();
	}   
	public int getProjectID() {
		return this.projectID;
	}

	public void setProjectID(int projectID) {
		this.projectID = projectID;
	}   
	public String getProjectName() {
		return this.projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}   
	public String getProjectDescription() {
		return this.projectDescription;
	}

	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}   
	public Date getProject_start_date() {
		return this.project_start_date;
	}

	public void setProject_start_date(Date project_start_date) {
		this.project_start_date = project_start_date;
	}   
	public Date getProject_end_date() {
		return this.project_end_date;
	}

	public void setProject_end_date(Date project_end_date) {
		this.project_end_date = project_end_date;
	}
   
}
