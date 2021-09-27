package com.cds.demo.paymentserver.service;

import org.springframework.stereotype.Service;

import com.cds.demo.paymentserver.dto.TransactionRequest;
import com.cds.demo.paymentserver.dto.TransactionResponse;
import com.cds.demo.paymentserver.exception.PaymentException;

@Service
public class ValidationService {

	/**
	 * Validates the request and table data to find mismatch for Update service.
	 * 
	 * @param TransactionRequest
	 * @param TransactionResponse
	 * @return true/Exception
	 */
	public Boolean validateRequestWithTable(TransactionRequest request, TransactionResponse response) {

		if (request.getAmount() > response.getAmount()) {
			throw new PaymentException("Amount: " + request.getAmount()
					+ " - cannot be greater than original transaction: " + response.getAmount());
		}

		if (!request.getCustomerName().equalsIgnoreCase(response.getCustomerName())) {
			throw new PaymentException("Customer name: " + request.getCustomerName()
					+ " - is different from the original transaction: " + response.getCustomerName());
		}

		if (!request.getEmailId().equalsIgnoreCase(response.getEmailId())) {
			throw new PaymentException("Email Id: " + request.getEmailId()
					+ " - different from the original transaction: " + response.getEmailId());
		}

		if (!request.getMobileNumber().equalsIgnoreCase(response.getMobileNumber())) {
			throw new PaymentException("Mobile Number: " + request.getMobileNumber()
					+ " - is different from the original transaction:" + response.getMobileNumber());
		}

		if (!request.getReceivedBy().equalsIgnoreCase(response.getReceivedBy())) {
			throw new PaymentException("Received By: " + request.getReceivedBy()
					+ " - is different from the original transaction: " + response.getReceivedBy());
		}

		return true;
	}
}
