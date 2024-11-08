package com.reusoil.app.controller.contenedor;

import com.reusoil.app.models.contenedor.ContenedorEntity;
import com.reusoil.app.services.contenedor.ContenedorService;
import com.reusoil.app.services.empresa.EmpresaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@Controller
@RequestMapping("/contenedor")
public class ContenedorController {

    private final ContenedorService contenedorService;
    private final EmpresaService empresaService;

    // Guardar o actualizar contenedor
    @PostMapping("/guardar")
    public String guardarContenedor(@Valid @ModelAttribute("contenedor") ContenedorEntity contenedor,
                                    BindingResult bindingResult, Model model) {
        // Verifica si existen errores de validación en el formulario
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "Por favor, corrija los errores en el formulario.");
            model.addAttribute("modoEdicion", true);
            model.addAttribute("contenedor", contenedor);
            model.addAttribute("empresas", empresaService.obtenerEmpresasPorEstado(true));
            return "vistas/contenedor/form_contenedor";
        }

//        try {
//            Optional<ContenedorEntity> contenedorExistente = contenedorService.(contenedor.getUbicacion());
//
//            // Validación de ubicación única
//            if (contenedorExistente.isPresent() && !contenedorExistente.get().getId().equals(contenedor.getId())) {
//                bindingResult.rejectValue("ubicacion", "error.contenedor", "Ya existe un contenedor en la misma ubicación.");
//                model.addAttribute("modoEdicion", true);
//                model.addAttribute("contenedor", contenedor);
//                model.addAttribute("empresas", empresaService.obtenerEmpresasPorEstado(true));
//                return "vistas/contenedor/form_contenedor";
//            }

            contenedorService.guardar(contenedor);
            return "redirect:/contenedor/listado-contenedores";

//        } catch (Exception e) {
//            model.addAttribute("error", "Ocurrió un error al guardar el contenedor. Intente nuevamente.");
//            model.addAttribute("modoEdicion", true);
//            model.addAttribute("contenedor", contenedor);
//            model.addAttribute("empresas", empresaService.obtenerEmpresasPorEstado(true));
//            return "vistas/contenedor/form_contenedor";
//        }
    }

    // Eliminar contenedor
    @GetMapping("/eliminar/{id}")
    public String eliminarContenedor(@PathVariable("id") Long id) {
        contenedorService.eliminarContenedorPorId(id);
        return "redirect:/contenedor/listado-contenedores";
    }
}
