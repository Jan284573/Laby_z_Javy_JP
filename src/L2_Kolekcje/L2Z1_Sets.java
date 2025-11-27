package L2_Kolekcje;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class L2Z1_Sets {
    public static void main(String[] args) {
        System.out.print("Podaj długość ciągu: ");
        var scanner= new Scanner(System.in);
        int n = scanner.nextInt();

        System.out.print("Podaj ciąg liczb: ");

        Set<Integer> zbiorhash = new HashSet<>();
        Set<Integer> zbiortree = new TreeSet<>();

        for(int i=0;i<n;i++){
            int liczba = scanner.nextInt();
            zbiorhash.add(liczba);
            zbiortree.add(liczba);
        }
        scanner.close();

        System.out.print("HashSet: ");
        for (int x: zbiorhash) {
            System.out.print(x+" ");
        }
        System.out.println();

        System.out.print("TreeSet: ");
        for (int x: zbiortree) {
            System.out.print(x+" ");
        }
        System.out.println();
    }
}
