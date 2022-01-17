/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FORMULARIO.VISTA;

import BASEDATO.LOCAL.ConnPostgres;
import Evento.Color.cla_color_palete;
import Evento.Combobox.EvenCombobox;
import Evento.JTextField.EvenJTextField;
import Evento.Jframe.EvenJFRAME;
import Evento.Jtable.EvenJtable;
import Evento.Mensaje.EvenMensajeJoptionpane;
import FORMULARIO.BO.*;
import FORMULARIO.DAO.*;
import FORMULARIO.ENTIDAD.*;
import java.awt.Color;
import java.sql.Connection;
import javax.swing.JOptionPane;

/**
 *
 * @author Digno
 */
public class FrmUsuario_evento extends javax.swing.JInternalFrame {

    /**
     * Creates new form FrmPro_Categoria
     */
    private EvenJFRAME evetbl = new EvenJFRAME();
    private EvenJTextField evejtf = new EvenJTextField();
    private EvenJtable evejt = new EvenJtable();
    private EvenCombobox evecomb = new EvenCombobox();
    Connection conn = ConnPostgres.getConnPosgres();
    private cla_color_palete clacolor = new cla_color_palete();
    private entidad_usuario_evento ENTusu = new entidad_usuario_evento();
    private dao_usuario_evento DAOusu = new dao_usuario_evento();
    private BO_usuario_evento BOusu = new BO_usuario_evento();
    private usuario_formulario ENTusuf = new usuario_formulario();
    private DAO_usuario_formulario DAOusuf = new DAO_usuario_formulario();
    private usuario_tipo_evento ENTusute = new usuario_tipo_evento();
    private DAO_usuario_tipo_evento DAOusute = new DAO_usuario_tipo_evento();
    private EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
    boolean estado_nuevo = false;
    boolean estado_guardar = false;
    boolean estado_modificar = false;
    private boolean hab_cargar_usuario_formulario;
    private boolean hab_cargar_usuario_tipo_evento;
    private int fk_idusuario_formulario;
    private int fk_idusuario_tipo_evento;

    private void abrir_formulario() {
        this.setTitle("USUARIO EVENTO");
        evetbl.centrar_formulario(this);
        reestableser();
        DAOusu.actualizar_tabla_usuario_evento(conn, tbllocal);
        color_formulario();
        cargar_usuario_formulario();
        cargar_usuario_tipo_evento();
    }
     private void cargar_usuario_formulario() {
        evecomb.cargarCombobox(conn, cmbformulario, "idusuario_formulario", "nombre", "usuario_formulario", "");
        hab_cargar_usuario_formulario = true;
    }
     private void cargar_usuario_tipo_evento() {
        evecomb.cargarCombobox(conn, cmbevento, "idusuario_tipo_evento", "nombre", "usuario_tipo_evento", "");
        hab_cargar_usuario_tipo_evento = true;
    }
    private void color_formulario() {
        jPBNUEVO.setBackground(clacolor.getCol_btn_exit());
        jPBGUARDAR.setBackground(clacolor.getCol_btn_exit());
        jPBMODIFICAR.setBackground(clacolor.getCol_btn_exit());
        jPBOTONES.setBackground(clacolor.getCol_btn_exit());
        jPDATO.setBackground(clacolor.getCol_panel_dato());
        jPtabla_categoria.setBackground(clacolor.getCol_panel_tabla());
    }

    private void reestableser() {
        
        txtidusuario_evento.setText(null);
        txtcod_evento.setText(null);
        cmbformulario.setSelectedIndex(0);
        cmbevento.setSelectedIndex(0);
        txtdescripcion.setText(null);
        txtmensaje_error.setText(null);
        estado_guardar = true;
        estado_modificar = false;
        txtcod_evento.grabFocus();
    }

    private boolean validar_guardar() {
        if (evejtf.getBoo_JTextField_vacio(txtcod_evento, "DEBE CARGAR UN COD.EVENTO (EL CODIGO DEBE ESTAR EN EL CODIGO FUENTE)")) {
            return false;
        }
        if (evecomb.getBoo_JCombobox_seleccionar(cmbformulario, "DEBE CARGAR UN FORMULARIO (NOMBRE DEL FORMULARIO)")) {
            return false;   
        }
        if (evecomb.getBoo_JCombobox_seleccionar(cmbevento, "DEBE CARGAR UN EVENTO (QUE HACE EL EVENTO)")) {
            return false;
        }
        if (evejtf.getBoo_JTextField_vacio(txtdescripcion, "DEBE CARGAR UNA DESCRIPCION (ES UNA ESPLICACION DE QUE HACE)")) {
            return false;
        }
        if (evejtf.getBoo_JTextField_vacio(txtmensaje_error, "DEBE CARGAR UNA ERROR (EL MENSAJE SALE SI TIENE RESTRICCION DE USO)")) {
            return false;
        }
        return true;
    }

