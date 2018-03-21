package OTS.tickets.OTSserver.repository;

import OTS.tickets.OTSserver.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {

    Coupon findCouponByDiscount(double discount);

    Coupon findCouponById(int id);
}
