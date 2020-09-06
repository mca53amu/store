package com.store.retail.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.retail.entity.CustomerUser;
import com.store.retail.entity.Invoice;

@Service
public class CustomerService {

	@Autowired
	CustomerRepository customerRepo;

	public Invoice loadInvoice(String invoiceNumber) {

		return new Invoice();
	}

	public Optional<CustomerUser> getCustomer(Long customerId) {
		return customerRepo.findById(customerId);
	}

}
