package facades;


import entidades.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import services.ClientesService;

import javax.persistence.OrderBy;
import javax.script.ScriptException;
import java.awt.print.Book;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientesFacade {

      @Autowired
     private ClientesService clientesService;


     @Autowired
     public ClientesFacade(ClientesService clientesService) {

          this.clientesService = clientesService;
     }

    /* public List<Clientes> getAllClientes() {
          return clientesService.getClientes();
     }
*/
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

     public String addClient(Clientes clienteNew) {
          Clientes cliente2 = null;
          String resultado = "";

               cliente2 = Clientes
                       .builder()
                       .dni(clienteNew.getDni())
                       .nombre(clienteNew.getNombre())
                       .apellidos(clienteNew.getApellidos())
                       .correo(clienteNew.getCorreo())
                       .direccion(clienteNew.getDireccion())
                       .cp(clienteNew.getCp())
                       .telefono(clienteNew.getTelefono())
                       .edad(clienteNew.getEdad())
                       .build();

               cliente2 = clientesService.save(cliente2);
               clienteNew.setId(cliente2.getId());

          if (cliente2 != null) {
               resultado = "Cliente creado con exito";
          }
          return resultado;
     }
     public String modificarCliente(Clientes clienteNew) {
          String resultado;
          Optional<Clientes> clienteOptional = clientesService.getClientesDni(clienteNew.getDni());

               if (clienteOptional.isPresent()) {
                   Clientes clienteLocal =clienteOptional.get();
                    clienteLocal.setNombre(clienteNew.getNombre());
                    clienteLocal.setApellidos(clienteNew.getApellidos());
                    clienteLocal.setCorreo(clienteNew.getCorreo());
                    clienteLocal.setDireccion(clienteNew.getDireccion());
                    clienteLocal.setCp(clienteNew.getCp());
                    clienteLocal.setTelefono(clienteNew.getTelefono());
                    clienteLocal.setEdad(clienteNew.getEdad());

                    clientesService.save(clienteLocal);
                    resultado = "El cliente " + clienteLocal.getNombre() +" se ha modificado con exito";

               }else{
                    resultado="No existe un cliente con ese dni para ser modificado";

               }

          return resultado;

     }
     public String borrarCliente(String dni) {

          Optional<Clientes> clienteOptional = clientesService.getClientesDni(dni);
          String resultado = "";
          if (clienteOptional.isPresent()) {
               resultado = "El usuario " + clienteOptional.get().getNombre() +" "+ clienteOptional.get().getApellidos() + " ha sido borrado";
               clientesService.delete(clienteOptional.get());

          } else {
               resultado = "Ya existe un usuario con ese DNI";

          }
          return resultado;
     }


}