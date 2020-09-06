/**
 * 
 */
package com.store.retail.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.store.retail.entity.Invoice;
import com.store.retail.service.InvoiceService;

/**
 * @author Mohammad Miyan
 *
 */
@RestController
@RequestMapping("/store")
public class StoreController {

	@Autowired
	private InvoiceService invoiceService;

	@RequestMapping(value = { "/invoice/{invoiceNumber}" }, produces = "application/json")
	public ResponseEntity<?> getNetPayableAmount(@PathVariable(name = "invoiceNumber") String invoiceNumber) throws Exception {

		Optional<InvoiceReponse> netPayable = invoiceService.getNetPayableAmount(invoiceNumber);

		if (netPayable.isPresent()) {
			return ResponseEntity.ok(netPayable.get());
		} else {
			throw new Exception("Invoice not found");
		}

	}
	
	@RequestMapping(value = { "/invoice/{customerId}/{amount}" }, produces = "application/json")
	public ResponseEntity<?> generateInvoiceForExistingCustomer(@PathVariable(name = "customerId") Long customerId,@PathVariable("amount") double amount) throws Exception {

		Optional<InvoiceReponse> invoice = invoiceService.generateInvoiceForExistingCustomer(customerId,amount);

		if (invoice.isPresent()) {
			return ResponseEntity.ok(invoice);
		} else {
			throw new Exception("Invoice not found");
		}

	}
	
	@RequestMapping(value = { "/customer/{userType}/{amount}" }, produces = "application/json")
	public ResponseEntity<?> generateInvoiceForNewCustomer(@PathVariable(name = "userType") String userType, @PathVariable("amount") double amount) throws Exception {

		Optional<InvoiceReponse> invoice = invoiceService.generateInvoiceNewCustomer(userType,amount);
		if (invoice.isPresent()) {
			return ResponseEntity.ok(invoice.get());
		} else {
			throw new Exception("Invoice not found");
		}

	}
}