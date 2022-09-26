package mx.com.axity.commons.to;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class ComputerOrderTO implements Serializable {

    private int idorder;
    private List<ComputerTO> computers;
    private List<MonitorTO> monitor;
    private List<KeyboardTO> keyboard;
    private List<MouseTO> mouse;
}