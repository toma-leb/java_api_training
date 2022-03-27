package fr.lernejo.navy_battle;

import java.util.Hashtable;

public class Boats {

    BoatsImplementer boatsImplementer = new BoatsImplementer();

    public int[][] implementBoat(int[][] board, int size) {
        String direction;
        int[] pos;


        pos = this.boatsImplementer.randomPos(board);
        Hashtable<String, Boolean> finder = this.boatsImplementer.findDirection(pos, size);
        finder = this.boatsImplementer.validatorPos(finder, pos, size, board);
        direction = this.boatsImplementer.Direction(finder);


        board = this.boatsImplementer.implementBoard(board,pos,size,direction);

        return board;
    }
}
