package facades;


import entidades.Clientes;
import entidades.ModifyClientes;

import org.apache.commons.io.FilenameUtils;
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
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
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
         String nombreImagenRepeat = null;
          
    	 String imagenFileName = null;
		imagenFileName = imagen.getOriginalFilename();
		
	    if(imagen!=null && !imagen.isEmpty()) {
     		hayImagen=true;
     	} 
	    boolean userReplaceFileRepeat = false;
	    boolean fileExist = false;
	    boolean fileRepeat = false;
			if(hayImagen) {
				fileExist = storageService.searchFile(storageService.getDirectorySave(),imagenFileName);
		    	 List<Clientes> clienteRepeatFile = clientesService.getClientesByFile(imagenFileName);
		    	 if(clienteRepeatFile.size()>1) {
						userReplaceFileRepeat  = true;
		    	 }
					if(fileExist && !userReplaceFileRepeat) {
						fileRepeat = true;
					}
				if(fileRepeat) {
						
				  String currentDate = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
					  String fileNewString = null;
					try {
						fileNewString = imagen.getOriginalFilename().replace(imagen.getOriginalFilename(), FilenameUtils.getBaseName(imagen.getOriginalFilename()).concat(currentDate) + "." + FilenameUtils.getExtension(imagen.getOriginalFilename())).toLowerCase();
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				       
				        String insPath = storageService.getDirectorySave() + fileNewString;
				        nombreImagenRepeat = fileNewString;
				        Path destinationFile = storageService.getDirectorySave().resolve(
								Paths.get(fileNewString))
								.normalize().toAbsolutePath();
				        
				        InputStream inputStream;
						try {
							inputStream = imagen.getInputStream();
							Files.copy(inputStream, destinationFile,
									StandardCopyOption.REPLACE_EXISTING);
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				
			} 
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
                		   Path rootLocation = Paths.get("src//main//resources//static/uploads");
                		storageService.setRootLocation(rootLocation);
                		   if(fileRepeat) {
                			   storageService.store(imagen);
                          		clienteLocal.setImagen(nombreImagenRepeat);
                			   
                		   }else {
                			   storageService.store(imagen);
                          		clienteLocal.setImagen(imagen.getOriginalFilename());
                			   
                		   }
                	   }else {
                		   
                   		clienteLocal.setImagen("");
                	   }
                		
                	   
                   }else {
                	   if(clienteLocal.getImagen()!="" && hayImagen) {
                    	 //  Path directorioGuardado = Paths.get("src//main//resources//static/uploads");
                    	  // storageService.delete(directorioGuardado, clienteLocal.getImagen());

                	   }
                	   if(hayImagen) {
                			   if(fileRepeat) {
                    			   storageService.delete(storageService.getDirectorySave(), clienteLocal.getImagen());
                    			   storageService.store(imagen);
                              		clienteLocal.setImagen(nombreImagenRepeat);
                    			   
                    		   }else {
                    			   storageService.store(imagen);
                              		clienteLocal.setImagen(imagen.getOriginalFilename());
                    			   
                    		   }
                			   
                		 
                		   
                	   }else {
                		   if(clienteLocal.getImagen()!= null || clienteLocal.getImagen()!= "") {
                			   clienteLocal.setImagen(clienteLocal.getImagen());
                		   }else {
                   		clienteLocal.setImagen("");
                		   }
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