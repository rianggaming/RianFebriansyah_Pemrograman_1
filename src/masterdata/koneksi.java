/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdata;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class koneksi {

    private static Connection koneksi;

    public static Connection getConnection() {

        try {

            if (koneksi == null || koneksi.isClosed()) {

                String url = "jdbc:mysql://localhost:3306/smart_parking";
                String user = "root";
                String pass = "";

                koneksi = DriverManager.getConnection(
                        url, user, pass);

            }

            return koneksi;

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    null,
                    "Koneksi Gagal : " + e.getMessage());

            return null;
        }
    }

    public static void main(String[] args) {
        getConnection();
    }
}