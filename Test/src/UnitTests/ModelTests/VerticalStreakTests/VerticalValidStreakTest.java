package UnitTests.ModelTests.VerticalStreakTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import game.Model;
import interfaces.IModel;
import util.GameSettings;

public class VerticalValidStreakTest extends Model{

    private GameSettings testSettings;
    boolean isWin = false;

    @Test
    public void checkForVerticalStreakTest1(){
        this.testSettings = new GameSettings(6,7,4); 
        super.initNewGame(testSettings);
        // testing board
        // - - - - - -
        // O - - - - -
        // O - - - - -
        // O - - - - -
        // x x x - x -
        byte[][] board = {
            {0,0,0,0,0,0,0},
            {2,0,0,0,0,0,0},
            {2,0,0,0,0,0,0},
            {2,0,0,0,0,0,0},
            {2,0,0,0,0,0,0},
            {1,1,1,0,1,0,0},
        };
        int activePlayer = 2;
        super.checkForVerticalStreak(board,activePlayer,1,0,this.isWin);
        assertEquals(IModel.GAME_STATUS_WIN_2, getGameStatus());
    }


    @Test
    public void checkForVerticalStreakTest2(){
        this.testSettings = new GameSettings(6,7,4); 
        super.initNewGame(testSettings);
        // testing board
        // - - - - - -
        // O - - - - -
        // O - - - - -
        // O - - - - -
        // O x x - x x
        byte[][] board = {
            {0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0},
            {2,0,0,0,0,0,0},
            {2,0,0,0,0,0,0},
            {2,0,0,0,0,0,0},
            {2,1,1,0,1,0,0},
        };
        int activePlayer = 2;
        super.checkForVerticalStreak(board,activePlayer,2,0,this.isWin);
        assertEquals(IModel.GAME_STATUS_WIN_2, getGameStatus());
    }


    @Test
    public void checkForVerticalStreakTest3(){
        this.testSettings = new GameSettings(6,7,4); 
        super.initNewGame(testSettings);
        // testing board
        // - - - - - -
        // O - - - - -
        // O - - - - -
        // O - - - - -
        // x x x - x -
        byte[][] board = {
            {0,0,0,0,0,0,0},
            {2,0,0,0,0,0,0},
            {2,0,0,0,0,0,0},
            {2,0,0,0,0,0,0},
            {2,0,0,0,0,0,0},
            {1,1,1,0,1,0,0},
        };
        int activePlayer = 2;
        super.checkForVerticalStreak(board,activePlayer,1,0,this.isWin);
        assertEquals(IModel.GAME_STATUS_WIN_2, getGameStatus());
    }



    @Test
    public void checkForVerticalStreakTest4(){
        this.testSettings = new GameSettings(6,7,4); 
        super.initNewGame(testSettings);
        // testing board
        // - - - - - -
        // O - - - - -
        // O - - - - -
        // O - - - - -
        // x x x - x -
        byte[][] board = {
            {0,0,0,0,0,0,0},
            {2,0,0,0,0,0,0},
            {2,0,0,0,0,0,0},
            {2,0,0,0,0,0,0},
            {2,0,0,0,0,0,0},
            {1,1,1,0,1,0,0},
        };
        int activePlayer = 2;
        super.checkForVerticalStreak(board,activePlayer,1,0,this.isWin);
        assertEquals(IModel.GAME_STATUS_WIN_2, getGameStatus());
    }



    @Test
    public void checkForVerticalStreakTest5(){
        this.testSettings = new GameSettings(6,7,4); 
        super.initNewGame(testSettings);
        // testing board
        // - - - - - -
        // O - - - - -
        // O - - - - -
        // O - - - - -
        // x x x - x -
        byte[][] board = {
            {0,0,0,0,0,0,0},
            {2,0,0,0,0,0,0},
            {2,0,0,0,0,0,0},
            {2,0,0,0,0,0,0},
            {2,0,0,0,0,0,0},
            {1,1,1,0,1,0,0},
        };
        int activePlayer = 2;
        super.checkForVerticalStreak(board,activePlayer,1,0,this.isWin);
        assertEquals(IModel.GAME_STATUS_WIN_2, getGameStatus());
    }



    @Test
    public void checkForVerticalStreakTest6(){
        this.testSettings = new GameSettings(6,7,4); 
        super.initNewGame(testSettings);
        // testing board
        // - - - - - -
        // O - - - - -
        // O - - - - -
        // O - - - - -
        // x x x - x -
        byte[][] board = {
            {0,0,0,0,0,0,0},
            {2,0,0,0,0,0,0},
            {2,0,0,0,0,0,0},
            {2,0,0,0,0,0,0},
            {2,0,0,0,0,0,0},
            {1,1,1,0,1,0,0},
        };
        int activePlayer = 2;
        super.checkForVerticalStreak(board,activePlayer,1,0,this.isWin);
        assertEquals(IModel.GAME_STATUS_WIN_2, getGameStatus());
    }
}
