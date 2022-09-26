package mx.com.axity.services.service.impl;

import mx.com.axity.commons.to.MouseTO;
import mx.com.axity.commons.to.ServiceOrdersTO;
import mx.com.axity.model.MouseDO;
import mx.com.axity.persistence.MouseDAO;
import mx.com.axity.services.service.IMouseService;
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
public class MouseServiceImpl implements IMouseService {

    static final Logger LOG = LogManager.getLogger(MouseServiceImpl.class);

    @Autowired
    MouseDAO mouseDAO;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public MouseTO getMouseByFKIdComputer(int FKIdComputer) {
        LOG.info("Getting Mouse...");
        MouseDO mouseDO = this.mouseDAO.findByFKIdComputer(FKIdComputer);
        Type DAOType = new TypeToken<MouseTO>() {}.getType();
        MouseTO mouseTO = this.modelMapper.map(mouseDO, DAOType);
        return mouseTO;
    }

    @Override
    public List<MouseTO> getMouseByFK(int FKIdComputer) {
        LOG.info("Getting Mouse...");
        List<MouseDO> mouseDO = mouseDAO.findAll();
        List<MouseTO> mouseTO = new ArrayList<>();
        MouseTO mouseto = new MouseTO();

        mouseDO.forEach(x->{
            if(FKIdComputer == x.getFKIdComputer().getIdComputer()){
                mouseto.setBrand(x.getBrand());
                mouseto.setSerialNumber(x.getSerialNumber());
                mouseto.setFKIdComputer(x.getFKIdComputer().getIdComputer());
                mouseTO.add(mouseto);
            }
        });

        return mouseTO;
    }
}
