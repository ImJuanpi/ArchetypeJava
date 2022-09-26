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
@Table(name = "monitor")
@NoArgsConstructor
@AllArgsConstructor
public class MonitorDO implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idMonitor")
    private int idMonitor;
    @Column(name = "brand")
    private String brand;
    @Column(name = "model")
    private String model;
    @Column(name = "serialNumber")
    private String serialNumber;
    @ManyToOne
    @JoinColumn(name = "FKIdComputer")
    private ComputerDO FKIdComputer;

}
