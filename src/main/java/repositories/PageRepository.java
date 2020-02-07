package repositories;

import entidades.Clientes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface PageRepository extends PagingAndSortingRepository<Clientes, Serializable> {


    @Query("SELECT count(nombre) FROM Clientes")
    int findAllCountUsers();

    Page<Clientes> findAll(Pageable pageable);
}