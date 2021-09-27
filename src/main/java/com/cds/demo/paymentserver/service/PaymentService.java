package com.cds.demo.paymentserver.service;

import java.util.List;
import java.util.Optional;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cds.demo.paymentserver.dao.TransactionRepository;
import com.cds.demo.paymentserver.dto.TransactionRequest;
import com.cds.demo.paymentserver.dto.TransactionResponse;
import com.cds.demo.paymentserver.dto.TransactionType;
import com.cds.demo.paymentserver.entity.Transactions;
import com.cds.demo.paymentserver.exception.PaymentException;
import com.cds.demo.paymentserver.mapper.PaymentObjectMapper;

@Service
public class PaymentService {

	private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);
	
	@Autowired
	private TransactionRepository repository;
	
	@Autowired 
	private PaymentObjectMapper mapper;
	
	@Autowired
	private ValidationService validationService;
	
	/**
	 * Method to fetch all transactions 
	 * @return List<TransactionResponse>
	 */
	public List<TransactionResponse> getAllTransactions() {
		logger.info("going to get all records");
		Iterable<Transactions>  iterable = repository.findAll();
		List<Transactions> transactionsList = 	StreamSupport
													.stream(Spliterators.spliteratorUnknownSize(iterable.iterator(), 
															Spliterator.SORTED), false)
												.collect(Collectors.toList());
	  return mapper.transform(transactionsList);
		 
	}
	
	/**
	 * Get a transaction 
	 * @param id
	 * @return TransactionResponse
	 */
	public TransactionResponse getTransactionById(Integer id) {
		logger.info("fetching record by id");
		 Optional<Transactions> op = repository.findById(id);
		 if(op.isPresent()) 
			 return mapper.transform(op.get());
		 logger.warn("no record found for "+id);
		throw new PaymentException("No record found for Id "+id);
	}
	
	/**
	 * Method to save a payment
	 * @param TransactionRequest
	 * @return TransactionResponse
	 */
	public TransactionResponse saveTransaction(TransactionRequest request) {
		logger.info("going to save record");
		Transactions transactions = mapper.buildRequest(request);
		Transactions result = repository.save(transactions);
		logger.info("record saved succesfully");
		return mapper.transform(result);
	}
	
	/**
	 * Update a transaction based on ID
	 * @param id
	 * @param TransactionRequest
	 * @return TransactionResponse
	 */
	public TransactionResponse updateTransaction(Integer id, TransactionRequest request) {
		logger.info("fetch data by id");
		TransactionResponse rowObject = getTransactionById(id);
		
		logger.info("validate request with existing table data");
		if (validationService.validateRequestWithTable(request, rowObject)) {
			logger.info("going to update record");
			Transactions transactions = mapper.buildRequest(request);
			transactions.setTransactionId(rowObject.getTransactionId());
			transactions.setId(id);
			transactions.setTransactionType(TransactionType.REFUND.name());
			Transactions result = repository.save(transactions);
			logger.info("record updated");	
			return mapper.transform(result);
		}
		
		throw new PaymentException("Details of the original transaction is changed " + rowObject.getTransactionId());
		
	}
	
	/**
	 * Delete a transaction by Id
	 * @param id
	 */
	public void deleteTransaction(Integer id) {
		repository.deleteById(id);
	}
	
}
