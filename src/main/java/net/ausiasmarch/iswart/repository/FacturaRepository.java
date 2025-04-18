package net.ausiasmarch.iswart.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.ausiasmarch.iswart.entity.FacturaEntity;

public interface FacturaRepository extends JpaRepository<FacturaEntity, Long> {
    
        Page<FacturaEntity> findByFechaContaining(
        String filter1, Pageable pageable
        );

    List<FacturaEntity> findByUsuarioId(Long id_usuario);

    @Query("SELECT f FROM FacturaEntity f WHERE f.usuario.id = ?1")
    Page<FacturaEntity> findByUsuarioId(Long id_usuario, Pageable pageable);

}