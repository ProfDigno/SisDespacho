/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FORMULARIO.VISTA;

import BASEDATO.EvenConexion;
import BASEDATO.LOCAL.ConnPostgres;
import Evento.Color.cla_color_palete;
import Evento.JLabel.EvenJLabel;
import Evento.JTextField.EvenJTextField;
import Evento.Jframe.EvenJFRAME;
import Evento.Jtable.EvenJtable;
import FORMULARIO.BO.*;
import FORMULARIO.DAO.*;
import FORMULARIO.ENTIDAD.*;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Digno
 */
public class FrmPre_item_liquidacion_final extends javax.swing.JInternalFrame {

    private EvenJFRAME evetbl = new EvenJFRAME();
    private EvenJtable eveJtab = new EvenJtable();
    private EvenJLabel evelbl = new EvenJLabel();
    private EvenConexion eveconn = new EvenConexion();
    private pre_item_liquidacion_final ENTpilf = new pre_item_liquidacion_final();
    private BO_pre_item_liquidacion_final BOpilf = new BO_pre_item_liquidacion_final();
    private DAO_pre_item_liquidacion_final DAOpilf = new DAO_pre_item_liquidacion_final();
    private comprobante_liquidacion ENTcl = new comprobante_liquidacion();
    private BO_comprobante_liquidacion BOcl = new BO_comprobante_liquidacion();
    private DAO_comprobante_liquidacion DAOcl = new DAO_comprobante_liquidacion();
    private EvenJTextField evejtf = new EvenJTextField();
    Connection conn = ConnPostgres.getConnPosgres();
    cla_color_palete clacolor = new cla_color_palete();
    private dao_usuario DAOusu = new dao_usuario();
    private DefaultTableModel model_item_liquidacion = new DefaultTableModel();
    private int orden_item;
    private int fk_idcomprobante_liquidacion;

    private void abrir_formulario() {
        this.setTitle("MENU PRE ITEM LIQUIDACION");
        evetbl.centrar_formulario_internalframa(this);
        reestableser();
        DAOpilf.actualizar_tabla_pre_item_liquidacion_final(conn, tblitem_liquidacion_final);
        color_formulario();
    }

    private void color_formulario() {
        panel_tabla.setBackground(clacolor.getColor_tabla());
        panel_insertar.setBackground(clacolor.getColor_insertar_primario());
    }

