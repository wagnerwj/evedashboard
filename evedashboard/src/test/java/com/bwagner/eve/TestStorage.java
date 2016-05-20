package com.bwagner.eve;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.bwagner.eve.dao.EveDashboardDao;
import com.bwagner.eve.domain.EveAPI;
import com.bwagner.eve.domain.EveCharacter;
import com.bwagner.eve.domain.EvePilot;

import junit.framework.TestCase;

public class TestStorage extends TestCase {

	
	public void testApp() {
//		SessionFactory sessionFactory = new Configuration().configure()
//				.buildSessionFactory();
//		Session session = sessionFactory.openSession();
//		session.beginTransaction();
// 
//		EveAPI api = new EveAPI();
//		
//		api.setKey("2312312");
//		session.save(api);
// 
//		session.getTransaction().commit();
//		session.close();
		
		EveDashboardDao dao = new EveDashboardDao();
//		EveCharacter character1= new EveCharacter();
//		character1.setId(1012l);
//		character1.setName("Wakka Wakka Wakka 3");
		dao.openCurrentSessionwithTransaction();
		//dao.save(character1);
//		EvePilot pilot= new EvePilot();
//		pilot.setEmailAddress("owatagoosiam@siam.com");
//		pilot.setSalt("WKIQDW@!@");
//		pilot.getCharacters().add(character1);
		
		
//		dao.save(pilot);
		
		EvePilot pilot = dao.findByEveCharacterId(1012l);
		System.out.println(pilot.getEmailAddress());
		dao.closeCurrentSessionwithTransaction();
	}
}
