package vn.edu.iuh.fit.services;


import vn.edu.iuh.fit.entities.Role;
import vn.edu.iuh.fit.repositories.RoleRespository;

import java.util.List;
import java.util.Optional;

public class RoleService {
    private static final RoleRespository roleRespository= new RoleRespository();

    public Optional<Role> findRoleAccessByRoleId(String roleId) {
        return roleRespository.findRoleAccessByRoleId(roleId);
    }
    public boolean addRole(Role role){
        return roleRespository.addRole(role);
    }
    public List<Role> getAll() {
        return roleRespository.getAll();
    }

    public Optional<Boolean> deleteRoleById(String roleId) {
        return roleRespository.deleteRoleById(roleId);
    }
    public Optional<Role> updateRole(Role roleUpdate){
        return roleRespository.updateRole(roleUpdate);
    }
}
