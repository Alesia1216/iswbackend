package net.ausiasmarch.iswart.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ValidationException;
import lombok.AllArgsConstructor;
import net.ausiasmarch.iswart.entity.TipousuarioEntity;
import net.ausiasmarch.iswart.entity.UsuarioEntity;
import net.ausiasmarch.iswart.exception.UnauthorizedAccessException;
import net.ausiasmarch.iswart.repository.UsuarioRepository;

@Service
public class UsuarioService implements ServiceInterface<UsuarioEntity> {

    @Autowired
    UsuarioRepository oUsuarioRepository;

    HttpServletRequest oHttpServletRequest;

    @Autowired

    AuthService oAuthService;

    TipousuarioService oTipousuarioService;

    public Page<UsuarioEntity> getPage(Pageable oPageable, Optional<String> filter) {

        if (oAuthService.isAdmin()) {
            return oUsuarioRepository.findAll(oPageable);
        } else {
            throw new UnauthorizedAccessException("No tienes permisos para acceder a esta zona");
        }

        // if (filter.isPresent()) {
        // return oUsuarioRepository
        // .findByNombreContainingOrApellido1ContainingOrApellido2ContainingOrEmailContaining(
        // filter.get(), filter.get(), filter.get(), filter.get(),
        // oPageable);
        // } else {
        // return oUsuarioRepository.findAll(oPageable);
        // }

    }

    public UsuarioEntity get(Long id) {

        UsuarioEntity usuario = oUsuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario " + id + " no encontrado"));

        if (oAuthService.isContableWithItsOwnData(id) || oAuthService.isAdmin()
                || oAuthService.isAuditorWithItsOwnData(id)) {
            return usuario;
        } else {
            throw new UnauthorizedAccessException("No tienes permisos para acceder a esta zona");
        }
    }

    public UsuarioEntity getByEmail(String email) {

        UsuarioEntity usuario = oUsuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario " + email + " no encontrado"));

        if (oAuthService.isContableWithItsOwnData(usuario.getId()) || oAuthService.isAdmin()
                || oAuthService.isAuditorWithItsOwnData(usuario.getId())) {
            return usuario;
        } else {
            throw new UnauthorizedAccessException("No tienes permisos para acceder a esta zona");
        }
    }

    public Long count() {
        if (oAuthService.isAdmin()) {
            return oUsuarioRepository.count();
        } else {
            throw new UnauthorizedAccessException("No tienes permisos para acceder a esta zona");
        }
    }

    public UsuarioEntity create(UsuarioEntity oUsuarioEntity) {
        if (oAuthService.isAdmin()) {
            return oUsuarioRepository.save(oUsuarioEntity);
        } else {
            throw new UnauthorizedAccessException("No tienes permisos para acceder a esta zona");
        }
    }

    public UsuarioEntity update(UsuarioEntity oUsuarioEntity) {

        if (oAuthService.isAdmin() || oAuthService.isContableWithItsOwnData(oUsuarioEntity.getId())
                || oAuthService.isAuditorWithItsOwnData(oUsuarioEntity.getId())) {

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
            if (oUsuarioEntity.getPassword() != null) {
                oUsuarioEntityFromDatabase.setPassword(oUsuarioEntity.getPassword());
            }
            if (oUsuarioEntity.getTelefono() != null) {
                oUsuarioEntityFromDatabase.setTelefono(oUsuarioEntity.getTelefono());
            }
            if (oUsuarioEntity.getDireccion() != null) {
                oUsuarioEntityFromDatabase.setDireccion(oUsuarioEntity.getDireccion());
            }
            if (oUsuarioEntity.getTipousuario() != null) {
                oUsuarioEntityFromDatabase.setTipousuario(oUsuarioEntity.getTipousuario());
            }
            return oUsuarioRepository.save(oUsuarioEntityFromDatabase);

        } else {

            throw new UnauthorizedAccessException("No tienes permisos para acceder a esta zona");
        }
    }

    public Long deleteAll() {
        if (oAuthService.isAdmin()) {
            oUsuarioRepository.deleteAll();
            return this.count();
        } else {
            throw new UnauthorizedAccessException("No tienes permisos para acceder a esta zona");
        }
    }

    public Long delete(Long id) {
        if (oAuthService.isAdmin()) {
            oUsuarioRepository.deleteById(id);
            return 1L;
        } else {
            throw new UnauthorizedAccessException("No tienes permisos para acceder a esta zona");
        }
    }

}
