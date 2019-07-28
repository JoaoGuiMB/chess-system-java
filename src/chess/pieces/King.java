package chess.pieces;

import boardgame.Board;
import boardgame.position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece{
	
	private ChessMatch chessMatch;

	public King(Board board, Color color,ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
	}
	@Override
	public String toString() {
		return "K";
	}
	
	private boolean canMove(position position) {
		ChessPiece p = (ChessPiece) getBoard().piece(position);
		return p == null || p.getColor() != getColor();
	}
	
	
	private boolean testRookCastling(position position) {
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		return p != null && p instanceof Rook && p.getColor() == getColor() && p.getMoveCount() == 0;
	}
	
	
	
	
	
	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		position p = new position(0,0);
		
		//above
		p.setValues(position.getRow()-1, position.getColumn());
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			
		}
		//below
		p.setValues(position.getRow()+1, position.getColumn());
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			
		}
		//left
		p.setValues(position.getRow(), position.getColumn()-1);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			
		}
		//Right
		p.setValues(position.getRow(), position.getColumn()+1);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			
		}
		//NW
		p.setValues(position.getRow()-1, position.getColumn()- 1);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			
		}
		
		//NE
		p.setValues(position.getRow()- 1, position.getColumn() + 1);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			
		}
		//SW
		p.setValues(position.getRow()+1, position.getColumn()- 1);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			
		}
		//SE
		p.setValues(position.getRow()+1, position.getColumn()+1);
		if(getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			
		}
		
		//Special Move Castling
		if(getMoveCount() == 0 && !chessMatch.getCheck()) {
			//#specialmove castling kingside rook
			position posT1 = new position(position.getRow(),position.getColumn() + 3);
			if(testRookCastling(posT1)) {
				position p1 = new position(position.getRow(),position.getColumn() + 1);
				position p2 = new position(position.getRow(),position.getColumn() + 2);
				if(getBoard().piece(p1) == null && getBoard().piece(p2) == null) {
					mat[position.getRow()][position.getColumn() + 2] = true;
				}
			}
			//#specialmove castling queenside rook
			position posT2 = new position(position.getRow(),position.getColumn() - 4);
			if(testRookCastling(posT2)) {
				position p1 = new position(position.getRow(),position.getColumn() - 1 );
				position p2 = new position(position.getRow(),position.getColumn() - 2);
				position p3 = new position(position.getRow(),position.getColumn() - 3);
				if(getBoard().piece(p1) == null && getBoard().piece(p2) == null && getBoard().piece(p3) == null) {
					mat[position.getRow()][position.getColumn() - 2] = true;
				}
			}
			
		}
		
		
		
		return mat;
	}

}
