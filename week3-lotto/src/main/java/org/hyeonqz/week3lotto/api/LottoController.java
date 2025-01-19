package org.hyeonqz.week3lotto.api;

import org.hyeonqz.week3lotto.dtos.LottoOutput;
import org.hyeonqz.week3lotto.service.LottoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/lotto")
@RestController
public class LottoController {
	private final LottoService lottoService;

	public LottoController (LottoService lottoService) {
		this.lottoService = lottoService;
	}

	@PostMapping("/create/{amount}")
	public ResponseEntity<?> createLotto(@PathVariable int amount) {
		LottoOutput.ResponseResult lotto = lottoService.createLotto(amount);
		return ResponseEntity.ok(lotto);
	}

	@PostMapping("/create/winningNum")
	public ResponseEntity<?> responseWinningNum() {
		LottoOutput.ResponseWinning responseWinning = lottoService.createWinningNumber();
		return ResponseEntity.ok(responseWinning);
	}

	@PostMapping("/create/bonusNum")
	public ResponseEntity<?> responseBonusNum() {
		LottoOutput.ResponseBonusNumber bonusNumber = lottoService.createBonusNumber();
		return ResponseEntity.ok(bonusNumber);
	}

	@GetMapping("/response")
	public ResponseEntity<?> getLottoData() {
		LottoOutput.ResponseShowLottoData lottoData = lottoService.getLottoData();
		return ResponseEntity.ok(lottoData);
	}

	@GetMapping("/match")
	public ResponseEntity<?> getWinningMatchData() {
		LottoOutput.showMatchLottoData winningList = lottoService.getWinningList();
		return ResponseEntity.ok(winningList);
	}

	// 수익률을 구한다
	@GetMapping("/rate")
	public ResponseEntity<?> getRateData() {

		return ResponseEntity.ok("");
	}
}
