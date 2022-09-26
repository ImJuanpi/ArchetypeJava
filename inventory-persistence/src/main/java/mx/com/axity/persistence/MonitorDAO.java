package mx.com.axity.persistence;

import mx.com.axity.model.MonitorDO;
import mx.com.axity.model.MouseDO;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MonitorDAO extends CrudRepository<MonitorDO, Long> {

    MonitorDO findByFKIdComputer(int FKIdComputer);

    List<MonitorDO> findAll();
}
