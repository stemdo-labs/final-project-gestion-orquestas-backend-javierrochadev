package com.gruposei.gestion_orquestas.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "meeting_minutes")
public class MeetingMinute {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String text;
    private String author;
    private Date date;

    @ManyToMany
    @JoinTable(name = "users_meeting_minutes",
            joinColumns = @JoinColumn(name = "minute_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> usersMeeting = new ArrayList<>();

    public MeetingMinute() {
    }

    public MeetingMinute(Long id, String text, String author, Date date) {
        this.id = id;
        this.text = text;
        this.author = author;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<User> getUsersMeeting() {
        return usersMeeting;
    }

    public void setUsersMeeting(List<User> usersMeeting) {
        this.usersMeeting = usersMeeting;
    }

    public void addUsers(User user){

        usersMeeting.add(user);
    }
}
