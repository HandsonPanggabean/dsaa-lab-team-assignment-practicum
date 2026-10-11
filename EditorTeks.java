// Nama dan NIM Team Assignment Practicum DSAA (LAB) - Group 6
// CHRISTIAN HOTASI VICCARE HUTAGAOL - 2902804014
// FAIZA KURNIAWATI - 2902807880
// ARI JUNIANTO - 2902828701
// UTAS PRASOJO - 2902809551
// HANDSON PANGGABEAN - 2902820005

import java.util.Stack;

public class EditorTeks {

    private String teksSekarang;
    private Stack<String> undoStack;
    private Stack<String> redoStack;

    // constructor
    public EditorTeks() {
        this.teksSekarang = "";
        this.undoStack = new Stack<>();
        this.redoStack = new Stack<>();
    }

    // Menambahkan teks baru di akhir teks saat ini
    public void tambahTeks(String teks) {
        undoStack.push(teksSekarang); // kondisi teks sebelumnya disimpan ke undoStack (push)
        teksSekarang = teksSekarang + teks;
        redoStack.clear(); // redoStack dikosongkan karena riwayat redo tidak berlaku lagi setelah ada perubahan baru
    }

    // Mengembalikan ke kondisi sebelum perubahan terakhir
    public boolean undo() {
        if (undoStack.isEmpty()) {
            System.out.println("Tidak ada yang bisa di-undo.");
            return false;
        }
        redoStack.push(teksSekarang); // teks saat ini dipindahkan ke redoStack
        teksSekarang = undoStack.pop(); // kondisi sebelumnya diambil dari undoStack (pop)
        return true;
    }

    // Mengembalikan perubahan yang telah di-undo
    public boolean redo() {
        if (redoStack.isEmpty()) {
            System.out.println("Tidak ada yang bisa di-redo.");
            return false;
        }
        undoStack.push(teksSekarang); // teks saat ini dipindahkan ke undoStack
        teksSekarang = redoStack.pop(); // Kondisi yang dibatalkan diambil dari redoStack (pop)
        return true;
    }

    // getter: mengambil teks yang sedang ditampilkan di editor
    public String getTeksSekarang() {
        return teksSekarang;
    }

    // Menampilkan teks saat ini ke layar, diberi tanda kutip agar teks kosong tetap terlihat
    public void tampilkanTeks() {
        System.out.println("Teks saat ini: \"" + teksSekarang + "\"");
    }

    // testing
    public static void main(String[] args) {
        EditorTeks editor = new EditorTeks();

        // Skenario sesuai contoh output di soal
        editor.tambahTeks("Selamat");
        editor.tambahTeks(" datang");
        editor.tampilkanTeks(); 

        editor.undo();
        System.out.print("Undo: ");
        System.out.println("\"" + editor.getTeksSekarang() + "\""); 

        editor.redo();
        System.out.print("Redo: ");
        System.out.println("\"" + editor.getTeksSekarang() + "\""); 

        // Edge case: undo/redo saat stack kosong
        System.out.println("\n--- Edge case ---");
        EditorTeks kosong = new EditorTeks();
        kosong.undo();
        kosong.redo();

        // Edge case: tambah teks baru setelah undo (redo harus terhapus)
        editor.undo();
        editor.tambahTeks(" semua");
        editor.tampilkanTeks(); 
        editor.redo(); 

        // Pengukuran waktu eksekusi untuk laporan analisis
        System.out.println("\n--- Waktu eksekusi ---");
        EditorTeks uji = new EditorTeks();
        int n = 10000;

        long mulai = System.nanoTime();
        for (int i = 0; i < n; i++)
            uji.tambahTeks("a");
        long selesai = System.nanoTime();
        System.out.println("tambahTeks x" + n + ": " + (selesai - mulai) + " ns");

        mulai = System.nanoTime();
        for (int i = 0; i < n; i++)
            uji.undo();
        selesai = System.nanoTime();
        System.out.println("undo x" + n + ": " + (selesai - mulai) + " ns");

        mulai = System.nanoTime();
        for (int i = 0; i < n; i++)
            uji.redo();
        selesai = System.nanoTime();
        System.out.println("redo x" + n + ": " + (selesai - mulai) + " ns");
    }
}