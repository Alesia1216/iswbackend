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

import net.ausiasmarch.iswart.entity.CompraEntity;
import net.ausiasmarch.iswart.service.CompraService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/compra")
public class Compra {
    
 @Autowired
    CompraService oCompraService;

    @GetMapping("")
    public ResponseEntity<Page<CompraEntity>> getPage(
            Pageable oPageable,
            @RequestParam  Optional<String> filter) {
        return new ResponseEntity<Page<CompraEntity>>(oCompraService.getPage(oPageable, filter), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<CompraEntity> getCompra(@PathVariable Long id) {
        return new ResponseEntity<CompraEntity>(oCompraService.get(id), HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return new ResponseEntity<Long>(oCompraService.count(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        return new ResponseEntity<Long>(oCompraService.delete(id), HttpStatus.OK);
    }

    @PutMapping("/create")
    public ResponseEntity<CompraEntity> create(@RequestBody CompraEntity oCompraEntity) {
        return new ResponseEntity<CompraEntity>(oCompraService.create(oCompraEntity), HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<CompraEntity> update(@RequestBody CompraEntity oCompraEntity) {
        return new ResponseEntity<CompraEntity>(oCompraService.update(oCompraEntity), HttpStatus.OK);
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<Long> deleteAll() {
        return new ResponseEntity<Long>(oCompraService.deleteAll(), HttpStatus.OK);
    }

    //TO DO hacer metodos getusuario y getproducto??


}
