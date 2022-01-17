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
import Evento.Mensaje.EvenMensajeJoptionpane;
import FORMULARIO.BO.*;
import FORMULARIO.DAO.*;
import FORMULARIO.ENTIDAD.*;
import java.awt.Color;
import java.sql.Connection;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Digno
 */
public class FrmUsuario_rol extends javax.swing.JInternalFrame {

    /**
     * Creates new form FrmPro_Categoria
     */
    EvenJFRAME evetbl = new EvenJFRAME();
    EvenJTextField evejtf = new EvenJTextField();
    EvenJtable evejt = new EvenJtable();
    DefaultTableModel model_urol = new DefaultTableModel();
    Connection conn = ConnPostgres.getConnPosgres();
    cla_color_palete clacolor = new cla_color_palete();
    entidad_usuario_rol entidad = new entidad_usuario_rol();
    dao_usuario_rol DAO = new dao_usuario_rol();
    dao_usuario_evento dao_eve = new dao_usuario_evento();
    BO_usuario_rol BO = new BO_usuario_rol();

    EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
    boolean estado_nuevo = false;
    boolean estado_guardar = false;
    boolean estado_modificar = false;

    private void abrir_formulario() {
        this.setTitle("USUARIO ROL");
        evetbl.centrar_formulario(this);
        reestableser();
        DAO.actualizar_tabla_usuario_rol(conn, tbllocal);
        dao_eve.cargar_usuario_evento_en_rol(conn, model_urol, tbllocal_evento);
        color_formulario();
    }

    private void color_formulario() {
        jPBNUEVO.setBackground(clacolor.getCol_btn_exit());
        jPBGUARDAR.setBackground(clacolor.getCol_btn_exit());
        jPBMODIFICAR.setBackground(clacolor.getCol_btn_exit());
        jPBOTONES.setBackground(clacolor.getCol_btn_exit());
        jPDATO.setBackground(clacolor.getCol_panel_dato());
        jPtabla_categoria.setBackground(clacolor.getCol_panel_tabla());
        jPtabla_evento.setBackground(clacolor.getColor_insertar_secundario());
    }

    private void reestableser() {
        evejt.limpiar_tabla_datos(model_urol);
        txtidcategoria.setText(null);
        txtnombre.setText(null);
        txtdescripcion.setText(null);
        estado_guardar = true;
        estado_modificar = false;
        txtnombre.grabFocus();
    }

    private boolean validar_guardar() {
        if (evejtf.getBoo_JTextField_vacio(txtnombre, "DEBE CARGAR UN NOMBRE")) {
            return false;
        }
        if (evejtf.getBoo_JTextField_vacio(txtdescripcion, "DEBE CARGAR UNA DESCRIPCION")) {
            return false;
        }
        return true;
    }

    private void boton_guardar() {
        if (estado_guardar) {
            if (validar_guardar()) {
                jPBGUARDAR.setBackground(Color.green);
                entidad.setC3nombre(txtnombre.getText());
                entidad.setC4descripcion(txtdescripcion.getText());
                BO.insertar_usuario_rol(entidad, tbllocal, tbllocal_evento);
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
                entidad.setC1idusuario_rol(Integer.parseInt(txtidcategoria.getText()));
                entidad.setC3nombre(txtnombre.getText());
                entidad.setC4descripcion(txtdescripcion.getText());
                BO.update_usuario_rol(entidad, tbllocal, tbllocal_evento);
            }
        } else {
            evemen.error_boton_modificar();
        }
    }

    private void boton_nuevo() {
        jPBNUEVO.setBackground(Color.green);
        reestableser();
    }

    private void seleccionar_tabla() {
        int idusuario_rol = evejt.getInt_select_id(tbllocal);
        DAO.cargar_usuario_rol(conn, entidad, tbllocal);
        txtidcategoria.setText(String.valueOf(entidad.getC1idusuario_rol()));
        txtnombre.setText(entidad.getC3nombre());
        txtdescripcion.setText(entidad.getC4descripcion());
        evejt.limpiar_tabla_todasfila(tbllocal_evento);
        dao_eve.cargar_usuario_evento_en_rol_seleccionar(conn, model_urol, tbllocal_evento, idusuario_rol);
        estado_guardar = false;
        estado_modificar = true;
    }

