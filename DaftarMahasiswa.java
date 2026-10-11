// Nama dan NIM Team Assignment Practicum DSAA (LAB) - Group 6
// CHRISTIAN HOTASI VICCARE HUTAGAOL - 2902804014
// FAIZA KURNIAWATI - 2902807880
// ARI JUNIANTO - 2902828701
// UTAS PRASOJO - 2902809551
// HANDSON PANGGABEAN - 2902820005

public class DaftarMahasiswa {
    private Node head; // node pertama dalam linked list
    private Node tail; // node terakhir dalam linked list
    private int jumlahMahasiswa; // jumlah mahasiswa yang tersimpan saat ini

    // constructor
    public DaftarMahasiswa() {
        this.head = null;
        this.tail = null;
        this.jumlahMahasiswa = 0;
    }

    // getter jumlah mahasiswa yang tersimpan saat ini
    public int getJumlahMahasiswa() {
        return jumlahMahasiswa;
    }

    // helper method: mencari Node berdasarkan NIM
    private Node cariNode(long nim) {
        Node current = head;
        while (current != null) {
            // jika ditemukan
            if (current.getNim() == nim) {
                return current; // return node
            }
            current = current.getNext();
        }
        return null; // jika tidak ditemukan return null
    }

    // Menambahkan mahasiswa ke akhir linked list
    // Insert di tail O(1), tetapi pengecekan NIM duplikat (cariNode) O(n), sehingga total O(n)
    public void tambahMahasiswa(long nim, String nama, double nilai) {
        // validasi NIM yang duplikat
        if (cariNode(nim) != null) {
            System.out.println("Gagal menambahkan: NIM " + nim + " sudah terdaftar.");
            return;
        }

        Node baru = new Node(nim, nama, nilai);

        // jika list masih kosong
        if (head == null) {
            // node mahasiswa baru jadi head sekaligus tail
            head = baru;
            tail = baru;
        } else {
            // sambungkan node mahasiswa lama (tail) ke node mahasiswa baru, lalu geser tail
            tail.setNext(baru);
            tail = baru;
        }

        jumlahMahasiswa++;
        System.out.println("Mahasiswa " + nama + " (NIM: " + nim + ") berhasil ditambahkan.");
    }

    // Menghapus mahasiswa dari linked list berdasarkan NIM
    // O(n) karena menelusuri list satu per satu
    public void hapusMahasiswa(long nim) {
        // validasi jika list masih kosong
        if (head == null) {
            System.out.println("Gagal menghapus: daftar mahasiswa masih kosong.");
            return;
        }

        Node current = head; // mulai dari node pertama
        Node sebelumnya = null;

        // telusuri list sampai ketemu node dengan NIM yang dicari
        while (current != null && current.getNim() != nim) {
            // jika current node mahasiswa ada dan current node mahasiswa nim tidak sama dengan parameter nim
            sebelumnya = current; // simpan node mahasiswa ke dalam variable "sebelumnya"
            current = current.getNext(); // set current menjadi node mahasiswa yang selanjutnya
        }

        // jika setelah perulangan di atas tidak ada node mahasiswa yang ditemukan
        if (current == null) {
            // berikan informasi bahwa mahasiswa tidak ditemukan
            System.out.println("Mahasiswa dengan NIM " + nim + " tidak ditemukan.");
            return;
        }

        // jika node sebelumnya tidak ada
        if (sebelumnya == null) {
            // berarti yang dihapus adalah head
            head = current.getNext(); // geser head ke node mahasiswa berikutnya
        } else {
            // berarti node yang dihapus berada di tengah-tengah
            sebelumnya.setNext(current.getNext());
            /**
             * current.getNext() -> pointer ke node mahasiswa selanjutnya dari node mahasiswa yang akan dihapus
             * sebelumnya.setNext() -> set posisi node mahasiswa dari current.getNext() menjadi di samping node mahasiswa sebelumnya
             * 
             * misalkan list: Andi, Budi, Cecep
             * misalkan Budi (tengah) yang mau dihapus
             * 
             * posisi list berubah menjadi : Andi, Cecep dengan menggunakan sebelumnya.setNext(current.getNext());
            */
        }

        // jika node yang akan dihapus adalah tail
        if (current == tail) {
            tail = sebelumnya; // perbarui tail menjadi sebelumnya
        }

        jumlahMahasiswa--;
        System.out.println("Mahasiswa " + current.getNama() + " (NIM: " + nim + ") berhasil dihapus.");
    }

    // Update nilai mahasiswa berdasarkan NIM
    // O(n) karena menelusuri list satu per satu
    public void updateNilai(long nim, double nilaiBaru) {
        Node target = cariNode(nim); // cari node mahasiswa

        // validasi jika node mahasiswa tidak ditemukan
        if (target == null) {
            System.out.println("Mahasiswa dengan NIM " + nim + " tidak ditemukan.");
            return;
        }

        System.out.println("Mengupdate nilai mahasiswa (" + target.getNama() + " -> " + nilaiBaru + ")");
        target.setNilai(nilaiBaru); // set nilai node mahasiswa menjadi nilaiBaru dari parameter
    }

    // Menampilkan seluruh daftar mahasiswa secara berurutan dari head ke tail
    // O(n) karena menelusuri list satu per satu
    public void tampilkanDaftar() {
        // validasi jika list masih kosong
        if (head == null) {
            System.out.println("Daftar mahasiswa masih kosong.");
            return;
        }

        System.out.println("Daftar Mahasiswa:");
        Node current = head; // mulai dari node pertama
        int nomor = 1; // counter mahasiswa

        // selama current berisi node mahasiswa
        while (current != null) {
            System.out.println(nomor + ". NIM: " + current.getNim() + ", Nama: " + current.getNama() + ", Nilai: " + current.getNilai());
            current = current.getNext(); // set current menjadi node mahasiswa berikutnya
            nomor++; // counter mahasiswa = counter mahasiswa + 1
        }
    }

    // testing
    public static void main(String[] args) {
        DaftarMahasiswa daftar = new DaftarMahasiswa();

        daftar.tambahMahasiswa(12345, "Andi", 85);
        daftar.tambahMahasiswa(67890, "Budi", 90);

        System.out.println();
        daftar.tampilkanDaftar();

        System.out.println();
        daftar.updateNilai(67890, 95);

        System.out.println();
        daftar.tampilkanDaftar();

        System.out.println();
        daftar.hapusMahasiswa(12345);

        System.out.println();
        daftar.tampilkanDaftar();
    }
}