package com.scrooge.scrooge.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Upload {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String imgAddress;
}
