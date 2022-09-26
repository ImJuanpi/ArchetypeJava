package mx.com.axity.persistence;

import mx.com.axity.model.ComputerDO;
import org.springframework.data.repository.CrudRepository;

public interface ComputerDAO extends CrudRepository<ComputerDO, Long> {

    ComputerDO findByidComputer(int idComputer);

}
