package mx.com.axity.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "serviceorders")
@NoArgsConstructor
@AllArgsConstructor
public class ServiceOrdersDO implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idServiceOrders")
    private int idServiceOrders;
    @ManyToOne
    @JoinColumn(name = "FKIdOrders")
    private OrdersDO FKIdOrders;
    @ManyToOne
    @JoinColumn(name = "FKIdComputer")
    private ComputerDO FKIdComputer;
}
