package fr.lernejo.navy_battle;

import java.security.SecureRandom;
import java.util.Hashtable;

public class BoatsImplementer {

    private final SecureRandom random = new SecureRandom();

    public int [] randomPos(int[][] board) {
        int[] pos = new int[2];

        do {
            pos[0] = this.random.nextInt(10);
            pos[1] = this.random.nextInt(10);
        } while (board[pos[0]][pos[1]] == 1);

        return pos;
    }

    public Hashtable<String,Boolean> findDirection(int[] pos, int size) {
        Hashtable<String,Boolean> direction = new Hashtable<>();

        direction.put("Right",true);
        direction.put("Left",true);
        direction.put("Bottom",true);
        direction.put("Top",true);

        if ((pos[0] - (size-1)) < 0) direction.put("Left", false);
        if ((pos[0] + (size-1)) > 9) direction.put("Right", false);
        if ((pos[1] - (size-1)) < 0) direction.put("Top", false);
        if ((pos[1] + (size-1)) > 9) direction.put("Bottom", false);

        return direction;
    }

    public Hashtable<String,Boolean> validatorPos (Hashtable<String,Boolean> direction, int[] pos, int size, int[][] board) {

        if (direction.get("Right")) {
            boolean free = true;
            for (int i = 0; i < size && free; i++) free = validatorBoard(board, pos[0] + i, pos[1]);
            direction.put("Right",free);
        }
        if (direction.get("Left")) {
            boolean free = true;
            for (int j = 0; j < size && free; j++) free = validatorBoard(board, pos[0] - j, pos[1]);
            direction.put("Left",free);
        }
        if (direction.get("Bottom")) {
            boolean free = true;
            for (int k = 0; k < size && free; k++) free = validatorBoard(board, pos[0], pos[1] + k);
            direction.put("Bottom",free);
        }
        if (direction.get("Top")) {
            boolean free = true;
            for (int l = 0; l < size && free; l++) free = validatorBoard(board, pos[0], pos[1] - l);
            direction.put("Top",free);
        }

        return direction;
    }

    public String Direction(Hashtable<String,Boolean> direction) {
        Object directionVal;
        Object[] array = direction.keySet().toArray();

        do {
            int ran = this.random.nextInt(array.length);
            directionVal = array[ran];
        } while (!direction.get(directionVal.toString()));

        return directionVal.toString();
    }

    public boolean validatorBoard (int[][] board, int column, int row) {
        return board[column][row] != 1;
    }

    public int[][] implementBoard (int[][] board, int[] pos, int size, String direction) {

        if (direction.equals("Right")) for (int i = 0; i < size; i++) board[pos[0] + i][pos[1]] = 1;
        if (direction.equals("Left")) for (int i = 0; i < size; i++) board[pos[0] - i][pos[1]] = 1;
        if (direction.equals("Bottom")) for (int i = 0; i < size; i++) board[pos[0]][pos[1] + i] = 1;
        if (direction.equals("Top")) for (int i = 0; i < size; i++) board[pos[0]][pos[1] - i] = 1;

        return board;
    }
}
