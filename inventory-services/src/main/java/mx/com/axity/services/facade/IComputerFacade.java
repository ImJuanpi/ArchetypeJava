package mx.com.axity.services.facade;

import mx.com.axity.commons.to.ComputerOrderTO;
import mx.com.axity.commons.to.ComputerTO;

public interface IComputerFacade {
    ComputerTO getComputerById(int idComputer);

    ComputerOrderTO createComputerOrder(ComputerOrderTO computerorderto);
}
