package services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import entidades.Clientes;
import entidades.Pedidos;
import entidades.Productos;
import repositories.ClientesRepository;
import repositories.PedidosRepository;
import repositories.ProductosRepository;

@Service 
public class PedidosServices {
  
Pedidos pedido;

@Autowired
ClientesRepository clientesRepository;
@Autowired
ProductosRepository productosRepository;
@Autowired
PedidosRepository pedidosRepository;

	public String  añadirPedido(String dni, String isbn,int unidades) {
		String salida="";
		Optional<Clientes> clienteCanFound = clientesRepository.findClientesByDni(dni);
		Optional<Productos> productCanFound = productosRepository.findByIsbn(isbn);
		Clientes clienteFound =clienteCanFound.get();
		pedido = new Pedidos(); 
		pedido.setCliente(clienteFound);
		pedido.setProductos(productCanFound.get());
		pedido.setUnidades(unidades);
		pedidosRepository.save(pedido);
		salida="Pedido guardado con exito";
		return salida;
		
		
	}
	
	
}
