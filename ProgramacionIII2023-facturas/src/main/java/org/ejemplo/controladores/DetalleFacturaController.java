package org.ejemplo.controladores;

import lombok.extern.slf4j.Slf4j;
import org.ejemplo.exceptions.ClientException;
import org.ejemplo.exceptions.ProductoException;
import org.ejemplo.modelos.DetalleFactura;
import org.ejemplo.modelos.Factura;
import org.ejemplo.modelos.Producto;
import org.ejemplo.servicios.DetalleFacturaService;
import org.ejemplo.servicios.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class DetalleFacturaController {
    @Autowired
    public DetalleFacturaService service;

    @PostMapping("/detalleFactura/create")
    public ResponseEntity<String> createProducto(@RequestBody DetalleFactura detalleFactura){
        try{
            String respuesta = service.guardar (detalleFactura);
            log.info("Producto creado de forma correcta {}", detalleFactura.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
        } catch (ClientException e){
            log.warn("No se esta cumpliendo con las validaciones. Cliente a crear: {}", detalleFactura);
            return ResponseEntity.status(e.getStatusCode()).body(String.format("%s \n %s", e.getMessage(), e.getCausa()));
        } catch (Exception e){
            log.error("Error: ",e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ups!!! Algo salio mal, nuestro desarrolladores estan trabajando para solucionarlo");
        }
    }

    @GetMapping("/detalleFactura/getAll")
    public ResponseEntity<List<DetalleFactura>> getAllProducts(){
        return ResponseEntity.ok(service.retornar());
    }

    @PostMapping("/detalleFactura/update")
    public ResponseEntity<String> updateProducto(@RequestBody DetalleFactura detalleFactura) throws ClientException {
        try{
            String respuesta = service.actualizar (detalleFactura);
            log.info("Producto creado de forma correcta {}", detalleFactura.getId());
            return ResponseEntity.status(HttpStatus.OK).body(respuesta);
        } catch (Exception e){
            log.error("Error: ",e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ups!!! Algo salio mal, nuestro desarrolladores estan trabajando para solucionarlo");
        }
    }
    @DeleteMapping("/detalleFactura/delete/{codigo}")
    public ResponseEntity<String> deleteProducto(@PathVariable(value = "codigo") Integer id){
        try{
            service.borrar (id);
            log.info("Producto borrado de forma correcta {}", id);
            return ResponseEntity.status(HttpStatus.OK).body("Producto Borraddo de Forma Correcta");
        } catch (Exception e){
            log.error("Error: ",e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ups!!! Algo salio mal, nuestro desarrolladores estan trabajando para solucionarlo");
        }
    }
}
