package vn.edu.iuh.fit.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import vn.edu.iuh.fit.entities.GrantAccess;
import vn.edu.iuh.fit.entities.Role;
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
}
