package Test.UnitTests.ModelTests.horizontalStreakTests;
import static org.junit.Assert.assertEquals;



import org.junit.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import game.Model;
import interfaces.IModel;
import util.GameSettings;
/**
 * Unit test class for horizontal win detection
 * The current parameters required for the method are 
 *  - the col and row of the placed token
 *  - the active player that made the move
 *  - the board  
 */
public class winDetectionTests extends Model{
    GameSettings testSettings;


    @AfterEach
    protected void cleanUp(){
        super.resetModel();
    }
    @Test
    public void checkForHorizontalStreakTest1(){
        this.testSettings = new GameSettings(6,7,4); 
        super.initNewGame(testSettings);
        // testing board
        // - - - - -
        // O - - - -
        // O - - - -
        // O - - - -
        // x x x x -
        byte[][] board = {
            {0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0},
            {2,0,0,0,0,0,0},
            {2,0,0,0,0,0,0},
            {2,0,0,0,0,0,0},
            {1,1,1,1,0,0,0},
        };
        int activePlayer = 1;
        super.checkForHorizontalStreak(board,activePlayer,5,3);
        assertEquals(IModel.GAME_STATUS_WIN_1, getGameStatus());
    }
    @Test
    public void checkForHorizontalStreakTest2(){
        this.testSettings = new GameSettings(6,7,4); 
        super.initNewGame(testSettings);
        // testing board
        // - - - - -
        // O - - - -
        // O - - - -
        // O - - - -
        // x x x x -
        byte[][] board = {
            {0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0},
            {2,0,0,0,0,0,0},
            {2,0,0,0,0,0,0},
            {2,1,1,1,1,0,0},
        };
        int activePlayer = 1;
        super.checkForHorizontalStreak(board,activePlayer,5,4);
        assertEquals(IModel.GAME_STATUS_WIN_1, getGameStatus());
    }

    @Test
    public void checkForHorizontalStreakTest3(){
        this.testSettings = new GameSettings(6,7,4); 
        super.initNewGame(testSettings);
        // testing board
        // - - - - -
        // O - - - -
        // O - - - -
        // O - - - -
        // x x x x -
        byte[][] board = {
            {0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0},
            {1,2,2,2,1,1,1},
        };
        int activePlayer = 1;
        super.checkForHorizontalStreak(board,activePlayer,5,5);
        assertEquals(IModel.GAME_STATUS_ONGOING, getGameStatus());
    }


    
}
