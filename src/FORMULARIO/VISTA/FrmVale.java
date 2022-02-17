/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FORMULARIO.VISTA;

import BASEDATO.EvenConexion;
import BASEDATO.LOCAL.ConnPostgres;
import BUSCAR.ClaVarBuscar;
import BUSCAR.JDiaBuscarDosColumnas;
import Evento.Color.cla_color_palete;
import Evento.Fecha.EvenFecha;
import Evento.JLabel.EvenJLabel;
import Evento.JTextField.EvenJTextField;
import Evento.Jframe.EvenJFRAME;
import Evento.Jtable.EvenJtable;
import Evento.Utilitario.EvenNumero_a_Letra;
import FORMULARIO.BO.*;
import FORMULARIO.DAO.*;
import FORMULARIO.ENTIDAD.*;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Digno
 */
public class FrmVale extends javax.swing.JInternalFrame {

    EvenJFRAME evetbl = new EvenJFRAME();
    EvenJtable eveJtab = new EvenJtable();
    EvenJLabel evelbl = new EvenJLabel();
    private vale ENTv = new vale();
    private BO_vale BOv = new BO_vale();
    private DAO_vale DAOv = new DAO_vale();
    private funcionario ENTfu = new funcionario();
    private DAO_funcionario DAOfu = new DAO_funcionario();
    private caja_detalle ENTcaja = new caja_detalle();
    private DAO_caja_detalle DAOcaja = new DAO_caja_detalle();
    private EvenJTextField evejtf = new EvenJTextField();
    private EvenConexion eveconn = new EvenConexion();
    Connection conn = ConnPostgres.getConnPosgres();
    cla_color_palete clacolor = new cla_color_palete();
    private ClaVarBuscar vbus = new ClaVarBuscar();
    private EvenFecha evefec = new EvenFecha();
    private EvenNumero_a_Letra nroletra = new EvenNumero_a_Letra();
    private dao_usuario dao_usu = new dao_usuario();
    private entidad_usuario ENTusu = new entidad_usuario();
    private String creado_por = ENTusu.getGlobal_idusuario() + "-" + ENTusu.getGlobal_nombre();
    private static int fk_idfuncionario;

    public static int getFk_idfuncionario() {
        return fk_idfuncionario;
    }

    public static void setFk_idfuncionario(int fk_idfuncionario) {
        FrmVale.fk_idfuncionario = fk_idfuncionario;
    }
    private String estado_EMITIDO = "EMITIDO";
    private String estado_ANULADO = "ANULADO";
    private double monto_vale;
    private int idvale_select;
    private int idvale;

    private void abrir_formulario() {
        this.setTitle("VALE");
        evetbl.centrar_formulario_internalframa(this);
        reestableser();
        color_formulario();
        evefec.cargar_combobox_directo(cmbfecha_vale);
    }

    private void color_formulario() {
        panel_tabla.setBackground(clacolor.getColor_tabla());
        panel_insertar.setBackground(clacolor.getColor_insertar_primario());
    }

    private boolean validar_guardar() {
        if (evejtf.getBoo_JTextarea_vacio(txtadescripcion, "DEBE CARGAR UNA DESCRIPCION")) {
            return false;
        }
        if (evejtf.getBoo_JTextField_vacio(txtmonto_vale, "DEBE CARGAR UN MONTO")) {
            return false;
        }
        if (getFk_idfuncionario() == 0) {
            JOptionPane.showMessageDialog(null, "NO SE CARGO EL FUNCIONARIO");
            txtbuscar_funcionario.setText(null);
            txtbuscar_funcionario.grabFocus();
            return false;
        }
        return true;
    }

    private void cargar_dato() {
        ENTv.setC3creado_por(creado_por);
        ENTv.setC4descripcion(txtadescripcion.getText());
        monto_vale = Double.parseDouble(txtmonto_vale.getText());
        ENTv.setC5monto_vale(monto_vale);
        String Smonto_vale = String.valueOf(monto_vale);
        ENTv.setC6monto_letra(nroletra.Convertir(Smonto_vale, true));
        ENTv.setC7estado(estado_EMITIDO);
        ENTv.setC8fk_idfuncionario(getFk_idfuncionario());
        ENTv.setC9fk_idusuario(ENTusu.getGlobal_idusuario());
    }

