package com.reusoil.app.services.empresa;

import com.reusoil.app.models.empresa.EmpresaEntity;
import com.reusoil.app.repository.empresa.EmpresaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EmpresaServiceImpl implements EmpresaService{

    final private EmpresaRepository empresaRepository;
    @Override
    @Transactional(readOnly = true)
    public List<EmpresaEntity> obtenerEmpresas() {
        return empresaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<EmpresaEntity> obtenerEmpresaPorId(Long id) {
        return empresaRepository.findById(id);
    }

    @Override
    @Transactional
    public void guardar(EmpresaEntity empresa) {
        empresaRepository.save(empresa);
    }

    @Override
    @Transactional
    public void eliminarEmpresaPorId(Long id) {
        empresaRepository.deleteById(id);
    }

    @Override
    public Optional<EmpresaEntity> obtenerEmpresaPorNombre(String nombre) {
        return empresaRepository.findByNombre(nombre);
    }

    @Override
    public List<EmpresaEntity> obtenerEmpresasPorEstado(boolean estado) {
        return empresaRepository.findByEstado(estado);
    }

    public void borradoLogico(Long id) {
        Optional<EmpresaEntity> empresa = empresaRepository.findById(id);
        if (empresa.isPresent()) {
            EmpresaEntity entidad = empresa.get();
            entidad.setEstado(false);
            empresaRepository.save(entidad);
        }
    }

}
