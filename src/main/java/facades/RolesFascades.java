package facades;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import entidades.Role;
import entidades.User_roles;
import repositories.RolesRepository;
import repositories.UserRolesRepository;
import services.RolesService;

@Component
public class RolesFascades {

	@Autowired
	RolesService rolesService;
	@Autowired
	RolesRepository rolesRepository;
	@Autowired
	UserRolesRepository userRolesRepository;
	
	public String añadirRole(String roleName) {
		String salida="";
		Optional<Role> RoleCanFound = rolesService.returnRepeatRole(roleName);
		if(RoleCanFound.isPresent()){
			salida= "Role repetido";
			
		}else {
			Role role = new Role(roleName);
			salida="Rol creado " ;
			rolesRepository.save(role);
		}
		
		
		
		
		return salida;

	}
	
	public String borrarRole(String roleName) {
		String salida="";
		Optional<Role> roleCanFound = rolesService.returnRepeatRole(roleName);
		if(roleCanFound.isPresent()){
			 Role rolFound = roleCanFound.get();
			User_roles relationFound =  userRolesRepository.getUserRoleById(rolFound.getId());
			userRolesRepository.delete(relationFound);
			 rolesRepository.delete(rolFound);
			
		}else {
			 
			salida="Rol no encontrado " ;
			 
		}
		
		
		
		
		return salida;

	}
}
