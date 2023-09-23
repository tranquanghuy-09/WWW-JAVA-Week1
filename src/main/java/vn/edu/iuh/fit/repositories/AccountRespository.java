package vn.edu.iuh.fit.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import vn.edu.iuh.fit.entities.Account;

import java.util.List;
import java.util.Optional;

public class AccountRespository {
    private EntityManager em;
    public AccountRespository(){
        em = DBConnection.getInstance().getEntityManager();
    }
    public boolean addAccount(Account account){
        EntityTransaction tr = em.getTransaction();
        tr.begin();
        try {
            em.persist(account);
            tr.commit();
            return true;
        }catch (Exception e){
            tr.rollback();
            e.printStackTrace();
        }
        return false;
    }

    public Optional<Account> findAccount(String id){
        EntityTransaction tr = em.getTransaction();
        tr.begin();
        try{
            Account account = em.find(Account.class, id);
            tr.commit();
            return Optional.of(account);
        }catch(Exception e){
            tr.rollback();
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<Account> updateAccount(Account accountUpdate){
        EntityTransaction tr = em.getTransaction();
        tr.begin();
        try {
            Account account = em.find(Account.class, accountUpdate.getId());
            if (account != null) {
                em.merge(accountUpdate);

                tr.commit();
                return Optional.of(account);
            } else {
                tr.rollback();
                return Optional.empty();
            }
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public Optional<Boolean> deleteAccountById(String accountId) {
        EntityTransaction tr = em.getTransaction();
        tr.begin();
        try {
            Account account = em.find(Account.class, accountId);
            if (account != null) {
                em.remove(account);
                tr.commit();
                return Optional.of(true);
            } else {
                tr.rollback();
                return Optional.empty();
            }
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
            return Optional.of(false);
        }
    }

    public List<Account> getAll() {
        EntityTransaction tr = em.getTransaction();
        tr.begin();
        try {
//            String sql = "SELECT * FROM ACCOUNT";
            List<Account> resultList = em.createQuery("from Account" , Account.class).getResultList();
            tr.commit();
            return resultList;
        } catch (Exception e){
            tr.rollback();
            e.printStackTrace();
        }
        return null;
    }

    public Optional<Account> checkLogin(String username, String password) {
        EntityTransaction tr = em.getTransaction();
        tr.begin();
        try {
            String sql = "SELECT * FROM account WHERE account_id = ? AND PASSWORD = ? AND `status` = 1";
            Account account = (Account) em.createNativeQuery(sql, Account.class).setParameter(1, username).setParameter(2, password).getSingleResult();
            tr.commit();
            return Optional.of(account);
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
