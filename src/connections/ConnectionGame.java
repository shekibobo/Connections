package connections;

public class ConnectionGame {
	
	/* variables for comparison */
	private final int EMPTY = -1;
	private final int NO_WINNER = -1;
	
	/* game setup variables */
	private int winner;
	private int winLength;
	private int playerCount;
	private int rows;
	private int cols;
	
	/* gameplay variables */
	private int currentPlayer;
	private int turnCount;
	
	/** playerConnections - int array - playerConnections[<playernumber>] number of connected tokens. */
	private int[] playerConnections;
	
	/** layout of the game board as a 2-dimensional array of size [rows][cols] */
	private int[][] gameState;
	
	public ConnectionGame() {
		
	}
	
	/** Method setNumOfPlayers(): <br />
	 * Sets the number of players in the game.
	 */
	public void setPlayerCount(int playerCnt) {
		/* two player game */
		int minPlayers = 2;
		
		/* set at least two players in the game */
		this.playerConnections = new int[Math.max(minPlayers, playerCnt)];
		
		/* initialize the playerConnections array, setting them to zero throughout */
		for (int i = 0; i < this.playerConnections.length; i++) {
			this.resetPlayerConnections(i);
		}
	}
	
	/** Method getPlayerCount(): <br />
	 * @return int number of players in the game.
	 */
	public int getPlayerCount() {
		return this.playerCount;
	}
	
	/** Method setPlayerConnections
	 * Increments the connection count if a connection is found.
	 * @param player - player ID number
	 */
	public void setPlayerConnections(int player) {
		this.playerConnections[player]++;
	}
	
	/** Method resetPlayerConnections: <br />
	 * Resets the playerConnections to 0 for the specified player.
	 * @param player - int player ID number.
	 */
	public void resetPlayerConnections(int player) {
		this.playerConnections[player] = 0;
	}
	
	/** Method setWinner: <br />
	 * Sets the winner of the game to the specified player.
	 * @param player - int player ID number
	 */
	private void setWinner(int player) {
		if (player < playerCount && player > NO_WINNER) {
			this.winner = player;
		}
		else {
			this.winner = NO_WINNER;
		}		
	}
	
	/** Method getWinner(): <br />
	 * returns the player id number
	 * @return int player id number
	 */
	public int getWinner() {
		return this.winner;
	}
	
	/** Method setWinLength(int length): <br />
	 * Sets the win length to the value specified by the user. If win length is
	 * invalid, uses the default winLength.
	 * @param length int number of player pieces in a row required for a win
	 */
	public void setWinLength(int length) {
		length = Math.abs(length);
		if (length <= this.rows || length <= this.cols) {
			this.winLength = length;
		}
		else {
			System.out.println("Invalid win length (" + length + ") selected. Using the default");
			this.winLength = Math.min(this.rows, this.cols);
		}
	}
	
	/** Method getWinLength: <br />
	 * @return int required number of tokens in a row to win.
	 */
	public int getWinLength() {
		return this.winLength;
	}
	
	/** Method setGameSize(): <br />
	 * Creates an empty square game board the minimum size required for the 
	 * number of players in the game.
	 */
	public void setGameSize() {
		this.rows = this.cols = 
			(int) Math.sqrt(this.getPlayerCount() * this.winLength) + 1;
	}
	
	/** Method setGameSize(int size): <br />
	 * Creates an empty square game board the size specified by the user.
	 * Ensures the size of the game board is of at least the minimum size
	 * for the number of players.
	 * @param squareSize  int number of rows and columns for a square board.
	 */
	public void setGameSize(int size) {
		if ((size * size) >= (this.getPlayerCount() * winLength) - (this.getPlayerCount() - 1)) {			
			this.rows = this.cols = Math.abs(size);
		}
		else {
			System.out.println("Invalid game size.  Using default values.");
			this.setGameSize();
		}
	}
	
	/** Method setGameSize(int rows, int cols): <br />
	 * Creates an empty game board using dimensions provided by the user. If the 
	 * board size is invalid, attempts to set a square board using the maximum
	 * dimension.  Else it creates a square board using default minimum size.
	 * @param rows int number of rows (vertical dimension)
	 * @param cols int number of columns (horizontal dimension)
	 */
	public void setGameSize(int rows, int cols) {
		rows = Math.abs(rows);
		cols = Math.abs(cols);
		
		if ((rows * cols) >= (this.getPlayerCount() * winLength) - (this.getPlayerCount() - 1)) {
			this.rows = rows;
			this.cols = cols;
		}
		else {
			System.out.println("Invalid game size. Resizing...");
			this.setGameSize(Math.max(rows, cols));
		}
	}

}
