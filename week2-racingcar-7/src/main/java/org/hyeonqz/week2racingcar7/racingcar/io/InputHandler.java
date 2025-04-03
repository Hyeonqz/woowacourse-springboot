package org.hyeonqz.week2racingcar7.racingcar.io;

import java.util.List;

import camp.nextstep.edu.missionutils.Console;

public class InputHandler {

	public List<String> consoleInput () {
		printInput();
		String input = Console.readLine();
		isInputValidLength(input);
		return splitInput(input);
	}

	public String secondConsoleInput () {
		secondPrintInput();
		String moveNumberInput = Console.readLine();
		isValidMoveNumber(moveNumberInput);
		return moveNumberInput;
	}

	private void printInput () {
		System.out.println("경주할 자동차 이름을 입력하세요. (이름은 쉼표(,) 기준으로 구분");
	}

	private void isInputValidLength (String input) {
		List<String> list = splitInput(input);
		for(String str : list) {
			if(str.length() <= 5)
				continue;
			else
				throw new IllegalArgumentException("5글자 초과의 자동차 이름이 부여되었습니다.");
		}
	}

	private List<String> splitInput (String input) {
		return List.of(input.split(","));
	}

	private void secondPrintInput () {
		System.out.println("시도할 횟수는 몇 회인가요?");
	}

	private void isValidMoveNumber (String moveNum) {
		isValidMoveNumberLessThanTen(moveNum);
		isMoveNumberNegative(moveNum);
	}

	private void isValidMoveNumberLessThanTen(String moveNum) {
		if (Integer.parseInt(moveNum) >= 10)
			throw new IllegalArgumentException("자동차 이동 횟수는 9이하만 가능합니다. 게임을 종료합니다.");
	}


	private void isMoveNumberNegative (String moveNum) {
		if(Integer.parseInt(moveNum) < 0)
			throw new IllegalArgumentException("음수는 입력할 수 없습니다. 게임을 종료합니다.");
	}

}