    private boolean validar_guardar() {
        if (evejtf.getBoo_JTextField_vacio(txtorden, "DEBE CARGAR UN ORDEN")) {
            return false;
        }
        if (fk_idcomprobante_liquidacion == 0) {
            JOptionPane.showMessageDialog(null, "NO SE ENCONTRO NINGUN COMPROBANTE CARGADO", "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private void boton_guardar() {
        if (validar_guardar()) {
            ENTpilf.setC2orden(Integer.parseInt(txtorden.getText()));
            ENTpilf.setC3fk_idcomprobante_liquidacion(fk_idcomprobante_liquidacion);
            BOpilf.insertar_pre_item_liquidacion_final(ENTpilf, tblitem_liquidacion_final);
            reestableser();
        }
    }

    private void boton_editar() {
        if (validar_guardar()) {
            ENTpilf.setC1idpre_item_liquidacion_final(Integer.parseInt(txtid.getText()));
            ENTpilf.setC3fk_idcomprobante_liquidacion(fk_idcomprobante_liquidacion);
            ENTpilf.setC2orden(Integer.parseInt(txtorden.getText()));
            ENTpilf.setC4eliminado(false);
            BOpilf.update_pre_item_liquidacion_final(ENTpilf, tblitem_liquidacion_final, true);
        }
    }

    private void boton_eliminado() {
        if (validar_guardar()) {
            ENTpilf.setC1idpre_item_liquidacion_final(Integer.parseInt(txtid.getText()));
            ENTpilf.setC4eliminado(true);
            BOpilf.update_pre_item_liquidacion_final(ENTpilf, tblitem_liquidacion_final, false);
            reestableser();
        }
    }

    private void seleccionar_tabla() {
        int idproducto = eveJtab.getInt_select_id(tblitem_liquidacion_final);
        DAOpilf.cargar_pre_item_liquidacion_final(conn, ENTpilf, idproducto);
        txtid.setText(String.valueOf(ENTpilf.getC1idpre_item_liquidacion_final()));
        txtorden.setText(String.valueOf(ENTpilf.getC2orden()));
        fk_idcomprobante_liquidacion = ENTpilf.getC3fk_idcomprobante_liquidacion();
        DAOcl.cargar_comprobante_liquidacion(conn, ENTcl, fk_idcomprobante_liquidacion);
        txtbucar_comprobante.setText(ENTcl.getC2descripcion());
        btnguardar.setEnabled(false);
        btneditar.setEnabled(true);
        btndeletar.setEnabled(true);
    }

    private void reestableser() {
        txtid.setText(null);
        txtorden.setText(null);
        txtbucar_comprobante.setText(null);
        btnguardar.setEnabled(true);
        btneditar.setEnabled(false);
        btndeletar.setEnabled(false);
        txtorden.grabFocus();
    }

    private void boton_nuevo() {
        reestableser();
    }

    void seleccionar_cargar_comprobante() {
        fk_idcomprobante_liquidacion = eveconn.getInt_Solo_seleccionar_JLista(conn, jList_comprobante, "comprobante_liquidacion", "descripcion", "idcomprobante_liquidacion", true);
        DAOcl.cargar_comprobante_liquidacion(conn, ENTcl, fk_idcomprobante_liquidacion);
        txtbucar_comprobante.setText(ENTcl.getC2descripcion());
        txtorden.grabFocus();
    }

    public FrmPre_item_liquidacion_final() {
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        panel_insertar = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtid = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtorden = new javax.swing.JTextField();
        btnnuevo = new javax.swing.JButton();
        btnguardar = new javax.swing.JButton();
        btneditar = new javax.swing.JButton();
        btndeletar = new javax.swing.JButton();
        jList_comprobante = new javax.swing.JList<>();
        lblcomprobante = new javax.swing.JLabel();
        txtbucar_comprobante = new javax.swing.JTextField();
        panel_tabla = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblitem_liquidacion_final = new javax.swing.JTable();

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
        jLabel2.setText("ORDEN:");

        txtorden.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtorden.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtordenKeyPressed(evt);
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

        jList_comprobante.setBackground(new java.awt.Color(204, 204, 255));
        jList_comprobante.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jList_comprobante.setSelectionBackground(new java.awt.Color(255, 51, 51));
        jList_comprobante.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jList_comprobanteMouseReleased(evt);
            }
        });
        jList_comprobante.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jList_comprobanteKeyPressed(evt);
            }
        });

        lblcomprobante.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblcomprobante.setText("COMPROBANTE:");
        lblcomprobante.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblcomprobanteMouseMoved(evt);
            }
        });
        lblcomprobante.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblcomprobanteMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblcomprobanteMouseExited(evt);
            }
        });

        txtbucar_comprobante.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtbucar_comprobante.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtbucar_comprobanteKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbucar_comprobanteKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout panel_insertarLayout = new javax.swing.GroupLayout(panel_insertar);
        panel_insertar.setLayout(panel_insertarLayout);
        panel_insertarLayout.setHorizontalGroup(
            panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_insertarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_insertarLayout.createSequentialGroup()
                        .addGroup(panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jList_comprobante, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panel_insertarLayout.createSequentialGroup()
                            .addComponent(lblcomprobante)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtbucar_comprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panel_insertarLayout.createSequentialGroup()
                        .addGroup(panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtorden, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panel_insertarLayout.createSequentialGroup()
                                .addComponent(btnnuevo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnguardar)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btneditar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btndeletar)))
                .addContainerGap(77, Short.MAX_VALUE))
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
                    .addComponent(lblcomprobante)
                    .addComponent(txtbucar_comprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jList_comprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtorden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnnuevo)
                    .addComponent(btnguardar)
                    .addComponent(btneditar)
                    .addComponent(btndeletar))
                .addContainerGap())
        );

        panel_tabla.setBorder(javax.swing.BorderFactory.createTitledBorder("ITEM LIQUIDACION"));

        tblitem_liquidacion_final.setModel(new javax.swing.table.DefaultTableModel(
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
        tblitem_liquidacion_final.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblitem_liquidacion_finalMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblitem_liquidacion_final);

        javax.swing.GroupLayout panel_tablaLayout = new javax.swing.GroupLayout(panel_tabla);
        panel_tabla.setLayout(panel_tablaLayout);
        panel_tablaLayout.setHorizontalGroup(
            panel_tablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 676, Short.MAX_VALUE)
        );
        panel_tablaLayout.setVerticalGroup(
            panel_tablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_tablaLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 83, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(panel_insertar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_tabla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_insertar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panel_tabla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("CREAR MENU", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
        // TODO add your handling code here:
        boton_guardar();
    }//GEN-LAST:event_btnguardarActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        // TODO add your handling code here:
        DAOpilf.ancho_tabla_pre_item_liquidacion_final(tblitem_liquidacion_final);
    }//GEN-LAST:event_formInternalFrameOpened

    private void tblitem_liquidacion_finalMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblitem_liquidacion_finalMouseReleased
        // TODO add your handling code here:
        seleccionar_tabla();
    }//GEN-LAST:event_tblitem_liquidacion_finalMouseReleased

    private void btneditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditarActionPerformed
        // TODO add your handling code here:
        boton_editar();
    }//GEN-LAST:event_btneditarActionPerformed

    private void btnnuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnuevoActionPerformed
        // TODO add your handling code here:
        boton_nuevo();
    }//GEN-LAST:event_btnnuevoActionPerformed

    private void txtordenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtordenKeyPressed
        // TODO add your handling code here:
