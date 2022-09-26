package mx.com.axity.services.service.impl;

import mx.com.axity.commons.to.MonitorTO;
import mx.com.axity.commons.to.MouseTO;
import mx.com.axity.model.MonitorDO;
import mx.com.axity.model.MouseDO;
import mx.com.axity.persistence.MonitorDAO;
import mx.com.axity.services.service.IMonitorService;
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
public class MonitorServiceImpl implements IMonitorService {

    static final Logger LOG = LogManager.getLogger(MonitorServiceImpl.class);

    @Autowired
    MonitorDAO monitorDAO;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public MonitorTO getMonitorByFKIdComputer(int FKIdComputer) {
        LOG.info("Getting Monitor...");
        MonitorDO monitorDO = this.monitorDAO.findByFKIdComputer(FKIdComputer);
        Type DAOType = new TypeToken<MonitorTO>() {}.getType();
        MonitorTO monitorTO = this.modelMapper.map(monitorDO, DAOType);
        return monitorTO;
    }

    @Override
    public List<MonitorTO> getMonitorByFK(int FKIdComputer) {
        LOG.info("Getting Monitor...");
        List<MonitorDO> monitorDO = monitorDAO.findAll();
        List<MonitorTO> monitorTO = new ArrayList<>();
        MonitorTO monitorto = new MonitorTO();

        monitorDO.forEach(x->{
            if(FKIdComputer == x.getFKIdComputer().getIdComputer()){
                monitorto.setBrand(x.getBrand());
                monitorto.setSerialNumber(x.getSerialNumber());
                monitorto.setFKIdComputer(x.getFKIdComputer().getIdComputer());
                monitorto.setModel(x.getModel());
                monitorTO.add(monitorto);
            }
        });

        return monitorTO;
    }
}
