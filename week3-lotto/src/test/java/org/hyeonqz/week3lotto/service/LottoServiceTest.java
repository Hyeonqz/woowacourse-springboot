package org.hyeonqz.week3lotto.service;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.hyeonqz.week3lotto.dtos.LottoOutput;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LottoServiceTest {

	@Autowired
	private LottoService lottoService;

	@Test
	@DisplayName("로또 구매 금액을 입력 받아 로또 를 구매한다.")
	void LottoCreateTest() {
	    // given
		int amount = 10000;

	    // when
		LottoOutput.ResponseResult lotto = lottoService.createLotto(amount);

		// then
		Assertions.assertThat(lotto).isNotNull();
	}

	@Test
	@DisplayName("로또 당첨 번호를 출력한다.")
	void responseWiningLottoTest() {
	    // given & when
		LottoOutput.ResponseWinning responseWinning = lottoService.responseWinningNumber();

		// then
		Assertions.assertThat(responseWinning).isNotNull();
	}

	@Test
	@DisplayName("보너스 번호를 출력한다.")
	void createBonusNumber() {
	    // given & when
		LottoOutput.ResponseWinning responseWinning = lottoService.responseWinningNumber();
		LottoOutput.ResponseBonusNumber bonusNumber = lottoService.createBonusNumber();

	    // then
		Assertions.assertThat(bonusNumber.bonusNumber()).isNotNull();
	}

	@Test
	@DisplayName("총 구매한 로또 개수와 로또 를 출력한다")
	void showTotalLotto() {
	    // given & when
		int amount = 10;
		LottoOutput.ResponseResult lotto = lottoService.createLotto(amount);
		LottoOutput.ResponseShowLottoData lottoData = lottoService.getLottoData();

		System.out.println(lottoData);

		// then
		Assertions.assertThat(lottoData).isNotNull();
	}



}