package org.app.service.ejb;

import java.util.Collection;

import javax.ejb.Remote;

import org.app.patterns.EntityRepository;
import org.app.service.entities.Benefits;

//Implement Simple CRUD

@Remote
public interface BenefitsService extends EntityRepository<Benefits> {
//Create or Update
	
	Benefits addBenefit(Benefits benefitToAdd);
	
//Delete
	String removeBenefit(Benefits benefitToDelete);
	
//READ
	Benefits getBenefitByID(Integer benefitID);
	Collection<Benefits> getBenefits();
	
//Custom READ: custom query
	Benefits getBenefitByName(String benefitName);
	
//Others
	String sayRest();
}
