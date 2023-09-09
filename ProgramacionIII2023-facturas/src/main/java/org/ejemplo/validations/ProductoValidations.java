package org.ejemplo.validations;

import org.ejemplo.exceptions.ProductoException;
import org.ejemplo.exceptions.UserException;
import org.ejemplo.modelos.Producto;
import org.ejemplo.modelos.Usuario;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.ejemplo.utils.Utils.validateStringNotEmptyAndNotNull;

public class ProductoValidations {
    public static Boolean validateExistProduct(List<Producto> productos, String codigo){
        for(Producto producto: productos){
            if (producto.getCodigo().equals(codigo)){
                return true;
            }
        }
        return false;
    }

    public static void validateProductoForCreate(List<Producto> productos, Producto producto) throws ProductoException {
        if (validateStringNotEmptyAndNotNull(producto.getCodigo())){
            throw new ProductoException(HttpStatus.PRECONDITION_FAILED,"Error en el campo Codigo", "Es el identificador del producto, no puede ser nulo");
        }
        if (validateStringNotEmptyAndNotNull(producto.getNombre())){
            throw new ProductoException(HttpStatus.PRECONDITION_FAILED,"Error en el campo Nombre", "No se permite valor nulo");
        }
        if (producto.getPrecio()<0){
            throw new ProductoException(HttpStatus.PRECONDITION_FAILED,"Error en el campo Precio", "No se permite valor menor a cero");
        }
        if(validateExistProduct(productos, producto.getCodigo())){
            throw new ProductoException(HttpStatus.PRECONDITION_FAILED, "No se puede ingresar el producto " + producto.getCodigo(), "El producto ya se encuentra registrado");
        }
    }

    public static void validateProductoForUpdate(List<Producto> productos, Producto producto) throws ProductoException {
        if(!validateExistProduct(productos, producto.getCodigo())){
            throw new ProductoException(HttpStatus.PRECONDITION_FAILED, "No se puede actualizar el producto " + producto.getCodigo(), "El producto no se encuentra registrado");
        }
        if (validateStringNotEmptyAndNotNull(producto.getCodigo())){
            throw new ProductoException(HttpStatus.PRECONDITION_FAILED,"Error en el campo Codigo", "Es el identificador del producto, no puede ser nulo");
        }
        if (validateStringNotEmptyAndNotNull(producto.getNombre())){
            throw new ProductoException(HttpStatus.PRECONDITION_FAILED,"Error en el campo Nombre", "No se permite valor nulo");
        }
        if (producto.getPrecio()<0){
            throw new ProductoException(HttpStatus.PRECONDITION_FAILED,"Error en el campo Precio", "No se permite valor menor a cero");
        }
    }

    public static void validateProductoForDelete(List<Producto> productos, String codigo) throws ProductoException {
        if(!validateExistProduct(productos, codigo)){
            throw new ProductoException(HttpStatus.PRECONDITION_FAILED, "No se puede eliminar el producto " + codigo, "El producto no se encuentra registrado");
        }
    }
    }

