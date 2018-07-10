package com.model.pojo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Class that map the entity UserRole in the database
 * @author lovet
 */
@Entity
@Table(name = "UserRole", catalog = "MA9627s")
public class UserRole implements Serializable {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_role", unique = true/*, nullable = false*/)
    private int idRole;
    @Column(name = "role_name")
    private String roleName;

    
    public UserRole() {
    }

    public UserRole(int idRole, String roleName) {
        this.idRole = idRole;
        this.roleName = roleName;
    }

    
    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "UserRole{" + "idRole=" + idRole + ", roleName=" + roleName + '}';
    }
}
