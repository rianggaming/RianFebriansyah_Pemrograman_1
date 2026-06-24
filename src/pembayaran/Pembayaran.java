/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pembayaran;

import koneksi.koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;

/**
 *
 * @author macbook
 */
public class Pembayaran extends javax.swing.JFrame {

    /**
     * Creates new form Pembayaran
     */
    public Pembayaran() {
        initComponents();
        setSize(900, 600);
        setLocationRelativeTo(null);
        jTextField1.setEditable(false);
        jTextField2.setEditable(false);
    
        loadTransaksi();
        tampilWaktu();
        tampilData();
    }

    String idTransaksi = "";
    String idPembayaran = "";
    
    public void tampilWaktu() {
    SimpleDateFormat sdf =
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    jTextField2.setText(sdf.format(new Date()));
}
    
    public void loadTransaksi() {

    try {

        Connection conn = koneksi.getConnection();

        String sql =
                "SELECT transaksi.id_transaksi, kendaraan.no_polisi, kendaraan.jenis_kendaraan "
                + "FROM transaksi "
                + "JOIN kendaraan ON transaksi.id_kendaraan = kendaraan.id_kendaraan "
                + "WHERE transaksi.status='Selesai' "
                + "AND transaksi.id_transaksi NOT IN "
                + "(SELECT id_transaksi FROM pembayaran)";

        PreparedStatement pst =
                conn.prepareStatement(sql);

        ResultSet rs =
                pst.executeQuery();

        jComboBox1.removeAllItems();

        while (rs.next()) {

            jComboBox1.addItem(
                    rs.getString("id_transaksi")
                    + " - "
                    + rs.getString("no_polisi")
                    + " - "
                    + rs.getString("jenis_kendaraan"));
        }

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                null,
                "Gagal memuat transaksi : "
                + e.getMessage());
    }
}
    
    public void tampilData() {

    DefaultTableModel model =
            new DefaultTableModel();

    model.addColumn("ID Pembayaran");
    model.addColumn("ID Transaksi");
    model.addColumn("No Polisi");
    model.addColumn("Total Tagihan");
    model.addColumn("Metode Bayar");
    model.addColumn("Waktu Bayar");

    try {

        Connection conn =
                koneksi.getConnection();

        String sql =
                "SELECT pembayaran.id_pembayaran, "
                + "pembayaran.id_transaksi, "
                + "kendaraan.no_polisi, "
                + "pembayaran.total_tagihan, "
                + "pembayaran.metode_bayar, "
                + "pembayaran.waktu_bayar "
                + "FROM pembayaran "
                + "JOIN transaksi ON pembayaran.id_transaksi = transaksi.id_transaksi "
                + "JOIN kendaraan ON transaksi.id_kendaraan = kendaraan.id_kendaraan "
                + "ORDER BY pembayaran.id_pembayaran DESC";

        PreparedStatement pst =
                conn.prepareStatement(sql);

        ResultSet rs =
                pst.executeQuery();

        while (rs.next()) {

            model.addRow(new Object[]{
                rs.getString("id_pembayaran"),
                rs.getString("id_transaksi"),
                rs.getString("no_polisi"),
                rs.getString("total_tagihan"),
                rs.getString("metode_bayar"),
                rs.getString("waktu_bayar")
            });
        }

        jTable1.setModel(model);

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                null,
                "Data pembayaran gagal ditampilkan : "
                + e.getMessage());
    }
}
    
    
    public void cariData() {

    DefaultTableModel model =
            new DefaultTableModel();

    model.addColumn("ID Pembayaran");
    model.addColumn("ID Transaksi");
    model.addColumn("No Polisi");
    model.addColumn("Total Tagihan");
    model.addColumn("Metode Bayar");
    model.addColumn("Waktu Bayar");

    try {

        String keyword =
                jTextField3.getText();

        Connection conn =
                koneksi.getConnection();

        String sql =
                "SELECT pembayaran.id_pembayaran, "
                + "pembayaran.id_transaksi, "
                + "kendaraan.no_polisi, "
                + "pembayaran.total_tagihan, "
                + "pembayaran.metode_bayar, "
                + "pembayaran.waktu_bayar "
                + "FROM pembayaran "
                + "JOIN transaksi ON pembayaran.id_transaksi = transaksi.id_transaksi "
                + "JOIN kendaraan ON transaksi.id_kendaraan = kendaraan.id_kendaraan "
                + "WHERE pembayaran.id_pembayaran LIKE ? "
                + "OR pembayaran.id_transaksi LIKE ? "
                + "OR kendaraan.no_polisi LIKE ? "
                + "OR pembayaran.total_tagihan LIKE ? "
                + "OR pembayaran.metode_bayar LIKE ? "
                + "OR pembayaran.waktu_bayar LIKE ? "
                + "ORDER BY pembayaran.id_pembayaran DESC";

        PreparedStatement pst =
                conn.prepareStatement(sql);

        pst.setString(1, "%" + keyword + "%");
        pst.setString(2, "%" + keyword + "%");
        pst.setString(3, "%" + keyword + "%");
        pst.setString(4, "%" + keyword + "%");
        pst.setString(5, "%" + keyword + "%");
        pst.setString(6, "%" + keyword + "%");

        ResultSet rs =
                pst.executeQuery();

        while (rs.next()) {

            model.addRow(new Object[]{
                rs.getString("id_pembayaran"),
                rs.getString("id_transaksi"),
                rs.getString("no_polisi"),
                rs.getString("total_tagihan"),
                rs.getString("metode_bayar"),
                rs.getString("waktu_bayar")
            });
        }

        jTable1.setModel(model);

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                null,
                "Data pembayaran gagal dicari : "
                + e.getMessage());
    }
}
    public void resetForm() {

    idTransaksi = "";

    if (jComboBox1.getItemCount() > 0) {
        jComboBox1.setSelectedIndex(0);
    }

    if (jComboBox2.getItemCount() > 0) {
        jComboBox2.setSelectedIndex(0);
    }

    jTextField1.setText("");
    tampilWaktu();
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
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Pembayaran");

        jButton1.setText("Simpan");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Cari");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Reset");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel2.setText("Transaksi");

        jLabel3.setText("Total Tagihan");

        jLabel4.setText("Metode Bayar");

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
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

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "QRIS", "E-Money", "Flazz", "TapCash", "Brizzi" }));

        jLabel5.setText("Waktu Bayar");

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jButton4.setText("Print");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Hapus");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Refresh");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Kembali");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(123, 123, 123)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(46, 46, 46)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(49, 49, 49)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3)))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(376, 376, 376)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(193, 193, 193)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 498, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 209, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton7)
                .addGap(162, 162, 162))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jButton3))
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2)
                    .addComponent(jButton5)
                    .addComponent(jButton4)
                    .addComponent(jButton6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton7)
                .addGap(161, 161, 161))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 603, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
