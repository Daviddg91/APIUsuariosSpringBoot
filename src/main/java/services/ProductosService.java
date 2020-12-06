package services;

import entidades.Clientes;
import entidades.Productos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import repositories.ClientesRepository;
import repositories.PageRepository;
import repositories.ProductosRepository;

import java.util.List;
import java.util.Optional;

@Service
public  class ProductosService {

    @Autowired
    ProductosRepository productosRepository;

 
    public Productos save(Productos productos) {
        return productosRepository.save(productos);
    }
    
    public Productos getProductoByISBN (String isbn){
     Optional<Productos> productCanFound =	productosRepository.findByIsbn(isbn);
     	Productos product =null;
    	if(productCanFound.isPresent()) {
    		product =  productCanFound.get();
    		
    	}
    	return product;
    	
    }
    
    public Optional<Productos> getProductoByISBNOptional(String isbn){
        Optional<Productos> productCanFound =	productosRepository.findByIsbn(isbn);
        	 
      
       	return productCanFound;
       	
       }
  /*  @Autowired
    public ProductosService(ClientesRepository clientesRepository){
        this.clientesRepository = clientesRepository;
    }
    public List<Clientes> buscarClientes(String busqueda) {
        List<Clientes> listaClientes = clientesRepository.findAllByNombreAndApellidosAndDni2(busqueda);
        return listaClientes;
    }
 
    
  
  
    public void delete(Clientes cliente) {
        clientesRepository.delete(cliente);
    }
  
*/

}
