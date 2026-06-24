/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import dashboard.dashboard;
import javax.swing.JFrame;


/**
 *
 * @author macbook
 */
public class Navigasi { 
    public static void kembaliKeDashboard(JFrame formSekarang) {

        dashboard db = new dashboard();
        db.setVisible(true);

        formSekarang.dispose();
    }
    
}
