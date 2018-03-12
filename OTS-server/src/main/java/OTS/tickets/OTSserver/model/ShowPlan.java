package OTS.tickets.OTSserver.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "show_plan")
public class ShowPlan {

    @Id
    @GeneratedValue
    private Integer id;
}
