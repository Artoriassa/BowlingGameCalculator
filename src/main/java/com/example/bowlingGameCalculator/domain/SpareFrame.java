package com.example.bowlingGameCalculator.domain;

public class SpareFrame extends Frame {
	public SpareFrame(int firstThrow) {
		super(firstThrow, 10 - firstThrow);
	}

	@Override
	public int score() {
		return 10; // Score for a Spare frame
	}
}