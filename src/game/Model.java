package game;

import interfaces.IModel;
import interfaces.IPlayer;
import util.GameSettings;


public class Model implements IModel
{
	// A reference to the game settings from which you can retrieve the number
	// of rows and columns the board has and how long the win streak is.
	protected GameSettings settings;
	protected int nrRows;
	protected int nrCols;
	protected byte board[][];
	protected byte activePlayer = 1;
	protected int gameStatus = 0; 
	protected int moveCol;
	protected int moveRow;

	
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
		this.moveCol = move;
		int row = getNextEmptySlot(move);
		moveRow = row;

		board[row][move] = activePlayer;

		updateGameStatus(row,move);

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
	/**
	 * returns the row index of the free slot after the 
	 * top token in the stack
	 * e.g
	 * board
	 * 0 2 1 1 0 nextEmptySlots for each column 
	 * @param col column to check the next empty slot
	 * @return nextEmptySlot
	 */
	protected int getNextEmptySlot(int col) {
		for (int i = nrRows - 1; i > -1; i--) {
			if(board[i][col] == 0){
				return i;
			}
		}

		return -1;
	}

	/**
	 * method used to reset model
	 * only use for testing purposes!!
	 */
	protected void resetModel(){
		this.activePlayer = 1;
		this.board = null;
		this.gameStatus = 0;
		this.nrCols = getGameSettings().nrCols;
		this.nrRows = getGameSettings().nrRows;
	}
	
	/**
	 * runs the vertical,horizontal and diagonal
	 * streak detections and updates the game status 
	 * accordingly
	 * @param row
	 * @param move
	 */
	protected void updateGameStatus(int row,int move) {

		checkForHorizontalStreak(this.board,this.activePlayer,row,move);
		checkForDiagonalStreak(this.board, this.activePlayer, row, move);
		checkForTie();
	}

	protected void checkForTie() {
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

	/**
	 * checks for a diagonal streak from the token placed by the active player
	 * by checking all four diagonal directions
	 * and updates the game status accordingly
	 * @param activePlayer the active player that made the move
	 * @param board the game board 
	 * @param row row of the inserted token
	 * @param move column of the inserted token 
	 */
	protected void checkForDiagonalStreak(byte[][] board, int activePlayer ,int row, int move) {

		int[][] diagDirec = {{1,1},{-1,-1},{1,-1},{-1,1}};
		int highestCount = 0;

		for (int[] vector : diagDirec) {

				int count = 1;
				int vecX = vector[0];
				int vecY = vector[1];

				for (int magnitude = 1; magnitude < settings.minStreakLength; magnitude++) {

					int nextX = row + (vecY * magnitude);
					int nextY = move + (vecX * magnitude);

					try{
						int nextSlot = board[nextX][nextY];
						nextX = 0;
						nextY = 0;
						if(board[row][move] == nextSlot){
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

	/**
	 * checks for a horizontal streak from the token placed by the active player
	 * by checking both a right and a left horizontal streak
	 * and updates the game status accordingly
	 * @param activePlayer the active player that made the move
	 * @param board the game board 
	 * @param row row of the inserted token
	 * @param move column of the inserted token 
	 */
	protected void checkForHorizontalStreak(byte[][]board,int activePlayer,int row ,int move) {
		int highestCount = 0;
		//check for right horizontal streak
		if((move + settings.minStreakLength > settings.nrCols) && (move - settings.minStreakLength < 0)){
			
			gameStatus = IModel.GAME_STATUS_ONGOING;
			return;
		}
		//check for right horizontal streak
		int count = 0;
		for (int i = 0; i < board.length-move; i++) {
			int currToken = board[row][move+i]; 

			if(currToken != activePlayer && count <= settings.minStreakLength){
				break;
			}
			else{
				count++;
				if(count > highestCount ){
					highestCount = count;
				}
			}
		}

		count = 0;
		//check for left horizontal streak
		for (int i = 0; i <= move; i++) {
			int currToken = board[row][move-i];
			
			if(currToken != activePlayer && count <= settings.minStreakLength){
				break;
			}
			else{
				count++;
				if(count > highestCount ){
					highestCount = count;
				}
			}
		}

		//update game status
		gameStatus = highestCount == settings.minStreakLength ? activePlayer : IModel.GAME_STATUS_ONGOING;
	}

	protected void checkForVerticalStreak(byte[][] board,int activePlayer,int row,int move) {
		int highestCount = 0;
		//handle out of boundary streaks
		if((row + settings.minStreakLength > settings.nrRows) && (row - settings.minStreakLength < 0)){
			
			gameStatus = IModel.GAME_STATUS_ONGOING;
			return;
		}

		//check for downward streak
		int count = 0;
		for (int i = row; i < settings.nrRows; i++) {

			int currToken = board[i][move]; 

			if(currToken != activePlayer && count <= settings.minStreakLength){
				break;
			}
			else{
				count++;
				if(count > highestCount ){
					highestCount = count;
				}
			}
		}

		//update game status
		gameStatus = highestCount == settings.minStreakLength ? activePlayer : IModel.GAME_STATUS_ONGOING;
	}
}
