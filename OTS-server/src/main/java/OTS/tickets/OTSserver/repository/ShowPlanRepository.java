package OTS.tickets.OTSserver.repository;

import OTS.tickets.OTSserver.model.ShowPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ShowPlanRepository extends JpaRepository<ShowPlan, Integer>, JpaSpecificationExecutor<ShowPlan> {

    ShowPlan findShowPlanByIdOrderBySeatsAsc(int id);

    ShowPlan findShowPlanByName(String name);


}
