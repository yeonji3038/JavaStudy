package com.example.java.domain.board;

//CREATE TABLE boardBOARD AUTHOR AUTHOR AUTHOR BOARD BOARD BOARD  (
//        ID INT AUTO_INCREMENT PRIMARY KEY,
//        AUTHOR VARCHAR(255),
//        TITLE VARCHAR(255),
//        CONTENT TEXT
//        );




import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private String author;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}