package OTS.tickets.OTSserver.repository;

import OTS.tickets.OTSserver.model.ShowPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShowPlanRepository extends JpaRepository<ShowPlan, Integer> {

    ShowPlan findShowPlanByIdOrderBySeatsAsc(int id);

    ShowPlan findShowPlanByName(String name);


}
