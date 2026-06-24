import java.util.Scanner;

public class Latihan1 {
    public static void main(String args[]) {

        Scanner input = new Scanner(System.in);

        String nama, nim, alamat;
        int usia;

        System.out.print("Input Nama   : ");
        nama = input.nextLine();

        System.out.print("Input NIM    : ");
        nim = input.nextLine();

        System.out.print("Input Usia   : ");
        usia = input.nextInt();
        input.nextLine();

        System.out.print("Input Alamat : ");
        alamat = input.nextLine();

        System.out.println("\n=== DATA MAHASISWA ===");
        System.out.println("Nama   : " + nama);
        System.out.println("NIM    : " + nim);
        System.out.println("Usia   : " + usia);
        System.out.println("Alamat : " + alamat);

        if (usia >= 18) {
            System.out.println("Status : Dewasa");
        } else {
            System.out.println("Status : Belum Dewasa");
        }

        input.close();
    }
}
