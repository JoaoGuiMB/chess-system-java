package boardgame;

public class Board {
	
	
	private int rows;
	private int columns ;
	private Piece[][] pieces;
	
	public Board(int rows, int columns) {
		if(rows < 1 || columns<1) {
			throw new BoardException("Error Creating board: there must be at least 1 row and 1 column");
		}
		
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece [rows][columns];
	}

	public int getRows() {
		return rows;
	}

	

	public int getColumns() {
		return columns;
	}

		
	public Piece piece(int row,int column) {
		if(!positionExits(row,column)) {
			throw new BoardException("Position not in the board");
		}
		return pieces[row][column];
	}
	
	public Piece piece(position position) {
		if(!positionExists(position)) {
			throw new BoardException("Position not in the board");
		}
		return pieces[position.getRow()][position.getColumn()];
	}
	
	public void placePiece(Piece piece, position position) {
		if(thereIsAPiece(position)) {
			throw new BoardException("There is already a piece on position" + position);
		}
		
		pieces[position.getRow()][position.getColumn()] =  piece;
		
		piece.position = position;
		
	}
	
	private boolean positionExits(int row, int column) {
		return row>=0 && row < rows && column >=0 && column <columns;
	}
	
	public boolean positionExists(position position) {
		return positionExits(position.getRow(),position.getColumn() );
	}
	public boolean thereIsAPiece(position position) {
		if(!positionExists(position)) {
			throw new BoardException("Position not in the board");
		}
		
		return piece(position) != null;
	}
	
	
}