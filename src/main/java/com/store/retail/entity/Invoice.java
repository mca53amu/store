package com.store.retail.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.store.retail.service.Discount;

@Entity
@NamedQueries({
		@NamedQuery(name = "Invoice.getInvoiceByCustomerId", query = "from Invoice  where customerUser.id =:customerId") })
public class Invoice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long invoiceNumber;
	private double amount;
	private double netPayAmount;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerId", referencedColumnName = "id")
	private CustomerUser customerUser;

	public Long getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(Long invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getNetPayAmount() {
		return netPayAmount;
	}

	public void setNetPayAmount(double netPayAmount) {
		this.netPayAmount = netPayAmount;
	}

	public CustomerUser getCustomerUser() {
		return customerUser;
	}

	public void setCustomerUser(CustomerUser customerUser) {
		this.customerUser = customerUser;
	}
}
