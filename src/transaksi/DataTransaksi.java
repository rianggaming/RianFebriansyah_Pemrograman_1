/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transaksi;

import koneksi.koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.FileWriter;
import java.awt.Desktop;
import java.text.MessageFormat;
import javax.swing.JTable;

/**
 *
 * @author macbook
 */
public class DataTransaksi extends javax.swing.JFrame {

    /**
     * Creates new form DataTransaksi
     */
    
    String idTransaksi = "";
    public DataTransaksi() {
        initComponents();
        setSize(900, 600);
        setLocationRelativeTo(null);
        tampilData();
    }
    
    public void tampilData() {

    DefaultTableModel model = new DefaultTableModel();

    model.addColumn("ID Transaksi");
    model.addColumn("No Polisi");
    model.addColumn("Jenis Kendaraan");
    model.addColumn("Waktu Masuk");
    model.addColumn("Waktu Keluar");
    model.addColumn("Total Tarif");
    model.addColumn("Status");

    try {

        Connection conn = koneksi.getConnection();

        String sql =
                "SELECT transaksi.id_transaksi, "
                + "kendaraan.no_polisi, "
                + "kendaraan.jenis_kendaraan, "
                + "transaksi.waktu_masuk, "
                + "transaksi.waktu_keluar, "
                + "transaksi.total_tarif, "
                + "transaksi.status "
                + "FROM transaksi "
                + "JOIN kendaraan ON transaksi.id_kendaraan = kendaraan.id_kendaraan "
                + "ORDER BY transaksi.id_transaksi DESC";

        PreparedStatement pst = conn.prepareStatement(sql);

        ResultSet rs = pst.executeQuery();

        while (rs.next()) {

            model.addRow(new Object[]{
                rs.getString("id_transaksi"),
                rs.getString("no_polisi"),
                rs.getString("jenis_kendaraan"),
                rs.getString("waktu_masuk"),
                rs.getString("waktu_keluar"),
                rs.getString("total_tarif"),
                rs.getString("status")
            });
        }

        jTable1.setModel(model);

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                null,
                "Data transaksi gagal ditampilkan : "
                + e.getMessage());
    }
}
    
    public void cariData() {

    DefaultTableModel model = new DefaultTableModel();

    model.addColumn("ID Transaksi");
    model.addColumn("No Polisi");
    model.addColumn("Jenis Kendaraan");
    model.addColumn("Waktu Masuk");
    model.addColumn("Waktu Keluar");
    model.addColumn("Total Tarif");
    model.addColumn("Status");

    try {

        Connection conn = koneksi.getConnection();

        String keyword = jTextField1.getText();

        String sql =
                "SELECT transaksi.id_transaksi, "
                + "kendaraan.no_polisi, "
                + "kendaraan.jenis_kendaraan, "
                + "transaksi.waktu_masuk, "
                + "transaksi.waktu_keluar, "
                + "transaksi.total_tarif, "
                + "transaksi.status "
                + "FROM transaksi "
                + "JOIN kendaraan ON transaksi.id_kendaraan = kendaraan.id_kendaraan "
                + "WHERE transaksi.id_transaksi LIKE ? "
                + "OR kendaraan.no_polisi LIKE ? "
                + "OR kendaraan.jenis_kendaraan LIKE ? "
                + "OR transaksi.waktu_masuk LIKE ? "
                + "OR transaksi.waktu_keluar LIKE ? "
                + "OR transaksi.total_tarif LIKE ? "
                + "OR transaksi.status LIKE ? "
                + "ORDER BY transaksi.id_transaksi DESC";

        PreparedStatement pst = conn.prepareStatement(sql);

        pst.setString(1, "%" + keyword + "%");
        pst.setString(2, "%" + keyword + "%");
        pst.setString(3, "%" + keyword + "%");
        pst.setString(4, "%" + keyword + "%");
        pst.setString(5, "%" + keyword + "%");
        pst.setString(6, "%" + keyword + "%");
        pst.setString(7, "%" + keyword + "%");

        ResultSet rs = pst.executeQuery();

        while (rs.next()) {

            model.addRow(new Object[]{
                rs.getString("id_transaksi"),
                rs.getString("no_polisi"),
                rs.getString("jenis_kendaraan"),
                rs.getString("waktu_masuk"),
                rs.getString("waktu_keluar"),
                rs.getString("total_tarif"),
                rs.getString("status")
            });
        }

        jTable1.setModel(model);

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                null,
                "Data transaksi gagal dicari : "
                + e.getMessage());
    }
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButtonHapus = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 1, 24)); // NOI18N
        jLabel1.setText("DATA TRANSAKSI");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jButton1.setText("Cari");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Refresh");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Print");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButtonHapus.setText("Hapus");
        jButtonHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHapusActionPerformed(evt);
            }
        });

        jButton4.setText("Kembali");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(126, 126, 126)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonHapus)
                .addGap(234, 234, 234))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(185, 185, 185))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton4)
                        .addGap(132, 132, 132))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel1)
                .addGap(48, 48, 48)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButtonHapus))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
