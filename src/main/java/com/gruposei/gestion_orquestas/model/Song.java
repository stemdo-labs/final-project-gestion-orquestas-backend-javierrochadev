package com.gruposei.gestion_orquestas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "songs")
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    @ManyToOne
    @JoinColumn(name = "composer_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Composer author;
    @ManyToOne
    @JoinColumn(name = "imagen_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Imagen imagen;

    public Song() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Composer getAuthor() {
        return author;
    }

    public void setAuthor(Composer author) {
        this.author = author;
    }

    public Imagen getImagen() {
        return imagen;
    }

    public void setImagen(Imagen imagen) {
        this.imagen = imagen;
    }
}
