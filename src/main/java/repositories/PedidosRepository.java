package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import entidades.Pedidos;
 

@Repository
public interface PedidosRepository extends JpaRepository<Pedidos, Long> {
	 
 
 
}