try {
    String html = "<html>"
            + "<head>"
            + "<title>Data Transaksi</title>"
            + "<style>"
            + "table{border-collapse:collapse;width:100%;}"
            + "th,td{border:1px solid black;padding:8px;text-align:left;}"
            + "th{background:#cccccc;}"
            + "h2{text-align:center;}"
            + "</style>"
            + "</head>"
            + "<body>";

    html += "<h2>LAPORAN DATA TRANSAKSI PARKIR</h2>";
    html += "<table>";
    html += "<tr>"
            + "<th>ID Transaksi</th>"
            + "<th>No Polisi</th>"
            + "<th>Jenis Kendaraan</th>"
            + "<th>Waktu Masuk</th>"
            + "<th>Waktu Keluar</th>"
            + "<th>Total Tarif</th>"
            + "<th>Status</th>"
            + "</tr>";

    for (int i = 0; i < jTable1.getRowCount(); i++) {

        html += "<tr>";

        for (int j = 0; j < jTable1.getColumnCount(); j++) {

            html += "<td>"
                    + jTable1.getValueAt(i, j)
                    + "</td>";
        }

        html += "</tr>";
    }

    html += "</table>";
    html += "</body></html>";

    File file = new File("laporan_transaksi.html");

    FileWriter fw = new FileWriter(file);
    fw.write(html);
    fw.close();

    Desktop.getDesktop().browse(file.toURI());

} catch (Exception e) {

    JOptionPane.showMessageDialog(null,
            "Gagal membuat laporan : "
            + e.getMessage());
}        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
cariData();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
jTextField1.setText("");
tampilData();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
                                    

    int baris = jTable1.getSelectedRow();

    if (baris != -1) {

        idTransaksi = jTable1.getValueAt(baris, 0).toString();

    }
      // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButtonHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHapusActionPerformed
try {

    if (idTransaksi == null || idTransaksi.equals("")) {

        JOptionPane.showMessageDialog(
                null,
                "Pilih data transaksi yang akan dihapus terlebih dahulu");

        return;
    }

    int konfirmasi =
            JOptionPane.showConfirmDialog(
                    null,
                    "Yakin ingin menghapus data transaksi ini?",
                    "Konfirmasi Hapus",
                    JOptionPane.YES_NO_OPTION);

    if (konfirmasi == JOptionPane.YES_OPTION) {

        Connection conn = koneksi.getConnection();

        String sql =
                "DELETE FROM transaksi WHERE id_transaksi=?";

        PreparedStatement pst =
                conn.prepareStatement(sql);

        pst.setString(1, idTransaksi);

        pst.executeUpdate();

        JOptionPane.showMessageDialog(
                null,
                "Data transaksi berhasil dihapus");

        tampilData();

        idTransaksi = "";
    }

} catch (Exception e) {

    JOptionPane.showMessageDialog(
            null,
            "Data transaksi gagal dihapus : "
            + e.getMessage());
}        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonHapusActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
helper.Navigasi.kembaliKeDashboard(this);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DataTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DataTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DataTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DataTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DataTransaksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButtonHapus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
