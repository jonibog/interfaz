package org.ejemplo.controladores;

import lombok.extern.slf4j.Slf4j;
import org.ejemplo.exceptions.ClientException;
import org.ejemplo.modelos.DetalleFactura;
import org.ejemplo.modelos.Factura;
import org.ejemplo.servicios.DetalleFacturaService;
import org.ejemplo.servicios.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class FacturaController {
    @Autowired
    public FacturaService service;

    @PostMapping("/factura/create")
    public ResponseEntity<String> create (@RequestBody Factura factura){
        try{
            String respuesta = service.guardar (factura);
            log.info("Producto creado de forma correcta {}", factura);
            return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
        } catch (Exception e){
            log.error("Error: ",e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ups!!! Algo salio mal, nuestro desarrolladores estan trabajando para solucionarlo");
        }
    }

    @GetMapping("/factura/getAll")
    public ResponseEntity<List<Factura>> getAllProducts(){
        return ResponseEntity.ok(service.retornar());
    }

}
