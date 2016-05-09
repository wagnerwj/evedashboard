package com.bwagner.eve;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.bwagner.eve.domain.EveAPI;
import com.bwagner.eve.domain.EvePilot;

import junit.framework.TestCase;

public class TestStorage extends TestCase {

	
	public void testApp() {
		SessionFactory sessionFactory = new Configuration().configure()
				.buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
 
		EveAPI api = new EveAPI();
		
		api.setKey("2312312");
		session.save(api);
 
		session.getTransaction().commit();
		session.close();
	}
}
