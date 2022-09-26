package mx.com.axity.commons.to;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class OrdersTO implements Serializable {

    private int idOrders;
    private String date;
    private String clientName;
    private List<ComputerTO> computers;
    private List<MonitorTO> monitor;
    private List<KeyboardTO> keyboard;
    private List<MouseTO> mouse;

}