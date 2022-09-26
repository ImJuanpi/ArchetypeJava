package mx.com.axity.services.service;

import mx.com.axity.commons.to.OrdersTO;
import mx.com.axity.commons.to.ServiceOrdersTO;

import java.util.List;

public interface IServiceOrdersService {

    List<ServiceOrdersTO> getAllServiceOrderByFKIdOrder(int idOrder);
}
