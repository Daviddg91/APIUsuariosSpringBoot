package dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import validators.Nif;
import validators.UniqueNif;
@Data
public class clientesFileupload {
    private MultipartFile image;
 

    @javax.validation.constraints.NotEmpty (message="El nombre no puede quedar vacio")
    private String nombre;
    @javax.validation.constraints.NotEmpty(message="Los apellidos no pueden quedar vacios")
    private String apellidos;
    @NotNull (message="La edad no puede quedar vacia")
    @Min (value=18, message="La Edad minima para registrarse es de 18 años")
    @Max (value=100, message="La Edad maxima para registrarse es de 100 años")
    private int edad;
    @javax.validation.constraints.NotEmpty(message="La direccion no puede quedar vacio")
    private String direccion;
   
    @UniqueNif(message="El dni esta repetido")
    @Nif(message="El dni no esta regulado")
   // @Size (min=9,max=9, message="Introduzca un dni de 9 digitos ")
    @javax.validation.constraints.NotEmpty(message="El dni no puede quedar vacio")
    private String dni;
    //  @Pattern(regexp="^(0|[1-9][0-9]*)$", message="Introduzca numeros en el Codigo postal")
    @Range(min = 1000,max = 999999, message="Introduzca un Codigo Postal correcto")
    @NotNull (message="El Codigo Postal no puede quedar vacio")
    private Integer cp;
    // @Pattern(regexp = "[0-9]+", message = "holaholaholas")

    
    
   /* @Range(min = 100000000,max = 999999999L, message="Introduzca un Telefono correcto")
    @Max(value = 999999999L, message="El telefono tiene demasiados digitos")*/
    @NotNull (message="El telefono no puede quedar vacio")
    @Min(value = 99999, message="El telefono tiene pocos digitos")
    @Max(value = 999999999, message="El telefono tiene demasiados digitos")

    private String telefono;
    @javax.validation.constraints.Email(message="Escriba un email valido")
    @javax.validation.constraints.NotEmpty(message="El correo no puede quedar vacio")
    private String correo;
}