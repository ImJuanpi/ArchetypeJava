package mx.com.axity.persistence;

import mx.com.axity.model.KeyboardDO;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface KeyboardDAO extends CrudRepository<KeyboardDO, Long> {

    KeyboardDO findByFKIdComputer(int FKIdComputer);

    List<KeyboardDO> findAll ();
}
