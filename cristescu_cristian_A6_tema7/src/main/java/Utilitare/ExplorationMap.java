package Utilitare;

import java.util.ArrayList;
import java.util.List;

public class ExplorationMap {
    private static final Integer MAX_MATRIX_SIZE = 100;
    private static final List<Token>[][] matrix = new List[MAX_MATRIX_SIZE][MAX_MATRIX_SIZE];
    private final int size;
    private final SharedMemory shared;
    private int exploring = 0;

    public ExplorationMap(int size) {
        this.size = size;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = new ArrayList<>();
            }
        }
        shared = new SharedMemory(size);
    }

    public void resetExploring() {
        this.exploring = 0;
    }

    public boolean visit(int row, int col, Robot robot) {
        boolean visited = false;
        synchronized (matrix[row][col]) {
            if (!matrix[row][col].isEmpty()) {
                //System.out.print("Elementul de pe pozitia " + row + " " + col + " deja a fost vizitat: ");
                return true;
            } else {
                List<Token> extracted = new ArrayList<>(shared.extractTokens(size));
                matrix[row][col].addAll(extracted);
                robot.explore.getMap().getMatrix()[row][col].addAll(extracted);
//                matrix[row][col].addAll(shared.extractTokens(size));
//                robot.explore.getMap().getMatrix()[row][col].addAll(shared.extractTokens(size));
                //System.out.print("Vizitez elementul de pe pozitia " + row + " " + col+": ");
                return true;
            }
        }
    }

    public boolean isExplored() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (matrix[i][j].isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean visitSystematic(int col, Robot r) {
        synchronized (matrix[0][col]) {
            if (matrix[0][col].isEmpty()) {
                exploring++;
                for (int i = 0; i < size; i++) {
                    //System.out.println(r.getName()+": vizitez elementul de pe pozitia "+i+"x"+col);
                    List<Token> extracted = new ArrayList<>(shared.extractTokens(size));
                    matrix[i][col].addAll(extracted);
                    r.explore.getMap().getMatrix()[i][col].addAll(extracted);
                    r.setExtractedTokens(r.getExtractedTokens()+extracted.size());
                }
                return true;
            }
            if (!matrix[0][col].isEmpty()) {
                for (int i = 1; i < size; i++) {
                    if (matrix[i][col].isEmpty()) {
                        List<Token> extracted = new ArrayList<>(shared.extractTokens(size));
                        matrix[i][col].addAll(extracted);
                        r.explore.getMap().getMatrix()[i][col].addAll(extracted);
                        r.setExtractedTokens(r.getExtractedTokens()+extracted.size());
                    }
                }
            }
            return false;
        }
    }

    public int getExploring() {
        return this.exploring;
    }

    public int getSize() {
        return this.size;
    }

    public List<Token>[][] getMatrix() {
        return this.matrix;
    }

    @Override
    public String toString() {
        String afisare = new String();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                for (Token t : matrix[i][j]) {
                    afisare = afisare.concat(t.toString());
                    afisare = afisare.concat(" ");
                }
                afisare = afisare.concat("##");
            }
            afisare = afisare.concat("\n");
        }
        return afisare;
    }

}
