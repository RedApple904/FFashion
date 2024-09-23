/**
 * @author Tran Thien Thanh 09/04/1996
 */
package fashion.mock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fashion.mock.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
