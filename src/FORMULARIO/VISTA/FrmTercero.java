/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FORMULARIO.VISTA;

import BASEDATO.EvenConexion;
import BASEDATO.LOCAL.ConnPostgres;
import Evento.Color.cla_color_palete;
import Evento.Combobox.EvenCombobox;
import Evento.Fecha.EvenFecha;
import Evento.JLabel.EvenJLabel;
import Evento.JTextField.EvenJTextField;
import Evento.Jframe.EvenJFRAME;
import Evento.Jtable.EvenJtable;
import Evento.Mensaje.EvenMensajeJoptionpane;
import Evento.Utilitario.EvenNumero_a_Letra;
import FORMULARIO.BO.*;
import FORMULARIO.DAO.*;
import FORMULARIO.ENTIDAD.*;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
     EvenConexion eveconn = new EvenConexion();
    private tercero ENTter = new tercero();
    private BO_tercero BOter = new BO_tercero();
    private DAO_tercero DAOter = new DAO_tercero();
    private tercero_pais ENTpais = new tercero_pais();
    private DAO_tercero_pais DAOpais = new DAO_tercero_pais();
    private tercero_ciudad ENTciu = new tercero_ciudad();
    private DAO_tercero_ciudad DAOciu = new DAO_tercero_ciudad();
    private tercero_rubro ENTrub = new tercero_rubro();
    private DAO_tercero_rubro DAOrub = new DAO_tercero_rubro();
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
     private saldo_credito_cliente scfina = new saldo_credito_cliente();
    private credito_cliente cfina = new credito_cliente();
    private grupo_credito_cliente gcfina = new grupo_credito_cliente();
    private DAO_grupo_credito_cliente gcfina_dao = new DAO_grupo_credito_cliente();
    private DAO_credito_cliente cfina_dao = new DAO_credito_cliente();
    private DAO_liquidacion_final DAOliqfin = new DAO_liquidacion_final();
    private EvenNumero_a_Letra nroletra = new EvenNumero_a_Letra();
    private entidad_usuario usu = new entidad_usuario();
    EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
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
    private String estado_EMITIDO = "EMITIDO";
    private String estado_ABIERTO = "ABIERTO";
    private boolean hab_combo_rubro;
    private int fk_idtercero_rubro;
    private double saldo_credito;
    private void abrir_formulario() {
        this.setTitle("TERCERO");
        evetbl.centrar_formulario_internalframa(this);        
        reestableser_tercero();
        reestableser_item_tipo_registro();
        DAOter.actualizar_tabla_tercero(conn, tbltercero);
        color_formulario();
        cargar_rubro();
        cargar_pais();
        cargar_ciudad();
        cargar_tipo_registro();
        cargar_tipo_dependencia();
        cargar_tipo_institucion();
        evefec.cargar_combobox_intervalo_fecha(cmbfecha_liquidacion);
        evefec.cargar_combobox_intervalo_fecha(cmbfecha_recibo);
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
    private void cargar_rubro() {
        evecomb.cargarCombobox(conn, jCrubros, "idtercero_rubro", "nombre", "tercero_rubro", "");
        hab_combo_rubro = true;
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
//        if (evejtf.getBoo_JTextField_vacio(txtsaldo_credito, "DEBE CARGAR UNA SALDO O  CERO")) {
//            return false;
//        }
        if(evecomb.getBoo_JCombobox_seleccionar(jCpais,"SELECCIONAR UN PAIS")){
            return false;
        }
        if(evecomb.getBoo_JCombobox_seleccionar(jCciudad,"SELECCIONAR UNA CIUDAD")){
            return false;
        }
        if(evecomb.getBoo_JCombobox_seleccionar(jCrubros,"SELECCIONAR UN RUBRO")){
            return false;
        }
        if(!jCimportador.isSelected() && !jCexportador.isSelected() && !jCdespachante.isSelected() 
                && !jCcolaborador.isSelected()&& !jCproveedor.isSelected()&& !jCtransportadora.isSelected()){
            JOptionPane.showMessageDialog(null,"MARCAR AL MENOS UNA RELACION");
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
        ENTter.setC17saldo_credito(saldo_credito);
        ENTter.setC18fk_idtercero_rubro(fk_idtercero_rubro);
        ENTter.setC19exportador(jCexportador.isSelected());
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
            int idcliente = (eveconn.getInt_ultimoID_mas_uno(conn, ENTter.getTb_tercero(), ENTter.getId_idtercero()));
            int idsaldo_credito_cliente = (eveconn.getInt_ultimoID_mas_uno(conn, scfina.getTb_saldo_credito_cliente(), scfina.getId_idsaldo_credito_cliente()));
            int idgrupo_credito_cliente = (eveconn.getInt_ultimoID_mas_uno(conn, gcfina.getTb_grupo_credito_cliente(), gcfina.getId_idgrupo_credito_cliente()));
            cargar_dato_tercero();
            cargar_saldo_credito_cliente(idcliente);
            cargar_grupo_credito_cliente(idcliente);
            cargar_credito_cliente(idsaldo_credito_cliente, idgrupo_credito_cliente);
            if (BOter.getBoolean_insertar_cliente_con_credito_inicio1(ENTter, scfina, cfina, gcfina)) {
                reestableser_tercero();
                gcfina_dao.actualizar_tabla_grupo_credito_cliente_idc(conn, tblgrupo_credito_cliente, idcliente);
                cfina_dao.actualizar_tabla_credito_cliente_por_grupo(conn, tblcredito_cliente, idgrupo_credito_cliente);
            }
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
        txtnombre_liquidacion.setText(ENTter.getC4nombre());
        txtnombre_recibo.setText(ENTter.getC4nombre());
        txtruc.setText(ENTter.getC5ruc());
        txtruc_liquidacion.setText(ENTter.getC5ruc());
        txtruc_recibo.setText(ENTter.getC5ruc());
        txttelefono.setText(ENTter.getC6telefono());
        txtdireccion.setText(ENTter.getC7direccion());
        txtrepre_nombre.setText(ENTter.getC8representante_nombre());
        txtrepre_cedula.setText(ENTter.getC9representante_cedula());
        jCimportador.setSelected(ENTter.getC10importador());
        jCdespachante.setSelected(ENTter.getC11despachante());
        jCcolaborador.setSelected(ENTter.getC12colaborador());
        jCproveedor.setSelected(ENTter.getC13proveedor());
        jCtransportadora.setSelected(ENTter.getC14transportadora());
        jCexportador.setSelected(ENTter.getC19exportador());
        fk_idtercero_pais=ENTter.getC15fk_idtercero_pais();
        fk_idtercero_ciudad=ENTter.getC16fk_idtercero_ciudad();
        fk_idtercero_rubro=ENTter.getC18fk_idtercero_rubro();
        saldo_credito=(ENTter.getC17saldo_credito());
        jFsaldo_credito.setValue(ENTter.getC17saldo_credito());
        suma_liquidacion("");
        suma_recibo("");
        DAOpais.cargar_tercero_pais(conn, ENTpais,fk_idtercero_pais);
        evecomb.setSeleccionarCombobox(jCpais, ENTpais.getC1idtercero_pais(), ENTpais.getC2nombre());
        DAOciu.cargar_tercero_ciudad(conn, ENTciu,fk_idtercero_ciudad);
        evecomb.setSeleccionarCombobox(jCciudad, ENTciu.getC1idtercero_ciudad(), ENTciu.getC2nombre());
        DAOrub.cargar_tercero_rubro(conn, ENTrub, fk_idtercero_rubro);
        evecomb.setSeleccionarCombobox(jCrubros, ENTrub.getC1idtercero_rubro(), ENTrub.getC2nombre());
        gcfina_dao.actualizar_tabla_grupo_credito_cliente_idc(conn, tblgrupo_credito_cliente, fk_idtercero);
        gcfina_dao.cargar_grupo_credito_cliente_id(conn, gcfina, fk_idtercero);
        cfina_dao.actualizar_tabla_credito_cliente_por_grupo(conn, tblcredito_cliente, gcfina.getC1idgrupo_credito_cliente());
        btnguardar_tercero.setEnabled(false);
        btneditar_tercero.setEnabled(true);
    }
    void suma_liquidacion(String fecha){
        DAOter.actualizar_tabla_tercero_liquidacion(conn, tblliquidacion_final, fk_idtercero,fecha);
        double suma_saldo=eveJtab.getDouble_sumar_tabla(tblliquidacion_final, 9);
        double suma_pagar=eveJtab.getDouble_sumar_tabla(tblliquidacion_final, 10);
        double suma_pagado=eveJtab.getDouble_sumar_tabla(tblliquidacion_final, 11);
        jFsuma_saldo.setValue(suma_saldo);
        jFsuma_pagar.setValue(suma_pagar);
        jFsuma_pagado.setValue(suma_pagado);
    }
    void suma_recibo(String fecha){
        DAOter.actualizar_tabla_tercero_recibo(conn, tblrecibo, fk_idtercero,fecha);
        double suma_recibo=eveJtab.getDouble_sumar_tabla(tblrecibo,5);
        jFsuma_recibo.setValue(suma_recibo);
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
         jCexportador.setSelected(false);
        jFsaldo_credito.setValue(0);
        fk_idtercero_pais=0;
        fk_idtercero_ciudad=0;
        jCpais.setSelectedIndex(0);
        jCciudad.setSelectedIndex(0);
        jCrubros.setSelectedIndex(0);
        DAOter.actualizar_tabla_tercero(conn, tbltercero);
        jFsaldo_credito_total.setValue(DAOter.getDouble_sumar_monto_credito_cliente(conn));
        btnguardar_tercero.setEnabled(true);
        btneditar_tercero.setEnabled(false);
        btndeletar_tercero.setEnabled(false);
        txtnombre.grabFocus();
    }
    private void cargar_saldo_credito_cliente(int idcliente) {
        scfina.setC3descripcion("CREDITO DE CLIENTE DE INICIO");
        scfina.setC4monto_saldo_credito(ENTter.getC17saldo_credito());//cambiar
        String Ssaldo_credito=String.valueOf((int)saldo_credito);
        scfina.setC5monto_letra(nroletra.Convertir(Ssaldo_credito, true));
        scfina.setC6estado(estado_EMITIDO);
        scfina.setC7fk_idcliente(idcliente);
        scfina.setC8fk_idusuario(usu.getGlobal_idusuario());
    }
    private void cargar_grupo_credito_cliente(int idcliente) {
        gcfina.setC4estado(estado_ABIERTO);
        gcfina.setC5fk_idcliente(idcliente);
    }
    private void cargar_credito_cliente(int idsaldo_credito_cliente, int idgrupo_credito_cliente) {
        cfina.setC3descripcion("CREDITO DE CLIENTE DE INICIO");
        cfina.setC4estado(estado_EMITIDO);
        cfina.setC5monto_contado(0);
        cfina.setC6monto_credito(ENTter.getC17saldo_credito());//cambiar clie.getC13saldo_credito()
        cfina.setC7tabla_origen("CLIENTE");
        cfina.setC8fk_idgrupo_credito_cliente(idgrupo_credito_cliente);
        cfina.setC11fk_idventa_alquiler(0);
        cfina.setC10fk_idrecibo_pago_cliente(0);
        cfina.setC9fk_idsaldo_credito_cliente(idsaldo_credito_cliente);
        cfina.setC12vence(false);
        cfina.setC13fecha_vence(evefec.getString_formato_fecha_hora_zona());
    }
     private void boton_guardar_credito_inicio() {
        if (tblgrupo_credito_cliente.getRowCount() == 0) {
            int idcliente = eveJtab.getInt_select_id(tbltercero);
            int idsaldo_credito_cliente = (eveconn.getInt_ultimoID_mas_uno(conn, scfina.getTb_saldo_credito_cliente(), scfina.getId_idsaldo_credito_cliente()));
            int idgrupo_credito_cliente = (eveconn.getInt_ultimoID_mas_uno(conn, gcfina.getTb_grupo_credito_cliente(), gcfina.getId_idgrupo_credito_cliente()));
            cargar_saldo_credito_cliente(idcliente);
            cargar_grupo_credito_cliente(idcliente);
            cargar_credito_cliente(idsaldo_credito_cliente, idgrupo_credito_cliente);
            if (BOter.getBoolean_insertar_credito_inicio1(scfina, cfina, gcfina)) {
                gcfina_dao.actualizar_tabla_grupo_credito_cliente_idc(conn, tblgrupo_credito_cliente, idcliente);
                cfina_dao.actualizar_tabla_credito_cliente_por_grupo(conn, tblcredito_cliente, idgrupo_credito_cliente);
                JOptionPane.showMessageDialog(null, "CREDITO GRUPO INICIADO ");
            }
        } else {
            JOptionPane.showMessageDialog(null, "YA TIENE CREDITO GRUPO INICIADO ");
        }
    }
      private void cargar_credito_cliente() {
        if (!eveJtab.getBoolean_validar_select(tblgrupo_credito_cliente)) {
            int idgrupo_credito_cliente = eveJtab.getInt_select_id(tblgrupo_credito_cliente);
            cfina_dao.actualizar_tabla_credito_cliente_por_grupo(conn, tblcredito_cliente, idgrupo_credito_cliente);
        }
    }
     
      private void actualizar_todo() {
        DAOter.actualizar_tabla_tercero(conn, tbltercero);
//        sumar_monto_credito_cliente();
    }
      private void boton_imprimir_recibo() {
//        if (dao_usu.getBoolean_hab_evento_mensaje_error(conn, "24")) {
            if (!eveJtab.getBoolean_validar_select(tblrecibo)) {
                int idrecibo=eveJtab.getInt_select_id(tblrecibo);
                DAOter.imprimir_rep_recibo(conn,idrecibo);
            }
//        }
    }
      private void boton_imprimir_liquidacion_final() {
//        if (dao_usu.getBoolean_hab_evento_mensaje_error(conn, "24")) {
            if (!eveJtab.getBoolean_validar_select(tblliquidacion_final)) {
                int idliquidacion_final_select=eveJtab.getInt_select_id(tblliquidacion_final);
                DAOliqfin.imprimir_rep_liquidacion_final(conn, idliquidacion_final_select);
            }
//        }
    }
      private void boton_imprimir_cuenta_liquidacion_final() {
//        if (dao_usu.getBoolean_hab_evento_mensaje_error(conn, "24")) {
            if (!eveJtab.getBoolean_validar_select(tbltercero)) {
                fk_idtercero=eveJtab.getInt_select_id(tbltercero);
                String fecha=evefec.getIntervalo_fecha_combobox(cmbfecha_liquidacion," lf.fecha_despacho ");
                DAOliqfin.imprimir_rep_cuenta_liquidacion(conn, fk_idtercero,fecha);
            }
//        }
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
        lblrubro = new javax.swing.JLabel();
        jCrubros = new javax.swing.JComboBox<>();
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
        jCexportador = new javax.swing.JCheckBox();
        jLabel14 = new javax.swing.JLabel();
        jFsaldo_credito = new javax.swing.JFormattedTextField();
        panel_tabla = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbltercero = new javax.swing.JTable();
        btnpagar_credito = new javax.swing.JButton();
        btnactualizar_tabla = new javax.swing.JButton();
        btncrearcredito_inicio = new javax.swing.JButton();
        jFsaldo_credito_total = new javax.swing.JFormattedTextField();
        btnimprimir_cuenta1 = new javax.swing.JButton();
        btngenerar_credito = new javax.swing.JButton();
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
        jPanel7 = new javax.swing.JPanel();
        panel_tabla_cliente1 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblgrupo_credito_cliente = new javax.swing.JTable();
        panel_tabla_cliente2 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblcredito_cliente = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblliquidacion_final = new javax.swing.JTable();
        jFsuma_saldo = new javax.swing.JFormattedTextField();
        jLabel16 = new javax.swing.JLabel();
        txtnombre_liquidacion = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtruc_liquidacion = new javax.swing.JTextField();
        jFsuma_pagado = new javax.swing.JFormattedTextField();
        jFsuma_pagar = new javax.swing.JFormattedTextField();
        jLabel18 = new javax.swing.JLabel();
        cmbfecha_liquidacion = new javax.swing.JComboBox<>();
        btnimprimir_liquidacion = new javax.swing.JButton();
        btnimprimir_cuenta = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblrecibo = new javax.swing.JTable();
        jFsuma_recibo = new javax.swing.JFormattedTextField();
        jLabel19 = new javax.swing.JLabel();
        txtnombre_recibo = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtruc_recibo = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        cmbfecha_recibo = new javax.swing.JComboBox<>();
        btnimprimir_recibo = new javax.swing.JButton();

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

        lblrubro.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblrubro.setText("RUBRO:");
        lblrubro.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblrubroMouseMoved(evt);
            }
        });
        lblrubro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblrubroMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblrubroMouseExited(evt);
            }
        });

        jCrubros.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jCrubros.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jCrubros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCrubrosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel5)
                        .addComponent(jLabel4)
                        .addComponent(jLabel3)
                        .addComponent(jLabel2)
                        .addComponent(lblpais, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(lblrubro))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtdireccion, javax.swing.GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE)
                    .addComponent(txttelefono, javax.swing.GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE)
                    .addComponent(txtruc, javax.swing.GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE)
                    .addComponent(txtnombre, javax.swing.GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jCpais, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblciudad)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCciudad, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jCrubros, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCrubros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblrubro))
                .addGap(0, 16, Short.MAX_VALUE))
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

        jCimportador.setText("IMPORTADOR");

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

        jCexportador.setText("EXPORTADOR");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCexportador)
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
                .addComponent(jCexportador)
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

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel14.setText("SALDO DE CREDITO:");

        jFsaldo_credito.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFsaldo_credito.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        javax.swing.GroupLayout panel_insertarLayout = new javax.swing.GroupLayout(panel_insertar);
        panel_insertar.setLayout(panel_insertarLayout);
        panel_insertarLayout.setHorizontalGroup(
            panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_insertarLayout.createSequentialGroup()
                .addGroup(panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_insertarLayout.createSequentialGroup()
                        .addGroup(panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFsaldo_credito, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(108, Short.MAX_VALUE))
        );
        panel_insertarLayout.setVerticalGroup(
            panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_insertarLayout.createSequentialGroup()
                .addGroup(panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panel_insertarLayout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(41, 41, 41))
                    .addGroup(panel_insertarLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jFsaldo_credito, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
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

        btnpagar_credito.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnpagar_credito.setText("PAGAR CREDITO");
        btnpagar_credito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnpagar_creditoActionPerformed(evt);
            }
        });

        btnactualizar_tabla.setText("ACTUALIZAR TABLA");
        btnactualizar_tabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnactualizar_tablaActionPerformed(evt);
            }
        });

        btncrearcredito_inicio.setText("CREAR CREDITO DE INICIO");
        btncrearcredito_inicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncrearcredito_inicioActionPerformed(evt);
            }
        });

        jFsaldo_credito_total.setBorder(javax.swing.BorderFactory.createTitledBorder("SALDO TOTAL:"));
        jFsaldo_credito_total.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0 Gs"))));
        jFsaldo_credito_total.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFsaldo_credito_total.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        btnimprimir_cuenta1.setText("IMP. CUENTA");
        btnimprimir_cuenta1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnimprimir_cuenta1ActionPerformed(evt);
            }
        });

        btngenerar_credito.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btngenerar_credito.setText("GENERAR CREDITO");
        btngenerar_credito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btngenerar_creditoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_tablaLayout = new javax.swing.GroupLayout(panel_tabla);
        panel_tabla.setLayout(panel_tablaLayout);
        panel_tablaLayout.setHorizontalGroup(
            panel_tablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(panel_tablaLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(panel_tablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnpagar_credito, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnactualizar_tabla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_tablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnimprimir_cuenta1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btncrearcredito_inicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btngenerar_credito, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(212, 212, 212)
                .addComponent(jFsaldo_credito_total, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panel_tablaLayout.setVerticalGroup(
            panel_tablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_tablaLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_tablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_tablaLayout.createSequentialGroup()
                        .addGroup(panel_tablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnpagar_credito)
                            .addComponent(btncrearcredito_inicio, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                            .addComponent(btngenerar_credito))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_tablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnactualizar_tabla, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnimprimir_cuenta1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panel_tablaLayout.createSequentialGroup()
                        .addComponent(jFsaldo_credito_total, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 587, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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

        panel_tabla_cliente1.setBackground(new java.awt.Color(51, 204, 255));
        panel_tabla_cliente1.setBorder(javax.swing.BorderFactory.createTitledBorder("GRUPO CREDITO FINANZA"));

        tblgrupo_credito_cliente.setModel(new javax.swing.table.DefaultTableModel(
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
        tblgrupo_credito_cliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblgrupo_credito_clienteMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblgrupo_credito_clienteMouseReleased(evt);
            }
        });
        jScrollPane5.setViewportView(tblgrupo_credito_cliente);

        javax.swing.GroupLayout panel_tabla_cliente1Layout = new javax.swing.GroupLayout(panel_tabla_cliente1);
        panel_tabla_cliente1.setLayout(panel_tabla_cliente1Layout);
        panel_tabla_cliente1Layout.setHorizontalGroup(
            panel_tabla_cliente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 951, Short.MAX_VALUE)
        );
        panel_tabla_cliente1Layout.setVerticalGroup(
            panel_tabla_cliente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        panel_tabla_cliente2.setBackground(new java.awt.Color(51, 204, 255));
        panel_tabla_cliente2.setBorder(javax.swing.BorderFactory.createTitledBorder("CREDITO FINANZA"));

        tblcredito_cliente.setModel(new javax.swing.table.DefaultTableModel(
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
        tblcredito_cliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblcredito_clienteMouseReleased(evt);
            }
        });
        jScrollPane6.setViewportView(tblcredito_cliente);

        javax.swing.GroupLayout panel_tabla_cliente2Layout = new javax.swing.GroupLayout(panel_tabla_cliente2);
        panel_tabla_cliente2.setLayout(panel_tabla_cliente2Layout);
        panel_tabla_cliente2Layout.setHorizontalGroup(
            panel_tabla_cliente2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6)
        );
        panel_tabla_cliente2Layout.setVerticalGroup(
            panel_tabla_cliente2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_tabla_cliente1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panel_tabla_cliente2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(panel_tabla_cliente1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_tabla_cliente2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("GRUPO CREDITO", jPanel7);

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("LIQUIDACION FINAL"));

        tblliquidacion_final.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(tblliquidacion_final);

        jFsuma_saldo.setEditable(false);
        jFsuma_saldo.setBorder(javax.swing.BorderFactory.createTitledBorder("Suma Saldo:"));
        jFsuma_saldo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0 Gs"))));
        jFsuma_saldo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFsuma_saldo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel16.setText("NOMBRE:");

        txtnombre_liquidacion.setEditable(false);
        txtnombre_liquidacion.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel17.setText("RUC:");

        txtruc_liquidacion.setEditable(false);
        txtruc_liquidacion.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jFsuma_pagado.setEditable(false);
        jFsuma_pagado.setBorder(javax.swing.BorderFactory.createTitledBorder("Suma Pagado:"));
        jFsuma_pagado.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0 Gs"))));
        jFsuma_pagado.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFsuma_pagado.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jFsuma_pagar.setEditable(false);
        jFsuma_pagar.setBorder(javax.swing.BorderFactory.createTitledBorder("Suma Pagar:"));
        jFsuma_pagar.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0 Gs"))));
        jFsuma_pagar.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFsuma_pagar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel18.setText("Fecha:");

        cmbfecha_liquidacion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbfecha_liquidacionItemStateChanged(evt);
            }
        });

        btnimprimir_liquidacion.setText("IMP. LIQUIDACION");
        btnimprimir_liquidacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnimprimir_liquidacionActionPerformed(evt);
            }
        });

        btnimprimir_cuenta.setText("IMP. CUENTA");
        btnimprimir_cuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnimprimir_cuentaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 951, Short.MAX_VALUE)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtnombre_liquidacion, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtruc_liquidacion, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbfecha_liquidacion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addComponent(btnimprimir_liquidacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnimprimir_cuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jFsuma_pagar, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jFsuma_pagado, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jFsuma_saldo, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel16)
                    .addComponent(txtnombre_liquidacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(txtruc_liquidacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel18)
                        .addComponent(cmbfecha_liquidacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnimprimir_cuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnimprimir_liquidacion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFsuma_pagar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFsuma_pagado, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFsuma_saldo, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("TABLA LIQUIDACION", jPanel8);

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder("RECIBO"));

        tblrecibo.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(tblrecibo);

        jFsuma_recibo.setEditable(false);
        jFsuma_recibo.setBorder(javax.swing.BorderFactory.createTitledBorder("Suma Recibo:"));
        jFsuma_recibo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0 Gs"))));
        jFsuma_recibo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFsuma_recibo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel19.setText("NOMBRE:");

        txtnombre_recibo.setEditable(false);
        txtnombre_recibo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel20.setText("RUC:");

        txtruc_recibo.setEditable(false);
        txtruc_recibo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel15.setText("Fecha:");

        cmbfecha_recibo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbfecha_reciboItemStateChanged(evt);
            }
        });

        btnimprimir_recibo.setText("IMPRIMIR");
        btnimprimir_recibo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnimprimir_reciboActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 951, Short.MAX_VALUE)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtnombre_recibo, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtruc_recibo, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbfecha_recibo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnimprimir_recibo, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFsuma_recibo, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel19)
                    .addComponent(txtnombre_recibo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(txtruc_recibo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15)
                        .addComponent(cmbfecha_recibo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnimprimir_recibo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFsuma_recibo, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("TABLA RECIBO", jPanel10);

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

    private void tblgrupo_credito_clienteMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblgrupo_credito_clienteMousePressed
        // TODO add your handling code here:
        cargar_credito_cliente();
    }//GEN-LAST:event_tblgrupo_credito_clienteMousePressed

    private void tblgrupo_credito_clienteMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblgrupo_credito_clienteMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tblgrupo_credito_clienteMouseReleased

    private void tblcredito_clienteMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblcredito_clienteMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tblcredito_clienteMouseReleased

    private void btnpagar_creditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnpagar_creditoActionPerformed
        // TODO add your handling code here:
        if (tbltercero.getSelectedRow() >= 0) {
            evetbl.abrir_TablaJinternal(new FrmRecibo_pago_tercero());
        }
    }//GEN-LAST:event_btnpagar_creditoActionPerformed

    private void btnactualizar_tablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnactualizar_tablaActionPerformed
        // TODO add your handling code here:
        actualizar_todo();
    }//GEN-LAST:event_btnactualizar_tablaActionPerformed

    private void btncrearcredito_inicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncrearcredito_inicioActionPerformed
        // TODO add your handling code here:
        boton_guardar_credito_inicio();
    }//GEN-LAST:event_btncrearcredito_inicioActionPerformed

    private void lblrubroMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblrubroMouseMoved
        // TODO add your handling code here:
        evelbl.evento_MouseMoved(lblrubro);
    }//GEN-LAST:event_lblrubroMouseMoved

    private void lblrubroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblrubroMouseClicked
        // TODO add your handling code here:
         evetbl.abrir_TablaJinternal(new FrmTercero_rubro());
    }//GEN-LAST:event_lblrubroMouseClicked

    private void lblrubroMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblrubroMouseExited
        // TODO add your handling code here:
         evelbl.evento_MouseExited(lblrubro);
    }//GEN-LAST:event_lblrubroMouseExited

    private void jCrubrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCrubrosActionPerformed
        // TODO add your handling code here:
        if (hab_combo_rubro) {
            fk_idtercero_rubro = evecomb.getInt_seleccionar_COMBOBOX(conn, jCrubros, "idtercero_rubro", "nombre", "tercero_rubro");
        }
    }//GEN-LAST:event_jCrubrosActionPerformed

    private void cmbfecha_reciboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbfecha_reciboItemStateChanged
        // TODO add your handling code here:
        String fecha=evefec.getIntervalo_fecha_combobox(cmbfecha_recibo," re.fecha_emision ");
        suma_recibo(fecha);
    }//GEN-LAST:event_cmbfecha_reciboItemStateChanged

    private void cmbfecha_liquidacionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbfecha_liquidacionItemStateChanged
        // TODO add your handling code here:
        String fecha=evefec.getIntervalo_fecha_combobox(cmbfecha_liquidacion," lf.fecha_despacho ");
        suma_liquidacion(fecha);
    }//GEN-LAST:event_cmbfecha_liquidacionItemStateChanged

    private void btnimprimir_reciboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnimprimir_reciboActionPerformed
        // TODO add your handling code here:
        boton_imprimir_recibo();
    }//GEN-LAST:event_btnimprimir_reciboActionPerformed

    private void btnimprimir_liquidacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnimprimir_liquidacionActionPerformed
        // TODO add your handling code here:
        boton_imprimir_liquidacion_final();
    }//GEN-LAST:event_btnimprimir_liquidacionActionPerformed

    private void btnimprimir_cuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnimprimir_cuentaActionPerformed
        // TODO add your handling code here:
        boton_imprimir_cuenta_liquidacion_final();
    }//GEN-LAST:event_btnimprimir_cuentaActionPerformed

    private void btnimprimir_cuenta1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnimprimir_cuenta1ActionPerformed
        // TODO add your handling code here:
        boton_imprimir_cuenta_liquidacion_final();
    }//GEN-LAST:event_btnimprimir_cuenta1ActionPerformed

    private void btngenerar_creditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btngenerar_creditoActionPerformed
        // TODO add your handling code here:
        if (tbltercero.getSelectedRow() >= 0) {
            evetbl.abrir_TablaJinternal(new FrmRecibo_pago_tercero_generar_credito());
        }
    }//GEN-LAST:event_btngenerar_creditoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnabrir_imagen;
    private javax.swing.JButton btnactualizar_tabla;
    private javax.swing.JButton btncrearcredito_inicio;
    private javax.swing.JButton btndeletar_tercero;
    private javax.swing.JButton btneditar_item_tr;
    private javax.swing.JButton btneditar_tercero;
    private javax.swing.JButton btngenerar_credito;
    private javax.swing.JButton btnguardar_item_tr;
    private javax.swing.JButton btnguardar_tercero;
    private javax.swing.JButton btnimprimir_cuenta;
    private javax.swing.JButton btnimprimir_cuenta1;
    private javax.swing.JButton btnimprimir_liquidacion;
    private javax.swing.JButton btnimprimir_recibo;
    private javax.swing.JButton btnnuevo_item_tr;
    private javax.swing.JButton btnnuevo_tercero;
    private javax.swing.JButton btnpagar_credito;
    private javax.swing.JComboBox<String> cmbfecha_liquidacion;
    private javax.swing.JComboBox<String> cmbfecha_recibo;
    private javax.swing.ButtonGroup est_reg;
    private javax.swing.JComboBox<String> jCciudad;
    private javax.swing.JCheckBox jCcolaborador;
    private javax.swing.JCheckBox jCdespachante;
    private javax.swing.JCheckBox jCexportador;
    private javax.swing.JCheckBox jCimportador;
    private javax.swing.JComboBox<String> jCpais;
    private javax.swing.JCheckBox jCproveedor;
    private javax.swing.JComboBox<String> jCrubros;
    private javax.swing.JComboBox<String> jCtipo_dependencia;
    private javax.swing.JComboBox<String> jCtipo_institucion;
    private javax.swing.JComboBox<String> jCtipo_registro;
    private javax.swing.JCheckBox jCtransportadora;
    private javax.swing.JFormattedTextField jFsaldo_credito;
    public static javax.swing.JFormattedTextField jFsaldo_credito_total;
    private javax.swing.JFormattedTextField jFsuma_pagado;
    private javax.swing.JFormattedTextField jFsuma_pagar;
    private javax.swing.JFormattedTextField jFsuma_recibo;
    private javax.swing.JFormattedTextField jFsuma_saldo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRest_activo;
    private javax.swing.JRadioButton jRest_desabilitado;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblciudad;
    private javax.swing.JLabel lblformatfech_1;
    private javax.swing.JLabel lblformatfech_2;
    private javax.swing.JLabel lblformatfech_3;
    private javax.swing.JLabel lblpais;
    private javax.swing.JLabel lblrubro;
    private javax.swing.JLabel lbltipo_dependencia;
    private javax.swing.JLabel lbltipo_institucion;
    private javax.swing.JLabel lbltipo_registro;
    private javax.swing.JPanel panel_insertar;
    private javax.swing.JPanel panel_tabla;
    private javax.swing.JPanel panel_tabla_cliente1;
    private javax.swing.JPanel panel_tabla_cliente2;
    public static javax.swing.JTable tblcredito_cliente;
    public static javax.swing.JTable tblgrupo_credito_cliente;
    private javax.swing.JTable tblitem_tipo_registro;
    private javax.swing.JTable tblliquidacion_final;
    private javax.swing.JTable tblrecibo;
    public static javax.swing.JTable tbltercero;
    private javax.swing.JTextField txtdireccion;
    private javax.swing.JTextField txtfecha_estado;
    private javax.swing.JTextField txtfecha_habilitacion;
    private javax.swing.JTextField txtfecha_vencimiento;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtiditem_tipo_registro;
    private javax.swing.JTextField txtimagen;
    private javax.swing.JTextField txtnombre;
    private javax.swing.JTextField txtnombre_liquidacion;
    private javax.swing.JTextField txtnombre_recibo;
    private javax.swing.JTextField txtnro_habilitacion;
    private javax.swing.JTextField txtrepre_cedula;
    private javax.swing.JTextField txtrepre_nombre;
    private javax.swing.JTextField txtruc;
    private javax.swing.JTextField txtruc_liquidacion;
    private javax.swing.JTextField txtruc_recibo;
    private javax.swing.JTextField txttelefono;
    // End of variables declaration//GEN-END:variables
}
