package org.hyeonqz.week3lotto.utils;

import java.util.HashSet;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoUtilsTest {
	@Test
	@DisplayName("중복되지않는 로또 번호를 6개를 생성한다.")
	void createRandomLottoNumberTest () {
		// given
		var set = new HashSet<Integer>();

		for (int i = 0; i < 5; i++) {
			int num = (int)(Math.random() * 45) + 1;
			set.add(num);
		}

		while(set.size() != 6) {
			int num = (int)(Math.random() * 45) + 1;
			set.add(num);
		}

		// then
		Assertions.assertThat(set).hasSize(6);
	}
}
