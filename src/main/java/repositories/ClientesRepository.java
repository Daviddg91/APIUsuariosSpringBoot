package repositories;

import entidades.Clientes;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ClientesRepository extends CrudRepository<Clientes, Long> {

    @Query("SELECT count(*) FROM Clientes")
    int findAllCountUsers();

    List<Clientes> findAll();
    List<Clientes> findAllByOrderByApellidos();
   List<Clientes> findAllByOrderByNombre();
    List<Clientes> findAllByOrderByCorreo();
    List<Clientes> findAllByOrderByDireccion();
    List<Clientes> findAllByOrderByEdad();

    @Query("Select c from Clientes c where Nombre LIKE CONCAT('%',:busqueda,'%') or Apellidos LIKE CONCAT('%',:busqueda,'%') or DNI = :busqueda")
    List<Clientes> findAllByNombreAndApellidosAndDni2(@Param("busqueda") String busqueda);

    Optional<Clientes> findClientesByDni(String dni);

}