package com.gruposei.gestion_orquestas.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String name;
    private String lastname;
    private String email;
    private String address;
    @Column(nullable = false)
    private boolean enabled = true;

    @ManyToMany
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> rolesUser = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "usersMeeting")
    private List<MeetingMinute> usersInMeeting = new ArrayList<>();

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(Long id, String username, String password, String name, String lastname, String email, String address) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void addRole(Role role){

        rolesUser.add(role);
    }

    public void addRoles(List<Role> role){

        for (Role r:role) {

            rolesUser.add(r);
        }
    }

    public void setRoles(List<Role> role){

            this.rolesUser= role;
    }

    public List<Role> getRolesUser() {
        return rolesUser;
    }

    public void setRolesUser(List<Role> rolesUser) {
        this.rolesUser = rolesUser;
    }

    public List<MeetingMinute> getUsersInMeeting() {
        return usersInMeeting;
    }

    public void setUsersInMeeting(List<MeetingMinute> usersInMeeting) {
        this.usersInMeeting = usersInMeeting;
    }
}