    private void boton_guardar() {
        if (estado_guardar) {
            if (validar_guardar()) {
                jPBGUARDAR.setBackground(Color.green);
                ENTusu.setC3cod_evento(Integer.parseInt(txtcod_evento.getText()));
                ENTusu.setC4fk_idusuario_formulario(fk_idusuario_formulario);
                ENTusu.setC5fk_idusuario_tipo_evento(fk_idusuario_tipo_evento);
                ENTusu.setC6descripcion(txtdescripcion.getText());
                ENTusu.setC7mensaje_error(txtmensaje_error.getText());
                BOusu.insertar_usuario_evento(ENTusu, tbllocal);
                reestableser();
            }
        } else {
            evemen.error_boton_guardar();
        }
    }

    private void boton_modificar() {
        if (estado_modificar) {
            if (validar_guardar()) {
                jPBMODIFICAR.setBackground(Color.green);
                ENTusu.setC1idusuario_evento(Integer.parseInt(txtidusuario_evento.getText()));
                ENTusu.setC3cod_evento(Integer.parseInt(txtcod_evento.getText()));
                ENTusu.setC4fk_idusuario_formulario(fk_idusuario_formulario);
                ENTusu.setC5fk_idusuario_tipo_evento(fk_idusuario_tipo_evento);
                ENTusu.setC6descripcion(txtdescripcion.getText());
                ENTusu.setC7mensaje_error(txtmensaje_error.getText());
                BOusu.update_usuario_evento(ENTusu, tbllocal);
            }
        } else {
            evemen.error_boton_modificar();
        }
    }
    private void boton_nuevo(){
        jPBNUEVO.setBackground(Color.green);
        reestableser();
    }
    private void seleccionar_tabla() {
         int idproveedor = evejt.getInt_select_id(tbllocal);
        DAOusu.cargar_usuario_evento(conn, ENTusu, tbllocal);
        txtidusuario_evento.setText(String.valueOf(ENTusu.getC1idusuario_evento()));
        txtcod_evento.setText(String.valueOf(ENTusu.getC3cod_evento()));
        DAOusuf.cargar_usuario_formulario(conn, ENTusuf, ENTusu.getC4fk_idusuario_formulario());
        cmbformulario.setSelectedItem(ENTusuf.getC2nombre()+"-("+ENTusuf.getC1idusuario_formulario()+")");
        DAOusute.cargar_usuario_tipo_evento(conn, ENTusute, ENTusu.getC5fk_idusuario_tipo_evento());
        cmbevento.setSelectedItem(ENTusute.getC2nombre()+"-("+ENTusute.getC1idusuario_tipo_evento()+")");
        txtdescripcion.setText(ENTusu.getC6descripcion());
        txtmensaje_error.setText(ENTusu.getC7mensaje_error());
        estado_guardar = false;
        estado_modificar = true;
    }

