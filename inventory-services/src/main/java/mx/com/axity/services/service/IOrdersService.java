package mx.com.axity.services.service;

import mx.com.axity.commons.to.OrdersTO;
import java.util.List;

public interface IOrdersService {

    OrdersTO getOrdersById(int idOrder);

    OrdersTO createOrder(OrdersTO fullorder);
}
