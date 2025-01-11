package org.hyeonqz.week3lotto.repository;

import java.util.List;

import org.hyeonqz.week3lotto.entity.LottoData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LottoDataRepository extends JpaRepository<LottoData, Long> {
	List<LottoData> findLottoDataByLottoId(Long lottoId);
}
