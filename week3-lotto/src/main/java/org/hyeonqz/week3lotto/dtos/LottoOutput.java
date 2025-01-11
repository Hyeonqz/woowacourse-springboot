package org.hyeonqz.week3lotto.dtos;

import java.time.LocalDateTime;

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

}
