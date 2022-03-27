package fr.lernejo.navy_battle;

import java.util.InvalidPropertiesFormatException;

public class Board {
    public int[][] board = new int[10][10];
    Boats boats = new Boats();

    public void init() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                this.board[i][j] = 0;
            }
        }
    }

    public void serverBoard() {
        this.board = this.boats.implementBoat(this.board,5);
        this.board = this.boats.implementBoat(this.board,4);
        this.board = this.boats.implementBoat(this.board,3);
        this.board = this.boats.implementBoat(this.board,3);
        this.board = this.boats.implementBoat(this.board,2);
    }

    public void printBoard() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(this.board[j][i] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void check() throws InvalidPropertiesFormatException {
        int res = 0;

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (this.board[j][i] == 1) {
                    res +=1;
                }
            }
        }

        if (res != 17) {
            throw new InvalidPropertiesFormatException("Board isn't correct");
        }
    }

}
