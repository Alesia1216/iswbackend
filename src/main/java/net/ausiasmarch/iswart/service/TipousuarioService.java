package net.ausiasmarch.iswart.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.ausiasmarch.iswart.entity.TipousuarioEntity;
import net.ausiasmarch.iswart.repository.TipousuarioRepository;

@Service
public class TipousuarioService implements ServiceInterface<TipousuarioEntity> {

    @Autowired
    private TipousuarioRepository oTipousuarioRepository;
    
     public Page<TipousuarioEntity> getPage(Pageable oPageable, Optional<String> filter) {

        // if (filter.isPresent()) {
        //     return oTipousuarioRepository
        //             .findByNombreContainingOrApellido1ContainingOrApellido2ContainingOrEmailContaining(
        //                     filter.get(), filter.get(), filter.get(), filter.get(),
        //                     oPageable);
        // } else {
        //     return oTipousuarioRepository.findAll(oPageable);
        // }

        return oTipousuarioRepository.findAll(oPageable);
    }

    public TipousuarioEntity get(Long id) {
        return oTipousuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("No se ha encontrado el tipo de usuario"));
        // return oTipousuarioRepository.findById(id).get();
    }

    public Long count() {
        return oTipousuarioRepository.count();
    }

    public TipousuarioEntity create(TipousuarioEntity oTipousuarioEntity) {
        //if (oTipousuarioEntity.getTipousuario() == null) {
        //throw new ValidationException("El tipo de usuario es obligatorio");
        //}
        return oTipousuarioRepository.save(oTipousuarioEntity);
    }

    public TipousuarioEntity update(TipousuarioEntity oTipousuarioEntity) {
        TipousuarioEntity oTipousuarioEntityFromDatabase = oTipousuarioRepository.findById(oTipousuarioEntity.getId()).get();
        if (oTipousuarioEntity.getDescripcion() != null) {
            oTipousuarioEntityFromDatabase.setDescripcion(oTipousuarioEntity.getDescripcion());
        }
        return oTipousuarioRepository.save(oTipousuarioEntityFromDatabase);
    }

    public Long deleteAll() {
        oTipousuarioRepository.deleteAll();
        return this.count();
    }
    
    public Long delete(Long id) {
        oTipousuarioRepository.deleteById(id);
        return 1L;
    }
}
