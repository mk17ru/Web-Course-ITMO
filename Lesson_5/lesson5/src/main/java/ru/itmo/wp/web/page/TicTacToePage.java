package ru.itmo.wp.web.page;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@SuppressWarnings({"unused", "RedundantSuppression"})
public class TicTacToePage {

    private static State state = new State(3, true);

    public static class State {
        private static int size;
        private static boolean crossesMove;
        private static ArrayList<ArrayList<String>> cells;
        private static String phase;

        public State(int size, boolean crossesMove) {
            State.size = size;
            State.crossesMove = crossesMove;
            cells = new ArrayList<>();
            for (int i = 0; i < size; ++i) {
                cells.add(new ArrayList<>());
                for (int j = 0; j < size; ++j) {
                    cells.get(i).add("");
                }
            }
            phase = "RUNNING";
        }

        public int getSize() {
            return size;
        }

        public boolean isCrossesMove() {
            return crossesMove;
        }

        public ArrayList<ArrayList<String>> getCells() {
            return cells;
        }

        public String getPhase() {
            return phase;
        }


        public static boolean isFull() {
            int cou = 0;
            for (int i = 0; i < size; ++i) {
                for (int j = 0; j < 3; ++j) {
                    if (!cells.get(i).get(j).equals("")) {
                        cou++;
                    }
                }
            }
            return cou == size * size;
        }

        public static boolean check(String cur) {
            for (int i = 0; i < size; ++i) {
                int cou = 0;
                for (int j = 0; j < 3; ++j) {
                    if (cells.get(i).get(j).equals(cur)) {
                        cou++;
                    }
                }
                if (cou == 3) {
                    return true;
                }
                cou = 0;
                for (int j = 0; j < 3; ++j) {
                    if (cells.get(j).get(i).equals(cur)) {
                        cou++;
                    }
                }
                if (cou == 3) {
                    return true;
                }
            }
            int cou = 0;
            for (int i = 0; i < size; ++i) {
                if (cells.get(i).get(i).equals(cur)) {
                    cou++;
                }
                if (cou == 3) {
                    return true;
                }
            }
            cou = 0;
            for (int i = 0; i < size; ++i) {
                if (cells.get(size - 1 - i).get(i).equals(cur)) {
                    cou++;
                }
                if (cou == 3) {
                    return true;
                }
            }
            return false;
        }
        public static void calcPhase() {
            if (check("X")) {
                phase = "WON_X";
            } else if (check("O")) {
                phase = "WON_O";
            } else if (isFull()){
                phase = "DRAW";
            } else {
                phase = "RUNNING";
            }
        }

        public static void doMove(int x, int y) {
            if (x >= size || y >= size || x < 0 || y < 0) {
                return;
            }
            if (!cells.get(x).get(y).equals("")) {
                return;
            }
            cells.get(x).set(y, state.isCrossesMove() ? "X" : "O");
            crossesMove ^= true;
        }
    }

    private void onMove(HttpServletRequest request, Map<String, Object> view) {
        view.put("state", state);
        if (!state.getPhase().equals("RUNNING")) {
            return;
        }
        for (int i = 0; i < state.getSize(); ++i) {
            for (int j = 0; j < state.getSize(); ++j) {
                if (request.getParameter("cell_" + Integer.toString(i) + Integer.toString(j)) != null) {
                    State.doMove(i, j);
                    break;
                }
            }
        }
        State.calcPhase();
    }
    private void action(Map<String, Object> view) {
        view.put("state", state);
        State.calcPhase();
    }

    private void newGame(Map<String, Object> view) {
        state = new State(3, true);
        action(view);
    }

}
