package mx.com.axity.commons.to;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ComputerTO implements Serializable {

    private int idComputer;
    private String brand;
    private String serialNumber;

}
