package mx.com.axity.commons.to;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ServiceOrdersTO implements Serializable {

    private int idServiceOrders;
    private int FKIdOrders;
    private int FKIdComputer;
}
