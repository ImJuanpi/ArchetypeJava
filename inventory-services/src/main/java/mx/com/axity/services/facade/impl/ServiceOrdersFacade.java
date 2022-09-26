package mx.com.axity.services.facade.impl;

import mx.com.axity.commons.to.ServiceOrdersTO;
import mx.com.axity.services.facade.IServiceOrdersFacade;
import mx.com.axity.services.service.IServiceOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ServiceOrdersFacade implements IServiceOrdersFacade {

    @Autowired
    private IServiceOrdersService serviceorderService;

    @Override
    public List<ServiceOrdersTO> getAllServiceOrderByOrderId(int idOrder) {
        return this.serviceorderService.getAllServiceOrderByFKIdOrder(idOrder);
    }
}
