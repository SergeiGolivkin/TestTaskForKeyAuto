
package com.forkeyauto.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String role;

    @Column
    private String fio;

    @Column
    private String post;

    @Column
    private String password;

    @Column
    private boolean blocked;
}

