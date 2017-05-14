package com.example.easynoteaca.pojo;

import java.io.Serializable;
import java.util.Date;

public class Note implements Serializable {
    private static final long serialVersionUID = -1;
    private long id;
    private String title;
    private String description;
    private Date createDate;
    private int color;
    private boolean important;

    public Note(String title, String description, boolean important,int color) {
        this.title = title;
        this.description = description;
        this.color = color;
        this.important = important;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public boolean isImportant() {
        return important;
    }

    public void setImportant(boolean important) {
        this.important = important;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", createDate=" + createDate +
                ", color=" + color +
                ", important=" + important +
                '}';
    }

}
