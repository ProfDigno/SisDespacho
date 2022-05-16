/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FORMULARIO.VISTA;

import BASEDATO.LOCAL.ConnPostgres;
import Evento.Color.cla_color_palete;
import Evento.JTextField.EvenJTextField;
import Evento.Jframe.EvenJFRAME;
import Evento.Jtable.EvenJtable;
import FORMULARIO.BO.*;
import FORMULARIO.DAO.*;
import FORMULARIO.ENTIDAD.*;
import java.sql.Connection;

/**
 *
 * @author Digno
 */
public class FrmComprobante_liquidacion extends javax.swing.JInternalFrame {

    EvenJFRAME evetbl = new EvenJFRAME();
    EvenJtable eveJtab = new EvenJtable();
    private comprobante_liquidacion ENTcl = new comprobante_liquidacion();
    private BO_comprobante_liquidacion BOcl = new BO_comprobante_liquidacion();
    private DAO_comprobante_liquidacion DAOcl = new DAO_comprobante_liquidacion();
    private EvenJTextField evejtf = new EvenJTextField();
    Connection conn = ConnPostgres.getConnPosgres();
    cla_color_palete clacolor = new cla_color_palete();
    private dao_usuario DAOusu=new dao_usuario();
    private String tipo_SIN_IVA="SIN_IVA";
    private String tipo_SOLO_IVA="SOLO_IVA";
    private String tipo_SIN_Y_SOLO_IVA="SIN_Y_SOLO_IVA";

    private void abrir_formulario() {
        this.setTitle("COMPROBANTE LIQUIDACION");
        evetbl.centrar_formulario_internalframa(this);
        reestableser();
        DAOcl.actualizar_tabla_comprobante_liquidacion(conn, tbltabla);
        color_formulario();
    }

    private void color_formulario() {
        panel_tabla.setBackground(clacolor.getColor_tabla());
        panel_insertar.setBackground(clacolor.getColor_insertar_primario());
    }

    private boolean validar_guardar() {
        if (evejtf.getBoo_JTextField_vacio(txtdescripcion, "DEBE CARGAR UN NOMBRE")) {
            return false;
        }
        return true;
    }

    double getDoubleIva() {
        double iva = 0;
        if (jRiva_5.isSelected()) {
            iva = 5;
        }
        if (jRiva_10.isSelected()) {
            iva = 10;
        }
        if (jRiva_exenta.isSelected()) {
            iva = 0;
        }
        return iva;
    }
    String getStringTipoIva() {
        String tipo = "error";
        if (jRtipo_monto_sin_iva.isSelected()) {
            tipo = tipo_SIN_IVA;
        }
        if (jRtipo_monto_solo_iva.isSelected()) {
            tipo = tipo_SOLO_IVA;
        }
        if (jRtipo_siniva_soloiva.isSelected()) {
            tipo = tipo_SIN_Y_SOLO_IVA;
        }
        return tipo;
    }
    void cargar_dato() {
        ENTcl.setC2descripcion(txtdescripcion.getText());
        ENTcl.setC3por_iva(getDoubleIva());
        ENTcl.setC4tipo_iva(getStringTipoIva());
        ENTcl.setC5nro_despacho(jCnro_despacho.isSelected());
        ENTcl.setC6eliminado(false);
    }

    private void boton_guardar() {
        if (validar_guardar()) {
            cargar_dato();
            BOcl.insertar_comprobante_liquidacion(ENTcl, tbltabla);
            reestableser();
        }
    }

