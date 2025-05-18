package net.ausiasmarch.iswart.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.ausiasmarch.iswart.entity.FacturaEntity;
import net.ausiasmarch.iswart.entity.FacturaEntity;
import net.ausiasmarch.iswart.entity.ProductoEntity;
import net.ausiasmarch.iswart.exception.ExistingEntityException;
import net.ausiasmarch.iswart.exception.UnauthorizedAccessException;
import net.ausiasmarch.iswart.repository.FacturaRepository;

@Service
public class FacturaService implements ServiceInterface<FacturaEntity> {
    
    @Autowired
    AuthService oAuthService;

    @Autowired
    FacturaRepository oFacturaRepository;


        public Page<FacturaEntity> getPage(Pageable oPageable, Optional<String> filter) {
        if (filter.isPresent()) {
            String filterValue = filter.get();

            return oFacturaRepository.findByFechaContaining(
                        filterValue, oPageable);

                        //TODO hacer distintos filtros para mes año y dia
            
        } else {
            // Si no hay filtro, devolvemos todos los productos
            return oFacturaRepository.findAll(oPageable);
        }
    }

     public Page<FacturaEntity> getHistorial(Pageable oPageable, Long id_usuario, Optional<String> filter) {
         return oFacturaRepository.findByUsuarioId(id_usuario, oPageable);
     }

    public FacturaEntity get(Long id) {
        return oFacturaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se ha encontrado el regitro en el carrito"));
    }

     public Long count() {
         if (oAuthService.isAdmin()) {
             return oFacturaRepository.count();
         } else {
             throw new UnauthorizedAccessException("No tienes permisos para acceder a esta zona");
         }
     }

    public FacturaEntity create(FacturaEntity oFacturaEntity) {
        if (oAuthService.isAdminOrClient()) {
                return oFacturaRepository.save(oFacturaEntity);
        } else {
            throw new UnauthorizedAccessException("No tienes permisos para acceder a esta zona");
        }
    }

    public FacturaEntity update(FacturaEntity oFacturaEntity) {
        if (oAuthService.isAdmin() || oAuthService.isClientWithItsOwnData(oFacturaEntity.getUsuario().getId())) {
            FacturaEntity oFacturaEntityFromDatabase = oFacturaRepository.findById(oFacturaEntity.getId()).get();

                if (oFacturaEntity.getFecha() != null) {
                    oFacturaEntityFromDatabase.setFecha(oFacturaEntity.getFecha());
                }
                if (oFacturaEntity.getUsuario() != null) {
                    oFacturaEntityFromDatabase.setUsuario(oFacturaEntity.getUsuario());
                }
                return oFacturaRepository.save(oFacturaEntityFromDatabase);
            
        } else {
            throw new UnauthorizedAccessException("No tienes permisos para acceder a esta zona");
        }
    }

    public Long deleteAll() {
        if (oAuthService.isAdmin()) {
            oFacturaRepository.deleteAll();
            return this.count();
        } else {
            throw new UnauthorizedAccessException("No tienes permisos para acceder a esta zona");
        }
    }

    public Long delete(Long id) {
        if (oAuthService.isAdmin()) {
            oFacturaRepository.deleteById(id);
            return 1L;
        } else {
            throw new UnauthorizedAccessException("No tienes permisos para acceder a esta zona");
        }
    }



}
