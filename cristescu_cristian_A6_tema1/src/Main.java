
import java.util.Scanner;
/*
Cristescu Cristian A6
 */
public class Main {
    public static void main(String[] args) {
        long startTime = System.nanoTime();

        System.out.print("Insert your number: ");
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        if (n < 0) {
            System.out.println("Numarul trebuie sa fie pozitiv");
            return;
        }
        int[][] L = new int[n][n];
        int pivot = n + 1;
        for (int i = 0; i < n; i++) {

            int[] line = new int[n];
            int index = 0;
            int aux = pivot;
            while (aux <= n) {
                line[index] = aux;  //obtin numerele de la k al n, pentru k<=n
                index++;
                aux++;
            }
            for (int y = 1; y < pivot; y++) {
                line[index] = y;    //obtin numerele de la 1 la k
                index++;
            }
            for (int j = 0; j < n; j++) {
                L[i][j] = line[j];   //tranfer numerele din vector in linia corespunzatoare
            }

            pivot--;
        }
        if (n >= 30_000) {
            long endTime = System.nanoTime();
            long total = endTime - startTime;
            System.out.println(total);
        } else {

            for (int i = 0; i < n; i++) {
                String line = new String();
                for (int j = 0; j < n; j++) {
                    line = line + " " + Integer.toString(L[i][j]);
                }
                System.out.println(line);
            }
        }
    }
}