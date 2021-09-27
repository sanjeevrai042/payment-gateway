package com.cds.demo.paymentserver.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cds.demo.paymentserver.entity.Transactions;

@Repository
public interface TransactionRepository extends CrudRepository<Transactions, Integer> {
	
	 @Override
     List<Transactions> findAll();

}
