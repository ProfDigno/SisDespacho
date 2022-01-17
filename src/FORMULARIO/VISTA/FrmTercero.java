/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FORMULARIO.VISTA;

import BASEDATO.LOCAL.ConnPostgres;
import Evento.Color.cla_color_palete;
import Evento.Combobox.EvenCombobox;
import Evento.Fecha.EvenFecha;
import Evento.JLabel.EvenJLabel;
import Evento.JTextField.EvenJTextField;
import Evento.Jframe.EvenJFRAME;
import Evento.Jtable.EvenJtable;
import FORMULARIO.BO.*;
import FORMULARIO.DAO.*;
import FORMULARIO.ENTIDAD.*;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import javax.swing.JOptionPane;

/**
 *
 * @author Digno
 */
public class FrmTercero extends javax.swing.JInternalFrame {

    EvenJFRAME evetbl = new EvenJFRAME();
    EvenJtable eveJtab = new EvenJtable();
     EvenCombobox evecomb = new EvenCombobox();
     EvenJLabel evelbl=new EvenJLabel();
     EvenFecha evefec=new EvenFecha();
    private tercero ENTter = new tercero();
    private BO_tercero BOter = new BO_tercero();
    private DAO_tercero DAOter = new DAO_tercero();
    private tercero_pais ENTpais = new tercero_pais();
    private DAO_tercero_pais DAOpais = new DAO_tercero_pais();
    private tercero_ciudad ENTciu = new tercero_ciudad();
    private DAO_tercero_ciudad DAOciu = new DAO_tercero_ciudad();
    private item_tipo_registro ENTitr = new item_tipo_registro();
    private BO_item_tipo_registro BOitr = new BO_item_tipo_registro();
    private DAO_item_tipo_registro DAOitr = new DAO_item_tipo_registro();
    private tipo_registro ENTtr = new tipo_registro();
    private DAO_tipo_registro DAOtr = new DAO_tipo_registro();
    private tipo_dependencia ENTtd = new tipo_dependencia();
    private DAO_tipo_dependencia DAOtd = new DAO_tipo_dependencia();
    private tipo_institucion ENTti = new tipo_institucion();
    private DAO_tipo_institucion DAOti = new DAO_tipo_institucion();
    private EvenJTextField evejtf = new EvenJTextField();
    Connection conn = ConnPostgres.getConnPosgres();
    cla_color_palete clacolor= new cla_color_palete();
    private boolean hab_combo_pais=false;
    private int fk_idtercero_pais;
    private boolean hab_combo_ciudad=false;
    private int fk_idtercero_ciudad;
    private boolean hab_combo_tipo_institucion;
    private boolean hab_combo_tipo_dependencia;
    private boolean hab_combo_tipo_registro;
    private int fk_idtipo_registro;
    private int fk_idtipo_dependencia;
    private int fk_idtipo_institucion;
    private String creado_por="Digno";
    private int fk_idtercero;
    private int iditem_tipo_registro;
    private String estado_activo="ACTIVO";
    private String estado_desabilitado="DESABILITADO";
    private void abrir_formulario() {
        this.setTitle("TERCERO");
        evetbl.centrar_formulario_internalframa(this);        
        reestableser_tercero();
        reestableser_item_tipo_registro();
        DAOter.actualizar_tabla_tercero(conn, tbltercero);
        color_formulario();
        cargar_pais();
        cargar_ciudad();
        cargar_tipo_registro();
        cargar_tipo_dependencia();
        cargar_tipo_institucion();
    }
    private void reestableser_item_tipo_registro(){
        txtfecha_estado.setText(evefec.getString_formato_fecha());
        txtfecha_habilitacion.setText(evefec.getString_formato_fecha());
        txtfecha_vencimiento.setText(evefec.getString_formato_fecha());
        txtnro_habilitacion.setText(null);
        txtimagen.setText("SIN-IMAGEN");
        lblformatfech_1.setText(evefec.getFormato_fecha());
        lblformatfech_2.setText(evefec.getFormato_fecha());
        lblformatfech_3.setText(evefec.getFormato_fecha());
        
        jCtipo_dependencia.setSelectedIndex(0);
        jCtipo_institucion.setSelectedIndex(0);
        jCtipo_registro.setSelectedIndex(0);
        btnguardar_item_tr.setEnabled(true);
        btneditar_item_tr.setEnabled(false);
        txtfecha_estado.grabFocus();
    }
    private void cargar_pais() {
        evecomb.cargarCombobox(conn, jCpais, "idtercero_pais", "nombre", "tercero_pais", "");
        hab_combo_pais = true;
    }
    private void cargar_ciudad() {
        evecomb.cargarCombobox(conn, jCciudad, "idtercero_ciudad", "nombre", "tercero_ciudad", "");
        hab_combo_ciudad = true;
    }
    private void cargar_tipo_registro() {
        evecomb.cargarCombobox(conn, jCtipo_registro, "idtipo_registro", "nombre", "tipo_registro", "");
        hab_combo_tipo_registro = true;
    }
    private void cargar_tipo_dependencia() {
        evecomb.cargarCombobox(conn, jCtipo_dependencia, "idtipo_dependencia", "nombre", "tipo_dependencia", "");
        hab_combo_tipo_dependencia = true;
    }
    private void cargar_tipo_institucion() {
        evecomb.cargarCombobox(conn, jCtipo_institucion, "idtipo_institucion", "nombre", "tipo_institucion", "");
        hab_combo_tipo_institucion = true;
    }
    private void color_formulario(){
        panel_tabla.setBackground(clacolor.getColor_tabla());
        panel_insertar.setBackground(clacolor.getColor_insertar_primario());
    }
    private boolean validar_guardar_tercero() {
        if (evejtf.getBoo_JTextField_vacio(txtnombre, "DEBE CARGAR UN NOMBRE")) {
            return false;
        }
        if (evejtf.getBoo_JTextField_vacio(txtruc, "DEBE CARGAR UN RUC")) {
            return false;
        }
        if (evejtf.getBoo_JTextField_vacio(txttelefono, "DEBE CARGAR UN TELEFONO")) {
            return false;
        }
        if (evejtf.getBoo_JTextField_vacio(txtdireccion, "DEBE CARGAR UNA DIRECCION")) {
            return false;
        }
        if (evejtf.getBoo_JTextField_vacio(txtrepre_nombre, "DEBE CARGAR UN NOMBRE DEL REPRESENTANTE")) {
            return false;
        }
        if (evejtf.getBoo_JTextField_vacio(txtrepre_cedula, "DEBE CARGAR UNA CEDULA DEL REPRESENTANTE")) {
            return false;
        }
        if(evecomb.getBoo_JCombobox_seleccionar(jCpais,"SELECCIONAR UN PAIS")){
            return false;
        }
        if(evecomb.getBoo_JCombobox_seleccionar(jCciudad,"SELECCIONAR UNA CIUDAD")){
            return false;
        }
        
        return true;
    }
    private boolean validar_guardar_item_tipo_registro() {
        if (evejtf.getBoo_JTextField_vacio(txtfecha_estado, "DEBE CARGAR UNA FECHA ESTADO")) {
            txtfecha_estado.setText(evefec.getString_formato_fecha());
            return false;
        }
        if (evejtf.getBoo_JTextField_vacio(txtfecha_habilitacion, "DEBE CARGAR UNA FECHA HABILITACION")) {
            txtfecha_habilitacion.setText(evefec.getString_formato_fecha());
            return false;
        }
        if (evejtf.getBoo_JTextField_vacio(txtfecha_vencimiento, "DEBE CARGAR UNA FECHA VENCIMIENTO")) {
            txtfecha_vencimiento.setText(evefec.getString_formato_fecha());
            return false;
        }
        if (evejtf.getBoo_JTextField_vacio(txtnro_habilitacion, "DEBE CARGAR UN NUMERO DE HABILITACION")) {
            return false;
        }
        if (evejtf.getBoo_JTextField_vacio(txtimagen, "DEBE CARGAR UNA IMAGEN")) {
            return false;
        }
        if(evecomb.getBoo_JCombobox_seleccionar(jCtipo_registro,"SELECCIONE UN TIPO DE REGISTRO")){
            return false;
        }
        if(evecomb.getBoo_JCombobox_seleccionar(jCtipo_dependencia,"SELECCIONE UN TIPO DE DEPENDENCIA")){
            return false;
        }
        if(evecomb.getBoo_JCombobox_seleccionar(jCtipo_institucion,"SELECCIONE UN TIPO DE INSTITUCION")){
            return false;
        }
        if(tbltercero.getSelectedRow()<0){
            eveJtab.mostrar_JTabbedPane(jTabbedPane1, 1);
            JOptionPane.showMessageDialog(null, "SELECCIONE LA TABLA DE TERCERO", "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    private void cargar_dato_tercero(){
        ENTter.setC3creado_por(creado_por);
        ENTter.setC4nombre(txtnombre.getText());
        ENTter.setC5ruc(txtruc.getText());
        ENTter.setC6telefono(txttelefono.getText());
        ENTter.setC7direccion(txtdireccion.getText());
        ENTter.setC8representante_nombre(txtrepre_nombre.getText());
        ENTter.setC9representante_cedula(txtrepre_cedula.getText());
        ENTter.setC10importador(jCimportador.isSelected());
        ENTter.setC11despachante(jCdespachante.isSelected());
        ENTter.setC12colaborador(jCcolaborador.isSelected());
        ENTter.setC13proveedor(jCproveedor.isSelected());
        ENTter.setC14transportadora(jCtransportadora.isSelected());
        ENTter.setC15fk_idtercero_pais(fk_idtercero_pais);
        ENTter.setC16fk_idtercero_ciudad(fk_idtercero_ciudad);
    }
    private String getestado_registro(){
        String estado="error";
        if(jRest_activo.isSelected()){
            estado=estado_activo;
        }
        if(jRest_desabilitado.isSelected()){
            estado=estado_desabilitado;
        }
        return estado;
    }
    private void setestado_registro(String estado){
        if(estado.equals(estado_activo)){
            jRest_activo.setSelected(true);
            jRest_desabilitado.setSelected(false);
        }
        if(estado.equals(estado_desabilitado)){
            jRest_activo.setSelected(false);
            jRest_desabilitado.setSelected(true);
        }
    }
    private void cargar_dato_item_tipo_registro(){
        ENTitr.setC3creado_por(creado_por);
        ENTitr.setC4estado(getestado_registro());
        ENTitr.setC5fecha_estado(evefec.getString_validar_fecha(txtfecha_estado.getText()));
        ENTitr.setC6nro_habilitacion(txtnro_habilitacion.getText());
        ENTitr.setC7fecha_habilitacion(evefec.getString_validar_fecha(txtfecha_habilitacion.getText()));
        ENTitr.setC8fecha_vencimiento(evefec.getString_validar_fecha(txtfecha_vencimiento.getText()));
        ENTitr.setC9imagen(txtimagen.getText());
        ENTitr.setC10fk_idtipo_registro(fk_idtipo_registro);
        ENTitr.setC11fk_idtipo_dependencia(fk_idtipo_dependencia);
        ENTitr.setC12fk_idtipo_institucion(fk_idtipo_institucion);
        ENTitr.setC13fk_idtercero(fk_idtercero);
    }
    private void boton_guardar_tercero() {
        if (validar_guardar_tercero()) {
            cargar_dato_tercero();
            BOter.insertar_tercero(ENTter, tbltercero);
            reestableser_tercero();
        }
    }
    private void boton_guardar_item_tipo_registro() {
        if (validar_guardar_item_tipo_registro()) {
            cargar_dato_item_tipo_registro();
            BOitr.insertar_item_tipo_registro(ENTitr, tblitem_tipo_registro);
            reestableser_item_tipo_registro();
        }
    }
    private void boton_editar_tercero() {
        if (validar_guardar_tercero()) {
            ENTter.setC1idtercero(Integer.parseInt(txtid.getText()));
            cargar_dato_tercero();
            BOter.update_tercero(ENTter, tbltercero);
        }
    }
    private void boton_editar_item_tipo_registro() {
        if (validar_guardar_item_tipo_registro()) {
            ENTitr.setC1iditem_tipo_registro(Integer.parseInt(txtiditem_tipo_registro.getText()));
            cargar_dato_item_tipo_registro();
            BOitr.update_item_tipo_registro(ENTitr, tblitem_tipo_registro);
        }
    }

    private void seleccionar_tabla_tercero() {
        fk_idtercero = eveJtab.getInt_select_id(tbltercero);
        DAOter.cargar_tercero(conn,ENTter, fk_idtercero);
        txtid.setText(String.valueOf(ENTter.getC1idtercero()));
        txtnombre.setText(ENTter.getC4nombre());
        txtruc.setText(ENTter.getC5ruc());
        txttelefono.setText(ENTter.getC6telefono());
        txtdireccion.setText(ENTter.getC7direccion());
        txtrepre_nombre.setText(ENTter.getC8representante_nombre());
        txtrepre_cedula.setText(ENTter.getC9representante_cedula());
        jCimportador.setSelected(ENTter.getC10importador());
        jCdespachante.setSelected(ENTter.getC11despachante());
        jCcolaborador.setSelected(ENTter.getC12colaborador());
        jCproveedor.setSelected(ENTter.getC13proveedor());
        jCtransportadora.setSelected(ENTter.getC14transportadora());
        fk_idtercero_pais=ENTter.getC15fk_idtercero_pais();
        fk_idtercero_ciudad=ENTter.getC16fk_idtercero_ciudad();
        DAOpais.cargar_tercero_pais(conn, ENTpais,fk_idtercero_pais);
        evecomb.setSeleccionarCombobox(jCpais, ENTpais.getC1idtercero_pais(), ENTpais.getC2nombre());
        DAOciu.cargar_tercero_ciudad(conn, ENTciu,fk_idtercero_ciudad);
        evecomb.setSeleccionarCombobox(jCciudad, ENTciu.getC1idtercero_ciudad(), ENTciu.getC2nombre());
        btnguardar_tercero.setEnabled(false);
        btneditar_tercero.setEnabled(true);
    }
    private void seleccionar_tabla_item_tipo_registro() {
        iditem_tipo_registro = eveJtab.getInt_select_id(tblitem_tipo_registro);
        DAOitr.cargar_item_tipo_registro(conn, ENTitr, iditem_tipo_registro);
        txtiditem_tipo_registro.setText(String.valueOf(ENTitr.getC1iditem_tipo_registro()));
        setestado_registro(ENTitr.getC4estado());
        txtfecha_estado.setText(ENTitr.getC5fecha_estado());
        txtnro_habilitacion.setText(ENTitr.getC6nro_habilitacion());
        txtfecha_habilitacion.setText(ENTitr.getC7fecha_habilitacion());
        txtfecha_vencimiento.setText(ENTitr.getC8fecha_vencimiento());
        txtimagen.setText(ENTitr.getC9imagen());
        fk_idtipo_registro=ENTitr.getC10fk_idtipo_registro();
        fk_idtipo_dependencia=ENTitr.getC11fk_idtipo_dependencia();
        fk_idtipo_institucion=ENTitr.getC12fk_idtipo_institucion();
        fk_idtercero=ENTitr.getC13fk_idtercero();
        DAOtr.cargar_tipo_registro(conn, ENTtr,fk_idtipo_registro);
        evecomb.setSeleccionarCombobox(jCtipo_registro, ENTtr.getC1idtipo_registro(), ENTtr.getC2nombre());
        DAOtd.cargar_tipo_dependencia(conn, ENTtd,fk_idtipo_dependencia);
        evecomb.setSeleccionarCombobox(jCtipo_dependencia, ENTtd.getC1idtipo_dependencia(), ENTtd.getC2nombre());
        DAOti.cargar_tipo_institucion(conn, ENTti,fk_idtipo_institucion);
        evecomb.setSeleccionarCombobox(jCtipo_institucion, ENTti.getC1idtipo_institucion(), ENTtd.getC2nombre());
        btnguardar_item_tr.setEnabled(false);
        btneditar_item_tr.setEnabled(true);
    }
    private void reestableser_tercero(){
        txtid.setText(null);
        txtnombre.setText(null);
        txtruc.setText(null);
        txttelefono.setText(null);
        txtdireccion.setText(null);
        txtrepre_nombre.setText(null);
        txtrepre_cedula.setText(null);
        jCimportador.setSelected(false);
        jCdespachante.setSelected(false);
        jCcolaborador.setSelected(false);
        jCproveedor.setSelected(false);
        jCtransportadora.setSelected(false);
        fk_idtercero_pais=0;
        fk_idtercero_ciudad=0;
        jCpais.setSelectedIndex(0);
        jCciudad.setSelectedIndex(0);
        btnguardar_tercero.setEnabled(true);
        btneditar_tercero.setEnabled(false);
        btndeletar_tercero.setEnabled(false);
        txtnombre.grabFocus();
    }
    private void boton_nuevo_tercero(){
        reestableser_tercero();
    }
    public FrmTercero() {
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

        est_reg = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        panel_insertar = new javax.swing.JPanel();
        btnnuevo_tercero = new javax.swing.JButton();
        btnguardar_tercero = new javax.swing.JButton();
        btneditar_tercero = new javax.swing.JButton();
        btndeletar_tercero = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtnombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtruc = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txttelefono = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtdireccion = new javax.swing.JTextField();
        lblpais = new javax.swing.JLabel();
        jCpais = new javax.swing.JComboBox<>();
        lblciudad = new javax.swing.JLabel();
        jCciudad = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtrepre_cedula = new javax.swing.JTextField();
        txtrepre_nombre = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jCimportador = new javax.swing.JCheckBox();
        jCdespachante = new javax.swing.JCheckBox();
        jCcolaborador = new javax.swing.JCheckBox();
        jCproveedor = new javax.swing.JCheckBox();
        jCtransportadora = new javax.swing.JCheckBox();
        lblID = new javax.swing.JLabel();
        txtid = new javax.swing.JTextField();
        panel_tabla = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbltercero = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtfecha_estado = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtfecha_habilitacion = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtfecha_vencimiento = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jRest_activo = new javax.swing.JRadioButton();
        jRest_desabilitado = new javax.swing.JRadioButton();
        jLabel11 = new javax.swing.JLabel();
        txtnro_habilitacion = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtimagen = new javax.swing.JTextField();
        btnabrir_imagen = new javax.swing.JButton();
        lbltipo_registro = new javax.swing.JLabel();
        jCtipo_registro = new javax.swing.JComboBox<>();
        lbltipo_dependencia = new javax.swing.JLabel();
        jCtipo_dependencia = new javax.swing.JComboBox<>();
        lbltipo_institucion = new javax.swing.JLabel();
        jCtipo_institucion = new javax.swing.JComboBox<>();
        btnnuevo_item_tr = new javax.swing.JButton();
        btnguardar_item_tr = new javax.swing.JButton();
        btneditar_item_tr = new javax.swing.JButton();
        lblformatfech_1 = new javax.swing.JLabel();
        lblformatfech_2 = new javax.swing.JLabel();
        lblformatfech_3 = new javax.swing.JLabel();
        txtiditem_tipo_registro = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblitem_tipo_registro = new javax.swing.JTable();

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

        btnnuevo_tercero.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/ABM/nuevo.png"))); // NOI18N
        btnnuevo_tercero.setText("NUEVO");
        btnnuevo_tercero.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnnuevo_tercero.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnnuevo_tercero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnuevo_terceroActionPerformed(evt);
            }
        });

