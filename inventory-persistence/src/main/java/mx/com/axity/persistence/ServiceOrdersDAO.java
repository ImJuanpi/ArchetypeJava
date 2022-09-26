package mx.com.axity.persistence;

import mx.com.axity.model.ServiceOrdersDO;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ServiceOrdersDAO extends CrudRepository<ServiceOrdersDO, Long> {

    List<ServiceOrdersDO> findAll();
    List<ServiceOrdersDO> findByFKIdOrders(int id);

}
