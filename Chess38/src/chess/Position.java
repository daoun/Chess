package chess;

/**
 * Position helper class to indicate the location of the piece on the game board. 
 * File is the horizontal positions and rank is the vertical positions.
 * @author Capki Kim, Daoun Oh
 *
 */
public class Position {
	private int rank;
	private int file;
	
	/**
	 * Gets the rank of piece from a private field.
	 * @return rank
	 */
	public int getRank() {
		return rank;
	}

	/**
	 * Sets the rank of the piece.
	 * @param rank vertical position
	 */
	public void setRank(int rank) {
		this.rank = rank;
	}

	/**
	 * Gets the file of piece from a private field.
	 * @return file
	 */
	public int getFile() {
		return file;
	}

	/**
	 * Sets the file of the piece.
	 * @param file horizontal position
	 */
	public void setFile(int file) {
		this.file = file;
	}

	/**
	 * Default constructor.
	 * @param rank vertical position representation
	 * @param file horizontal position representation
	 */
	public Position(int rank, int file){
		this.rank = rank;
		this.file = file;
	}
}
