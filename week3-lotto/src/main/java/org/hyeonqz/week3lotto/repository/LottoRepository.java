package org.hyeonqz.week3lotto.repository;

import org.hyeonqz.week3lotto.entity.Lotto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LottoRepository extends JpaRepository<Lotto, Long> {
}
