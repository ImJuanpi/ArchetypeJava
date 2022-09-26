package mx.com.axity.services.facade;

import mx.com.axity.commons.to.OrdersTO;
import mx.com.axity.commons.to.ServiceOrdersTO;
import mx.com.axity.model.ServiceOrdersDO;

import java.util.List;

public interface IServiceOrdersFacade {

    List<ServiceOrdersTO> getAllServiceOrderByOrderId(int idorder);

}
