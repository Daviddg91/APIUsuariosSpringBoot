package repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import entidades.Role;
import entidades.User_roles;

 

@Repository
public interface UserRolesRepository extends JpaRepository<User_roles, Long> {
	 

		@Query("selec ur from User_roles ur where roles_id= :roleId")
		User_roles getUserRoleById(@Param("roleId") Long long1);

		 
}

 
