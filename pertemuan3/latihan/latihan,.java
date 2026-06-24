import java.util.Scanner;

public class latihan_rian {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        
        float kehadiran, realisasi, tugas, uts, uas;
        float bobotkehadiran, bobottugas, bobotuts, bobotuas, nilaiakhir;
        
        System.out.print("Input kehadiran   : ");
        kehadiran = input.nextFloat();

        System.out.print("Input realisasi   : ");
        realisasi = input.nextFloat();

        System.out.print("Input tugas       : ");
        tugas = input.nextFloat();

        System.out.print("Input UTS         : ");
        uts = input.nextFloat();

        System.out.print("Input UAS         : ");
        uas = input.nextFloat();

        if (realisasi == 0) {
            System.out.println("Realisasi tidak boleh 0!");
            return;
        }

        bobotkehadiran = (kehadiran / realisasi) * 10;
        bobottugas = (tugas * 20) / 100;
        bobotuts = (uts * 30) / 100;
        bobotuas = (uas * 40) / 100;
        nilaiakhir = bobotkehadiran + bobottugas + bobotuts + bobotuas;

        System.out.println("\n================================");
        System.out.println("Rincian Nilai:");
        System.out.println("Skor Kehadiran : " + bobotkehadiran);
        System.out.println("Skor Tugas     : " + bobottugas);
        System.out.println("Skor UTS       : " + bobotuts);
        System.out.println("Skor UAS       : " + bobotuas);
        System.out.println("--------------------------------");
        System.out.println("TOTAL NILAI    : " + nilaiakhir);
        System.out.println("================================");

        input.close();
    }
}
