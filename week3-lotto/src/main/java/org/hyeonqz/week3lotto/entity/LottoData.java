package org.hyeonqz.week3lotto.entity;

import org.hibernate.annotations.Comment;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class LottoData {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Comment("lotto 발급 id, 원래는 연관관계를 가져야함.")
	private Long lottoId;

	@Comment("구매한 로또 번호")
	private String lottoNum;

	public LottoData (Long lottoId, String lottoNum) {
		this.lottoId = lottoId;
		this.lottoNum = lottoNum;
	}

}
