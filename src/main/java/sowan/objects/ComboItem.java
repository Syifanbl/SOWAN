/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sowan.objects;

/**
 *
 * @author SUWONO
 */

public class ComboItem {
    private String key;
    private String value;

    // CONSTRUCTOR: Bagian ini yang sering salah (lupa pakai this.)
    public ComboItem(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    // Mengubah cara render text di ComboBox agar lebih aman
    @Override
    public String toString() {
        if (value == null || value.isEmpty()) {
            return "Error: Value Kosong"; // Agar terlihat jika ada yang salah
        }
        return value; 
    }

}
