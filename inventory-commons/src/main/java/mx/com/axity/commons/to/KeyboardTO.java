package mx.com.axity.commons.to;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class KeyboardTO implements Serializable {

    private int idKeyboard;
    private String brand;
    private String serialNumber;
    private int FKIdComputer;
}
