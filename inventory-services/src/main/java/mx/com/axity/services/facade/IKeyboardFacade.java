package mx.com.axity.services.facade;

import mx.com.axity.commons.to.KeyboardTO;

public interface IKeyboardFacade {
    KeyboardTO getKeyBoardByFKIdComputer(int FKIdComputer);
}
