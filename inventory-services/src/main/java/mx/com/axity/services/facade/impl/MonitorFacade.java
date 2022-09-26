package mx.com.axity.services.facade.impl;

import mx.com.axity.commons.to.MonitorTO;
import mx.com.axity.services.facade.IMonitorFacade;
import mx.com.axity.services.service.IMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MonitorFacade implements IMonitorFacade {

    @Autowired
    private IMonitorService monitorService;

    @Override
    public MonitorTO getMonitorByFKIdComputer(int FKIdComputer) {
        return this.monitorService.getMonitorByFKIdComputer(FKIdComputer);
    }
}
