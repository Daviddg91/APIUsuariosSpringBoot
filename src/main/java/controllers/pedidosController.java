package controllers;


import entidades.Pedidos;
import repositories.PedidosRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import services.PedidosServices;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/pedidos")
//@CrossOrigin(origins ={"http://localhost:9090","http://78.30.47.216:9090", "http://78.30.47.216:8080","http://192.168.1.133:8080","http://192.168.1.133:9090"})
public class pedidosController {

    @Autowired
    private PedidosServices pedidosServices;
    @Autowired
    private PedidosRepository pedidosRepository;
 
    
    
 
   @RequestMapping()
   public List<Pedidos> getAll(){
       return pedidosRepository.findAll();
   }


    @PostMapping(params = {"dni","isbn","unidades"})
public String getAllOrder(@RequestParam("dni") String dni,@RequestParam("isbn") String isbn,@RequestParam("unidades") int unidades){

    return pedidosServices.añadirPedido(dni, isbn, unidades);
}
    
 





}