        btnguardar_tercero.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/ABM/guardar.png"))); // NOI18N
        btnguardar_tercero.setText("GUARDAR");
        btnguardar_tercero.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnguardar_tercero.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnguardar_tercero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardar_terceroActionPerformed(evt);
            }
        });

        btneditar_tercero.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/ABM/modificar.png"))); // NOI18N
        btneditar_tercero.setText("EDITAR");
        btneditar_tercero.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btneditar_tercero.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btneditar_tercero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditar_terceroActionPerformed(evt);
            }
        });

        btndeletar_tercero.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/ABM/eliminar.png"))); // NOI18N
        btndeletar_tercero.setText("DELETAR");
        btndeletar_tercero.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btndeletar_tercero.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("DATOS EMPRESA "));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("NOMBRE:");

        txtnombre.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtnombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtnombreKeyPressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("RUC:");

        txtruc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtruc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtrucKeyPressed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("TELEFONO:");

        txttelefono.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txttelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txttelefonoKeyPressed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("DIRECCION:");

        txtdireccion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtdireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtdireccionKeyPressed(evt);
            }
        });

        lblpais.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblpais.setText("PAIS:");
        lblpais.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblpaisMouseMoved(evt);
            }
        });
        lblpais.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblpaisMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblpaisMouseExited(evt);
            }
        });

        jCpais.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jCpais.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jCpais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCpaisActionPerformed(evt);
            }
        });

        lblciudad.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblciudad.setText("CIUDAD:");
        lblciudad.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblciudadMouseMoved(evt);
            }
        });
        lblciudad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblciudadMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblciudadMouseExited(evt);
            }
        });

        jCciudad.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jCciudad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jCciudad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCciudadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(lblpais, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtdireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txttelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtruc, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jCpais, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblciudad)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCciudad, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(64, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtruc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txttelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtdireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblciudad)
                        .addComponent(jCciudad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblpais)
                        .addComponent(jCpais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 10, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("REPRESENTANTE"));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("NOMBRE:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("CEDULA:");

        txtrepre_cedula.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtrepre_cedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtrepre_cedulaKeyPressed(evt);
            }
        });

        txtrepre_nombre.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtrepre_nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtrepre_nombreKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtrepre_cedula, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtrepre_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(82, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtrepre_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtrepre_cedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 8, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("RELACIONES"));

        jCimportador.setText("IMPORTADOR O EXPORTADOR");

        jCdespachante.setText("DESPACHANTE");

        jCcolaborador.setText("COLABORADOR");

        jCproveedor.setText("PROVEEDOR");

        jCtransportadora.setText("TRANSPORTADORA");

        lblID.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblID.setText("ID:");
        lblID.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        txtid.setEditable(false);
        txtid.setBackground(new java.awt.Color(204, 204, 204));
        txtid.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCimportador)
                    .addComponent(jCdespachante)
                    .addComponent(jCcolaborador)
                    .addComponent(jCproveedor)
                    .addComponent(jCtransportadora)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lblID)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(130, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jCimportador)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCdespachante)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCcolaborador)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCproveedor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCtransportadora)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblID)
                    .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout panel_insertarLayout = new javax.swing.GroupLayout(panel_insertar);
        panel_insertar.setLayout(panel_insertarLayout);
        panel_insertarLayout.setHorizontalGroup(
            panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_insertarLayout.createSequentialGroup()
                .addGroup(panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_insertarLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnnuevo_tercero)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnguardar_tercero)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btneditar_tercero)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btndeletar_tercero))
                    .addGroup(panel_insertarLayout.createSequentialGroup()
                        .addGroup(panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        panel_insertarLayout.setVerticalGroup(
            panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_insertarLayout.createSequentialGroup()
                .addGroup(panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panel_insertarLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 117, Short.MAX_VALUE)
                .addGroup(panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnnuevo_tercero)
                    .addComponent(btnguardar_tercero)
                    .addComponent(btneditar_tercero)
                    .addComponent(btndeletar_tercero))
                .addContainerGap())
        );

        jTabbedPane1.addTab("CARGAR DATO", panel_insertar);

        panel_tabla.setBackground(new java.awt.Color(51, 204, 255));
        panel_tabla.setBorder(javax.swing.BorderFactory.createTitledBorder("TABLA"));

        tbltercero.setModel(new javax.swing.table.DefaultTableModel(
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
        tbltercero.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblterceroMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tbltercero);

        javax.swing.GroupLayout panel_tablaLayout = new javax.swing.GroupLayout(panel_tabla);
        panel_tabla.setLayout(panel_tablaLayout);
        panel_tablaLayout.setHorizontalGroup(
            panel_tablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 936, Short.MAX_VALUE)
        );
        panel_tablaLayout.setVerticalGroup(
            panel_tablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("FILTRO TABLA", panel_tabla);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("DATOS REGISTRO"));

        jLabel1.setText("FECHA ESTADO:");

        txtfecha_estado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtfecha_estadoKeyPressed(evt);
            }
        });

        jLabel6.setText("FECHA HABILITACION:");

        txtfecha_habilitacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtfecha_habilitacionKeyPressed(evt);
            }
        });

        jLabel7.setText("FECHA VENCIMIENTO:");

        txtfecha_vencimiento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtfecha_vencimientoKeyPressed(evt);
            }
        });

        jLabel10.setText("ESTADO:");

        est_reg.add(jRest_activo);
        jRest_activo.setSelected(true);
        jRest_activo.setText("ACTIVO");

        est_reg.add(jRest_desabilitado);
        jRest_desabilitado.setText("DESABILITADO");

        jLabel11.setText("Nro HABILITACION:");

        jLabel12.setText("Ruta IMAGEN:");

        btnabrir_imagen.setText("ABRIR");

        lbltipo_registro.setText("TIPO REGISTRO:");
        lbltipo_registro.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lbltipo_registroMouseMoved(evt);
            }
        });
        lbltipo_registro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbltipo_registroMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbltipo_registroMouseExited(evt);
            }
        });

        jCtipo_registro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jCtipo_registro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jCtipo_registroMouseExited(evt);
            }
        });
        jCtipo_registro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCtipo_registroActionPerformed(evt);
            }
        });

        lbltipo_dependencia.setText("TIPO DEPENDENCIA:");
        lbltipo_dependencia.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lbltipo_dependenciaMouseMoved(evt);
            }
        });
        lbltipo_dependencia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbltipo_dependenciaMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbltipo_dependenciaMouseExited(evt);
            }
        });

        jCtipo_dependencia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jCtipo_dependencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCtipo_dependenciaActionPerformed(evt);
            }
        });

        lbltipo_institucion.setText("TIPO INSTITUCION:");
        lbltipo_institucion.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lbltipo_institucionMouseMoved(evt);
            }
        });
        lbltipo_institucion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbltipo_institucionMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbltipo_institucionMouseExited(evt);
            }
        });

        jCtipo_institucion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jCtipo_institucion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCtipo_institucionActionPerformed(evt);
            }
        });

        btnnuevo_item_tr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/ABM/nuevo.png"))); // NOI18N
        btnnuevo_item_tr.setText("NUEVO");
        btnnuevo_item_tr.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnnuevo_item_tr.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnnuevo_item_tr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnuevo_item_trActionPerformed(evt);
            }
        });

        btnguardar_item_tr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/ABM/guardar.png"))); // NOI18N
        btnguardar_item_tr.setText("GUARDAR");
        btnguardar_item_tr.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnguardar_item_tr.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnguardar_item_tr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardar_item_trActionPerformed(evt);
            }
        });

        btneditar_item_tr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/ABM/modificar.png"))); // NOI18N
        btneditar_item_tr.setText("EDITAR");
        btneditar_item_tr.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btneditar_item_tr.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btneditar_item_tr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditar_item_trActionPerformed(evt);
            }
        });

        lblformatfech_1.setText("formatfec");

        lblformatfech_2.setText("formatfec");

        lblformatfech_3.setText("formatfec");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel10)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6)
                            .addComponent(jLabel1)
                            .addComponent(jLabel12)
                            .addComponent(lbltipo_registro))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jRest_activo, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jRest_desabilitado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap(38, Short.MAX_VALUE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txtimagen, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                                            .addComponent(txtfecha_vencimiento, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtfecha_habilitacion, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtfecha_estado, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnabrir_imagen)
                                            .addComponent(lblformatfech_1)
                                            .addComponent(lblformatfech_2)
                                            .addComponent(lblformatfech_3)))
                                    .addComponent(txtnro_habilitacion, javax.swing.GroupLayout.Alignment.LEADING))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCtipo_dependencia, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jCtipo_registro, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jCtipo_institucion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbltipo_dependencia)
                                    .addComponent(lbltipo_institucion)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(btnnuevo_item_tr)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnguardar_item_tr)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btneditar_item_tr)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtiditem_tipo_registro, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtfecha_estado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblformatfech_1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtfecha_habilitacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblformatfech_2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtfecha_vencimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblformatfech_3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jRest_activo)
                    .addComponent(jRest_desabilitado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtnro_habilitacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel12)
                    .addComponent(txtimagen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnabrir_imagen))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbltipo_registro)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCtipo_registro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbltipo_dependencia)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCtipo_dependencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbltipo_institucion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCtipo_institucion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnnuevo_item_tr)
                    .addComponent(btnguardar_item_tr)
                    .addComponent(btneditar_item_tr)
                    .addComponent(txtiditem_tipo_registro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("TABLA REGISTRO"));

        tblitem_tipo_registro.setModel(new javax.swing.table.DefaultTableModel(
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
        tblitem_tipo_registro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblitem_tipo_registroMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tblitem_tipo_registro);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 37, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("TIPO REGISTRO", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnguardar_terceroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardar_terceroActionPerformed
        // TODO add your handling code here:
        boton_guardar_tercero();
    }//GEN-LAST:event_btnguardar_terceroActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        // TODO add your handling code here:
        DAOter.ancho_tabla_tercero(tbltercero);
    }//GEN-LAST:event_formInternalFrameOpened

    private void tblterceroMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblterceroMouseReleased
        // TODO add your handling code here:
        seleccionar_tabla_tercero();
    }//GEN-LAST:event_tblterceroMouseReleased

    private void btneditar_terceroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditar_terceroActionPerformed
        // TODO add your handling code here:
        boton_editar_tercero();
    }//GEN-LAST:event_btneditar_terceroActionPerformed

    private void btnnuevo_terceroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnuevo_terceroActionPerformed
        // TODO add your handling code here:
        boton_nuevo_tercero();
    }//GEN-LAST:event_btnnuevo_terceroActionPerformed

    private void txtnombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombreKeyPressed
        // TODO add your handling code here:
