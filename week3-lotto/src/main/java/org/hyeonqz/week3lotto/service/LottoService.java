package org.hyeonqz.week3lotto.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.hyeonqz.week3lotto.dtos.LottoOutput;
import org.hyeonqz.week3lotto.entity.Lotto;
import org.hyeonqz.week3lotto.entity.LottoData;
import org.hyeonqz.week3lotto.repository.LottoDataRepository;
import org.hyeonqz.week3lotto.repository.LottoRepository;
import org.hyeonqz.week3lotto.utils.LottoUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
public class LottoService {
	private final LottoRepository lottoRepository;
	private final LottoDataRepository lottoDataRepository;

	private Logger log = Logger.getLogger("LottoService");

	public LottoService (LottoRepository lottoRepository, LottoDataRepository lottoDataRepository) {
		this.lottoRepository = lottoRepository;
		this.lottoDataRepository = lottoDataRepository;
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
			LottoData lottoData = new LottoData(lotto.getId(), lottoList.get(i).toString());
			lottoDataRepository.save(lottoData);
		}

		LottoOutput.ResponseLottoData responseLottoData = new LottoOutput.ResponseLottoData(lottoStr);

		return new LottoOutput.ResponseResult("jin",count,amount,responseLottoData, now);
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
