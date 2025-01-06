package net.ausiasmarch.iswart.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.ausiasmarch.iswart.entity.ProductoEntity;

public interface ProductoRepository extends JpaRepository<ProductoEntity, Long>{
    
    Page<ProductoEntity> findByDescripcionContainingOrEstiloContaining(
        String filter1, String filter2, Pageable pageable
    );

    Page<ProductoEntity> findByUnidades(
        Long filter3, Pageable pageable
    );

    Page<ProductoEntity> findByPrecio(
        Double filter3, Pageable pageable
    );
}
