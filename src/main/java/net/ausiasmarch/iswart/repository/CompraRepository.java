package net.ausiasmarch.iswart.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.ausiasmarch.iswart.entity.CompraEntity;

public interface CompraRepository extends JpaRepository<CompraEntity, Long> {
    
}
