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
import org.app.service.entities.Intern;



@Path("intern") // http://localhost:8080/TAM4/rest/intern
//1. Remote interface
@Stateless @LocalBean

public class InternServicesEJB extends EntityRepositoryBase<Intern> implements InternServices {
	private static Logger logger = Logger.getLogger(InternServicesEJB.class.getName());
	
	private EntityRepository<Intern> entityRepository;	
		
		
		@PostConstruct
		public void init(){
		
			logger.info("Initialized :");	
		}
		
		public InternServicesEJB() {
			super();
			logger.info("InternServiceEJB INIT: : " + this.em);		
		}

		
		/*CRUD Operations
		 * Create Or Update*/
		@Override
		@POST
		public Intern addIntern(Intern internToAdd) {
			em.persist(internToAdd);
			em.flush();
			//transaction are managed by default by container
			em.refresh(internToAdd);
					return internToAdd;
		}
		// DELETE
		@Override
		@DELETE
		public String removeIntern(Intern internToDelete) {
			internToDelete = em.merge(internToDelete);
			em.remove(internToDelete);
			em.flush();
			return "TRUE";
		}
		//READ
		@Override
		@GET
		public Intern getInternByID(Integer studentID) {
			return em.find(Intern.class, studentID);
		}
		public Collection<Intern> getIntern(){
			List<Intern> intern = em.createQuery("Select m FROM Intern m", Intern.class)
						.getResultList();
			return intern;
		}

		@Override
		public Intern getInternByName(String internName) {
			return em.createQuery("SELECT m FROM Intern m WHERE m.name = :name", Intern.class)
					.setParameter("name", internName)
					.getSingleResult();
		}

		@Override
		@GET
		public String sayRest() {
			return "Feature Service is On...";
		}
		//Sprint 4 - Rest services - Try 1 - http://localhost:8080/TAM4/rest/intern REST-resource: project-collection
		// Prepare EJB Data Service to generate RESTfull Data Resources
		/**********************Benefit Collection - GET *********************/
		@Override
		@GET
		@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
		public Collection<Intern> toCollection(){
			logger.info("***** DEBUG REST toCollection()");
			return super.toCollection();
		}
		/*********************************************************************/
		/********************************GET By ID****************************/
		//@Override
		@GET @Path("/{id}")			// http://localhost:8080/TAM4/rest/intern/data/{id} - REST-resource: project-entity
		@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
		public Intern getByID(@PathParam("id") Integer id) {
			Intern intern = super.getById(id);
			logger.info("******* DEBUG REST getByID(" + id + ") = " + intern);
			return intern;
		}
		
		/******************************** POST *******************************/
		@POST		//http://localhost:8080/TAM4/rest/benefits 	REST-resource: benefits-collection
		@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
		@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
		public Collection<Intern> addIntoCollection(Intern intern) {
			super.add(intern);
			return super.toCollection();
		}
		
		/******************************** DELETE *****************************/
		@DELETE 	//http://localhost:8080/TAM4/rest/intern 	REST-resource: intern-collection
		@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
		@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
		@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) // autonomos transaction
		public Collection<Intern> removeFromCollection(Intern intern) {
			logger.info("******DEBUG: called REMOVE - Intern: " + intern);
			super.remove(intern);
			return super.toCollection();
		}
		/*********************************************************************/
		@DELETE @Path("/{id}") //http://localhost:8080/TAM4/rest/intern 	REST-resource: intern-collection
		@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
		public void remove(@PathParam("id") Integer id) {
			logger.info("***DEBUG: called REMOVE - ByID() for intern >>>>>>>>>>>>> simplified ! + id");
			Intern intern = super.getById(id);
			super.remove(intern);
		}
		/*********************************************************************/
		public Intern toDTO() {
			Intern internDTO = new Intern();
			return internDTO;
		}
}
