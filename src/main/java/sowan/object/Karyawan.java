/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package sowan.object;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

/**
 *
 * @author SUWONO
 */
public class Karyawan {
    private ObjectId id;
    private String rfid_uid;
    private String nama_lengkap;
    private String departemen;
    private String jam_masuk_standar;
    private String jam_pulang_standar;
    private int gaji_harian;
    private int uang_bonus;


    public String getRfid_uid() {
        return rfid_uid;
    }

    public void setRfid_uid(String rfid_uid) {
        this.rfid_uid = rfid_uid;
    }

    public String getNama_lengkap() {
        return nama_lengkap;
    }

    public void setNama_lengkap(String nama_lengkap) {
        this.nama_lengkap = nama_lengkap;
    }

    public String getDepartemen() {
        return departemen;
    }

    public void setDepartemen(String departemen) {
        this.departemen = departemen;
    }

    public String getJam_masuk_standar() {
        return jam_masuk_standar;
    }

    public void setJam_masuk_standar(String jam_masuk_standar) {
        this.jam_masuk_standar = jam_masuk_standar;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public int getGaji_harian() {
        return gaji_harian;
    }

    public void setGaji_harian(int gaji_harian) {
        this.gaji_harian = gaji_harian;
    }

    public int getUang_bonus() {
        return uang_bonus;
    }

    public void setUang_bonus(int uang_bonus) {
        this.uang_bonus = uang_bonus;
    }

    public String getJam_pulang_standar() {
        return jam_pulang_standar;
    }

    public void setJam_pulang_standar(String jam_pulang_standar) {
        this.jam_pulang_standar = jam_pulang_standar;
    }

    
}