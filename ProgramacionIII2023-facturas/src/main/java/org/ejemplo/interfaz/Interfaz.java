package org.ejemplo.interfaz;

import org.ejemplo.modelos.DetalleFactura;

import java.util.List;

public interface Interfaz<T> {
    T crear(T entidad);
    List<T> obtenerTodos();
    T obtenerPorId(Integer id);
    T actualizar(T entidad);

    String actualizar(DetalleFactura detalleFactura);

    void borrar (Integer id);
}


