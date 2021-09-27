package com.cds.demo.paymentserver.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="authorization")
@Data
public class User {

	@Id
	private String username;
	
	@Column
	private String password;
	
	
}
