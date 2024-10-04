package org.example.lab620202269.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "peliculas")
@Getter
@Setter
public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "peliculaId")
    private Integer peliculaId;

    @Column(name = "titulo", length = 100)
    private String titulo;

    @Column(name = "fechaEstreno")
    private LocalDate fechaEstreno;

    @Column(name = "duracionMinutos")
    private Integer duracionMinutos;

    //@OneToMany(mappedBy = "pelicula", cascade = CascadeType.ALL)
    //private Set<PeliculaDirector> peliculaDirectores = new HashSet<>();

}


