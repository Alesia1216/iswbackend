package net.ausiasmarch.iswart.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import net.ausiasmarch.iswart.api.LineaFactura;
import net.ausiasmarch.iswart.entity. LineafacturaEntity;

public interface LineafacturaRepository extends JpaRepository<LineafacturaEntity, Long> {
    
        Page<LineafacturaEntity> findByPrecioContainingOrCantidadContaining(
        String filter1, String filter2, Pageable pageable
        );

        List<LineafacturaEntity> findByFacturaId(Long facturaId);
}
