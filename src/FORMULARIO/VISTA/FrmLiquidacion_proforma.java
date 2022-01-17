/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FORMULARIO.VISTA;

import BASEDATO.EvenConexion;
import BASEDATO.LOCAL.ConnPostgres;
import Evento.Color.cla_color_palete;
import Evento.Combobox.CargaDirectoCombobox;
import Evento.Combobox.EvenCombobox;
import Evento.Fecha.EvenFecha;
import Evento.Imagen.EventoImagen;
import Evento.JFormattedTextField.EvenJFormattedTextField;
import Evento.JTextField.EvenJTextField;
import Evento.JasperReport.EvenJasperReport;
import Evento.Jframe.EvenJFRAME;
import Evento.Jtable.EvenJtable;
import Evento.Mensaje.EvenMensajeJoptionpane;
import FORMULARIO.BO.*;
import FORMULARIO.DAO.*;
import FORMULARIO.ENTIDAD.*;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

/**
 *
 * @author Digno
 */
public class FrmLiquidacion_proforma extends javax.swing.JInternalFrame {

    EvenJFRAME evetbl = new EvenJFRAME();
    EvenJtable eveJtab = new EvenJtable();
    EvenCombobox evecomb = new EvenCombobox();
    EventoImagen eveimg = new EventoImagen();
    EvenConexion eveconn = new EvenConexion();
    EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
    EvenJasperReport rep = new EvenJasperReport();
    CargaDirectoCombobox carcbm=new CargaDirectoCombobox();
    private honorario_despacho ENThd = new honorario_despacho();
    private DAO_honorario_despacho DAOhd = new DAO_honorario_despacho();
    private entidad_usuario ENTusu = new entidad_usuario();
    private tercero ENTter = new tercero();
    private DAO_tercero DAOter = new DAO_tercero();
    private aduana ENTadu = new aduana();
    private DAO_aduana DAOadu = new DAO_aduana();
    private liquidacion_proforma ENTlp = new liquidacion_proforma();
    private BO_liquidacion_proforma BOlp = new BO_liquidacion_proforma();
    private DAO_liquidacion_proforma DAOlp = new DAO_liquidacion_proforma();
    private moneda_cambio ENTmc = new moneda_cambio();
    private DAO_moneda_cambio DAOmc = new DAO_moneda_cambio();
    private EvenJTextField evejtf = new EvenJTextField();
    private item_comprobante itemcom = new item_comprobante();
    private EvenJFormattedTextField evejfor = new EvenJFormattedTextField();
    private DefaultTableModel model_item_con_comprobante = new DefaultTableModel();
    private DefaultTableModel model_item_sin_comprobante = new DefaultTableModel();
    private DefaultTableModel model_item_bole_despa = new DefaultTableModel();
    private DefaultTableModel model_item_mercaderia = new DefaultTableModel();
    private DefaultTableModel model_fact_nro_proforma = new DefaultTableModel();
    private DAO_tipo_tasa_cambio ttcam_dao = new DAO_tipo_tasa_cambio();
    private EvenFecha evefec = new EvenFecha();
    private dao_usuario dao_usu = new dao_usuario();//dao_usuario
    Connection conn = ConnPostgres.getConnPosgres();
    private cla_color_palete clacolor = new cla_color_palete();
    private int idliquidacion_proforma;
    private String creado_por = ENTusu.getGlobal_idusuario() + "-" + ENTusu.getGlobal_nombre();
    private boolean hab_cargar_tercero_importador;
    private boolean hab_cargar_tercero_despachante;
    private boolean hab_cargar_tercero_transportadora;
    private boolean hab_cargar_tercero_colaborador;
    private int fk_idtercero_importador;
    private int fk_idtercero_transportadora;
    private int fk_idtercero_colaborador;
    private int fk_idtercero_despachante;
    private boolean hab_cargar_aduana;
    private int fk_idaduana;
    private double monto_factura;
    private double monto_sin_comprobante;
    private double monto_con_comprobante;
    private double tasa_cambio_aduana;
    private double tasa_cambio_mercado;
    private String estado_emitido = "EMITIDO";
    private String estado_anulado = "ANULADO";
    private boolean hab_cargar_tc_con_comprobante;
    private int fk_idtc_con_comprobante;
    private int fk_idtc_sin_comprobante;
    private boolean hab_cargar_tc_sin_comprobante;
    private int fk_idtc_bole_despa;
    private double monto_bole_despa;
    private String ruta_imagen = "SIN-IMAGEN";
    private boolean hab_cargar_tc_bole_despa;
    private String referencia = "SIN-REF.";
    private boolean hab_cargar_tc_mercaderia;
    private int fk_idtc_mercaderia;
    private boolean hab_cargar_moneda_cambio;
    private int fk_idmoneda_cambio;
    private int fk_idhonorario_despacho;
    private double monto_honorario_despachante;
    private String tipo_tasa_cambio;
    private String liquidacion_impor = "IMPORTACION";
    private String liquidacion_espor = "EXPORTACION";
    private String tipo_archivo_abrir = "pdf";

    private void abrir_formulario() {
        this.setTitle("LIQUIDACION PROFORMA");
        evetbl.centrar_formulario_internalframa(this);
        carcbm.cargarCombobox_via_transporte(cmbvia_transporte);
        carcbm.cargarCombobox_modo_transporte(cmbcontenedor_tipo);
        reestableser_liquidacion_proforma();
        reestableser_item_tc_con_comprobante();
        reestableser_item_tc_sin_comprobante();
        reestableser_item_tc_bole_despa();
        reestableser_item_tc_mercaderia();
        reestableser_fact_nro_proforma();
        DAOlp.actualizar_tabla_liquidacion_proforma(conn, tblliquidacion_proforma);
        color_formulario();
        cargar_tercero_importador();
        cargar_tercero_despachante();
        cargar_tercero_transportadora();
        cargar_tercero_colaborador();
        cargar_aduana();
        cargar_ultimo_honorario_despacho();
        cargar_tc_con_comprobante();
        cargar_tc_sin_comprobante();
        cargar_tc_bole_despa();
        cargar_tc_mercaderia();
        cargar_moneda_cambio();
        crear_item_tc_con_comprobante();
        crear_item_tc_sin_comprobante();
        crear_item_tc_bole_despa();
        crear_item_tc_mercaderia();
        crear_fact_nro_proforma();
    }

    private void color_formulario() {
        panel_filtro.setBackground(clacolor.getColor_tabla());
        panel_insertar.setBackground(clacolor.getColor_insertar_primario());
    }

    private boolean validar_guardar_liquidacion_proforma() {
        if (evejtf.getBoo_JTextField_vacio(txtlp_fecha_despacho, "DEBE CARGAR UNA FECHA DESPACHO")) {
            return false;
        }
        if (evejtf.getBoo_JTextField_vacio(txtlp_contenedor_nro, "DEBE CARGAR UN CONTENEDOR NUMERO")) {
            return false;
        }
        if (evejtf.getBoo_JTextField_vacio(txtlp_tasa_cambio_aduana, "DEBE CARGAR TASA DE CAMBIO DE ADUANA")) {
            return false;
        }
        if (evejtf.getBoo_JTextField_vacio(txtlp_tasa_cambio_mercado, "DEBE CARGAR TASA DE CAMBIO DEL MERCADO")) {
            return false;
        }
        if (evejtf.getBoo_JTextarea_vacio(txtlp_observacion, "DEBE CARGAR UNA OBSERVACION")) {
            return false;
        }
        if (evecomb.getBoo_JCombobox_seleccionar(cmbvia_transporte, "SELECCIONE UNA VIA DE TRANSPORTE")) {
            return false;
        }
        if (evecomb.getBoo_JCombobox_seleccionar(cmbaduana, "SELECCIONE UNA ADUANA")) {
            return false;
        }
        if (evecomb.getBoo_JCombobox_seleccionar(cmbcontenedor_tipo, "SELECCIONE UN TIPO DE CONTENEDOR")) {
            return false;
        }
        if (evecomb.getBoo_JCombobox_seleccionar(cmbtercero_importador, "SELECCIONE UN IMPORTADOR")) {
            eveJtab.mostrar_JTabbedPane(jTab_item, 0);
            return false;
        }
        if (evecomb.getBoo_JCombobox_seleccionar(cmbtercero_transportadora, "SELECCIONE UN TRANSPORTADOR")) {
            eveJtab.mostrar_JTabbedPane(jTab_item, 0);
            return false;
        }
        if (evecomb.getBoo_JCombobox_seleccionar(cmbtercero_colaborador, "SELECCIONE UN HECHO POR")) {
            eveJtab.mostrar_JTabbedPane(jTab_item, 0);
            return false;
        }
        if (evecomb.getBoo_JCombobox_seleccionar(cmbtercero_despachante, "SELECCIONE UN DESPACHANTE")) {
            eveJtab.mostrar_JTabbedPane(jTab_item, 0);
            return false;
        }
        if (eveJtab.getBoolean_validar_cant_cargado(tblitem_con_comprobante)) {
            eveJtab.mostrar_JTabbedPane(jTab_item, 1);
            return false;
        }
        if (eveJtab.getBoolean_validar_cant_cargado(tblitem_sin_comprobante)) {
            eveJtab.mostrar_JTabbedPane(jTab_item, 2);
            return false;
        }
        if (eveJtab.getBoolean_validar_cant_cargado(tblitem_bole_despa)) {
            eveJtab.mostrar_JTabbedPane(jTab_item, 3);
            return false;
        }
        return true;
    }

    private String gettipo_liquidacion() {
        String liquidacion = "error";
        if (jRimportacion.isSelected()) {
            liquidacion = liquidacion_impor;
        }
        if (jRexportacion.isSelected()) {
            liquidacion = liquidacion_espor;
        }
        return liquidacion;
    }

    private void boton_guardar_liquidacion_proforma() {
        if (validar_guardar_liquidacion_proforma()) {
            ENTlp.setC3creado_por(creado_por);
            ENTlp.setC4fecha_despacho(evefec.getString_validar_fecha(txtlp_fecha_despacho.getText()));
            ENTlp.setC5contenedor_nro(txtlp_contenedor_nro.getText());
            ENTlp.setC6contenedor_tipo(cmbcontenedor_tipo.getSelectedItem().toString());
            ENTlp.setC7via_transporte(cmbvia_transporte.getSelectedItem().toString());
            ENTlp.setC8monto_factura(monto_factura);
            ENTlp.setC9monto_sin_comprobante(monto_sin_comprobante);
            ENTlp.setC10monto_con_comprobante(monto_con_comprobante);
            ENTlp.setC11tasa_cambio_aduana(evejtf.getDouble_format_nro_entero(txtlp_tasa_cambio_aduana.getText()));
            ENTlp.setC12tasa_cambio_mercado(evejtf.getDouble_format_nro_entero(txtlp_tasa_cambio_mercado.getText()));
            ENTlp.setC13estado(estado_emitido);
            ENTlp.setC14fk_idtercero_importador(fk_idtercero_importador);
            ENTlp.setC15fk_idtercero_despachante(fk_idtercero_despachante);
            ENTlp.setC16fk_idtercero_colaborador(fk_idtercero_colaborador);
            ENTlp.setC17fk_idtercero_transportadora(fk_idtercero_transportadora);
            ENTlp.setC18fk_idaduana(fk_idaduana);
            ENTlp.setC19observacion(txtlp_observacion.getText());
            ENTlp.setC20tipo_tasa_cambio(tipo_tasa_cambio);
            ENTlp.setC21tipo_liquidacion(gettipo_liquidacion());
            ENTlp.setC22fk_idhonorario_despacho(fk_idhonorario_despacho);
            ENTlp.setC23monto_boleto_despachante(monto_bole_despa);
            ENTlp.setC24monto_honorario_despachante(monto_honorario_despachante);
            ENTlp.setC25fk_idmoneda_cambio(fk_idmoneda_cambio);
            BOlp.insertar_liquidacion_proforma(ENTlp, tblitem_con_comprobante, tblitem_sin_comprobante,
                    tblitem_bole_despa, tblitem_mercaderia, tblfact_nro_proforma);
            DAOlp.actualizar_tabla_liquidacion_proforma(conn, tblliquidacion_proforma);
            reestableser_liquidacion_proforma();
        }
    }

