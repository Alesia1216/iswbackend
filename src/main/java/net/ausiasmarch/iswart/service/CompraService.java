package net.ausiasmarch.iswart.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.ausiasmarch.iswart.entity.CompraEntity;
import net.ausiasmarch.iswart.repository.CompraRepository;

@Service
public class CompraService implements ServiceInterface<CompraEntity>{

    @Autowired
    private CompraRepository oCompraRepository;

        public Page<CompraEntity> getPage(Pageable oPageable, Optional<String> filter) {

        // if (filter.isPresent()) {
        //     return oCompraRepository
        //             .findByNombreContainingOrApellido1ContainingOrApellido2ContainingOrEmailContaining(
        //                     filter.get(), filter.get(), filter.get(), filter.get(),
        //                     oPageable);
        // } else {
        //     return oCompraRepository.findAll(oPageable);
        // }

        return oCompraRepository.findAll(oPageable);
    }

    public Page<CompraEntity> getHistorial(Pageable oPageable, Long id_usuario, Optional<String> filter) {
        return oCompraRepository.findByUsuarioId(id_usuario, oPageable);
    }

    public CompraEntity get(Long id) {
        return oCompraRepository.findById(id).orElseThrow(() -> new RuntimeException("No se ha encontrado el usuario"));
        // return oCompraRepository.findById(id).get();
    }

    public Long count() {
        return oCompraRepository.count();
    }

    public CompraEntity create(CompraEntity oCompraEntity) {
        //if (oCompraEntity.getTipousuario() == null) {
        //throw new ValidationException("El tipo de usuario es obligatorio");
        //}
        return oCompraRepository.save(oCompraEntity);
    }

    public CompraEntity update(CompraEntity oCompraEntity) {
        CompraEntity oCompraEntityFromDatabase = oCompraRepository.findById(oCompraEntity.getId()).get();
        if (oCompraEntity.getFecha() != null) {
            oCompraEntityFromDatabase.setFecha(oCompraEntity.getFecha());
        }
        return oCompraRepository.save(oCompraEntityFromDatabase);
    }

    public Long deleteAll() {
        oCompraRepository.deleteAll();
        return this.count();
    }
    
    public Long delete(Long id) {
        oCompraRepository.deleteById(id);
        return 1L;
    }
    
}
