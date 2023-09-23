package vn.edu.iuh.fit.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import vn.edu.iuh.fit.entities.Log;
import vn.edu.iuh.fit.entities.Role;

import java.util.List;

public class LogRespository {
    private EntityManager em;
    public LogRespository(){
        em = DBConnection.getInstance().getEntityManager();
    }
    public boolean addLog(Log log){
        EntityTransaction tr = em.getTransaction();
        tr.begin();
        try {
            em.persist(log);
            tr.commit();
            return true;
        }catch (Exception e){
            tr.rollback();
            e.printStackTrace();
        }
        return false;
    }

    public List<Log> getAll() {
        EntityTransaction tr = em.getTransaction();
        tr.begin();
        try {
            List<Log> resultList = em.createQuery("from Log" , Log.class).getResultList();
            tr.commit();
            return resultList;
        } catch (Exception e){
            tr.rollback();
            e.printStackTrace();
        }
        return null;
    }
}
