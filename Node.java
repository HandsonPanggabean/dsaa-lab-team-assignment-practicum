// Nama dan NIM Team Assignment Practicum DSAA (LAB) - Group 6
// CHRISTIAN HOTASI VICCARE HUTAGAOL - 2902804014
// FAIZA KURNIAWATI - 2902807880
// ARI JUNIANTO - 2902828701
// UTAS PRASOJO - 2902809551
// HANDSON PANGGABEAN - 2902820005

public class Node {
    private long nim;
    private String nama;
    private double nilai;
    private Node next; // referensi ke node berikutnya dalam linked list

    // constructor untuk inisialisasi data mahasiswa pada sebuah node baru
    public Node(long nim, String nama, double nilai) {
        this.nim = nim;
        this.nama = nama;
        this.nilai = nilai;
        this.next = null;
    }

    // getter & setter nim
    public long getNim() {
        return nim;
    }
    public void setNim(long nim) {
        this.nim = nim;
    }

    // getter & setter nama
    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }

    // getter & setter nilai
    public double getNilai() {
        return nilai;
    }
    public void setNilai(double nilai) {
        this.nilai = nilai;
    }

    // getter & setter next (pointer ke node berikutnya)
    public Node getNext() {
        return next;
    }
    public void setNext(Node next) {
        this.next = next;
    }
}