//        evejtf.saltar_campo_enter(evt, txtnombre, txtprecio_venta);
//if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
//            boton_cargar_item_liquidacion_final();
//        }
    }//GEN-LAST:event_txtordenKeyPressed

    private void jList_comprobanteMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList_comprobanteMouseReleased
        // TODO add your handling code here:
        seleccionar_cargar_comprobante();
    }//GEN-LAST:event_jList_comprobanteMouseReleased

    private void jList_comprobanteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jList_comprobanteKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            seleccionar_cargar_comprobante();
        }
    }//GEN-LAST:event_jList_comprobanteKeyPressed

    private void lblcomprobanteMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblcomprobanteMouseMoved
        // TODO add your handling code here:
        evelbl.evento_MouseMoved(lblcomprobante);
    }//GEN-LAST:event_lblcomprobanteMouseMoved

    private void lblcomprobanteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblcomprobanteMouseClicked
        // TODO add your handling code here:
        evetbl.abrir_TablaJinternal(new FrmComprobante_liquidacion());
    }//GEN-LAST:event_lblcomprobanteMouseClicked

    private void lblcomprobanteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblcomprobanteMouseExited
        // TODO add your handling code here:
        evelbl.evento_MouseExited(lblcomprobante);
    }//GEN-LAST:event_lblcomprobanteMouseExited

    private void txtbucar_comprobanteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbucar_comprobanteKeyPressed
        // TODO add your handling code here:
        if (txtbucar_comprobante.getText().trim().length() > 1) {
            evejtf.seleccionar_lista(evt, jList_comprobante);
        }
    }//GEN-LAST:event_txtbucar_comprobanteKeyPressed

    private void txtbucar_comprobanteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbucar_comprobanteKeyReleased
        // TODO add your handling code here:
        if (txtbucar_comprobante.getText().trim().length() > 1) {
            eveconn.buscar_cargar_condicion_Jlista(conn, txtbucar_comprobante, jList_comprobante, "comprobante_liquidacion", "descripcion", "descripcion", "");
        }
    }//GEN-LAST:event_txtbucar_comprobanteKeyReleased

    private void btndeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeletarActionPerformed
        // TODO add your handling code here:
//        if(DAOusu.getboo_habilitar_boton_eliminar(conn)){
            boton_eliminado();
//        }
    }//GEN-LAST:event_btndeletarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btndeletar;
    private javax.swing.JButton btneditar;
    private javax.swing.JButton btnguardar;
    private javax.swing.JButton btnnuevo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JList<String> jList_comprobante;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblcomprobante;
    private javax.swing.JPanel panel_insertar;
    private javax.swing.JPanel panel_tabla;
    private javax.swing.JTable tblitem_liquidacion_final;
    private javax.swing.JTextField txtbucar_comprobante;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtorden;
    // End of variables declaration//GEN-END:variables
}
