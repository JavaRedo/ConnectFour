package game;

import interfaces.IModel;
import interfaces.IPlayer;
import util.GameSettings;


public class Model implements IModel
{
	// A reference to the game settings from which you can retrieve the number
	// of rows and columns the board has and how long the win streak is.
	private GameSettings settings;
	private int nrRows;
	private int nrCols;
	private byte board[][];
	private byte activePlayer = 1;
	private int gameStatus = 0; 

	
	// The default constructor.
	public Model()
	{
		// You probably won't need this.
	}
	
	// A constructor that takes another instance of the same type as its parameter.
	// This is called a copy constructor.
	public Model(IModel model)
	{
		// You may (or may not) find this useful for advanced tasks.
	}
	
	// Called when a new game is started on an empty board.
	public void initNewGame(GameSettings settings)
	{
		this.settings = settings;
		nrCols = settings.nrCols;
		nrRows = settings.nrRows;

		board = new byte[nrRows][nrCols];

		for (byte[] bs : board) {
			for (byte b : bs) {
				b = 0;
			}
		}
	}
	
	// Called when a game state should be loaded from the given file.
	public void initSavedGame(String fileName)
	{
		// This is an advanced feature. If not attempting it, you can ignore this method.
	}
	
	// Returns whether or not the passed in move is valid at this time.
	public boolean isMoveValid(int move)
	{
		if((move < 0 || move > nrCols)  || getNextEmptySlot(move) == -1){
			return false;
		}
		else{
			return true;
		}
	}
	
	// Actions the given move if it is valid. Otherwise, does nothing.
	public void makeMove(int move)
	{

		int nextEmptySlot = getNextEmptySlot(move);

		board[nextEmptySlot][move] = activePlayer;

		updateGameStatus(nextEmptySlot,move);

		if(activePlayer == 1){
			activePlayer = 2; 
		}
		else{
			activePlayer= 1;
		}
	}

	// Returns one of the following codes to indicate the game's current status.
	// IModel.java in the "interfaces" package defines constants you can use for this.
	// 0 = Game in progress
	// 1 = Player 1 has won
	// 2 = Player 2 has won
	// 3 = Tie (board is full and there is no winner)
	public byte getGameStatus()
	{
		// Assuming the game is never ending.
		switch (gameStatus) {
			
			case 1 : return IModel.GAME_STATUS_WIN_1; 

			case 2: return IModel.GAME_STATUS_WIN_2;

			case 3: return IModel.GAME_STATUS_TIE;
			
			default: return IModel.GAME_STATUS_ONGOING;
		}
	}
	
	// Returns the number of the player whose turn it is.
	public byte getActivePlayer()
	{
		return activePlayer;
	}
	// Returns the owner of the piece in the given row and column on the board.
	// Return 1 or 2 for players 1 and 2 respectively or 0 for empty cells.
	public byte getPieceIn(int row, int column)
	{
		byte pieceIn = board[row][column];  
		return pieceIn;
	}
	
	// Returns a reference to the game settings, from which you can retrieve the
	// number of rows and columns the board has and how long the win streak is.
	public GameSettings getGameSettings()
	{
		return settings;
	}
	
	// =========================================================================
	// ================================ HELPERS ================================
	// =========================================================================
	
	// You may find it useful to define some helper methods here.
	private int getNextEmptySlot(int col) {
		for (int i = nrRows - 1; i > -1; i--) {
			if(board[i][col] == 0){
				return i;
			}
		}

		return -1;
	}
	
	private void updateGameStatus(int nextEmptySlot, int move) {
		// checkForHorizontalStreak();
		// checkForVerticalStreak();
		checkForDiagonalStreak(nextEmptySlot,move);
		checkForTie();
	}

	private void checkForTie() {
		boolean columnsFull = true;
		for (int i = 0; i < nrCols; i++) {
			if(board[nrRows-1][i] != 0){
				columnsFull = false;
				break;
			}
		}

		if(columnsFull && (gameStatus != 1 || gameStatus != 2)){
			gameStatus = 3;
		};
	}

	private void checkForDiagonalStreak(int nextEmptySlot, int move) {

		int[][] diagDirec = {{1,1},{-1,-1},{1,-1},{-1,1}};
		int highestCount = 0;

		for (int[] vector : diagDirec) {

				int count = 1;
				int vecX = vector[0];
				int vecY = vector[1];

				for (int magnitude = 1; magnitude < 4; magnitude++) {

					int nextX = nextEmptySlot + (vecY * magnitude);
					int nextY = move + (vecX * magnitude);

					try{
						int nextSlot = board[nextX][nextY];
						nextX = 0;
						nextY = 0;
						if(board[nextEmptySlot][move] == nextSlot){
							count+=1;
						}
						else{
							break;
						}
					}
					catch(IndexOutOfBoundsException e){
						continue;
					}


				}

				if (count > highestCount) {
					highestCount = count;
				}
		}
		
		gameStatus = highestCount == settings.minStreakLength ? activePlayer : IModel.GAME_STATUS_ONGOING; 
	}

	private void checkForHorizontalStreak() {
		for (int i = 0; i < nrRows; i++) {
			int highestCount = 0;
			for (int j = 0; j < nrCols; j++) {
				int count = 0;
				int currRowEle = board[i][j];
				if(j+1 < nrCols){
					int nextEle = board[i][j+1];

					if (currRowEle == nextEle && nextEle != 0 && currRowEle != 0) {
						count++;
					}
					else{
						count = 0;
					}
				}

				if(count > highestCount){
					highestCount = count;
				}
			}

			if(highestCount == settings.minStreakLength){
				gameStatus = activePlayer;
			}
		}
	}

	private void checkForVerticalStreak() {
		for (int i = 0; i < nrRows; i++) {
			int highestCount = 0;
			for (int j = 0; j < nrCols; j++) {
				int count = 0;
				int currColEle = board[i][j];
				if(i+1 < nrRows){
					int nextEle = board[i+1][j];

					if (currColEle == nextEle && nextEle != 0 && currColEle != 0) {
						count++;
					}
					else{
						count = 0;
					}
				}

				if(count > highestCount){
					highestCount = count;
				}
			}

			if(highestCount == settings.minStreakLength){
				gameStatus = activePlayer;
			}
		}
	}
}
