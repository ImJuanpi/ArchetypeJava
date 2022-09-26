package mx.com.axity.services.service;

import mx.com.axity.commons.to.MonitorTO;
import mx.com.axity.commons.to.MouseTO;
import mx.com.axity.model.MonitorDO;

import java.util.List;

public interface IMonitorService {

    MonitorTO getMonitorByFKIdComputer(int FKIdComputer);

    List<MonitorTO> getMonitorByFK(int fkid);

}
