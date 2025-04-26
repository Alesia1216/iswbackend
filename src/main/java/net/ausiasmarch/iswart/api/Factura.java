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

import net.ausiasmarch.iswart.entity.FacturaEntity;
import net.ausiasmarch.iswart.service.FacturaService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/factura")
public class Factura {
    @Autowired
    FacturaService oFacturaService;

    @GetMapping("")
    public ResponseEntity<Page<FacturaEntity>> getPage(
            Pageable oPageable,
            @RequestParam  Optional<String> filter) {
        return new ResponseEntity<Page<FacturaEntity>>(oFacturaService.getPage(oPageable, filter), HttpStatus.OK);
    }

    // @GetMapping("getHistorial/{id}")
    // public ResponseEntity<Page<FacturaEntity>> getHistorial(
    //         Pageable oPageable, @PathVariable Long id,
    //         @RequestParam Optional<String> filter) {
    //     return new ResponseEntity<>(oFacturaService.getHistorial(oPageable, id, filter), HttpStatus.OK);
    // }

    @GetMapping("/get/{id}")
    public ResponseEntity<FacturaEntity> getCompra(@PathVariable Long id) {
        return new ResponseEntity<FacturaEntity>(oFacturaService.get(id), HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return new ResponseEntity<Long>(oFacturaService.count(), HttpStatus.OK);
    }

    @PutMapping("/create")
    public ResponseEntity<FacturaEntity> create(@RequestBody FacturaEntity oFacturaEntity) {
        return new ResponseEntity<FacturaEntity>(oFacturaService.create(oFacturaEntity), HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<FacturaEntity> update(@RequestBody FacturaEntity oFacturaEntity) {
        return new ResponseEntity<FacturaEntity>(oFacturaService.update(oFacturaEntity), HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        return new ResponseEntity<Long>(oFacturaService.delete(id), HttpStatus.OK);
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<Long> deleteAll() {
        return new ResponseEntity<Long>(oFacturaService.deleteAll(), HttpStatus.OK);
    }

    //TO DO hacer metodos getusuario y getproducto??


}

