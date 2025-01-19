package org.hyeonqz.week3lotto.dtos;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import org.hyeonqz.week3lotto.entity.LottoData;

public class LottoOutput {

	public record ResponseLottoData(
		String lottoNum
	) {

	}

	public record ResponseResult(
		String userName,
		Integer count,
		Integer amount,
		ResponseLottoData responseLottoData,
		LocalDateTime createdTime
	) {

	}

	public record ResponseWinning(
		String winning
	) {

	}

	public record ResponseBonusNumber(
		String bonusNumber
	) {

	}

	public record ResponseShowLottoData(
		String count,
		List<String> lottoNum
	) {

	}

	public record showMatchLottoData(
		String statistics,
		String three,
		String four,
		String five,
		String fiveBonus,
		String six
	) {


	}

	public record showMatchRate(
		String rate
	) {

	}

}
