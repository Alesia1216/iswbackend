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

import net.ausiasmarch.iswart.entity.LineafacturaEntity;
import net.ausiasmarch.iswart.service.LineafacturaService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("/lineafactura")
public class LineaFactura {
    
    @Autowired
    LineafacturaService oLineafacturaService;

    @GetMapping("")
    public ResponseEntity<Page<LineafacturaEntity>> getPage(
            Pageable oPageable,
            @RequestParam  Optional<String> filter) {
        return new ResponseEntity<Page<LineafacturaEntity>>(oLineafacturaService.getPage(oPageable, filter), HttpStatus.OK);
    }

    // @GetMapping("getHistorial/{id}")
    // public ResponseEntity<Page<LineafacturaEntity>> getHistorial(
    //         Pageable oPageable, @PathVariable Long id,
    //         @RequestParam Optional<String> filter) {
    //     return new ResponseEntity<>(oLineafacturaService.getHistorial(oPageable, id, filter), HttpStatus.OK);
    // }

    @GetMapping("/get/{id}")
    public ResponseEntity<LineafacturaEntity> getCompra(@PathVariable Long id) {
        return new ResponseEntity<LineafacturaEntity>(oLineafacturaService.get(id), HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return new ResponseEntity<Long>(oLineafacturaService.count(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        return new ResponseEntity<Long>(oLineafacturaService.delete(id), HttpStatus.OK);
    }

    @PutMapping("/create")
    public ResponseEntity<LineafacturaEntity> create(@RequestBody LineafacturaEntity oLineafacturaEntity) {
        return new ResponseEntity<LineafacturaEntity>(oLineafacturaService.create(oLineafacturaEntity), HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<LineafacturaEntity> update(@RequestBody LineafacturaEntity oLineafacturaEntity) {
        return new ResponseEntity<LineafacturaEntity>(oLineafacturaService.update(oLineafacturaEntity), HttpStatus.OK);
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<Long> deleteAll() {
        return new ResponseEntity<Long>(oLineafacturaService.deleteAll(), HttpStatus.OK);
    }

    //TO DO hacer metodos getusuario y getproducto??


}