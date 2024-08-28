package com.biblioteca.controller;

import com.biblioteca.model.Prestamo;
import com.biblioteca.service.BibliotecaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PrestamoController {

    @Autowired
    private BibliotecaService bibliotecaService;

    @PostMapping("/prestamos/solicitar")
    public String solicitarPrestamo(@RequestParam Long libroId, @RequestParam String usuario) {
        bibliotecaService.solicitarPrestamo(libroId, usuario);
        return "redirect:/libros";
    }

    @PostMapping("/prestamos/devolver")
    public String registrarDevolucion(@RequestParam Long prestamoId) {
        bibliotecaService.registrarDevolucion(prestamoId);
        return "redirect:/libros";
    }
}
