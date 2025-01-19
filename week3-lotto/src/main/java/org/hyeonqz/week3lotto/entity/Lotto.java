package org.hyeonqz.week3lotto.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.Comment;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Lotto {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Comment("로또 구매자 이름")
	private String userName;

	@Comment("로또 구매 갯수")
	private Integer count;

	@Comment("로또 구매 총 가격")
	private Integer amount;

	@Comment("로또 구매 날짜")
	private LocalDateTime createdTime;

	public Lotto (String userName, Integer count, Integer amount, LocalDateTime createdTime) {
		this.userName = userName;
		this.count = count;
		this.amount = amount;
		this.createdTime = createdTime;
	}

	public Long getId () {
		return id;
	}

	public Integer getAmount () {
		return amount;
	}

	public Lotto () {
	}

}
