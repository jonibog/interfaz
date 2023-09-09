package org.ejemplo.validations;

import org.ejemplo.exceptions.ClientException;
import org.ejemplo.modelos.Cliente;
import org.ejemplo.modelos.DetalleFactura;
import org.ejemplo.modelos.Producto;
import org.ejemplo.repository.ProductoRepository;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DetalleFacturaValidations {
    public static boolean existDetalle(List<DetalleFactura> detalles, DetalleFactura detalle){
        for(DetalleFactura d: detalles){
            if (d.getId().equals(detalle.getId())){
                return true;
            }
        }
        return false;
    }

    public static void validateForCreate(ProductoRepository productoRepository, List<DetalleFactura> detalles, DetalleFactura  detalleFactura) throws ClientException {
        if (existDetalle(detalles, detalleFactura)){
            throw new ClientException(HttpStatus.PRECONDITION_FAILED, "Error al crear el Detalle", "El id del detalle ya existe");
        }
        validateDetalle(productoRepository, detalleFactura);
    }

    public static void validateForUpdate(ProductoRepository productoRepository, List<DetalleFactura> detalles, DetalleFactura  detalleFactura) throws ClientException {

        if (!existDetalle(detalles, detalleFactura)){
            throw new ClientException(HttpStatus.PRECONDITION_FAILED, "Error al actualizar el Detalle", "El id del detalle no existe");
        }

        validateDetalle(productoRepository, detalleFactura);
    }

    private static void validateDetalle(ProductoRepository productoRepository, DetalleFactura detalleFactura) throws ClientException {
        if (detalleFactura.getProducto() == null){
            throw new ClientException(HttpStatus.PRECONDITION_FAILED, "No se puede ingresar el Detalle ", "No contiene un Producto");
        }
        if (detalleFactura.getCantidad() <= 0){
            throw new ClientException(HttpStatus.PRECONDITION_FAILED, "No se puede ingresar el Detalle ", "La cantidad debe ser mayor  a 0");
        }
        Optional<Producto> optionalProducto = productoRepository.findById(detalleFactura.getProducto().getCodigo());
        if (optionalProducto.isEmpty()){
            throw new ClientException(HttpStatus.PRECONDITION_FAILED, "No se puede ingresar el Detalle ", "El producto especificado no existe");
        }
        Producto producto = optionalProducto.get();
        if (detalleFactura.getCantidad() > producto.getStock()){
            throw new ClientException(HttpStatus.PRECONDITION_FAILED, "No se puede ingresar el Detalle ", String.format("La cantidad no puede ser mayo al stock (%s)",producto.getStock()));
        }
    }
}

