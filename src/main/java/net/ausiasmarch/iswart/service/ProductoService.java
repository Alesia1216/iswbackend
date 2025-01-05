package net.ausiasmarch.iswart.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.ausiasmarch.iswart.entity.ProductoEntity;
import net.ausiasmarch.iswart.repository.ProductoRepository;

@Service
public class ProductoService implements ServiceInterface<ProductoEntity> {
    
    @Autowired
    private ProductoRepository oProductoRepository;

        public Page<ProductoEntity> getPage(Pageable oPageable, Optional<String> filter) {

        // if (filter.isPresent()) {
        //     return oProductoRepository
        //             .findByNombreContainingOrApellido1ContainingOrApellido2ContainingOrEmailContaining(
        //                     filter.get(), filter.get(), filter.get(), filter.get(),
        //                     oPageable);
        // } else {
        //     return oProductoRepository.findAll(oPageable);
        // }

        return oProductoRepository.findAll(oPageable);
    }

    public ProductoEntity get(Long id) {
        return oProductoRepository.findById(id).orElseThrow(() -> new RuntimeException("No se ha encontrado el producto"));
        // return oProductoRepository.findById(id).get();
    }

    public Long count() {
        return oProductoRepository.count();
    }

    public ProductoEntity create(ProductoEntity oProductoEntity) {
        //if (oProductoEntity.getTipousuario() == null) {
        //throw new ValidationException("El tipo de usuario es obligatorio");
        //}
        return oProductoRepository.save(oProductoEntity);
    }

    public ProductoEntity update(ProductoEntity oProductoEntity) {
        ProductoEntity oProductoEntityFromDatabase = oProductoRepository.findById(oProductoEntity.getId()).get();
        if (oProductoEntity.getDescripcion() != null) {
            oProductoEntityFromDatabase.setDescripcion(oProductoEntity.getDescripcion());
        }
        if (oProductoEntity.getEstilo() != null) {
            oProductoEntityFromDatabase.setEstilo(oProductoEntity.getEstilo());
        }
        if (oProductoEntity.getUnidades() != null) {
            oProductoEntityFromDatabase.setUnidades(oProductoEntity.getUnidades());
        }
        if (oProductoEntity.getPrecio() != null) {
            oProductoEntityFromDatabase.setPrecio(oProductoEntity.getPrecio());
        }
        return oProductoRepository.save(oProductoEntityFromDatabase);
    }

    public Long deleteAll() {
        oProductoRepository.deleteAll();
        return this.count();
    }
    
    public Long delete(Long id) {
        oProductoRepository.deleteById(id);
        return 1L;
    }
}
