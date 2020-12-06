package com.innovationchef.repository;

import com.innovationchef.model.GameEntity;
import com.innovationchef.model.ProgressEntity;

import java.util.Iterator;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DatabaseAccessObject {

    SessionFactory sf;

    @Autowired
    DatabaseAccessObject(SessionFactory sf) {
        this.sf = sf;
    }

    public void run() {
        System.out.println("Executing");
        this.sf.getCurrentSession().createNativeQuery("select 1").getSingleResult();
    }

    public void startGameInsert(GameEntity gameEntity) {
        this.sf.getCurrentSession().persist(gameEntity);
    }
    
    public void saveProgressObject(ProgressEntity progressEntity) {
        this.sf.getCurrentSession().persist(progressEntity);
    }
//    public List<ProgressEntity> selectProgressObject(ProgressEntity progressEntity) {
//    	Session session =this.sf.getCurrentSession(); 
//    	CriteriaBuilder cb = session.getCriteriaBuilder(); 
//    	CriteriaQuery<GameEntity> cq = cb.createQuery(GameEntity.class); 
//    	Root<GameEntity> from = cq.from(GameEntity.class); 
//    	cq.select(from).where(cb.and(cb.equal(from.get("player1"),firstId),cb.equal(from.get("status"),"open"),cb.equal(from.get("player2"),secondId))); 
//    	TypedQuery<GameEntity> query = session.createQuery(cq); 
//    	List<GameEntity> resultList=query.getResultList(); 
//    	Iterator<GameEntity> itr= resultList.iterator(); 
//    	return resultList;
//    }
    public void startGameUpdate(GameEntity gameEntity) {
    	this.sf.getCurrentSession().merge(gameEntity);
    }
    public void updateGame(GameEntity gameEntity,long id) {
    	gameEntity=this.sf.getCurrentSession().get(GameEntity.class, id);
    	gameEntity.setStatus("closed");
    	this.sf.getCurrentSession().update(gameEntity);
    }
    public void updateProgress(ProgressEntity progressEntity) {
    	this.sf.getCurrentSession().update(progressEntity);
    }
    public List<GameEntity> startGameSelect(GameEntity gameEntity,long Id) {
    	Session session =this.sf.getCurrentSession(); 
    	CriteriaBuilder cb = session.getCriteriaBuilder(); 
    	CriteriaQuery<GameEntity> cq = cb.createQuery(GameEntity.class); 
    	Root<GameEntity> from = cq.from(GameEntity.class); 
    	cq.select(from).where(cb.and(cb.equal(from.get("gameId"),Id),cb.equal(from.get("status"),"open"))); 
    	TypedQuery<GameEntity> query = session.createQuery(cq); 
    	List<GameEntity> resultList=query.getResultList(); 
    	Iterator<GameEntity> itr= resultList.iterator(); 
    	return resultList;
    }
    public List<GameEntity> startGameSelectOnClosed(GameEntity gameEntity,long Id) {
    	Session session =this.sf.getCurrentSession(); 
    	CriteriaBuilder cb = session.getCriteriaBuilder(); 
    	CriteriaQuery<GameEntity> cq = cb.createQuery(GameEntity.class); 
    	Root<GameEntity> from = cq.from(GameEntity.class); 
    	cq.select(from).where(cb.and(cb.equal(from.get("gameId"),Id),cb.equal(from.get("status"),"closed"))); 
    	TypedQuery<GameEntity> query = session.createQuery(cq); 
    	List<GameEntity> resultList=query.getResultList(); 
    	Iterator<GameEntity> itr= resultList.iterator(); 
    	return resultList;}
    public List<GameEntity>showMatrixSelect(GameEntity gameEntity,long gameEntity_Id) {
    	Session session =this.sf.getCurrentSession(); 
    	CriteriaBuilder cb = session.getCriteriaBuilder(); 
    	CriteriaQuery<GameEntity> cq = cb.createQuery(GameEntity.class); 
    	Root<GameEntity> from = cq.from(GameEntity.class); 
    	cq.select(from).where(cb.and(cb.equal(from.get("gameId"),gameEntity_Id),cb.equal(from.get("status"),"open"))); 
    	TypedQuery<GameEntity> query = session.createQuery(cq); 
    	List<GameEntity> resultList=query.getResultList(); 
    	Iterator<GameEntity> itr= resultList.iterator(); 
    	return resultList;
    }
    public List<ProgressEntity>selectProgress(ProgressEntity progressEntity,int Id) {
    	Session session =this.sf.getCurrentSession(); 
    	CriteriaBuilder cb = session.getCriteriaBuilder(); 
    	CriteriaQuery<ProgressEntity> cq = cb.createQuery(ProgressEntity.class); 
    	Root<ProgressEntity> from = cq.from(ProgressEntity.class); 
    	cq.select(from).where(cb.and(cb.equal(from.get("matchId"),Id),cb.equal(from.get("timeS"),"second"))); 
    	TypedQuery<ProgressEntity> query = session.createQuery(cq); 
    	List<ProgressEntity> resultList=query.getResultList(); 
    	Iterator<ProgressEntity> itr= resultList.iterator(); 
    	return resultList;
    }
}

//Session session =this.sf.getCurrentSession(); CriteriaBuilder cb = session.getCriteriaBuilder(); CriteriaQuery<MatchLog> cq = cb.createQuery(MatchLog.class); Root<MatchLog> from = cq.from(MatchLog.class); cq.select(from).where(cb.and(cb.equal(from.get("matchid"),"40"),cb.equal(from.get("color"),"yellow"))); TypedQuery<MatchLog> query = session.createQuery(cq); List<MatchLog> resultList=query.getResultList(); Iterator<MatchLog> itr= resultList.iterator(); while(itr.hasNext()){ int i=itr.next().getCol_position(); System.out.println(i); }

