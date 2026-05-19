/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorin.db;
 
import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;


/**
 *
 * @author Lenovo
 */
public class koneksi {
    
    public static Connection Go() {
        try {
            MysqlDataSource MDS = new MysqlDataSource();
            MDS.setServerName("localhost");
            MDS.setDatabaseName("motorindong"); 
            MDS.setUser("root");
            MDS.setPassword("");
            MDS.setPortNumber(3306);
            MDS.setServerTimezone("Asia/Jakarta");
            
            Connection conn = MDS.getConnection();
            System.out.println("Koneksi Ke databe berhasil");
            return conn;
        } catch (SQLException e) {
            System.err.println("Gagal Terhubung. \n"
                    + "Error: "+e.getMessage());
        }
        return null;
    }
    
    public static void main(String[] args) {
        Connection C = koneksi.Go();
//    }
}}

    

