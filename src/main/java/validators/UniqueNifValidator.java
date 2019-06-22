package validators;


 import entidades.Clientes;
 import org.springframework.beans.factory.annotation.Autowired;
 import services.ClientesService;

 import javax.validation.ConstraintValidator;
 import javax.validation.ConstraintValidatorContext;
 import java.util.Optional;

public class UniqueNifValidator implements ConstraintValidator<UniqueNif, String> {
    @Autowired
    private ClientesService clientesService;

    @Override
    public void initialize(UniqueNif constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Optional<Clientes> clienteOptional = clientesService.getClientesDni(value);

        if (clienteOptional.isPresent()) {
           return false;
        }

        return true;
    }
}

