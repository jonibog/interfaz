package org.ejemplo.validations;

import org.ejemplo.exceptions.UserException;
import org.ejemplo.modelos.Usuario;
import org.ejemplo.utils.Utils;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.util.List;

import static org.ejemplo.utils.Utils.validateStringNotEmptyAndNotNull;

public class UserValidations {
    public static Boolean validateExistUser(List<Usuario> usuarios, String username){
        for(Usuario user: usuarios){
            if (user.getUser().equals(username)){
                return true;
            }
        }
        return false;
    }

    public static void validateUserForRegister(List<Usuario> usuarios, Usuario usuario) throws UserException {
        if (validateStringNotEmptyAndNotNull(usuario.getUser())){
            throw new UserException(HttpStatus.PRECONDITION_FAILED,"Error en el campo usuario", "No se permite valor nulo");
        }

        if(validateExistUser(usuarios, usuario.getUser())){
            throw new UserException(HttpStatus.PRECONDITION_FAILED, "No se puede ingresar el usuario " + usuario.getUser(), "El usuario ya se encuentra registrado");
        }

        if (validateStringNotEmptyAndNotNull(usuario.getRole())
        || (!usuario.getRole().equalsIgnoreCase("administrador")
        && !usuario.getRole().equalsIgnoreCase("vendedor"))){
            throw new UserException(HttpStatus.PRECONDITION_FAILED, "No se puede ingresar el usuario " + usuario.getUser(), "Porque el rol es incorrecto");
        }
    }


    }


