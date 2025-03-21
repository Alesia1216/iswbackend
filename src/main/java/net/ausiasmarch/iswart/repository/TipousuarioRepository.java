package net.ausiasmarch.iswart.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.ausiasmarch.iswart.entity.TipousuarioEntity;

public interface TipousuarioRepository extends JpaRepository<TipousuarioEntity, Long> {
    
}
