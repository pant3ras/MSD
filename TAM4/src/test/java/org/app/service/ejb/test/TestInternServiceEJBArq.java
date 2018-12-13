package org.app.service.ejb.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.logging.Logger;

import javax.ejb.EJB;

import org.app.patterns.EntityRepository;
import org.app.service.ejb.BenefitsService;
import org.app.service.ejb.ManagerService;
import org.app.service.ejb.InternServices;
import org.app.service.entities.Benefits;
import org.app.service.entities.Manager;
import org.app.service.entities.Intern;
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

public class TestInternServiceEJBArq {
	private static Logger logger = Logger.getLogger(TestInternServiceEJBArq.class.getName());
	@EJB
	private InternServices service;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}	

	@Deployment
	public static Archive<?> createDeployment() {
	        return ShrinkWrap
	                .create(WebArchive.class, "msd-test.war")
	                .addPackage(EntityRepository.class.getPackage())
	                .addPackage(InternServices.class.getPackage())
	                .addPackage(Intern.class.getPackage())
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
	public void test2_GetIntern() {
	logger.info("DEBUG:: JUnit Testing): testGetIntern ...");
	Collection<Intern> intern = service.getIntern();

	assertTrue("Fail to read interns!", intern.size() > 0);
	}
	
	@Test
	public void test3_AddIntern() {
	logger.info("Debug:: Junit TESTING: testAddIntern ....");
	
	Integer internToAdd = 21;
	LocalDate date = LocalDate.now();
	String studentSurname = "testSurname";
	String studentName = "testName";
	for (int i=1; i <= internToAdd; i++) { 
		service.addIntern(new Intern(i, date, date, "Student_Surname_" + studentSurname, "Student_Name_" + studentName));
	}
	
	Collection<Intern> intern = service.getIntern();	
	assertTrue("Fail to add interns!!", intern.size() == internToAdd);
	}
	
	@Test
	public void test4_DeleteIntern() {
	logger.info("DEBUG:: JUNIT TESTING: testDeleteIntern");
	Collection<Intern> intern = service.getIntern();
	
	for (Intern m: intern)
		service.removeIntern(m);
		Collection<Intern> internAfterDelete = service.getIntern();
	assertTrue("Fail to read interns!!", internAfterDelete.size() == 0);
	}	
}
