package com.example.bowlingGameCalculator.domain;

public class OpenFrame extends Frame {
	public OpenFrame(int firstThrow, int secondThrow) {
		super(firstThrow, secondThrow);
	}

	@Override
	public int score() {
		return firstThrow + secondThrow; // Score for an Open frame
	}
}