import edu.princeton.cs.algs4.MinPQ;

import java.util.Comparator;
import java.util.Stack;

public class Solver {
    private class SearchNode {
        private Board board;
        private int moves;
        private SearchNode previous;

        private SearchNode(Board board, int moves, SearchNode previous) {
            this.board = board;
            this.moves = moves;
            this.previous = previous;
        }
    }

    private class SearchSolutionOrder implements Comparator<SearchNode> {

        @Override
        public int compare(SearchNode solutionNode1, SearchNode solutionNode2) {
            int sN1 = solutionNode1.board.manhattan();
            int sN2 = solutionNode2.board.manhattan();
            int sN1Prio = solutionNode1.moves + sN1;
            int sN2Prio = solutionNode2.moves + sN2;

            if (sN1Prio < sN2Prio) {
                return -1;
            }
            if (sN1Prio > sN2Prio) {
                return 1;
            }
            if (sN1 < sN2) {
                return -1;
            }
            if (sN1 > sN2) {
                return 1;
            }

            return 0;
        }
    }

    private Stack<Board> solutions;
    private boolean solvable = true;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {          // Todo: Work in progress
        if (initial == null) {
            throw new IllegalArgumentException();
        }

        MinPQ<SearchNode> searcher = new MinPQ<>(new SearchSolutionOrder());

        SearchNode currentSearchNode = new SearchNode(initial, 0, null);
        boolean alive = true;


        while (alive) {

            if (currentSearchNode.board.isGoal()) {

                while(currentSearchNode.previous != null) {
                    solutions.push(currentSearchNode.board);
                    currentSearchNode = currentSearchNode.previous;
                }
                solutions.push(initial);
                alive = false;
            }

            for (Board neighbor : currentSearchNode.board.neighbors()) {

                if (currentSearchNode.previous == null || !currentSearchNode.previous.board.equals(neighbor)) {

                    searcher.insert(new SearchNode(neighbor, currentSearchNode.moves + 1, currentSearchNode));
                }

            }

        }

    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return solvable;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        return solvable ? (solutions.size() - 1) : -1;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        return solvable ? solutions : null;
    }

    // test client (see below)
    public static void main(String[] args) {

    }

}