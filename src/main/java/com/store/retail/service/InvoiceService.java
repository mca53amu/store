package com.store.retail.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.retail.controller.InvoiceReponse;
import com.store.retail.entity.CustomerUser;
import com.store.retail.entity.Invoice;

@Service
public class InvoiceService {

	@Autowired
	private InvoiceRepository invoiceRepo;
	
	@Autowired
	private CustomerService customerService;

	public Optional<InvoiceReponse> getNetPayableAmount(String invoiceNumber) {
		Long invoiceId = Long.parseLong(invoiceNumber);
		Optional<Invoice> invoice = invoiceRepo.findById(invoiceId);
		if (invoice.isPresent()) {
			return Optional.ofNullable(buildInvoiceResponse(invoice.get()));

		}
		return Optional.ofNullable(null);
	}

	public Optional<Invoice> generateInvoiceNewCustomer(String userType) {
		Invoice invoice = new Invoice();
		invoice.setAmount(450);
		CustomerUser customerUser = getNewCustomer(userType);
		customerUser.setUserType(Discount.getDiscount(userType).get());
		invoice.setCustomerUser(customerUser);
		Invoice save = invoiceRepo.save(invoice);
		return Optional.ofNullable(save);

	}
	
	public Optional<Invoice> generateInvoiceForExistingCustomer(Long id,double amount) {
		Invoice invoice = new Invoice();
		invoice.setAmount(amount);
		CustomerUser customerUser = customerService.getCustomer(id).get();
		invoice.setCustomerUser(customerUser);
		Invoice save = invoiceRepo.save(invoice);
		return Optional.ofNullable(save);

	}

	private CustomerUser getNewCustomer(String userType) {
		CustomerUser customerUser = new CustomerUser();
		customerUser.setUserType(Discount.getDiscount(userType).get());
		return customerUser;
	}
	

	private InvoiceReponse buildInvoiceResponse(Invoice invoice) {
		String discountName = invoice.getCustomerUser().getUserType().getName();
		double amount = invoice.getAmount();
		Long invoiceNum = invoice.getInvoiceNumber();
		double netPayAmount = invoice.getNetPayAmount();
		double discountAmount = getDiscountAmount(invoice.getCustomerUser().getUserType(), amount);
		return new InvoiceReponse(invoiceNum, discountName, discountAmount, amount, netPayAmount);

	}

	private double getDiscountAmount(Discount discount, double amount) {
		amount = amount - (amount * discount.getPercentage() / 100);
		return amount;
	}

}
