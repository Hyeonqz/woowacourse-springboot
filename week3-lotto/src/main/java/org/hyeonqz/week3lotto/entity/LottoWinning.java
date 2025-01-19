package org.hyeonqz.week3lotto.entity;

import org.hibernate.annotations.Comment;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class LottoWinning {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Comment("당첨번호")
	private String winningNumber;

	@Comment("보너스 번호")
	private String bonusNumber;

	public LottoWinning (String winningNumber) {
		this.winningNumber = winningNumber;
	}

	public String getWinningNumber () {
		return winningNumber;
	}

	public String getBonusNumber () {
		return bonusNumber;
	}

	public LottoWinning () {
	}

	public void createBonusNumber(String bonusNumber) {
		this.bonusNumber = bonusNumber;
	}

}
