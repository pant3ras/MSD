package org.app.service.ejb;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.app.patterns.EntityRepository;
import org.app.patterns.EntityRepositoryBase;
import org.app.service.entities.Manager;

@Path("manager") // http://localhost:8080/TAM4/rest/manager
//1. Remote interface
@Stateless @LocalBean
public class ManagerServiceEJB extends EntityRepositoryBase<Manager> implements ManagerService{
	private static Logger logger = Logger.getLogger(ManagerServiceEJB.class.getName());
	
private EntityRepository<Manager> entityRepository;	
	
	
	@PostConstruct
	public void init(){
	
		logger.info("Initialized :");	
	}
	
	public ManagerServiceEJB() {
		super();
		logger.info("ManagerServiceEJB INIT: : " + this.em);		
	}

	
	/*CRUD Operations
	 * Create Or Update*/
	@Override
	@POST
	public Manager addManager(Manager managerToAdd) {
		em.persist(managerToAdd);
		em.flush();
		//transaction are managed by default by container
		em.refresh(managerToAdd);
				return managerToAdd;
	}
	// DELETE
	@Override
	@DELETE
	public String removeManager(Manager managerToDelete) {
		managerToDelete = em.merge(managerToDelete);
		em.remove(managerToDelete);
		em.flush();
		return "TRUE";
	}
	//READ
	@Override
	@GET
	public Manager getManagerByID(Integer managerID) {
		return em.find(Manager.class, managerID);
	}
	public Collection<Manager> getManager(){
		List<Manager> manager = em.createQuery("Select m FROM Manager m", Manager.class)
					.getResultList();
		return manager;
	}

	@Override
	public Manager getManagerByName(String managerName) {
		return em.createQuery("SELECT m FROM Manager m WHERE m.name = :name", Manager.class)
				.setParameter("name", managerName)
				.getSingleResult();
	}

	@Override
	@GET
	public String sayRest() {
		return "Feature Service is On...";
	}
	//Sprint 4 - Rest services - Try 1 - http://localhost:8080/TAM4/rest/manager REST-resource: project-collection
	// Prepare EJB Data Service to generate RESTfull Data Resources
	/**********************Benefit Collection - GET *********************/
	@Override
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Collection<Manager> toCollection(){
		logger.info("***** DEBUG REST toCollection()");
		return super.toCollection();
	}
	/*********************************************************************/
	/********************************GET By ID****************************/
	//@Override
	@GET @Path("/{id}")			// http://localhost:8080/TAM4/rest/manager/data/{id} - REST-resource: project-entity
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Manager getByID(@PathParam("id") Integer id) {
		Manager manager = super.getById(id);
		logger.info("******* DEBUG REST getByID(" + id + ") = " + manager);
		return manager;
	}
	
	/******************************** POST *******************************/
	@POST		//http://localhost:8080/TAM4/rest/benefits 	REST-resource: benefits-collection
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Collection<Manager> addIntoCollection(Manager manager) {
		super.add(manager);
		return super.toCollection();
	}
	
	/******************************** DELETE *****************************/
	@DELETE 	//http://localhost:8080/TAM4/rest/manager 	REST-resource: manager-collection
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) // autonomos transaction
	public Collection<Manager> removeFromCollection(Manager manager) {
		logger.info("******DEBUG: called REMOVE - Manager: " + manager);
		super.remove(manager);
		return super.toCollection();
	}
	/*********************************************************************/
	@DELETE @Path("/{id}") //http://localhost:8080/TAM4/rest/manager 	REST-resource: manager-collection
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void remove(@PathParam("id") Integer id) {
		logger.info("***DEBUG: called REMOVE - ByID() for manager >>>>>>>>>>>>> simplified ! + id");
		Manager manager = super.getById(id);
		super.remove(manager);
	}
	/*********************************************************************/
	public Manager toDTO() {
		Manager managerDTO = new Manager();
		return managerDTO;
	}
	
	
	
}
