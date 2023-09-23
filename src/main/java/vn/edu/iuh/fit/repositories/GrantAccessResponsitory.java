package vn.edu.iuh.fit.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import vn.edu.iuh.fit.entities.Account;
import vn.edu.iuh.fit.entities.GrantAccess;

import java.util.Optional;

public class GrantAccessResponsitory {

    private EntityManager em;
    public GrantAccessResponsitory(){
        em = DBConnection.getInstance().getEntityManager();
    }

    public Optional<GrantAccess> findGrantAccessByAccountId(String accountId) {
        EntityTransaction tr = em.getTransaction();
        tr.begin();
        try {
            String sql = "SELECT * FROM grant_access WHERE account_id = ? ";
            GrantAccess grantAccess = (GrantAccess) em.createNativeQuery(sql, GrantAccess.class).setParameter(1, accountId).getSingleResult();
            tr.commit();
            return Optional.of(grantAccess);
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
        }
        return Optional.empty();
    }
//    public Optional<GrantAccess> findGrantAccessByAccountId(String accountId){
//        EntityTransaction tr = em.getTransaction();
//        tr.begin();
//        try{
//            GrantAccess grantAccess = em.find(GrantAccess.class, accountId);
//            tr.commit();
//            return Optional.of(grantAccess);
//        }catch(Exception e){
//            tr.rollback();
//            e.printStackTrace();
//        }
//        return Optional.empty();
//    }
}
