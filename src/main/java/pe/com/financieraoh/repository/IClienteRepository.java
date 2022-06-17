package pe.com.financieraoh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.com.financieraoh.model.Cliente;
import pe.com.financieraoh.util.Constants;

import java.util.List;

@Repository
public interface IClienteRepository extends JpaRepository<Cliente, Long> {
    @Query("SELECT c FROM Cliente c WHERE c.estado = " + Constants.ESTADO_ACTIVO)
    List<Cliente> findAll();

    @Query("SELECT c FROM Cliente c WHERE c.idCliente = :idCliente AND c.estado = " + Constants.ESTADO_ACTIVO)
    Cliente findId(@Param("idCliente") Long idCliente);

}
