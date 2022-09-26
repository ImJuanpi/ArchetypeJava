package mx.com.axity.commons.to;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class MonitorTO implements Serializable {

    private int idMonitor;
    private String brand;
    private String model;
    private String serialNumber;
    private int FKIdComputer;
}
