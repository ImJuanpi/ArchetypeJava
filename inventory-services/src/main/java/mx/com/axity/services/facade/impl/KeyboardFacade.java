package mx.com.axity.services.facade.impl;

import mx.com.axity.commons.to.KeyboardTO;
import mx.com.axity.commons.to.MouseTO;
import mx.com.axity.services.facade.IKeyboardFacade;
import mx.com.axity.services.facade.IMouseFacade;
import mx.com.axity.services.service.IKeyboardService;
import mx.com.axity.services.service.IMouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KeyboardFacade implements IKeyboardFacade {

    @Autowired
    private IKeyboardService keyboardService;

    @Override
    public KeyboardTO getKeyBoardByFKIdComputer(int FKIdComputer) {
        return this.keyboardService.getKeyboardByFKIdComputer(FKIdComputer);
    }
}
