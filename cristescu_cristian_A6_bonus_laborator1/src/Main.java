import java.util.Scanner;

public class Main {//Cristescu Cristian 2A6

    public static int[][] multiplyMatrix(int dimension, int[][] A, int power) {
        int i, j;
        int[][] B = new int[dimension][dimension];//matricea care va fi returnata
        int[][] A1 = new int[dimension][dimension];//matrice folosita pentru  a calcula A^power, in care este stocata mereu matricea A la o putere k, cu k <= power

        for (int p = 0; p < dimension; p++) {
            for (int m = 0; m < dimension; m++) {
                A1[p][m] = A[p][m];
            }
        }

        if (power == 1) {
            return A;
        }


        int step = 0;//folosit pentru a determina daca a fost calculata matricea A^2 pentru a putea scade din variabila power, valoarea 2

        while (power != 0) {
            for (i = 0; i < dimension; i++) {
                for (j = 0; j < dimension; j++) {
                    int mul = 0;//variabila in care este stocat elementul de pe pozitia i si j in matricea rezultata dupa ridicarea la putere
                    for (int k = 0; k < dimension; k++) {
                        mul += A1[i][k] * A[k][j];
                    }
                    B[i][j] = mul;
                }

            }

            if (step == 0) {
                step++;
                power = power - 2;
            } else power = power - 1;

            for (int p = 0; p < dimension; p++) {
                for (int q = 0; q < dimension; q++) {
                    A1[p][q] = B[p][q];
                }
            }
        }
        return B;
    }

    public static void printMatrix(int[][] A, int dimension) {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                System.out.print(A[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduceti numarul de noduri ale circuitului ");
        int dimension = scanner.nextInt();

        if (dimension < 3) {
            System.out.println("Dimensiunea circuitului este invalida. Numarul de noduri trebuie sa fie minim 3");
            return;
        }


        int[][] A = new int[dimension][dimension];


        for (int i = 0; i < dimension; i++)
            for (int j = 0; j < dimension; j++) {
                A[i][j] = 0;
            }

        for (int i = 0; i < dimension - 1; i++) {//aici este completata matricea de adiacenta cu valori de 1
            A[i][i + 1]++;//matricea de adicenta a unui graf ciclu are urmaroarea forma: pe pozitiile 0,n si n,0 sunt valori de 1
            A[i + 1][i]++;//si elementele de pe diagonalele de deasupra diagonalei principale si sub diagonala principala vor fi 1
        }

//        ex: n=4 =>   0 1 0 1
//                     1 0 1 0
//                     0 1 0 1
//                     1 0 1 0

        A[0][dimension - 1] = 1;
        A[dimension - 1][0] = 1;
        System.out.println("Matricea de adiacenta a circuitului C_n este: ");
        printMatrix(A, dimension);
        System.out.println();
        for(int i=1;i<=dimension;i++) {
            System.out.println("A^"+i+":");
            printMatrix(multiplyMatrix(dimension, A, i), dimension);
            System.out.println();
        }
    }
}