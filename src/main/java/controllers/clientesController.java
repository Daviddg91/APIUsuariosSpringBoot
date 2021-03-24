package controllers;


import entidades.Clientes;
import entidades.ModifyClientes;
import facades.ClientesFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import dto.clientesFileupload;
import services.ClientesService;
import storage.FileSystemStorageService;
import storage.StorageProperties;
import storage.StorageService;

import javax.validation.Valid;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@CrossOrigin()
@RestController
@RequestMapping("/users")
//@CrossOrigin(origins ={"http://localhost:9090","http://78.30.47.216:9090", "http://78.30.47.216:8080","http://192.168.1.133:8080","http://192.168.1.133:9090"})
public class clientesController {
@Autowired 
StorageService storageService;
    @Autowired
    private ClientesFacade clientesFacade;
    @Autowired
    private ClientesService cleintesService;
     
    @PostMapping
    public String añadirCliente(@RequestBody @Valid Clientes cliente) {
    	
    	return clientesFacade.addClient(cliente);

    	
    	
    }
    //añadir cliente con imagen 
    @RequestMapping(path = "/addCliente", method = RequestMethod.POST,consumes = {"multipart/form-data"})
    public String añadirCliente(@ModelAttribute @Valid clientesFileupload clienteFileUploadModel , @ModelAttribute @Valid Clientes cliente1) {
    	 
    	MultipartFile imagen = clienteFileUploadModel.getImage();
    	
    	return clientesFacade.addClient(cliente1, imagen);

    	
    	
    }
    @PutMapping(path = "/modifyClient", consumes = {"multipart/form-data"})
    public String modificarClienteImagen(@ModelAttribute @Valid clientesFileupload clienteFileUploadModel, @ModelAttribute @Valid ModifyClientes modifyClientes){
    	MultipartFile imagen = clienteFileUploadModel.getImage();

    	return clientesFacade.modificarClienteImagen(modifyClientes,imagen);
    }
    @PutMapping
    public String modificarCliente(@RequestBody @Valid ModifyClientes modifyClientes){
        return clientesFacade.modificarCliente(modifyClientes);
    }
    @GetMapping("/files/{filename}")
	@ResponseBody
	public String serveFile(@PathVariable String filename) {
    	String fileUrl="";
		 Path directorioGuardado = Paths.get("src//main//resources//static/uploads");
    	storageService.setRootLocation(directorioGuardado);

		Resource file = storageService.loadAsResource(filename);
		
			try {
				  fileUrl =  file.getURI().getRawPath().toString();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return fileUrl;
	
	}
   @RequestMapping()
   public List<Clientes> getAll(){
       return cleintesService.getClientes();
   }
    @RequestMapping(params = "totalElements")
    public Integer getTotalElements(){

        return cleintesService.getClientesTotalElements();
    }




    @RequestMapping(params = "ordenar")
public List<Clientes> getAllOrder(@RequestParam("ordenar") String ordenar){

    return clientesFacade.getAllClientesOrder(ordenar);
}
    

    @RequestMapping(params = {"busqueda", "count"})
    public int getBusquedaClientesCount(@RequestParam("busqueda") String busqueda){
        return clientesFacade.GetClientesBusqueda(busqueda).size();
}
    @RequestMapping(params = "busqueda")
    public List<Clientes> getBusquedaClientes(@RequestParam("busqueda") String busqueda){
        return clientesFacade.GetClientesBusqueda(busqueda);
}

@RequestMapping(params = "eliminarDNI")
    public String eliminarClienteDNI(@RequestParam("eliminarDNI") String eliminarDNI){
        return clientesFacade.borrarCliente(eliminarDNI);
}


    @RequestMapping(params = "dni")
    public Clientes getClientesDNI(@RequestParam("dni") String dni){
        return clientesFacade.getClientesDNI(dni);
    }





}