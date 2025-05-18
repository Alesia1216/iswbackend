package net.ausiasmarch.iswart.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.ausiasmarch.iswart.api.LineaFactura;
import net.ausiasmarch.iswart.entity.CarritoEntity;
import net.ausiasmarch.iswart.entity.LineafacturaEntity;
import net.ausiasmarch.iswart.entity.LineafacturaEntity;
import net.ausiasmarch.iswart.exception.ExistingEntityException;
import net.ausiasmarch.iswart.exception.UnauthorizedAccessException;
import net.ausiasmarch.iswart.repository.LineafacturaRepository;

@Service
public class LineafacturaService implements ServiceInterface<LineafacturaEntity> {
    
    @Autowired
    AuthService oAuthService;

    @Autowired
    LineafacturaRepository oLineafacturaRepository;

    public Page<LineafacturaEntity> getPage(Pageable oPageable, Optional<String> filter) {
        if (filter.isPresent()) {
            String filterValue = filter.get();

            return oLineafacturaRepository.findByPrecioContainingOrCantidadContaining(
                        filterValue,filterValue,oPageable);
            
        } else {
            // Si no hay filtro, devolvemos todos los productos
            return oLineafacturaRepository.findAll(oPageable);
        }
    }

    public List<LineafacturaEntity> getLineasByFacturaId(Long facturaId) {
        return oLineafacturaRepository.findByFacturaId(facturaId);
    }

    public LineafacturaEntity get(Long id) {
        return oLineafacturaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se ha encontrado el regitro en el carrito"));
    }

     public Long count() {
         if (oAuthService.isAdmin()) {
             return oLineafacturaRepository.count();
         } else {
             throw new UnauthorizedAccessException("No tienes permisos para acceder a esta zona");
         }
     }

    public LineafacturaEntity create(LineafacturaEntity oLineafacturaEntity) {
        if (oAuthService.isAdminOrClient()) {
                return oLineafacturaRepository.save(oLineafacturaEntity);
        } else {
            throw new UnauthorizedAccessException("No tienes permisos para acceder a esta zona");
        }
    }

    public LineafacturaEntity update(LineafacturaEntity oLineafacturaEntity) {
        if (oAuthService.isAdmin() || oAuthService.isClientWithItsOwnData(oLineafacturaEntity.getFactura().getUsuario().getId())) {
            LineafacturaEntity oLineafacturaEntityFromDatabase = oLineafacturaRepository.findById(oLineafacturaEntity.getId()).get();

                if (oLineafacturaEntity.getFactura() != null) {
                    oLineafacturaEntityFromDatabase.setFactura(oLineafacturaEntity.getFactura());
                }
                if (oLineafacturaEntity.getProducto() != null) {
                    oLineafacturaEntityFromDatabase.setProducto(oLineafacturaEntity.getProducto());
                }
                if (oLineafacturaEntity.getPrecio() != null) {
                    oLineafacturaEntityFromDatabase.setPrecio(oLineafacturaEntity.getPrecio());
                }
                if (oLineafacturaEntity.getCantidad() != null) {
                    oLineafacturaEntityFromDatabase.setCantidad(oLineafacturaEntity.getCantidad());
                }
                return oLineafacturaRepository.save(oLineafacturaEntityFromDatabase);
            
        } else {
            throw new UnauthorizedAccessException("No tienes permisos para acceder a esta zona");
        }
    }

    public Long deleteAll() {
        if (oAuthService.isAdmin()) {
            oLineafacturaRepository.deleteAll();
            return this.count();
        } else {
            throw new UnauthorizedAccessException("No tienes permisos para acceder a esta zona");
        }
    }

    public Long delete(Long id) {
        if (oAuthService.isAdmin()) {
            oLineafacturaRepository.deleteById(id);
            return 1L;
        } else {
            throw new UnauthorizedAccessException("No tienes permisos para acceder a esta zona");
        }
    }


}
