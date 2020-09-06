package com.store.retail.entity;

import java.util.Optional;

public enum Discount {
	STAFF("STAFF", 30), AFFILIATE("AFFILIATE", 10), EXISTINGUSER("EXISTINGUSER", 5), NORMALUSER("NORMALUSER", 0);

	private final String name;
	private final int percentage;

	private Discount(final String name, int percentage) {
		this.name = name;
		this.percentage = percentage;
	}

	public static Optional<Discount> getDiscount(String userType) {

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
