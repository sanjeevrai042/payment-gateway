package com.cds.demo.paymentserver;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.cds.demo.paymentserver.dao.TransactionRepository;
import com.cds.demo.paymentserver.dto.TransactionRequest;
import com.cds.demo.paymentserver.dto.TransactionResponse;
import com.cds.demo.paymentserver.entity.Transactions;
import com.cds.demo.paymentserver.mapper.PaymentObjectMapper;
import com.cds.demo.paymentserver.service.PaymentService;
import com.cds.demo.paymentserver.service.ValidationService;


@RunWith(MockitoJUnitRunner.class)
public class PaymentServiceTest {

	@InjectMocks
	private PaymentService paymentService;
	
	@Mock
	private TransactionRepository repository;
	
	@Mock 
	private PaymentObjectMapper mapper;
	
	@Mock
	private ValidationService validationService;
	
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetAllTransactions() {
		List<TransactionResponse> expected = getTransactionResponses();
		Mockito.when(repository.findAll()).thenReturn(getTransaction());
		Mockito.when(mapper.transform(getTransaction())).thenReturn(expected); 
		List<TransactionResponse> actual = paymentService.getAllTransactions();
		assertEquals(expected,actual);
	}

	@Test
	public void testGetTransactionById() {
		Integer id = new  Integer(100);
		Mockito.when(repository.findById(id)).thenReturn(getTransactionById());
		Mockito.when(mapper.transform(getTransactionById().get())).thenReturn(getTransactionResponse());
		TransactionResponse actual = paymentService.getTransactionById(id);
		assertEquals(actual, getTransactionResponse());
	}

	@Test
	public void testSaveTransaction() {
	}

	@Test
	public void testUpdateTransaction() {
	}

	@Test
	public void testDeleteTransaction() {
	}
	
	private List<Transactions> getTransaction(){
		Transactions t1 = Transactions
									.builder()
										.amount(new Float(100.00))
										.comment("Test comment")
										.customerName("Rahul Ghosh")
										.emailId("testemail@gmail.com")
										.id(new Integer(100))
										.mobileNumber("9815263621")
										.receivedBy("Gpay")
										.transactionMode("CreditCard")
										.transactionId("qwtruqwuqw1212uut")
									.build();
		Transactions t2 = Transactions
				.builder()
					.amount(new Float(100.00))
					.comment("Test comment")
					.customerName("Adam")
					.emailId("testemail@gmail.com")
					.id(new Integer(100))
					.mobileNumber("9815263621")
					.receivedBy("Gpay")
					.transactionMode("CreditCard")
					.transactionId("qwtruqwuqw1212uut")
				.build();
		
		List<Transactions> list = new ArrayList<>();
		list.add(t1);
		list.add(t2);
		return list;
	}
	
	private Optional<Transactions> getTransactionById(){
		
		Optional<Transactions> t1 = Optional.of(Transactions.builder()
				.amount(new Float(100.00))
				.comment("Test commenet")
				.customerName("Rahul Ghosh")
				.emailId("testemail@gmail.com")
				.id(new Integer(100))
				.mobileNumber("9815263621")
				.receivedBy("Gpay")
				.transactionMode("CreditCard")
				.transactionId("qwtruqwuqw1212uut")
			.build());
									
		
		return t1;
	}
	
	private List<Transactions> getTransactionList(){
		Transactions t1 = Transactions
									.builder()
										.amount(new Float(100.00))
										.comment("Test commenet")
										.customerName("Rahul Ghosh")
										.emailId("testemail@gmail.com")
										.id(new Integer(100))
										.mobileNumber("9815263621")
										.receivedBy("Gpay")
										.transactionMode("CreditCard")
										.transactionId("qwtruqwuqw1212uut")
									.build();
		Transactions t2 = Transactions
				.builder()
					.amount(new Float(100.00))
					.comment("Test commenet")
					.customerName("Adam")
					.emailId("testemail@gmail.com")
					.id(new Integer(100))
					.mobileNumber("9815263621")
					.receivedBy("Gpay")
					.transactionMode("CreditCard")
					.transactionId("qwtruqwuqw1212uut")
				.build();
		
		List<Transactions> list = new ArrayList<>();
		list.add(t1);
		list.add(t2);
		return list;
	}
	
	
	private List<TransactionResponse> getTransactionResponses(){
		List<TransactionResponse> list = new ArrayList<>();
		TransactionResponse t1 = TransactionResponse.builder()	.amount(new Float(100.00))
				.comment("Test commenet")
				.customerName("Rahul Ghosh")
				.emailId("testemail@gmail.com")
				.id(new Integer(100))
				.mobileNumber("9815263621")
				.receivedBy("Gpay")
				.transactionId("qwtruqwuqw1212uut")
				.build();
		TransactionResponse t2 = TransactionResponse.builder()	.amount(new Float(100.00))
				.comment("Test commenet")
				.customerName("Adam")
				.emailId("testemail@gmail.com")
				.id(new Integer(100))
				.mobileNumber("9815263621")
				.receivedBy("Gpay")
				.transactionId("qwtruqwuqw1212uut")
				.build();
		list.add(t1);
		list.add(t2);
		return list;
	}
	
	private TransactionResponse getTransactionResponse(){
		TransactionResponse t1 = TransactionResponse.builder()	
				.amount(new Float(100.00))
				.comment("Test comment")
				.customerName("Rahul Ghosh")
				.emailId("testemail@gmail.com")
				.id(new Integer(100))
				.mobileNumber("9815263621")
				.receivedBy("Gpay")
				.transactionId("qwtruqwuqw1212uut")
				.build();
		return t1;
	}
	
	private TransactionRequest getTransactionRequest(){
		TransactionRequest t1 = TransactionRequest.builder()	
				.amount(new Float(100.00))
				.comment("Test comment")
				.customerName("Rahul Ghosh")
				.emailId("testemail@gmail.com")
				.id(new Integer(100))
				.mobileNumber("9815263621")
				.receivedBy("Gpay")
				.build();
		return t1;
	}
}
