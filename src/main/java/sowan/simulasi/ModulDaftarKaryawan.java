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
import sowan.object.Karyawan;
import sowan.dao.GenericDAO;

public class ModulDaftarKaryawan {
    public static void main(String[] args) {
        GenericDAO<Karyawan> karyawanDAO = new GenericDAO<>("karyawan", Karyawan.class);

        // Simulasi pendaftaran karyawan baru
        Karyawan k = new Karyawan();
        k.setRfid_uid("04A1B2C3");
        k.setNama_lengkap("Budi Santoso");
        k.setDepartemen("IT Developer");
        // Jam masuk standar untuk perhitungan bonus
        k.setJam_masuk_standar("08:00"); 
        k.setJam_pulang_standar("17:00");
        k.setGaji_harian(100000); // Rp 100.000 / hari
        k.setUang_bonus(25000);   // Bonus Rp 25.000 jika berangkat in-time

        karyawanDAO.save(k);
        System.out.println("✅ Karyawan berhasil didaftarkan dengan RFID: " + k.getRfid_uid());
    }
}