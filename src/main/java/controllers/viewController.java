package controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import entidades.Clientes;
import services.ClientesService;
import services.ServiciosBeans;

@Controller()
class viewController {

	@Autowired
	ServiciosBeans serviciosBeans;
	@Autowired
	ClientesService clientService;
	
    @GetMapping("/welcome")
    String index(Model model) {
        model.addAttribute("now", LocalDateTime.now());
        return "index";
    }
    @GetMapping("/saludo")
    String indexSaludo(Model model2) {
    	model2.addAttribute("hora", LocalDateTime.now());
    	
    	String saludo = serviciosBeans.getSaludo();
    	
    	System.out.println(saludo);
    	return "pages/saludo/"+ saludo;
    }
    @GetMapping("/tecnologias")
    String tecnologiasVariadas() {
    	
    	
    	String inicio = serviciosBeans.getTecnologiasVariadas();
    	
    	return "pages/inicios/"+ inicio;
    }
    
    @GetMapping("/loremipsum")
    String loremipsum() {
    	
 
    	return "pages/loremipsum";
    }
    @GetMapping("/clientesVista")
    String clientesVista(Model model ) {
    	List<Clientes> list = clientService.getClientes();
    	model.addAttribute("list",list );
 
    	return "pages/clientesVista";
    }
    @GetMapping("/crearCliente")
    String nuevoCliente() {
    	
 
    	return "pages/crud/CrearCliente";
    }
    @GetMapping("/editarCliente")
    String editarCliente() {
    	
 
    	return "pages/crud/editarCliente";
    }
}