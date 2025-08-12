package br.com.reginaldo.sudoku;

import br.com.reginaldo.sudoku.domain.Board;
import br.com.reginaldo.sudoku.domain.Coordinates;
import br.com.reginaldo.sudoku.service.GameService;
import java.util.Scanner;

public class Game {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[][] start = new int[9][9];
        boolean[][] fixed = new boolean[9][9];

        if (args != null && args.length > 0) {
            int count = loadFromArgs(args, start, fixed);
            System.out.println("Carregado via argumentos: " + count + " posições definidas.\n");
        } else {
            int[][] defaultStart = {
                    {0,0,0, 2,6,0, 7,0,1},
                    {6,8,0, 0,7,0, 0,9,0},
                    {1,9,0, 0,0,4, 5,0,0},

                    {8,2,0, 1,0,0, 0,4,0},
                    {0,0,4, 6,0,2, 9,0,0},
                    {0,5,0, 0,0,3, 0,2,8},

                    {0,0,9, 3,0,0, 0,7,4},
                    {0,4,0, 0,5,0, 0,3,6},
                    {7,0,3, 0,1,8, 0,0,0}
            };
            boolean[][] defaultFixed = new boolean[9][9];
            for (int r = 0; r < 9; r++) {
                for (int c = 0; c < 9; c++) {
                    defaultFixed[r][c] = defaultStart[r][c] != 0;
                }
            }
            start = defaultStart;
            fixed = defaultFixed;
            System.out.println("Sem argumentos: usando puzzle padrão.\n");
        }

        Board board = new Board(start, fixed);
        GameService gameService = new GameService(board);

        System.out.println("Sudoku CLI");
        System.out.println("Digite: linha coluna valor (1-9) ou 0 para limpar. Comando 'q' para sair\n");

        while (true) {
            gameService.printBoard();

            if (gameService.isComplete()) {
                System.out.println("Parabéns! Você completou o puzzle!");
                break;
            }

            System.out.print("Digite sua jogada (linha coluna valor) 0 = limpar, q = sair: ");
            String line = sc.nextLine().trim();

            if (line.equalsIgnoreCase("q")) {
                System.out.println("Saindo...");
                break;
            }

            String[] parts = line.split("\\s+");
            if (parts.length != 3) {
                System.out.println("Formato inválido. Use: linha coluna valor");
                continue;
            }

            Integer r = parseIndex(parts[0]);
            Integer c = parseIndex(parts[1]);
            Integer v = parseValue(parts[2]);

            if (r == null || c == null || v == null) {
                System.out.println("Linha/coluna/valor inválidos");
                continue;
            }

            Coordinates coord = new Coordinates(r, c);

            if (v == 0) {
                gameService.clear(coord);
                continue;
            }

            if (!gameService.setValue(coord, v)) {
                System.out.println("Movimento inválido ou célula fixa");
            }
        }

        sc.close();
    }

    private static int loadFromArgs(String[] args, int[][] start, boolean[][] fixed) {
        String all = String.join(" ", args).trim();
        if (all.isEmpty()) return 0;
        String[] tokens = all.split("\\s+");
        int count = 0;
        for (String tok : tokens) {
            try {
                String[] parts = tok.split(";");
                if (parts.length != 2) continue;
                String[] rc = parts[0].split(",");
                String[] vf = parts[1].split(",");
                if (rc.length != 2 || vf.length != 2) continue;
                int r = Integer.parseInt(rc[0].trim());
                int c = Integer.parseInt(rc[1].trim());
                int v = Integer.parseInt(vf[0].trim());
                boolean f = Boolean.parseBoolean(vf[1].trim());
                if (r < 0 || r > 8 || c < 0 || c > 8 || v < 0 || v > 9) continue;
                if (v == 0 && f) f = false;
                start[r][c] = v;
                fixed[r][c] = f;
                count++;
            } catch (Exception ignored) {}
        }
        return count;
    }

    private static Integer parseIndex(String s) {
        try {
            int x = Integer.parseInt(s);
            if (x < 1 || x > 9) return null;
            return x - 1;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private static Integer parseValue(String s) {
        try {
            int v = Integer.parseInt(s);
            if (v < 0 || v > 9) return null;
            return v;
        } catch (NumberFormatException e) {
            return null;
        }
    }
}