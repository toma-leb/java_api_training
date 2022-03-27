package fr.lernejo.navy_battle;

import java.util.InvalidPropertiesFormatException;

public class Board {
    private int[][] myBoard = new int[10][10];
    private int[][] opponentBoard = new int[10][10];
    private int[][] locationBoat = new int[5][4];
    Boats boats = new Boats();

    public void init() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                this.myBoard[i][j] = 0;
            }
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                this.opponentBoard[i][j] = 0;
            }
        }
    }

    public void fillBoard() {
        this.myBoard = this.boats.implementBoat(this.myBoard,5);
        this.myBoard = this.boats.implementBoat(this.myBoard,4);
        this.myBoard = this.boats.implementBoat(this.myBoard,3);
        this.myBoard = this.boats.implementBoat(this.myBoard,3);
        this.myBoard = this.boats.implementBoat(this.myBoard,2);

        this.locationBoat = this.boats.getLocationBoat();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(this.locationBoat[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void printBoard() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(this.myBoard[j][i] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public boolean isOver() {
        int res = 0;

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (this.myBoard[j][i] == 2) {
                    res +=1;
                }
            }
        }

        return res == 17;
    }

    public void hitBoat(int column, int row) {
        this.myBoard[column][row] = 2;
    }

    public int[][] getMyBoard() {
        return this.myBoard;
    }

    public int[][] getOpponentBoard() {
        return this.opponentBoard;
    }

    public int[][] getLocationBoat() {
        return this.locationBoat;
    }


}
