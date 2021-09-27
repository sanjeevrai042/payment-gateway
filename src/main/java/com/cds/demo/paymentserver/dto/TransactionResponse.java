package com.cds.demo.paymentserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TransactionResponse {

	private Integer id;
	private String transactionId;
	private String customerName;
	private TransactionType transactionType;
	private TransactionMode transactionMode;
	private Float amount;
	private String comment;
	private String receivedBy;
	private String emailId;
	private String mobileNumber;
	private Audit audit;
}
