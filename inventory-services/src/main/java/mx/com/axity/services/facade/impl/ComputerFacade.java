package mx.com.axity.services.facade.impl;

import mx.com.axity.commons.to.ComputerOrderTO;
import mx.com.axity.commons.to.ComputerTO;
import mx.com.axity.services.facade.IComputerFacade;
import mx.com.axity.services.service.IComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ComputerFacade implements IComputerFacade {

    @Autowired
    private IComputerService computerService;

    @Override
    public ComputerTO getComputerById(int idComputer) {
        return this.computerService.getComputerById(idComputer);
    }
    public ComputerOrderTO createComputerOrder(ComputerOrderTO computerorderto){
        return this.computerService.createComputerOrder(computerorderto);
    }
}
