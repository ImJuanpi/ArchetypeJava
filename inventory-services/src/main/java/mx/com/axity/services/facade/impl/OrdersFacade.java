package mx.com.axity.services.facade.impl;

import mx.com.axity.commons.to.OrdersTO;
import mx.com.axity.services.facade.IOrdersFacade;
import mx.com.axity.services.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class OrdersFacade implements IOrdersFacade {

    @Autowired
    private IOrdersService ordersService;

    @Override
    public OrdersTO getOrdersById(int idOrder) {
        return this.ordersService.getOrdersById(idOrder);
    }

    @Override
    public OrdersTO createOrder(OrdersTO newOrder){
        return this.ordersService.createOrder(newOrder);
    }
}
