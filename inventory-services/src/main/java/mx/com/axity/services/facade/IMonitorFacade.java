package mx.com.axity.services.facade;

import mx.com.axity.commons.to.MonitorTO;

public interface IMonitorFacade {
    MonitorTO getMonitorByFKIdComputer(int FKIdComputer);
}
