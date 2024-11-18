package com.gruposei.gestion_orquestas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;
    @ManyToOne(optional = false)
    @JoinColumn(name = "show_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Show show;
//    @JsonIgnore
    private String code;
    private Date purchaseDate;
    private boolean used;

    public Ticket() {
    }

    public Ticket(Long id, User user, Show show, String code, Date purchaseDate, boolean used) {
        this.id = id;
        this.user = user;
        this.show = show;
        this.code = code;
        this.purchaseDate = purchaseDate;
        this.used = used;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {

        this.code = code;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }
}
