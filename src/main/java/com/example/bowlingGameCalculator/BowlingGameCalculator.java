package com.example.bowlingGameCalculator;

import com.example.bowlingGameCalculator.domain.Frame;
import com.example.bowlingGameCalculator.domain.SpareFrame;
import com.example.bowlingGameCalculator.domain.StrikeFrame;

public class BowlingGameCalculator {
    public int calculateTotalScore(Frame[] frames) {
        int totalScore = 0;

        for (int i = 0; i < frames.length; i++) {
            totalScore += calculateFrameScore(frames, i);
        }

        return totalScore;
    }

    private int calculateFrameScore(Frame[] frames, int index) {
        Scorer scorer = new Scorer();
        int frameScore = 0;

        if (frames[index] instanceof StrikeFrame) {
            frameScore += 10 + calculateStrikeBonus(frames, index);
        } else if (frames[index] instanceof SpareFrame) {
            frameScore += 10 + calculateSpareBonus(frames, index);
        } else {
            frameScore += scorer.calculateFrameScore(frames[index]);
        }

        return frameScore;
    }

    private int calculateStrikeBonus(Frame[] frames, int index) {
        Scorer scorer = new Scorer();
        int bonus = 0;

        if (index < frames.length - 1) {
            bonus += scorer.calculateFrameScore(frames[index + 1]);
            if (frames[index + 1] instanceof StrikeFrame && index < frames.length - 2) {
                bonus += frames[index + 2].firstThrow;
            }
        }

        return bonus;
    }

    private int calculateSpareBonus(Frame[] frames, int index) {
        if (index < frames.length - 1) {
            return frames[index + 1].firstThrow;
        }
        return 0;
    }
}