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
@Table(name = "mouse")
@NoArgsConstructor
@AllArgsConstructor
public class MouseDO implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idMouse")
    private int idMouse;
    @Column(name = "brand")
    private String brand;
    @Column(name = "serialNumber")
    private String serialNumber;
    @ManyToOne
    @JoinColumn(name = "FKIdComputer")
    private ComputerDO FKIdComputer;

}
