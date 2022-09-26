package mx.com.axity.services.service;

import mx.com.axity.commons.to.KeyboardTO;

import java.util.List;

public interface IKeyboardService {
    KeyboardTO getKeyboardByFKIdComputer(int FKIdComputer);

    List<KeyboardTO> getKeyboardByFK(int fkid);
}
