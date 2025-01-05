package net.ausiasmarch.iswart.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return new ResponseEntity<Long>(oProductoService.count(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        return new ResponseEntity<Long>(oProductoService.delete(id), HttpStatus.OK);
    }

    @PutMapping("/create")
    public ResponseEntity<ProductoEntity> create(@RequestBody ProductoEntity oProductoEntity) {
        return new ResponseEntity<ProductoEntity>(oProductoService.create(oProductoEntity), HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<ProductoEntity> update(@RequestBody ProductoEntity oProductoEntity) {
        return new ResponseEntity<ProductoEntity>(oProductoService.update(oProductoEntity), HttpStatus.OK);
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<Long> deleteAll() {
        return new ResponseEntity<Long>(oProductoService.deleteAll(), HttpStatus.OK);
    }

}
