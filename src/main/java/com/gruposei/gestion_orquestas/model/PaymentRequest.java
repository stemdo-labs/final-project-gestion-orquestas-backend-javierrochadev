package com.gruposei.gestion_orquestas.model;

import javax.persistence.*;

@Entity
@Table(name = "payment_requests")
public class PaymentRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "show_id")
    private Show show;
    private int quantity;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    private boolean paid = false;
    private String externalReference;
    private String preferenceID;

    public PaymentRequest() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public String getExternalReference() {
        return externalReference;
    }

    public void setExternalReference(String externalReference) {
        this.externalReference = externalReference;
    }

    public String getPreferenceID() {
        return preferenceID;
    }

    public void setPreferenceID(String preferenceID) {
        this.preferenceID = preferenceID;
    }
}
