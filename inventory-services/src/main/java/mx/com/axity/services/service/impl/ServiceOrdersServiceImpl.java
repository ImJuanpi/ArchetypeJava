package mx.com.axity.services.service.impl;

import mx.com.axity.commons.to.ServiceOrdersTO;
import mx.com.axity.model.ServiceOrdersDO;
import mx.com.axity.persistence.ServiceOrdersDAO;
import mx.com.axity.services.service.IServiceOrdersService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceOrdersServiceImpl implements IServiceOrdersService {

    static final Logger LOG = LogManager.getLogger(ServiceOrdersServiceImpl.class);

    @Autowired
    ServiceOrdersDAO serviceordersDAO;

    @Autowired
    ComputerServiceImpl computer;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<ServiceOrdersTO> getAllServiceOrderByFKIdOrder(int idOrder) {
        LOG.info("Getting All Service Orders by Id Orders");
        List<ServiceOrdersDO> serviceorderDO = serviceordersDAO.findAll();
        List<ServiceOrdersTO> serviceorderTO = new ArrayList<>();
        serviceorderDO.forEach(x->{
            ServiceOrdersTO serviceorderto = new ServiceOrdersTO();
            if(idOrder == x.getFKIdOrders().getIdOrders()){
                serviceorderto.setFKIdOrders(x.getFKIdOrders().getIdOrders());
                serviceorderto.setFKIdComputer(x.getFKIdComputer().getIdComputer());
                serviceorderTO.add(serviceorderto);
            }
        });

        LOG.info(serviceorderTO.get(0).getFKIdComputer());

        return serviceorderTO;
    }
}
