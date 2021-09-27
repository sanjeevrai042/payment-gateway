package com.cds.demo.paymentserver.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "transaction_details")
public class Transactions implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "customer_name")
	private String customerName;

	@Column(name = "amount")
	private Float amount;

	@Column(name = "transaction_id")
	private String transactionId;

	@Column(name = "transaction_type")
	private String transactionType;

	@Column(name = "comment")
	private String comment;

	@Column(name = "received_by")
	private String receivedBy;

	@Column(name = "timestamp")
	private Timestamp timestamp;

	@Column(name = "status")
	private String status;

	@Column(name = "transaction_mode")
	private String transactionMode;
	
	@Column(name = "email_id")
	private String emailId;
	
	@Column(name = "mobile_number")
	private String mobileNumber;

}
