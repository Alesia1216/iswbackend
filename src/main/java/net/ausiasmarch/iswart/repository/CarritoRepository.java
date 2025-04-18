package net.ausiasmarch.iswart.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.ausiasmarch.iswart.entity.CarritoEntity;

public interface CarritoRepository extends JpaRepository<CarritoEntity, Long> {
    
        List<CarritoEntity> findByUsuarioId(Long id_usuario);

        @Query("SELECT c FROM CarritoEntity c WHERE c.usuario.id = ?1")
        Page<CarritoEntity> findByUsuarioId(Long id_usuario, Pageable pageable);

}