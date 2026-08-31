/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;
import dao.UsuarioDAO;
import modelo.Usuario;
import javax.swing.JOptionPane;
import controlador.Sesion;

/**
 *
 * @author chumuslavia
 */
public class FrmModificarUsuario extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FrmModificarUsuario.class.getName());
    private FrmUsuarios ventanaUsuarios;
private Usuario usuario;
private UsuarioDAO usuarioDAO;

    /**
     * Creates new form FrmModificarUsuario
     */
    public FrmModificarUsuario(FrmUsuarios ventanaUsuarios, Usuario usuario) {
    initComponents();
    setLocationRelativeTo(null);

    this.ventanaUsuarios = ventanaUsuarios;
    this.usuario = usuario;
    this.usuarioDAO = new UsuarioDAO();

    configurarPermisos();
    cargarDatos();
}
    private void configurarPermisos() {

    Usuario usuarioActual = Sesion.getUsuarioActual();

    if (usuarioActual == null) {

        JOptionPane.showMessageDialog(
                this,
                "No hay una sesión activa.",
                "Acceso denegado",
                JOptionPane.ERROR_MESSAGE
        );

        jButton1.setEnabled(false);
        cmbRol.setEnabled(false);
        cmbEstado.setEnabled(false);

        return;
    }

    String rolActual = usuarioActual.getRol();
    String rolSeleccionado = usuario.getRol();

    // =====================================================
    // OWNER
    // Puede modificar Administradores y usuarios normales.
    // NO puede modificar otro Owner.
    // =====================================================

    if ("Owner".equalsIgnoreCase(rolActual)) {

        if ("Owner".equalsIgnoreCase(rolSeleccionado)) {

            JOptionPane.showMessageDialog(
                    this,
                    "No se puede modificar un usuario Owner.",
                    "Acceso denegado",
                    JOptionPane.WARNING_MESSAGE
            );

            jButton1.setEnabled(false);
            cmbRol.setEnabled(false);
            cmbEstado.setEnabled(false);

            return;
        }

        // El Owner puede cambiar el rol entre los permitidos
        cmbRol.removeAllItems();
        cmbRol.addItem("Administrador");
        cmbRol.addItem("Recepción");
        cmbRol.addItem("Médico");
        cmbRol.addItem("Laboratorio");

        return;
    }


    // =====================================================
    // ADMINISTRADOR
    // Puede modificar únicamente:
    // Recepción
    // Médico
    // Laboratorio
    //
    // NO puede modificar:
    // Owner
    // Administrador
    // =====================================================

    if ("Administrador".equalsIgnoreCase(rolActual)) {

        if ("Owner".equalsIgnoreCase(rolSeleccionado)
                || "Administrador".equalsIgnoreCase(rolSeleccionado)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Un Administrador no tiene permisos para modificar a "
                    + rolSeleccionado + ".",
                    "Acceso denegado",
                    JOptionPane.WARNING_MESSAGE
            );

            jButton1.setEnabled(false);
            cmbRol.setEnabled(false);
            cmbEstado.setEnabled(false);

            return;
        }

        cmbRol.removeAllItems();
        cmbRol.addItem("Recepción");
        cmbRol.addItem("Médico");
        cmbRol.addItem("Laboratorio");

        return;
    }


    // =====================================================
    // RECEPCIÓN / MÉDICO / LABORATORIO
    // No pueden modificar usuarios
    // =====================================================

    if ("Recepción".equalsIgnoreCase(rolActual)
            || "Médico".equalsIgnoreCase(rolActual)
            || "Laboratorio".equalsIgnoreCase(rolActual)) {

        JOptionPane.showMessageDialog(
                this,
                "Su rol no tiene permisos para modificar usuarios.",
                "Acceso denegado",
                JOptionPane.WARNING_MESSAGE
        );

        jButton1.setEnabled(false);
        cmbRol.setEnabled(false);
        cmbEstado.setEnabled(false);

        return;
    }


    // =====================================================
    // ROL NO RECONOCIDO
    // =====================================================

    JOptionPane.showMessageDialog(
            this,
            "El rol de la sesión no tiene permisos definidos.",
            "Acceso denegado",
            JOptionPane.ERROR_MESSAGE
    );

    jButton1.setEnabled(false);
    cmbRol.setEnabled(false);
    cmbEstado.setEnabled(false);
}
    
    private void cargarDatos() {

    txtUsuario.setText(usuario.getUsuario());
    txtNombre.setText(usuario.getNombre());
    txtContrasena.setText(usuario.getPassword());
    txtConfirmarContrasena.setText(usuario.getPassword());

    cmbRol.setSelectedItem(usuario.getRol());
    cmbEstado.setSelectedItem(usuario.getEstado());
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelRound1 = new vista.PanelRound();
        panelRound2 = new vista.PanelRound();
        jLabel2 = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        lblNombre = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        lblContrasena = new javax.swing.JLabel();
        txtContrasena = new javax.swing.JPasswordField();
        lblConfirmarContrasena = new javax.swing.JLabel();
        txtConfirmarContrasena = new javax.swing.JPasswordField();
        lblRol = new javax.swing.JLabel();
        cmbRol = new javax.swing.JComboBox<>();
        lblEstado = new javax.swing.JLabel();
        cmbEstado = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        btcancelar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(new java.awt.Dimension(1000, 627));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelRound1.setRoundBottomLeft(20);
        panelRound1.setRoundBottomRight(20);
        panelRound1.setRoundTopLeft(20);
        panelRound1.setRoundTopRight(20);

        panelRound2.setBackground(new java.awt.Color(153, 153, 153));
        panelRound2.setRoundTopLeft(20);
        panelRound2.setRoundTopRight(20);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("MODIFICAR USUARIO");

        javax.swing.GroupLayout panelRound2Layout = new javax.swing.GroupLayout(panelRound2);
        panelRound2.setLayout(panelRound2Layout);
        panelRound2Layout.setHorizontalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addGap(199, 199, 199)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelRound2Layout.setVerticalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblUsuario.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblUsuario.setText("Usuario:");

        txtUsuario.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblNombre.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblNombre.setText("Nombre:");

        lblContrasena.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblContrasena.setText("Contraseña:");

        txtContrasena.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblConfirmarContrasena.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        lblConfirmarContrasena.setText("Confirmar contraseña:");

        txtConfirmarContrasena.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblRol.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblRol.setText("Rol:");

        cmbRol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrador", "Recepción", "Médico", "Laboratorio" }));

        lblEstado.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblEstado.setText("Estado:");

        cmbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activo", "Inactivo" }));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/yo100x100png.png"))); // NOI18N

        jButton1.setBackground(new java.awt.Color(153, 255, 153));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton1.setText("GUARDAR CAMBIOS");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(this::jButton1ActionPerformed);

        btcancelar.setBackground(new java.awt.Color(255, 0, 0));
        btcancelar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btcancelar.setText("CANCELAR ");
        btcancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btcancelar.addActionListener(this::btcancelarActionPerformed);

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRound2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(panelRound1Layout.createSequentialGroup()
                            .addComponent(lblNombre)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 505, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panelRound1Layout.createSequentialGroup()
                            .addComponent(lblUsuario)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtUsuario))
                        .addGroup(panelRound1Layout.createSequentialGroup()
                            .addComponent(lblContrasena)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtContrasena))
                        .addGroup(panelRound1Layout.createSequentialGroup()
                            .addComponent(lblConfirmarContrasena)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtConfirmarContrasena)))
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelRound1Layout.createSequentialGroup()
                                    .addComponent(lblRol, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(cmbRol, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(panelRound1Layout.createSequentialGroup()
                                    .addComponent(lblEstado)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(cmbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(panelRound1Layout.createSequentialGroup()
                                .addGap(68, 68, 68)
                                .addComponent(jButton1)))
                        .addGap(117, 117, 117)
                        .addComponent(btcancelar)))
                .addContainerGap(50, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addGap(14, 14, 14))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addComponent(panelRound2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUsuario)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblContrasena)
                    .addComponent(txtContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblConfirmarContrasena)
                    .addComponent(txtConfirmarContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRol)
                    .addComponent(cmbRol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEstado)
                    .addComponent(cmbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 108, Short.MAX_VALUE)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(btcancelar))
                .addGap(44, 44, 44)
                .addComponent(jLabel9)
                .addContainerGap())
        );

        getContentPane().add(panelRound1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 660, 600));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/iberoamericana1107-1000.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 630));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // =====================================================
    // 1. VERIFICAR SESIÓN
    // =====================================================

    Usuario usuarioActual = Sesion.getUsuarioActual();

    if (usuarioActual == null) {

        JOptionPane.showMessageDialog(
                this,
                "No hay una sesión activa.",
                "Acceso denegado",
                JOptionPane.ERROR_MESSAGE
        );

        return;
    }


    String rolActual = usuarioActual.getRol();
    String rolAnterior = usuario.getRol();
    String nuevoRol = cmbRol.getSelectedItem().toString();


    // =====================================================
    // 2. PROTEGER OWNER
    // =====================================================

    // Nunca se puede modificar un Owner desde este formulario
    if ("Owner".equalsIgnoreCase(rolAnterior)) {

        if (!"Owner".equalsIgnoreCase(rolActual)) {

            JOptionPane.showMessageDialog(
                    this,
                    "No tiene permisos para modificar al Owner.",
                    "Acceso denegado",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        // Incluso el Owner no debe modificar otro Owner
        JOptionPane.showMessageDialog(
                this,
                "El usuario Owner no puede ser modificado desde este formulario.",
                "Acceso denegado",
                JOptionPane.WARNING_MESSAGE
        );

        return;
    }


    // =====================================================
    // 3. OWNER
    // =====================================================

    if ("Owner".equalsIgnoreCase(rolActual)) {

        // El Owner puede modificar usuarios excepto Owner.

        if ("Owner".equalsIgnoreCase(nuevoRol)) {

            JOptionPane.showMessageDialog(
                    this,
                    "No se puede asignar el rol Owner desde este formulario.",
                    "Acceso denegado",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }
    }


    // =====================================================
    // 4. ADMINISTRADOR
    // =====================================================

    else if ("Administrador".equalsIgnoreCase(rolActual)) {

        // No puede modificar Administradores
        if ("Administrador".equalsIgnoreCase(rolAnterior)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Un Administrador no puede modificar a otro Administrador.",
                    "Acceso denegado",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        // No puede modificar Owner
        if ("Owner".equalsIgnoreCase(rolAnterior)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Un Administrador no puede modificar al Owner.",
                    "Acceso denegado",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        // Tampoco puede convertir un usuario normal en Administrador
        if ("Administrador".equalsIgnoreCase(nuevoRol)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Un Administrador no puede asignar el rol Administrador.",
                    "Acceso denegado",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        // Nunca puede asignar Owner
        if ("Owner".equalsIgnoreCase(nuevoRol)) {

            JOptionPane.showMessageDialog(
                    this,
                    "No tiene permisos para asignar el rol Owner.",
                    "Acceso denegado",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }
    }


    // =====================================================
    // 5. RECEPCIÓN / MÉDICO / LABORATORIO
    // =====================================================

    else if ("Recepción".equalsIgnoreCase(rolActual)
            || "Médico".equalsIgnoreCase(rolActual)
            || "Laboratorio".equalsIgnoreCase(rolActual)) {

        JOptionPane.showMessageDialog(
                this,
                "Su rol no tiene permisos para modificar usuarios.",
                "Acceso denegado",
                JOptionPane.ERROR_MESSAGE
        );

        return;
    }


    // =====================================================
    // 6. VALIDAR CAMPOS
    // =====================================================

    String usuarioNombre = txtUsuario.getText().trim();
    String nombre = txtNombre.getText().trim();
    String password = new String(txtContrasena.getPassword());
    String confirmarPassword =
            new String(txtConfirmarContrasena.getPassword());

    String estado = cmbEstado.getSelectedItem().toString();


    if (usuarioNombre.isEmpty()
            || nombre.isEmpty()
            || password.isEmpty()) {

        JOptionPane.showMessageDialog(
                this,
                "Complete todos los campos.",
                "Campos incompletos",
                JOptionPane.WARNING_MESSAGE
        );

        return;
    }


    // =====================================================
    // 7. VALIDAR CONTRASEÑA
    // =====================================================

    if (!password.equals(confirmarPassword)) {

        JOptionPane.showMessageDialog(
                this,
                "Las contraseñas no coinciden.",
                "Error",
                JOptionPane.ERROR_MESSAGE
        );

        return;
    }


    // =====================================================
    // 8. ACTUALIZAR OBJETO
    // =====================================================

    usuario.setUsuario(usuarioNombre);
    usuario.setNombre(nombre);
    usuario.setPassword(password);
    usuario.setRol(nuevoRol);
    usuario.setEstado(estado);


    // =====================================================
    // 9. GUARDAR
    // =====================================================

    if (usuarioDAO.modificar(usuario)) {

        usuarioDAO.cambiarEstado(
                usuario.getIdUsuario(),
                estado
        );

        JOptionPane.showMessageDialog(
                this,
                "Usuario modificado correctamente.",
                "Éxito",
                JOptionPane.INFORMATION_MESSAGE
        );

        ventanaUsuarios.actualizarUsuarios();
        ventanaUsuarios.setVisible(true);

        this.dispose();

    } else {

        JOptionPane.showMessageDialog(
                this,
                "No se pudo modificar el usuario.",
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
}

private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {

    ventanaUsuarios.setVisible(true);
    this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btcancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btcancelarActionPerformed
        // TODO add your handling code here:
        ventanaUsuarios.setVisible(true);
    this.dispose();
    }//GEN-LAST:event_btcancelarActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btcancelar;
    private javax.swing.JComboBox<String> cmbEstado;
    private javax.swing.JComboBox<String> cmbRol;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel lblConfirmarContrasena;
    private javax.swing.JLabel lblContrasena;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblRol;
    private javax.swing.JLabel lblUsuario;
    private vista.PanelRound panelRound1;
    private vista.PanelRound panelRound2;
    private javax.swing.JPasswordField txtConfirmarContrasena;
    private javax.swing.JPasswordField txtContrasena;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
