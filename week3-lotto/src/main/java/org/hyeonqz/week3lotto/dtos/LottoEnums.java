package org.hyeonqz.week3lotto.dtos;

public enum LottoEnums {
	THREE("3 ","개 일치 (5,000원) - "),
	FOUR("4 ","개 일치 (50,000원) - "),
	FIVE("5 ","개 일치 (1,500,000원) - "),
	FIVE_BONUS("5 ","개 일치, 보너스 볼 일치 (30,000,000원) - "),
	SIX("6 ","개 일치 (2,000,000,000원) - "),
	;


	private final String count;
	private final String description;

	LottoEnums (String count, String description) {
		this.count = count;
		this.description = description;
	}

	public String getCount () {
		return count;
	}

	public String getDescription () {
		return description;
	}
}
