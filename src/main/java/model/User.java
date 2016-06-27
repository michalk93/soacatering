package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by mkolbusz on 6/5/16.
 */
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "user_id"),
        @UniqueConstraint(columnNames = "email")}
)
public class User implements Serializable {
    private static final long serialVersionID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "user_id", unique = true, nullable = false)
    int id;

    @Column(name = "firstname", nullable = false)
    String firstname;

    @Column(name = "lastname", nullable = false)
    String lastname;

    @Column(name = "email", nullable = false, unique = true)
    String email;

    @Column(name = "password", nullable = false)
    String password;

    @Column(name = "is_logged")
    int isLogged = 0;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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

    public int getIsLogged() {
        return isLogged;
    }

    public void setIsLogged(int isLogged) {
        this.isLogged = isLogged;
    }

    @Override
    public String toString() {
        return this.firstname + " " + this.lastname + " " + this.email;
    }
}
