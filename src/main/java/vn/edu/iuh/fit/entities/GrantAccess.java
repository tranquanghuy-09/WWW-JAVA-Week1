package vn.edu.iuh.fit.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.io.Serializable;

@Entity
@Table(name = "grant_access")
public class GrantAccess implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
    @Id
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    @Column(name = "is_grant", columnDefinition = "bit")
    @ColumnDefault("1")
    private boolean isGrant;
    @Column(columnDefinition = "varchar(250)")
    @ColumnDefault("''")
    private String note;

    public GrantAccess() {
    }

    public GrantAccess(Account account, Role role, boolean isGrant, String note) {
        this.account = account;
        this.role = role;
        this.isGrant = isGrant;
        this.note = note;
    }

    @Override
    public String toString() {
        return "GrantAccess{" +
                "account=" + account +
                ", role=" + role +
                ", isGrant=" + isGrant +
                ", note='" + note + '\'' +
                '}';
    }
}
