package fashion.mock.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "ORDERS")
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;

	@Column(name = "orderDate", nullable = false)
	private LocalDate orderDate;

	@Column(name = "shippingPrice", nullable = false)
	private Double shippingPrice;

	@Column(name = "totalPrice", nullable = false)
	private Double totalPrice;

	// New field to store the received address
	@Column(name = "receiveAddress", nullable = false, columnDefinition = "NVARCHAR(255)")
	private String receiveAddress;

	@Column(columnDefinition = "NVARCHAR(255)")
	private String status;

	@Column(name = "updatedDate")
	private LocalDate updatedDate;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<TransactionHistory> transactionHistories;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderDetail> orderDetails;

	public Order(Long id, User user, LocalDate orderDate, Double shippingPrice, Double totalPrice,
			String receiveAddress, String status, LocalDate updatedDate, List<TransactionHistory> transactionHistories,
			List<OrderDetail> orderDetails) {
		this.id = id;
		this.user = user;
		this.orderDate = orderDate;
		this.shippingPrice = shippingPrice;
		this.totalPrice = totalPrice;
		this.receiveAddress = receiveAddress;
		this.status = status;
		this.updatedDate = updatedDate;
		this.transactionHistories = transactionHistories;
		this.orderDetails = orderDetails;
	}

	public String getReceiveAddress() {
		return receiveAddress;
	}

	public void setReceiveAddress(String receiveAddress) {
		this.receiveAddress = receiveAddress;
	}

	public Order() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public Double getShippingPrice() {
		return shippingPrice;
	}

	public void setShippingPrice(Double shippingPrice) {
		this.shippingPrice = shippingPrice;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDate updatedDate) {
		this.updatedDate = updatedDate;
	}

	public List<TransactionHistory> getTransactionHistories() {
		return transactionHistories;
	}

	public void setTransactionHistories(List<TransactionHistory> transactionHistories) {
		this.transactionHistories = transactionHistories;
	}

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

}
