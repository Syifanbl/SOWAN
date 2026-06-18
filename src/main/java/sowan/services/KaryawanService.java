/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sowan.services;
import sowan.dao.GenericDAO;
import sowan.objects.Karyawan;
import sowan.gui.FormTambahKaryawan;
import sowan.gui.DashboardPage;
import sowan.gui.FormEditKaryawan;
import com.mongodb.client.model.Filters;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import koneksi.EncryptionUtils;
/**
 *
 * @author SUWONO
 */
public class KaryawanService {
    
    
    // Inisialisasi GenericDAO khusus untuk entitas Karyawan
    // Menggunakan koleksi "karyawan" dan referensi Class Karyawan
  
    private final GenericDAO<Karyawan> DAO;
    
    public KaryawanService() {
        this.DAO = new GenericDAO<>("karyawan", Karyawan.class);
    }
    
    /**
     * 1.CREATE: Fungsi untuk menyimpan data karyawan baru ke MongoDB [2], [3]
     *
     * @param karyawanBaru
     */
    public void tambahKaryawan(Karyawan karyawanBaru) {
        DAO.save(karyawanBaru); // Memanggil insertOne melalui GenericDAO [3]
    }
   
    public void tambahKaryawan(String id_karyawan, String rfid_uid, String nama_lengkap, String departemen, String jabatan, String shift) {
        Karyawan karyawanBaru = new Karyawan(id_karyawan, rfid_uid, nama_lengkap, departemen, jabatan, shift);
        DAO.save(karyawanBaru); // Memanggil insertOne melalui GenericDAO [3]
    }
    
    /**
     * 2. READ (All): Fungsi untuk mengambil semua data karyawan [5], [6]
     */
    
    public void tampilkanDaftarKaryawan() {
        List<Karyawan> daftar = DAO.findAll();
        System.out.println("--- Daftar Karyawan ---");
        for (Karyawan k : daftar) {
            System.out.println(k.toString()); // Menggunakan format toString di sumber [7]
        }
    }
    
    /**
     * 2.READ (All): Fungsi untuk mengambil semua data karyawan [5], [6]
     *
     * @param targetDashboard
     * @param key
     */
    
    public void tampilKaryawan(JPanel targetDashboard, String key) {
        List<Karyawan> daftarKaryawan;
        if (key == null || key.isEmpty()) {
            daftarKaryawan = DAO.findAll();
        } else {
            daftarKaryawan = cariKaryawan(key);
        }

        targetDashboard.removeAll();
        targetDashboard.setLayout(new BorderLayout());

        // Background lebih soft (Light Gray/Blueish)
        targetDashboard.setBackground(new Color(245, 247, 251));

        // --- HEADER ---
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));

        JLabel lblTitle = new JLabel("Manajemen Data Karyawan");
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 24));
        lblTitle.setForeground(new Color(44, 62, 80)); // Dark Blue/Gray

        JButton btnTambah = new JButton("+ Tambah Karyawan");
        btnTambah.setBackground(new Color(125, 88, 255)); // Modern Green
        btnTambah.setForeground(Color.WHITE);
        btnTambah.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnTambah.setFocusPainted(false);
        btnTambah.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnTambah.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnTambah.addActionListener(e -> {
            FormTambahKaryawan formTambah = new FormTambahKaryawan(targetDashboard);
            formTambah.setLocationRelativeTo(null);
            formTambah.setVisible(true);
        });

        headerPanel.add(lblTitle, BorderLayout.WEST);
        headerPanel.add(btnTambah, BorderLayout.EAST);
        targetDashboard.add(headerPanel, BorderLayout.NORTH);

        // --- GRID PANEL ---
        // Gunakan hgap dan vgap yang lebih besar untuk pernapasan desain
        JPanel gridPanel = new JPanel(new GridLayout(0, 3, 20, 20));
        gridPanel.setOpaque(false);
        gridPanel.setBorder(BorderFactory.createEmptyBorder(10, 25, 25, 25));

        try {
            for (Karyawan k : daftarKaryawan) {
                // 2. Menghilangkan garis tepi pada CardPanel
                JPanel cardPanel = new JPanel(new BorderLayout(10, 10));
                cardPanel.setBackground(Color.WHITE);

                // Gunakan EmptyBorder saja (tanpa LineBorder) agar garis hilang
                cardPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

                // 3. Trik HTML Tabel untuk merapikan titik dua (:)
                // Kita menggabungkan semua info ke dalam satu JLabel HTML
                String infoHtml = "<html>"
                        + "<table style='margin-bottom: 5px;'>"
                        + "<tr><td><font color='#7f8c8d'><b>ID</b></font></td><td>:</td><td>" + EncryptionUtils.decrypt(k.getId_karyawan()) + "</td></tr>"
                        + "<tr><td><font color='#7f8c8d'><b>Nama</b></font></td><td>:</td><td>" + k.getNama_lengkap() + "</td></tr>"
                        + "<tr><td><font color='#7f8c8d'><b>RFID</b></font></td><td>:</td><td>" + k.getRfid_uid() + "</td></tr>"
                        + "<tr><td><font color='#7f8c8d'><b>Dept</b></font></td><td>:</td><td>" + k.getDepartemen() + "</td></tr>"
                        + "<tr><td><font color='#7f8c8d'><b>Jabatan</b></font></td><td>:</td><td>" + k.getJabatan() + "</td></tr>"
                        + "<tr><td><font color='#7f8c8d'><b>Shift</b></font></td><td>:</td><td>" + k.getShift() + "</td></tr>"
                        + "</table></html>";

                JLabel lblInfo = new JLabel(infoHtml);
                lblInfo.setFont(new Font("SansSerif", Font.PLAIN, 13));

                // Control Panel (Tombol di bawah)
                JPanel controlPanel = new JPanel(new GridLayout(1, 2, 10, 0));
                controlPanel.setOpaque(false);
                controlPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));

                JButton btnEdit = new JButton("Edit");
                btnEdit.setBackground(new Color(52, 152, 219)); // Modern Blue
                btnEdit.setForeground(Color.WHITE);
                btnEdit.setFocusPainted(false);
                btnEdit.setCursor(new Cursor(Cursor.HAND_CURSOR));
                btnEdit.addActionListener(e -> {
                    FormEditKaryawan formEdit = new FormEditKaryawan(targetDashboard);
                    formEdit.id_karyawan.setText(k.getId_karyawan());
                    formEdit.id_karyawan.setEnabled(false);
                    formEdit.txtDepartemen.setSelectedItem(k.getDepartemen());
                    formEdit.rfid_uid.setText(k.getRfid_uid());
                    formEdit.nama_lengkap.setText(k.getNama_lengkap());
                    formEdit.jabatan.setSelectedItem(k.getJabatan());
                    formEdit.shift.setSelectedItem(k.getShift());
                    formEdit.setLocationRelativeTo(null);
                    formEdit.setVisible(true);
                });

                JButton btnDel = new JButton("Delete");
                btnDel.setBackground(new Color(231, 76, 60)); // Modern Red
                btnDel.setForeground(Color.WHITE);
                btnDel.setFocusPainted(false);
                btnDel.setCursor(new Cursor(Cursor.HAND_CURSOR));
                btnDel.addActionListener(e -> {
                    int choice = JOptionPane.showConfirmDialog(null, "Hapus " + k.getNama_lengkap() + "?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                    if (choice == JOptionPane.YES_OPTION) {
                        hapusKaryawan(k.getId_karyawan());
                        tampilKaryawan(targetDashboard, "");
                    }
                });

                controlPanel.add(btnEdit);
                controlPanel.add(btnDel);

                cardPanel.add(lblInfo, BorderLayout.CENTER);
                cardPanel.add(controlPanel, BorderLayout.SOUTH);
                gridPanel.add(cardPanel);
            }

            // --- SCROLLBAR ---
            JPanel wrapper = new JPanel(new BorderLayout());
            wrapper.setOpaque(false);
            wrapper.setBorder(null);
            wrapper.add(gridPanel, BorderLayout.NORTH);

            JScrollPane scroll = new JScrollPane(wrapper);
            scroll.setBorder(null); // Menghilangkan garis kotak luar yang membungkus semua kartu
            scroll.setOpaque(false);
            scroll.getViewport().setOpaque(false);
            scroll.getVerticalScrollBar().setUnitIncrement(16);
            scroll.setBorder(null);

            targetDashboard.add(scroll, BorderLayout.CENTER);
            targetDashboard.revalidate();
            targetDashboard.repaint();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

