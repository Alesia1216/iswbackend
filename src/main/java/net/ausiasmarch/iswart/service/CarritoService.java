package net.ausiasmarch.iswart.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.ausiasmarch.iswart.entity.CarritoEntity;
import net.ausiasmarch.iswart.entity.CarritoEntity;
import net.ausiasmarch.iswart.exception.ExistingEntityException;
import net.ausiasmarch.iswart.exception.UnauthorizedAccessException;
import net.ausiasmarch.iswart.repository.CarritoRepository;

@Service
public class CarritoService implements ServiceInterface<CarritoEntity> {
    
    @Autowired
    private CarritoRepository oCarritoRepository;

    @Autowired
    AuthService oAuthService;

    public Page<CarritoEntity> getPage(Pageable oPageable, Optional<String> filter) {
        return oCarritoRepository.findAll(oPageable);
    }

    public CarritoEntity get(Long id) {
        return oCarritoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se ha encontrado el regitro en el carrito"));
    }

     public Long count() {
         if (oAuthService.isAdmin()) {
             return oCarritoRepository.count();
         } else {
             throw new UnauthorizedAccessException("No tienes permisos para acceder a esta zona");
         }
     }

    public CarritoEntity create(CarritoEntity oCarritoEntity) {
        if (oAuthService.isAdminOrClient()) {
                return oCarritoRepository.save(oCarritoEntity);
        } else {
            throw new UnauthorizedAccessException("No tienes permisos para acceder a esta zona");
        }
    }

    public CarritoEntity update(CarritoEntity oCarritoEntity) {
        if (oAuthService.isAdmin() || oAuthService.isClientWithItsOwnData(oCarritoEntity.getUsuario().getId())) {
            CarritoEntity oCarritoEntityFromDatabase = oCarritoRepository.findById(oCarritoEntity.getId()).get();

                if (oCarritoEntity.getUsuario() != null) {
                    oCarritoEntityFromDatabase.setUsuario(oCarritoEntity.getUsuario());
                }
                if (oCarritoEntity.getProducto() != null) {
                    oCarritoEntityFromDatabase.setProducto(oCarritoEntity.getProducto());
                }
                return oCarritoRepository.save(oCarritoEntityFromDatabase);
            
        } else {
            throw new UnauthorizedAccessException("No tienes permisos para acceder a esta zona");
        }
    }

    public Long deleteAll() {
        if (oAuthService.isAdmin()) {
            oCarritoRepository.deleteAll();
            return this.count();
        } else {
            throw new UnauthorizedAccessException("No tienes permisos para acceder a esta zona");
        }
    }

    public Long delete(Long id) {
        if (oAuthService.isAdmin()) {
            oCarritoRepository.deleteById(id);
            return 1L;
        } else {
            throw new UnauthorizedAccessException("No tienes permisos para acceder a esta zona");
        }
    }

}
