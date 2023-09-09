package org.ejemplo.servicios;

import lombok.extern.slf4j.Slf4j;
import org.ejemplo.exceptions.ClientException;
import org.ejemplo.exceptions.UserException;
import org.ejemplo.interfaz.Interfaz;
import org.ejemplo.modelos.Cliente;
import org.ejemplo.repository.ClienteRepository;
import org.ejemplo.validations.ClienteValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ClienteService {
    @Autowired
    ClienteRepository repository;

    public String guardarCliente(Cliente cliente) throws ClientException {
        ClienteValidations.validateUserForRegister(repository.findAll(), cliente);
        repository.save(cliente);
        return "usuario cargado correctamente";
    }

    public List<Cliente> retornarUsuarios() {
        return repository.findAll();
    }

    public void borrarUsuarios(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
    }
}

