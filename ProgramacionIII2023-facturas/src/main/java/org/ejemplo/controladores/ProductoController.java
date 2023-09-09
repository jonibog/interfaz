package org.ejemplo.controladores;

import lombok.extern.slf4j.Slf4j;
import org.ejemplo.exceptions.ProductoException;
import org.ejemplo.modelos.Producto;
import org.ejemplo.servicios.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class ProductoController {
    @Autowired
    public ProductoService service;

    @PostMapping("/producto/create")
    public ResponseEntity<String> createProducto(@RequestBody Producto producto){
        try{
            String respuesta = service.guardarProducto (producto);
            log.info("Producto creado de forma correcta {}", producto.getCodigo());
            return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
        } catch (ProductoException e){
            log.warn("No se esta cumpliendo con las validaciones. Producto a crear: {}", producto);
            return ResponseEntity.status(e.getStatusCode()).body(String.format("%s \n %s", e.getMessage(), e.getCausa()));
        } catch (Exception e){
            log.error("Error: ",e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ups!!! Algo salio mal, nuestro desarrolladores estan trabajando para solucionarlo");
        }
    }

    @GetMapping("/producto/getAll")
    public ResponseEntity<List<Producto>> getAllProducts(){
        return ResponseEntity.ok(service.retornarProductos());
    }
    @PostMapping("/producto/update")
    public ResponseEntity<String> updateProducto(@RequestBody Producto producto){
        try{
            String respuesta = service.actualizar (producto);
            log.info("Producto creado de forma correcta {}", producto.getCodigo());
            return ResponseEntity.status(HttpStatus.OK).body(respuesta);
        } catch (ProductoException e){
            log.warn("No se esta cumpliendo con las validaciones. Producto a crear: {}", producto);
            return ResponseEntity.status(e.getStatusCode()).body(String.format("%s \n %s", e.getMessage(), e.getCausa()));
        } catch (Exception e){
            log.error("Error: ",e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ups!!! Algo salio mal, nuestro desarrolladores estan trabajando para solucionarlo");
        }
    }
    @DeleteMapping("/producto/delete/{codigo}")
    public ResponseEntity<String> deleteProducto(@PathVariable(value = "codigo") String codigo){
        try{
            service.borrar (codigo);
            log.info("Producto borrado de forma correcta {}", codigo);
            return ResponseEntity.status(HttpStatus.OK).body("Producto Borraddo de Forma Correcta");
        } catch (ProductoException e){
            log.warn("No se esta cumpliendo con las validaciones. Producto a eliminar: {}", codigo);
            return ResponseEntity.status(e.getStatusCode()).body(String.format("%s \n %s", e.getMessage(), e.getCausa()));
        } catch (Exception e){
            log.error("Error: ",e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ups!!! Algo salio mal, nuestro desarrolladores estan trabajando para solucionarlo");
        }
    }
}
