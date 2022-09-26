package mx.com.axity.services.facade.impl;

import mx.com.axity.commons.to.MouseTO;
import mx.com.axity.services.facade.IMouseFacade;
import mx.com.axity.services.service.IMouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MouseFacade implements IMouseFacade {

    @Autowired
    private IMouseService mouseService;

    @Override
    public MouseTO getMouseById(int FKIdComputer) {
        return this.mouseService.getMouseByFKIdComputer(FKIdComputer);
    }
}
