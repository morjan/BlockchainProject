package com.model.pojo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Class that map the entity UserLogin in the database
 * @author lovet
 */
@Entity
@Table(name = "UserLogin", catalog = "MA9627s")
public class UserLogin implements Serializable {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_login", unique = true/*, nullable = false*/)
    private int idLogin;
    @Column(name = "id_role")
    private int idRole;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "password")
    private String password;

    public UserLogin() {
    }

    public UserLogin(String email, String password) {
        this.email = email;
        this.password = password;
    }
    
    public UserLogin(int idRole, String email, String password) {
        this.idRole = idRole;
        this.email = email;
        this.password = password;
    }

    public UserLogin(int idLogin, int idRole, String email, String password) {
        this.idLogin = idLogin;
        this.idRole = idRole;
        this.email = email;
        this.password = password;
    }

    
    public int getIdLogin() {
        return idLogin;
    }

    public void setIdLogin(int idLogin) {
        this.idLogin = idLogin;
    }

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Login{" + "idLogin=" + idLogin + ", idRole=" + idRole + ", email=" + email + ", password=" + password + '}';
    }   
}
