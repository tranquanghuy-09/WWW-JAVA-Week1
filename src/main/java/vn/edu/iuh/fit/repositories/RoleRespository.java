package vn.edu.iuh.fit.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import vn.edu.iuh.fit.entities.Account;
import vn.edu.iuh.fit.entities.Role;

import java.util.List;
import java.util.Optional;

public class RoleRespository {
    private EntityManager em;
    public RoleRespository(){
        em = DBConnection.getInstance().getEntityManager();
    }

    public Optional<Role> findRoleAccessByRoleId(String roleId) {
        EntityTransaction tr = em.getTransaction();
        tr.begin();
        try {
            String sql = "SELECT * FROM role WHERE role_id = ?";
            Role role = (Role) em.createNativeQuery(sql, Role.class).setParameter(1, roleId).getSingleResult();
            tr.commit();
            return Optional.of(role);
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public boolean addRole(Role role){
        EntityTransaction tr = em.getTransaction();
        tr.begin();
        try {
            em.persist(role);
            tr.commit();
            return true;
        }catch (Exception e){
            tr.rollback();
            e.printStackTrace();
        }
        return false;
    }

    public List<Role> getAll() {
        EntityTransaction tr = em.getTransaction();
        tr.begin();
        try {
            List<Role> resultList = em.createQuery("from Role" , Role.class).getResultList();
            tr.commit();
            return resultList;
        } catch (Exception e){
            tr.rollback();
            e.printStackTrace();
        }
        return null;
    }

    public Optional<Boolean> deleteRoleById(String roleId) {
        EntityTransaction tr = em.getTransaction();
        tr.begin();
        try {
            Role role = em.find(Role.class, roleId);
            if (role != null) {
                em.remove(role);
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

    public Optional<Role> updateRole(Role roleUpdate){
        EntityTransaction tr = em.getTransaction();
        tr.begin();
        try {
            Role role = em.find(Role.class, roleUpdate.getId());
            if (role != null) {
                em.merge(roleUpdate);

                tr.commit();
                return Optional.of(role);
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

}
