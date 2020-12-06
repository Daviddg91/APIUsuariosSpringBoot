package entidades;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Clientes_productosPedidos")
public class Pedidos {

	   @Id
	   @GeneratedValue(strategy = GenerationType.AUTO)
	   private Long idPedido;
	   @ManyToOne
		 @JoinColumn(name = "isbn")	    private   Productos productos;
	 @ManyToOne
	 @JoinColumn(name = "dni")
	   private  Clientes cliente;
	  private int unidades;
	public Productos getProductos() {
		return productos;
	}
	public void setProductos(Productos productos) {
		this.productos = productos;
	}
	public Clientes getCliente() {
		return cliente;
	}
	public void setCliente(Clientes cliente) {
		this.cliente = cliente;
	}
	public int getUnidades() {
		return unidades;
	}
	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}
 
	  
}
