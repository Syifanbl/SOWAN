/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sowan.objects;
import org.bson.types.ObjectId;

import java.util.Date;

public class LogAbsensi {
    private ObjectId id;
    private String rfid_uid;
    private Date waktu_scan;
    private String lokasi_mesin;
    private boolean status_terproses;


    public String getRfid_uid() {
        return rfid_uid;
    }

    public void setRfid_uid(String rfid_uid) {
        this.rfid_uid = rfid_uid;
    }

    public Date getWaktu_scan() {
        return waktu_scan;
    }

    public void setWaktu_scan(Date waktu_scan) {
        this.waktu_scan = waktu_scan;
    }

    public String getLokasi_mesin() {
        return lokasi_mesin;
    }

    public void setLokasi_mesin(String lokasi_mesin) {
        this.lokasi_mesin = lokasi_mesin;
    }

    public boolean isStatus_terproses() {
        return status_terproses;
    }

    public void setStatus_terproses(boolean status_terproses) {
        this.status_terproses = status_terproses;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

}