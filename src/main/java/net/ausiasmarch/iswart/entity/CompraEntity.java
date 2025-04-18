// package net.ausiasmarch.iswart.entity;

// import jakarta.persistence.*;
// import lombok.AllArgsConstructor;
// import lombok.Getter;
// import lombok.NoArgsConstructor;
// import lombok.Setter;
// import java.time.OffsetDateTime;


// @Entity
// @Getter
// @Setter
// @AllArgsConstructor
// @NoArgsConstructor
// @Table(name = "compra")
// public class CompraEntity {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @Temporal(TemporalType.TIMESTAMP)
//     @Column(nullable = false)
//     private OffsetDateTime fecha;

//     @ManyToOne(fetch = jakarta.persistence.FetchType.EAGER)
//     @JoinColumn(name = "id_usuario")
//     private UsuarioEntity usuario;

//     @ManyToOne(fetch = jakarta.persistence.FetchType.EAGER)
//     @JoinColumn(name = "id_producto")
//     private ProductoEntity producto;
    
// }
