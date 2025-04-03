package org.hyeonqz.week2racingcar7.racingcar;

import java.util.List;

import racingcar.io.InputHandler;
import racingcar.io.OutputHandler;

public class Application {
	private final static InputHandler inputHandler = new InputHandler();
	private final static OutputHandler outputHandler = new OutputHandler();

	public static void main (String[] args) {
		List<String> carList = inputHandler.consoleInput();

		String input2 = inputHandler.secondConsoleInput();
	}

}
