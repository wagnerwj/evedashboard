package com.bwagner.eve.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bwagner.eve.domain.EvePilot;

@Repository
public interface EvePilotDao extends CrudRepository<EvePilot, Long> {

	
	public long count();

	public void delete(Long arg0);

	public void delete(EvePilot arg0);

	public void delete(Iterable<? extends EvePilot> arg0) ;
	public void deleteAll() ;

	public boolean exists(Long arg0) ;

	public Iterable<EvePilot> findAll() ;

	public Iterable<EvePilot> findAll(Iterable<Long> arg0) ;

	public EvePilot findOne(Long arg0) ;

	public <S extends EvePilot> S save(S arg0) ;

	public <S extends EvePilot> Iterable<S> save(Iterable<S> arg0) ;
}
