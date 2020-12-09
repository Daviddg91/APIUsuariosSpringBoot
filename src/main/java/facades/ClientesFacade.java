package facades;


import entidades.Clientes;
import entidades.ModifyClientes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import dto.clientesFileupload;
import services.ClientesService;
import storage.FileSystemStorageService;
import storage.StorageProperties;
import storage.StorageService;

import javax.persistence.OrderBy;
import javax.script.ScriptException;
import java.awt.print.Book;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientesFacade {
	 @Autowired
	 StorageService storageService;
	 @Autowired
	 StorageProperties stoprageProp;
      @Autowired
     private ClientesService clientesService;
       

     @Autowired
     public ClientesFacade(ClientesService clientesService) {

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
     public String addClient(Clientes clienteNew, MultipartFile imagen) {
    	 boolean conimagen=false ;
    	 if(imagen!=null) {
    	 if(!imagen.isEmpty()) {
//    		 Path directorioGuardado = Paths.get("src//main//resources//static/uploads");
//    		 String rutaAbsoluta = directorioGuardado.toFile().getAbsolutePath();
//    		 try {
//				byte[] bytesImg = imagen.getBytes();
//				Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
//				Files.write(rutaCompleta, bytesImg);
//				conimagen = true;
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
    		 stoprageProp.setLocation("src//main//resources//static/uploads");
    		 storageService.store(imagen);
    		 conimagen = true;
    	 }
    		
    		 
    	 }
    	 Clientes cliente2 = null;
         String resultado = "";
         
    	 if (conimagen) {
    		
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
                              .imagen(imagen.getOriginalFilename())
                              .build();

                      cliente2 = clientesService.save(cliente2);
                       
    		 
    	 }else{

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
                     .imagen("")
                     .build();

             cliente2 = clientesService.save(cliente2);
              
    	 }
    	   if (cliente2 != null) {
               resultado = "Cliente creado con exito";
          }
          return resultado;
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
                          .imagen(clienteNew.getImagen())
                          .build();

                  cliente2 = clientesService.save(cliente2);
                  clienteNew.setId(cliente2.getId());
    		 
    /*	 }else {
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
    	 }*/
          if (cliente2 != null) {
               resultado = "Cliente creado con exito";
          }
          return resultado;
     }
     public String modificarCliente(ModifyClientes modifyClientes) {
          String resultado;
          Optional<Clientes> clienteOptional = clientesService.getClientesDni(modifyClientes.getDni());

               if (clienteOptional.isPresent()) {
                   Clientes clienteLocal =clienteOptional.get();
                    clienteLocal.setNombre(modifyClientes.getNombre());
                    clienteLocal.setApellidos(modifyClientes.getApellidos());
                    clienteLocal.setCorreo(modifyClientes.getCorreo());
                    clienteLocal.setDireccion(modifyClientes.getDireccion());
                    clienteLocal.setCp(modifyClientes.getCp());
                    clienteLocal.setTelefono(String.valueOf((modifyClientes.getTelefono())));
                    clienteLocal.setEdad(modifyClientes.getEdad());

                    clientesService.save(clienteLocal);
                    resultado = "El cliente " + clienteLocal.getNombre() +" se ha modificado con exito";

               }else{
                    resultado="No existe un cliente con ese dni para ser modificado";

               }

          return resultado;

     }
     public String modificarClienteImagen(ModifyClientes modifyClientes,MultipartFile imagen ) {
         String resultado;
         Optional<Clientes> clienteOptional = clientesService.getClientesDni(modifyClientes.getDni());
         boolean hayImagen=false;	
         if(imagen!=null && !imagen.isEmpty()) {
         		hayImagen=true;
         	}
              if (clienteOptional.isPresent()) {
                  Clientes clienteLocal =clienteOptional.get();
                   clienteLocal.setNombre(modifyClientes.getNombre());
                   clienteLocal.setApellidos(modifyClientes.getApellidos());
                   clienteLocal.setCorreo(modifyClientes.getCorreo());
                   clienteLocal.setDireccion(modifyClientes.getDireccion());
                   clienteLocal.setCp(modifyClientes.getCp());
                   clienteLocal.setTelefono(String.valueOf((modifyClientes.getTelefono())));
                   clienteLocal.setEdad(modifyClientes.getEdad());

                   if(clienteLocal.getImagen()==null || clienteLocal.getImagen()=="") {
                	   if(hayImagen) {
                		   storageService.store(imagen);
                   		clienteLocal.setImagen(imagen.getOriginalFilename());
                		   
                	   }else {
                		   
                   		clienteLocal.setImagen("");
                	   }
                		
                	   
                   }else {
                	   if(clienteLocal.getImagen()!="" && hayImagen) {
                    	   Path directorioGuardado = Paths.get("src//main//resources//static/uploads");
                    	   storageService.delete(directorioGuardado, clienteLocal.getImagen());

                	   }
                	   if(hayImagen) {
                		   storageService.store(imagen);
                   		clienteLocal.setImagen(imagen.getOriginalFilename());
                		   
                	   }else {
                		   
                   		clienteLocal.setImagen("");
                	   }
                   }
                   
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
               resultado = "No existe un usuario con ese DNI";

          }
          return resultado;
     }


}