// Fungsi Helper untuk Label (Tambahkan di bawah fungsi utama atau di class service)
    private JLabel createStyledLabel(String label, String value) {
        JLabel lbl = new JLabel("<html><b>" + label + ":</b> " + value + "</html>");
        lbl.setFont(new Font("SansSerif", Font.PLAIN, 13));
        lbl.setForeground(new Color(52, 73, 94));
        return lbl;
    }

    /**
     * 3.READ (One): Mencari satu karyawan spesifik berdasarkan UID RFID [5],
     * [6] Sangat krusial untuk alur Tap Kartu pada Pertemuan 14 [8].
     *
     * @param key
     * @return
     */
    public List<Karyawan> cariKaryawan(String key) {
        List<Bson> filters = new ArrayList<>();
        // Get all fields from the Karyawan class
        for (Field field : Karyawan.class.getDeclaredFields()) {
            // Skip the uidRfid field and non-string fields if necessary
            if (field.getName().equals("rfid_uid")) {
                continue;
            }
            filters.add(Filters.regex(field.getName(), key, "i"));
        }
        // Search and return Karyawan objects directly
        List<Karyawan> results = DAO.findMany(Filters.or(filters));
        return results;
    }
    
    /**
     * 4.UPDATE: Memperbarui data karyawan menggunakan filter Bson [5], [6]
     *
     * @param newK
     * @param page
     */
    public void updateKaryawan(Karyawan newK, JPanel targetDashboard) {
        // Pastikan filter sesuai dengan nama field di database Anda
        Bson filter = Filters.eq("id_karyawan", newK.getId_karyawan());
        Karyawan k = DAO.findOne(filter);
        if (k != null) {

            DAO.update(filter, newK);
            // Memberikan pesan sukses
            JOptionPane.showMessageDialog(null, "Data berhasil diperbarui!");
            // REFRESH menggunakan fungsi tampilKaryawan yang ada di class ini
            tampilKaryawan(targetDashboard, "");
        } else {
            JOptionPane.showMessageDialog(null, "Data tidak ditemukan di database!");
        }
    }
        
    /**
     * 5.DELETE: Menghapus data karyawan dari database [5], [6]
     * @param idK
     * @param page
     */
    public void hapusKaryawan(String idK) {
        Bson filter = Filters.eq("id_karyawan", idK);
        DAO.delete(filter); // Menghapus dari database
    }
    
}
