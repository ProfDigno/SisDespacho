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
import FILTRO.ClaAuxFiltroVenta;
import FORMULARIO.BO.*;
import FORMULARIO.DAO.*;
import FORMULARIO.ENTIDAD.*;
import java.sql.Connection;

/**
 *
 * @author Digno
 */
public class FrmTipo_comprobante extends javax.swing.JInternalFrame {

    EvenJFRAME evetbl = new EvenJFRAME();
    EvenJtable eveJtab = new EvenJtable();
    private tipo_comprobante ENTtc = new tipo_comprobante();
    private BO_tipo_comprobante BOtc = new BO_tipo_comprobante();
    private DAO_tipo_comprobante DAOtc = new DAO_tipo_comprobante();
    private EvenJTextField evejtf = new EvenJTextField();
    private dao_usuario DAOusu=new dao_usuario();
    Connection conn = ConnPostgres.getConnPosgres();
    cla_color_palete clacolor= new cla_color_palete();
    ClaAuxFiltroVenta clafil=new ClaAuxFiltroVenta();
    private void abrir_formulario() {
        this.setTitle("TIPO COMPROBANTE");
        evetbl.centrar_formulario_internalframa(this);        
        reestableser();
        
        color_formulario();
        if(ENTtc.isConfig_inicio()){
            jCcon_comprobante.setSelected(ENTtc.isIni_con_comprobante());
            jCsin_comprobante.setSelected(ENTtc.isIni_sin_comprobante());
            jCboleta_despachante.setSelected(ENTtc.isIni_boleta_despachante());
            jCmercaderia.setSelected(ENTtc.isIni_mercaderia());
            jCtipo_factura.setSelected(ENTtc.isIni_tipo_factura());
            ENTtc.setConfig_inicio(false);
        }
        actualizar_tabla();
    }
    void actualizar_tabla(){
        String filtro=clafil.filtro_tipo_comprobante(jCcon_comprobante, jCsin_comprobante, jCboleta_despachante, jCmercaderia, jCtipo_factura);
        DAOtc.actualizar_tabla_tipo_comprobante(conn, tbltabla,filtro);
    }
    private void color_formulario(){
        panel_tabla.setBackground(clacolor.getColor_tabla());
        panel_insertar.setBackground(clacolor.getColor_insertar_primario());
    }
    private boolean validar_guardar() {
        if (evejtf.getBoo_JTextField_vacio(txtnombre, "DEBE CARGAR UN NOMBRE")) {
            return false;
        }
        return true;
    }

    private void boton_guardar() {
        if (validar_guardar()) {
            ENTtc.setC2descripcion(txtnombre.getText());
            ENTtc.setC3con_comprobante(jCcon_comprobante.isSelected());
            ENTtc.setC4sin_comprobante(jCsin_comprobante.isSelected());
            ENTtc.setC5boleta_despachante(jCboleta_despachante.isSelected());
            ENTtc.setC6mercaderia(jCmercaderia.isSelected());
            ENTtc.setC7tipo_factura(jCtipo_factura.isSelected());
            BOtc.insertar_tipo_comprobante(ENTtc, tbltabla);
            reestableser();
        }
    }

    private void boton_editar() {
        if (validar_guardar()) {
            ENTtc.setC1idtipo_comprobante(Integer.parseInt(txtid.getText()));
            ENTtc.setC2descripcion(txtnombre.getText());
            ENTtc.setC3con_comprobante(jCcon_comprobante.isSelected());
            ENTtc.setC4sin_comprobante(jCsin_comprobante.isSelected());
            ENTtc.setC5boleta_despachante(jCboleta_despachante.isSelected());
            ENTtc.setC6mercaderia(jCmercaderia.isSelected());
            ENTtc.setC7tipo_factura(jCtipo_factura.isSelected());
            ENTtc.setC8eliminado(false);
            BOtc.update_tipo_comprobante(ENTtc, tbltabla,true);
            actualizar_tabla();
        }
    }
    private void boton_eliminar() {
        if (validar_guardar()) {
            ENTtc.setC1idtipo_comprobante(Integer.parseInt(txtid.getText()));
            ENTtc.setC8eliminado(true);
            BOtc.update_tipo_comprobante(ENTtc, tbltabla,false);
            actualizar_tabla();
            reestableser();
        }
    }
    private void seleccionar_tabla() {
        int idproducto = eveJtab.getInt_select_id(tbltabla);
        DAOtc.cargar_tipo_comprobante(conn,ENTtc, idproducto);
        txtid.setText(String.valueOf(ENTtc.getC1idtipo_comprobante()));
        txtnombre.setText(ENTtc.getC2descripcion());
        jCcon_comprobante.setSelected(ENTtc.getC3con_comprobante());
        jCsin_comprobante.setSelected(ENTtc.getC4sin_comprobante());
        jCboleta_despachante.setSelected(ENTtc.getC5boleta_despachante());
        jCmercaderia.setSelected(ENTtc.getC6mercaderia());
        jCtipo_factura.setSelected(ENTtc.getC7tipo_factura());
        btnguardar.setEnabled(false);
        btneditar.setEnabled(true);
        btndeletar.setEnabled(true);
    }
    private void reestableser(){
        txtid.setText(null);
        txtnombre.setText(null);
        jCcon_comprobante.setSelected(false);
        jCsin_comprobante.setSelected(false);
        jCboleta_despachante.setSelected(false);
        jCmercaderia.setSelected(false);
        jCtipo_factura.setSelected(false);
        btnguardar.setEnabled(true);
        btneditar.setEnabled(false);
        btndeletar.setEnabled(false);
        txtnombre.grabFocus();
    }
    private void boton_nuevo(){
        reestableser();
    }
    public FrmTipo_comprobante() {
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

        panel_insertar = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtid = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtnombre = new javax.swing.JTextField();
        btnnuevo = new javax.swing.JButton();
        btnguardar = new javax.swing.JButton();
        btneditar = new javax.swing.JButton();
        btndeletar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jCcon_comprobante = new javax.swing.JCheckBox();
        jCsin_comprobante = new javax.swing.JCheckBox();
        jCboleta_despachante = new javax.swing.JCheckBox();
        jCmercaderia = new javax.swing.JCheckBox();
        jCtipo_factura = new javax.swing.JCheckBox();
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
        jLabel2.setText("NOMBRE:");

        txtnombre.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtnombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtnombreKeyPressed(evt);
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

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("TIPOS"));

