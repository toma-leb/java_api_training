package fr.lernejo.navy_battle;

import java.util.InvalidPropertiesFormatException;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Game {

    private final Board board = new Board();

    public void init() {
        board.init();
        board.fillBoard();
        System.out.println("My Board :");
        board.printBoard();
    }

    public String shooAt (String cell) {

        int column = cell.charAt(0) - 65;
        int row = Integer.parseInt(cell.substring(1)) - 1;
        System.out.println(column + " " + row);

        int cellStatus = board.getMyBoard()[column][row];

        if (cellStatus == 1) {
            board.hitBoat(column,row);
            if (isSunk(column, row)) {
                return "sunk";
            } else {
                return "hit";
            }
        } else {
            return "miss";
        }
    }

    public Boolean isSunk (int column, int row) {
        int[][] locationBoat = board.getLocationBoat();

        boolean found = false;
        int minX,minY,maxX,maxY;
        minX = minY = maxX = maxY = 0;

        for(int i = 0; i < 5 && !(found); i ++) {
            int locMinX = min(locationBoat[i][0],locationBoat[i][2]);
            int locMaxX = max(locationBoat[i][0],locationBoat[i][2]);
            int locMinY = min(locationBoat[i][1],locationBoat[i][3]);
            int locMaxY = max(locationBoat[i][1],locationBoat[i][3]);
            if ((locMinX <= column) && (locMaxX >= column)) {
                if ((locMinY <= row) && (locMaxY >= row)) {
                    minX = locMinX;
                    minY = locMinY;
                    maxX = locMaxX;
                    maxY = locMaxY;
                    found = true;
                }
            }
        }

        System.out.println(found);
        System.out.println(minX + " " + maxX + " " + minY + " " + maxY);

        boolean sunk = false;
        int[][] myBoard = board.getMyBoard();

        if (minX == maxX) {
            for (int i = minY; i <= maxY; i++) {
                sunk = myBoard[minX][i] == 2;
            }
        } else {
            for (int i = minX; i <= maxX; i++) {
                sunk = myBoard[i][minY] == 2;
            }
        }

        return sunk;
    }

    public boolean isOver() {
        return board.isOver();
    }
}
