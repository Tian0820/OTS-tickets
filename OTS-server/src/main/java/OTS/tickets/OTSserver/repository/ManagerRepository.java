package OTS.tickets.OTSserver.repository;

import OTS.tickets.OTSserver.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager, Integer> {

    Manager findManagerByManagerName(String managerName);

    Manager findManagerById(int id);
}
