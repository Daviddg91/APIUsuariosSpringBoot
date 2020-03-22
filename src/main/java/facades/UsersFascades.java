package facades;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import entidades.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import entidades.Role;
import repositories.UserRepository;
import services.RolesService;
import services.UserServiceImpl;

@Component
public class UsersFascades {
 
 
@Autowired
UserServiceImpl userService;
@Autowired
RolesService rolesService;
@Autowired
UserRepository userRepository;

	public String añadirRolUsuarioCliente(Long idUsuario,String idRole) {
		String salida ="";
		Optional<User> usuario = userService.findById(idUsuario);
		if(usuario.isPresent()) {
			User userFinded = usuario.get();
			
			Optional<Role> canFoundRole = rolesService.returnRepeatRole(idRole);
			Collection<Role> rolesUsuario = userFinded.getRoles();
			if(canFoundRole.isPresent()) {
				rolesUsuario.add(canFoundRole.get());
				
			}else {
				
				Role roleNew = new Role(idRole);
	        	
		       	 
				rolesUsuario.add(roleNew);
				
			}
			if(!rolesUsuario.isEmpty()) {
				
				
				userFinded.setRoles(rolesUsuario);
				salida="Role Adquirido";
				userRepository.save(userFinded);
			}
			
			
		}else {
			salida="Usuario no encontrado";
			
		}
 
		return salida;
	}
	
	
	
}
