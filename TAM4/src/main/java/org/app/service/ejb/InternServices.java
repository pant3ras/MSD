package org.app.service.ejb;

import java.util.Collection;

import javax.ejb.Remote;

import org.app.patterns.EntityRepository;
import org.app.service.entities.Intern;

@Remote
public interface InternServices extends EntityRepository<Intern> {
	//Create or Update
			Intern addIntern(Intern internToAdd);
			
		//Delete
			String removeIntern(Intern internToDelete);
			
		//READ
			Intern getInternByID(Integer studentID);
			Collection<Intern> getIntern();
			
		//Custom READ: custom query
			Intern getInternByName(String studentName);
			
		//Others
			String sayRest();
}
