package com.galaxe.lenskart.entity;

import java.sql.Timestamp;
import java.util.List;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class CustomerOrder {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderId;
	private Timestamp orderDate;
	private Timestamp expectedDeliveryDate;
	private String paymentMode;
	private Long discount;
	private String orderStatus;
	private String paymentId;
	
	@ManyToOne
	@JoinColumn(name = "customerId")
	private Customer customer;
	
	@OneToMany(mappedBy ="customerOrder", cascade = CascadeType.ALL )
	private List<OrderItem> orderItems;
	
	@OneToOne
	@JoinColumn(name = "deliveryId")
	private DeliveryAddress deliveryAddress;
	
	@OneToOne(mappedBy = "customerOrder",cascade = CascadeType.ALL)
	private PaymentDetails paymentDetails;
	
	
}
