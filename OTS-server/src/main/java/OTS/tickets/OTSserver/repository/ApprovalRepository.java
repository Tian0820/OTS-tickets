package OTS.tickets.OTSserver.repository;

import OTS.tickets.OTSserver.model.Approval;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApprovalRepository extends JpaRepository<Approval, Integer> {

    Approval findApprovalByVenueId(int id);

    Approval findApprovalById(int id);

}
