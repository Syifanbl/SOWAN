/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sowan.objects;

/**
 *
 * @author SUWONO
 */
import java.util.Date;

import org.bson.types.ObjectId; 

    

public class Absensi {
    private ObjectId id;
    private ObjectId karyawan_Id;
    private String tanggal;
    private Date waktu_masuk;
    private Date waktu_pulang;
    private boolean bonus_kedisiplinan;
    private int total_gaji_hari_ini;


    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public Date getWaktu_masuk() {
        return waktu_masuk;
    }

    public void setWaktu_masuk(Date waktu_masuk) {
        this.waktu_masuk = waktu_masuk;
    }

    public Date getWaktu_pulang() {
        return waktu_pulang;
    }

    public void setWaktu_pulang(Date waktu_pulang) {
        this.waktu_pulang = waktu_pulang;
    }

    public boolean isBonus_kedisiplinan() {
        return bonus_kedisiplinan;
    }

        public void setBonus_kedisiplinan(boolean bonus_kedisiplinan) {
        this.bonus_kedisiplinan = bonus_kedisiplinan;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getKaryawan_Id() {
        return karyawan_Id;
    }

    public void setKaryawan_Id(ObjectId karyawan_Id) {
        this.karyawan_Id = karyawan_Id;
    }

    public int getTotal_gaji_hari_ini() {
        return total_gaji_hari_ini;
    }

    public void setTotal_gaji_hari_ini(int total_gaji_hari_ini) {
        this.total_gaji_hari_ini = total_gaji_hari_ini;
    }
    
    


}