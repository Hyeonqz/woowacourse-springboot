package org.hyeonqz.week3lotto.service;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.hyeonqz.week3lotto.dtos.LottoOutput;
import org.hyeonqz.week3lotto.entity.LottoData;
import org.hyeonqz.week3lotto.entity.LottoWinning;
import org.hyeonqz.week3lotto.repository.LottoDataRepository;
import org.hyeonqz.week3lotto.repository.LottoRepository;
import org.hyeonqz.week3lotto.repository.LottoWinningRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LottoServiceTest {

	@Autowired
	private LottoService lottoService;

	@Autowired
	private LottoRepository lottoRepository;

	@Autowired
	private LottoDataRepository lottoDataRepository;

	@Autowired
	private LottoWinningRepository lottoWinningRepository;

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
		LottoOutput.ResponseWinning responseWinning = lottoService.createWinningNumber();

		// then
		Assertions.assertThat(responseWinning).isNotNull();
	}

	@Test
	@DisplayName("보너스 번호를 출력한다.")
	void createBonusNumber() {
	    // given & when
		LottoOutput.ResponseWinning responseWinning = lottoService.createWinningNumber();
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

	@Test
	@DisplayName("내 로또번호와 당첨번호를 맞혀보고 결과를 출력한다.")
	void showMatchResultLotto() {
	    // given
		lottoService.createLotto(100000);
	    lottoService.createWinningNumber();
		lottoService.createBonusNumber();

		lottoService.getWinningList();

	    // when
		LottoWinning lottoWinning = lottoWinningRepository.findById(1L)
			.orElseThrow(() -> new RuntimeException("당첨 번호 가 없습니다."));

		List<LottoData> all = lottoDataRepository.findAll();

		for (int i = 0; i < all.size() ; i++) {
			String lottoNum = all.get(i).getLottoNum().replaceAll("[^0-9]"," "); // 1 3 23 25 26 38
			System.out.println("내 로또 번호 : " + lottoNum);

			String winning = lottoWinning.getWinningNumber().replaceAll("[^0-9]"," ");
			System.out.println("이번 회차 당첨 번호: " + winning);

			for (int j = 0; j < winning.length(); j++) {
				// 내가 구매한 로또 번호를 기준으로 이번 회차 당첨 번호랑 비교를 해야 한다.
			}
			// winning 기준으로 lottoNum 이 맞는게 있는지 로직 찾기.
		}

	    // then
	}

	@Test
	@DisplayName("로또 당첨 번호와 내 당첨번호를 맞춰본 후 결과를 출력한다.")
	void getWinningList() {
	    // given
		lottoService.createLotto(5000);
		lottoService.createWinningNumber();
		lottoService.createBonusNumber();

		// when
		LottoOutput.showMatchLottoData winningList = lottoService.getWinningList();
		System.out.println(winningList);

		// then
		Assertions.assertThat(winningList).isNotNull();
	}

	@Test
	@DisplayName("로또 수익률을 구한다.")
	void getMatchRate() {
	    // given
		lottoService.createLotto(10000);
		lottoService.createWinningNumber();
		lottoService.createBonusNumber();

		// when
		lottoService.getWinningList();
		LottoOutput.showMatchRate matchRate = lottoService.getMatchRate();

		// then
		Assertions.assertThat(matchRate).isNotNull();
	}

}