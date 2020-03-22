package validators;


 import entidades.Clientes;
import entidades.Role;

import org.springframework.beans.factory.annotation.Autowired;
 import services.ClientesService;
import services.RolesService;
import services.UserServiceImpl;

import javax.validation.ConstraintValidator;
 import javax.validation.ConstraintValidatorContext;

import java.util.List;
import java.util.Optional;

public class UniqueRoleValidator implements ConstraintValidator<UniqueNif, String> {
    @Autowired
    private RolesService rolesService;

    public void initialize(UniqueRole constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Optional<Role> roleOptional =null;
        
        List<String> listRoles =  rolesService.GetRolesNames();
        
        if(listRoles.contains(value)) {
            return false;
        }else{
        	  return true;
        }

        
      
    }
}

