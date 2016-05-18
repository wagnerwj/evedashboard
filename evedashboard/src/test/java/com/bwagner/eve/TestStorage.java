package com.bwagner.eve;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.bwagner.eve.dao.EvePilotDao;
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
		
		EvePilotDao dao = new EvePilotDao();
		EveCharacter character1= new EveCharacter();
		character1.setId(1002l);
		character1.setName("Wakka Wakka Wakka 2");
		dao.openCurrentSessionwithTransaction();
		EvePilot pilot= dao.findByEmail("owatagoosiam@siam.com");
		pilot.getCharacters().add(character1);
		
		
		dao.save(pilot);
		dao.closeCurrentSessionwithTransaction();
	}
}
