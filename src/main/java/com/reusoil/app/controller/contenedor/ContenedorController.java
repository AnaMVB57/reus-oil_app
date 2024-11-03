package com.reusoil.app.controller.contenedor;

import com.reusoil.app.models.contenedor.ContenedorEntity;
import com.reusoil.app.services.contenedor.ContenedorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("/contenedor")
public class ContenedorController {

    private final ContenedorService contenedorService;

    // Guardar nuevo contenedor
    @PostMapping("/guardar")
    public String guardarContenedor(@ModelAttribute("contenedor") ContenedorEntity contenedor) {
        contenedorService.guardar(contenedor);
        return "redirect:/contenedor/listado-contenedores";
    }

    // Eliminar contenedor
    @GetMapping("/eliminar/{id}")
    public String eliminarContenedor(@PathVariable("id") Long id) {
        contenedorService.eliminarContenedorPorId(id);
        return "redirect:/contenedor/listado-contenedores";
    }


}
