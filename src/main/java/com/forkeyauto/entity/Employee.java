
package com.forkeyauto.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;

@Data
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int type;

    @Column
    private String role;

    @Column
    private String fio;

    @Column
    private String post;

    @Column
    private String password;
}

