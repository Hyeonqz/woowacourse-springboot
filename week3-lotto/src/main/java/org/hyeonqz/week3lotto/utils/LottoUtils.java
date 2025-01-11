package org.hyeonqz.week3lotto.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LottoUtils {

	// 로또 6자리 발행
	public static List<Set<Integer>> issueLotto (int count) {
		List<Set<Integer>> result = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			Set<Integer> lottoNumbers = new HashSet<>();
			while (lottoNumbers.size() < 6) {
				int num = (int) (Math.random() * 45) + 1;
				lottoNumbers.add(num);
			}
			result.add(lottoNumbers);
		}
		return result;
	}

	public static String issueWinningLotto() {
		var set = new HashSet<Integer>();

		while(set.size() < 6) {
			int num = (int) (Math.random() * 45) + 1;
			set.add(num);
		}

		return set.stream()
			.map(String::valueOf)
			.collect(Collectors.joining(", "));
	}

}
