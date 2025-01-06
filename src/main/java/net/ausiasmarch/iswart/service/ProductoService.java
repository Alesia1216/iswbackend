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
        if (filter.isPresent()) {
            String filterValue = filter.get();
    
            // Verificar si el filtro es numérico (Long para unidades o Double para precio)
            boolean isNumeric = filterValue.matches("-?\\d+(\\.\\d+)?");  // Permite números enteros y decimales
    
            if (isNumeric) {
                try {
                    // Intentamos convertir el filtro a Long para unidades
                    Long filterLong = Long.parseLong(filterValue);
    
                    // Intentamos convertir el filtro a Double para precio
                    Double filterDouble = Double.parseDouble(filterValue);
    
                    // Buscamos por unidades exactas
                    Page<ProductoEntity> unidadesResult = oProductoRepository.findByUnidades(filterLong, oPageable);
                    // Buscamos por precio exacto
                    Page<ProductoEntity> precioResult = oProductoRepository.findByPrecio(filterDouble, oPageable);
    
                    // Combinamos los resultados de unidades y precio (si es necesario combinar)
                    return unidadesResult.getTotalElements() > 0 ? unidadesResult : precioResult;
                } catch (NumberFormatException e) {
                    // Si el filtro no se puede convertir a Long o Double, devolvemos una página vacía
                    return Page.empty();
                }
            } else {
                // Si el filtro contiene letras, buscamos en la descripción o estilo
                return oProductoRepository.findByDescripcionContainingOrEstiloContaining(
                    filterValue, filterValue, oPageable);
            }
        } else {
            // Si no hay filtro, devolvemos todos los productos
            return oProductoRepository.findAll(oPageable);
        }
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
