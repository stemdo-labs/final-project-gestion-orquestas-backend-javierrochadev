package com.gruposei.gestion_orquestas.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "news_content")
public class NewContent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Lob
    private String content;
    @ManyToOne(optional = false)
    @JoinColumn(name = "new_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private New news;
    @ManyToOne(optional = false)
    @JoinColumn(name = "type_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private NewContentType newContentType;

    public NewContent() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public New getNews() {
        return news;
    }

    public void setNews(New n) {
        this.news = n;
    }

    public NewContentType getNewContentType() {
        return newContentType;
    }

    public void setNewContentType(NewContentType newContentType) {
        this.newContentType = newContentType;
    }
}
