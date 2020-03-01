package repositories;

import entidades.Clientes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface PageRepository extends PagingAndSortingRepository<Clientes, Serializable> {

	/*  @Query("Select c from Clientes c where Nombre LIKE CONCAT('%', :buscar ,'%') or Apellidos LIKE CONCAT('%', :buscar ,'%') or DNI = :buscar ")
	    Page<Clientes> findAllByNombreAndApellidosAndDni2(@Param("buscar") String busqueda, Pageable pageable );
	 */
    Page<Clientes> findAll(Pageable pageable);
}