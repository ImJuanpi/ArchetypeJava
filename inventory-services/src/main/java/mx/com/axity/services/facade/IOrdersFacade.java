package mx.com.axity.services.facade;

import mx.com.axity.commons.to.OrdersTO;

import java.util.List;

public interface IOrdersFacade {

    OrdersTO getOrdersById(int idOrder);
    OrdersTO createOrder(OrdersTO newOrder);

}
