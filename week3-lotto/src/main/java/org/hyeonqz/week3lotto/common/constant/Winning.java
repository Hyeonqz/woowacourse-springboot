package org.hyeonqz.week3lotto.common.constant;

public enum Winning {
	THREE(3, "5,000원"),
	FOUR(4, "50,000원"),
	FIVE(5, "1,500,000원"),
	FIVE_BONUS(5, "30,000,000원"),
	SIX(6, "2,000,000,000원");

	private final int size;
	private final String description;

	Winning(int size, String description) {
		this.size = size;
		this.description = description;
	}

	public int getSize() {
		return size;
	}

	public String getDescription() {
		return description;
	}
}