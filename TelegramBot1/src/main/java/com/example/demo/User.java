package com.example.demo;

import parser.Vacancies;

import javax.persistence.*;


@Entity
public class User {
    @Id
    @GeneratedValue
    private  Long id;

    private Long chatId;
    private Integer stateId;
    private boolean admin;
    private String profession;
    private String level;
    private String city;
    private Boolean notified = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="vac_id")
    private Vacancies vacancies;

    public User() {
    }
    public User(Long chatId, Integer state ){
        this.chatId = chatId;
        this.stateId = state;
    }

    public User(Long chatId, Integer state, Boolean admin){
        this.chatId = chatId;
        this.stateId = state;
        this.admin = admin;
    }

    public long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public boolean getAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }
    public Boolean getNotified() {
        return notified;
    }

    public void setNotified(Boolean notified) {
        this.notified = notified;
    }
}
