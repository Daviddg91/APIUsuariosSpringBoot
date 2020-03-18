package controllers;


import entidades.Clientes;
import entidades.ModifyClientes;
import facades.ClientesFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import services.ClientesService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/users")
//@CrossOrigin(origins ={"http://localhost:9090","http://78.30.47.216:9090", "http://78.30.47.216:8080","http://192.168.1.133:8080","http://192.168.1.133:9090"})
public class clientesController {

    @Autowired
    private ClientesFacade clientesFacade;
    @Autowired
    private ClientesService cleintesService;
    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }
    @PostMapping
    public String añadirCliente(@RequestBody @Valid Clientes cliente) {
        return clientesFacade.addClient(cliente);
    }
    @PutMapping
    public String modificarCliente(@RequestBody @Valid ModifyClientes modifyClientes){
        return clientesFacade.modificarCliente(modifyClientes);
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