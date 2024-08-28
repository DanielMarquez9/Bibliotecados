package com.biblioteca.controller;

import com.biblioteca.model.Libro;
import com.biblioteca.service.BibliotecaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class LibroController {

    @Autowired
    private BibliotecaService bibliotecaService;

    @GetMapping("/libros")
    public String verCatalogo(Model model) {
        List<Libro> libros = bibliotecaService.buscarLibrosPorTitulo("");
        model.addAttribute("libros", libros);
        return "catalogo";
    }

    @PostMapping("/libros/agregar")
    public String agregarLibro(Libro libro) {
        bibliotecaService.agregarLibro(libro);
        return "redirect:/libros";
    }

    @GetMapping("/libros/buscar")
    public String buscarLibro(@RequestParam String query, Model model) {
        List<Libro> libros = bibliotecaService.buscarLibrosPorTitulo(query);
        model.addAttribute("libros", libros);
        return "catalogo";
    }
}
