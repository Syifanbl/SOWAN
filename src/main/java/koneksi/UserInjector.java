/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package koneksi;
import sowan.services.AuthService;

/**
 *
 * @author SUWONO
 */
public class UserInjector {
    public static void main(String[] args) {
        AuthService userService = new AuthService();
        userService.registerUser("GEND1T", "admin", "123"); 
    }
}
