package repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import entidades.Clientes;
import entidades.Role;
 

@Repository
public interface RolesRepository extends JpaRepository<Role, Long> {
		List<Role> findAll();
	  Optional<Role> findById(Long id);
	 
	
	  //@Query("SELECT r FROM Role r join fetch r.role_id  where r.user_id = :userId ")
	 
}
