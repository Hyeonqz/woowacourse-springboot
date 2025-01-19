package org.hyeonqz.week3lotto.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LottoException extends RuntimeException {

	private static final Logger log = LoggerFactory.getLogger(LottoException.class);

	public LottoException () {
		super();
	}

	public LottoException (String message) {
		super(message);
		log.error("로또 번호는 1부터 45 사이의 숫자여야 합니다.");
	}

	public LottoException (String message, Throwable cause) {
		super(message, cause);
	}

	public LottoException (Throwable cause) {
		super(cause);
	}

	protected LottoException (String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
