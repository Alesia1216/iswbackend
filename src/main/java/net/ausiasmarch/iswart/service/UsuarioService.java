package net.ausiasmarch.iswart.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import net.ausiasmarch.iswart.entity.UsuarioEntity;
import net.ausiasmarch.iswart.exception.ExistingEntityException;
import net.ausiasmarch.iswart.exception.NotAcceptableException;
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

        if (filter.isPresent()) {
            if (oAuthService.isAdmin()) {
                return oUsuarioRepository
                        .findByNombreContainingOrApellido1ContainingOrApellido2ContainingOrEmailContainingOrDireccionContaining(
                                filter.get(), filter.get(), filter.get(), filter.get(), filter.get(),
                                oPageable);
            } else {
                throw new UnauthorizedAccessException("Disculpa, no tienes permisos para acceder a esta zona");
            }
        } else {
            if (oAuthService.isAdmin()) {
                return oUsuarioRepository.findAll(oPageable);
            } else {
                throw new UnauthorizedAccessException("Disculpa, no tienes permisos para acceder a esta zona");
            }
        }

    }

    public UsuarioEntity get(Long id) {

        UsuarioEntity usuario = oUsuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(" Disculpa, no hay ningun usuario con el id" + id));

        if (oAuthService.isClientWithItsOwnData(id) || oAuthService.isAdmin()) {
            return usuario;
        } else {
            throw new UnauthorizedAccessException("Disculpa, no tienes permisos para acceder a esta zona");
        }
    }

    public UsuarioEntity getByEmail(String email) {

        UsuarioEntity usuario = oUsuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException(" Disculpa, no hay ningun usuario con el email" + email));

        if (oAuthService.isClientWithItsOwnData(usuario.getId()) || oAuthService.isAdmin()) {
            return usuario;
        } else {
            throw new UnauthorizedAccessException("Disculpa, no tienes permisos para acceder a esta zona");
        }
    }

    public Long count() {
        if (oAuthService.isAdmin()) {
            return oUsuarioRepository.count();
        } else {
            throw new UnauthorizedAccessException("Disculpa, no tienes permisos para acceder a esta zona");
        }
    }

    public UsuarioEntity create(UsuarioEntity oUsuarioEntity) {
        if (oUsuarioRepository.findByEmail(oUsuarioEntity.getEmail()).isPresent()) {
            throw new ExistingEntityException("Ya existe un usuario con el email " + oUsuarioEntity.getEmail());
        } else {
            return oUsuarioRepository.save(oUsuarioEntity);
        }
        // if (oAuthService.isAdmin()) {

        // } else {
        // throw new UnauthorizedAccessException("Disculpa, no tienes permisos para
        // acceder a esta
        // zona");
        // }
    }

    public UsuarioEntity update(UsuarioEntity oUsuarioEntity) {

        if (oAuthService.isAdmin() || oAuthService.isClientWithItsOwnData(oUsuarioEntity.getId())) {

            UsuarioEntity oUsuarioEntityFromDatabase = oUsuarioRepository.findById(oUsuarioEntity.getId()).orElseThrow(
                    () -> new EntityNotFoundException("Usuario no encontrado"));

            // Verifica si el nuevo email ya existe y no es el mismo del usuario actual
            if (!oUsuarioEntityFromDatabase.getEmail().equals(oUsuarioEntity.getEmail()) &&
                    oUsuarioRepository.findByEmail(oUsuarioEntity.getEmail()).isPresent()) {
                throw new ExistingEntityException("Ya existe un usuario con el email " + oUsuarioEntity.getEmail());
            } else {
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
            }
        } else {

            throw new UnauthorizedAccessException("Disculpa, no tienes permisos para acceder a esta zona");
        }
    }

    public UsuarioEntity changePassword(long userId, String oldPassword, String newPassword) {
        UsuarioEntity oUsuarioEntityFromDatabase = oUsuarioRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("Usuario no encontrado"));

        if (oAuthService.isAdmin() || oAuthService.isClientWithItsOwnData(oUsuarioEntityFromDatabase.getId())) {

            // Compara hash con hash
            if (!oUsuarioEntityFromDatabase.getPassword().equals(oldPassword)) {
                throw new NotAcceptableException("La contraseña actual no es correcta");
            }

            oUsuarioEntityFromDatabase.setPassword(newPassword); // Ya viene con hash desde el frontend
            return oUsuarioRepository.save(oUsuarioEntityFromDatabase);
        } else {
            throw new UnauthorizedAccessException("Disculpa, no tienes permisos para acceder a esta zona");
        }
    }

    public Long deleteAll() {
        if (oAuthService.isAdmin()) {
            oUsuarioRepository.deleteAll();
            return this.count();
        } else {
            throw new UnauthorizedAccessException("Disculpa, no tienes permisos para acceder a esta zona");
        }
    }

    public Long delete(Long id) {
        if (oAuthService.isAdmin()) {
            oUsuarioRepository.deleteById(id);
            return 1L;
        } else {
            throw new UnauthorizedAccessException("Disculpa, no tienes permisos para acceder a esta zona");
        }
    }

}
