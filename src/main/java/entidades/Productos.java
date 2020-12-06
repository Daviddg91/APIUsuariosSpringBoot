package entidades;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
 import validators.Nif;
import validators.UniqueNif;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Productos")
    public class Productos {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
        @javax.validation.constraints.NotEmpty(message="La nomenclatura  no pueden quedar vacia")

        private String nomenclatura;
        @javax.validation.constraints.NotEmpty (message="El nombre no puede quedar vacio")

        private String nombre;
 
        @Column(unique=true)
         @javax.validation.constraints.NotEmpty(message="El ISBN no puede quedar vacio")
        private String isbn;
        @javax.validation.constraints.NotEmpty(message="La descripción no puede quedar vacio")
        private String descripcion;




    }



