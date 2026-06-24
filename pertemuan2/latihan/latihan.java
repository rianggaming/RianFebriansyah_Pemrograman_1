import java.util.Scanner;

public class Latihan1 {
    public static void main(String args[]) {

        Scanner input = new Scanner(System.in);

        String nama, nim, alamat;
        int usia;

        System.out.print("input nama: ");
        nama = input.nextLine();
        System.out.print("nama: " + nama);

        System.out.print("\ninput nim: ");
        nim = input.nextLine();

        System.out.print("input usia: ");
        usia = input.nextInt();
        input.nextLine(); // supaya tidak loncat

        System.out.print("input alamat: ");
        alamat = input.nextLine();
    }
}
