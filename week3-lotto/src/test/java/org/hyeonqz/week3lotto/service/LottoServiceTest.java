package org.hyeonqz.week3lotto.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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



}