package mx.com.axity.persistence;

import mx.com.axity.model.OrdersDO;
import org.springframework.data.repository.CrudRepository;

public interface OrdersDAO extends CrudRepository<OrdersDO, Long> {
    OrdersDO findByidOrders(int idOrders);
}
