package com.biblioteca.service;

import com.biblioteca.model.Libro;
import com.biblioteca.model.Prestamo;
import com.biblioteca.repository.LibroRepository;
import com.biblioteca.repository.PrestamoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BibliotecaService {

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private PrestamoRepository prestamoRepository;

    public List<Libro> buscarLibrosPorTitulo(String titulo) {
        return libroRepository.findByTituloContaining(titulo);
    }

    public List<Libro> buscarLibrosPorAutor(String autor) {
        return libroRepository.findByAutorContaining(autor);
    }

    public Libro agregarLibro(Libro libro) {
        libro.setDisponible(true);
        return libroRepository.save(libro);
    }

    public Prestamo solicitarPrestamo(Long libroId, String usuario) {
        Libro libro = libroRepository.findById(libroId).orElseThrow();
        if (libro.isDisponible()) {
            libro.setDisponible(false);
            libroRepository.save(libro);

            Prestamo prestamo = new Prestamo();
            prestamo.setLibro(libro);
            prestamo.setUsuario(usuario);
            prestamo.setFechaPrestamo(LocalDate.now());

            return prestamoRepository.save(prestamo);
        }
        return null;
    }

    public void registrarDevolucion(Long prestamoId) {
        Prestamo prestamo = prestamoRepository.findById(prestamoId).orElseThrow();
        Libro libro = prestamo.getLibro();
        libro.setDisponible(true);
        libroRepository.save(libro);

        prestamo.setFechaDevolucion(LocalDate.now());
        prestamoRepository.save(prestamo);
    }
}
