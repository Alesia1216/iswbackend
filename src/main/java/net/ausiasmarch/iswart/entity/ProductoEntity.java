package net.ausiasmarch.iswart.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "producto")
public class ProductoEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Size(min = 3, max = 255)
    private String descripcion;
    
    @NotNull
    @Size(min = 3, max = 255)
    private String estilo;
    
    @NotNull
    private Long unidades;

    @NotNull
    private Double precio;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] imagen;

    private boolean habilitado;
 
    
}
