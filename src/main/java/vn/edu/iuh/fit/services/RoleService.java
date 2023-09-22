package vn.edu.iuh.fit.services;


import vn.edu.iuh.fit.entities.Role;
import vn.edu.iuh.fit.repositories.RoleRespository;

import java.util.Optional;

public class RoleService {
    private static final RoleRespository roleRespository= new RoleRespository();

    public Optional<Role> findRoleAccessByRoleId(String roleId) {
        return roleRespository.findRoleAccessByRoleId(roleId);
    }
}
