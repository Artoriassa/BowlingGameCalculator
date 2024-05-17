package com.example.bowlingGameCalculator.domain;

public class StrikeFrame extends Frame {
	public StrikeFrame() {
		super(10, 0);
	}

	@Override
	public int score() {
		return 10; // Score for a Strike frame
	}
}