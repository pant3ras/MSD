package org.app.service.entities;

import java.io.Serializable;
import java.lang.String;
import java.sql.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Benefits
 *
 */
@Entity
@Table(name="Benefits")

public class Benefits implements Serializable {

	@Id 
	@Column(name="Benefit_ID")
	private int benefitID;
	@Column(name="Benefit_Name")
	private String benefitName;
	@Column(name="Benefit_Description")
	private String benefitDescription;
	
	// relationship between Benefits and Intern
	@ManyToOne
	@JoinColumn(name="benefit_int", nullable=true)
	private Intern intern;
	
	//relationship between Benefits and Manager
/*	@ManyToOne
	@JoinColumn(name="benefit_mng", nullable=false)
	private Manager manager;
	*/
	private static final long serialVersionUID = 1L;

	public Benefits() {
		super();
	} 
	
	//to be deleted
	public Benefits(int benefitID, String benefitName, String benefitDescription, Intern intern) {
		this.benefitID = benefitID;
		this.benefitName = benefitName;
		this.benefitDescription = benefitDescription;
		this.intern = intern;
	}
	//if the test work
	//Auto generated ...
	public Benefits(Integer id, String string, Date date) {
		// TODO Auto-generated constructor stub
	}

	public int getBenefitID() {
		return this.benefitID;
	}

	public void setBenefitID(int benefitID) {
		this.benefitID = benefitID;
	}   
	public String getBenefitDescription() {
		return this.benefitDescription;
	}

	public void setBenefitDescription(String benefitDescription) {
		this.benefitDescription = benefitDescription;
	}   
	public String getBenefitName() {
		return this.benefitName;
	}

	public void setBenefitName(String benefitName) {
		this.benefitName = benefitName;
	}
   
	public Intern getIntern() {
		return intern;
		}
//	public Intern setIntern() {
//		this.Intern = intern;
//	}
	}

