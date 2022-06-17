package pe.com.financieraoh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.financieraoh.model.Cliente;
import pe.com.financieraoh.repository.IClienteRepository;
import pe.com.financieraoh.service.IClienteService;
import pe.com.financieraoh.util.Constants;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl implements IClienteService {

    @Autowired
    private IClienteRepository repo;

    @Override
    public Cliente registrar(Cliente cliente) throws Exception {
        cliente.setEstado(Constants.ESTADO_ACTIVO);
        return repo.save(cliente);
    }

    @Override
    public Cliente modificar(Cliente cliente) throws Exception {
        return repo.save(cliente);
    }

    @Override
    public List<Cliente> listar() throws Exception {
        return repo.findAll();
    }

    @Override
    public Cliente listarPorId(Integer id) throws Exception {
        return repo.findId(id.longValue());
    }

    @Override
    public void eliminar(Cliente cliente) throws Exception {
        cliente.setEstado(Constants.ESTADO_INACTIVO);
        repo.save(cliente);
    }
}
