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
import sowan.object.*;
import sowan.dao.GenericDAO;
import com.mongodb.client.model.Filters;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;


public class EnginePemrosesAbsensi {
    public static void main(String[] args) {
        GenericDAO<LogAbsensi> logDAO = new GenericDAO<>("log_mesin_absensi", LogAbsensi.class);
        GenericDAO<Absensi> absensiDAO = new GenericDAO<>("absensi", Absensi.class);
        GenericDAO<Karyawan> karyawanDAO = new GenericDAO<>("karyawan", Karyawan.class);

        System.out.println("🔄 Memulai Engine Pemroses Absensi...");

        // 1. Ambil semua log yang BELUM diproses
        List<LogAbsensi> logBelumDiproses = logDAO.findMany(Filters.eq("status_terproses", false));

        for (LogAbsensi log : logBelumDiproses) {
            // Konversi Date ke LocalDateTime untuk kemudahan kalkulasi waktu
            LocalDateTime waktuScan = log.getWaktu_scan().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            
            // 2. Cari data karyawan
            Karyawan karyawan = karyawanDAO.findOne(Filters.eq("rfid_uid", log.getRfid_uid()));
            if (karyawan == null) {
                System.out.println("⚠️ Kartu " + log.getRfid_uid() + " tidak terdaftar. Melewati...");
                tandaiLogSelesai(logDAO, log);
                continue;
            }

            // 3. LOGIKA PERGANTIAN HARI KERJA (Batas Maghrib)
            // Misalnya waktu maghrib ditetapkan pukul 18:00
            LocalTime batasMaghrib = LocalTime.of(18, 0);
            String tanggalKerjaStr = waktuScan.toLocalDate().toString();
            
            if (waktuScan.toLocalTime().isAfter(batasMaghrib) || waktuScan.toLocalTime().equals(batasMaghrib)) {
                // Jika scan setelah maghrib, dihitung sebagai hari kerja BESOKNYA
                tanggalKerjaStr = waktuScan.toLocalDate().plusDays(1).toString();
            }

            // 4. PENENTUAN MASUK ATAU PULANG
            // Cek apakah di 'tanggalKerjaStr' ini karyawan sudah punya dokumen absensi
            Absensi dataAbsenHariIni = absensiDAO.findOne(
                Filters.and(
                    Filters.eq("karyawan_id", karyawan.getId()),
                    Filters.eq("tanggal", tanggalKerjaStr)
                )
            );

            if (dataAbsenHariIni == null) {
                // Skenario A: Belum ada data = ABSEN MASUK
                Absensi absenBaru = new Absensi();
                absenBaru.setKaryawan_Id(karyawan.getId());
                absenBaru.setTanggal(tanggalKerjaStr);
                absenBaru.setWaktu_masuk(log.getWaktu_scan());
                
                // Cek kedisiplinan (Apakah datang sebelum jam masuk standar?)
                LocalTime jamStandar = LocalTime.parse(karyawan.getJam_masuk_standar());
                if (waktuScan.toLocalTime().isBefore(jamStandar) || waktuScan.toLocalTime().equals(jamStandar)) {
                    absenBaru.setBonus_kedisiplinan(true);
                } else {
                    absenBaru.setBonus_kedisiplinan(false);
                }

                absensiDAO.save(absenBaru);
                System.out.println("✅ [MASUK] Diproses untuk: " + karyawan.getNama_lengkap());

            } else {
                // Skenario B: Sudah ada data = ABSEN PULANG (Update dokumen lama)
                dataAbsenHariIni.setWaktu_pulang(log.getWaktu_scan());
                
                // 1. DETEKSI WAKTU KELUAR/PULANG
                LocalTime jamPulangStandar = LocalTime.parse(karyawan.getJam_pulang_standar());
                LocalTime waktuPulangAktual = waktuScan.toLocalTime();
                
                String statusPulang = "Tepat Waktu/Overtime";
                if (waktuPulangAktual.isBefore(jamPulangStandar)) {
                    statusPulang = "Pulang Lebih Awal";
                }

                // 2. KALKULASI GAJI HARIAN
                // Gaji pokok harian otomatis diberikan
                int totalGaji = karyawan.getGaji_harian(); 
                
                // Jika tadi pagi dapat bonus kedisiplinan, tambahkan uang bonusnya
                if (dataAbsenHariIni.isBonus_kedisiplinan()) {
                    totalGaji += karyawan.getUang_bonus();
                }
                
                dataAbsenHariIni.setTotal_gaji_hari_ini(totalGaji);
                
                // 3. UPDATE KE MONGODB
                absensiDAO.update(Filters.eq("_id", dataAbsenHariIni.getId()), dataAbsenHariIni);
                
                System.out.println("✅ [PULANG] Diproses untuk: " + karyawan.getNama_lengkap());
                System.out.println("   - Status Pulang: " + statusPulang);
                System.out.println("   - Total Gaji Hari Ini: Rp " + totalGaji);
            }

            // 5. Tandai Log sebagai 'Sudah Diproses' agar tidak dikalkulasi ulang
            tandaiLogSelesai(logDAO, log);
        }
        System.out.println("✅ Semua antrean log berhasil diproses.");
    }

    private static void tandaiLogSelesai(GenericDAO<LogAbsensi> logDAO, LogAbsensi log) {
        log.setStatus_terproses (true);
        // Lakukan update ke MongoDB
        logDAO.update(Filters.eq("_id", log.getId()), log);
    }
}