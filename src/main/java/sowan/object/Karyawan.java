/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package sowan.object;

/**
 *
 * @author SUWONO
 */
public class Karyawan {
    // Sesuaikan nama variabel dengan field di database
    private String rfid; 
    private String nip;  
    private String namaLengkap;
    private String departemen;

    // Konstruktor Kosong (WAJIB untuk MongoDB POJO)
    public Karyawan() {
    }

    // Konstruktor dengan parameter (Opsional)
    public Karyawan(String rfid, String nip, String namaLengkap, String departemen) {
        this.rfid = rfid;
        this.nip = nip;
        this.namaLengkap = namaLengkap;
        this.departemen = departemen;
    }

    // --- GETTER & SETTER ---
    public String getRfid() {
        return rfid;
    }

    public void setRfid(String rfid) {
        this.rfid = rfid;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getDepartemen() {
        return departemen;
    }

    public void setDepartemen(String departemen) {
        this.departemen = departemen;
    }
}