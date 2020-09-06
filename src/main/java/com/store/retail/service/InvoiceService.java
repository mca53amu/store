package com.store.retail.service;

import java.util.Optional;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.retail.entity.CustomerUser;
import com.store.retail.entity.Discount;
import com.store.retail.entity.Invoice;
import com.store.retail.response.InvoiceReponse;

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

	public Optional<InvoiceReponse> generateInvoiceNewCustomer(String userType, double amount) {
		Invoice invoice = new Invoice();
		invoice.setAmount(amount);
		CustomerUser customerUser = getNewCustomer(userType);
		Discount userType2 = Discount.getDiscount(userType).get();
		customerUser.setUserType(userType2);
		customerService.save(customerUser);
		invoice.setCustomerUser(customerUser);
		invoiceRepo.save(invoice);
		return Optional.ofNullable(buildInvoiceResponse(invoice));

	}

	public Optional<InvoiceReponse> generateInvoiceForExistingCustomer(Long id, double amount) {
		Invoice invoice = new Invoice();
		invoice.setAmount(amount);
		CustomerUser customerUser = customerService.getCustomer(id).get();
		invoice.setCustomerUser(customerUser);
		invoiceRepo.save(invoice);
		return Optional.ofNullable(buildInvoiceResponse(invoice));

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
		double discountAmount = getDiscountAmount(invoice.getCustomerUser().getUserType(), amount);
		double netPayAmount = amount-discountAmount;
		invoice.setNetPayAmount(netPayAmount);
		return new InvoiceReponse(invoiceNum, discountName, discountAmount, amount, netPayAmount);

	}

	private double getDiscountAmount(Discount discount, double amount) {
		if (discount.getName().equals("NORMALUSER")) {
			int multiple=(int) (amount/100);
			double netDiscount =multiple*5;
			return netDiscount;
		}
		return (amount*discount.getPercentage() / 100);
	}

}