    private void boton_anular_liquidacion_proforma() {
        if (dao_usu.getBoolean_hab_evento_mensaje_error(conn, "22")) {
            if (!eveJtab.getBoolean_validar_select(tblliquidacion_proforma)) {
                ENTlp.setC13estado(estado_anulado);
                ENTlp.setC1idliquidacion_proforma(idliquidacion_proforma);
                BOlp.update_liquidacion_proforma_ANULAR(ENTlp);
                DAOlp.actualizar_tabla_liquidacion_proforma(conn, tblliquidacion_proforma);
            }
        }
    }

    private void boton_recargar_liquidacion_proforma() {
        if (dao_usu.getBoolean_hab_evento_mensaje_error(conn, "23")) {
            if (!eveJtab.getBoolean_validar_select(tblliquidacion_proforma)) {
                reestableser_liquidacion_proforma();
                DAOlp.cargar_liquidacion_proforma(conn, ENTlp, idliquidacion_proforma);
                txtlp_fecha_despacho.setText(ENTlp.getC4fecha_despacho());
                txtlp_observacion.setText(ENTlp.getC19observacion());
                if (ENTlp.getC21tipo_liquidacion().equals(liquidacion_impor)) {
                    jRimportacion.setSelected(true);
                }
                if (ENTlp.getC21tipo_liquidacion().equals(liquidacion_espor)) {
                    jRexportacion.setSelected(true);
                }
                txtlp_contenedor_nro.setText(ENTlp.getC5contenedor_nro());
                cmbcontenedor_tipo.setSelectedItem(ENTlp.getC6contenedor_tipo());
                fk_idmoneda_cambio = ENTlp.getC25fk_idmoneda_cambio();
                cargar_dato_select_moneda_cambio(fk_idmoneda_cambio, true);
                cmbvia_transporte.setSelectedItem(ENTlp.getC7via_transporte());
                fk_idaduana = ENTlp.getC18fk_idaduana();
                DAOadu.cargar_aduana(conn, ENTadu, fk_idaduana);
                cmbaduana.setSelectedItem(ENTadu.getC2nombre() + "-(" + ENTadu.getC1idaduana() + ")");
                cargar_item_tc_comprobante_sql(conn, itemcom.getTipo_comprobante_CON_COMPROBANTE(), idliquidacion_proforma, tblitem_con_comprobante, model_item_con_comprobante);
                cargar_item_tc_comprobante_sql(conn, itemcom.getTipo_comprobante_SIN_COMPROBANTE(), idliquidacion_proforma, tblitem_sin_comprobante, model_item_sin_comprobante);
                cargar_item_tc_comprobante_sql(conn, itemcom.getTipo_comprobante_MERCADERIA(), idliquidacion_proforma, tblitem_mercaderia, model_item_mercaderia);
                cargar_item_tc_comprobante_sql(conn, itemcom.getTipo_comprobante_BOLETA_DESPACHO(), idliquidacion_proforma, tblitem_bole_despa, model_item_bole_despa);
                cargar_item_factura_numero_sql(conn, idliquidacion_proforma, tblfact_nro_proforma, model_fact_nro_proforma);
                sumar_item_tc_con_comprobante();
                sumar_item_tc_sin_comprobante();
                sumar_item_tc_bole_despa();
                sumar_fact_nro_proforma();
                cargar_terceros(fk_idtercero_importador, ENTlp.getC14fk_idtercero_importador(), cmbtercero_importador);
                cargar_terceros(fk_idtercero_transportadora, ENTlp.getC17fk_idtercero_transportadora(), cmbtercero_transportadora);
                cargar_terceros(fk_idtercero_colaborador, ENTlp.getC16fk_idtercero_colaborador(), cmbtercero_colaborador);
                cargar_terceros(fk_idtercero_despachante, ENTlp.getC15fk_idtercero_despachante(), cmbtercero_despachante);
//            txtruc_importador.setText(ENTter.getC5ruc());
                JOptionPane.showMessageDialog(null, "DATOS RECARGADOS");
                eveJtab.mostrar_JTabbedPane(jTab_principal, 0);
            }
        }
    }

    private void cargar_terceros(int fk_idtercero, int getfk_idtercero, JComboBox cmbtercero) {
        fk_idtercero = getfk_idtercero;
        DAOter.cargar_tercero(conn, ENTter, fk_idtercero);
        cmbtercero.setSelectedItem(ENTter.getC4nombre() + "-(" + ENTter.getC1idtercero() + ")");
    }

    private void boton_editar_liquidacion_proforma() {
        if (validar_guardar_liquidacion_proforma()) {

        }
    }

    private void seleccionar_tabla_liquidacion_proforma() {
        idliquidacion_proforma = eveJtab.getInt_select_id(tblliquidacion_proforma);
        DAOlp.cargar_liquidacion_proforma(conn, ENTlp, idliquidacion_proforma);
        jFsuma_sin_comprobante_select.setValue(ENTlp.getC9monto_sin_comprobante());
        jFhonorario_despacho_select.setValue(ENTlp.getC24monto_honorario_despachante());
        jFsuma_bole_despa_select.setValue(ENTlp.getC23monto_boleto_despachante());
        jFsuma_total_nota_select.setValue(ENTlp.getC9monto_sin_comprobante() + ENTlp.getC24monto_honorario_despachante() + ENTlp.getC23monto_boleto_despachante());
        DAOter.cargar_tercero(conn, ENTter, ENTlp.getC14fk_idtercero_importador());
        txtnom_importador.setText(ENTter.getC4nombre());
        DAOter.cargar_tercero(conn, ENTter, ENTlp.getC17fk_idtercero_transportadora());
        txtnom_transportador.setText(ENTter.getC4nombre());
        DAOter.cargar_tercero(conn, ENTter, ENTlp.getC15fk_idtercero_despachante());
        txtnom_despachante.setText(ENTter.getC4nombre());
        DAOter.cargar_tercero(conn, ENTter, ENTlp.getC16fk_idtercero_colaborador());
        txtnom_hecho.setText(ENTter.getC4nombre());
        txtcontenedor_nro.setText(ENTlp.getC5contenedor_nro());
        txtcontenedor_tipo.setText(ENTlp.getC6contenedor_tipo());
        txtvia_transporte.setText(ENTlp.getC7via_transporte());
        if (ENTlp.getC13estado().equals(estado_emitido)) {
            btnanular_liquidacion.setEnabled(true);
            btnrecargar_liquidacion.setEnabled(false);
        }
        if (ENTlp.getC13estado().equals(estado_anulado)) {
            btnanular_liquidacion.setEnabled(false);
            btnrecargar_liquidacion.setEnabled(true);
        }
//        btnguardar.setEnabled(false);
    }

    private void reestableser_liquidacion_proforma() {
        txtlp_fecha_despacho.setText(evefec.getString_formato_fecha());
        txtid.setText(null);
        txtlp_contenedor_nro.setText("x");
        txtlp_tasa_cambio_aduana.setText(null);
        txtlp_tasa_cambio_mercado.setText(null);
        txtlp_observacion.setText("NINGUNA");
        fk_idtercero_importador = 0;
        fk_idtercero_transportadora = 0;
        fk_idtercero_colaborador = 0;
        fk_idtercero_despachante = 0;
        fk_idaduana = 0;
        monto_factura = 0;
        monto_sin_comprobante = 0;
        monto_con_comprobante = 0;
        tasa_cambio_aduana = 0;
        tasa_cambio_mercado = 0;
        estado_emitido = "EMITIDO";
        cmbvia_transporte.setSelectedIndex(0);
        cmbaduana.setSelectedIndex(0);
        cmbcontenedor_tipo.setSelectedIndex(0);
        cmbtercero_importador.setSelectedIndex(0);
        cmbtercero_despachante.setSelectedIndex(0);
        cmbtercero_colaborador.setSelectedIndex(0);
        cmbtercero_transportadora.setSelectedIndex(0);
        cmbmoneda_cambio.setSelectedIndex(0);
        txtruc_importador.setText(null);
        txtruc_despachante.setText(null);
        txtruc_colaborador.setText(null);
        txtruc_transportadora.setText(null);
        jFsuma_sin_comprobante.setValue(0);
        jFsuma_con_comprobante.setValue(0);
        jFsuma_bole_despa.setValue(0);
        jFsuma_monto_factura.setValue(0);
        jFsuma_monto_factura_gua.setValue(0);
        btnguardar.setEnabled(true);
        btneditar.setEnabled(false);
        btndeletar.setEnabled(false);
        eveJtab.limpiar_tabla_datos(model_item_con_comprobante);
        eveJtab.limpiar_tabla_datos(model_item_sin_comprobante);
        eveJtab.limpiar_tabla_datos(model_item_bole_despa);
        eveJtab.limpiar_tabla_datos(model_item_mercaderia);
        eveJtab.limpiar_tabla_datos(model_fact_nro_proforma);
        txtlp_fecha_despacho.grabFocus();
    }

    private void cargar_tercero_importador() {
        evecomb.cargarCombobox(conn, cmbtercero_importador, "idtercero", "nombre", "tercero", "where importador=true ");
        hab_cargar_tercero_importador = true;
    }

    private void cargar_tercero_despachante() {
        evecomb.cargarCombobox(conn, cmbtercero_despachante, "idtercero", "nombre", "tercero", "where despachante=true ");
        hab_cargar_tercero_despachante = true;
    }

    private void cargar_tercero_transportadora() {
        evecomb.cargarCombobox(conn, cmbtercero_transportadora, "idtercero", "nombre", "tercero", "where transportadora=true ");
        hab_cargar_tercero_transportadora = true;
    }

    private void cargar_tercero_colaborador() {
        evecomb.cargarCombobox(conn, cmbtercero_colaborador, "idtercero", "nombre", "tercero", "where colaborador=true ");
        hab_cargar_tercero_colaborador = true;
    }

    private void cargar_aduana() {
        evecomb.cargarCombobox(conn, cmbaduana, "idaduana", "nombre", "aduana", "");
        hab_cargar_aduana = true;
    }

    private void cargar_tc_con_comprobante() {
        evecomb.cargarCombobox(conn, cmbtc_con_comprobante, "idtipo_comprobante", "descripcion", "tipo_comprobante", "where con_comprobante=true");
        hab_cargar_tc_con_comprobante = true;
    }

    private void cargar_tc_sin_comprobante() {
        evecomb.cargarCombobox(conn, cmbtc_sin_comprobante, "idtipo_comprobante", "descripcion", "tipo_comprobante", "where sin_comprobante=true");
        hab_cargar_tc_sin_comprobante = true;
    }

    private void cargar_tc_bole_despa() {
        evecomb.cargarCombobox(conn, cmbtc_bole_despa, "idtipo_comprobante", "descripcion", "tipo_comprobante", "where boleta_despachante=true");
        hab_cargar_tc_bole_despa = true;
    }

    private void cargar_tc_mercaderia() {
        evecomb.cargarCombobox(conn, cmbtc_mercaderia, "idtipo_comprobante", "descripcion", "tipo_comprobante", "where mercaderia=true");
        hab_cargar_tc_mercaderia = true;
    }

    private void cargar_moneda_cambio() {
        evecomb.cargarCombobox(conn, cmbmoneda_cambio, "idmoneda_cambio", "moneda", "moneda_cambio", "");
        hab_cargar_moneda_cambio = true;
    }

    private void boton_nuevo_liquidacion_proforma() {
        reestableser_liquidacion_proforma();
    }

    private void crear_item_tc_con_comprobante() {
        String dato[] = {"id", "descripcion", "monto", "referencia", "imagen"};
        eveJtab.crear_tabla_datos(tblitem_con_comprobante, model_item_con_comprobante, dato);
    }

    private void crear_item_tc_sin_comprobante() {
        String dato[] = {"id", "descripcion", "monto", "referencia", "imagen"};
        eveJtab.crear_tabla_datos(tblitem_sin_comprobante, model_item_sin_comprobante, dato);
    }

    private void crear_item_tc_bole_despa() {
        String dato[] = {"id", "descripcion", "monto", "referencia", "imagen"};
        eveJtab.crear_tabla_datos(tblitem_bole_despa, model_item_bole_despa, dato);
    }

    private void crear_item_tc_mercaderia() {
        String dato[] = {"id", "descripcion"};
        eveJtab.crear_tabla_datos(tblitem_mercaderia, model_item_mercaderia, dato);
    }