try {

    if (jComboBox1.getSelectedItem() == null) {
        return;
    }

    String data =
            jComboBox1.getSelectedItem().toString();

    idTransaksi =
            data.split(" - ")[0];

    Connection conn =
            koneksi.getConnection();

    String sql =
            "SELECT total_tarif FROM transaksi WHERE id_transaksi=?";

    PreparedStatement pst =
            conn.prepareStatement(sql);

    pst.setString(1, idTransaksi);

    ResultSet rs =
            pst.executeQuery();

    if (rs.next()) {

        jTextField1.setText(
                rs.getString("total_tarif"));
    }

} catch (Exception e) {

    JOptionPane.showMessageDialog(
            null,
            "Gagal menampilkan total tagihan : "
            + e.getMessage());
}        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
                                         

    try {

        if (idTransaksi == null || idTransaksi.equals("")) {

            JOptionPane.showMessageDialog(
                    null,
                    "Pilih transaksi terlebih dahulu");

            return;
        }

        if (jTextField1.getText().trim().equals("")
                || jTextField2.getText().trim().equals("")) {

            JOptionPane.showMessageDialog(
                    null,
                    "Data pembayaran belum lengkap");

            return;
        }

        Connection conn =
                koneksi.getConnection();

        String sql =
                "INSERT INTO pembayaran "
                + "(id_transaksi, total_tagihan, metode_bayar, waktu_bayar) "
                + "VALUES (?, ?, ?, ?)";

        PreparedStatement pst =
                conn.prepareStatement(sql);

        pst.setString(1, idTransaksi);
        pst.setString(2, jTextField1.getText());
        pst.setString(3, jComboBox2.getSelectedItem().toString());
        pst.setString(4, jTextField2.getText());

        pst.executeUpdate();

        JOptionPane.showMessageDialog(
                null,
                "Pembayaran berhasil disimpan");

        tampilData();
        loadTransaksi();
        resetForm();

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                null,
                "Pembayaran gagal disimpan : "
                + e.getMessage());
    }
       // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
