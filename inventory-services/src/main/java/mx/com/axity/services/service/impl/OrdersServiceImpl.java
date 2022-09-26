package mx.com.axity.services.service.impl;

import mx.com.axity.commons.to.*;
import mx.com.axity.model.*;
import mx.com.axity.persistence.*;
import mx.com.axity.services.service.IOrdersService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrdersServiceImpl implements IOrdersService {

    static final Logger LOG = LogManager.getLogger(OrdersServiceImpl.class);

    @Autowired
    OrdersDAO ordersDAO;
    @Autowired
    ComputerDAO computerDAO;
    @Autowired
    MouseDAO mouseDAO;
    @Autowired
    KeyboardDAO keyboardDAO;
    @Autowired
    MonitorDAO monitorDAO;
    @Autowired
    ServiceOrdersDAO serviceOrdersDAO;

    @Autowired
    ServiceOrdersServiceImpl serviceorderserviceImpl;
    @Autowired
    ComputerServiceImpl computerserviceImpl;
    @Autowired
    MouseServiceImpl mouseserviceImpl;
    @Autowired
    MonitorServiceImpl monitorserviceImpl;
    @Autowired
    KeyboardServiceImpl KeyboardserviceImpl;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public OrdersTO getOrdersById(int idOrders) {
        LOG.info("Here Getting Order By Id...");
        OrdersDO ordersDO = ordersDAO.findByidOrders(idOrders);
        if (ordersDO == null){
            LOG.info("There's no orders...");
            return null;
        }
        else{
            Type DAOType = new TypeToken<OrdersTO>() {}.getType();
            OrdersTO ordersTO = this.modelMapper.map(ordersDO, DAOType);

            List<ServiceOrdersTO> serviceordersTO = serviceorderserviceImpl.getAllServiceOrderByFKIdOrder(idOrders);

            List<ComputerTO> computerTO = new ArrayList<>();
            List<MouseTO> mouseTO = new ArrayList<>();
            List<MonitorTO> monitorTO = new ArrayList<>();
            List<KeyboardTO> keyboardTO = new ArrayList<>();

            serviceordersTO.forEach(serviceorder -> {
                //LOG.info(serviceorder.getFKIdComputer());
                computerTO.add(computerserviceImpl.getComputerById(serviceorder.getFKIdComputer()));
            });

            computerTO.forEach(computer -> {
                mouseTO.addAll(mouseserviceImpl.getMouseByFK(computer.getIdComputer()));
                monitorTO.addAll(monitorserviceImpl.getMonitorByFK(computer.getIdComputer()));
                keyboardTO.addAll(KeyboardserviceImpl.getKeyboardByFK(computer.getIdComputer()));
            });

            ordersTO.setComputers(computerTO);
            ordersTO.setMonitor(monitorTO);
            ordersTO.setKeyboard(keyboardTO);
            ordersTO.setMouse(mouseTO);

            return ordersTO;
        }
    }

    public OrdersTO createOrder(OrdersTO fullorderto){

        OrdersDO orderDO = new OrdersDO();

        //OrdersDAO
        orderDO = setOrders(fullorderto);

        //ComputerDAO
        setComputers(fullorderto, orderDO);

        return fullorderto;
    }

    public OrdersDO setOrders(OrdersTO fullorderto){
        OrdersDO orderDO = new OrdersDO();
        orderDO.setDate(fullorderto.getDate());
        orderDO.setClientName(fullorderto.getClientName());
        ordersDAO.save(orderDO);

        fullorderto.setIdOrders(orderDO.getIdOrders());
        return orderDO;
    }

    public void setComputers(OrdersTO fullorderto, OrdersDO orderdo){
        ComputerDO computerdo = new ComputerDO();

        fullorderto.getComputers().forEach(computer -> {
            computerdo.setBrand(computer.getBrand());
            computerdo.setSerialNumber(computer.getSerialNumber());
            computerDAO.save(computerdo);

            //MonitorDO
            setMonitors(fullorderto, computer.getIdComputer(), computerdo);

            //MouseDO
            setMouses(fullorderto, computer.getIdComputer(), computerdo);

            //KeyboardDO
            setKeyboards(fullorderto, computer.getIdComputer(), computerdo);

            computer.setIdComputer(computerdo.getIdComputer());

            //ServiceOrdersDAO
            setServiceOrders(fullorderto, computerdo, orderdo);
        });

    }

    public void setMonitors(OrdersTO fullorderto, int idcomputer, ComputerDO computerdo){
        MonitorDO monitordo = new MonitorDO();

        fullorderto.getMonitor().forEach(monitor -> {
            if(monitor.getFKIdComputer() == idcomputer){
                monitordo.setBrand(monitor.getBrand());
                monitordo.setSerialNumber(monitor.getSerialNumber());
                monitordo.setModel(monitor.getModel());
                monitordo.setFKIdComputer(computerdo);

                monitorDAO.save(monitordo);
                monitor.setIdMonitor(monitordo.getIdMonitor());
                monitor.setFKIdComputer(computerdo.getIdComputer());
            }
        });
    }

    public void setMouses(OrdersTO fullorderto, int idcomputer, ComputerDO computerdo){
        MouseDO mousesdo = new MouseDO();

        fullorderto.getMouse().forEach(mouse -> {
            if(mouse.getFKIdComputer() == idcomputer){
                mousesdo.setBrand(mouse.getBrand());
                mousesdo.setSerialNumber(mouse.getSerialNumber());
                mousesdo.setFKIdComputer(computerdo);

                mouseDAO.save(mousesdo);
                mouse.setIdMouse(mousesdo.getIdMouse());
                mouse.setFKIdComputer(computerdo.getIdComputer());
            }
        });
    }

    public void setKeyboards(OrdersTO fullorderto, int idcomputer, ComputerDO computerdo){
        KeyboardDO keyboarddo = new KeyboardDO();

        fullorderto.getKeyboard().forEach(keyboard -> {
            if(keyboard.getFKIdComputer() == idcomputer){
                keyboarddo.setBrand(keyboard.getBrand());
                keyboarddo.setSerialNumber(keyboard.getSerialNumber());
                keyboarddo.setFKIdComputer(computerdo);

                keyboardDAO.save(keyboarddo);
                keyboard.setIdKeyboard(keyboarddo.getIdKeyboard());
                keyboard.setFKIdComputer(computerdo.getIdComputer());
            }
        });
    }

    public void setServiceOrders(OrdersTO fullorderto, ComputerDO computerdo, OrdersDO orderdo){
        ServiceOrdersDO serviceorderdo = new ServiceOrdersDO();

        fullorderto.getComputers().forEach(computer -> {
            serviceorderdo.setFKIdOrders(orderdo);
            serviceorderdo.setFKIdComputer(computerdo);
            serviceOrdersDAO.save(serviceorderdo);
        });
    }

}
