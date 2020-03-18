package repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import entidades.Role;
 

@Repository
public interface ChangeRoleRepository extends JpaRepository<Role, Long> {

	  Optional<Role> findById(Long id);
	 
}
