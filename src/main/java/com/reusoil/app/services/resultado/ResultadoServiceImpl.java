package com.reusoil.app.services.resultado;

import com.reusoil.app.models.resultado.ResultadoAPI;
import com.reusoil.app.models.resultado.ResultadoEntity;
import com.reusoil.app.repository.ResultadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class ResultadoServiceImpl implements ResultadoService{

    private final ResultadoRepository resultadoRepository;
    @Override
    public void guardar(ResultadoAPI resultado) {

        ResultadoEntity resultadoGuardar = new ResultadoEntity();
        resultadoGuardar.setResultadoTemperatura(23.5F); // Ejemplo de temperatura
        resultadoGuardar.setNivelLlenado(75);           // Ejemplo de nivel de llenado
        // Otros datos de sensores
        resultadoRepository.save(resultadoGuardar);
    }

    @Override
    public List<ResultadoEntity> obtenerTodos() {
        return resultadoRepository.findAll();
    }

    @Override
    public Optional<ResultadoEntity> obtenerPorId(Long id) {
        return resultadoRepository.findById(id);
    }
}
