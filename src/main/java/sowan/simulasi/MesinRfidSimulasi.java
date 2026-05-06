/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sowan.simulasi;

/**
 *
 * @author SUWONO
 */

import koneksi.MongoManager;
import sowan.object.LogAbsensi;
import sowan.dao.GenericDAO;
import java.util.Date;
import java.util.Scanner;

public class MesinRfidSimulasi {
    public static void main(String[] args) {
        GenericDAO<LogAbsensi> logDAO = new GenericDAO<>("log_mesin_absensi", LogAbsensi.class);
        Scanner input = new Scanner(System.in);

        System.out.println("=== MESIN RFID AKTIF ===");
        while (true) {
            System.out.print("Tempelkan Kartu: ");
            String rfid = input.nextLine();

            // Mesin HANYA mencatat UID dan Waktu, lalu langsung simpan.
            LogAbsensi log = new LogAbsensi();
            log.setRfid_uid(rfid);
            log.setWaktu_scan(new Date()); 
            log.setLokasi_mesin("Pintu Utama");
            log.setStatus_terproses(false); // Tandai bahwa log ini masih mentah

            logDAO.save(log);
            System.out.println("TITT! (Data tersimpan di Log)");
        }
    }
}