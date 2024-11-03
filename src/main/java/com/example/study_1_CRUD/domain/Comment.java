package com.example.study_1_CRUD.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.example.study_1_CRUD.domain.Users;

@Entity
@Getter @Setter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne
    private Users author;
}
