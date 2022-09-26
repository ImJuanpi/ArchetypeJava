package mx.com.axity.services.service.impl;

import mx.com.axity.commons.to.KeyboardTO;
import mx.com.axity.commons.to.MouseTO;
import mx.com.axity.model.KeyboardDO;
import mx.com.axity.model.MouseDO;
import mx.com.axity.persistence.KeyboardDAO;
import mx.com.axity.services.service.IKeyboardService;
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
public class KeyboardServiceImpl implements IKeyboardService {

    static final Logger LOG = LogManager.getLogger(KeyboardServiceImpl.class);

    @Autowired
    KeyboardDAO keyboardDAO;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public KeyboardTO getKeyboardByFKIdComputer(int FKIdComputer) {
        LOG.info("Getting Keyboard...");
        KeyboardDO keyboardDO = this.keyboardDAO.findByFKIdComputer(FKIdComputer);
        Type DAOType = new TypeToken<KeyboardTO>() {}.getType();
        KeyboardTO keyboardTO = this.modelMapper.map(keyboardDO, DAOType);
        return keyboardTO;
    }

    @Override
    public List<KeyboardTO> getKeyboardByFK(int FKIdComputer) {
        LOG.info("Getting Keyboard...");
        List<KeyboardDO> keyboardDO = keyboardDAO.findAll();
        List<KeyboardTO> keyboardTO = new ArrayList<>();
        KeyboardTO keyboardto = new KeyboardTO();

        keyboardDO.forEach(x->{
            if(FKIdComputer == x.getFKIdComputer().getIdComputer()){
                keyboardto.setBrand(x.getBrand());
                keyboardto.setSerialNumber(x.getSerialNumber());
                keyboardto.setFKIdComputer(x.getFKIdComputer().getIdComputer());
                keyboardTO.add(keyboardto);
            }
        });

        return keyboardTO;
    }
}
