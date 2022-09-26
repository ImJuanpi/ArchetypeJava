package mx.com.axity.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "computer")
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class ComputerDO implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idComputer")
    private int idComputer;
    @Column(name = "brand")
    private String brand;
    @Column(name = "serialNumber")
    private String serialNumber;

}