    public FrmUsuario_evento() {
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
        jPDATO = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtidusuario_evento = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtcod_evento = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtdescripcion = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtmensaje_error = new javax.swing.JTextField();
        cmbformulario = new javax.swing.JComboBox<>();
        cmbevento = new javax.swing.JComboBox<>();
        jPtabla_categoria = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbllocal = new javax.swing.JTable();
        jPBOTONES = new javax.swing.JPanel();
        jPBNUEVO = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPBGUARDAR = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPBMODIFICAR = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setClosable(true);
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

        jPDATO.setBorder(javax.swing.BorderFactory.createTitledBorder("DATO "));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("ID:");

        txtidusuario_evento.setEditable(false);
        txtidusuario_evento.setBackground(new java.awt.Color(204, 204, 255));
        txtidusuario_evento.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("cod_evento:");

        txtcod_evento.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("formulario:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("evento:");

        txtdescripcion.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("descripcion:");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("mensaje_error:");

        txtmensaje_error.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        cmbformulario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbformulario.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbformularioItemStateChanged(evt);
            }
        });

        cmbevento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbevento.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbeventoItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPDATOLayout = new javax.swing.GroupLayout(jPDATO);
        jPDATO.setLayout(jPDATOLayout);
        jPDATOLayout.setHorizontalGroup(
            jPDATOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPDATOLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPDATOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel10)
                    .addComponent(jLabel9)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPDATOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtmensaje_error)
                    .addComponent(txtdescripcion, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPDATOLayout.createSequentialGroup()
                        .addGroup(jPDATOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtidusuario_evento, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtcod_evento, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbformulario, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbevento, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 80, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPDATOLayout.setVerticalGroup(
            jPDATOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPDATOLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPDATOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtidusuario_evento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPDATOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtcod_evento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPDATOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cmbformulario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPDATOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cmbevento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPDATOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtdescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPDATOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtmensaje_error, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(216, Short.MAX_VALUE))
        );

        jPtabla_categoria.setBorder(javax.swing.BorderFactory.createTitledBorder("TABLA"));

        tbllocal.setModel(new javax.swing.table.DefaultTableModel(
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
        tbllocal.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                tbllocalMouseMoved(evt);
            }
        });
        tbllocal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tbllocalMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tbllocal);

        javax.swing.GroupLayout jPtabla_categoriaLayout = new javax.swing.GroupLayout(jPtabla_categoria);
        jPtabla_categoria.setLayout(jPtabla_categoriaLayout);
        jPtabla_categoriaLayout.setHorizontalGroup(
            jPtabla_categoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE)
        );
        jPtabla_categoriaLayout.setVerticalGroup(
            jPtabla_categoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jPBOTONES.setBackground(new java.awt.Color(204, 204, 255));
        jPBOTONES.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPBOTONES.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPBNUEVO.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jPBNUEVOMouseMoved(evt);
            }
        });
        jPBNUEVO.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPBNUEVOMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPBNUEVOMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jPBNUEVOMouseReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("NUEVO");

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/ABM/nuevo_32.png"))); // NOI18N

        javax.swing.GroupLayout jPBNUEVOLayout = new javax.swing.GroupLayout(jPBNUEVO);
        jPBNUEVO.setLayout(jPBNUEVOLayout);
        jPBNUEVOLayout.setHorizontalGroup(
            jPBNUEVOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPBNUEVOLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addContainerGap(54, Short.MAX_VALUE))
        );
        jPBNUEVOLayout.setVerticalGroup(
            jPBNUEVOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPBNUEVOLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPBOTONES.add(jPBNUEVO, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 170, 40));

        jPBGUARDAR.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jPBGUARDARMouseMoved(evt);
            }
        });
        jPBGUARDAR.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPBGUARDARMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPBGUARDARMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jPBGUARDARMouseReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("GUARDAR");

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/ABM/guardar_32.png"))); // NOI18N

        javax.swing.GroupLayout jPBGUARDARLayout = new javax.swing.GroupLayout(jPBGUARDAR);
        jPBGUARDAR.setLayout(jPBGUARDARLayout);
        jPBGUARDARLayout.setHorizontalGroup(
            jPBGUARDARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPBGUARDARLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPBGUARDARLayout.setVerticalGroup(
            jPBGUARDARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPBGUARDARLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPBOTONES.add(jPBGUARDAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, 170, 40));

        jPBMODIFICAR.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jPBMODIFICARMouseMoved(evt);
            }
        });
        jPBMODIFICAR.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPBMODIFICARMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPBMODIFICARMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jPBMODIFICARMouseReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("MODIFICAR");

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/ABM/modificar_32.png"))); // NOI18N

        javax.swing.GroupLayout jPBMODIFICARLayout = new javax.swing.GroupLayout(jPBMODIFICAR);
        jPBMODIFICAR.setLayout(jPBMODIFICARLayout);
        jPBMODIFICARLayout.setHorizontalGroup(
            jPBMODIFICARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPBMODIFICARLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(32, 32, 32))
        );
        jPBMODIFICARLayout.setVerticalGroup(
            jPBMODIFICARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPBMODIFICARLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPBOTONES.add(jPBMODIFICAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, 170, 40));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPDATO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPtabla_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPBOTONES, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPDATO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPtabla_categoria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPBOTONES, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("DATOS GUARDAR/MODIFICAR", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPBNUEVOMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPBNUEVOMouseExited
        // TODO add your handling code here:
        jPBNUEVO.setBackground(clacolor.getCol_btn_exit());
    }//GEN-LAST:event_jPBNUEVOMouseExited

    private void jPBNUEVOMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPBNUEVOMouseMoved
        // TODO add your handling code here:
        jPBNUEVO.setBackground(clacolor.getCol_btn_move());
    }//GEN-LAST:event_jPBNUEVOMouseMoved

    private void jPBGUARDARMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPBGUARDARMouseExited
        // TODO add your handling code here:
        jPBGUARDAR.setBackground(clacolor.getCol_btn_exit());
    }//GEN-LAST:event_jPBGUARDARMouseExited

    private void jPBGUARDARMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPBGUARDARMouseMoved
        // TODO add your handling code here:
        if (estado_guardar) {
            jPBGUARDAR.setBackground(clacolor.getCol_btn_move_ok());
        } else {
            jPBGUARDAR.setBackground(clacolor.getCol_btn_move());
        }
    }//GEN-LAST:event_jPBGUARDARMouseMoved

    private void jPBMODIFICARMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPBMODIFICARMouseExited
        // TODO add your handling code here:
        jPBMODIFICAR.setBackground(clacolor.getCol_btn_exit());
    }//GEN-LAST:event_jPBMODIFICARMouseExited

    private void jPBMODIFICARMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPBMODIFICARMouseMoved
        // TODO add your handling code here:
        if (estado_modificar) {
            jPBMODIFICAR.setBackground(clacolor.getCol_btn_move_ok());
        } else {
            jPBMODIFICAR.setBackground(clacolor.getCol_btn_move());
        }
    }//GEN-LAST:event_jPBMODIFICARMouseMoved

    private void jPBNUEVOMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPBNUEVOMouseClicked
        // TODO add your handling code here:
        