    private void boton_editar() {
        if (validar_guardar()) {
            ENTcl.setC1idcomprobante_liquidacion(Integer.parseInt(txtid.getText()));
            cargar_dato();
            ENTcl.setC6eliminado(false);
            BOcl.update_comprobante_liquidacion(ENTcl, tbltabla,true);
        }
    }
    private void boton_eliminar() {
        if (validar_guardar()) {
            ENTcl.setC1idcomprobante_liquidacion(Integer.parseInt(txtid.getText()));
            ENTcl.setC6eliminado(true);
            BOcl.update_comprobante_liquidacion(ENTcl, tbltabla,false);
            reestableser();
        }
    }
    private void seleccionar_tabla() {
        int idproducto = eveJtab.getInt_select_id(tbltabla);
        DAOcl.cargar_comprobante_liquidacion(conn, ENTcl, idproducto);
        txtid.setText(String.valueOf(ENTcl.getC1idcomprobante_liquidacion()));
        txtdescripcion.setText(ENTcl.getC2descripcion());
        if(ENTcl.getC3por_iva()==5){
            jRiva_5.setSelected(true);
        }
        if(ENTcl.getC3por_iva()==10){
            jRiva_10.setSelected(true);
        }
        if(ENTcl.getC3por_iva()==0){
            jRiva_exenta.setSelected(true);
        }
        if(ENTcl.getC4tipo_iva().equals(tipo_SIN_IVA)){
            jRtipo_monto_sin_iva.setSelected(true);
        }
        if(ENTcl.getC4tipo_iva().equals(tipo_SOLO_IVA)){
            jRtipo_monto_solo_iva.setSelected(true);
        }
        if(ENTcl.getC4tipo_iva().equals(tipo_SIN_Y_SOLO_IVA)){
            jRtipo_siniva_soloiva.setSelected(true);
        }
        jCnro_despacho.setSelected(ENTcl.getC5nro_despacho());
        btnguardar.setEnabled(false);
        btneditar.setEnabled(true);
        btndeletar.setEnabled(true);
    }

    private void reestableser() {
        txtid.setText(null);
        txtdescripcion.setText(null);
        jRiva_10.setSelected(true);
        jRtipo_siniva_soloiva.setSelected(false);
        jCnro_despacho.setSelected(false);
        btnguardar.setEnabled(true);
        btneditar.setEnabled(false);
        btndeletar.setEnabled(false);
        txtdescripcion.grabFocus();
    }

    private void boton_nuevo() {
        reestableser();
    }

