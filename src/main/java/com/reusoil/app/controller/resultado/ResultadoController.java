package com.reusoil.app.controller.resultado;

import com.reusoil.app.models.resultado.ResultadoEntity;
import com.reusoil.app.repository.ResultadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController // Cambiado a @RestController para devolver JSON automáticamente
@RequestMapping("/resultado")
@RequiredArgsConstructor
public class ResultadoController {

    private final ResultadoRepository resultadoRepository;

    @PostMapping("/guardar")
    public String guardarResultado(
            @RequestParam float resultadoTemperatura,
            @RequestParam float nivelLlenado,
            @RequestParam boolean estado) {

        // Crear y guardar el resultado
        ResultadoEntity resultado = new ResultadoEntity();
        resultado.setResultadoTemperatura(resultadoTemperatura);
        resultado.setNivelLlenado(nivelLlenado);
        resultado.setFechaMedicion(LocalDate.now());
        resultado.setEstado(estado);

        resultadoRepository.save(resultado);
        return "Dato guardado exitosamente";
    }
}

//    @GetMapping("/listar") // Nuevo método para listar registros
//    public List<ResultadoEntity> listarResultados() {
//        List<ResultadoEntity> resultados = resultadoRepository.findAll();
//        // Imprimir los resultados en la consola
//        resultados.forEach(resultado -> System.out.println(resultado));
//        return resultados;
//    }



//    @Controller
//    @RequestMapping("/medida")
//    @RequiredArgsConstructor
//    public class ResultadoController {
//
//        private final ResultadoRepository resultadoRepository;
//        private final ConfiguracionRepository configuracionRepository;
//
//        @PostMapping("/guardar")
//        @ResponseBody // Asegúrate de agregar esto para devolver JSON
//        public String guardarResultado(
//                @RequestParam float resultadoTemperatura,
//                @RequestParam float nivelLlenado,
//                @RequestParam boolean estado,
//                @RequestParam Long idConfiguracion) {
//
//            // Buscar la configuración correspondiente
//            Optional<Configuracion> configuracionOptional = configuracionRepository.findById(idConfiguracion);
//
//            if (configuracionOptional.isEmpty()) {
//                return "Error: Configuración con ID " + idConfiguracion + " no encontrada.";
//            }
//
//            // Crear y guardar el resultado
//            Configuracion configuracion = configuracionOptional.get();
//            ResultadoEntity resultado = new ResultadoEntity();
//            resultado.setResultadoTemperatura(resultadoTemperatura);
//            resultado.setNivelLlenado(nivelLlenado);
//            resultado.setFechaMedicion(LocalDate.now());
//            resultado.setEstado(estado);
//            resultado.setConfiguracion(configuracion);
//
//            resultadoRepository.save(resultado);
//            return "Dato guardado exitosamente";
//        }
//}
