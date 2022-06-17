package pe.com.financieraoh.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pe.com.financieraoh.dto.ClienteDTO;
import pe.com.financieraoh.dto.ClienteInputDTO;
import pe.com.financieraoh.exception.ModeloNotFoundException;
import pe.com.financieraoh.model.Cliente;
import pe.com.financieraoh.service.IClienteService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private IClienteService service;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listar() throws Exception{
        List<ClienteDTO> lista = service.listar().stream().map(c -> mapper.map(c, ClienteDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> listarPorId(@PathVariable("id") Integer id) throws Exception{
        ClienteDTO dtoResponse;
        Cliente obj = service.listarPorId(id);

        if(obj == null) {
          throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
        }else {
            dtoResponse = mapper.map(obj, ClienteDTO.class);
        }
        return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> registrar(@Valid @RequestBody ClienteInputDTO dtoRequest) throws Exception{
        Cliente cliente = mapper.map(dtoRequest, Cliente.class);
        cliente = service.registrar(cliente);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getIdCliente().intValue()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<ClienteDTO> modificar(@RequestBody ClienteDTO dtoRequest) throws Exception{
        Cliente cliente = service.listarPorId(dtoRequest.getIdCliente());

        if(cliente == null) {
            throw new ModeloNotFoundException("ID NO ENCONTRADO " + dtoRequest.getIdCliente());
        }

        Cliente obj = mapper.map(dtoRequest, Cliente.class);
        obj.setEstado(cliente.getEstado());

        obj = service.modificar(obj);

        ClienteDTO dtoResponse = mapper.map(obj, ClienteDTO.class);

        return new ResponseEntity<>(dtoResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception{
        Cliente cliente = service.listarPorId(id);

        if(cliente == null) {
            throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
        }

        service.eliminar(cliente);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