//        boton_nuevo();
    }//GEN-LAST:event_jPBNUEVOMouseClicked

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        // TODO add your handling code here:
        DAOusu.ancho_tabla_usuario_evento(tbllocal);
    }//GEN-LAST:event_formInternalFrameOpened

    private void jPBGUARDARMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPBGUARDARMouseClicked
        // TODO add your handling code here:
//        boton_guardar();
    }//GEN-LAST:event_jPBGUARDARMouseClicked

    private void tbllocalMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbllocalMouseReleased
        // TODO add your handling code here:
        seleccionar_tabla();
    }//GEN-LAST:event_tbllocalMouseReleased

    private void jPBMODIFICARMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPBMODIFICARMouseClicked
        // TODO add your handling code here:
//        boton_modificar();
    }//GEN-LAST:event_jPBMODIFICARMouseClicked

    private void tbllocalMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbllocalMouseMoved
        // TODO add your handling code here:
//         seleccionar_tabla();
    }//GEN-LAST:event_tbllocalMouseMoved

    private void jPBNUEVOMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPBNUEVOMouseReleased
        // TODO add your handling code here:
         boton_nuevo();
    }//GEN-LAST:event_jPBNUEVOMouseReleased

    private void jPBGUARDARMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPBGUARDARMouseReleased
        // TODO add your handling code here:
        boton_guardar();
    }//GEN-LAST:event_jPBGUARDARMouseReleased

    private void jPBMODIFICARMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPBMODIFICARMouseReleased
        // TODO add your handling code here:
        boton_modificar();
    }//GEN-LAST:event_jPBMODIFICARMouseReleased

    private void cmbformularioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbformularioItemStateChanged
        // TODO add your handling code here:
        if (hab_cargar_usuario_formulario) {
            fk_idusuario_formulario = evecomb.getInt_seleccionar_COMBOBOX(conn, cmbformulario, "idusuario_formulario", "nombre", "usuario_formulario");
        }
    }//GEN-LAST:event_cmbformularioItemStateChanged

    private void cmbeventoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbeventoItemStateChanged
        // TODO add your handling code here:
        if (hab_cargar_usuario_tipo_evento) {
            fk_idusuario_tipo_evento = evecomb.getInt_seleccionar_COMBOBOX(conn, cmbevento, "idusuario_tipo_evento", "nombre", "usuario_tipo_evento");
        }
    }//GEN-LAST:event_cmbeventoItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmbevento;
    private javax.swing.JComboBox<String> cmbformulario;
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
    private javax.swing.JPanel jPBGUARDAR;
    private javax.swing.JPanel jPBMODIFICAR;
    private javax.swing.JPanel jPBNUEVO;
    private javax.swing.JPanel jPBOTONES;
    private javax.swing.JPanel jPDATO;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPtabla_categoria;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tbllocal;
    private javax.swing.JTextField txtcod_evento;
    private javax.swing.JTextField txtdescripcion;
    private javax.swing.JTextField txtidusuario_evento;
    private javax.swing.JTextField txtmensaje_error;
    // End of variables declaration//GEN-END:variables
}
