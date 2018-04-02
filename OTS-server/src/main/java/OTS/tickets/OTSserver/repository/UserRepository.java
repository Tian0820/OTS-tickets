package OTS.tickets.OTSserver.repository;

import OTS.tickets.OTSserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserById(Integer id);

    User findUserByUsername(String username);

    User findUserByEmail(String email);

}
