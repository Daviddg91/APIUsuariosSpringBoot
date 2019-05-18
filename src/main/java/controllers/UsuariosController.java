package controllers;


import entidades.Clientes;
import facades.ClientesFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/users")
public class UsuariosController {

    @Autowired
    private ClientesFacade clientesFacade;

    @PostMapping
    public String añadirCliente(@RequestBody @Valid Clientes cliente) {
        return clientesFacade.addClient(cliente);
    }
    @PutMapping
    public String modificarCliente(@RequestBody @Valid Clientes cliente){
        return clientesFacade.modificarCliente(cliente);
    }
@RequestMapping
public List<Clientes> getAll(){

    return clientesFacade.getAllClientes();
}@RequestMapping(params = "ordenar")
public List<Clientes> getAllOrder(@RequestParam("ordenar") String ordenar){

    return clientesFacade.getAllClientesOrder(ordenar);
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