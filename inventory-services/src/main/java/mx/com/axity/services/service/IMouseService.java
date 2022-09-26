package mx.com.axity.services.service;

import mx.com.axity.commons.to.MouseTO;

import java.util.List;

public interface IMouseService {

    MouseTO getMouseByFKIdComputer(int FKIdComputer);
    List<MouseTO> getMouseByFK(int fkid);

}
