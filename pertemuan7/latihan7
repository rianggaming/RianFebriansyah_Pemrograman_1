import java.util.Scanner;

public class pertemuan7_elektronik {
    public static void main(String[] args) {

        Scanner simpan = new Scanner(System.in);

        int pilihan, jumlah;
        String merk;
        String produk = "";
        int harga = 0;
        int total = 0;
        char lagi;

        do {
            System.out.println("\n=== TOKO ELEKTRONIK ===");
            System.out.println("1. Headset    (Rp50000)");
            System.out.println("2. Mouse      (Rp30000)");
            System.out.println("3. Keyboard   (Rp80000)");
            System.out.println("4. Flashdisk  (Rp60000)");
            System.out.println("5. Powerbank  (Rp100000)");

            System.out.print("Masukkan pilihan (1-5): ");
            pilihan = simpan.nextInt();
            simpan.nextLine();

            if (pilihan >= 1 && pilihan <= 5) {

                switch (pilihan) {
                    case 1:
                        produk = "Headset";
                        harga = 50000;
                        break;
                    case 2:
                        produk = "Mouse";
                        harga = 30000;
                        break;
                    case 3:
                        produk = "Keyboard";
                        harga = 80000;
                        break;
                    case 4:
                        produk = "Flashdisk";
                        harga = 60000;
                        break;
                    case 5:
                        produk = "Powerbank";
                        harga = 100000;
                        break;
                }

                System.out.print("Masukkan merk: ");
                merk = simpan.nextLine();

                System.out.print("Masukkan jumlah: ");
                jumlah = simpan.nextInt();

                int subtotal = harga * jumlah;
                total += subtotal;

                System.out.println("\n--- Struk Sementara ---");
                System.out.println("Produk : " + produk + " (" + merk + ")");
                System.out.println("Harga  : Rp" + harga);
                System.out.println("Jumlah : " + jumlah);
                System.out.println("Subtotal: Rp" + subtotal);
                System.out.println("Total sementara: Rp" + total);

            } else {
                System.out.println("❌ Pilihan tidak tersedia");
            }

            System.out.print("\nMau beli lagi? (y/n): ");
            lagi = simpan.next().charAt(0);

        } while (lagi == 'y' || lagi == 'Y');

        System.out.println("\n=== TOTAL AKHIR ===");
        System.out.println("Total bayar: Rp" + total);
        System.out.println("Terima kasih 🙏");
    }
}
