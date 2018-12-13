package org.app.service.entities;

import java.io.Serializable;
import java.lang.String;
import java.util.Set;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity implementation class for Entity: Manager
 *
 */

@XmlRootElement(name="manager")
@XmlAccessorType(XmlAccessType.NONE)
@Entity
@Table(name="Manager")

public class Manager implements Serializable {

	   
	@Id @GeneratedValue
	@Column(name="Manager_ID")
	private Integer managerID;
	
	@Column(name="Manager_Name")
	private String managerName;
	
	@Column(name="Manager_Email")
	private String managerEmail;

	
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
	
	public Manager(int managerID, String managerName, String managerEmail) {
		this.managerID = managerID;
		this.managerName = managerName;
		this.managerEmail = managerEmail;
		
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
	
	 public static String BASE_URL = "http://localhost:8080/TAM4/rest/manager";
	 @XmlElement(name = "link")
	 public AtomLink getLink() throws Exception {
		 String restUrl = BASE_URL + this.getManagerID();
		 return new AtomLink(restUrl, "get-manager");
	 }
	 
	 public void setLink(AtomLink link){}
   
}
