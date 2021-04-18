package viewControllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import entidades.Clientes;
import services.ClientesService;
import Beans.ServiciosBeans;

@Controller()
class viewController {

	@Autowired
	ServiciosBeans serviciosBeans;
	@Autowired
	ClientesService clientService;
	
	@GetMapping("/serviciosBeans")
	ServiciosBeans getServiciosBeans() {
        
        return serviciosBeans;
    }
	
    @GetMapping("/welcome")
    String index(Model model) {
        model.addAttribute("now", LocalDateTime.now());
        return "index";
    }
    @GetMapping("/saludo")
    String indexSaludo(Model model2) {
    	model2.addAttribute("hora", LocalDateTime.now());
    	String saludo = serviciosBeans.getSaludo();
    	return "pages/saludo/"+ saludo;
    }
 
    @GetMapping("/tecnologias")
    String tecnologiasVariadas() {
    	String inicio = serviciosBeans.getTecnologiasVariadas();
    	return "pages/inicios/"+ inicio;
    }
    @GetMapping("/pedidos")
    String pedidos() {
 
    	return "pages/pedidos";
    }

    @GetMapping("/clientesVista")
    String clientesVista(Model model ) {
    	List<Clientes> list = clientService.getClientes();
    	model.addAttribute("list",list );
 
    	return "pages/crud/clientes/clientesVista";
    }
    @GetMapping("/crearCliente")
    String nuevoCliente() {
    	
 
    	return "pages/crud/clientes/CrearCliente";
    }
    @GetMapping("/editarCliente")
    String editarCliente() {
    	
 
    	return "pages/crud/clientes/editarCliente";
    }
    @GetMapping("/login")
    public String login(Model model) {
        return "pages/admin/login";
    }
    @RequestMapping("/adminuser" )
    String paginaAdminUsers( ) {
         return "pages/admin/usersRoles";
 
    }
    @RequestMapping("/productosVista")
    String paginaProductosVista( ) {
         return "pages/crud/productos/productosVista";
    }
    @RequestMapping("/crearProducto" )
    String paginaCrearProducto( ) {
        return "pages/crud/productos/CrearProductos";
    }
    @RequestMapping("/editarProducto" )
    String paginaEditarProducto( ) {
        return "pages/crud/productos/editarProducto";
    }
}