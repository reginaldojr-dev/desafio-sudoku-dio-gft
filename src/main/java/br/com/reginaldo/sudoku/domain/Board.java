package br.com.reginaldo.sudoku.domain;

public class Board {
    private final int[][] values;
    private final boolean[][] fixed;

    // Construtor completo
    public Board(int[][] values, boolean[][] fixed) {
        this.values = values;
        this.fixed = fixed;
    }

    // Construtor que assume que nenhuma célula é fixa
    public Board(int[][] values) {
        this.values = values;
        this.fixed = new boolean[9][9];
    }

    public int get(int row, int col) {
        return values[row][col];
    }

    public boolean isFixed(int row, int col) {
        return fixed[row][col];
    }

    public int[][] snapshotValues() {
        return values;
    }

    public boolean[][] snapshotFixed() {
        return fixed;
    }

    public boolean isValidBoard() {
        return false;
    }

    public boolean isComplete() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (values[row][col] == 0) {
                    return false;
                }
            }
        }
        return isValidBoard();
    }



    public boolean isValidMove(int row, int col, int value) {
        if (value < 1 || value > 9) return false;

        // Verifica linha
        for (int j = 0; j < 9; j++) {
            if (values[row][j] == value) return false;
        }

        // Verifica coluna
        for (int i = 0; i < 9; i++) {
            if (values[i][col] == value) return false;
        }

        // Verifica bloco 3x3
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (values[i][j] == value) return false;
            }
        }

        return true;
    }



    public boolean set(int row, int col, int value) {
        if (fixed[row][col]) return false;
        if (!isValidMove(row, col, value)) return false;

        values[row][col] = value;
        return true;
    }

    public boolean clear(int row, int col) {
        if (fixed[row][col]) return false;

        values[row][col] = 0;
        return true;
    }


}