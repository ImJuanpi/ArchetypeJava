package mx.com.axity.services.service;

import mx.com.axity.commons.to.ComputerOrderTO;
import mx.com.axity.commons.to.ComputerTO;

import java.util.List;

public interface IComputerService {

    ComputerTO getComputerById(int idComputer);
    ComputerOrderTO createComputerOrder(ComputerOrderTO computerorderto);
}