//        evejtf.saltar_campo_enter(evt, txtnombre, txtprecio_venta);
    }//GEN-LAST:event_txtnombreKeyPressed

    private void txtrucKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrucKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtrucKeyPressed

    private void txttelefonoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttelefonoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttelefonoKeyPressed

    private void txtdireccionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdireccionKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdireccionKeyPressed

    private void txtrepre_cedulaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrepre_cedulaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtrepre_cedulaKeyPressed

    private void txtrepre_nombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrepre_nombreKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtrepre_nombreKeyPressed

    private void jCpaisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCpaisActionPerformed
        // TODO add your handling code here:
        if (hab_combo_pais) {
            fk_idtercero_pais = evecomb.getInt_seleccionar_COMBOBOX(conn, jCpais, "idtercero_pais", "nombre", "tercero_pais");
        }
    }//GEN-LAST:event_jCpaisActionPerformed

    private void jCciudadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCciudadActionPerformed
        // TODO add your handling code here:
        if (hab_combo_ciudad) {
            fk_idtercero_ciudad = evecomb.getInt_seleccionar_COMBOBOX(conn, jCciudad, "idtercero_ciudad", "nombre", "tercero_ciudad");
        }
    }//GEN-LAST:event_jCciudadActionPerformed

    private void lblciudadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblciudadMouseClicked
        // TODO add your handling code here:
        evetbl.abrir_TablaJinternal(new FrmTercero_ciudad());
    }//GEN-LAST:event_lblciudadMouseClicked

    private void lblciudadMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblciudadMouseMoved
        // TODO add your handling code here:
        evelbl.evento_MouseMoved(lblciudad);
    }//GEN-LAST:event_lblciudadMouseMoved

    private void lblciudadMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblciudadMouseExited
        // TODO add your handling code here:
        evelbl.evento_MouseExited(lblciudad);
    }//GEN-LAST:event_lblciudadMouseExited

    private void lblpaisMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblpaisMouseMoved
        // TODO add your handling code here:
        evelbl.evento_MouseMoved(lblpais);
    }//GEN-LAST:event_lblpaisMouseMoved

    private void lblpaisMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblpaisMouseExited
        // TODO add your handling code here:
        evelbl.evento_MouseExited(lblpais);
    }//GEN-LAST:event_lblpaisMouseExited

    private void lblpaisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblpaisMouseClicked
        // TODO add your handling code here:
        evetbl.abrir_TablaJinternal(new FrmTercero_pais());
    }//GEN-LAST:event_lblpaisMouseClicked

    private void btnnuevo_item_trActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnuevo_item_trActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnnuevo_item_trActionPerformed

    private void btnguardar_item_trActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardar_item_trActionPerformed
        // TODO add your handling code here:
        boton_guardar_item_tipo_registro();
    }//GEN-LAST:event_btnguardar_item_trActionPerformed

    private void btneditar_item_trActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditar_item_trActionPerformed
        // TODO add your handling code here:
        boton_editar_item_tipo_registro();
    }//GEN-LAST:event_btneditar_item_trActionPerformed

    private void jCtipo_registroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCtipo_registroActionPerformed
        // TODO add your handling code here:
        if (hab_combo_tipo_registro) {
            fk_idtipo_registro = evecomb.getInt_seleccionar_COMBOBOX(conn, jCtipo_registro, "idtipo_registro", "nombre", "tipo_registro");
        }
    }//GEN-LAST:event_jCtipo_registroActionPerformed

    private void jCtipo_dependenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCtipo_dependenciaActionPerformed
        // TODO add your handling code here:
        if (hab_combo_tipo_dependencia) {
            fk_idtipo_dependencia = evecomb.getInt_seleccionar_COMBOBOX(conn, jCtipo_dependencia, "idtipo_dependencia", "nombre", "tipo_dependencia");
        }
    }//GEN-LAST:event_jCtipo_dependenciaActionPerformed

    private void jCtipo_institucionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCtipo_institucionActionPerformed
        // TODO add your handling code here:
        if (hab_combo_tipo_institucion) {
            fk_idtipo_institucion = evecomb.getInt_seleccionar_COMBOBOX(conn, jCtipo_institucion, "idtipo_institucion", "nombre", "tipo_institucion");
        }
    }//GEN-LAST:event_jCtipo_institucionActionPerformed

    private void jCtipo_registroMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCtipo_registroMouseExited
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jCtipo_registroMouseExited

    private void lbltipo_registroMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbltipo_registroMouseExited
        // TODO add your handling code here:
        evelbl.evento_MouseExited(lbltipo_registro);
    }//GEN-LAST:event_lbltipo_registroMouseExited

    private void lbltipo_dependenciaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbltipo_dependenciaMouseExited
        // TODO add your handling code here:
        evelbl.evento_MouseExited(lbltipo_dependencia);
    }//GEN-LAST:event_lbltipo_dependenciaMouseExited

    private void lbltipo_institucionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbltipo_institucionMouseExited
        // TODO add your handling code here:
         evelbl.evento_MouseExited(lbltipo_institucion);
    }//GEN-LAST:event_lbltipo_institucionMouseExited

    private void lbltipo_registroMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbltipo_registroMouseMoved
        // TODO add your handling code here:
        evelbl.evento_MouseMoved(lbltipo_registro);
    }//GEN-LAST:event_lbltipo_registroMouseMoved

    private void lbltipo_dependenciaMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbltipo_dependenciaMouseMoved
        // TODO add your handling code here:
        evelbl.evento_MouseMoved(lbltipo_dependencia);
    }//GEN-LAST:event_lbltipo_dependenciaMouseMoved

    private void lbltipo_institucionMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbltipo_institucionMouseMoved
        // TODO add your handling code here:
        evelbl.evento_MouseMoved(lbltipo_institucion);
    }//GEN-LAST:event_lbltipo_institucionMouseMoved

    private void lbltipo_registroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbltipo_registroMouseClicked
        // TODO add your handling code here:
        evetbl.abrir_TablaJinternal(new FrmTipo_Registro());
    }//GEN-LAST:event_lbltipo_registroMouseClicked

    private void lbltipo_dependenciaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbltipo_dependenciaMouseClicked
        // TODO add your handling code here:
        evetbl.abrir_TablaJinternal(new FrmTipo_dependencia());
    }//GEN-LAST:event_lbltipo_dependenciaMouseClicked

    private void lbltipo_institucionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbltipo_institucionMouseClicked
        // TODO add your handling code here:
        evetbl.abrir_TablaJinternal(new FrmTipo_institucion());
    }//GEN-LAST:event_lbltipo_institucionMouseClicked

    private void txtfecha_estadoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfecha_estadoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtfecha_estado.setText(evefec.getString_validar_fecha(txtfecha_estado.getText()));
            evejtf.saltar_campo_directo(txtfecha_estado, txtfecha_habilitacion);
        }
        
    }//GEN-LAST:event_txtfecha_estadoKeyPressed

    private void txtfecha_habilitacionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfecha_habilitacionKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtfecha_habilitacion.setText(evefec.getString_validar_fecha(txtfecha_habilitacion.getText()));
            evejtf.saltar_campo_directo(txtfecha_habilitacion, txtfecha_vencimiento);
        }
    }//GEN-LAST:event_txtfecha_habilitacionKeyPressed

    private void txtfecha_vencimientoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfecha_vencimientoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtfecha_vencimiento.setText(evefec.getString_validar_fecha(txtfecha_vencimiento.getText()));
            evejtf.saltar_campo_directo(txtfecha_vencimiento, txtnro_habilitacion);
        }
    }//GEN-LAST:event_txtfecha_vencimientoKeyPressed

    private void tblitem_tipo_registroMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblitem_tipo_registroMouseReleased
        // TODO add your handling code here:
        seleccionar_tabla_item_tipo_registro();
    }//GEN-LAST:event_tblitem_tipo_registroMouseReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnabrir_imagen;
    private javax.swing.JButton btndeletar_tercero;
    private javax.swing.JButton btneditar_item_tr;
    private javax.swing.JButton btneditar_tercero;
    private javax.swing.JButton btnguardar_item_tr;
    private javax.swing.JButton btnguardar_tercero;
    private javax.swing.JButton btnnuevo_item_tr;
    private javax.swing.JButton btnnuevo_tercero;
    private javax.swing.ButtonGroup est_reg;
    private javax.swing.JComboBox<String> jCciudad;
    private javax.swing.JCheckBox jCcolaborador;
    private javax.swing.JCheckBox jCdespachante;
    private javax.swing.JCheckBox jCimportador;
    private javax.swing.JComboBox<String> jCpais;
    private javax.swing.JCheckBox jCproveedor;
    private javax.swing.JComboBox<String> jCtipo_dependencia;
    private javax.swing.JComboBox<String> jCtipo_institucion;
    private javax.swing.JComboBox<String> jCtipo_registro;
    private javax.swing.JCheckBox jCtransportadora;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JRadioButton jRest_activo;
    private javax.swing.JRadioButton jRest_desabilitado;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblciudad;
    private javax.swing.JLabel lblformatfech_1;
    private javax.swing.JLabel lblformatfech_2;
    private javax.swing.JLabel lblformatfech_3;
    private javax.swing.JLabel lblpais;
    private javax.swing.JLabel lbltipo_dependencia;
    private javax.swing.JLabel lbltipo_institucion;
    private javax.swing.JLabel lbltipo_registro;
    private javax.swing.JPanel panel_insertar;
    private javax.swing.JPanel panel_tabla;
    private javax.swing.JTable tblitem_tipo_registro;
    private javax.swing.JTable tbltercero;
    private javax.swing.JTextField txtdireccion;
    private javax.swing.JTextField txtfecha_estado;
    private javax.swing.JTextField txtfecha_habilitacion;
    private javax.swing.JTextField txtfecha_vencimiento;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtiditem_tipo_registro;
    private javax.swing.JTextField txtimagen;
    private javax.swing.JTextField txtnombre;
    private javax.swing.JTextField txtnro_habilitacion;
    private javax.swing.JTextField txtrepre_cedula;
    private javax.swing.JTextField txtrepre_nombre;
    private javax.swing.JTextField txtruc;
    private javax.swing.JTextField txttelefono;
    // End of variables declaration//GEN-END:variables
}