    private void crear_fact_nro_proforma() {
        String dato[] = {"id", "moneda", "gua_unid", "monto", "tt_guarani", "nro_factura", "imagen"};
        eveJtab.crear_tabla_datos(tblfact_nro_proforma, model_fact_nro_proforma, dato);
    }

    private void cargar_item_tc_comprobante_sql(Connection conn, String tipo_comprabante, int fk_idliquidacion_proforma, JTable tblitem, DefaultTableModel model_item) {
        String titulo = "Cargar_liquidacion_proforma";
        String sql_cargar = "select fk_idtipo_comprobante,descripcion,monto,referencia,imagen \n"
                + "from item_comprobante \n"
                + "where tipo_comprobante='" + tipo_comprabante + "' "
                + "and fk_idliquidacion_proforma=" + fk_idliquidacion_proforma;
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql_cargar, titulo);
            while (rs.next()) {
                item_comprobante itemcom = new item_comprobante();
                itemcom.setC1iditem_comprobante(rs.getInt(1));
                itemcom.setC4descripcion(rs.getString(2));
                itemcom.setC5monto(rs.getDouble(3));
                itemcom.setC6referencia(rs.getString(4));
                itemcom.setC7imagen(rs.getString(5));
                cargar_item_tc_comprobante(itemcom, tblitem, model_item);
                evemen.Imprimir_serial_sql(sql_cargar + "\n", titulo);
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_cargar + "\n", titulo);
        }
    }

    private void cargar_item_factura_numero_sql(Connection conn, int fk_idliquidacion_proforma, JTable tblitem, DefaultTableModel model_item) {
        String titulo = "Cargar_liquidacion_proforma";
        String sql_cargar = "select fac.fk_idmoneda_cambio as id,\n"
                + "(mc.moneda||'-('||fac.fk_idmoneda_cambio||')') as moneda,\n"
                + "trim(to_char(fac.guarani_unidad,'999999999')) as gua_unid,"
                + "trim(to_char(fac.monto,'999999999')) as monto,\n"
                + "trim(to_char((fac.guarani_unidad*fac.monto),'999999999')) as tt_guarani,\n"
                + "fac.nro_factura,fac.imagen\n"
                + "from factura_nro_proforma fac,moneda_cambio mc\n"
                + "where fac.fk_idmoneda_cambio=mc.idmoneda_cambio \n"
                + "and fac.fk_idliquidacion_proforma=" + fk_idliquidacion_proforma;
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql_cargar, titulo);
            while (rs.next()) {
                factura_nro_proforma itemfac = new factura_nro_proforma();
                itemfac.setC9fk_idmoneda_cambio(rs.getInt(1));
                String moneda = (rs.getString(2));
                itemfac.setC8guarani_unidad(rs.getDouble(3));
                itemfac.setC5monto(rs.getDouble(4));
//                String tt_guarani=(rs.getString(5));
                itemfac.setC4nro_factura(rs.getString(6));
                itemfac.setC6imagen(rs.getString(7));
                cargar_item_factura_numero(itemfac, moneda, tblitem, model_item);
                evemen.Imprimir_serial_sql(sql_cargar + "\n", titulo);
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql_cargar + "\n", titulo);
        }
    }

    private void cargar_item_tc_con_comprobante() {
        String id = "";
        String descripcion = "";
        String monto = "";
        String referencia = "";
        String imagen = "";
        id = String.valueOf(fk_idtc_con_comprobante);
        descripcion = cmbtc_con_comprobante.getSelectedItem().toString();
        monto = txtmonto_con_comprobante.getText();
        referencia = txtreferencia_con_comprobante.getText();
        imagen = txtimagen_con_comprobante.getText();
        String dato[] = {id, descripcion, evejtf.getString_format_nro_decimal_quitar_punto(monto), referencia, imagen};
        eveJtab.cargar_tabla_datos(tblitem_con_comprobante, model_item_con_comprobante, dato);
    }

    private void cargar_item_tc_comprobante(item_comprobante itemcom, JTable tblitem, DefaultTableModel model_item) {
        String id = "";
        String descripcion = "";
        String monto = "";
        String referencia = "";
        String imagen = "";
        id = String.valueOf(itemcom.getC1iditem_comprobante());
        descripcion = itemcom.getC4descripcion();
        monto = String.valueOf(itemcom.getC5monto());
        referencia = itemcom.getC6referencia();
        imagen = itemcom.getC7imagen();
        String dato[] = {id, descripcion, monto, referencia, imagen};
        eveJtab.cargar_tabla_datos(tblitem, model_item, dato);
    }

    private void cargar_item_factura_numero(factura_nro_proforma itemfac, String moneda, JTable tblitem, DefaultTableModel model_item) {
        String id = String.valueOf(itemfac.getC9fk_idmoneda_cambio());
        String moneda_cambio = moneda;
        String monto_sf = String.valueOf((int) itemfac.getC5monto());
        String guarani_unidad_sf = String.valueOf((int) itemfac.getC8guarani_unidad());
        String nro_factura = itemfac.getC4nro_factura();
        String imagen = itemfac.getC6imagen();
        long IL_monto = Long.parseLong(monto_sf);
        long IL_guarani_unidad = Long.parseLong(guarani_unidad_sf);
        long Itt_guarani = (IL_monto * IL_guarani_unidad);
        String tt_guarani_sf = String.valueOf(Itt_guarani);
        String dato[] = {id, moneda_cambio, guarani_unidad_sf, monto_sf, tt_guarani_sf, nro_factura, imagen};
        eveJtab.cargar_tabla_datos(tblitem, model_item, dato);
    }

    private void cargar_item_tc_sin_comprobante() {
        String id = String.valueOf(fk_idtc_sin_comprobante);
        String descripcion = cmbtc_sin_comprobante.getSelectedItem().toString();
        String monto = txtmonto_sin_comprobante.getText();
        String referencia = txtreferencia_sin_comprobante.getText();
        String imagen = txtimagen_sin_comprobante.getText();
        String dato[] = {id, descripcion, evejtf.getString_format_nro_decimal_quitar_punto(monto), referencia, imagen};
        eveJtab.cargar_tabla_datos(tblitem_sin_comprobante, model_item_sin_comprobante, dato);
    }

    private void cargar_item_tc_bole_despa() {
        String id = String.valueOf(fk_idtc_bole_despa);
        String descripcion = cmbtc_bole_despa.getSelectedItem().toString();
        String monto = txtmonto_bole_despa.getText();
        String referencia = txtreferencia_bole_despa.getText();
        String imagen = txtimagen_bole_despa.getText();
        String dato[] = {id, descripcion, evejtf.getString_format_nro_decimal_quitar_punto(monto), referencia, imagen};
        eveJtab.cargar_tabla_datos(tblitem_bole_despa, model_item_bole_despa, dato);
    }

    private void cargar_item_tc_mercaderia() {
        String id = String.valueOf(fk_idtc_mercaderia);
        String descripcion = cmbtc_mercaderia.getSelectedItem().toString();
        String dato[] = {id, descripcion};
        eveJtab.cargar_tabla_datos(tblitem_mercaderia, model_item_mercaderia, dato);
    }

    private void cargar_factura_nro_proforma() {
        String id = String.valueOf(fk_idmoneda_cambio);
        String moneda_cambio = cmbmoneda_cambio.getSelectedItem().toString();
        String monto = txtmonto_fact_nro_proforma.getText();
        String monto_sf = evejtf.getString_format_nro_decimal_quitar_punto(monto);
        String guarani_unidad = (txtlp_tasa_cambio_aduana.getText());
        String guarani_unidad_sf = evejtf.getString_format_nro_decimal_quitar_punto(guarani_unidad);
        String nro_factura = txtnro_factura_fact_nro_proforma.getText();
        String imagen = txtimagen_fact_nro_proforma.getText();
        long IL_monto = Long.parseLong(monto_sf);
        long IL_guarani_unidad = Long.parseLong(guarani_unidad_sf);
        long Itt_guarani = (IL_monto * IL_guarani_unidad);
        String tt_guarani_sf = String.valueOf(Itt_guarani);
        System.out.println("monto: " + monto + " monto_sf: " + monto_sf + " guarani_unidad: " + guarani_unidad + " guarani_unidad_sf: " + guarani_unidad_sf + " Itt_guarani: " + Itt_guarani + " tt_guarani_sf: " + tt_guarani_sf);
        String dato[] = {id, moneda_cambio, guarani_unidad_sf, monto_sf, tt_guarani_sf, nro_factura, imagen};
        eveJtab.cargar_tabla_datos(tblfact_nro_proforma, model_fact_nro_proforma, dato);
    }

    private boolean validar_cargar_item_tc_con_comprobante() {
        if (evejtf.getBoo_JTextField_vacio(txtmonto_con_comprobante, "DEBE CARGAR UN MONTO")) {
            return false;
        }
        if (evejtf.getBoo_JTextField_vacio(txtreferencia_con_comprobante, "DEBE CARGAR UNA REFERENCIA")) {
            return false;
        }
        if (evejtf.getBoo_JTextField_vacio(txtimagen_con_comprobante, "DEBE CARGAR UNA IMAGEN")) {
            return false;
        }
        if (evecomb.getBoo_JCombobox_seleccionar(cmbtc_con_comprobante, "SELECCIONE UN COMPROBANTE")) {
            eveJtab.mostrar_JTabbedPane(jTab_item, 1);
            return false;
        }
        return true;
    }

    private boolean validar_cargar_item_tc_sin_comprobante() {
        if (evejtf.getBoo_JTextField_vacio(txtmonto_sin_comprobante, "DEBE CARGAR UN MONTO")) {
            return false;
        }
        if (evejtf.getBoo_JTextField_vacio(txtreferencia_sin_comprobante, "DEBE CARGAR UNA REFERENCIA")) {
            return false;
        }
        if (evejtf.getBoo_JTextField_vacio(txtimagen_sin_comprobante, "DEBE CARGAR UNA IMAGEN")) {
            return false;
        }
        if (evecomb.getBoo_JCombobox_seleccionar(cmbtc_sin_comprobante, "SELECCIONE UN COMPROBANTE")) {
            eveJtab.mostrar_JTabbedPane(jTab_item, 2);
            return false;
        }
        return true;
    }

    private boolean validar_cargar_item_tc_bole_despa() {
        if (evejtf.getBoo_JTextField_vacio(txtmonto_bole_despa, "DEBE CARGAR UN MONTO")) {
            return false;
        }
        if (evejtf.getBoo_JTextField_vacio(txtreferencia_bole_despa, "DEBE CARGAR UNA REFERENCIA")) {
            return false;
        }
        if (evejtf.getBoo_JTextField_vacio(txtimagen_bole_despa, "DEBE CARGAR UNA IMAGEN")) {
            return false;
        }
        if (evecomb.getBoo_JCombobox_seleccionar(cmbtc_bole_despa, "SELECCIONE UN COMPROBANTE")) {
            eveJtab.mostrar_JTabbedPane(jTab_item, 3);
            return false;
        }
        return true;
    }

    private boolean validar_cargar_item_tc_mercaderia() {
        if (evecomb.getBoo_JCombobox_seleccionar(cmbtc_mercaderia, "SELECCIONE UNA MERCADERIA")) {
            eveJtab.mostrar_JTabbedPane(jTab_item, 4);
            return false;
        }
        return true;
    }

    private boolean validar_cargar_factura_nro_proforma() {
        if (evejtf.getBoo_JTextField_vacio(txtmonto_fact_nro_proforma, "DEBE CARGAR UN MONTO")) {
            return false;
        }
        if (evejtf.getBoo_JTextField_vacio(txtnro_factura_fact_nro_proforma, "DEBE CARGAR NRO FACTURA")) {
            return false;
        }
        if (evejtf.getBoo_JTextField_vacio(txtimagen_fact_nro_proforma, "DEBE CARGAR UNA IMAGEN")) {
            return false;
        }

        if (evecomb.getBoo_JCombobox_seleccionar(cmbmoneda_cambio, "SELECCIONE UNA MONEDA")) {
            eveJtab.mostrar_JTabbedPane(jTab_item, 5);
            return false;
        }
        return true;
    }

    private void reestableser_item_tc_con_comprobante() {
        cmbtc_con_comprobante.setSelectedIndex(0);
        txtmonto_con_comprobante.setText(null);
        txtreferencia_con_comprobante.setText(referencia);
        txtimagen_con_comprobante.setText(ruta_imagen);
    }

    private void reestableser_item_tc_sin_comprobante() {
        cmbtc_sin_comprobante.setSelectedIndex(0);
        txtmonto_sin_comprobante.setText(null);
        txtreferencia_sin_comprobante.setText(referencia);
        txtimagen_sin_comprobante.setText(ruta_imagen);
    }

    private void reestableser_item_tc_bole_despa() {
        cmbtc_bole_despa.setSelectedIndex(0);
        txtmonto_bole_despa.setText(null);
        txtreferencia_bole_despa.setText(referencia);
        txtimagen_bole_despa.setText(ruta_imagen);
    }

    private void reestableser_item_tc_mercaderia() {
        cmbtc_mercaderia.setSelectedIndex(0);
    }

    private void reestableser_fact_nro_proforma() {
//        cmbmoneda_cambio.setSelectedIndex(0);
        txtmonto_fact_nro_proforma.setText(null);
        txtnro_factura_fact_nro_proforma.setText(null);
        txtimagen_fact_nro_proforma.setText(ruta_imagen);
        txtmonto_fact_nro_proforma.grabFocus();
    }

    void ancho_item_tc_con_comprobante() {
        int Ancho[] = {5, 50, 15, 20, 10};
        eveJtab.setAnchoColumnaJtable(tblitem_con_comprobante, Ancho);
    }

    void ancho_item_tc_sin_comprobante() {
        int Ancho[] = {5, 50, 15, 20, 10};
        eveJtab.setAnchoColumnaJtable(tblitem_sin_comprobante, Ancho);
    }

    void ancho_item_tc_bole_despa() {
        int Ancho[] = {5, 50, 15, 20, 10};
        eveJtab.setAnchoColumnaJtable(tblitem_bole_despa, Ancho);
    }

    void ancho_item_tc_mercaderia() {
        int Ancho[] = {20, 80};
        eveJtab.setAnchoColumnaJtable(tblitem_mercaderia, Ancho);
    }

    private void ancho_fact_nro_proforma() {
        int Ancho[] = {5, 20, 10, 15, 20, 20, 10};
        eveJtab.setAnchoColumnaJtable(tblfact_nro_proforma, Ancho);
    }

    void sumar_item_tc_con_comprobante() {
        double monto = eveJtab.getDouble_sumar_tabla(tblitem_con_comprobante, 2);
        monto_con_comprobante = monto;
        jFsuma_con_comprobante.setValue(monto_con_comprobante);
    }

    void sumar_item_tc_sin_comprobante() {
        double monto = eveJtab.getDouble_sumar_tabla(tblitem_sin_comprobante, 2);
        monto_sin_comprobante = monto;
        jFsuma_sin_comprobante.setValue(monto_sin_comprobante);
        jFsuma_total_nota.setValue(monto_sin_comprobante + monto_bole_despa + monto_honorario_despachante);
    }

    void sumar_item_tc_bole_despa() {
        double monto = eveJtab.getDouble_sumar_tabla(tblitem_bole_despa, 2);
        monto_bole_despa = monto;
        jFsuma_bole_despa.setValue(monto_bole_despa);
        jFsuma_total_nota.setValue(monto_sin_comprobante + monto_bole_despa + monto_honorario_despachante);
    }

    private void sumar_fact_nro_proforma() {
        double monto_moneda_otro = eveJtab.getDouble_sumar_tabla(tblfact_nro_proforma, 3);
        monto_factura = monto_moneda_otro;
        evejfor.setCargarMonto_Formato(jFsuma_monto_factura, monto_moneda_otro, ENTmc.getC5sigla());
        long monto_moneda_gua = eveJtab.getLong_sumar_tabla(tblfact_nro_proforma, 4);
        jFsuma_monto_factura_gua.setValue(monto_moneda_gua);
    }

    void boton_cargar_item_tc_sin_comprobante() {
        if (validar_cargar_item_tc_sin_comprobante()) {
            cargar_item_tc_sin_comprobante();
            sumar_item_tc_sin_comprobante();
            reestableser_item_tc_sin_comprobante();
        }
    }

    void boton_cargar_item_tc_bole_despa() {
        if (validar_cargar_item_tc_bole_despa()) {
            cargar_item_tc_bole_despa();
            sumar_item_tc_bole_despa();
            reestableser_item_tc_bole_despa();
        }
    }

    void boton_cargar_item_tc_con_comprobante() {
        if (validar_cargar_item_tc_con_comprobante()) {
            cargar_item_tc_con_comprobante();
            sumar_item_tc_con_comprobante();
            reestableser_item_tc_con_comprobante();
        }
    }

    void boton_cargar_item_tc_mercaderia() {
        if (validar_cargar_item_tc_mercaderia()) {
            cargar_item_tc_mercaderia();
//            sumar_item_tc_con_comprobante();
            reestableser_item_tc_mercaderia();
        }
    }

    private void boton_cargar_factura_nro_proforma() {
        if (validar_cargar_factura_nro_proforma()) {
            cargar_factura_nro_proforma();
            sumar_fact_nro_proforma();
            reestableser_fact_nro_proforma();
        }
    }

    void boton_eliminar_item_tc_con_comprobante() {
        if (!eveJtab.getBoolean_validar_select(tblitem_con_comprobante)) {
            if (evemen.MensajeGeneral_warning("DESEAS ELIMINAR ESTE FILA", "ELIMINAR", "ACEPTAR", "CANCELAR")) {
                if (eveJtab.getBoolean_Eliminar_Fila(tblitem_con_comprobante, model_item_con_comprobante)) {
                    sumar_item_tc_con_comprobante();
                    eveJtab.mostrar_JTabbedPane(jTab_item, 1);
                }
            }
        }
    }

    void boton_eliminar_item_tc_sin_comprobante() {
        if (!eveJtab.getBoolean_validar_select(tblitem_sin_comprobante)) {
            if (evemen.MensajeGeneral_warning("DESEAS ELIMINAR ESTE FILA", "ELIMINAR", "ACEPTAR", "CANCELAR")) {
                if (eveJtab.getBoolean_Eliminar_Fila(tblitem_sin_comprobante, model_item_sin_comprobante)) {
                    sumar_item_tc_con_comprobante();
                    eveJtab.mostrar_JTabbedPane(jTab_item, 2);
                }
            }
        }
    }

    void boton_eliminar_item_tc_bole_despa() {
        if (!eveJtab.getBoolean_validar_select(tblitem_bole_despa)) {
            if (evemen.MensajeGeneral_warning("DESEAS ELIMINAR ESTE FILA", "ELIMINAR", "ACEPTAR", "CANCELAR")) {
                if (eveJtab.getBoolean_Eliminar_Fila(tblitem_bole_despa, model_item_bole_despa)) {
                    sumar_item_tc_bole_despa();
                    eveJtab.mostrar_JTabbedPane(jTab_item, 3);
                }
            }
        }
    }

    void boton_eliminar_item_tc_mercaderia() {
        if (!eveJtab.getBoolean_validar_select(tblitem_mercaderia)) {
            if (evemen.MensajeGeneral_warning("DESEAS ELIMINAR ESTE FILA", "ELIMINAR", "ACEPTAR", "CANCELAR")) {
                if (eveJtab.getBoolean_Eliminar_Fila(tblitem_mercaderia, model_item_mercaderia)) {
//                    sumar_item_tc_bole_despa();
                    eveJtab.mostrar_JTabbedPane(jTab_item, 4);
                }
            }
        }
    }

    void boton_eliminar_item_tc_fac_monto() {
        if (!eveJtab.getBoolean_validar_select(tblfact_nro_proforma)) {
            if (evemen.MensajeGeneral_warning("DESEAS ELIMINAR ESTE FILA", "ELIMINAR", "ACEPTAR", "CANCELAR")) {
                if (eveJtab.getBoolean_Eliminar_Fila(tblfact_nro_proforma, model_fact_nro_proforma)) {
                    sumar_fact_nro_proforma();
                    eveJtab.mostrar_JTabbedPane(jTab_item, 5);
                }
            }
        }
    }

    private void cargar_ultimo_honorario_despacho() {
        int id = (eveconn.getInt_ultimoID_max(conn, ENThd.getTb_honorario_despacho(), ENThd.getId_idhonorario_despacho()));
        DAOhd.cargar_honorario_despacho(conn, ENThd, id);
        fk_idhonorario_despacho = ENThd.getC1idhonorario_despacho();
        monto_honorario_despachante = ENThd.getC3monto();
        jFhonorario_despacho.setValue(monto_honorario_despachante);
    }

    public void imprimir_rep_liquidacion_proforma(Connection conn, int id) {
        String sql = "select lp.idliquidacion_proforma as idlp\n"
                + ",tei.nombre as importador\n"
                + ",('R:'||tei.ruc) as imp_ruc\n"
                + ",ted.nombre as despacho\n"
                + ",('R:'||ted.ruc) as des_ruc\n"
                + ",tet.nombre as transporte\n"
                + ",('R:'||tet.ruc) as tra_ruc\n"
                + ",tec.nombre as hecho_por\n"
                + ",lp.fecha_despacho,lp.contenedor_nro as cont_nro\n"
                + ",lp.contenedor_tipo as cont_tipo\n"
                + ",lp.via_transporte as via\n"
                + ",ad.nombre as aduana\n"
                + ",lp.tasa_cambio_aduana as tca \n"
                + ",lp.tasa_cambio_mercado as tcm\n"
                + ",ic.descripcion as descrip,ic.monto as monto\n"
                + ",ic.referencia as referencia\n"
                + ",ic.tipo_comprobante as tipo_comp\n"
                + ",case when ic.tipo_comprobante='" + itemcom.getTipo_comprobante_MERCADERIA() + "' then 1\n"
                + "when ic.tipo_comprobante='" + itemcom.getTipo_comprobante_CON_COMPROBANTE() + "' then 2\n"
                + "when ic.tipo_comprobante='" + itemcom.getTipo_comprobante_SIN_COMPROBANTE() + "' then 3\n"
                + "when ic.tipo_comprobante='" + itemcom.getTipo_comprobante_BOLETA_DESPACHO() + "' then 4\n"
                + "else 0 end as orden\n"
                + ",lp.tipo_liquidacion as liquidacion\n"
                + ",lp.monto_sin_comprobante as monto_sin_comp\n"
                + ",lp.monto_boleto_despachante as monto_boleto\n"
                + ",lp.monto_honorario_despachante as monto_hono\n"
                + ",(lp.monto_sin_comprobante+lp.monto_boleto_despachante+lp.monto_honorario_despachante) as monto_nota\n"
                + ",lp.observacion as observacion "
                + "from liquidacion_proforma lp,\n"
                + "tercero tei,tercero ted, tercero tet,tercero tec,\n"
                + "aduana ad,item_comprobante ic \n"
                + "where lp.fk_idtercero_importador=tei.idtercero \n"
                + "and lp.fk_idtercero_despachante=ted.idtercero \n"
                + "and lp.fk_idtercero_transportadora =tet.idtercero \n"
                + "and lp.fk_idtercero_colaborador=tec.idtercero \n"
                + "and lp.fk_idaduana = ad.idaduana\n"
                + "and lp.idliquidacion_proforma = ic.fk_idliquidacion_proforma \n"
                + "and lp.idliquidacion_proforma =" + id
                + " order by 20 asc,ic.descripcion desc;";
        String titulonota = "LIQUIDACION";
        String direccion = "src/REPORTE/PROFORMA/repProforma1.jrxml";
        rep.imprimirjasper(conn, sql, titulonota, direccion);
    }

    private void cargar_dato_select_moneda_cambio(int fk_idmoneda_cambio, boolean cargarcombo) {
        DAOmc.cargar_moneda_cambio(conn, ENTmc, fk_idmoneda_cambio);
        tipo_tasa_cambio = ENTmc.getC2moneda();
        lblmoneda.setText(tipo_tasa_cambio);
        txtlp_tasa_cambio_aduana.setText(evejtf.getString_format_nro_decimal(ENTmc.getC3guarani_unidad_aduana()));
        txtlp_tasa_cambio_mercado.setText(evejtf.getString_format_nro_decimal(ENTmc.getC4guarani_unidad_mercado()));
        if (cargarcombo) {
            cmbmoneda_cambio.setSelectedItem(ENTmc.getC2moneda() + "-(" + ENTmc.getC1idmoneda_cambio() + ")");
        } else {
            txtmonto_fact_nro_proforma.grabFocus();
        }
    }

    private void boton_imprimir_proforma() {
        if (dao_usu.getBoolean_hab_evento_mensaje_error(conn, "24")) {
            if (!eveJtab.getBoolean_validar_select(tblliquidacion_proforma)) {
                imprimir_rep_liquidacion_proforma(conn, idliquidacion_proforma);
            }
        }
    }

    public FrmLiquidacion_proforma() {
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

        gru_tipocambio = new javax.swing.ButtonGroup();
        gru_im_ex = new javax.swing.ButtonGroup();
        jTab_principal = new javax.swing.JTabbedPane();
        panel_insertar = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtid = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtlp_fecha_despacho = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtlp_contenedor_nro = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cmbcontenedor_tipo = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        cmbvia_transporte = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtlp_tasa_cambio_aduana = new javax.swing.JTextField();
        txtlp_tasa_cambio_mercado = new javax.swing.JTextField();
        cmbmoneda_cambio = new javax.swing.JComboBox<>();
        jTab_item = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        cmbtercero_importador = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        txtruc_importador = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();
        cmbtercero_transportadora = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        txtruc_transportadora = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
        cmbtercero_colaborador = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        txtruc_colaborador = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel14 = new javax.swing.JLabel();
        cmbtercero_despachante = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        txtruc_despachante = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        cmbtc_con_comprobante = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        txtmonto_con_comprobante = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtreferencia_con_comprobante = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtimagen_con_comprobante = new javax.swing.JTextField();
        btnimagen_con_comprobante = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblitem_con_comprobante = new javax.swing.JTable();
        btncargar_con_comprobante = new javax.swing.JButton();
        btneliminar_con_comp = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        btncargar_sin_comprobante = new javax.swing.JButton();
        btneliminar_sin_comprobante = new javax.swing.JButton();
        txtimagen_sin_comprobante = new javax.swing.JTextField();
        btnimagen_sin_comprobante = new javax.swing.JButton();
        txtreferencia_sin_comprobante = new javax.swing.JTextField();
        txtmonto_sin_comprobante = new javax.swing.JTextField();
        cmbtc_sin_comprobante = new javax.swing.JComboBox<>();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblitem_sin_comprobante = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        btncargar_bole_despa = new javax.swing.JButton();
        btneliminar_bole_despa = new javax.swing.JButton();
        txtimagen_bole_despa = new javax.swing.JTextField();
        btnimagen_bole_despa = new javax.swing.JButton();
        txtreferencia_bole_despa = new javax.swing.JTextField();
        txtmonto_bole_despa = new javax.swing.JTextField();
        cmbtc_bole_despa = new javax.swing.JComboBox<>();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblitem_bole_despa = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        cmbtc_mercaderia = new javax.swing.JComboBox<>();
        btncargar_mercaderia = new javax.swing.JButton();
        btneliminar_mercaderia = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblitem_mercaderia = new javax.swing.JTable();
        jPanel13 = new javax.swing.JPanel();
        btneliminar_fact_nro_proforma = new javax.swing.JButton();
        btncargar_fact_nro_proforma = new javax.swing.JButton();
        txtimagen_fact_nro_proforma = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        btnimagen_fact_nro_proforma = new javax.swing.JButton();
        txtnro_factura_fact_nro_proforma = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        txtmonto_fact_nro_proforma = new javax.swing.JTextField();
        jPanel14 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblfact_nro_proforma = new javax.swing.JTable();
        lblmoneda = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        cmbaduana = new javax.swing.JComboBox<>();
        btnnuevo = new javax.swing.JButton();
        btnguardar = new javax.swing.JButton();
        btneditar = new javax.swing.JButton();
        btndeletar = new javax.swing.JButton();
        jFsuma_monto_factura = new javax.swing.JFormattedTextField();
        jFsuma_sin_comprobante = new javax.swing.JFormattedTextField();
        jFsuma_con_comprobante = new javax.swing.JFormattedTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtlp_observacion = new javax.swing.JTextArea();
        jFsuma_bole_despa = new javax.swing.JFormattedTextField();
        jRimportacion = new javax.swing.JRadioButton();
        jRexportacion = new javax.swing.JRadioButton();
        jFsuma_monto_factura_gua = new javax.swing.JFormattedTextField();
        jFsuma_total_nota = new javax.swing.JFormattedTextField();
        jFhonorario_despacho = new javax.swing.JFormattedTextField();
        jPanel5 = new javax.swing.JPanel();
        panel_filtro = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblliquidacion_proforma = new javax.swing.JTable();
        btnimprimir_proforma = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        txtnom_importador = new javax.swing.JTextField();
        txtnom_transportador = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        txtnom_despachante = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        txtnom_hecho = new javax.swing.JTextField();
        jPanel15 = new javax.swing.JPanel();
        jFsuma_sin_comprobante_select = new javax.swing.JFormattedTextField();
        jFhonorario_despacho_select = new javax.swing.JFormattedTextField();
        jFsuma_bole_despa_select = new javax.swing.JFormattedTextField();
        jFsuma_total_nota_select = new javax.swing.JFormattedTextField();
        jPanel18 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        txtcontenedor_nro = new javax.swing.JTextField();
        txtcontenedor_tipo = new javax.swing.JTextField();
        txtvia_transporte = new javax.swing.JTextField();
        jPanel17 = new javax.swing.JPanel();
        btnanular_liquidacion = new javax.swing.JButton();
        btnrecargar_liquidacion = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setResizable(true);
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

        jLabel1.setText("ID:");

        jLabel2.setText("FEC. DESPACHO:");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("CONTENEDOR"));

        jLabel3.setText("NUMERO:");

        jLabel4.setText("TIPO CARGA:");

        cmbcontenedor_tipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-SELECCIONAR-", "SIN-TIPO", "CONSOLIDADA o LCL", "COMPLETA o FCL" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbcontenedor_tipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtlp_contenedor_nro, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel4))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtlp_contenedor_nro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbcontenedor_tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel5.setText("VIA DE TRANSPORTE:");

        cmbvia_transporte.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-SELECCIONAR-", "AEREO", "FLUVIAL", "MARITIMO", "TERRESTRE" }));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("TASA DE CAMBIO"));

        jLabel6.setText("ADUANA:");

        jLabel7.setText("MERCADO:");

        txtlp_tasa_cambio_aduana.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtlp_tasa_cambio_aduana.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtlp_tasa_cambio_aduanaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtlp_tasa_cambio_aduanaKeyTyped(evt);
            }
        });

        txtlp_tasa_cambio_mercado.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtlp_tasa_cambio_mercado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtlp_tasa_cambio_mercadoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtlp_tasa_cambio_mercadoKeyTyped(evt);
            }
        });

        cmbmoneda_cambio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbmoneda_cambio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbmoneda_cambioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(txtlp_tasa_cambio_aduana, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtlp_tasa_cambio_mercado)))
                .addGap(9, 9, 9))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(cmbmoneda_cambio, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(cmbmoneda_cambio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtlp_tasa_cambio_aduana, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtlp_tasa_cambio_mercado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 11, Short.MAX_VALUE))
        );

        jTab_item.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel8.setText("IMPORTADOR:");

        cmbtercero_importador.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbtercero_importador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbtercero_importadorActionPerformed(evt);
            }
        });

        jLabel9.setText("RUC:");

        jLabel10.setText("TRANSPORTADORA:");

        cmbtercero_transportadora.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbtercero_transportadora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbtercero_transportadoraActionPerformed(evt);
            }
        });

        jLabel11.setText("RUC:");

        jLabel12.setText("HECHO POR:");

        cmbtercero_colaborador.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbtercero_colaborador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbtercero_colaboradorActionPerformed(evt);
            }
        });

        jLabel13.setText("RUC:");

        jLabel14.setText("DESPACHANTE:");

        cmbtercero_despachante.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbtercero_despachante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbtercero_despachanteActionPerformed(evt);
            }
        });

        jLabel15.setText("RUC:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addComponent(jSeparator2)
            .addComponent(jSeparator3)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel10)
                    .addComponent(jLabel12)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbtercero_despachante, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbtercero_colaborador, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbtercero_transportadora, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbtercero_importador, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtruc_importador, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtruc_transportadora, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtruc_colaborador, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtruc_despachante, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(305, Short.MAX_VALUE))
            .addComponent(jSeparator4)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cmbtercero_importador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txtruc_importador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cmbtercero_transportadora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(txtruc_transportadora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(cmbtercero_colaborador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(txtruc_colaborador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(cmbtercero_despachante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(txtruc_despachante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jTab_item.addTab("TERCEROS", jPanel4);

        jLabel17.setText("COMPROBANTE:");

        cmbtc_con_comprobante.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbtc_con_comprobante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbtc_con_comprobanteActionPerformed(evt);
            }
        });

        jLabel18.setText("MONTO:");

        txtmonto_con_comprobante.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtmonto_con_comprobante.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtmonto_con_comprobanteKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtmonto_con_comprobanteKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtmonto_con_comprobanteKeyTyped(evt);
            }
        });

        jLabel19.setText("REFERENCIA:");

        txtreferencia_con_comprobante.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtreferencia_con_comprobanteKeyPressed(evt);
            }
        });

        jLabel20.setText("IMAGEN:");

        txtimagen_con_comprobante.setEditable(false);

        btnimagen_con_comprobante.setText("ABRIR");
        btnimagen_con_comprobante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnimagen_con_comprobanteActionPerformed(evt);
            }
        });

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("CON COMPROBANTE"));

        tblitem_con_comprobante.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(tblitem_con_comprobante);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 643, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        btncargar_con_comprobante.setText("CARGAR");
        btncargar_con_comprobante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncargar_con_comprobanteActionPerformed(evt);
            }
        });

        btneliminar_con_comp.setText("ELIMINAR ITEM");
        btneliminar_con_comp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminar_con_compActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addComponent(jLabel18)
                            .addComponent(jLabel17)
                            .addComponent(jLabel20))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtmonto_con_comprobante, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(txtimagen_con_comprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnimagen_con_comprobante))
                            .addComponent(cmbtc_con_comprobante, javax.swing.GroupLayout.Alignment.LEADING, 0, 198, Short.MAX_VALUE)
                            .addComponent(txtreferencia_con_comprobante)))
                    .addComponent(btncargar_con_comprobante, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btneliminar_con_comp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(cmbtc_con_comprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtmonto_con_comprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txtreferencia_con_comprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel20)
                    .addComponent(txtimagen_con_comprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnimagen_con_comprobante))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btncargar_con_comprobante)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btneliminar_con_comp)
                .addContainerGap(37, Short.MAX_VALUE))
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTab_item.addTab("CON COMPROBANTE", jPanel1);

        jLabel21.setText("COMPROBANTE:");

        jLabel22.setText("MONTO:");

        jLabel23.setText("REFERENCIA:");

        jLabel24.setText("IMAGEN:");

        btncargar_sin_comprobante.setText("CARGAR");
        btncargar_sin_comprobante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncargar_sin_comprobanteActionPerformed(evt);
            }
        });

        btneliminar_sin_comprobante.setText("ELIMINAR ITEM");
        btneliminar_sin_comprobante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminar_sin_comprobanteActionPerformed(evt);
            }
        });

        txtimagen_sin_comprobante.setEditable(false);

        btnimagen_sin_comprobante.setText("ABRIR");
        btnimagen_sin_comprobante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnimagen_sin_comprobanteActionPerformed(evt);
            }
        });

        txtreferencia_sin_comprobante.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtreferencia_sin_comprobanteKeyPressed(evt);
            }
        });

        txtmonto_sin_comprobante.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtmonto_sin_comprobante.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtmonto_sin_comprobanteKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtmonto_sin_comprobanteKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtmonto_sin_comprobanteKeyTyped(evt);
            }
        });

        cmbtc_sin_comprobante.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbtc_sin_comprobante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbtc_sin_comprobanteActionPerformed(evt);
            }
        });

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("SIN COMPROBANTE"));

        tblitem_sin_comprobante.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(tblitem_sin_comprobante);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 643, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23)
                            .addComponent(jLabel22)
                            .addComponent(jLabel21)
                            .addComponent(jLabel24))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtmonto_sin_comprobante, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                .addComponent(txtimagen_sin_comprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnimagen_sin_comprobante))
                            .addComponent(cmbtc_sin_comprobante, javax.swing.GroupLayout.Alignment.LEADING, 0, 198, Short.MAX_VALUE)
                            .addComponent(txtreferencia_sin_comprobante)))
                    .addComponent(btncargar_sin_comprobante, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btneliminar_sin_comprobante, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(cmbtc_sin_comprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(txtmonto_sin_comprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(txtreferencia_sin_comprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel24)
                    .addComponent(txtimagen_sin_comprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnimagen_sin_comprobante))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btncargar_sin_comprobante)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btneliminar_sin_comprobante)
                .addContainerGap(37, Short.MAX_VALUE))
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTab_item.addTab("SIN COMPROBANTE", jPanel6);

        jLabel25.setText("COMPROBANTE:");

        jLabel26.setText("MONTO:");

        jLabel27.setText("REFERENCIA:");

        jLabel28.setText("IMAGEN:");

        btncargar_bole_despa.setText("CARGAR");
        btncargar_bole_despa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncargar_bole_despaActionPerformed(evt);
            }
        });

        btneliminar_bole_despa.setText("ELIMINAR ITEM");
        btneliminar_bole_despa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminar_bole_despaActionPerformed(evt);
            }
        });

        txtimagen_bole_despa.setEditable(false);

        btnimagen_bole_despa.setText("ABRIR");
        btnimagen_bole_despa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnimagen_bole_despaActionPerformed(evt);
            }
        });

        txtreferencia_bole_despa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtreferencia_bole_despaKeyPressed(evt);
            }
        });

        txtmonto_bole_despa.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtmonto_bole_despa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtmonto_bole_despaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtmonto_bole_despaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtmonto_bole_despaKeyTyped(evt);
            }
        });

        cmbtc_bole_despa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbtc_bole_despa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbtc_bole_despaActionPerformed(evt);
            }
        });

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder("BOLETA DESPACHO"));

        tblitem_bole_despa.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane5.setViewportView(tblitem_bole_despa);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 643, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel27)
                            .addComponent(jLabel26)
                            .addComponent(jLabel25)
                            .addComponent(jLabel28))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtmonto_bole_despa, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                .addComponent(txtimagen_bole_despa, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnimagen_bole_despa))
                            .addComponent(cmbtc_bole_despa, javax.swing.GroupLayout.Alignment.LEADING, 0, 198, Short.MAX_VALUE)
                            .addComponent(txtreferencia_bole_despa)))
                    .addComponent(btncargar_bole_despa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btneliminar_bole_despa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(cmbtc_bole_despa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(txtmonto_bole_despa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(txtreferencia_bole_despa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel28)
                    .addComponent(txtimagen_bole_despa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnimagen_bole_despa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btncargar_bole_despa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btneliminar_bole_despa)
                .addContainerGap(37, Short.MAX_VALUE))
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTab_item.addTab("BOLETA DESPACHANTE", jPanel7);

        jLabel29.setText("MERCADERIA:");

        cmbtc_mercaderia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbtc_mercaderia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbtc_mercaderiaActionPerformed(evt);
            }
        });

        btncargar_mercaderia.setText("CARGAR");
        btncargar_mercaderia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncargar_mercaderiaActionPerformed(evt);
            }
        });

        btneliminar_mercaderia.setText("ELIMINAR ITEM");
        btneliminar_mercaderia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminar_mercaderiaActionPerformed(evt);
            }
        });

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder("MERCADERIA"));

        tblitem_mercaderia.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane6.setViewportView(tblitem_mercaderia);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 653, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel29)
                    .addComponent(btncargar_mercaderia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btneliminar_mercaderia, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
                    .addComponent(cmbtc_mercaderia, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbtc_mercaderia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addComponent(btncargar_mercaderia)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btneliminar_mercaderia)
                .addContainerGap(43, Short.MAX_VALUE))
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTab_item.addTab("MERCADERIA", jPanel8);

        btneliminar_fact_nro_proforma.setText("ELIMINAR ITEM");
        btneliminar_fact_nro_proforma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminar_fact_nro_proformaActionPerformed(evt);
            }
        });

        btncargar_fact_nro_proforma.setText("CARGAR");
        btncargar_fact_nro_proforma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncargar_fact_nro_proformaActionPerformed(evt);
            }
        });

        txtimagen_fact_nro_proforma.setEditable(false);

        jLabel30.setText("IMAGEN:");

        btnimagen_fact_nro_proforma.setText("ABRIR");
        btnimagen_fact_nro_proforma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnimagen_fact_nro_proformaActionPerformed(evt);
            }
        });

        txtnro_factura_fact_nro_proforma.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtnro_factura_fact_nro_proformaKeyPressed(evt);
            }
        });

        jLabel31.setText("NRO-FACTURA");

        jLabel32.setText("MONTO:");

        jLabel33.setText("MONEDA:");

        txtmonto_fact_nro_proforma.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtmonto_fact_nro_proforma.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtmonto_fact_nro_proformaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtmonto_fact_nro_proformaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtmonto_fact_nro_proformaKeyTyped(evt);
            }
        });

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder("FACTURA - MONTO"));

        tblfact_nro_proforma.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane7.setViewportView(tblfact_nro_proforma);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 24, Short.MAX_VALUE))
        );

        lblmoneda.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblmoneda.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblmoneda.setText("jLabel34");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btncargar_fact_nro_proforma, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btneliminar_fact_nro_proforma, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel31)
                            .addComponent(jLabel32)
                            .addComponent(jLabel30)
                            .addComponent(jLabel33))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(txtimagen_fact_nro_proforma, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnimagen_fact_nro_proforma))
                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtnro_factura_fact_nro_proforma, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                                .addComponent(txtmonto_fact_nro_proforma, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(lblmoneda, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(lblmoneda))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(txtmonto_fact_nro_proforma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(txtnro_factura_fact_nro_proforma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel30)
                    .addComponent(txtimagen_fact_nro_proforma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnimagen_fact_nro_proforma))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btncargar_fact_nro_proforma)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btneliminar_fact_nro_proforma)
                .addContainerGap())
            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTab_item.addTab("FACTURAS-MONTOS", jPanel13);

        jLabel16.setText("ADUANA:");

        cmbaduana.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbaduana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbaduanaActionPerformed(evt);
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

        jFsuma_monto_factura.setBorder(javax.swing.BorderFactory.createTitledBorder("MONTO-FACTURA"));
        jFsuma_monto_factura.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0"))));
        jFsuma_monto_factura.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFsuma_monto_factura.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jFsuma_sin_comprobante.setBackground(new java.awt.Color(255, 255, 102));
        jFsuma_sin_comprobante.setBorder(javax.swing.BorderFactory.createTitledBorder("SIN-COMPROBANTE"));
        jFsuma_sin_comprobante.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0 Gs"))));
        jFsuma_sin_comprobante.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFsuma_sin_comprobante.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jFsuma_con_comprobante.setBorder(javax.swing.BorderFactory.createTitledBorder("CON-COMPROBANTE"));
        jFsuma_con_comprobante.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0 Gs"))));
        jFsuma_con_comprobante.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFsuma_con_comprobante.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        txtlp_observacion.setColumns(20);
        txtlp_observacion.setRows(5);
        txtlp_observacion.setBorder(javax.swing.BorderFactory.createTitledBorder("OBSERVACION"));
        jScrollPane1.setViewportView(txtlp_observacion);

        jFsuma_bole_despa.setBackground(new java.awt.Color(255, 255, 102));
        jFsuma_bole_despa.setBorder(javax.swing.BorderFactory.createTitledBorder("BOLE-DESPACHO"));
        jFsuma_bole_despa.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0 Gs"))));
        jFsuma_bole_despa.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFsuma_bole_despa.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        gru_im_ex.add(jRimportacion);
        jRimportacion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jRimportacion.setForeground(new java.awt.Color(0, 0, 102));
        jRimportacion.setSelected(true);
        jRimportacion.setText("IMPORTACION");
        jRimportacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRimportacionActionPerformed(evt);
            }
        });

        gru_im_ex.add(jRexportacion);
        jRexportacion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jRexportacion.setForeground(new java.awt.Color(102, 0, 0));
        jRexportacion.setText("EXPORTACION");

        jFsuma_monto_factura_gua.setBorder(javax.swing.BorderFactory.createTitledBorder("MONTO-FACTURA-GUA"));
        jFsuma_monto_factura_gua.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0 Gs"))));
        jFsuma_monto_factura_gua.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFsuma_monto_factura_gua.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jFsuma_total_nota.setBackground(new java.awt.Color(255, 204, 0));
        jFsuma_total_nota.setBorder(javax.swing.BorderFactory.createTitledBorder("TOTAL-NOTA"));
        jFsuma_total_nota.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0 Gs"))));
        jFsuma_total_nota.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFsuma_total_nota.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jFhonorario_despacho.setBackground(new java.awt.Color(255, 255, 102));
        jFhonorario_despacho.setBorder(javax.swing.BorderFactory.createTitledBorder("HONORARIO-DESPA"));
        jFhonorario_despacho.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0 Gs"))));
        jFhonorario_despacho.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFhonorario_despacho.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        javax.swing.GroupLayout panel_insertarLayout = new javax.swing.GroupLayout(panel_insertar);
        panel_insertar.setLayout(panel_insertarLayout);
        panel_insertarLayout.setHorizontalGroup(
            panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTab_item)
            .addGroup(panel_insertarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_insertarLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtlp_fecha_despacho, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jRimportacion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRexportacion)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panel_insertarLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5)
                            .addComponent(cmbvia_transporte, 0, 238, Short.MAX_VALUE)
                            .addComponent(jLabel16)
                            .addComponent(cmbaduana, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1)))
                .addContainerGap())
            .addGroup(panel_insertarLayout.createSequentialGroup()
                .addComponent(btnnuevo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnguardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btneditar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btndeletar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jFsuma_con_comprobante, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                    .addComponent(jFsuma_sin_comprobante))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jFhonorario_despacho, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                    .addComponent(jFsuma_monto_factura))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jFsuma_bole_despa)
                    .addComponent(jFsuma_monto_factura_gua))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jFsuma_total_nota, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panel_insertarLayout.setVerticalGroup(
            panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_insertarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtlp_fecha_despacho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRimportacion)
                    .addComponent(jRexportacion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(panel_insertarLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbvia_transporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbaduana, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTab_item, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_insertarLayout.createSequentialGroup()
                        .addGroup(panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFsuma_sin_comprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFsuma_bole_despa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFsuma_total_nota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFhonorario_despacho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_insertarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jFsuma_monto_factura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jFsuma_monto_factura_gua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jFsuma_con_comprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnnuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btneditar, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btndeletar, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(91, Short.MAX_VALUE))
        );

        jTab_principal.addTab("DATOS PROFORMA", panel_insertar);

        panel_filtro.setBorder(javax.swing.BorderFactory.createTitledBorder("FILTRO DE TABLA"));

        tblliquidacion_proforma.setModel(new javax.swing.table.DefaultTableModel(
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
        tblliquidacion_proforma.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblliquidacion_proformaMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tblliquidacion_proforma);

        javax.swing.GroupLayout panel_filtroLayout = new javax.swing.GroupLayout(panel_filtro);
        panel_filtro.setLayout(panel_filtroLayout);
        panel_filtroLayout.setHorizontalGroup(
            panel_filtroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 968, Short.MAX_VALUE)
        );
        panel_filtroLayout.setVerticalGroup(
            panel_filtroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
        );

        btnimprimir_proforma.setText("IMPRIMIR");
        btnimprimir_proforma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnimprimir_proformaActionPerformed(evt);
            }
        });

        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder("TERCEROS"));

        jLabel34.setText("IMPORTADOR:");

        jLabel35.setText("TRANSPORTADOR:");

        jLabel36.setText("DESPACHANTE:");

        jLabel37.setText("HECHO POR:");

        jPanel15.setBackground(new java.awt.Color(204, 204, 204));
        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder("MONTO TOTAL NOTA"));

        jFsuma_sin_comprobante_select.setBackground(new java.awt.Color(255, 255, 102));
        jFsuma_sin_comprobante_select.setBorder(javax.swing.BorderFactory.createTitledBorder("SIN-COMPROBANTE"));
        jFsuma_sin_comprobante_select.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0 Gs"))));
        jFsuma_sin_comprobante_select.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFsuma_sin_comprobante_select.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jFhonorario_despacho_select.setBackground(new java.awt.Color(255, 255, 102));
        jFhonorario_despacho_select.setBorder(javax.swing.BorderFactory.createTitledBorder("HONORARIO-DESPA"));
        jFhonorario_despacho_select.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0 Gs"))));
        jFhonorario_despacho_select.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFhonorario_despacho_select.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jFsuma_bole_despa_select.setBackground(new java.awt.Color(255, 255, 102));
        jFsuma_bole_despa_select.setBorder(javax.swing.BorderFactory.createTitledBorder("BOLE-DESPACHO"));
        jFsuma_bole_despa_select.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0 Gs"))));
        jFsuma_bole_despa_select.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFsuma_bole_despa_select.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jFsuma_total_nota_select.setBackground(new java.awt.Color(255, 204, 0));
        jFsuma_total_nota_select.setBorder(javax.swing.BorderFactory.createTitledBorder("TOTAL-NOTA"));
        jFsuma_total_nota_select.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0 Gs"))));
        jFsuma_total_nota_select.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFsuma_total_nota_select.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(jFsuma_sin_comprobante_select, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jFhonorario_despacho_select, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jFsuma_bole_despa_select, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jFsuma_total_nota_select, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jFsuma_sin_comprobante_select, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jFhonorario_despacho_select, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jFsuma_bole_despa_select, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jFsuma_total_nota_select, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel18.setBackground(new java.awt.Color(204, 204, 204));

        jLabel38.setText("Conte. Nro:");

        jLabel39.setText("Conte. Tipo:");

        jLabel40.setText("Via:");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel40, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel39, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel38, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtcontenedor_nro)
                    .addComponent(txtcontenedor_tipo, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtvia_transporte, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(txtcontenedor_nro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(txtcontenedor_tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel40)
                    .addComponent(txtvia_transporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel34)
                    .addComponent(jLabel35)
                    .addComponent(jLabel36)
                    .addComponent(jLabel37))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtnom_hecho, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtnom_despachante, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtnom_transportador, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtnom_importador, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel34)
                            .addComponent(txtnom_importador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel35)
                            .addComponent(txtnom_transportador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel36)
                            .addComponent(txtnom_despachante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel37)
                            .addComponent(txtnom_hecho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
        );

        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder("FILTRO"));

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 96, Short.MAX_VALUE)
        );

        btnanular_liquidacion.setText("ANULAR");
        btnanular_liquidacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnanular_liquidacionActionPerformed(evt);
            }
        });

        btnrecargar_liquidacion.setText("RE-CARGAR");
        btnrecargar_liquidacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrecargar_liquidacionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_filtro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnimprimir_proforma, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(btnanular_liquidacion, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnrecargar_liquidacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(panel_filtro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnanular_liquidacion)
                            .addComponent(btnrecargar_liquidacion))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnimprimir_proforma)))
                .addContainerGap(112, Short.MAX_VALUE))
        );

        jTab_principal.addTab("FILTRO TABLA", jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTab_principal)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTab_principal, javax.swing.GroupLayout.PREFERRED_SIZE, 614, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnnuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnuevoActionPerformed
        // TODO add your handling code here:
        boton_nuevo_liquidacion_proforma();
    }//GEN-LAST:event_btnnuevoActionPerformed

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
        // TODO add your handling code here:
        boton_guardar_liquidacion_proforma();
    }//GEN-LAST:event_btnguardarActionPerformed

    private void btneditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditarActionPerformed
        // TODO add your handling code here:
        boton_editar_liquidacion_proforma();
    }//GEN-LAST:event_btneditarActionPerformed

    private void cmbtercero_importadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbtercero_importadorActionPerformed
        // TODO add your handling code here:
        if (hab_cargar_tercero_importador) {
            fk_idtercero_importador = evecomb.getInt_seleccionar_COMBOBOX(conn, cmbtercero_importador, "idtercero", "nombre", "tercero");
            DAOter.cargar_tercero(conn, ENTter, fk_idtercero_importador);
            txtruc_importador.setText(ENTter.getC5ruc());
        }
    }//GEN-LAST:event_cmbtercero_importadorActionPerformed

    private void cmbtercero_transportadoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbtercero_transportadoraActionPerformed
        // TODO add your handling code here:
        if (hab_cargar_tercero_transportadora) {
            fk_idtercero_transportadora = evecomb.getInt_seleccionar_COMBOBOX(conn, cmbtercero_transportadora, "idtercero", "nombre", "tercero");
            DAOter.cargar_tercero(conn, ENTter, fk_idtercero_transportadora);
            txtruc_transportadora.setText(ENTter.getC5ruc());
        }
    }//GEN-LAST:event_cmbtercero_transportadoraActionPerformed

    private void cmbtercero_colaboradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbtercero_colaboradorActionPerformed
        // TODO add your handling code here:
        if (hab_cargar_tercero_colaborador) {
            fk_idtercero_colaborador = evecomb.getInt_seleccionar_COMBOBOX(conn, cmbtercero_colaborador, "idtercero", "nombre", "tercero");
            DAOter.cargar_tercero(conn, ENTter, fk_idtercero_colaborador);
            txtruc_colaborador.setText(ENTter.getC5ruc());
        }
    }//GEN-LAST:event_cmbtercero_colaboradorActionPerformed

    private void cmbtercero_despachanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbtercero_despachanteActionPerformed
        // TODO add your handling code here:
        if (hab_cargar_tercero_despachante) {
            fk_idtercero_despachante = evecomb.getInt_seleccionar_COMBOBOX(conn, cmbtercero_despachante, "idtercero", "nombre", "tercero");
            DAOter.cargar_tercero(conn, ENTter, fk_idtercero_despachante);
            txtruc_despachante.setText(ENTter.getC5ruc());
        }
    }//GEN-LAST:event_cmbtercero_despachanteActionPerformed

    private void cmbaduanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbaduanaActionPerformed
        // TODO add your handling code here:
        if (hab_cargar_aduana) {
            fk_idaduana = evecomb.getInt_seleccionar_COMBOBOX(conn, cmbaduana, "idaduana", "nombre", "aduana");
        }
    }//GEN-LAST:event_cmbaduanaActionPerformed

    private void cmbtc_con_comprobanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbtc_con_comprobanteActionPerformed
        // TODO add your handling code here:
        if (hab_cargar_tc_con_comprobante) {
            fk_idtc_con_comprobante = evecomb.getInt_seleccionar_COMBOBOX(conn, cmbtc_con_comprobante, "idtipo_comprobante", "descripcion", "tipo_comprobante");
        }
    }//GEN-LAST:event_cmbtc_con_comprobanteActionPerformed

    private void btncargar_con_comprobanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncargar_con_comprobanteActionPerformed
        // TODO add your handling code here:
        boton_cargar_item_tc_con_comprobante();
    }//GEN-LAST:event_btncargar_con_comprobanteActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        // TODO add your handling code here:
        ancho_item_tc_con_comprobante();
        ancho_item_tc_sin_comprobante();
        ancho_item_tc_bole_despa();
        ancho_item_tc_mercaderia();
        ancho_fact_nro_proforma();
        DAOlp.ancho_tabla_liquidacion_proforma(tblliquidacion_proforma);
    }//GEN-LAST:event_formInternalFrameOpened

    private void btnimagen_con_comprobanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnimagen_con_comprobanteActionPerformed
        // TODO add your handling code here:
        eveimg.boton_abrir_imagen(tipo_archivo_abrir, txtimagen_con_comprobante);
    }//GEN-LAST:event_btnimagen_con_comprobanteActionPerformed

    private void btneliminar_con_compActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminar_con_compActionPerformed
        // TODO add your handling code here:
        boton_eliminar_item_tc_con_comprobante();
    }//GEN-LAST:event_btneliminar_con_compActionPerformed

    private void txtmonto_con_comprobanteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmonto_con_comprobanteKeyReleased
        // TODO add your handling code here:
        evejtf.getString_format_nro_entero(txtmonto_con_comprobante);
    }//GEN-LAST:event_txtmonto_con_comprobanteKeyReleased

    private void txtmonto_con_comprobanteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmonto_con_comprobanteKeyTyped
        // TODO add your handling code here:
        evejtf.soloNumero(evt);
    }//GEN-LAST:event_txtmonto_con_comprobanteKeyTyped

    private void txtmonto_con_comprobanteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmonto_con_comprobanteKeyPressed
        // TODO add your handling code here:
