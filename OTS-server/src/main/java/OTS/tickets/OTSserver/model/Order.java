package OTS.tickets.OTSserver.model;

import javax.persistence.*;

@Entity
@Table(name = "ticket_order")
public class Order {

    @Id
    @GeneratedValue
    private Integer id;

    public Order() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
