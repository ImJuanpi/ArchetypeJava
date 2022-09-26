package mx.com.axity.persistence;

import mx.com.axity.model.MouseDO;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MouseDAO extends CrudRepository<MouseDO, Long> {
    MouseDO findByFKIdComputer(int FKIdComputer);

    List<MouseDO> findAll();
}
