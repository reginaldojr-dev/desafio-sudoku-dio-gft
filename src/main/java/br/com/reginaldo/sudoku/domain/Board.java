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
        return false;
    }

    public boolean isValidMove(int row, int col, int value) {
        return false;
    }

    public boolean set(int row, int col, int value) {
        return false;
    }

    public boolean clear(int row, int col) {
        return false;
    }
}