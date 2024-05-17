package com.example.bowlingGameCalculator.domain;

public abstract class Frame {
	public int firstThrow;
	public int secondThrow;

	public Frame(int firstThrow, int secondThrow) {
		this.firstThrow = firstThrow;
		this.secondThrow = secondThrow;
	}

	public abstract int score();
}
