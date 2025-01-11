package org.hyeonqz.week3lotto.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.hyeonqz.week3lotto.dtos.LottoOutput;
import org.hyeonqz.week3lotto.entity.Lotto;
import org.hyeonqz.week3lotto.entity.LottoData;
import org.hyeonqz.week3lotto.entity.LottoWinning;
import org.hyeonqz.week3lotto.repository.LottoDataRepository;
import org.hyeonqz.week3lotto.repository.LottoRepository;
import org.hyeonqz.week3lotto.repository.LottoWinningRepository;
import org.hyeonqz.week3lotto.utils.LottoUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
public class LottoService {
	private final Logger log = Logger.getLogger("LottoService");

	private final LottoRepository lottoRepository;
	private final LottoDataRepository lottoDataRepository;
	private final LottoWinningRepository lottoWinningRepository;

	public LottoService (LottoRepository lottoRepository, LottoDataRepository lottoDataRepository,
		LottoWinningRepository lottoWinningRepository) {
		this.lottoRepository = lottoRepository;
		this.lottoDataRepository = lottoDataRepository;
		this.lottoWinningRepository = lottoWinningRepository;
	}

	@Transactional
	public LottoOutput.ResponseResult createLotto (int amount) {
		int count = countLotto(amount);
		List<Set<Integer>> lottoList = LottoUtils.issueLotto(count);
		LocalDateTime now = LocalDateTime.now();
		Lotto lotto = new Lotto("jin",count, amount, now);
		lottoRepository.save(lotto);

		String lottoStr = lottoListConvertString(lottoList);

		for (int i = 0; i < count; i++) {
			Set<Integer> lottoNumbers = lottoList.get(i);
			List<Integer> sortedNumbers = new ArrayList<>(lottoNumbers);
			Collections.sort(sortedNumbers);

			LottoData lottoData = new LottoData(lotto.getId(), sortedNumbers.toString());
			lottoDataRepository.save(lottoData);
		}

		LottoOutput.ResponseLottoData responseLottoData = new LottoOutput.ResponseLottoData(lottoStr);

		return new LottoOutput.ResponseResult("jin",count,amount,responseLottoData, now);
	}

	@Transactional
	public LottoOutput.ResponseWinning responseWinningNumber() {
		String winningNumber = LottoUtils.issueWinningLotto();

		LottoWinning lottoWinning = new LottoWinning(winningNumber);
		lottoWinningRepository.save(lottoWinning);

		return new LottoOutput.ResponseWinning(winningNumber);
	}

	@Transactional
	public LottoOutput.ResponseBonusNumber createBonusNumber() {
		// 당첨 번호를 조회한다.
		LottoWinning winning = lottoWinningRepository.findById(1L)
			.orElseThrow(() -> new RuntimeException("당첨번호가 존재하지 않습니다?"));

		String winningNumber = winning.getWinningNumber();

		// 당첨 번호에 있지 않은 수 중에서 랜덤으로 45이하의 수 1개를 생성한다.
		String bonus = LottoUtils.issueBonusNumber(winningNumber);
		winning.createBonusNumber(bonus);

		lottoWinningRepository.save(winning);

		return new LottoOutput.ResponseBonusNumber(bonus);
	}

	public LottoOutput.ResponseShowLottoData getLottoData() {
		List<LottoData> lottoData = lottoDataRepository.findLottoDataByLottoId(1L);

		List<String> list = new ArrayList<>();
		for (LottoData lottoDatum : lottoData) {
			list.add(lottoDatum.getLottoNum());
		}

		return new LottoOutput.ResponseShowLottoData(lottoData.size(), list);
	}

	private int countLotto (int amount) {
		return amount / 1000;
	}

	private String lottoListConvertString (List<Set<Integer>> lottoList) {
		return lottoList.stream()
			.map(String::valueOf)
			.collect(Collectors.joining(", "));
	}



}
