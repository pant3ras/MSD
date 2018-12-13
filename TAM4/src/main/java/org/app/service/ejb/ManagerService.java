package org.app.service.ejb;

import java.util.Collection;

import javax.ejb.Remote;

import org.app.patterns.EntityRepository;
import org.app.service.entities.Manager;

@Remote

public interface ManagerService extends EntityRepository<Manager>{
	//Create or Update
	
		Manager addManager(Manager managerToAdd);
		
	//Delete
		String removeManager(Manager managerToDelete);
		
	//READ
		Manager getManagerByID(Integer managerID);
		Collection<Manager> getManager();
		
	//Custom READ: custom query
		Manager getManagerByName(String managerName);
		
	//Others
		String sayRest();
}
