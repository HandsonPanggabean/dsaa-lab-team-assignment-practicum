// Nama dan NIM Team Assignment Practicum DSAA (LAB) - Group 6
// CHRISTIAN HOTASI VICCARE HUTAGAOL - 2902804014
// FAIZA KURNIAWATI - 2902807880
// ARI JUNIANTO - 2902828701
// UTAS PRASOJO - 2902809551
// HANDSON PANGGABEAN - 2902820005

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Benchmark {

    // Jumlah data yang digunakan dalam pengujian
    static final int JUMLAH_DATA = 10000;

    // Jumlah percobaan
    static final int JUMLAH_PERCOBAAN = 5;

    // Linked List sederhana

    static class SimpleLinkedList {

        private Node head;
        private Node tail;

        // Menambahkan data ke akhir linked list
        // O(1) karena menggunakan referensi tail
        public void add(int data) {
            Node baru = new Node(data, "", 0);

            // list kosong: node baru menjadi head sekaligus tail
            if (head == null) {
                head = baru;
                tail = baru;
            } else {
                // sambungkan ke tail lama, lalu geser tail
                tail.setNext(baru);
                tail = baru;
            }
        }

        // Menghapus data pertama
        // O(1) karena hanya menggeser head ke node berikutnya
        public void removeFirst() {
            if (head != null) {
                head = head.getNext();

                // jika list menjadi kosong, tail juga harus di-reset
                if (head == null) {
                    tail = null;
                }
            }
        }
    }
    
    // Benchmark Queue

    // Mengukur waktu menambah JUMLAH_DATA elemen ke Queue (offer), dalam nanodetik
    public static long benchmarkQueueTambah() {

        Queue<Integer> queue = new LinkedList<>();

        long mulai = System.nanoTime();

        for (int i = 0; i < JUMLAH_DATA; i++) {
            queue.offer(i);
        }

        long selesai = System.nanoTime();

        return selesai - mulai;
    }

    // Mengukur waktu menghapus JUMLAH_DATA elemen dari Queue (poll), dalam nanodetik (pengisian awal tidak ikut dihitung)
    public static long benchmarkQueueHapus() {

        Queue<Integer> queue = new LinkedList<>();

        // Isi queue terlebih dahulu
        for (int i = 0; i < JUMLAH_DATA; i++) {
            queue.offer(i);
        }

        long mulai = System.nanoTime();

        for (int i = 0; i < JUMLAH_DATA; i++) {
            queue.poll();
        }

        long selesai = System.nanoTime();

        return selesai - mulai;
    }

    // Benchmark Stack

    // Mengukur waktu menambah JUMLAH_DATA elemen ke Stack (push), dalam nanodetik
    public static long benchmarkStackTambah() {

        Stack<Integer> stack = new Stack<>();

        long mulai = System.nanoTime();

        for (int i = 0; i < JUMLAH_DATA; i++) {
            stack.push(i);
        }

        long selesai = System.nanoTime();

        return selesai - mulai;
    }

    // Mengukur waktu menghapus JUMLAH_DATA elemen dari Stack (pop), dalam nanodetik (pengisian awal tidak ikut dihitung)
    public static long benchmarkStackHapus() {

        Stack<Integer> stack = new Stack<>();

        // Isi stack terlebih dahulu
        for (int i = 0; i < JUMLAH_DATA; i++) {
            stack.push(i);
        }

        long mulai = System.nanoTime();

        for (int i = 0; i < JUMLAH_DATA; i++) {
            stack.pop();
        }

        long selesai = System.nanoTime();

        return selesai - mulai;
    }

    // Benchmark Linked List

    // Mengukur waktu menambah JUMLAH_DATA elemen ke SimpleLinkedList (add), dalam nanodetik
    public static long benchmarkLinkedListTambah() {

        SimpleLinkedList list = new SimpleLinkedList();

        long mulai = System.nanoTime();

        for (int i = 0; i < JUMLAH_DATA; i++) {
            list.add(i);
        }

        long selesai = System.nanoTime();

        return selesai - mulai;
    }

    // Mengukur waktu menghapus JUMLAH_DATA elemen dari SimpleLinkedList (removeFirst), dalam nanodetik (pengisian awal tidak ikut dihitung)
    public static long benchmarkLinkedListHapus() {

        SimpleLinkedList list = new SimpleLinkedList();

        // Isi linked list terlebih dahulu
        for (int i = 0; i < JUMLAH_DATA; i++) {
            list.add(i);
        }

        long mulai = System.nanoTime();

        for (int i = 0; i < JUMLAH_DATA; i++) {
            list.removeFirst();
        }

        long selesai = System.nanoTime();

        return selesai - mulai;
    }

    // Menghitung rata-rata

    // Rata-rata waktu operasi tambah pada Queue
    public static long rataRataQueueTambah() {
        long total = 0;

        for (int i = 0; i < JUMLAH_PERCOBAAN; i++) {
            total += benchmarkQueueTambah();
        }

        return total / JUMLAH_PERCOBAAN;
    }

    // Rata-rata waktu operasi hapus pada Queue
    public static long rataRataQueueHapus() {
        long total = 0;

        for (int i = 0; i < JUMLAH_PERCOBAAN; i++) {
            total += benchmarkQueueHapus();
        }

        return total / JUMLAH_PERCOBAAN;
    }

    // Rata-rata waktu operasi tambah pada Stack
    public static long rataRataStackTambah() {
        long total = 0;

        for (int i = 0; i < JUMLAH_PERCOBAAN; i++) {
            total += benchmarkStackTambah();
        }

        return total / JUMLAH_PERCOBAAN;
    }

    // Rata-rata waktu operasi hapus pada Stack
    public static long rataRataStackHapus() {
        long total = 0;

        for (int i = 0; i < JUMLAH_PERCOBAAN; i++) {
            total += benchmarkStackHapus();
        }

        return total / JUMLAH_PERCOBAAN;
    }

    // Rata-rata waktu operasi tambah pada Linked List
    public static long rataRataLinkedListTambah() {
        long total = 0;

        for (int i = 0; i < JUMLAH_PERCOBAAN; i++) {
            total += benchmarkLinkedListTambah();
        }

        return total / JUMLAH_PERCOBAAN;
    }

    // Rata-rata waktu operasi hapus pada Linked List
    public static long rataRataLinkedListHapus() {
        long total = 0;

        for (int i = 0; i < JUMLAH_PERCOBAAN; i++) {
            total += benchmarkLinkedListHapus();
        }

        return total / JUMLAH_PERCOBAAN;
    }

    // MAIN

    // Menjalankan seluruh benchmark dan menampilkan hasil rata-rata
    public static void main(String[] args) {

        System.out.println("==============================================");
        System.out.println("   BENCHMARK QUEUE vs STACK vs LINKED LIST");
        System.out.println("==============================================");

        System.out.println("Jumlah data     : " + JUMLAH_DATA);
        System.out.println("Jumlah percobaan: " + JUMLAH_PERCOBAAN);
        System.out.println();

        System.out.println("HASIL BENCHMARK (RATA-RATA)");
        System.out.println("----------------------------------------------");

        long queueTambah = rataRataQueueTambah();
        long stackTambah = rataRataStackTambah();
        long linkedListTambah = rataRataLinkedListTambah();

        long queueHapus = rataRataQueueHapus();
        long stackHapus = rataRataStackHapus();
        long linkedListHapus = rataRataLinkedListHapus();

        System.out.println();
        System.out.println("Operasi Tambah:");
        System.out.println("Queue       : " + queueTambah + " ns");
        System.out.println("Stack       : " + stackTambah + " ns");
        System.out.println("Linked List : " + linkedListTambah + " ns");

        System.out.println();
        System.out.println("Operasi Hapus:");
        System.out.println("Queue       : " + queueHapus + " ns");
        System.out.println("Stack       : " + stackHapus + " ns");
        System.out.println("Linked List : " + linkedListHapus + " ns");

        System.out.println();
        System.out.println("==============================================");
    }
}