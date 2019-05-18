package services;

import entidades.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import repositories.ClientesRepository;

import java.util.List;
import java.util.Optional;

@Service
public  class ClientesService {

    @Autowired
    ClientesRepository clientesRepository;

    @Autowired
    public ClientesService(ClientesRepository clientesRepository){
        this.clientesRepository = clientesRepository;
    }

    public List<Clientes> getClientes() {
        List<Clientes> listaClientes = clientesRepository.findAll();
         return listaClientes;
    }
    public List<Clientes> buscarClientes(String cadenaBusqueda) {
        List<Clientes> listaClientes = clientesRepository.findAllByNombreAndApellidosAndDni2(cadenaBusqueda);
        return listaClientes;
    }

    public Optional<Clientes> getClientesDni(String dni) {
        Optional<Clientes> listaClientes = clientesRepository.findClientesByDni(dni);
        return listaClientes;
    }
    public Clientes save(Clientes cliente) {
        return clientesRepository.save(cliente);
    }
    public void delete(Clientes cliente) {
        clientesRepository.delete(cliente);
    }
    public List<Clientes> getClientesOrdenar(String ordenar) {
        switch(ordenar){
            case "nombre":
                return clientesRepository.findAllByOrderByNombre();
            case "direccion":
                return clientesRepository.findAllByOrderByDireccion();
            case "edad":
                return clientesRepository.findAllByOrderByEdad();
            case "correo":
                return clientesRepository.findAllByOrderByCorreo();
            case "apellidos":
                return clientesRepository.findAllByOrderByApellidos();
        }
        return null;
    }


    public List<Clientes> getClientesOrderApellidos() {
        List<Clientes> listaClientes = clientesRepository.findAllByOrderByApellidos();
        return listaClientes;
    }

    public List<Clientes> getClientesOrderEdad() {
        List<Clientes> listaClientes = clientesRepository.findAllByOrderByEdad();
        return listaClientes;
    }

    public List<Clientes> getClientesOrderCorreo() {
        List<Clientes> listaClientes = clientesRepository.findAllByOrderByCorreo();
        return listaClientes;
    }

    public List<Clientes> getClientesOrderNombre() {
        List<Clientes> listaClientes = clientesRepository.findAllByOrderByNombre();
        return listaClientes;
    }
    public List<Clientes> getClientesOrderDireccion() {
        List<Clientes> listaClientes = clientesRepository.findAllByOrderByDireccion();
        return listaClientes;
    }


}