cariData();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
resetForm();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
try {
    String html = "<html>"
            + "<head>"
            + "<title>Data Pembayaran</title>"
            + "<style>"
            + "table{border-collapse:collapse;width:100%;}"
            + "th,td{border:1px solid black;padding:8px;text-align:left;}"
            + "th{background:#cccccc;}"
            + "h2{text-align:center;}"
            + "</style>"
            + "</head>"
            + "<body>";

    html += "<h2>LAPORAN DATA PEMBAYARAN</h2>";
    html += "<table>";
    html += "<tr>"
            + "<th>ID Pembayaran</th>"
            + "<th>ID Transaksi</th>"
            + "<th>No Polisi</th>"
            + "<th>Total Tagihan</th>"
            + "<th>Metode Bayar</th>"
            + "<th>Waktu Bayar</th>"
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

    File file = new File("laporan_pembayaran.html");

    FileWriter fw = new FileWriter(file);
    fw.write(html);
    fw.close();

    Desktop.getDesktop().browse(file.toURI());

} catch (Exception e) {

    JOptionPane.showMessageDialog(
            null,
            "Gagal membuat laporan : "
            + e.getMessage());
}        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
                                   

    int baris = jTable1.getSelectedRow();

    if (baris != -1) {

        idPembayaran = jTable1.getValueAt(baris, 0).toString();

    }
       // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
                                       
    try {

        if (idPembayaran == null || idPembayaran.equals("")) {

            JOptionPane.showMessageDialog(
                    null,
                    "Pilih data pembayaran yang akan dihapus terlebih dahulu");

            return;
        }

        int konfirmasi =
                JOptionPane.showConfirmDialog(
                        null,
                        "Yakin ingin menghapus data pembayaran ini?",
                        "Konfirmasi Hapus",
                        JOptionPane.YES_NO_OPTION);

        if (konfirmasi == JOptionPane.YES_OPTION) {

            Connection conn =
                    koneksi.getConnection();

            String sql =
                    "DELETE FROM pembayaran WHERE id_pembayaran=?";

            PreparedStatement pst =
                    conn.prepareStatement(sql);

            pst.setString(1, idPembayaran);

            pst.executeUpdate();

            JOptionPane.showMessageDialog(
                    null,
                    "Data pembayaran berhasil dihapus");

            tampilData();
            loadTransaksi();
            resetForm();

            idPembayaran = "";
        }

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                null,
                "Data pembayaran gagal dihapus : "
                + e.getMessage());
    }       // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
helper.Navigasi.kembaliKeDashboard(this);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

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
            java.util.logging.Logger.getLogger(Pembayaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pembayaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pembayaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pembayaran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pembayaran().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
