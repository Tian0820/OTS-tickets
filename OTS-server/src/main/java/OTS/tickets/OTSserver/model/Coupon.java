package OTS.tickets.OTSserver.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "coupon")
public class Coupon {

    @Id
    @GeneratedValue
    private Integer id;

    private String coupon_name;

    private Double discount;
}
