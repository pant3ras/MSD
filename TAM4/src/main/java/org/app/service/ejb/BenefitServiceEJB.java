package org.app.service.ejb;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
//import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.persistence.EntityManager;

import org.app.patterns.EntityRepository;
import org.app.patterns.EntityRepositoryBase;
import org.app.service.entities.Benefits;
import org.app.service.entities.Intern;
import org.app.service.entities.Internship;

//import org.jboss.resteasy.logging.Logger;
//import java.util.logging.Logger.getLogger;
import java.util.logging.Logger;

@Path("benefits") // http://localhost:8080/TAM4/rest/benefits
// 1. Remote interface
@Stateless @LocalBean
public class BenefitServiceEJB extends EntityRepositoryBase<Benefits> implements BenefitsService{ 
	private static Logger logger = Logger.getLogger(BenefitServiceEJB.class.getName());
	/* 2.Data service initialization
	 * Inject Resource 
	 * from sprint 3 */ 
//	@PersistenceContext(unitName="MSD")
//	private EntityManager em;
	
    // 3. Init with injected EntityManager 
	// Solution within the project
	private EntityRepository<Benefits> entityRepository;	
	
	/*Constructor
public BenefitServiceEJB() {
	}*/
	// Initialization after Constructor
	
	@PostConstruct
	public void init(){
	//	logger.info("POSTCONSTRUCT-INIT : " + this.em);
		logger.info("Initialized :");	
	}
	
	public BenefitServiceEJB() {
		super();
		logger.info("BenefitServiceEJB INIT: : " + this.em);		
	}

	
	/*CRUD Operations
	 * Create Or Updae*/
	@Override
	@POST
	public Benefits addBenefit(Benefits benefitToAdd) {
		em.persist(benefitToAdd);
		em.flush();
		//transaction are managed by default by container
		em.refresh(benefitToAdd);
				return benefitToAdd;
	}
	// DELETE
	@Override
	@DELETE
	public String removeBenefit(Benefits benefitToDelete) {
		benefitToDelete = em.merge(benefitToDelete);
		em.remove(benefitToDelete);
		em.flush();
		return "TRUE";
	}
	//READ
	@Override
	@GET
	public Benefits getBenefitByID(Integer benefitID) {
		return em.find(Benefits.class, benefitID);
	}
	public Collection<Benefits> getBenefits(){
		List<Benefits> benefits = em.createQuery("Select b FROM Benefits b", Benefits.class)
					.getResultList();
		return benefits;
	}

	@Override
	public Benefits getBenefitByName(String benefitName) {
		return em.createQuery("SELECT b FROM Benefits b WHERE b.name = :name", Benefits.class)
				.setParameter("name", benefitName)
				.getSingleResult();
	}

	@Override
	@GET
	public String sayRest() {
		return "Feature Service is On...";
	}
	//Sprint 4 - Rest services - Try 1 - http://localhost:8080/TAM4/rest/benefits REST-resource: project-collection
	// Prepare EJB Data Service to generate RESTfull Data Resources
	/**********************Benefit Collection - GET *********************/
	@Override
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Collection<Benefits> toCollection(){
		logger.info("***** DEBUG REST toCollection()");
		return super.toCollection();
	}
	/*********************************************************************/
	/********************************GET By ID****************************/
	//@Override
	@GET @Path("/{id}")			// http://localhost:8080/TAM4/rest/benefits/data/{id} - REST-resource: project-entity
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Benefits getByID(@PathParam("id") Integer id) {
		Benefits benefit = super.getById(id);
		logger.info("******* DEBUG REST getByID(" + id + ") = " + benefit);
		return benefit;
	}
	/*****************************@author PanTeraS************************/
	/******************************** POST *******************************/
	@POST		//http://localhost:8080/TAM4/rest/benefits 	REST-resource: benefits-collection
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Collection<Benefits> addIntoCollection(Benefits benefit) {
		super.add(benefit);
		return super.toCollection();
	}
	/*********************************************************************/
	/******************************** DELETE *****************************/
	@DELETE 	//http://localhost:8080/TAM4/rest/benefits 	REST-resource: benefits-collection
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) // autonomos transaction
	public Collection<Benefits> removeFromCollection(Benefits benefit) {
		logger.info("******DEBUG: called REMOVE - Benefits: " + benefit);
		super.remove(benefit);
		return super.toCollection();
	}
	/*********************************************************************/
	@DELETE @Path("/{id}") //http://localhost:8080/TAM4/rest/benefits 	REST-resource: benefits-collection
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void remove(@PathParam("id") Integer id) {
		logger.info("***DEBUG: called REMOVE - ByID() for benefit >>>>>>>>>>>>> simplified ! + id");
		Benefits benefit = super.getById(id);
		super.remove(benefit);
	}
	/*********************************************************************/
	public Benefits toDTO() {
		Benefits benefitDTO = new Benefits();
		return benefitDTO;
	}
	
	
	/****************************** PUT **********************************/
/*	@PUT @Path("/{id}") 	//TAM4/data/benefits 	REST-resource: benefits-collection
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Override
	public Benefits add(Benefits benefit) {
		benefit = super.add(benefit);
		//return Benefits.toDTOAggregate(benefit);
		return Benefits.class.toDTOAggregate(benefit);
		}*/

/*	@POST @Path("/new/{id}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	
	public Benefits createNewBenefit(@PathParam("id") Integer id) {
		
		Benefits benefit = new Benefits(id, "NEW Benefit" + "." + id, new Date());
		List<Intern> releaseBenefit = new ArrayList<>();
		Date publishDate = new Date();
		Long interval = (long) (301 * 24 * 60 * 60 * 1000);
		Integer releaseCount = 3;
		for (int i=0; i<=releaseCount-1; i++) {
			releaseBenefit.add(new Internship(null, "R: " + benefit.getBenefitID()+"."+ i,
					new Date(publishDate.getTime() + i * interval), benefit));
		}
		benefit.setBenefits(releaseBenefit);
		this.add(benefit);
		return benefit;
		}
	}*/
	
	/*	@Override
	public String getMessage() {
		return "Merge chestia asta?!!";
	}
*/	
}
