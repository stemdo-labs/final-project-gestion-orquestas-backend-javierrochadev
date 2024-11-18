package com.gruposei.gestion_orquestas.model;

import javax.persistence.*;

@Entity
@Table(name = "news_content_types")
public class NewContentType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String systemName;

    public NewContentType() {
    }

    public NewContentType(Long id, String name, String systemName) {
        this.id = id;
        this.name = name;
        this.systemName = systemName;
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

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }
}
