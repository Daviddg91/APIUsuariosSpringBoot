package facades;


import entidades.Clientes;
import entidades.ModifyProductos;
import entidades.Productos;
import repositories.ProductosRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import services.ClientesService;
import services.ProductosService;

import javax.persistence.OrderBy;
import javax.script.ScriptException;
import java.awt.print.Book;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductosFascade {

      @Autowired
     private ProductosService productosService ;
      @Autowired
      private ProductosRepository productosRepository;
      
      public Productos getClienteByISBN(String isbn) {
    	  Productos product = productosService.getProductoByISBN(isbn);
    	  return product;
      }
      public String borrarClienteIsbn(String isbn) {
    	  String salida="";
    	  Optional<Productos> product = productosService.getProductoByISBNOptional(isbn);
    	  if(product.isPresent()) {
    		  Productos productoFound = product.get();
        	  productosRepository.delete(productoFound);
    		  salida ="producto borrado con exito";

    	  }else {
    		  salida ="no se encontro el producto con el isbn:"+isbn;
    		  
    	  }
		return salida;
      }
  /*   @Autowired
     public ProductosFascade(ClientesService clientesService) {

          this.clientesService = clientesService;
     }

 
     public List<Clientes> getAllClientesOrder(String ordenar) {
          return  clientesService.getClientesOrdenar(ordenar);
     }

     public List<Clientes> GetClientesBusqueda(String cadenaBusqueda) {
          return clientesService.buscarClientes(cadenaBusqueda);
     }

     public Clientes getClientesDNI(String dni) {
          Object cliente1 = null;
          Optional cliente = clientesService.getClientesDni(dni);
          if (cliente.isPresent()) {
               return (Clientes) cliente.get();
          }


          return (Clientes) cliente1;
     }
*/
      public String addProduct(Productos productoNew) {
     	 Productos producto =  new Productos();
           String resultado = "";
           producto.setNomenclatura(productoNew.getNomenclatura());
           producto.setNombre(productoNew.getNombre());
           producto.setIsbn(productoNew.getIsbn());
           producto.setDescripcion(productoNew.getDescripcion());
           

             productosService.save(producto);
                 

          
                resultado = "Producto creado con exito";
        
           return resultado;
      }

     public String modificarProducto(ModifyProductos modifyProductos) {
          String resultado;
          Optional<Productos> productoOptional = productosService.getProductoByISBNOptional(modifyProductos.getIsbn());

               if (productoOptional.isPresent()) {
                   Productos productoFound =productoOptional.get();
                    productoFound.setNomenclatura(modifyProductos.getNomenclatura());
                    productoFound.setNombre(modifyProductos.getNombre());
                    productoFound.setIsbn(modifyProductos.getIsbn());
                    productoFound.setDescripcion(modifyProductos.getDescripcion());
                  

                    productosService.save(productoFound);
                    resultado = "El producto " + productoFound.getNombre() +" se ha modificado con exito";

               }else{
                    resultado="No existe el producto  con ese isbn para ser modificado";

               }

          return resultado;

     }
     /*
     public String borrarCliente(String dni) {

          Optional<Clientes> productoOptional = clientesService.getClientesDni(dni);
          String resultado = "";
          if (productoOptional.isPresent()) {
               resultado = "El usuario " + productoOptional.get().getNombre() +" "+ productoOptional.get().getApellidos() + " ha sido borrado";
               clientesService.delete(productoOptional.get());

          } else {
               resultado = "No existe un usuario con ese DNI";

          }
          return resultado;
     }
*/

}