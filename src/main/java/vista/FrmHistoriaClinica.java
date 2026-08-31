package vista;

import dao.HistoriaClinicaDAO;
import modelo.HistoriaClinica;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class FrmHistoriaClinica extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger =
            java.util.logging.Logger.getLogger(FrmHistoriaClinica.class.getName());

    private modelo.Paciente paciente;
    private modelo.Usuario usuario;
    private HistoriaClinicaDAO historiaDAO;

    public FrmHistoriaClinica() {
        initComponents();
        setLocationRelativeTo(null);
    }

    // Constructor anterior: mantiene compatibilidad
    public FrmHistoriaClinica(modelo.Paciente paciente) {

        initComponents();
        setLocationRelativeTo(null);

        this.paciente = paciente;
        this.historiaDAO = new HistoriaClinicaDAO();

        cargarPaciente(paciente);
        cargarHistorias();
    }

    // NUEVO CONSTRUCTOR
    public FrmHistoriaClinica(modelo.Paciente paciente, modelo.Usuario usuario) {

        initComponents();
        setLocationRelativeTo(null);

        this.paciente = paciente;
        this.usuario = usuario;
        this.historiaDAO = new HistoriaClinicaDAO();

        cargarPaciente(paciente);
        cargarHistorias();
    }

    private void cargarPaciente(modelo.Paciente paciente) {

        if (paciente == null) {
            return;
        }

        jLabel4.setText(
                paciente.getNombres() + " " +
                paciente.getApellidos()
        );

        jLabel6.setText(
                String.valueOf(paciente.getIdPaciente())
        );

        jLabel7.setText(
                paciente.getDni()
        );

        if (paciente.getFechaNacimiento() != null) {

            jLabel10.setText(
                    paciente.getFechaNacimiento().toString()
            );
        }
    }

    private void cargarHistorias() {

        DefaultTableModel modelo =
                (DefaultTableModel) tblConsultas.getModel();

        modelo.setRowCount(0);

        if (paciente == null) {
            return;
        }

        int idPaciente = paciente.getIdPaciente();

        List<HistoriaClinica> historias =
                historiaDAO.listarPorPaciente(idPaciente);

        for (HistoriaClinica historia : historias) {

            modelo.addRow(new Object[]{
                historia.getFechaRegistro(),
                historia.getNombreUsuario(),
                historia.getDiagnostico(),
                historia.getTratamiento()
            });
        }
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel8 = new javax.swing.JLabel();
        panelRound1 = new vista.PanelRound();
        panelRound2 = new vista.PanelRound();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        panelRound3 = new vista.PanelRound();
        jLabel12 = new javax.swing.JLabel();
        btnNuevaConsulta = new javax.swing.JButton();
        btnModificarConsulta = new javax.swing.JButton();
        btnEliminarConsulta = new javax.swing.JButton();
        btnVolver = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblConsultas = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        jLabel8.setText("jLabel8");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(new java.awt.Dimension(1000, 627));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelRound1.setRoundBottomLeft(20);
        panelRound1.setRoundBottomRight(20);
        panelRound1.setRoundTopLeft(20);
        panelRound1.setRoundTopRight(20);
        panelRound1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelRound2.setBackground(new java.awt.Color(153, 153, 153));
        panelRound2.setRoundTopLeft(20);
        panelRound2.setRoundTopRight(20);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("HISTORIA CLÍNICA");

        javax.swing.GroupLayout panelRound2Layout = new javax.swing.GroupLayout(panelRound2);
        panelRound2.setLayout(panelRound2Layout);
        panelRound2Layout.setHorizontalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addGap(371, 371, 371)
                .addComponent(jLabel2)
                .addContainerGap(385, Short.MAX_VALUE))
        );
        panelRound2Layout.setVerticalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelRound1.add(panelRound2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Paciente:");
        panelRound1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 71, -1, -1));
        panelRound1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(108, 71, 510, 20));

        jLabel5.setText("ID:");
        panelRound1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 116, 62, -1));
        panelRound1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 120, 269, 16));
        panelRound1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 70, 140, 20));

        jLabel9.setText("DNI :");
        panelRound1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 70, 31, -1));
        panelRound1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 100, 141, 20));

        jLabel11.setText("Fecha nacimiento:");
        panelRound1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 100, -1, -1));

        panelRound3.setBackground(new java.awt.Color(153, 153, 153));
        panelRound3.setRoundTopLeft(20);
        panelRound3.setRoundTopRight(20);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setText("CONSULTAS MÉDICAS");

        javax.swing.GroupLayout panelRound3Layout = new javax.swing.GroupLayout(panelRound3);
        panelRound3.setLayout(panelRound3Layout);
        panelRound3Layout.setHorizontalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound3Layout.createSequentialGroup()
                .addGap(394, 394, 394)
                .addComponent(jLabel12)
                .addContainerGap(424, Short.MAX_VALUE))
        );
        panelRound3Layout.setVerticalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelRound1.add(panelRound3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 970, 30));

        btnNuevaConsulta.setBackground(new java.awt.Color(153, 255, 153));
        btnNuevaConsulta.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNuevaConsulta.setText("NUEVA CONSULTA");
        btnNuevaConsulta.addActionListener(this::btnNuevaConsultaActionPerformed);
        panelRound1.add(btnNuevaConsulta, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 560, -1, -1));

        btnModificarConsulta.setBackground(new java.awt.Color(255, 255, 102));
        btnModificarConsulta.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnModificarConsulta.setText("VER / MODIFICAR");
        panelRound1.add(btnModificarConsulta, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 560, -1, -1));

        btnEliminarConsulta.setBackground(new java.awt.Color(204, 0, 0));
        btnEliminarConsulta.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEliminarConsulta.setText("ELIMINAR");
        panelRound1.add(btnEliminarConsulta, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 560, -1, -1));

        btnVolver.setBackground(new java.awt.Color(255, 102, 102));
        btnVolver.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnVolver.setText("VOLVER");
        btnVolver.addActionListener(this::btnVolverActionPerformed);
        panelRound1.add(btnVolver, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 560, -1, -1));

        tblConsultas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha", "Médico", "Diagnóstico", "tratamiento"
            }
        ));
        jScrollPane2.setViewportView(tblConsultas);
        if (tblConsultas.getColumnModel().getColumnCount() > 0) {
            tblConsultas.getColumnModel().getColumn(0).setPreferredWidth(15);
            tblConsultas.getColumnModel().getColumn(1).setPreferredWidth(150);
            tblConsultas.getColumnModel().getColumn(2).setPreferredWidth(250);
            tblConsultas.getColumnModel().getColumn(3).setPreferredWidth(250);
        }

        panelRound1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 950, 360));

        getContentPane().add(panelRound1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 970, 610));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iberoamericana1107-1000.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        // TODO add your handling code here:
        FrmMenuPrincipal menu = new FrmMenuPrincipal();
    menu.setVisible(true);
    this.dispose();
    }//GEN-LAST:event_btnVolverActionPerformed

    private void btnNuevaConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevaConsultaActionPerformed
        // TODO add your handling code here:
        FrmNuevaConsulta nuevaConsulta =
            new FrmNuevaConsulta(paciente, usuario);

    nuevaConsulta.setVisible(true);
    this.dispose();
    }//GEN-LAST:event_btnNuevaConsultaActionPerformed

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new FrmHistoriaClinica().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminarConsulta;
    private javax.swing.JButton btnModificarConsulta;
    private javax.swing.JButton btnNuevaConsulta;
    private javax.swing.JButton btnVolver;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private vista.PanelRound panelRound1;
    private vista.PanelRound panelRound2;
    private vista.PanelRound panelRound3;
    private javax.swing.JTable tblConsultas;
    // End of variables declaration//GEN-END:variables
}
