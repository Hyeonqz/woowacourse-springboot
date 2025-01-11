package org.hyeonqz.week3lotto.api;

import org.hyeonqz.week3lotto.dtos.LottoOutput;
import org.hyeonqz.week3lotto.service.LottoService;
import org.springframework.http.ResponseEntity;
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

	@PostMapping("/create/{count}")
	public ResponseEntity<?> createLotto(@PathVariable int count) {
		LottoOutput.ResponseResult lotto = lottoService.createLotto(count);
		return ResponseEntity.ok(lotto);
	}

	@PostMapping("/response/winningNum")
	public ResponseEntity<?> responseWinningNum() {
		// 담배피고와서 당첨번호 요청 api 까지 만들기
		return ResponseEntity.ok("");
	}
}
