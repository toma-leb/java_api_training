package fr.lernejo.navy_battle;

import java.util.Hashtable;

public class Boats {

    private final BoatsImplementer boatsImplementer = new BoatsImplementer();
    private final int[][] locationBoat = new int[5][4];
    private int locationBoatIndex = 0;

    public int[][] implementBoat(int[][] board, int size) {
        String direction;
        int[] pos;

        pos = this.boatsImplementer.randomPos(board);
        Hashtable<String, Boolean> finder = this.boatsImplementer.findDirection(pos, size);
        finder = this.boatsImplementer.validatorPos(finder, pos, size, board);
        direction = this.boatsImplementer.Direction(finder);

        addLocationBoat(pos,size,direction);
        board = this.boatsImplementer.implementBoard(board,pos,size,direction);

        return board;
    }

    public void addLocationBoat(int[] pos, int size, String direction) {
        size = size - 1;
        this.locationBoat[this.locationBoatIndex][0] = pos[0];
        this.locationBoat[this.locationBoatIndex][1] = pos[1];
        if (direction.equals("Right") || direction.equals("Left")) {
            this.locationBoat[this.locationBoatIndex][3] = pos[1];
            if (direction.equals("Right")) {
                this.locationBoat[this.locationBoatIndex][2] = pos[0] + size;
            } else {
                this.locationBoat[this.locationBoatIndex][2] = pos[0] - size;
            }
        } else {
            this.locationBoat[this.locationBoatIndex][2] = pos[0];
            if (direction.equals("Bottom")) {
                this.locationBoat[this.locationBoatIndex][3] = pos[1] + size;
            } else {
                this.locationBoat[this.locationBoatIndex][3] = pos[1] - size;
            }
        }
        this.locationBoatIndex += 1;
    }

    public int[][] getLocationBoat() {
        return this.locationBoat;
    }
}
