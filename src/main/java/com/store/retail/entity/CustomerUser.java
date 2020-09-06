package com.store.retail.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.store.retail.service.Discount;

@Entity
public class CustomerUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Enumerated(EnumType.STRING)
	private Discount userType;
	@OneToMany(mappedBy = "customerUser", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Invoice> invoices;

	public Long getCustomerId() {
		return id;
	}

	public void setCustomerId(Long id) {
		this.id = id;
	}

	public Discount getUserType() {
		return userType;
	}

	public void setUserType(Discount userType) {
		this.userType = userType;
	}

	public Set<Invoice> getInvoices() {
		return invoices;
	}

	public void setInvoices(Set<Invoice> invoices) {
		this.invoices = invoices;
	}

}
