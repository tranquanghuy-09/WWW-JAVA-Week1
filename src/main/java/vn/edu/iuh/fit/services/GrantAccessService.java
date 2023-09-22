package vn.edu.iuh.fit.services;

import vn.edu.iuh.fit.entities.GrantAccess;
import vn.edu.iuh.fit.repositories.GrantAccessResponsitory;

import java.util.Optional;

public class GrantAccessService {
    private static final GrantAccessResponsitory grantAccessResponsitory = new GrantAccessResponsitory();

//    public Optional<GrantAccess> findGrantAccess(String id){
//        return grantAccessResponsitory.findGrantAccess(id);
//    }

    public Optional<GrantAccess> findGrantAccessByAccountId(String accountId) {
        return grantAccessResponsitory.findGrantAccessByAccountId(accountId);
    }
}
