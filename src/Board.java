import edu.princeton.cs.algs4.Stack;

import java.util.ArrayList;
import java.util.Iterator;

public class Board {
    private class zeroPosition{
        public int row;
        public int col;
        private int n;

        public zeroPosition(int row, int col, int n){
            this.row = row;
            this.col = col;
            this.n =n;
        }

        public void up(){
            if (row > 0) {
                row = row--;
            }
        }
        public void left(){
            if (col > 0){
                col--;
            }
        }
        public void down(){
            if (row < n-1){
                row++;
            }
        }
        public void right(){
            if (col < n-1){
                col++;
            }
        }
    }
    private zeroPosition pos;
    private int n;
    private int[][] tiles;


    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        this.tiles = tiles;
        this.n = tiles.length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tiles[i][j] == 0){
                    pos = new zeroPosition(i,j,n);
                }
            }
        }
    }

    // string representation of this board
    public String toString() {
        StringBuilder string = new StringBuilder(dimension() + "\n");
        for (int[] tile : tiles) {
            for (int i : tile) {
                string.append(" ").append(i);
            }
            string.append("\n");
        }
        return String.valueOf(string);
    }

    // board dimension n
    public int dimension() {
        return n;
    }

    // number of tiles out of place
    public int hamming() {
        int counter = 0;
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                if (tiles[i][j] != correspondingNumber(i, j)) {
                    counter++;
                }
            }
        }
        return counter;
    }
    // Helper Method to figure out wtf it should be
    private int correspondingNumber(int row, int col) {
        if (row == dimension() - 1 && col == dimension() - 1) {
            return 0;
        }
        return row * dimension() + col + 1;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan(){
        int counter = 0;
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                if (tiles[i][j] != correspondingNumber(i,j) && tiles[i][j] != 0){
                    for (int k = 0; k < dimension() ; k++) {
                        for (int l = 0; l < dimension() ; l++) {
                            if(tiles[k][l] == correspondingNumber(i,j)){
                                counter += (Math.abs(k-i)+ Math.abs(l-j));
                            }
                        }
                    }
                }
            }
        }
        return counter;
    }

    // is this board the goal board?
    public boolean isGoal(){
        boolean isGoal = true;
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                if(tiles[i][j] != correspondingNumber(i,j)){
                    isGoal = false;
                }
            }
        }
        return isGoal;
    }

    // does this board equal y?
    public boolean equals(Object y){
        if(y == this){
            return true;
        }
        if(this.getClass() != y.getClass()){
            return false;
        }
        Board that = (Board) y;

        if(this.dimension() != that.dimension()){
            return false;
        }

        for (int i = 0; i < this.dimension() ; i++) {
            for (int j = 0; j < this. dimension() ; j++) {
                if (this.tiles[i][j] != that.tiles[i][j]){
                    return false;
                }
            }
        }

        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {                 //TODO so much todo...
        Stack<Board> neighbors = new Stack<Board>();

        if (pos.col > 0){
            neighbors.push(this.left());
        }
        if (pos.col < dimension()-1){
            neighbors.push(this.right());
        }
        if (pos.row > 0){
            neighbors.push(this.up());
        }
        if(pos.row < dimension()-1){
            neighbors.push(this.down());
        }

        return neighbors;
    }

    private Board left(){
        if(pos.col <= 0){
            return null;
        }

        int[][] leftTile =  tiles.clone();
        Board left = new Board(leftTile);

        left.tiles[pos.row][pos.col] = left.tiles[pos.row][pos.col-1];
        left.tiles[pos.row][pos.col-1] =0;
        left.pos.left();
        return left;
    }
    private Board right (){
        if (pos.col >= dimension()-1){
            return null;
        }

        int[][] rightTile = tiles.clone();
        Board right = new Board(rightTile);

        right.tiles[pos.row][pos.col] = right.tiles[pos.row][pos.col+1];
        right.tiles[pos.row][pos.col+1] =0;
        right.pos.right();
        return right;
    }
    private Board up(){
        if (pos.row <= 0){
            return null;
        }

        int[][] upTile = tiles.clone();
        Board up = new Board (upTile);

        up.tiles[pos.row-1][pos.col] = up.tiles[pos.row][pos.col];
        up.tiles[pos.row-1][pos.col] =0;
        up.pos.up();
        return up;
    }

    private Board down(){
        if (pos.row >= dimension()-1){
            return null;
        }

        int[][] downTile = tiles.clone();
        Board down = new Board (downTile);

        down.tiles[pos.row+1][pos.col] = down.tiles[pos.row][pos.col];
        down.tiles[pos.row+1][pos.col] =0;
        down.pos.down();
        return down;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin(){                                //Todo what todo...

    }

    // unit testing (not graded)
    public static void main(String[] args){

    }

}