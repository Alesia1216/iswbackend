package net.ausiasmarch.iswart.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import net.ausiasmarch.iswart.entity. LineafacturaEntity;

public interface LineafacturaRepository extends JpaRepository<LineafacturaEntity, Long> {
    
        Page<LineafacturaEntity> findByPrecioContainingOrCantidadContaining(
        String filter1, Pageable pageable
        );
}
