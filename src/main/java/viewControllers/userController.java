package viewControllers;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import entidades.ModifyClientes;
import entidades.Role;
import entidades.User;
import facades.RolesFascades;
import facades.UsersFascades;
import repositories.RolesRepository;
import repositories.UserRepository;
 

@RestController("")
public class userController {

	@Autowired
	UserRepository userRepository;
	@Autowired
	RolesRepository rolesRepository;
	 @Autowired
	 UsersFascades usersFascades;
	 @Autowired
	 RolesFascades rolesFascades;
	 
	   @GetMapping("adminuser/usuarios")
	    List<User> listaUsuarios() {
		   
		   List<User> listaUsuarios = userRepository.findAll();
		   return  listaUsuarios;
				    	 
	    }
	   @GetMapping("adminuser/roles")
	    List<Role> listaRoles() {
		   
		   List<Role> listaRoles = rolesRepository.findAll();
		   return  listaRoles;
				    	 
	    }
	   @GetMapping("userLogin/userById")
	    User buscarUsuariosPorEmail(@RequestParam("email") String email) {
	        User user =  userRepository.findByEmail(email);
	        return user;
	    }
	   
	   @GetMapping("adminuser/byUser")
	    List buscarUsuarios(@RequestParam("userId") Long idUsuario) {
	        List listaRoles =  userRepository.findRolesbyUser(idUsuario);
	        return listaRoles;
	 
	    }
	   @GetMapping("adminuser/RolesbyUser")
	    List buscarRoles(@RequestParam("userId") Long idUsuario) {
	        List listaRoles =  userRepository.findRolesbyUserRoles(idUsuario);
	        return listaRoles;
	 
	    }
	   @GetMapping("adminuser/AñadirRolesUser")
	    String añadirRolesUsuarios(@RequestParam("userId") Long idUsuario,@RequestParam("roleName") String roleName) {
		   return usersFascades.añadirRolUsuarioCliente(idUsuario, roleName);
	 
	    }
	  @GetMapping("adminuser/AñadirNewRoles")
	    String añadirRoles(@RequestParam("roleName") String roleName) {
	        String listaRoles =  rolesFascades.añadirRole(roleName);
	       
	        
	        return listaRoles;
	 
	    }
	  @GetMapping("adminuser/borrarRoleaUsuario")
	    String borrarRoleUsuario(@RequestParam("roleName") String roleName,@RequestParam("idUser") Long idUser) {
	        String listaRoles =  rolesFascades.borrarRoleUser(idUser,roleName);
	       
	        
	        return listaRoles;
	 
	    }
	  @GetMapping("adminuser/borrarRolePorCompleto")
	    String borrarRolePorCompleto(@RequestParam("roleName") String roleName) {
	        String listaRoles =  rolesFascades.borrarRolePorCompleto(roleName);
	       
	        return listaRoles;
	 
	    }
	  
	/*   @PutMapping
	    public String modificarCliente(@RequestParam("userId") Long idUsuario,@RequestParam("roleId") Long idRole ){
	        return clientesFacade.añadirRolUsuarioCliente(idUsuario,idRole);
	    }*/
}
