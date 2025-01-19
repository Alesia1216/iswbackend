package net.ausiasmarch.iswart.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import net.ausiasmarch.iswart.entity.UsuarioEntity;
import net.ausiasmarch.iswart.exception.UnauthorizedAccessException;
import net.ausiasmarch.iswart.repository.UsuarioRepository;

@Service
public class UsuarioService implements ServiceInterface<UsuarioEntity>{
    
    @Autowired
    UsuarioRepository oUsuarioRepository;

    HttpServletRequest oHttpServletRequest;

    AuthService oAuthService;

    //@Autowired
    //TipousuarioService oTipousuarioService;

    public Page<UsuarioEntity> getPage(Pageable oPageable, Optional<String> filter) {

        // if (filter.isPresent()) {
        //     return oUsuarioRepository
        //             .findByNombreContainingOrApellido1ContainingOrApellido2ContainingOrEmailContaining(
        //                     filter.get(), filter.get(), filter.get(), filter.get(),
        //                     oPageable);
        // } else {
        //     return oUsuarioRepository.findAll(oPageable);
        // }

        return oUsuarioRepository.findAll(oPageable);
    }

    public UsuarioEntity get(Long id) {
        if(oAuthService.isContableWithItsOwnData(id) || oAuthService.isAdmin()) {
            return oUsuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("No se ha encontrado el usuario"));
        }else{
            throw new UnauthorizedAccessException("No tienes permisos para acceder a esta zona");
        }
    }

    public Long count() {
        return oUsuarioRepository.count();
    }

    public UsuarioEntity create(UsuarioEntity oUsuarioEntity) {
        //if (oUsuarioEntity.getTipousuario() == null) {
        //throw new ValidationException("El tipo de usuario es obligatorio");
        //}
        return oUsuarioRepository.save(oUsuarioEntity);
    }

    public UsuarioEntity update(UsuarioEntity oUsuarioEntity) {
        UsuarioEntity oUsuarioEntityFromDatabase = oUsuarioRepository.findById(oUsuarioEntity.getId()).get();
        if (oUsuarioEntity.getNombre() != null) {
            oUsuarioEntityFromDatabase.setNombre(oUsuarioEntity.getNombre());
        }
        if (oUsuarioEntity.getApellido1() != null) {
            oUsuarioEntityFromDatabase.setApellido1(oUsuarioEntity.getApellido1());
        }
        if (oUsuarioEntity.getApellido2() != null) {
            oUsuarioEntityFromDatabase.setApellido2(oUsuarioEntity.getApellido2());
        }
        if (oUsuarioEntity.getEmail() != null) {
            oUsuarioEntityFromDatabase.setEmail(oUsuarioEntity.getEmail());
        }
        if (oUsuarioEntity.getTelefono() != null) {
            oUsuarioEntityFromDatabase.setTelefono(oUsuarioEntity.getTelefono());
        }
        if (oUsuarioEntity.getDireccion() != null) {
            oUsuarioEntityFromDatabase.setDireccion(oUsuarioEntity.getDireccion());
        }
        return oUsuarioRepository.save(oUsuarioEntityFromDatabase);
    }

    public Long deleteAll() {
        oUsuarioRepository.deleteAll();
        return this.count();
    }
    
    public Long delete(Long id) {
        oUsuarioRepository.deleteById(id);
        return 1L;
    }

      public String RestrictedArea() {

        if(oHttpServletRequest.getAttribute("email") == null) {
            return "No tienes permiso para la zona restringida";
        }else{
            return "Bienvenido a zona restringida";
        }

    }

}