        jCcon_comprobante.setText("CON COMPROBANTE");
        jCcon_comprobante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCcon_comprobanteActionPerformed(evt);
            }
        });

        jCsin_comprobante.setText("SIN COMPROBANTE");
        jCsin_comprobante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCsin_comprobanteActionPerformed(evt);
            }
        });

        jCboleta_despachante.setText("BOLETA DESPACHANTE");
        jCboleta_despachante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCboleta_despachanteActionPerformed(evt);
            }
        });

        jCmercaderia.setText("MERCADERIA");
        jCmercaderia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCmercaderiaActionPerformed(evt);
            }
        });

        jCtipo_factura.setText("TIPO FACTURA");
        jCtipo_factura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCtipo_facturaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jCcon_comprobante)
                        .addGap(26, 26, 26)
                        .addComponent(jCtipo_factura))
                    .addComponent(jCsin_comprobante)
                    .addComponent(jCboleta_despachante)
                    .addComponent(jCmercaderia))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCcon_comprobante)
                    .addComponent(jCtipo_factura))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCsin_comprobante)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCboleta_despachante)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCmercaderia)
                .addContainerGap(20, Short.MAX_VALUE))
        );

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
                        .addGroup(panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtnombre)
                            .addGroup(panel_insertarLayout.createSequentialGroup()
                                .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(panel_insertarLayout.createSequentialGroup()
                        .addGroup(panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panel_insertarLayout.createSequentialGroup()
                                .addComponent(btnnuevo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnguardar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btneditar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btndeletar))
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 114, Short.MAX_VALUE)))
                .addContainerGap())
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
                    .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnnuevo)
                    .addComponent(btnguardar)
                    .addComponent(btneditar)
                    .addComponent(btndeletar))
                .addContainerGap(88, Short.MAX_VALUE))
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
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)
        );
        panel_tablaLayout.setVerticalGroup(
            panel_tablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
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
        DAOtc.ancho_tabla_tipo_comprobante(tbltabla);
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

    private void txtnombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombreKeyPressed
        // TODO add your handling code here:
//        evejtf.saltar_campo_enter(evt, txtnombre, txtprecio_venta);
    }//GEN-LAST:event_txtnombreKeyPressed

    private void jCcon_comprobanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCcon_comprobanteActionPerformed
        // TODO add your handling code here:
        actualizar_tabla();
    }//GEN-LAST:event_jCcon_comprobanteActionPerformed

    private void jCsin_comprobanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCsin_comprobanteActionPerformed
        // TODO add your handling code here:
        actualizar_tabla();
    }//GEN-LAST:event_jCsin_comprobanteActionPerformed

    private void jCboleta_despachanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCboleta_despachanteActionPerformed
        // TODO add your handling code here:
        actualizar_tabla();
    }//GEN-LAST:event_jCboleta_despachanteActionPerformed

    private void jCmercaderiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCmercaderiaActionPerformed
        // TODO add your handling code here:
        actualizar_tabla();
    }//GEN-LAST:event_jCmercaderiaActionPerformed

    private void jCtipo_facturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCtipo_facturaActionPerformed
        // TODO add your handling code here:
        actualizar_tabla();
    }//GEN-LAST:event_jCtipo_facturaActionPerformed

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
    private javax.swing.JCheckBox jCboleta_despachante;
    private javax.swing.JCheckBox jCcon_comprobante;
    private javax.swing.JCheckBox jCmercaderia;
    private javax.swing.JCheckBox jCsin_comprobante;
    private javax.swing.JCheckBox jCtipo_factura;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panel_insertar;
    private javax.swing.JPanel panel_tabla;
    private javax.swing.JTable tbltabla;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtnombre;
    // End of variables declaration//GEN-END:variables
}
