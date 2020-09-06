package com.store.retail.response;

public class InvoiceReponse {
	
	private Long invoiceNumber;
	private String discountType;
	private double discountAmount;
	private double totalAmount;
	private double netPayAmount;
	
	public InvoiceReponse(Long invoiceNumber, String discountType, double discountAmount, double totalAmount,
			double netPayAmount) {
		super();
		this.invoiceNumber = invoiceNumber;
		this.discountType = discountType;
		this.discountAmount = discountAmount;
		this.totalAmount = totalAmount;
		this.netPayAmount = netPayAmount;
	}

	public Long getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(Long invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public String getDiscountType() {
		return discountType;
	}

	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}

	public double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public double getNetPayAmount() {
		return netPayAmount;
	}

	public void setNetPayAmount(double netPayAmount) {
		this.netPayAmount = netPayAmount;
	}
	
	

}