    void boton_eliminar_cargarevento() {
        if (!evejt.getBoolean_validar_select(tbllocal)) {
            if (evemen.MensajeGeneral_warning("ESTAS SEGURO DE CARGAR DENUEVO USUARIO_ROL\nSE ELIMINARA LA LISTA Y SE CARGA UNO NUEVO", "MODIFICAR", "ACEPTAR", "CANCELAR")) {
                evejt.limpiar_tabla_todasfila(tbllocal_evento);
                dao_eve.cargar_usuario_evento_en_rol(conn, model_urol, tbllocal_evento);
                BO.eliminar_recargarevento_usuario_rol(entidad, tbllocal, tbllocal_evento);
            }
        }
    }

    public FrmUsuario_rol() {
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
        txtidcategoria = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtnombre = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtdescripcion = new javax.swing.JTextField();
        jPtabla_evento = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbllocal_evento = new javax.swing.JTable();
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
        btneliminar_evento = new javax.swing.JButton();

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

        txtidcategoria.setEditable(false);
        txtidcategoria.setBackground(new java.awt.Color(204, 204, 255));
        txtidcategoria.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("NOMBRE:");

        txtnombre.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("DESCRIPCION:");

        txtdescripcion.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jPtabla_evento.setBorder(javax.swing.BorderFactory.createTitledBorder("EVENTOS"));

        tbllocal_evento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "codeve", "formulario", "evento", "descrip", "estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tbllocal_evento.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                tbllocal_eventoMouseMoved(evt);
            }
        });
        tbllocal_evento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tbllocal_eventoMouseReleased(evt);
            }
        });
        jScrollPane3.setViewportView(tbllocal_evento);
        if (tbllocal_evento.getColumnModel().getColumnCount() > 0) {
            tbllocal_evento.getColumnModel().getColumn(0).setPreferredWidth(10);
            tbllocal_evento.getColumnModel().getColumn(1).setPreferredWidth(20);
            tbllocal_evento.getColumnModel().getColumn(2).setPreferredWidth(30);
            tbllocal_evento.getColumnModel().getColumn(3).setPreferredWidth(30);
            tbllocal_evento.getColumnModel().getColumn(5).setPreferredWidth(10);
        }

        javax.swing.GroupLayout jPtabla_eventoLayout = new javax.swing.GroupLayout(jPtabla_evento);
        jPtabla_evento.setLayout(jPtabla_eventoLayout);
        jPtabla_eventoLayout.setHorizontalGroup(
            jPtabla_eventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPtabla_eventoLayout.setVerticalGroup(
            jPtabla_eventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPDATOLayout = new javax.swing.GroupLayout(jPDATO);
        jPDATO.setLayout(jPDATOLayout);
        jPDATOLayout.setHorizontalGroup(
            jPDATOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPDATOLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPDATOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPDATOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPDATOLayout.createSequentialGroup()
                        .addComponent(txtidcategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txtnombre, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtdescripcion)))
            .addComponent(jPtabla_evento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPDATOLayout.setVerticalGroup(
            jPDATOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPDATOLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPDATOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtidcategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPDATOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPDATOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtdescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPtabla_evento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        btneliminar_evento.setText("ELIMINAR Y CARGAR EVENTO");
        btneliminar_evento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminar_eventoActionPerformed(evt);
            }
        });
        jPBOTONES.add(btneliminar_evento, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 10, -1, -1));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPDATO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPtabla_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPBOTONES, javax.swing.GroupLayout.DEFAULT_SIZE, 986, Short.MAX_VALUE)
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
        DAO.ancho_tabla_usuario_rol(tbllocal);
        dao_eve.ancho_tabla_usuario_evento_en_rol(tbllocal_evento);
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

    private void tbllocal_eventoMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbllocal_eventoMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_tbllocal_eventoMouseMoved

    private void tbllocal_eventoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbllocal_eventoMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tbllocal_eventoMouseReleased

    private void btneliminar_eventoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminar_eventoActionPerformed
        // TODO add your handling code here:
        boton_eliminar_cargarevento();
    }//GEN-LAST:event_btneliminar_eventoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btneliminar_evento;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JPanel jPtabla_evento;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tbllocal;
    private javax.swing.JTable tbllocal_evento;
    private javax.swing.JTextField txtdescripcion;
    private javax.swing.JTextField txtidcategoria;
    private javax.swing.JTextField txtnombre;
    // End of variables declaration//GEN-END:variables
}
