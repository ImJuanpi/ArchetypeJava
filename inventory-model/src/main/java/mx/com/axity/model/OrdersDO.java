package mx.com.axity.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class OrdersDO implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idOrders")
    private int idOrders;
    @Column(name = "date")
    private String date;
    @Column(name = "clientName")
    private String clientName;

}
