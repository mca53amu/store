/**
 * 
 */
package com.store.retail.config;

import java.util.Optional;

/**
 * @author Mohammad Miyan
 *
 */
public enum UserTypeEnum {

	STAFF("STAFF1"), AFFILIATE("AFFILIATE"), EXISTINGUSER("EXISTINGUSER"), NORMALUSER("NORMALUSER");

	private final String userType;

	private UserTypeEnum(final String userType) {
		this.userType = userType;
	}

	public static Optional<UserTypeEnum> valueOfLabel(String label) {
		for (UserTypeEnum e : values()) {
			if (e.userType.equals(label)) {
				return Optional.ofNullable(e);
			}
		}
		return null;
	}

}