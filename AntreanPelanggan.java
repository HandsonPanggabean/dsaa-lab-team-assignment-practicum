// Nama dan NIM Team Assignment Practicum DSAA (LAB) - Group 6
// CHRISTIAN HOTASI VICCARE HUTAGAOL - 2902804014
// FAIZA KURNIAWATI - 2902807880
// ARI JUNIANTO - 2902828701
// UTAS PRASOJO - 2902809551
// HANDSON PANGGABEAN - 2902820005

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// Class untuk merepresentasikan data seorang pelanggan
class Pelanggan {
    private String nama;

    // constructor
    public Pelanggan(String nama) {
        this.nama = nama;
    }

    // getter nama pelanggan
    public String getNama() {
        return nama;
    }
}

public class AntreanPelanggan {
    // antrean pelanggan dengan prinsip FIFO (First In First Out)
    private Queue<Pelanggan> antrean;

    // constructor
    public AntreanPelanggan() {
        antrean = new LinkedList<>();
    }

    // Menambahkan pelanggan ke bagian belakang antrean
    // O(1) karena LinkedList menyimpan referensi ke node terakhir
    public void tambahPelanggan(String nama) {
        // validasi: nama tidak boleh kosong atau hanya spasi
        if (nama == null || nama.trim().isEmpty()) {
            System.out.println("Nama pelanggan tidak boleh kosong.");
            return;
        }
        nama = nama.trim(); // buang spasi di awal/akhir

        Pelanggan pelangganBaru = new Pelanggan(nama); // buat objek pelanggan baru
        antrean.add(pelangganBaru); // masukkan ke bagian belakang antrean
        System.out.println("Pelanggan " + nama + " berhasil ditambahkan ke antrean.");
    }

    // Melayani pelanggan yang berada di bagian depan antrean (FIFO)
    // Mengembalikan true jika ada pelanggan yang dilayani, false jika antrean kosong
    // O(1) karena hanya mengambil elemen pertama
    public boolean layaniPelanggan() {
        // validasi jika antrean masih kosong
        if (antrean.isEmpty()) {
            System.out.println("Antrean kosong.");
            return false;
        }

        // poll() mengambil sekaligus menghapus elemen pertama antrean
        Pelanggan pelanggan = antrean.poll();
        System.out.println("Melayani pelanggan: " + pelanggan.getNama());
        return true;
    }

    // Menampilkan seluruh pelanggan dalam antrean dari depan ke belakang
    // O(n) karena menelusuri antrean satu per satu
    public void tampilkanAntrean() {
        // validasi jika antrean masih kosong
        if (antrean.isEmpty()) {
            System.out.println("Antrean kosong.");
        } else {
            System.out.println("Pelanggan dalam antrean:");
            int nomor = 1; // counter nomor urut pelanggan
            for (Pelanggan pelanggan : antrean) {
                System.out.println(nomor + ". " + pelanggan.getNama());
                nomor++; // counter pelanggan = counter pelanggan + 1
            }
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in); // untuk membaca input dari user
        AntreanPelanggan sistemAntrean = new AntreanPelanggan();
        int pilihan;

        // tampilkan menu
        do {
            System.out.println("\n=== SISTEM ANTREAN PELANGGAN ===");
            System.out.println("1. Tambah Pelanggan");
            System.out.println("2. Layani Pelanggan");
            System.out.println("3. Tampilkan Antrean");
            System.out.println("4. Keluar");
            System.out.print("Pilih menu: ");

            // validasi input: ulangi selama input yang dimasukkan bukan angka
            while (!input.hasNextInt()) {
                System.out.println("Pilihan harus berupa angka.");
                input.nextLine();
                System.out.print("Pilih menu: ");
            }

            pilihan = input.nextInt();
            input.nextLine();

            switch (pilihan) {
                case 1: // tambah pelanggan baru ke antrean
                    System.out.print("Masukkan nama pelanggan: ");
                    String nama = input.nextLine();
                    sistemAntrean.tambahPelanggan(nama);
                    break;

                case 2: // layani pelanggan terdepan -> tampilkan sisa antrean
                    if (sistemAntrean.layaniPelanggan()) {
                        sistemAntrean.tampilkanAntrean();
                    }
                    break;

                case 3: // tampilkan seluruh antrean saat ini
                    sistemAntrean.tampilkanAntrean();
                    break;

                case 4: // keluar dari program (perulangan berhenti karena pilihan == 4)
                    System.out.println("Program selesai.");
                    break;

                default:
                    System.out.println("Pilihan tidak tersedia.");
            }
        } while (pilihan != 4);

        input.close();
    }
}
