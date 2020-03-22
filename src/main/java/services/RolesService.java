package services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entidades.Role;
import repositories.RolesRepository;

@Service
public class RolesService {
@Autowired
RolesRepository rolesRepository;

public Optional<Role> returnRepeatRole (String nameRole) {
	Optional<Role> roleFoundOrNot = Optional.empty();
	List<Role> listRoles =   rolesRepository.findAll();
	 System.out.println(nameRole);
    if(!listRoles.isEmpty()) {
       for (int i=0; i<listRoles.size(); i++) {
       	if(listRoles.get(i).getName().contains(nameRole)) {
       		  roleFoundOrNot = Optional.of(listRoles.get(i));
       		break;
       	}
       }
    }
	
	return roleFoundOrNot;
}

public Optional<Role> returnRepeatRoleinUser (Long idUser, String nameRole) {
	Optional<Role> roleFoundOrNot = Optional.empty();
	List<Role> listRoles =   rolesRepository.findAll();
	 System.out.println(nameRole);
    if(!listRoles.isEmpty()) {
       for (int i=0; i<listRoles.size(); i++) {
       	if(listRoles.get(i).getName().contains(nameRole)) {
       		  roleFoundOrNot = Optional.of(listRoles.get(i));
       		break;
       	}
       }
    }
	
	return roleFoundOrNot;
}


	   public List<String> GetRolesNames( ) {
			 List<String> listaRoles = rolesRepository.findRolesNames();
			return listaRoles;
		}
	   public List<Role> GetRoles( ) {
			 List<Role> listaRoles = rolesRepository.findRoles();
			return listaRoles;
		}
}
