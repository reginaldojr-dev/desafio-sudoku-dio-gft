package br.com.reginaldo.sudoku.service;

import br.com.reginaldo.sudoku.domain.Board;
import br.com.reginaldo.sudoku.domain.Coordinates;

public class GameService {
    private final Board board;

    public GameService(Board initialBoard) {
        this.board = initialBoard;
    }

    public boolean setValue(Coordinates coord, int value) {
        if (value < 1 || value > 9) return false;
        if (board.isFixed(coord.row(), coord.col())) return false;
        if (!isValidMove(coord, value)) return false;

        board.setValue(coord.row(), coord.col(), value);
        return true;
    }

    public void clear(Coordinates coord) {
        if (!board.isFixed(coord.row(), coord.col())) {
            board.clearValue(coord.row(), coord.col());
        }
    }

    public boolean isValidMove(Coordinates coord, int value) {
        int row = coord.row();
        int col = coord.col();

        // Linha
        for (int c = 0; c < 9; c++) {
            if (board.getValue(row, c) == value) return false;
        }
        // Coluna
        for (int r = 0; r < 9; r++) {
            if (board.getValue(r, col) == value) return false;
        }
        // Bloco 3x3
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        for (int r = startRow; r < startRow + 3; r++) {
            for (int c = startCol; c < startCol + 3; c++) {
                if (board.getValue(r, c) == value) return false;
            }
        }
        return true;
    }

    public boolean isComplete() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                int value = board.getValue(row, col);
                if (value == 0 || !isValidMove(new Coordinates(row, col), value)) {
                    return false;
                }
            }
        }
        return true;
    }

    public int[][] getBoardSnapshot() {
        return board.snapshotValues();
    }

    public boolean[][] getFixedSnapshot() {
        return board.snapshotFixed();
    }

    // Imprime o tabuleiro formatado
    public void printBoard() {
        int[][] values = board.snapshotValues();
        System.out.println();
        for (int r = 0; r < 9; r++) {
            if (r % 3 == 0) {
                System.out.println("+-------+-------+-------+");
            }
            for (int c = 0; c < 9; c++) {
                if (c % 3 == 0) System.out.print("| ");
                int val = values[r][c];
                System.out.print(val == 0 ? ". " : (val + " "));
            }
            System.out.println("|");
        }
        System.out.println("+-------+-------+-------+\n");
    }
}