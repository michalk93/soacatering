package model;

import javax.persistence.*;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by mkolbusz on 6/5/16.
 */
@Entity
@Table(name = "clients", uniqueConstraints = {
        @UniqueConstraint(columnNames = "client_id"), @UniqueConstraint(columnNames = "email")}
)
public class Client implements Serializable {
    private static final long serialVersionID = 1L;

    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Boolean isLogged;

    @Id
    @GeneratedValue
    @Column(name = "client_id", unique = true, nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "firstname", nullable = false)
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Column(name = "lastname", nullable = false)
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Column(name = "email", nullable = false, unique = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "is_logged")
    public Boolean getLogged() {
        return isLogged;
    }

    public void setLogged(Boolean logged) {
        isLogged = logged;
    }

    @Override
    public String toString() {
        return this.firstname + " " + this.lastname + " " + this.email;
    }
}
