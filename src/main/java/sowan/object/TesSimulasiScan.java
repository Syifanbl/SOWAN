/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sowan.object;

import koneksi.MongoManager;
import sowan.object.Karyawan; 
import sowan.object.LogAbsensi;
import sowan.dao.GenericDAO;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter; 
import java.util.Scanner;

public class TesSimulasiScan {
     public static void main(String[] args) {

        // 1. Inisialisasi Database
        MongoDatabase db = MongoManager.getDatabase();
        
        // 2. Inisialisasi DAO
        GenericDAO<Karyawan> karyawanDAO = new GenericDAO<>("karyawan", Karyawan.class);
        GenericDAO<LogAbsensi> logDAO = new GenericDAO<>("LogAbsensi", LogAbsensi.class);

        Scanner input = new Scanner(System.in);

        // Simulasi terus berjalan sampai dihentikan secara manual (Ctrl+C)
        while (true) {
            System.out.println("\n=== SIMULASI SCAN RFID SIPENTA ===");
            System.out.print("Scan RFID (ketik 'exit' untuk keluar): ");
            String rfidInput = input.nextLine();
            
            // Opsi untuk keluar dari simulasi
            if(rfidInput.equalsIgnoreCase("exit")) {
                System.out.println("Simulasi dihentikan.");
                break;
            }

            try {
                // 3. PENCARIAN
                Karyawan karyawan = karyawanDAO.findOne(Filters.eq("rfid", rfidInput));

                if (karyawan != null) {
                    LocalTime waktuSekarang = LocalTime.now();
                    LocalDate tanggalAbsensi = LocalDate.now();

                    // --- LOGIKA PERGANTIAN HARI (MAGHRIB) ---
                    // Jika waktu saat ini lebih dari atau sama dengan 18:00
                    LocalTime batasMaghrib = LocalTime.of(18, 0);
                    if (waktuSekarang.isAfter(batasMaghrib) || waktuSekarang.equals(batasMaghrib)) {
                        tanggalAbsensi = tanggalAbsensi.plusDays(1); // Ditambah 1 hari
                    }

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                    String waktuBersih = waktuSekarang.format(formatter);
                    // ----------------------------------------

                    // 4. Membuat objek LogAbsensi 
                    LogAbsensi log = new LogAbsensi(
                            karyawan.getNamaLengkap(), 
                            tanggalAbsensi.toString(), 
                            waktuBersih, 
                            "hadir"
                    );

                    // 5. Simpan ke MongoDB
                    logDAO.save(log);

                    System.out.println("\n✅ ABSENSI BERHASIL!");
                    System.out.println("Nama    : " + karyawan.getNamaLengkap());
                    System.out.println("Jabatan : " + karyawan.getDepartemen());
                    System.out.println("Tanggal : " + tanggalAbsensi.toString());
                    System.out.println("Waktu   : " + waktuBersih);

                } else {
                    System.out.println("\n❌ Kartu [" + rfidInput + "] tidak dikenali!");
                }
            } catch (Exception e) {
                System.err.println("\n❌ Terjadi kesalahan: " + e.getMessage());
            }
        }
        input.close();
    }
}