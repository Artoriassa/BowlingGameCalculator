package com.example.bowlingGameCalculator;

import com.example.bowlingGameCalculator.domain.Frame;

public class Scorer {
	public int calculateFrameScore(Frame frame) {
		return frame.score();
	}
}