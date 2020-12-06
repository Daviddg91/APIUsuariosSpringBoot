package facades;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import entidades.Clientes;
import entidades.Pedidos;
import entidades.Productos;
import repositories.ClientesRepository;
import repositories.PedidosRepository;
import repositories.ProductosRepository;

public class PedidosFascades {
	@Autowired 
	Pedidos pedido;
	@Autowired
	ClientesRepository clientesRepository ;
	@Autowired
	ProductosRepository productosRepository;
	@Autowired
	PedidosRepository pedidosRepository;

		public String  añadirPedido(String dni, String isbn,int unidades) {
			String salida="";
			Optional<Clientes> userCanFound = clientesRepository.findClientesByDni(dni);
			Optional<Productos> productCanFound = productosRepository.findByIsbn(isbn);

			pedido.setCliente(userCanFound.get());
			pedido.setProductos(productCanFound.get());
			pedido.setUnidades(unidades);
			pedidosRepository.save(pedido);
			return salida;
			
			
		}
		
}
