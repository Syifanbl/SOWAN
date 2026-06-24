/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package sowan.objects;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

/**
 *
 * @author SUWONO
 */
public class Karyawan {
    private String rfid_uid;
    private String nama_lengkap;
    private String departemen;
    private String jabatan;
    private String shift;
    private String id_karyawan;
    
    public Karyawan(){
        
    }
    
    public Karyawan(String id_karyawan, String rfid_uid, String nama_lengkap, String departemen, String jabatan, String shift) {
        this.rfid_uid = rfid_uid;
        this.nama_lengkap = nama_lengkap;
        this.departemen = departemen;
        this.jabatan = jabatan;
        this.shift = shift;
        this.id_karyawan = id_karyawan;
    }


    public String getId_karyawan() {
        return id_karyawan;
    }

    public void setId_karyawan(String id_karyawan) {
        this.id_karyawan = id_karyawan;
    }
    
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

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    
}