package org.ejemplo.validations;

import org.ejemplo.exceptions.ClientException;
import org.ejemplo.exceptions.UserException;
import org.ejemplo.modelos.Cliente;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import static org.ejemplo.utils.Utils.validateStringNotEmptyAndNotNull;

public class ClienteValidations {
    public static List<String> getCamposRepetidos(List<Cliente> usuarios, Cliente cliente){
        List<String> camposRepetidos = new ArrayList<>();
        for(Cliente user: usuarios){
            if (user.getNombre().equals(cliente.getNombre())){
                camposRepetidos.add("Nombre");
            }
            if(user.getCuit().equals(cliente.getCuit())){
                camposRepetidos.add("Cuit");
            }
            if(user.getDni().equals(cliente.getDni())){
                camposRepetidos.add("Dni");
            }
        }
        return camposRepetidos;
    }

    public static void validateUserForRegister(List<Cliente> clientes, Cliente  cliente) throws ClientException {
        if (validateStringNotEmptyAndNotNull(cliente.getNombre())){
            throw new ClientException(HttpStatus.PRECONDITION_FAILED,"Error en el campo nombre", "No se permite valor nulo");
        }

        List<String> camposRepetidos = getCamposRepetidos(clientes, cliente);
        if (!camposRepetidos.isEmpty()){
            throw new ClientException(HttpStatus.PRECONDITION_FAILED, "No se puede ingresar el usuario " + cliente.getNombre(), String.format("Los campos %s ya existen", camposRepetidos));
        }
    }
    }

