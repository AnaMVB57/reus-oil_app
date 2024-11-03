package com.reusoil.app.controller.empresa;

import com.reusoil.app.models.empresa.EmpresaEntity;
import com.reusoil.app.services.ciudad.CiudadService;
import com.reusoil.app.services.empresa.EmpresaService;
import com.reusoil.app.services.tipo_empresa.TipoEmpresaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@RequestMapping("/empresa")
@Controller
public class EmpresaController {

    private final CiudadService ciudadService;
    private final TipoEmpresaService tipoEmpresaService;
    private final EmpresaService empresaService;

    @PostMapping("/guardar")
    public String crearOActualizarEmpresa(@Valid @ModelAttribute("empresa") EmpresaEntity empresa,
                                          BindingResult bindingResult,
                                          Model model) {
        // Verificar errores de validación
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "Por favor, corrija los errores en el formulario");
            model.addAttribute("modoEdicion", false);
            model.addAttribute("ciudades", ciudadService.obtenerCiudades());
            model.addAttribute("tiposEmpresas", tipoEmpresaService.obtenerTiposEmpresa());
            return "vistas/empresa/form_empresa"; // Volver al formulario con errores de validación
        }

        try {
            // Verificar si el NIT ya existe en la base de datos
            Optional<EmpresaEntity> empresaExistente = empresaService.obtenerEmpresaPorId(empresa.getId());

            if (empresaExistente.isPresent()) {
                EmpresaEntity empresaOriginal = empresaExistente.get();

                // Verificar si el NIT ya está asignado a una empresa diferente
                if (empresaOriginal.getId().equals(empresa.getId())) {
                    bindingResult.rejectValue("id", "error.empresa", "Ya existe una empresa con el mismo NIT");
                    model.addAttribute("modoEdicion", false);
                    model.addAttribute("empresa", empresa);
                    model.addAttribute("ciudades", ciudadService.obtenerCiudades());
                    model.addAttribute("tiposEmpresas", tipoEmpresaService.obtenerTiposEmpresa());
                    return "vistas/empresa/form_empresa"; // Regresar al formulario con mensaje de error
                }
            }

            // Verificar si el nombre ya existe en otra empresa
            Optional<EmpresaEntity> empresaConMismoNombre = empresaService.obtenerEmpresaPorNombre(empresa.getNombre());
            if (empresaConMismoNombre.isPresent() && !empresaConMismoNombre.get().getId().equals(empresa.getId())) {
                bindingResult.rejectValue("nombre", "error.empresa", "Ya existe una empresa con el mismo nombre");
                cargarModelo(model);
                return "vistas/empresa/form_empresa";
            }

            // Guardar o actualizar la empresa según corresponda
            empresaService.guardar(empresa);
            return "redirect:/empresa/listado-empresas";
        } catch (Exception e) {
            model.addAttribute("error", "Ocurrió un error al guardar la empresa. Intente nuevamente.");
            model.addAttribute("modoEdicion", false);
            model.addAttribute("ciudades", ciudadService.obtenerCiudades());
            model.addAttribute("tiposEmpresas", tipoEmpresaService.obtenerTiposEmpresa());
            return "vistas/empresa/form_empresa"; // Redirigir al formulario en caso de error
        }
    }


    @GetMapping("/eliminar/{id}")
    public String eliminarEmpresa(@PathVariable Long id) {
        empresaService.borradoLogico(id);
        return "redirect:/empresa/listado-empresas";
    }

    // Método auxiliar para cargar datos adicionales en el modelo
    private void cargarModelo(Model model) {
        model.addAttribute("ciudades", ciudadService.obtenerCiudades());
        model.addAttribute("tiposEmpresas", tipoEmpresaService.obtenerTiposEmpresa());
        model.addAttribute("modoEdicion", false);
    }

//    @PostMapping("/editar")
//    public String guardarEdicion(@ModelAttribute("empresa") EmpresaEntity empresa) {
//        empresaService.guardar(empresa);
//        return "redirect:/empresas";
//    }


}
