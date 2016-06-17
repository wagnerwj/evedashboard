package com.bwagner.eve.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.bwagner.eve.domain.EveCharacter;
import com.bwagner.eve.domain.EvePilot;

public class EveDashboardDao  {

	private Session currentSession;
	
	private Transaction currentTransaction;

	
	private static SessionFactory getSessionFactory() {
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties());
		SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
		return sessionFactory;
	}
	
	public Session openCurrentSession() {
		currentSession = getSessionFactory().openSession();
		return currentSession;
	}

	public Session openCurrentSessionwithTransaction() {
		currentSession = getSessionFactory().openSession();
		currentTransaction = currentSession.beginTransaction();
		return currentSession;
	}
	
	public void closeCurrentSession() {
		currentSession.close();
	}
	
	public void closeCurrentSessionwithTransaction() {
		currentTransaction.commit();
		currentSession.close();
	}
	
	public Session getCurrentSession() {
		if(currentSession == null || !currentSession.isOpen()){
			return openCurrentSessionwithTransaction();
		}
		return currentSession;
	}

	public void setCurrentSession(Session currentSession) {
		this.currentSession = currentSession;
	}

	public Transaction getCurrentTransaction() {
		return currentTransaction;
	}

	public void setCurrentTransaction(Transaction currentTransaction) {
		this.currentTransaction = currentTransaction;
	}
	
	public long count() {
		// TODO Auto-generated method stub
		return getCurrentSession().createQuery("from EvePilot").list().size();
	}

	public void delete(Long id) {
		EvePilot deletingPilot = this.findById(id);
		this.delete(deletingPilot);

	}

	public void delete(EvePilot deletingPilot) {
		getCurrentSession().delete(deletingPilot);
	}

	public void delete(List<? extends EvePilot> entity) {
		// TODO Auto-generated method stub

	}

	public void deleteAll() {
		// TODO Auto-generated method stub

	}

	public boolean exists(Long id) {
		// TODO Auto-generated method stub
		return (getCurrentSession().get(EvePilot.class, id))!=null;
	}

	public List<EvePilot> findAll() {
		// TODO Auto-generated method stub
		return (List<EvePilot>) getCurrentSession().createQuery("from EvePilot").list();
	}

	public Iterable<EvePilot> findAll(Iterable<Long> entity) {
		// TODO Auto-generated method stub
		return null;
	}

	public EvePilot findById(Long id) {
		// TODO Auto-generated method stub
		return (EvePilot) getCurrentSession().get(EvePilot.class, id);
	}
	


	public void save(Object entity) {
		 getCurrentSession().saveOrUpdate(entity);
		 getCurrentSession().flush();
		 closeCurrentSessionwithTransaction();
	}

	public EvePilot findByEmail(String email){
		getCurrentSession();
		DetachedCriteria criteria = DetachedCriteria.forClass(EvePilot.class);
		criteria.add(Restrictions.ilike("emailAddress", email));
		return (EvePilot)criteria.getExecutableCriteria(getCurrentSession()).uniqueResult();
	}
	public <S extends EvePilot> Iterable<S> save(Iterable<S> entity) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public EvePilot findByEveCharacterId(Long characterId){
		EveCharacter eveCharacter = (EveCharacter) getCurrentSession().get(EveCharacter.class, characterId);
		EvePilot pilot = null;
		if(eveCharacter != null){
			pilot = this.findByEveCharacter(eveCharacter);
		}
		return pilot;
	}
	
	public EvePilot findByEveCharacter(EveCharacter character){
		DetachedCriteria criteria = DetachedCriteria.forClass(EvePilot.class,"evePilot");
		criteria.createAlias("evePilot.characters", "character");
		criteria.add(Restrictions.eq("character.id", character.getId()));
		criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		return (EvePilot)criteria.getExecutableCriteria(getCurrentSession()).uniqueResult();
		
	}

}
