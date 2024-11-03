package com.example.study_1_CRUD.controller;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BoardForm {
    private Long id;
    private String title;
    private String content;
    private String author;
}
