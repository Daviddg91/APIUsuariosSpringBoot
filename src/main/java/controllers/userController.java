package controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import entidades.Role;
import entidades.User;
import repositories.RolesRepository;
import repositories.UserRepository;
import repositories.User_rolesRepository;

@RestController("")
public class userController {

	@Autowired
	UserRepository userRepository;
	@Autowired
	RolesRepository rolesRepository;
	/*@Autowired
	User_rolesRepository user_rolesRepository;
	*/
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
	   @GetMapping("adminuser/rolesbyUser")
	    List buscarRoles(@RequestParam("userId") Long idUsuario) {
	        List listaRoles =  userRepository.findRolesbyUserRoles(idUsuario);
	        return listaRoles;
	 
	    }
	 /*  @GetMapping("adminuser/rolesbyIDdUser")
	    List buscarRolesusuarios(@RequestParam("userId") Long idUsuario) {
	        List listaRoles =  user_rolesRepository.findRolesbyUserRolesById(idUsuario);
	        return listaRoles;
	 
	    }*/
}
