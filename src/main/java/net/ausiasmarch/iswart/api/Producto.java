package net.ausiasmarch.iswart.api;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.multipart.MultipartFile;

import net.ausiasmarch.iswart.entity.ProductoEntity;
import net.ausiasmarch.iswart.service.ProductoService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/producto")
public class Producto {
     @Autowired
    ProductoService oProductoService;

    @GetMapping("")
    public ResponseEntity<Page<ProductoEntity>> getPage(
            Pageable oPageable,
            @RequestParam  Optional<String> filter) {
        return new ResponseEntity<Page<ProductoEntity>>(oProductoService.getPage(oPageable, filter), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ProductoEntity> getProducto(@PathVariable Long id) {
        return new ResponseEntity<ProductoEntity>(oProductoService.get(id), HttpStatus.OK);
    }

    @GetMapping("/get/{id}/imagen")
    public ResponseEntity<byte[]> getImagen(@PathVariable Long id) {
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                .body(oProductoService.getImagenById(id));
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return new ResponseEntity<Long>(oProductoService.count(), HttpStatus.OK);
    }

    @PutMapping("/create")
    public ResponseEntity<ProductoEntity> create(@RequestBody ProductoEntity oProductoEntity) {
        return new ResponseEntity<ProductoEntity>(oProductoService.create(oProductoEntity), HttpStatus.OK);
    }

    @PutMapping("/createImagen")
    public ResponseEntity<ProductoEntity> create(
            @RequestParam("descripcion") String descripcion,
            @RequestParam("estilo") String estilo,
            @RequestParam("unidades") Long unidades,
            @RequestParam("precio") Double precio,
            @RequestParam("habilitado") boolean habilitado,
            @RequestParam("imagen") MultipartFile imagen) {
        try {
            ProductoEntity oProductoEntity = new ProductoEntity();
            oProductoEntity.setDescripcion(descripcion);
            oProductoEntity.setEstilo(estilo);
            oProductoEntity.setUnidades(unidades);
            oProductoEntity.setPrecio(precio);
            oProductoEntity.setHabilitado(habilitado);
            oProductoEntity.setImagen(imagen.getBytes());
            return new ResponseEntity<ProductoEntity>(oProductoService.create(oProductoEntity), HttpStatus.OK);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PostMapping("/update")
    public ResponseEntity<ProductoEntity> update(@RequestBody ProductoEntity oProductoEntity) {
        return new ResponseEntity<ProductoEntity>(oProductoService.update(oProductoEntity), HttpStatus.OK);
    }

    @PostMapping("/updateImagen")
    public ResponseEntity<ProductoEntity> update(
            @RequestParam("id") Long id,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("estilo") String estilo,
            @RequestParam("unidades") Long unidades,
            @RequestParam("precio") Double precio,
            @RequestParam("habilitado") boolean habilitado,
            @RequestParam("imagen") MultipartFile imagen) {
        try {
            ProductoEntity oProductoEntity = new ProductoEntity();
            oProductoEntity.setId(id);
            oProductoEntity.setDescripcion(descripcion);
            oProductoEntity.setEstilo(estilo);
            oProductoEntity.setUnidades(unidades);
            oProductoEntity.setPrecio(precio);
            oProductoEntity.setHabilitado(habilitado);
            oProductoEntity.setImagen(imagen.getBytes());
            ProductoEntity updatedProducto = oProductoService.update(oProductoEntity);
            return new ResponseEntity<ProductoEntity>(updatedProducto, HttpStatus.OK);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/updatestock")
    public ResponseEntity<ProductoEntity> updateStock(@RequestBody ProductoEntity oProductoEntity) {
        return new ResponseEntity<ProductoEntity>(oProductoService.updateStock(oProductoEntity), HttpStatus.OK);
    }

    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        return new ResponseEntity<Long>(oProductoService.delete(id), HttpStatus.OK);
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<Long> deleteAll() {
        return new ResponseEntity<Long>(oProductoService.deleteAll(), HttpStatus.OK);
    }

}
