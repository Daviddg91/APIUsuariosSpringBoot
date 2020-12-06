package facades;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import entidades.Role;
import entidades.User;
import repositories.RolesRepository;
import repositories.UserRepository;
import services.RolesService;

@Component
public class RolesFascades {

	@Autowired
	RolesService rolesService;
	@Autowired
	RolesRepository rolesRepository;
	@Autowired
	UserRepository  userRepository;
	
	
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
	
 
	
	
	public String borrarRoleUser(Long idUser , String roleName) {
		String salida="";
		Optional<User> userSearch = userRepository.findById(idUser);
		Optional<Role> roleCanFound = rolesService.returnRepeatRole(roleName);

		if(userSearch.isPresent()){
			User user = userSearch.get();
			if(roleCanFound.isPresent()){
				 Role rolFound = roleCanFound.get();
				  Collection<Role> listaUserRoles =  user.getRoles();
				  listaUserRoles.remove(rolFound);
				  user.setRoles(listaUserRoles);
				  userRepository.save(user);
				
			}else {
				 
				salida="Rol no encontrado " ;
				 
			}
		}else {
			salida="Usuario no encontrado";
			
		}
		
		return salida;

	}
 
	
public String borrarRolePorCompleto(String roleName) {
	String salida ="";
	Optional<Role> roleCanFound = rolesService.returnRepeatRole(roleName);
	List<User> allUsers = userRepository.findAll();
	
	
		 
		if(roleCanFound.isPresent()){
			 Role roleFound = roleCanFound.get(); 
			for(int i= 0 ; i < allUsers.size(); i++) {
				allUsers.get(i).getRoles().remove(roleFound);
				userRepository.save(allUsers.get(i));
			}
			rolesRepository.delete(roleFound);
			
		}else {
			salida="role no encontrado";
			
		}
	return salida;
	 
}
	
	
}
