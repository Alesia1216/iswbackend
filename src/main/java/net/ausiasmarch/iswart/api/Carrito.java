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

import net.ausiasmarch.iswart.entity.CarritoEntity;
import net.ausiasmarch.iswart.service.CarritoService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/initial/carrito")
public class Carrito {
    @Autowired
    CarritoService oCarritoService;

    @GetMapping("")
    public ResponseEntity<Page<CarritoEntity>> getPage(
            Pageable oPageable,
            @RequestParam  Optional<String> filter) {
        return new ResponseEntity<Page<CarritoEntity>>(oCarritoService.getPage(oPageable, filter), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<CarritoEntity> getCompra(@PathVariable Long id) {
        return new ResponseEntity<CarritoEntity>(oCarritoService.get(id), HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return new ResponseEntity<Long>(oCarritoService.count(), HttpStatus.OK);
    }

    @PutMapping("/create")
    public ResponseEntity<CarritoEntity> create(@RequestBody CarritoEntity oCarritoEntity) {
        return new ResponseEntity<CarritoEntity>(oCarritoService.create(oCarritoEntity), HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<CarritoEntity> update(@RequestBody CarritoEntity oCarritoEntity) {
        return new ResponseEntity<CarritoEntity>(oCarritoService.update(oCarritoEntity), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        return new ResponseEntity<Long>(oCarritoService.delete(id), HttpStatus.OK);
    }

    @DeleteMapping("/deleteAllByUser/{id}")
    public ResponseEntity<Long> deleteAllByUser(@PathVariable Long id) {
        return new ResponseEntity<Long>(oCarritoService.deleteAllByUser(id), HttpStatus.OK);
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<Long> deleteAll() {
        return new ResponseEntity<Long>(oCarritoService.deleteAll(), HttpStatus.OK);
    }

    //TO DO hacer metodos getusuario y getproducto??


}
