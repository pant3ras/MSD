package org.app.service.ejb.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.logging.Logger;

import javax.ejb.EJB;

import org.app.patterns.EntityRepository;
import org.app.service.ejb.BenefitsService;
import org.app.service.ejb.ManagerService;
import org.app.service.entities.Benefits;
import org.app.service.entities.Manager;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

@RunWith(Arquillian.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class TestManagerServiceEJBArq {

	private static Logger logger = Logger.getLogger(TestManagerServiceEJBArq.class.getName());
	@EJB
	private ManagerService service;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}	

	@Deployment
	public static Archive<?> createDeployment() {
	        return ShrinkWrap
	                .create(WebArchive.class, "msd-test.war")
	                .addPackage(EntityRepository.class.getPackage())
	                .addPackage(ManagerService.class.getPackage())
	                .addPackage(Manager.class.getPackage())
	                .addAsResource("META-INF/persistence.xml")
	                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	   }


	@Test
	public void test1_GetMessage() {
		logger.info("DEBUG: Junit TESTING: getMessage ....");
		String response = service.sayRest();
		assertNotNull("Data Service Failed!!", response);
		logger.info("DEBUG: EJB response ..." + response);
	}
	
	@Test
	public void test2_GetManager() {
	logger.info("DEBUG:: JUnit Testing): testGetManager ...");
	Collection<Manager> manager = service.getManager();

	assertTrue("Fail to read managers!", manager.size() > 0);
	}
	
	@Test
	public void test3_AddManager() {
	logger.info("Debug:: Junit TESTING: testAddManager ....");
	
	Integer managerToAdd = 21;
	for (int i=1; i <= managerToAdd; i++) { 
		service.addManager(new Manager(i, "Manager_" + (100+i), "Email_" + (100+i)));
	}
	
	Collection<Manager> manager = service.getManager();	
	assertTrue("Fail to add managers!!", manager.size() == managerToAdd);
	}
	
	@Test
	public void test4_DeleteManager() {
	logger.info("DEBUG:: JUNIT TESTING: testDeleteManager");
	Collection<Manager> manager = service.getManager();
	
	for (Manager m: manager)
		service.removeManager(m);
		Collection<Manager> managerAfterDelete = service.getManager();
	assertTrue("Fail to read managers!!", managerAfterDelete.size() == 0);
	}
	
	
}
