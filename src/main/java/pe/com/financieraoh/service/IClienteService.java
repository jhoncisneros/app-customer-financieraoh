package pe.com.financieraoh.service;

import pe.com.financieraoh.model.Cliente;

import java.util.List;

public interface IClienteService {

    Cliente registrar(Cliente cliente) throws Exception;

    Cliente modificar(Cliente cliente) throws Exception;

    List<Cliente> listar() throws Exception;

    Cliente listarPorId(Integer id) throws Exception;

    void eliminar(Cliente id) throws Exception;

}
