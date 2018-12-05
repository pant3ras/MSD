package org.app.service.ejb;

import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;

import org.app.patterns.EntityRepository;
import org.app.patterns.EntityRepositoryBase;
import org.app.service.entities.Benefits;
import org.app.service.entities.EntityBase;
import org.jboss.resteasy.logging.Logger;



@Stateless @LocalBean
public class BenefitServiceEJB extends EntityRepositoryBase<Benefits> implements BenefitsService { //implements BenefitsService {
	private static Logger logger = Logger.getLogger(null);
	
	/*Data service initialization
	 * Inject Resource*/
//	@PersistenceContext(unitName="TAM4")
//	private EntityManager em;
	
    // 3. Init with injected EntityManager
	private EntityRepository<Benefits> entityRepository;	
	
	//Constructor
//	public BenefitServiceEJB() {
	//}
	// Initialization after Constructor
	
	@PostConstruct
	public void init(){
		logger.info("POSTCONSTRUCT-INIT : " + this.em);
	}
	
	public BenefitServiceEJB() {
		super();
		logger.info("BenefitServiceEJB INIT: : " + this.em);		
	}

	
	/*CRUD Operations
	 * Create Or Updae*/
	@Override
	public Benefits addBenefit(Benefits benefitToAdd) {
		em.persist(benefitToAdd);
		em.flush();
		//transaction are managed by default by container
		em.refresh(benefitToAdd);
				return benefitToAdd;
	}
	// DELETE
	@Override
	public String removeBenefit(Benefits benefitToDelete) {
		benefitToDelete = em.merge(benefitToDelete);
		em.remove(benefitToDelete);
		em.flush();
		return "TRUE";
	}
	//READ
	@Override
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
	public String sayRest() {
		return "Feature Service is On...";
	}

	@Override
	public String getMessage() {
		return "Merge chestia asta?!!";
	}
	
}
