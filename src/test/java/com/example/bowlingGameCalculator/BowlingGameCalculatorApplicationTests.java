package com.example.bowlingGameCalculator;

import com.example.bowlingGameCalculator.domain.Frame;
import com.example.bowlingGameCalculator.domain.OpenFrame;
import com.example.bowlingGameCalculator.domain.SpareFrame;
import com.example.bowlingGameCalculator.domain.StrikeFrame;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BowlingGameCalculatorApplicationTests {

	@Test
    public void testGutterGame() {
        Frame[] frames = new Frame[10]; // Assuming 10 frames in a game

        // Populate frames array with OpenFrame instances with both throws as 0
        for (int i = 0; i < 10; i++) {
            frames[i] = new OpenFrame(0, 0);
        }

        BowlingGameCalculator calculator = new BowlingGameCalculator();
        int totalScore = calculator.calculateTotalScore(frames);

        // Verify the final score is 0 for a gutter game
        assertEquals(0, totalScore);
    }

	@Test
    public void testAllOnesGame() {
        Frame[] frames = new Frame[10]; // Assuming 10 frames in a game

        // Populate frames array with OpenFrame instances with both throws as 1
        for (int i = 0; i < 10; i++) {
            frames[i] = new OpenFrame(1, 1);
        }

        BowlingGameCalculator calculator = new BowlingGameCalculator();
        int totalScore = calculator.calculateTotalScore(frames);

        // Verify the final score is 20 for a game of all ones
        assertEquals(20, totalScore);
    }

	@Test
    public void testOneSpareGame() {
        Frame[] frames = new Frame[10]; // Assuming 10 frames in a game

        // Populate frames array with SpareFrame instance with first throw as 5
        frames[0] = new SpareFrame(5);

		// Populate frames array with OpenFrame instance with both throws as 5 for the second frame
        frames[1] = new OpenFrame(5, 5);

        // Populate the rest of the frames with OpenFrame instances with both throws as 0
        for (int i = 2; i < 10; i++) {
            frames[i] = new OpenFrame(0, 0);
        }

        BowlingGameCalculator calculator = new BowlingGameCalculator();
        int totalScore = calculator.calculateTotalScore(frames);

        // Verify the final score is 10 for a game with one spare
        assertEquals(25, totalScore);
    }

	@Test
    public void testOneStrikeGame() {
        Frame[] frames = new Frame[10]; // Assuming 10 frames in a game

        // Populate frames array with StrikeFrame instance for the first frame
        frames[0] = new StrikeFrame();

        // Populate frames array with OpenFrame instance with both throws as 5 for the second frame
        frames[1] = new OpenFrame(5, 5);

        // Populate the rest of the frames with OpenFrame instances with both throws as 0
        for (int i = 2; i < 10; i++) {
            frames[i] = new OpenFrame(0, 0);
        }

        BowlingGameCalculator calculator = new BowlingGameCalculator();
        int totalScore = calculator.calculateTotalScore(frames);

        // Verify the final score is 20 for a game with one strike and second frame score of 10
        assertEquals(30, totalScore);
    }

	@Test
    public void testPerfectGame() {
        Frame[] frames = new Frame[11]; // Assuming 10 frames in a game

        // Populate frames array with 10 StrikeFrame instances
        for (int i = 0; i < 10; i++) {
            frames[i] = new StrikeFrame();
        }

        // Add extra frame for the 11th and 12th rolls for a perfect game
        frames[10] = new StrikeFrame();

        BowlingGameCalculator calculator = new BowlingGameCalculator();
        int totalScore = calculator.calculateTotalScore(frames);

        // Verify the final score is 300 for a perfect game
        assertEquals(300, totalScore);
    }

	@Test
    public void testRandomGame() {
        Frame[] frames = new Frame[10]; // Assuming 10 frames in a game

        // Populate frames array with a mix of StrikeFrame, SpareFrame, and OpenFrame instances
        frames[0] = new StrikeFrame();
        frames[1] = new SpareFrame(7);
        frames[2] = new OpenFrame(4, 2);
        frames[3] = new StrikeFrame();
        frames[4] = new SpareFrame(5);
        frames[5] = new OpenFrame(6, 3);
        frames[6] = new StrikeFrame();
        frames[7] = new OpenFrame(8, 1);
        frames[8] = new StrikeFrame();
        frames[9] = new SpareFrame(9);

        BowlingGameCalculator calculator = new BowlingGameCalculator();
        int totalScore = calculator.calculateTotalScore(frames);

        // Calculate the expected total score based on the provided frames
        int expectedScore = 20 + 14 + 6 + 20 + 16 + 9 + 19 + 9 + 20 + 10;

        // Verify the final score matches the expected total score
        assertEquals(expectedScore, totalScore);
    }

	@Test
    public void testIncompleteLastFrame() {
        Frame[] frames = new Frame[9]; // Assuming 10 frames in a game

        // Populate frames array with a mix of StrikeFrame, SpareFrame, and OpenFrame instances
        frames[0] = new StrikeFrame();
        frames[1] = new SpareFrame(7);
        frames[2] = new OpenFrame(4, 2);
        frames[3] = new StrikeFrame();
        frames[4] = new SpareFrame(5);
        frames[5] = new OpenFrame(6, 3);
        frames[6] = new StrikeFrame();
        frames[7] = new OpenFrame(8, 1);
        frames[8] = new StrikeFrame();
        
        // The last frame is incomplete

        BowlingGameCalculator calculator = new BowlingGameCalculator();
        int totalScore = calculator.calculateTotalScore(frames);

        // Calculate the expected total score based on the provided frames
        int expectedScore = 20 + 14 + 6 + 20 + 16 + 9 + 19 + 9 + 10;


        // Verify the final score matches the expected total score
        assertEquals(expectedScore, totalScore);
    }

	@Test
    public void testSixStrikesInARow() {
        Frame[] frames = new Frame[10]; // Assuming 10 frames in a game

        // Populate frames array with six consecutive StrikeFrames
        for (int i = 0; i < 6; i++) {
            frames[i] = new StrikeFrame();
        }

        // Last four frames with two bonus throws each to complete the game
        for (int i = 6; i < 10; i++) {
            frames[i] = new OpenFrame(0,0);
        }

        BowlingGameCalculator calculator = new BowlingGameCalculator();
        int totalScore = calculator.calculateTotalScore(frames);

        // Calculate the expected total score based on the provided frames (six consecutive strikes)
        int expectedScore = 150;

        // Verify the final score matches the expected total score for six consecutive strikes
        assertEquals(expectedScore, totalScore);
    }

	@Test
    public void testSevenSparesInARow() {
        Frame[] frames = new Frame[10]; // Assuming 10 frames in a game

        // Populate frames array with seven consecutive SpareFrames
        for (int i = 0; i < 7; i++) {
            frames[i] = new SpareFrame(5); // Each frame is a spare with 5 pins each throw
        }

        frames[7] = new OpenFrame(5, 2);
        frames[8] = new OpenFrame(1,1);
        frames[9] = new OpenFrame(2,2);

        BowlingGameCalculator calculator = new BowlingGameCalculator();
        int totalScore = calculator.calculateTotalScore(frames);

        // Calculate the expected total score based on the provided frames (seven consecutive spares)
        int expectedScore = 118;

        // Verify the final score matches the expected total score for seven consecutive spares
        assertEquals(expectedScore, totalScore);
    }

	@Test
    public void testMixedGame() {
        Frame[] frames = new Frame[10]; // Assuming 10 frames in a game

        frames[0] = new StrikeFrame(); // Frame 1 is a strike
        frames[1] = new SpareFrame(5); // Frame 2 is a spare with 5 pins each throw
        frames[2] = new OpenFrame(3, 2); // Frame 3 is an open frame with 3 and 2 pins knocked down
        frames[3] = new StrikeFrame(); // Frame 4 is a strike
        frames[4] = new SpareFrame(8); // Frame 5 is a spare with 8 and 2 pins knocked down
        frames[5] = new OpenFrame(6, 3); // Frame 6 is an open frame with 6 and 3 pins knocked down
        frames[6] = new StrikeFrame(); // Frame 7 is a strike
        frames[7] = new OpenFrame(5, 2); // Frame 8 is an open frame with 5 and 2 pins knocked down
        frames[8] = new SpareFrame(9); // Frame 9 is a spare with 9 and 1 pins knocked down
        frames[9] = new OpenFrame(4, 4); // Frame 10 is an open frame with 4 and 4 pins knocked down

        BowlingGameCalculator calculator = new BowlingGameCalculator();
        int totalScore = calculator.calculateTotalScore(frames);

        // Calculate the expected total score based on the provided frames
        // The total score calculation depends on the specific combination of strikes, spares, and open frames
        int expectedScore = 20 + 13 + 5 + 20 + 16 + 9 + 17 + 7 + 14 +8;

        // Verify the final score matches the expected total score for the mixed game
        assertEquals(expectedScore, totalScore);
    }
}