//        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        evejtf.saltar_campo_enter(evt, txtmonto_con_comprobante, txtreferencia_con_comprobante);
//        }
    }//GEN-LAST:event_txtmonto_con_comprobanteKeyPressed

    private void btncargar_sin_comprobanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncargar_sin_comprobanteActionPerformed
        // TODO add your handling code here:
        boton_cargar_item_tc_sin_comprobante();
    }//GEN-LAST:event_btncargar_sin_comprobanteActionPerformed

    private void btneliminar_sin_comprobanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminar_sin_comprobanteActionPerformed
        // TODO add your handling code here:
        boton_eliminar_item_tc_sin_comprobante();
    }//GEN-LAST:event_btneliminar_sin_comprobanteActionPerformed

    private void btnimagen_sin_comprobanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnimagen_sin_comprobanteActionPerformed
        // TODO add your handling code here:
        eveimg.boton_abrir_imagen(tipo_archivo_abrir, txtimagen_sin_comprobante);
    }//GEN-LAST:event_btnimagen_sin_comprobanteActionPerformed

    private void txtmonto_sin_comprobanteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmonto_sin_comprobanteKeyPressed
        // TODO add your handling code here:
        evejtf.saltar_campo_enter(evt, txtmonto_sin_comprobante, txtreferencia_sin_comprobante);
    }//GEN-LAST:event_txtmonto_sin_comprobanteKeyPressed

    private void txtmonto_sin_comprobanteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmonto_sin_comprobanteKeyReleased
        // TODO add your handling code here:
        evejtf.getString_format_nro_entero(txtmonto_sin_comprobante);
    }//GEN-LAST:event_txtmonto_sin_comprobanteKeyReleased

    private void txtmonto_sin_comprobanteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmonto_sin_comprobanteKeyTyped
        // TODO add your handling code here:
        evejtf.soloNumero(evt);
    }//GEN-LAST:event_txtmonto_sin_comprobanteKeyTyped

    private void cmbtc_sin_comprobanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbtc_sin_comprobanteActionPerformed
        // TODO add your handling code here:
        if (hab_cargar_tc_sin_comprobante) {
            fk_idtc_sin_comprobante = evecomb.getInt_seleccionar_COMBOBOX(conn, cmbtc_sin_comprobante, "idtipo_comprobante", "descripcion", "tipo_comprobante");
        }
    }//GEN-LAST:event_cmbtc_sin_comprobanteActionPerformed

    private void txtlp_tasa_cambio_aduanaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtlp_tasa_cambio_aduanaKeyReleased
        // TODO add your handling code here:
        evejtf.getString_format_nro_entero(txtlp_tasa_cambio_aduana);
    }//GEN-LAST:event_txtlp_tasa_cambio_aduanaKeyReleased

    private void txtlp_tasa_cambio_mercadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtlp_tasa_cambio_mercadoKeyReleased
        // TODO add your handling code here:
        evejtf.getString_format_nro_entero(txtlp_tasa_cambio_mercado);
    }//GEN-LAST:event_txtlp_tasa_cambio_mercadoKeyReleased

    private void txtlp_tasa_cambio_aduanaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtlp_tasa_cambio_aduanaKeyTyped
        // TODO add your handling code here:
        evejtf.soloNumero(evt);
    }//GEN-LAST:event_txtlp_tasa_cambio_aduanaKeyTyped

    private void txtlp_tasa_cambio_mercadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtlp_tasa_cambio_mercadoKeyTyped
        // TODO add your handling code here:
        evejtf.soloNumero(evt);
    }//GEN-LAST:event_txtlp_tasa_cambio_mercadoKeyTyped

    private void btncargar_bole_despaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncargar_bole_despaActionPerformed
        // TODO add your handling code here:
        boton_cargar_item_tc_bole_despa();
    }//GEN-LAST:event_btncargar_bole_despaActionPerformed

    private void btneliminar_bole_despaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminar_bole_despaActionPerformed
        // TODO add your handling code here:
        boton_eliminar_item_tc_bole_despa();
    }//GEN-LAST:event_btneliminar_bole_despaActionPerformed

    private void btnimagen_bole_despaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnimagen_bole_despaActionPerformed
        // TODO add your handling code here:
        eveimg.boton_abrir_imagen(tipo_archivo_abrir, txtimagen_bole_despa);
    }//GEN-LAST:event_btnimagen_bole_despaActionPerformed

    private void txtmonto_bole_despaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmonto_bole_despaKeyPressed
        // TODO add your handling code here:
        evejtf.saltar_campo_enter(evt, txtmonto_bole_despa, txtreferencia_bole_despa);
    }//GEN-LAST:event_txtmonto_bole_despaKeyPressed

    private void txtmonto_bole_despaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmonto_bole_despaKeyReleased
        // TODO add your handling code here:
        evejtf.getString_format_nro_entero(txtmonto_bole_despa);
    }//GEN-LAST:event_txtmonto_bole_despaKeyReleased

    private void txtmonto_bole_despaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmonto_bole_despaKeyTyped
        // TODO add your handling code here:
        evejtf.soloNumero(evt);
    }//GEN-LAST:event_txtmonto_bole_despaKeyTyped

    private void cmbtc_bole_despaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbtc_bole_despaActionPerformed
        // TODO add your handling code here:
        if (hab_cargar_tc_bole_despa) {
            fk_idtc_bole_despa = evecomb.getInt_seleccionar_COMBOBOX(conn, cmbtc_bole_despa, "idtipo_comprobante", "descripcion", "tipo_comprobante");
        }
    }//GEN-LAST:event_cmbtc_bole_despaActionPerformed

    private void txtreferencia_sin_comprobanteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtreferencia_sin_comprobanteKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            boton_cargar_item_tc_sin_comprobante();
        }
    }//GEN-LAST:event_txtreferencia_sin_comprobanteKeyPressed

    private void cmbtc_mercaderiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbtc_mercaderiaActionPerformed
        // TODO add your handling code here:
        if (hab_cargar_tc_mercaderia) {
            fk_idtc_mercaderia = evecomb.getInt_seleccionar_COMBOBOX(conn, cmbtc_mercaderia, "idtipo_comprobante", "descripcion", "tipo_comprobante");
        }
    }//GEN-LAST:event_cmbtc_mercaderiaActionPerformed

    private void btncargar_mercaderiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncargar_mercaderiaActionPerformed
        // TODO add your handling code here:
        boton_cargar_item_tc_mercaderia();
    }//GEN-LAST:event_btncargar_mercaderiaActionPerformed

    private void btneliminar_mercaderiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminar_mercaderiaActionPerformed
        // TODO add your handling code here:
        boton_eliminar_item_tc_mercaderia();
    }//GEN-LAST:event_btneliminar_mercaderiaActionPerformed

    private void btneliminar_fact_nro_proformaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminar_fact_nro_proformaActionPerformed
        // TODO add your handling code here:
        boton_eliminar_item_tc_fac_monto();
    }//GEN-LAST:event_btneliminar_fact_nro_proformaActionPerformed

    private void btncargar_fact_nro_proformaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncargar_fact_nro_proformaActionPerformed
        // TODO add your handling code here:
        boton_cargar_factura_nro_proforma();
    }//GEN-LAST:event_btncargar_fact_nro_proformaActionPerformed

    private void btnimagen_fact_nro_proformaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnimagen_fact_nro_proformaActionPerformed
        // TODO add your handling code here:
        eveimg.boton_abrir_imagen(tipo_archivo_abrir, txtimagen_fact_nro_proforma);
    }//GEN-LAST:event_btnimagen_fact_nro_proformaActionPerformed

    private void cmbmoneda_cambioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbmoneda_cambioActionPerformed
        // TODO add your handling code here:
        if (hab_cargar_moneda_cambio) {
            fk_idmoneda_cambio = evecomb.getInt_seleccionar_COMBOBOX(conn, cmbmoneda_cambio, "idmoneda_cambio", "moneda", "moneda_cambio");
            cargar_dato_select_moneda_cambio(fk_idmoneda_cambio, false);
        }
    }//GEN-LAST:event_cmbmoneda_cambioActionPerformed

    private void txtmonto_fact_nro_proformaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmonto_fact_nro_proformaKeyPressed
        // TODO add your handling code here:
        evejtf.saltar_campo_enter(evt, txtmonto_fact_nro_proforma, txtnro_factura_fact_nro_proforma);
    }//GEN-LAST:event_txtmonto_fact_nro_proformaKeyPressed

    private void txtmonto_fact_nro_proformaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmonto_fact_nro_proformaKeyReleased
        // TODO add your handling code here:
        evejtf.getString_format_nro_entero(txtmonto_fact_nro_proforma);
    }//GEN-LAST:event_txtmonto_fact_nro_proformaKeyReleased

    private void txtmonto_fact_nro_proformaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmonto_fact_nro_proformaKeyTyped
        // TODO add your handling code here:
        evejtf.soloNumero(evt);
    }//GEN-LAST:event_txtmonto_fact_nro_proformaKeyTyped

    private void btnimprimir_proformaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnimprimir_proformaActionPerformed
        // TODO add your handling code here:
        boton_imprimir_proforma();
    }//GEN-LAST:event_btnimprimir_proformaActionPerformed

    private void tblliquidacion_proformaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblliquidacion_proformaMouseReleased
        // TODO add your handling code here:
        seleccionar_tabla_liquidacion_proforma();
    }//GEN-LAST:event_tblliquidacion_proformaMouseReleased

    private void txtnro_factura_fact_nro_proformaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnro_factura_fact_nro_proformaKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            boton_cargar_factura_nro_proforma();
        }
    }//GEN-LAST:event_txtnro_factura_fact_nro_proformaKeyPressed

    private void txtreferencia_con_comprobanteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtreferencia_con_comprobanteKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            boton_cargar_item_tc_con_comprobante();
        }
    }//GEN-LAST:event_txtreferencia_con_comprobanteKeyPressed

    private void txtreferencia_bole_despaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtreferencia_bole_despaKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            boton_cargar_item_tc_bole_despa();
        }
    }//GEN-LAST:event_txtreferencia_bole_despaKeyPressed

    private void btnanular_liquidacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnanular_liquidacionActionPerformed
        // TODO add your handling code here:
        boton_anular_liquidacion_proforma();
    }//GEN-LAST:event_btnanular_liquidacionActionPerformed

    private void btnrecargar_liquidacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrecargar_liquidacionActionPerformed
        // TODO add your handling code here:
        boton_recargar_liquidacion_proforma();
    }//GEN-LAST:event_btnrecargar_liquidacionActionPerformed

    private void jRimportacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRimportacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRimportacionActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnanular_liquidacion;
    private javax.swing.JButton btncargar_bole_despa;
    private javax.swing.JButton btncargar_con_comprobante;
    private javax.swing.JButton btncargar_fact_nro_proforma;
    private javax.swing.JButton btncargar_mercaderia;
    private javax.swing.JButton btncargar_sin_comprobante;
    private javax.swing.JButton btndeletar;
    private javax.swing.JButton btneditar;
    private javax.swing.JButton btneliminar_bole_despa;
    private javax.swing.JButton btneliminar_con_comp;
    private javax.swing.JButton btneliminar_fact_nro_proforma;
    private javax.swing.JButton btneliminar_mercaderia;
    private javax.swing.JButton btneliminar_sin_comprobante;
    private javax.swing.JButton btnguardar;
    private javax.swing.JButton btnimagen_bole_despa;
    private javax.swing.JButton btnimagen_con_comprobante;
    private javax.swing.JButton btnimagen_fact_nro_proforma;
    private javax.swing.JButton btnimagen_sin_comprobante;
    private javax.swing.JButton btnimprimir_proforma;
    private javax.swing.JButton btnnuevo;
    private javax.swing.JButton btnrecargar_liquidacion;
    private javax.swing.JComboBox<String> cmbaduana;
    private javax.swing.JComboBox<String> cmbcontenedor_tipo;
    private javax.swing.JComboBox<String> cmbmoneda_cambio;
    private javax.swing.JComboBox<String> cmbtc_bole_despa;
    private javax.swing.JComboBox<String> cmbtc_con_comprobante;
    private javax.swing.JComboBox<String> cmbtc_mercaderia;
    private javax.swing.JComboBox<String> cmbtc_sin_comprobante;
    private javax.swing.JComboBox<String> cmbtercero_colaborador;
    private javax.swing.JComboBox<String> cmbtercero_despachante;
    private javax.swing.JComboBox<String> cmbtercero_importador;
    private javax.swing.JComboBox<String> cmbtercero_transportadora;
    private javax.swing.JComboBox<String> cmbvia_transporte;
    private javax.swing.ButtonGroup gru_im_ex;
    private javax.swing.ButtonGroup gru_tipocambio;
    private javax.swing.JFormattedTextField jFhonorario_despacho;
    private javax.swing.JFormattedTextField jFhonorario_despacho_select;
    private javax.swing.JFormattedTextField jFsuma_bole_despa;
    private javax.swing.JFormattedTextField jFsuma_bole_despa_select;
    private javax.swing.JFormattedTextField jFsuma_con_comprobante;
    private javax.swing.JFormattedTextField jFsuma_monto_factura;
    private javax.swing.JFormattedTextField jFsuma_monto_factura_gua;
    private javax.swing.JFormattedTextField jFsuma_sin_comprobante;
    private javax.swing.JFormattedTextField jFsuma_sin_comprobante_select;
    private javax.swing.JFormattedTextField jFsuma_total_nota;
    private javax.swing.JFormattedTextField jFsuma_total_nota_select;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRexportacion;
    private javax.swing.JRadioButton jRimportacion;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTabbedPane jTab_item;
    private javax.swing.JTabbedPane jTab_principal;
    private javax.swing.JLabel lblmoneda;
    private javax.swing.JPanel panel_filtro;
    private javax.swing.JPanel panel_insertar;
    private javax.swing.JTable tblfact_nro_proforma;
    private javax.swing.JTable tblitem_bole_despa;
    private javax.swing.JTable tblitem_con_comprobante;
    private javax.swing.JTable tblitem_mercaderia;
    private javax.swing.JTable tblitem_sin_comprobante;
    private javax.swing.JTable tblliquidacion_proforma;
    private javax.swing.JTextField txtcontenedor_nro;
    private javax.swing.JTextField txtcontenedor_tipo;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtimagen_bole_despa;
    private javax.swing.JTextField txtimagen_con_comprobante;
    private javax.swing.JTextField txtimagen_fact_nro_proforma;
    private javax.swing.JTextField txtimagen_sin_comprobante;
    private javax.swing.JTextField txtlp_contenedor_nro;
    private javax.swing.JTextField txtlp_fecha_despacho;
    private javax.swing.JTextArea txtlp_observacion;
    private javax.swing.JTextField txtlp_tasa_cambio_aduana;
    private javax.swing.JTextField txtlp_tasa_cambio_mercado;
    private javax.swing.JTextField txtmonto_bole_despa;
    private javax.swing.JTextField txtmonto_con_comprobante;
    private javax.swing.JTextField txtmonto_fact_nro_proforma;
    private javax.swing.JTextField txtmonto_sin_comprobante;
    private javax.swing.JTextField txtnom_despachante;
    private javax.swing.JTextField txtnom_hecho;
    private javax.swing.JTextField txtnom_importador;
    private javax.swing.JTextField txtnom_transportador;
    private javax.swing.JTextField txtnro_factura_fact_nro_proforma;
    private javax.swing.JTextField txtreferencia_bole_despa;
    private javax.swing.JTextField txtreferencia_con_comprobante;
    private javax.swing.JTextField txtreferencia_sin_comprobante;
    private javax.swing.JTextField txtruc_colaborador;
    private javax.swing.JTextField txtruc_despachante;
    private javax.swing.JTextField txtruc_importador;
    private javax.swing.JTextField txtruc_transportadora;
    private javax.swing.JTextField txtvia_transporte;
    // End of variables declaration//GEN-END:variables

}