    private void cargar_dato_caja_detalle() {
        DAOcaja.vaciar_caja_detalle(ENTcaja);
        ENTcaja.setC3creado_por(creado_por);
        ENTcaja.setC4descripcion("VALE: " + txtadescripcion.getText());
        ENTcaja.setC5estado(estado_EMITIDO);
        ENTcaja.setC9monto_vale(monto_vale);
        ENTcaja.setC11fk_idvale(idvale);
        ENTcaja.setC10fk_idusuario(ENTusu.getGlobal_idusuario());
    }

    private void boton_guardar() {
        if (validar_guardar()) {
            cargar_dato();
            cargar_dato_caja_detalle();
            BOv.insertar_vale(ENTv, ENTcaja);
            reestableser();
            int idvale_print = (eveconn.getInt_ultimoID_max(conn, ENTv.getTb_vale(), ENTv.getId_idvale()));
            DAOv.imprimir_rep_vale(conn, idvale_print);
            
        }
    }

    private void boton_anular() {
        if (tbltabla.getSelectedRow() >= 0) {
            ENTv.setC1idvale(Integer.parseInt(txtid.getText()));
            ENTv.setC7estado(estado_ANULADO);
            ENTcaja.setC5estado(estado_ANULADO);
            ENTcaja.setNom_campo_todos("fk_idvale");
            ENTcaja.setFk_idtodos(idvale_select);
            BOv.anular_vale(ENTv, ENTcaja);
            reestableser();
        } else {
            JOptionPane.showMessageDialog(null, "SELECCIONAR UN VALE PARA ANULAR", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
private void boton_imprimir() {
        if (tbltabla.getSelectedRow() >= 0) {
            idvale_select = eveJtab.getInt_select_id(tbltabla);
            DAOv.imprimir_rep_vale(conn, idvale_select);
        } else {
            JOptionPane.showMessageDialog(null, "SELECCIONAR UN VALE PARA ANULAR", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void seleccionar_tabla() {
        idvale_select = eveJtab.getInt_select_id(tbltabla);
        DAOv.cargar_vale(conn, ENTv, idvale_select);
        txtid.setText(String.valueOf(ENTv.getC1idvale()));
        txtadescripcion.setText(ENTv.getC4descripcion());
        txtmonto_vale.setText(String.valueOf((int) ENTv.getC5monto_vale()));
        DAOfu.cargar_funcionario(conn, ENTfu, ENTv.getC8fk_idfuncionario());
        txtbuscar_funcionario.setText(ENTfu.getC4nombre());
        setFk_idfuncionario(ENTfu.getC1idfuncionario());
        txtmonto_letra.setText(ENTv.getC6monto_letra());
        btnguardar.setEnabled(false);
        btnanular.setEnabled(true);
    }

    private void reestableser() {
        actualizar_tabla_vale();
        idvale = (eveconn.getInt_ultimoID_mas_uno(conn, ENTv.getTb_vale(), ENTv.getId_idvale()));
        
        txtid.setText(null);
        txtbuscar_funcionario.setText(null);
        txtadescripcion.setText("ADELANTO");
        setFk_idfuncionario(0);
        txtmonto_vale.setText(null);
        txtmonto_letra.setText(null);
        btnguardar.setEnabled(true);
        btnanular.setEnabled(false);
        txtadescripcion.grabFocus();
    }

    void actualizar_tabla_vale() {
        String filtro = "";
        String estado = "";
        if (jCanulado.isSelected()) {
            estado = " and v.estado='ANULADO' ";
        } else {
            estado = " and v.estado='EMITIDO' ";
        }
        String fecha = evefec.getFechaDirecto_combobox(cmbfecha_vale, " v.fecha_creado ");
        filtro = fecha + estado;
        DAOv.actualizar_tabla_vale(conn, tbltabla, filtro);
        double suma_vale = eveJtab.getDouble_sumar_tabla(tbltabla, 6);
        jFsuma_vale.setValue(suma_vale);
    }

    private void boton_nuevo() {
        reestableser();
    }

    private void abrir_buscar(int tipo, String nombre, JTextField txtbuscar) {
        vbus.setTipo_tabla(tipo);
        vbus.setNombre_tabla(nombre);
        vbus.setPre_busqueda(txtbuscar.getText());
        JDiaBuscarDosColumnas frm = new JDiaBuscarDosColumnas(null, true);
        frm.setVisible(true);
    }

    void cargar_monto() {
        if (txtmonto_vale.getText().trim().length() > 0) {
            monto_vale = Double.parseDouble(txtmonto_vale.getText());
            String Smonto_vale = String.valueOf(monto_vale);
            txtmonto_letra.setText(nroletra.Convertir(Smonto_vale, true));
        }
    }

    public FrmVale() {
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
        btnnuevo = new javax.swing.JButton();
        btnguardar = new javax.swing.JButton();
        btnanular = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtadescripcion = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        txtmonto_vale = new javax.swing.JTextField();
        lblfuncionario = new javax.swing.JLabel();
        txtbuscar_funcionario = new javax.swing.JTextField();
        btnbuscar_funcionario = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtmonto_letra = new javax.swing.JTextField();
        panel_tabla = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbltabla = new javax.swing.JTable();
        jFsuma_vale = new javax.swing.JFormattedTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        cmbfecha_vale = new javax.swing.JComboBox<>();
        jCanulado = new javax.swing.JCheckBox();
        btnimprimir = new javax.swing.JButton();

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

        btnanular.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/ABM/eliminar.png"))); // NOI18N
        btnanular.setText("ANULAR");
        btnanular.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnanular.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnanular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnanularActionPerformed(evt);
            }
        });

        txtadescripcion.setColumns(20);
        txtadescripcion.setRows(5);
        txtadescripcion.setBorder(javax.swing.BorderFactory.createTitledBorder("DESCRIPCION"));
        jScrollPane2.setViewportView(txtadescripcion);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("MONTO:");

        txtmonto_vale.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtmonto_vale.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtmonto_vale.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtmonto_valeKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtmonto_valeKeyTyped(evt);
            }
        });

        lblfuncionario.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblfuncionario.setText("Func.:");
        lblfuncionario.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblfuncionarioMouseMoved(evt);
            }
        });
        lblfuncionario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblfuncionarioMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblfuncionarioMouseExited(evt);
            }
        });

        txtbuscar_funcionario.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtbuscar_funcionario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtbuscar_funcionarioKeyPressed(evt);
            }
        });

        btnbuscar_funcionario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/ABM/mini_lupa.png"))); // NOI18N
        btnbuscar_funcionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscar_funcionarioActionPerformed(evt);
            }
        });

        jLabel4.setText("MONTO LETRA:");

        txtmonto_letra.setEditable(false);

        javax.swing.GroupLayout panel_insertarLayout = new javax.swing.GroupLayout(panel_insertar);
        panel_insertar.setLayout(panel_insertarLayout);
        panel_insertarLayout.setHorizontalGroup(
            panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_insertarLayout.createSequentialGroup()
                .addComponent(btnnuevo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnguardar)
                .addGap(93, 93, 93)
                .addComponent(btnanular)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(panel_insertarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_insertarLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtmonto_letra)
                    .addGroup(panel_insertarLayout.createSequentialGroup()
                        .addGroup(panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_insertarLayout.createSequentialGroup()
                                .addGroup(panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(lblfuncionario))
                                .addGap(20, 20, 20)
                                .addGroup(panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panel_insertarLayout.createSequentialGroup()
                                        .addComponent(txtbuscar_funcionario, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnbuscar_funcionario, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jLabel4))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panel_insertarLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtmonto_vale, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addGroup(panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblfuncionario)
                    .addComponent(txtbuscar_funcionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnbuscar_funcionario, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtmonto_vale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtmonto_letra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addGroup(panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnnuevo)
                    .addComponent(btnguardar)
                    .addComponent(btnanular))
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

        jFsuma_vale.setBorder(javax.swing.BorderFactory.createTitledBorder("SUMA VALE:"));
        jFsuma_vale.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFsuma_vale.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("FILTRO"));

        jLabel18.setText("Fecha:");

        cmbfecha_vale.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbfecha_valeItemStateChanged(evt);
            }
        });

        jCanulado.setText("ANULADO");
        jCanulado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCanuladoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCanulado)
                    .addComponent(cmbfecha_vale, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(cmbfecha_vale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCanulado)
                .addGap(0, 22, Short.MAX_VALUE))
        );

        btnimprimir.setText("IMPRIMIR");
        btnimprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnimprimirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_tablaLayout = new javax.swing.GroupLayout(panel_tabla);
        panel_tabla.setLayout(panel_tablaLayout);
        panel_tablaLayout.setHorizontalGroup(
            panel_tablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE)
            .addGroup(panel_tablaLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnimprimir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jFsuma_vale, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panel_tablaLayout.setVerticalGroup(
            panel_tablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_tablaLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_tablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_tablaLayout.createSequentialGroup()
                        .addComponent(jFsuma_vale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panel_tablaLayout.createSequentialGroup()
                        .addComponent(btnimprimir)
                        .addGap(0, 0, Short.MAX_VALUE))))
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
            .addComponent(panel_insertar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panel_tabla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
        // TODO add your handling code here:
        boton_guardar();
    }//GEN-LAST:event_btnguardarActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        // TODO add your handling code here:
        DAOv.ancho_tabla_vale(tbltabla);
    }//GEN-LAST:event_formInternalFrameOpened

    private void tbltablaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbltablaMouseReleased
        // TODO add your handling code here:
        seleccionar_tabla();
    }//GEN-LAST:event_tbltablaMouseReleased

    private void btnnuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnuevoActionPerformed
        // TODO add your handling code here:
        boton_nuevo();
    }//GEN-LAST:event_btnnuevoActionPerformed

    private void btnbuscar_funcionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscar_funcionarioActionPerformed
        // TODO add your handling code here:
        abrir_buscar(10, "FUNCIONARIO", txtbuscar_funcionario);
    }//GEN-LAST:event_btnbuscar_funcionarioActionPerformed

    private void txtbuscar_funcionarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscar_funcionarioKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            abrir_buscar(10, "FUNCIONARIO", txtbuscar_funcionario);
        }
    }//GEN-LAST:event_txtbuscar_funcionarioKeyPressed

    private void txtmonto_valeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmonto_valeKeyTyped
        // TODO add your handling code here:
        evejtf.soloNumero(evt);
    }//GEN-LAST:event_txtmonto_valeKeyTyped

    private void cmbfecha_valeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbfecha_valeItemStateChanged
        // TODO add your handling code here:
        actualizar_tabla_vale();
    }//GEN-LAST:event_cmbfecha_valeItemStateChanged

    private void btnanularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnanularActionPerformed
        // TODO add your handling code here:
        boton_anular();
    }//GEN-LAST:event_btnanularActionPerformed

    private void jCanuladoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCanuladoActionPerformed
        // TODO add your handling code here:
        actualizar_tabla_vale();
    }//GEN-LAST:event_jCanuladoActionPerformed

    private void lblfuncionarioMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblfuncionarioMouseMoved
        // TODO add your handling code here:
        evelbl.evento_MouseMoved(lblfuncionario);
    }//GEN-LAST:event_lblfuncionarioMouseMoved

    private void lblfuncionarioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblfuncionarioMouseExited
        // TODO add your handling code here:
        evelbl.evento_MouseExited(lblfuncionario);
    }//GEN-LAST:event_lblfuncionarioMouseExited

    private void lblfuncionarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblfuncionarioMouseClicked
        // TODO add your handling code here:
        evetbl.abrir_TablaJinternal(new FrmFuncionario());
    }//GEN-LAST:event_lblfuncionarioMouseClicked

    private void txtmonto_valeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmonto_valeKeyReleased
        // TODO add your handling code here:
        cargar_monto();
    }//GEN-LAST:event_txtmonto_valeKeyReleased

    private void btnimprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnimprimirActionPerformed
        // TODO add your handling code here:
        boton_imprimir();
    }//GEN-LAST:event_btnimprimirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnanular;
    private javax.swing.JButton btnbuscar_funcionario;
    private javax.swing.JButton btnguardar;
    private javax.swing.JButton btnimprimir;
    private javax.swing.JButton btnnuevo;
    private javax.swing.JComboBox<String> cmbfecha_vale;
    private javax.swing.JCheckBox jCanulado;
    private javax.swing.JFormattedTextField jFsuma_vale;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblfuncionario;
    private javax.swing.JPanel panel_insertar;
    private javax.swing.JPanel panel_tabla;
    private javax.swing.JTable tbltabla;
    public static javax.swing.JTextArea txtadescripcion;
    public static javax.swing.JTextField txtbuscar_funcionario;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtmonto_letra;
    private javax.swing.JTextField txtmonto_vale;
    // End of variables declaration//GEN-END:variables
}
