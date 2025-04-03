package org.hyeonqz.week2racingcar7.racingcar.io;

import static org.assertj.core.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InputHandlerTest {
	private InputHandler inputHandler;

	@BeforeEach
	void setUp () {
		inputHandler = new InputHandler();
	}

	@AfterEach
	void afterEach() {
		inputHandler = null;
	}


	@Test
	@DisplayName("입력받은 자동차 이름이 , 으로 나뉜다")
	void splitAndSaveList () {
		// given
		String input = "pobi,woni,juni";
		System.setIn(new ByteArrayInputStream(input.getBytes()));

		// when
		List<String> strings = inputHandler.consoleInput();

		// then
		assertThat(strings).hasSize(3);
		assertThat(strings).containsExactly("pobi", "woni", "juni");
	}

	@Test
	@DisplayName("입력받은 자동차 이름이 5글자가 초과되면 예외가 발생한다")
	void isInputValidLength () {
		// given
		String input = "pobi,woni,junisssss";
		System.setIn(new ByteArrayInputStream(input.getBytes()));

		// when

		// then
		assertThatThrownBy(() -> inputHandler.consoleInput())
			.hasMessage("5글자 초과의 자동차 이름이 부여되었습니다.")
		;
	}

	@Test
	@DisplayName("이동횟수가 10이상을 입력하면 예외가 발생한다.")
	void isValidMoveNumberLessThanTen () {
		// given
		String input = "20";
		System.setIn(new ByteArrayInputStream(input.getBytes()));

		// when

		// then
		assertThatThrownBy(() -> inputHandler.secondConsoleInput())
			.hasMessage("자동차 이동 횟수는 9이하만 가능합니다. 게임을 종료합니다.")
		;
	}

	@Test
	@DisplayName("음수를 입력받으면 예외가 발생한다.")
	void isMoveNumberNegative () {
		// given
		String input = "-20";
		System.setIn(new ByteArrayInputStream(input.getBytes()));

		// then
		assertThatThrownBy(() -> inputHandler.secondConsoleInput())
			.hasMessage("음수는 입력할 수 없습니다. 게임을 종료합니다.")
		;
	}

}