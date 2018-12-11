package org.app.service.ejb.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.logging.Logger;
import javax.ejb.EJB;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.app.service.entities.Benefits;
import org.app.service.entities.EntityBase;
import org.jboss.arquillian.container.test.api.Deployment;
import org.junit.Test;
import org.app.patterns.EntityRepository;
import org.app.service.ejb.BenefitServiceEJB;
import org.app.service.ejb.BenefitsService;
import org.app.service.ejb.DataService;
import org.app.service.entities.Benefits;;

@RunWith(Arquillian.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestBenefitServiceEJBArq {
	private static Logger logger = Logger.getLogger(TestBenefitServiceEJBArq.class.getName());
	@EJB
	private BenefitsService service;
	//Arquillian infrastructure
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}	
/*	
	@Deployment
	public static Archive<?> createDeploymet() {
		return ShrinkWrap
				.create(WebArchive.class, "tam4test.war")
				//.addPackage(Benefits.class.getPackage())
				.addPackage(EntityRepository.class.getPackage())
				.addClass(BenefitsService.class)
				.addClass(BenefitServiceEJB.class)
				.addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
//				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}
*/	
	@Deployment
	public static Archive<?> createDeployment() {
	        return ShrinkWrap
	                .create(WebArchive.class, "msd-test.war")
	                .addPackage(EntityRepository.class.getPackage())
	                .addPackage(BenefitsService.class.getPackage())
	                .addPackage(Benefits.class.getPackage())
	                .addAsResource("META-INF/persistence.xml")
	                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	   }


	@Test
	public void test1_GetMessage() {
		logger.info("DEBUG: Junit TESTING: getMessage ....");
		String response = service.sayRest();
		//String response = BenefitDataService.getMessage();
		assertNotNull("Data Service Failed!!", response);
		logger.info("DEBUG: EJB response ..." + response);
	}
	
	@Test
	public void test4_GetBenefits() {
	logger.info("DEBUG:: JUnit Testing): testGetBenefit ...");
	Collection<Benefits> benefits = service.getBenefits();
	//Collection<Benefits> benefits = BenefitDataService.getBenefits();
	assertTrue("Fail to read benefits!", benefits.size() > 0);
	}
	
	@Test
	public void test3_AddBenefits() {
	logger.info("Debug:: Junit TESTING: testAddBenefit ....");
	
	Integer benefitToAdd = 3;
	for (int i=1; i <= benefitToAdd; i++) { 
		service.addBenefit(new Benefits(i, "Benefit_" + (100+i), "SevenCard"+i, null));
		// BenefitDataService.addBenefits(new Benefits(null, "Benefit_" + (100+i)));
	}
	Collection<Benefits> benefits = service.getBenefits();
	//Collection<Benefits> benefits = BenefitDataService.getBenefits();
	assertTrue("Fail to add benefits!!", benefits.size() == benefitToAdd);
	}
	
	@Test
	public void test2_DeleteBenefit() {
	logger.info("DEBUG:: JUNIT TESTING: testDeleteBenefit");
	Collection<Benefits> benefits = service.getBenefits();
	//Collection<Benefits> benefits = BenefitDataService.getBenefits();
	for (Benefits b: benefits)
		service.removeBenefit(b);
		//BenefitDataService.removeBenefits(b);
		Collection<Benefits> benefitAfterDelete = service.getBenefits();
	//Collection<Benefits> benefitAfterDelete = BenefitDataService.getBenefits();
	assertTrue("Fail to read benefits!!", benefitAfterDelete.size() == 0);
	}
}
