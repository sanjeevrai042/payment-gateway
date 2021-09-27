package com.cds.demo.paymentserver.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cds.demo.paymentserver.dto.TransactionRequest;
import com.cds.demo.paymentserver.dto.TransactionResponse;
import com.cds.demo.paymentserver.service.PaymentService;

@RestController
public class PaymentController {

	private static Logger logger = LoggerFactory.getLogger(PaymentController.class);

	@Autowired
	private PaymentService service;

	@GetMapping("/payments/{id}")
	public ResponseEntity<TransactionResponse> findById(@PathVariable("id") Integer id) {
		logger.info("received request for listing a particular transaction");
		return new ResponseEntity<>(service.getTransactionById(id), HttpStatus.OK);
	}

	@GetMapping("/payments")
	public ResponseEntity<List<TransactionResponse>> findAll() {
		logger.info("received request for listing all transactions");
		return new ResponseEntity<>(service.getAllTransactions(), HttpStatus.OK);
	}

	@PostMapping("/payments")
	public ResponseEntity<TransactionResponse> save(@RequestBody TransactionRequest transactionRequest) {
		logger.info("received request for transaction save");
		return new ResponseEntity<TransactionResponse>(service.saveTransaction(transactionRequest), HttpStatus.CREATED);
	}

	@PutMapping("/payments/{id}")
	public ResponseEntity<TransactionResponse> update(@PathVariable("id") Integer id, @RequestBody TransactionRequest transactionRequest) {
		return new ResponseEntity(service.updateTransaction(id, transactionRequest), HttpStatus.OK);
	}

	@DeleteMapping("/payments/{id}")
	public ResponseEntity delete(@PathVariable("id") Integer id) {
		service.deleteTransaction(id);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
}
