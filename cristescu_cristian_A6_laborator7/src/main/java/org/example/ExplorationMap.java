package org.example;

import java.util.ArrayList;
import java.util.List;

public class ExplorationMap {
    private static final Integer MAX_MATRIX_SIZE = 100;
    private static final List<Token>[][] matrix = new List[MAX_MATRIX_SIZE][MAX_MATRIX_SIZE];
    private final int size;
    private final SharedMemory shared;

    public ExplorationMap(int size) {
        this.size=size;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = new ArrayList<>();
            }
        }
        shared = new SharedMemory(size);
    }

    public boolean visit(int row, int col, Robot robot) {
        boolean visited = false;
        synchronized (matrix[row][col]) {
            if (!matrix[row][col].isEmpty()) {
                System.out.print("Elementul de pe pozitia " + row + " " + col + " deja a fost vizitat: ");
                return true;
            } else {
                matrix[row][col].addAll(shared.extractTokens(size));
                System.out.print("Vizitez elementul de pe pozitia " + row + " " + col+": ");
                return true;
            }
        }
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

//    public void afiseaza() {
//        for (int i = 0; i < size; i++) {
//            for (int j = 0; j < size; j++) {
//                for (Token t : matrix[i][j]) {
//                    System.out.print(t.getNumber() + " ");
//                }
//                System.out.print("##");
//            }
//            System.out.println();
//        }
//    }
}
