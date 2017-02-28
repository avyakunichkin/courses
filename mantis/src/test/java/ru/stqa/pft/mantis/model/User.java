package ru.stqa.pft.mantis.model;

import com.google.gson.annotations.Expose;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mantis_user_table")
public class User {
    @Id
    @Column(name = "id")
    public int id;
    @Expose
    @Column(name = "username")
    public String name;
    @Expose
    @Column(name = "email")
    public String email;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void withId(int id) {
        this.id = id;
    }

    public String name() {
        return name;
    }

    public void withName(String name) {
        this.name = name;
    }

    public String email() {
        return email;
    }

    public void withEmail(String email) {
        this.email = email;
    }
}
