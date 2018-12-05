/*package org.app.service.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Singleton;
import javax.persistence.*;

@Singleton
public class InternshipFactory {
	
	 * Build a new internship with a specific ID, Name, description, start date, end date
	 * and contain a specific number of release
	 
	public Internship buildInternship(Integer internshipID, String internshipName, String internshipDescription,
			Integer releaseCount) {
		Internship releaseInt = new Internship( );
		List<Project> releasesInt = new ArrayList<>();
		
		Date releaseDate = new Date();
		Long interval = (long) (301 * 24 * 60 * 60 * 1000);
		
		for (int i=0; i<=releaseCount-1; i++) {
			releasesInt.add(new Project());
		}
		releaseInt.setInternships(releasesInt);
		return releaseInt;
	}

}
*/