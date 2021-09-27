package com.cds.demo.paymentserver.mapper;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.cds.demo.paymentserver.dto.Audit;
import com.cds.demo.paymentserver.dto.Status;
import com.cds.demo.paymentserver.dto.TransactionMode;
import com.cds.demo.paymentserver.dto.TransactionRequest;
import com.cds.demo.paymentserver.dto.TransactionResponse;
import com.cds.demo.paymentserver.dto.TransactionType;
import com.cds.demo.paymentserver.entity.Transactions;

/**
 * Class responsible to convert domain object to Entity objects and vice-versa
 * @author rahul.ghosh
 *
 */

@Component
public class PaymentObjectMapper {
	
	private static Logger logger = LoggerFactory.getLogger(PaymentObjectMapper.class);
	
	
	public TransactionResponse transform(Transactions t) {
		logger.info("transforming entity object to dto object");
		Audit audit = Audit.
						builder()
						.status(Status.valueOf(t.getStatus()))
						.timestamp(t.getTimestamp())
						.build();
		return TransactionResponse
					.builder()
						.id(t.getId())
						.customerName(t.getCustomerName())
						.comment(t.getComment())
						.transactionId(t.getTransactionId())
						.transactionMode(TransactionMode.fromValue(t.getTransactionMode()))
						.transactionType(TransactionType.valueOf(t.getTransactionType()))
						.amount(t.getAmount())
						.receivedBy(t.getReceivedBy())
						.emailId(t.getEmailId())
						.mobileNumber(t.getMobileNumber())
						.audit(audit)
					.build();
	}
	public List<TransactionResponse> transform(List<Transactions> transactionsList) {
		if(CollectionUtils.isEmpty(transactionsList)) {
			logger.warn("no record found in Transaction table");
			return Collections.emptyList();
		}
		logger.info("transforming entity object to dto object");
		List<TransactionResponse> responses = new ArrayList<>(transactionsList.size());
		for(Transactions t : transactionsList) {
			Audit audit = Audit.
					builder()
					.status(Status.valueOf(t.getStatus()))
					.timestamp(t.getTimestamp())
					.build();
			TransactionResponse transactionResponse = TransactionResponse
														.builder()
															.id(t.getId())
															.customerName(t.getCustomerName())
															.comment(t.getComment())
															.transactionId(t.getTransactionId())
															.transactionMode(TransactionMode.fromValue(t.getTransactionMode()))
															.transactionType(TransactionType.valueOf(t.getTransactionType()))
															.amount(t.getAmount())
															.receivedBy(t.getReceivedBy())
															.emailId(t.getEmailId())
															.mobileNumber(t.getMobileNumber())
															.audit(audit)
														.build();
			responses.add(transactionResponse);
			
		}
		return responses;
	}
	
	
	public Transactions buildRequest(TransactionRequest request) {
		logger.info("transforming  dto object to entity object");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return Transactions
				.builder()
					.amount(request.getAmount())
					.comment(request.getComment())
					.customerName(request.getCustomerName())
					.receivedBy(request.getReceivedBy())
					.timestamp(timestamp)
					.transactionId(UUID.randomUUID().toString())
					.transactionMode(request.getTransactionMode().getValue())
					.transactionType(TransactionType.NEW.name())
					.emailId(request.getEmailId())
					.mobileNumber(request.getMobileNumber())
					.status(Status.SUCCESS.name())
				.build();
	}

}
