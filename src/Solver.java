import edu.princeton.cs.algs4.MinPQ;

import java.util.Comparator;
import java.util.Stack;

public class Solver {
    private class SearchSolution {
        private Board board;
        private int moves;
        private SearchSolution previous;

        private SearchSolution(Board board, int moves, SearchSolution previous) {
            this.board = board;
            this.moves = moves;
            this.previous = previous;
        }
    }

    private class SearchSolutionOrder implements Comparator<SearchSolution> {

        @Override
        public int compare(SearchSolution solutionNode1, SearchSolution solutionNode2) {
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

        MinPQ<SearchSolution> searcher = new MinPQ<>(new SearchSolutionOrder());

        SearchSolution currentSearch = new SearchSolution(initial, 0, null);
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