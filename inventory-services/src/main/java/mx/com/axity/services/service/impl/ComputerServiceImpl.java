package mx.com.axity.services.service.impl;

import mx.com.axity.commons.to.ComputerOrderTO;
import mx.com.axity.commons.to.ComputerTO;
import mx.com.axity.commons.to.OrdersTO;
import mx.com.axity.model.*;
import mx.com.axity.persistence.*;
import mx.com.axity.services.service.IComputerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class ComputerServiceImpl implements IComputerService {

    static final Logger LOG = LogManager.getLogger(ComputerServiceImpl.class);

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
    ServiceOrdersDAO serviceordersDAO;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public ComputerTO getComputerById(int idComputer) {
        LOG.info("Getting Computers...");
        ComputerDO computerDO = this.computerDAO.findByidComputer(idComputer);
        Type DAOType = new TypeToken<ComputerTO>() {}.getType();
        ComputerTO computerTO = this.modelMapper.map(computerDO, DAOType);
        LOG.info("{} Computers", computerTO);
        return computerTO;
    }

    @Override
    public ComputerOrderTO createComputerOrder(ComputerOrderTO computerorderto) {

        OrdersDO ordersDO = ordersDAO.findByidOrders(computerorderto.getIdorder());

        if (ordersDO == null) {
            LOG.info("There's no orders with that id...");
            return null;
        } else {
            List<ServiceOrdersDO> serviceordersDO = serviceordersDAO.findAll();
            final int[] max = {0};
            serviceordersDO.forEach( aux ->{
                if(aux.getIdServiceOrders()==computerorderto.getIdorder())
                    max[0]++;
            });
            if (max[0] > 5) {
                LOG.info("There's max orders...");
                return null;
            } else {
                //ComputerDAO
                setComputers(computerorderto, ordersDO);
            }

            return computerorderto;
        }
    }

    public void setComputers(ComputerOrderTO computerorderto, OrdersDO orderdo){
        ComputerDO computerdo = new ComputerDO();


        computerorderto.getComputers().forEach(computer -> {
            computerdo.setBrand(computer.getBrand());
            computerdo.setSerialNumber(computer.getSerialNumber());
            computerDAO.save(computerdo);

            //MonitorDO
            setMonitors(computerorderto, computer.getIdComputer(), computerdo);

            //MouseDO
            setMouses(computerorderto, computer.getIdComputer(), computerdo);

            //KeyboardDO
            setKeyboards(computerorderto, computer.getIdComputer(), computerdo);

            computer.setIdComputer(computerdo.getIdComputer());

            //ServiceOrdersDAO
            setServiceOrders(computerorderto, computerdo, orderdo);
        });

    }

    public void setMonitors(ComputerOrderTO computerorderto, int idcomputer, ComputerDO computerdo){
        MonitorDO monitordo = new MonitorDO();

        computerorderto.getMonitor().forEach(monitor -> {
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

    public void setMouses(ComputerOrderTO computerorderto, int idcomputer, ComputerDO computerdo){
        MouseDO mousesdo = new MouseDO();

        computerorderto.getMouse().forEach(mouse -> {
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

    public void setKeyboards(ComputerOrderTO computerorderto, int idcomputer, ComputerDO computerdo){
        KeyboardDO keyboarddo = new KeyboardDO();

        computerorderto.getKeyboard().forEach(keyboard -> {
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

    public void setServiceOrders(ComputerOrderTO computerorderto, ComputerDO computerdo, OrdersDO orderdo){
        ServiceOrdersDO serviceorderdo = new ServiceOrdersDO();

        computerorderto.getComputers().forEach(computer -> {
            serviceorderdo.setFKIdOrders(orderdo);
            serviceorderdo.setFKIdComputer(computerdo);
            serviceordersDAO.save(serviceorderdo);
        });
    }
}
