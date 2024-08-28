package com.biblioteca.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Libro libro;

    private String usuario;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;

    // Getters y Setters
}
