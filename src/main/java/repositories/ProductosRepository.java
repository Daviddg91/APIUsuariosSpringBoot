package repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import entidades.Productos;
import entidades.Role;
import entidades.User;

@Repository
public interface ProductosRepository extends JpaRepository<Productos, Long> {
 
	
	@Query("select u from Productos u where u.isbn = :isbn")
	  Optional<Productos> findByIsbn(@Param("isbn") String isbn);
	  
}
