package com.apache.apibookshop.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="lendbook")
@Getter
@Setter
public class LendBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false, unique = true)
    private Long id;

    @Column(name="typeUser", length = 50)
    private Integer typeUser;

    @Column(name="codeBook")
    private String codeBook;

    @Column(name="limitDate")
    private LocalDate limitDate;
}
