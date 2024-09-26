/**
 * @author Tran Thien Thanh 09/04/1996
 */
package fashion.mock.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fashion.mock.model.Order;
import fashion.mock.model.User;
import fashion.mock.repository.OrderRepository;
import fashion.mock.repository.UserRepository;

@Service
public class OrderService {

	private final OrderRepository orderRepository;
	private final UserRepository userRepository;

	public OrderService(OrderRepository orderRepository, UserRepository userRepository) {
		this.orderRepository = orderRepository;
		this.userRepository = userRepository;
	}

	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}

	public Optional<Order> getOrderById(Long id) {
		return orderRepository.findById(id);
	}

	public User findUserById(Long userId) {
		return userRepository.findById(userId).orElse(null);
	}

	public Order saveOrder(Order order) {
		return orderRepository.save(order);
	}
	
	/**
	 * @author Duong Van Luc 01/07/2000
	 */
	
    public void updateOrderStatus(Long id, String status) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(status);
        order.setUpdatedDate(LocalDate.now());;
        orderRepository.save(order);     
    }
 
    /**
     * @author Duong Van Luc 01/07/2000
     */
    
    public Page<Order> getOrdersWithPagination(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }
    
    public Page<Order> findById(Long id, Pageable pageable) {
        return orderRepository.findById(id, pageable);
    }
}
