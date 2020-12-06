package entidades;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "productos")
    public class ModifyProductos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @javax.validation.constraints.NotEmpty(message="La nomenclatura  no pueden quedar vacia")

    private String nomenclatura;
    @javax.validation.constraints.NotEmpty (message="El nombre no puede quedar vacio")

    private String nombre;

    
     @javax.validation.constraints.NotEmpty(message="El ISBN no puede quedar vacio")
    private String isbn;
    @javax.validation.constraints.NotEmpty(message="La descripción no puede quedar vacio")
    private String descripcion;




    }



