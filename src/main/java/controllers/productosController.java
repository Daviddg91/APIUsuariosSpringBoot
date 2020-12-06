package controllers;


import entidades.Clientes;
import entidades.ModifyClientes;
import entidades.ModifyProductos;
import entidades.Productos;
import facades.ClientesFacade;
import facades.ProductosFascade;
import repositories.ProductosRepository;

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
@RequestMapping("/products")
//@CrossOrigin(origins ={"http://localhost:9090","http://78.30.47.216:9090", "http://78.30.47.216:8080","http://192.168.1.133:8080","http://192.168.1.133:9090"})
public class productosController {

    @Autowired
    ProductosRepository productosRepository;
    @Autowired
    ProductosFascade productosFascade;
    
    @RequestMapping()
    public List<Productos> getAll(){
        return productosRepository.findAll();
    }
    
    @PostMapping
    public String añadirProducto(@RequestBody @Valid Productos producto) {
        return productosFascade.addProduct(producto);
    }
    @RequestMapping(params = "isbnProduct")
    public Productos getClientesDNI(@RequestParam("isbnProduct") String isbnProduct){
        return productosFascade.getClienteByISBN(isbnProduct);
    }

    @PutMapping
    public String modificarCliente(@RequestBody @Valid ModifyProductos modifyProductos){
        return productosFascade.modificarProducto(modifyProductos);
    }
    @RequestMapping(params = "eliminarISBN")
    public String eliminarClienteDNI(@RequestParam("eliminarISBN") String isbn){
        return productosFascade.borrarClienteIsbn(isbn);
}

 


}