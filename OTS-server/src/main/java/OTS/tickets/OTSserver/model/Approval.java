package OTS.tickets.OTSserver.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "approval")
public class Approval {

    @Id
    @GeneratedValue
    private Integer id;

    /**
     * 审批状态，分为：
     * 审批成功：success
     * 审批失败：fail
     * 正在审批中：process
     */
    private String state;

    private LocalDateTime time;

    @ManyToOne
    @JsonManagedReference
    private Venue venue;

}
