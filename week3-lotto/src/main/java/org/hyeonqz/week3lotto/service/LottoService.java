package org.hyeonqz.week3lotto.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.hyeonqz.week3lotto.dtos.LottoEnums;
import org.hyeonqz.week3lotto.dtos.LottoOutput;
import org.hyeonqz.week3lotto.entity.Lotto;
import org.hyeonqz.week3lotto.entity.LottoData;
import org.hyeonqz.week3lotto.entity.LottoWinning;
import org.hyeonqz.week3lotto.repository.LottoDataRepository;
import org.hyeonqz.week3lotto.repository.LottoRepository;
import org.hyeonqz.week3lotto.repository.LottoWinningRepository;
import org.hyeonqz.week3lotto.common.utils.LottoUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
public class LottoService {
	private static final Logger log = LoggerFactory.getLogger(LottoService.class);

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

			LottoData lottoData = new LottoData(lotto.getId(), sortedNumbers.toString().replaceAll("[\\[\\]]", ""));
			lottoDataRepository.save(lottoData);
		}

		LottoOutput.ResponseLottoData responseLottoData = new LottoOutput.ResponseLottoData(lottoStr);

		return new LottoOutput.ResponseResult("jin",count,amount,responseLottoData, now);
	}

	@Transactional
	public LottoOutput.ResponseWinning createWinningNumber () {
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

		return new LottoOutput.ResponseShowLottoData(lottoData.size() + "개를 구매했습니다.", list);
	}

	public LottoOutput.showMatchLottoData getWinningList () {
		LottoWinning lottoWinning = lottoWinningRepository.findById(1L)
			.orElseThrow(() -> new RuntimeException("조회된 당첨 번호 가 없습니다. 이번 회차 당첨 번호를 출력해주세요"));

		// 당첨 번호
		String winningNumber = lottoWinning.getWinningNumber();
		Set<Integer> winningNumbers = getWinningLottoNumbers(winningNumber);

		// 보너스 볼
		String bonusNumber = lottoWinning.getBonusNumber();

		Map<LottoEnums, Integer> resultCount = getLottoEnumsIntegerMap();

		List<LottoData> allLottoData = lottoDataRepository.findAll();

		for(LottoData lottoData : allLottoData) {
			Set<Integer> userNumbers = getLottoNumbersBuyUser(lottoData);

			long matchCount = getMatchLottoCount(userNumbers, winningNumbers);
			boolean hasBonus = userNumbers.contains(bonusNumber);

			LottoNumberCompare(matchCount, resultCount, hasBonus);
		}

		return new LottoOutput.showMatchLottoData(
			"당첨 통계",
			LottoEnums.THREE.getCount() + LottoEnums.THREE.getDescription() + resultCount.get(LottoEnums.THREE).toString(),
			LottoEnums.FOUR.getCount() + LottoEnums.FOUR.getDescription() + resultCount.get(LottoEnums.FOUR).toString() ,
			 LottoEnums.FIVE.getCount()+ LottoEnums.FIVE.getDescription() + resultCount.get(LottoEnums.FIVE).toString(),
			 LottoEnums.FIVE_BONUS.getCount()+ LottoEnums.FIVE_BONUS.getDescription() + resultCount.get(LottoEnums.FIVE_BONUS).toString(),
			 LottoEnums.SIX.getCount() + LottoEnums.SIX.getDescription() + resultCount.get(LottoEnums.SIX).toString()
		);
	}

	private void LottoNumberCompare (long matchCount, Map<LottoEnums, Integer> resultCount, boolean hasBonus) {
		if(matchCount ==6) {
			resultCount.put(LottoEnums.SIX, resultCount.get(LottoEnums.SIX) + 1);
		}
		if(matchCount ==5 && hasBonus) {
			resultCount.put(LottoEnums.FIVE_BONUS, resultCount.get(LottoEnums.FIVE_BONUS) + 1);
		}
		if(matchCount == 5) {
			resultCount.put(LottoEnums.FIVE, resultCount.get(LottoEnums.FIVE)+1);
		}
		if(matchCount == 4) {
			resultCount.put(LottoEnums.FOUR, resultCount.get(LottoEnums.FOUR)+1);
		}
		if(matchCount == 3) {
			resultCount.put(LottoEnums.THREE, resultCount.get(LottoEnums.THREE)+1);
		}
	}

	private Set<Integer> getLottoNumbersBuyUser (LottoData lottoData) {
		return Arrays.stream(lottoData.getLottoNum().split(","))
			.map(String::trim)
			.map(Integer::parseInt)
			.collect(Collectors.toSet());
	}

	private Set<Integer> getWinningLottoNumbers (String winningNumber) {
		return Arrays.stream(winningNumber.split(","))
			.map(String::trim)
			.map(Integer::parseInt)
			.collect(Collectors.toSet());
	}

	private static long getMatchLottoCount (Set<Integer> userNumbers, Set<Integer> winningNumbers) {
		return userNumbers.stream()
			.filter(winningNumbers::contains)
			.count();
	}

	private Map<LottoEnums, Integer> getLottoEnumsIntegerMap () {
		Map<LottoEnums, Integer> resultCount = new HashMap<>();
		for(LottoEnums value : LottoEnums.values()) {
			resultCount.put(value, 0);
		}
		return resultCount;
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
