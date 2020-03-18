package repositories;

import java.util.List;
import java.util.Set;

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
	
	
	
/*
	 @Query("SELECT usr,ru,rol FROM User usr,User_roles ru,Role rol where rol.id=ru.roles_id AND usr.id.id = :userId ")
	  List<Object> findRolesbyUserRoles(@Param("userId") Long idUsuario);
*/
/*	@Query("SELECT r FROM Role r join fetch r.role_id  where r.user_id = :userId ")
	  List findRolesbyUserRoles(@Param("userId") Long idUsuario);*/
	/*@Query("select u from User u left join fetch u.roles r  where r.user_id = :userId ")
	  List findRolesbyUserRoles(@Param("userId") Long idUsuario);
	*/
	/*@Query("select u from User u left join fetch u.roles r where u.id = :userId ")
	  List findRolesbyUserRoles(@Param("userId") Long idUsuario);
	*/
	
	//@Query("select u from User u left join fetch u.roles r left join fetch u.user_roles ")
	 
	// @Query("select u,r from User u, Role r left join fetch u.user_roles sr left join fetch r.roles ")
	 
//	 @Query("select u from User u, Users_roles ur left join fetch ur.id where ur.role_id=u.roles.id")

/*	@Query("select u,ur from User u,Role r,  Users_roles ur left join fetch ur.id where ur.role_id=r.id")
	
	 List findRolesbyUserRoles(@Param("userId") Long idUsuario);
  */
	@Query("select u from User u ")
	 List findRolesbyUserRoles(@Param("userId") Long idUsuario);
 
 
}
