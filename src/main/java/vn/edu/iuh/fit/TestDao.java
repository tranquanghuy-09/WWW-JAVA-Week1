package vn.edu.iuh.fit;

import vn.edu.iuh.fit.entities.Role;
import vn.edu.iuh.fit.services.RoleService;

import java.util.List;

public class TestDao {
    public static void main(String[] args) {
        RoleService roleService = new RoleService();
        List<Role> list = roleService.getAll();
        System.out.println(list);

    }
}
