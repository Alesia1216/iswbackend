// package net.ausiasmarch.iswart.repository;

// import java.util.List;

// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.Pageable;
// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;

// import net.ausiasmarch.iswart.entity.CompraEntity;

// public interface CompraRepository extends JpaRepository<CompraEntity, Long> {
    
//     List<CompraEntity> findByUsuarioId(Long id_usuario);

//     @Query("SELECT c FROM CompraEntity c WHERE c.usuario.id = ?1")
//     Page<CompraEntity> findByUsuarioId(Long id_usuario, Pageable pageable);


// }
