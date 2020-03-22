package repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import entidades.Role;
import entidades.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);
	List<User> findAll();
	
	
	
 
	@Query("select u from User u where u.id = :userId")
	 List<User> findRolesbyUser(@Param("userId") Long idUsuario);
 
	@Query("select u.roles from User u where u.id = :userId")
	 List<Role> findRolesbyUserRoles(@Param("userId") Long idUsuario);
	
	@Query("select u.roles from User u where u.id = :userId")
	 List<Role> InsertRolesToUser(@Param("userId") Long idUsuario);

	@Query("select u from User u where u.id = :userId")
	  Optional<User> findById(@Param("userId") Long idUsuario);
	 
	 
	  
}
