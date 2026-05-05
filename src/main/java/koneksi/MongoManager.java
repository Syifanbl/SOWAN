/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package koneksi;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

public class MongoManager {
    private static MongoDatabase database;

    public static MongoDatabase getDatabase() {
        if (database == null) {
            try {
                // 1. Membuat konfigurasi "Penerjemah" (Codec) agar MongoDB mengerti Class Java kita
                CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(
                        MongoClientSettings.getDefaultCodecRegistry(),
                        CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build())
                );

                // 2. Hubungkan ke MongoDB lokal Anda
                MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");

                // 3. Sambungkan ke database 'db_perusahaan' dan terapkan penerjemah tadi
                database = mongoClient.getDatabase("db_perusahaan").withCodecRegistry(pojoCodecRegistry);
                
                System.out.println("[Sistem] Berhasil terhubung ke Database dengan POJO Codec.");
                
            } catch (Exception e) {
                System.err.println("[Sistem] Gagal terhubung ke database: " + e.getMessage());
            }
        }
        return database;
    }
}