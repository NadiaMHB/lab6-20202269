package org.example.lab620202269.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "directores")
public class Director {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "directorId")
    private Integer directorId;

    @Column(name = "nombre", length = 100)
    private String nombre;

    @Column(name = "telefono", length = 9)
    private String telefono;

    @Column(name = "nacionalidad", length = 50)
    private String nacionalidad;


    //@OneToMany(mappedBy = "director", cascade = CascadeType.ALL)
    //private Set<PeliculaDirector> peliculaDirectores = new HashSet<>();

}


