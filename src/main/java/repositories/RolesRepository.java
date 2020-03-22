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
 
		@Query("select u.roles from User u ")
		 List<Role> findRoles();
		@Query("select r.name from Role r")
		 List<String> findRolesNames();
		
		@Query("select r.name from Role r where name = :role_name")
		 Role findRolesByNames(@Param("role_name") String role_name);

	/*	@Query("delete from User_roles where roles_id= :roleId")
		Role deleteRelation(@Param("roleId") Long long1);*/
}
