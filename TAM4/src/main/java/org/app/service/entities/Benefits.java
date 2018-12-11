package org.app.service.entities;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity implementation class for Entity: Benefits
 */
@XmlRootElement(name="benefits")
@XmlAccessorType(XmlAccessType.NONE)
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
	@XmlElement
	public int getBenefitID() {
		return this.benefitID;
	}

	public void setBenefitID(int benefitID) {
		this.benefitID = benefitID;
	}   
	@XmlElement
	public String getBenefitDescription() {
		return this.benefitDescription;
	}

	public void setBenefitDescription(String benefitDescription) {
		this.benefitDescription = benefitDescription;
	}   
	@XmlElement
	public String getBenefitName() {
		return this.benefitName;
	}

	public void setBenefitName(String benefitName) {
		this.benefitName = benefitName;
	}
	@XmlElementWrapper(name = "intern")
	@XmlElement(name = "intern")
	public Intern getIntern() {
		return intern;
		}
//	public Intern setIntern() {
//		this.Intern = intern;
//	}
	
	/*Rest resurces
	 *Sprint 4 - part 3 
	 */
	 public static String BASE_URL = "http://localhost:8080/TAM4/rest/benefits";
	 @XmlElement(name = "link")
	 public AtomLink getLink() throws Exception {
		 String restUrl = BASE_URL + this.getBenefitID();
		 return new AtomLink(restUrl, "get-benefit");
	 }
	 
	 public void setLink(AtomLink link){}
	
	}