    public FrmComprobante_liquidacion() {
        initComponents();
        abrir_formulario();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gru_iva = new javax.swing.ButtonGroup();
        gru_tiva = new javax.swing.ButtonGroup();
        panel_insertar = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtid = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtdescripcion = new javax.swing.JTextField();
        btnnuevo = new javax.swing.JButton();
        btnguardar = new javax.swing.JButton();
        btneditar = new javax.swing.JButton();
        btndeletar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jRiva_5 = new javax.swing.JRadioButton();
        jRiva_10 = new javax.swing.JRadioButton();
        jRiva_exenta = new javax.swing.JRadioButton();
        jCnro_despacho = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        jRtipo_monto_sin_iva = new javax.swing.JRadioButton();
        jRtipo_monto_solo_iva = new javax.swing.JRadioButton();
        jRtipo_siniva_soloiva = new javax.swing.JRadioButton();
        panel_tabla = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbltabla = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });

        panel_insertar.setBackground(new java.awt.Color(153, 204, 255));
        panel_insertar.setBorder(javax.swing.BorderFactory.createTitledBorder("CREAR DATO"));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("ID:");

        txtid.setEditable(false);
        txtid.setBackground(new java.awt.Color(204, 204, 204));
        txtid.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Descripcion:");

        txtdescripcion.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtdescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtdescripcionKeyPressed(evt);
            }
        });

        btnnuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/ABM/nuevo.png"))); // NOI18N
        btnnuevo.setText("NUEVO");
        btnnuevo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnnuevo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnnuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnuevoActionPerformed(evt);
            }
        });

        btnguardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/ABM/guardar.png"))); // NOI18N
        btnguardar.setText("GUARDAR");
        btnguardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnguardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarActionPerformed(evt);
            }
        });

        btneditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/ABM/modificar.png"))); // NOI18N
        btneditar.setText("EDITAR");
        btneditar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btneditar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btneditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditarActionPerformed(evt);
            }
        });

        btndeletar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/ABM/eliminar.png"))); // NOI18N
        btndeletar.setText("DELETAR");
        btndeletar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btndeletar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btndeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeletarActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("IVA"));

        gru_iva.add(jRiva_5);
        jRiva_5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jRiva_5.setText("IVA 5");

        gru_iva.add(jRiva_10);
        jRiva_10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jRiva_10.setText("IVA 10");

        gru_iva.add(jRiva_exenta);
        jRiva_exenta.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jRiva_exenta.setText("EXENTA");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jRiva_5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRiva_10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRiva_exenta)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jRiva_5)
                .addComponent(jRiva_10)
                .addComponent(jRiva_exenta))
        );

        jCnro_despacho.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jCnro_despacho.setText("NRO DESPACHO");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("TIPO IVA"));

        gru_tiva.add(jRtipo_monto_sin_iva);
        jRtipo_monto_sin_iva.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jRtipo_monto_sin_iva.setText("MONTO SIN IVA");

        gru_tiva.add(jRtipo_monto_solo_iva);
        jRtipo_monto_solo_iva.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jRtipo_monto_solo_iva.setText("MONTO SOLO IVA");

        gru_tiva.add(jRtipo_siniva_soloiva);
        jRtipo_siniva_soloiva.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jRtipo_siniva_soloiva.setSelected(true);
        jRtipo_siniva_soloiva.setText("SIN IVA Y SOLO IVA");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jRtipo_monto_sin_iva)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRtipo_monto_solo_iva)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRtipo_siniva_soloiva))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jRtipo_monto_sin_iva)
                .addComponent(jRtipo_monto_solo_iva)
                .addComponent(jRtipo_siniva_soloiva))
        );

        javax.swing.GroupLayout panel_insertarLayout = new javax.swing.GroupLayout(panel_insertar);
        panel_insertar.setLayout(panel_insertarLayout);
        panel_insertarLayout.setHorizontalGroup(
            panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_insertarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCnro_despacho)
                    .addGroup(panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(panel_insertarLayout.createSequentialGroup()
                            .addGroup(panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addComponent(jLabel2))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtdescripcion)))
                        .addGroup(panel_insertarLayout.createSequentialGroup()
                            .addComponent(btnnuevo)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnguardar)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btneditar)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btndeletar))
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel_insertarLayout.setVerticalGroup(
            panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_insertarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtdescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCnro_despacho)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnnuevo)
                    .addComponent(btnguardar)
                    .addComponent(btneditar)
                    .addComponent(btndeletar))
                .addContainerGap())
        );

        panel_tabla.setBackground(new java.awt.Color(51, 204, 255));
        panel_tabla.setBorder(javax.swing.BorderFactory.createTitledBorder("TABLA"));

        tbltabla.setModel(new javax.swing.table.DefaultTableModel(
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
        tbltabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tbltablaMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tbltabla);

        javax.swing.GroupLayout panel_tablaLayout = new javax.swing.GroupLayout(panel_tabla);
        panel_tabla.setLayout(panel_tablaLayout);
        panel_tablaLayout.setHorizontalGroup(
            panel_tablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
        );
        panel_tablaLayout.setVerticalGroup(
            panel_tablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_tablaLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel_insertar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_tabla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(panel_tabla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel_insertar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
        // TODO add your handling code here:
        boton_guardar();
    }//GEN-LAST:event_btnguardarActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        // TODO add your handling code here:
        DAOcl.ancho_tabla_comprobante_liquidacion(tbltabla);
    }//GEN-LAST:event_formInternalFrameOpened

    private void tbltablaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbltablaMouseReleased
        // TODO add your handling code here:
        seleccionar_tabla();
    }//GEN-LAST:event_tbltablaMouseReleased

    private void btneditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditarActionPerformed
        // TODO add your handling code here:
        boton_editar();
    }//GEN-LAST:event_btneditarActionPerformed

    private void btnnuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnuevoActionPerformed
        // TODO add your handling code here:
        boton_nuevo();
    }//GEN-LAST:event_btnnuevoActionPerformed

    private void txtdescripcionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdescripcionKeyPressed
        // TODO add your handling code here:
//        evejtf.saltar_campo_enter(evt, txtnombre, txtprecio_venta);
    }//GEN-LAST:event_txtdescripcionKeyPressed

    private void btndeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeletarActionPerformed
        // TODO add your handling code here:
        if(DAOusu.getboo_habilitar_boton_eliminar(conn)){
            boton_eliminar();
        }
    }//GEN-LAST:event_btndeletarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btndeletar;
    private javax.swing.JButton btneditar;
    private javax.swing.JButton btnguardar;
    private javax.swing.JButton btnnuevo;
    private javax.swing.ButtonGroup gru_iva;
    private javax.swing.ButtonGroup gru_tiva;
    private javax.swing.JCheckBox jCnro_despacho;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jRiva_10;
    private javax.swing.JRadioButton jRiva_5;
    private javax.swing.JRadioButton jRiva_exenta;
    private javax.swing.JRadioButton jRtipo_monto_sin_iva;
    private javax.swing.JRadioButton jRtipo_monto_solo_iva;
    private javax.swing.JRadioButton jRtipo_siniva_soloiva;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panel_insertar;
    private javax.swing.JPanel panel_tabla;
    private javax.swing.JTable tbltabla;
    private javax.swing.JTextField txtdescripcion;
    private javax.swing.JTextField txtid;
    // End of variables declaration//GEN-END:variables
}
