package com.store.retail.service;

import java.util.Optional;

public enum Discount {
	STAFF("STAFF", 10), AFFILIATE("AFFILIATE", 20), EXISTINGUSER("EXISTINGUSER", 30), NORMALUSER("NORMALUSER", 40);

	private final String name;
	private final int percentage;

	private Discount(final String name, int percentage) {
		this.name = name;
		this.percentage = percentage;
	}

	static Optional<Discount> getDiscount(String userType) {

		for (Discount discount : Discount.values()) {
			if (discount.getName().equalsIgnoreCase(userType)) {
				return Optional.ofNullable(discount);
			}

		}
		return Optional.empty();
	}

	public String toString() {
		return name + " " + percentage;
	}

	public String getName() {
		return name;
	}

	public int getPercentage() {
		return percentage;
	}

}
