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
import Evento.Combobox.CargaDirectoCombobox;
import Evento.Combobox.EvenCombobox;
import Evento.Fecha.EvenFecha;
import Evento.Imagen.EventoImagen;
import Evento.JLabel.EvenJLabel;
import Evento.JTextField.EvenJTextField;
import Evento.JasperReport.EvenJasperReport;
import Evento.Jframe.EvenJFRAME;
import Evento.Jtable.EvenJtable;
import Evento.Mensaje.EvenMensajeJoptionpane;
import Evento.Utilitario.EvenNumero_a_Letra;
import FORMULARIO.BO.BO_comprobante_liquidacion;
import FORMULARIO.BO.BO_despacho_zona;
import FORMULARIO.BO.BO_incoterms;
import FORMULARIO.BO.BO_item_liquidacion_final;
import FORMULARIO.BO.BO_liquidacion_final;
import FORMULARIO.BO.BO_liquidacion_proforma;
import FORMULARIO.BO.BO_regimen;
import FORMULARIO.BO.BO_tercero_ciudad;
import FORMULARIO.BO.BO_transporte_empresa;
import FORMULARIO.DAO.DAO_aduana;
import FORMULARIO.DAO.DAO_caja_detalle;
import FORMULARIO.DAO.DAO_comprobante_liquidacion;
import FORMULARIO.DAO.DAO_despacho_zona;
import FORMULARIO.DAO.DAO_grupo_credito_cliente;
import FORMULARIO.DAO.DAO_honorario_despacho;
import FORMULARIO.DAO.DAO_incoterms;
import FORMULARIO.DAO.DAO_item_liquidacion_final;
import FORMULARIO.DAO.DAO_liquidacion_final;
import FORMULARIO.DAO.DAO_liquidacion_proforma;
import FORMULARIO.DAO.DAO_moneda_cambio;
import FORMULARIO.DAO.DAO_regimen;
import FORMULARIO.DAO.DAO_tercero;
import FORMULARIO.DAO.DAO_tercero_ciudad;
import FORMULARIO.DAO.DAO_tercero_rubro;
import FORMULARIO.DAO.DAO_tipo_comprobante;
import FORMULARIO.DAO.DAO_transporte_empresa;
import FORMULARIO.DAO.dao_usuario;
import FORMULARIO.ENTIDAD.aduana;
import FORMULARIO.ENTIDAD.caja_detalle;
import FORMULARIO.ENTIDAD.comprobante_liquidacion;
import FORMULARIO.ENTIDAD.credito_cliente;
import FORMULARIO.ENTIDAD.despacho_zona;
import FORMULARIO.ENTIDAD.entidad_usuario;
import FORMULARIO.ENTIDAD.grupo_credito_cliente;
import FORMULARIO.ENTIDAD.honorario_despacho;
import FORMULARIO.ENTIDAD.incoterms;
import FORMULARIO.ENTIDAD.item_liquidacion_final;
import FORMULARIO.ENTIDAD.liquidacion_final;
import FORMULARIO.ENTIDAD.liquidacion_proforma;
import FORMULARIO.ENTIDAD.moneda_cambio;
import FORMULARIO.ENTIDAD.regimen;
import FORMULARIO.ENTIDAD.tercero;
import FORMULARIO.ENTIDAD.tercero_ciudad;
import FORMULARIO.ENTIDAD.tercero_rubro;
import FORMULARIO.ENTIDAD.tipo_comprobante;
import FORMULARIO.ENTIDAD.transporte_empresa;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Digno
 */
public class FrmLiquidacion_final extends javax.swing.JInternalFrame {

    EvenJFRAME evetbl = new EvenJFRAME();
    EvenJtable eveJtab = new EvenJtable();
    EvenCombobox evecomb = new EvenCombobox();
    EventoImagen eveimg = new EventoImagen();
    EvenConexion eveconn = new EvenConexion();
    EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
    EvenJasperReport rep = new EvenJasperReport();
    EvenJLabel evelbl = new EvenJLabel();
    CargaDirectoCombobox carcbm = new CargaDirectoCombobox();
    private ClaVarBuscar vbus = new ClaVarBuscar();
    private EvenJTextField evejtf = new EvenJTextField();
    private EvenFecha evefec = new EvenFecha();
    private dao_usuario dao_usu = new dao_usuario();//dao_usuario
    Connection conn = ConnPostgres.getConnPosgres();
    private cla_color_palete clacolor = new cla_color_palete();
    private tercero ENTter = new tercero();
    private DAO_tercero DAOter = new DAO_tercero();
    private entidad_usuario ENTusu = new entidad_usuario();
    private honorario_despacho ENThd = new honorario_despacho();
    private DAO_honorario_despacho DAOhd = new DAO_honorario_despacho();
    private aduana ENTadu = new aduana();
    private DAO_aduana DAOadu = new DAO_aduana();
    private liquidacion_proforma ENTlp = new liquidacion_proforma();
    private BO_liquidacion_proforma BOlp = new BO_liquidacion_proforma();
    private DAO_liquidacion_proforma DAOlp = new DAO_liquidacion_proforma();
    private moneda_cambio ENTmc = new moneda_cambio();
    private DAO_moneda_cambio DAOmc = new DAO_moneda_cambio();
    private item_liquidacion_final ENTifin = new item_liquidacion_final();
    private DAO_item_liquidacion_final DAOifin = new DAO_item_liquidacion_final();
    private tipo_comprobante ENTtc = new tipo_comprobante();
    private DAO_tipo_comprobante DAOtc = new DAO_tipo_comprobante();
    private caja_detalle ENTcaja = new caja_detalle();
    private DAO_caja_detalle DAOcaja = new DAO_caja_detalle();
    private String creado_por = ENTusu.getGlobal_idusuario() + "-" + ENTusu.getGlobal_nombre();
    private DefaultTableModel model_item_liquidacion = new DefaultTableModel();
    private DefaultTableModel model_texte = new DefaultTableModel();
    private liquidacion_final liqfin = new liquidacion_final();
    private DAO_liquidacion_final DAOliqfin = new DAO_liquidacion_final();
    private BO_liquidacion_final BOliqfin = new BO_liquidacion_final();
    private DAO_grupo_credito_cliente gccDAO = new DAO_grupo_credito_cliente();
    private credito_cliente cclie = new credito_cliente();
    private grupo_credito_cliente gcc = new grupo_credito_cliente();
    private EvenNumero_a_Letra nl = new EvenNumero_a_Letra();
    private comprobante_liquidacion ENTcl = new comprobante_liquidacion();
    private DAO_comprobante_liquidacion DAOcl = new DAO_comprobante_liquidacion();
    private tercero_rubro ENTtr = new tercero_rubro();
    private DAO_tercero_rubro DAOtr = new DAO_tercero_rubro();
    private tercero_ciudad ENTciu = new tercero_ciudad();
    private DAO_tercero_ciudad DAOciu = new DAO_tercero_ciudad();
    private transporte_empresa ENTemp = new transporte_empresa();
    private DAO_transporte_empresa DAOemp = new DAO_transporte_empresa();
    private regimen ENTre = new regimen();
    private DAO_regimen DAOre = new DAO_regimen();
    private incoterms ENTinc = new incoterms();
    private DAO_incoterms DAOinc = new DAO_incoterms();
    private despacho_zona ENTdz = new despacho_zona();
    private DAO_despacho_zona DAOdz = new DAO_despacho_zona();
    private boolean hab_cargar_tercero_importador;
    private static int fk_idtercero_importador;
    private boolean hab_cargar_tercero_exportador;
    private static int fk_idtercero_exportador;
    private boolean hab_cargar_moneda_cambio;
    private int fk_idmoneda_cambio;
    private boolean hab_cargar_aduana;
    private static int fk_idaduana;
    private boolean hab_cargar_transporte_empresa;
    private static int fk_idtransporte_empresa;
    private boolean hab_cargar_despachozona;
    private int fk_iddespacho_zona;
    private boolean hab_cargar_tc_mercaderia;
    private static int fk_idtc_mercaderia;
    private boolean hab_combo_ciudad;
    private static int fk_idtercero_ciudad;
    private boolean hab_combo_regimen;
    private boolean hab_combo_incoterms;
    private static int fk_idtercero_despachante;
    private static int fk_idregimen;
    private static int fk_idincoterms;
    private String tipo_tasa_cambio;
    private double tasa_cambio_aduana = 0;
    private double tasa_cambio_mercado = 0;
    boolean cargado_inicial = true;
    private int fk_idtipo_comprobante;
    private int idliquidacion_final;
    private int orden_item;
    private double monto_total_despacho;
    private double monto_adelanto;
    private double monto_pagar;
    private String monto_letra;
    private double monto_factura;
    private double monto_seguro;
    private double monto_flete;
    private double monto_ajuste_incluir;
    private double monto_cif;
    private double monto_imponible;
    private String liquidacion_impor = "IMPORTACION";
    private String liquidacion_espor = "EXPORTACION";
    private String estado_emitido = "EMITIDO";
    private String estado_anulado = "ANULADO";
    private int idliquidacion_final_select;
    private String tabla_origen = "LIQUIDACION";
    private String estado_pagado = "PAGADO";
    private String tipo_SIN_IVA = "SIN_IVA";
    private String tipo_SOLO_IVA = "SOLO_IVA";
    private String tipo_SIN_Y_SOLO_IVA = "SIN_Y_SOLO_IVA";
    private int fila_select_item_orden_lab;
    private String sin_iva;
    private String solo_iva;

    public static int getFk_idtc_mercaderia() {
        return fk_idtc_mercaderia;
    }

    public static void setFk_idtc_mercaderia(int fk_idtc_mercaderia) {
        FrmLiquidacion_final.fk_idtc_mercaderia = fk_idtc_mercaderia;
    }

    public static int getFk_idtercero_ciudad() {
        return fk_idtercero_ciudad;
    }

    public static void setFk_idtercero_ciudad(int fk_idtercero_ciudad) {
        FrmLiquidacion_final.fk_idtercero_ciudad = fk_idtercero_ciudad;
    }

    public static int getFk_idtercero_importador() {
        return fk_idtercero_importador;
    }

    public static void setFk_idtercero_importador(int fk_idtercero_importador) {
        FrmLiquidacion_final.fk_idtercero_importador = fk_idtercero_importador;
    }

    public static int getFk_idtercero_exportador() {
        return fk_idtercero_exportador;
    }

    public static void setFk_idtercero_exportador(int fk_idtercero_exportador) {
        FrmLiquidacion_final.fk_idtercero_exportador = fk_idtercero_exportador;
    }

    public static int getFk_idaduana() {
        return fk_idaduana;
    }

    public static void setFk_idaduana(int fk_idaduana) {
        FrmLiquidacion_final.fk_idaduana = fk_idaduana;
    }

    public static int getFk_idtransporte_empresa() {
        return fk_idtransporte_empresa;
    }

    public static void setFk_idtransporte_empresa(int fk_idtransporte_empresa) {
        FrmLiquidacion_final.fk_idtransporte_empresa = fk_idtransporte_empresa;
    }

    public static int getFk_idregimen() {
        return fk_idregimen;
    }

    public static void setFk_idregimen(int fk_idregimen) {
        FrmLiquidacion_final.fk_idregimen = fk_idregimen;
    }

    public static int getFk_idincoterms() {
        return fk_idincoterms;
    }

    public static void setFk_idincoterms(int fk_idincoterms) {
        FrmLiquidacion_final.fk_idincoterms = fk_idincoterms;
    }

    public int getFk_iddespacho_zona() {
        return fk_iddespacho_zona;
    }

    public void setFk_iddespacho_zona(int fk_iddespacho_zona) {
        this.fk_iddespacho_zona = fk_iddespacho_zona;
    }

    public static int getFk_idtercero_despachante() {
        return fk_idtercero_despachante;
    }

    public static void setFk_idtercero_despachante(int fk_idtercero_despachante) {
        FrmLiquidacion_final.fk_idtercero_despachante = fk_idtercero_despachante;
    }

    private void abrir_formulario() {
        this.setTitle("LIQUIDACION FINAL");
        evetbl.centrar_formulario_internalframa(this);
        if (cargado_inicial) {
            carcbm.cargarCombobox_via_transporte(cmbvia_transporte);
            carcbm.cargarCombobox_modo_transporte(cmbcontenedor_tipo);
            cargar_moneda_cambio();
            cargar_despacho_zona();
            cargar_montos();
            crear_item_liquidacion_final();
            reestableser_liquidacion();
            cargado_inicial = false;
        }
    }

    void cargar_colores_impor_export() {
        if (jRimportacion.isSelected()) {
            panel_encabezado.setBackground(clacolor.getColor_importacion());
            panel_item_liquidacion.setBackground(clacolor.getColor_importacion());
            panel_cargar_item.setBackground(clacolor.getColor_importacion());
            panel_filtro_liquidacion.setBackground(clacolor.getColor_importacion());
        }
        if (jRexportacion.isSelected()) {
            panel_encabezado.setBackground(clacolor.getColor_exportacion());
            panel_item_liquidacion.setBackground(clacolor.getColor_exportacion());
            panel_cargar_item.setBackground(clacolor.getColor_exportacion());
            panel_filtro_liquidacion.setBackground(clacolor.getColor_exportacion());
        }
    }

    private void cargar_moneda_cambio() {
        evecomb.cargarCombobox(conn, cmbmoneda_cambio, "idmoneda_cambio", "moneda", "moneda_cambio", "");
        hab_cargar_moneda_cambio = true;
    }

    private void cargar_despacho_zona() {
        evecomb.cargarCombobox(conn, cmbdespachozona, "iddespacho_zona", "nombre", "despacho_zona", "");
        hab_cargar_despachozona = true;
    }

    private void cargar_dato_select_moneda_cambio(int fk_idmoneda_cambio, boolean cargarcombo) {
        DAOmc.cargar_moneda_cambio(conn, ENTmc, fk_idmoneda_cambio);
        tipo_tasa_cambio = ENTmc.getC2moneda();
        lblmoneda1.setText(tipo_tasa_cambio);
        lblmoneda2.setText(tipo_tasa_cambio);
        tasa_cambio_aduana = ENTmc.getC3guarani_unidad_aduana();
        tasa_cambio_mercado = ENTmc.getC4guarani_unidad_mercado();
        txtlp_tasa_cambio_aduana.setText(String.valueOf(tasa_cambio_aduana));
        txtlp_tasa_cambio_mercado.setText(String.valueOf(tasa_cambio_mercado));
        if (cargarcombo) {
            cmbmoneda_cambio.setSelectedItem(ENTmc.getC2moneda() + "-(" + ENTmc.getC1idmoneda_cambio() + ")");
        } else {
//            txtmonto_fact_nro_proforma.grabFocus();
        }
    }

    boolean validar_cargamonto() {
        boolean validar = true;
        txtmonto_factura.setBackground(Color.white);
        txtmonto_seguro.setBackground(Color.white);
        txtmonto_flete.setBackground(Color.white);
        txtmonto_ajuste.setBackground(Color.white);
        txtlp_tasa_cambio_aduana.setBackground(Color.white);
        txtlp_tasa_cambio_mercado.setBackground(Color.white);
        if (txtmonto_factura.getText().trim().length() == 0) {
            txtmonto_factura.setBackground(Color.yellow);
            validar = false;
        }
        if (txtmonto_seguro.getText().trim().length() == 0) {
            txtmonto_seguro.setBackground(Color.yellow);
            validar = false;
        }
        if (txtmonto_flete.getText().trim().length() == 0) {
            txtmonto_flete.setBackground(Color.yellow);
            validar = false;
        }
        if (txtmonto_ajuste.getText().trim().length() == 0) {
            txtmonto_ajuste.setBackground(Color.yellow);
            validar = false;
        }
        if (txtlp_tasa_cambio_aduana.getText().trim().length() == 0) {
            txtlp_tasa_cambio_aduana.setBackground(Color.yellow);
            validar = false;
        }
        if (cmbmoneda_cambio.getSelectedIndex() == 0) {
            txtlp_tasa_cambio_aduana.setBackground(Color.yellow);
            txtlp_tasa_cambio_mercado.setBackground(Color.yellow);
            validar = false;
        }
        return validar;
    }

    void cargar_montos() {
        if (validar_cargamonto()) {
            monto_factura = Double.parseDouble(txtmonto_factura.getText());
            monto_seguro = Double.parseDouble(txtmonto_seguro.getText());
            monto_flete = Double.parseDouble(txtmonto_flete.getText());
            tasa_cambio_aduana = Double.parseDouble(txtlp_tasa_cambio_aduana.getText());
            monto_ajuste_incluir = Double.parseDouble(txtmonto_ajuste.getText());
            monto_cif = (monto_factura + monto_seguro + monto_flete);
            monto_imponible = (monto_cif + monto_ajuste_incluir) * tasa_cambio_aduana;
            jFmonto_total.setValue(monto_cif + monto_ajuste_incluir);
            jFmonto_imponible.setValue(monto_imponible);
            jFmonto_cif.setValue(monto_cif);
        }
    }

    void reestableser_liquidacion() {
        eveJtab.mostrar_JTabbedPane(jTab_liquidacion, 0);
        idliquidacion_final = (eveconn.getInt_ultimoID_mas_uno(conn, liqfin.getTb_liquidacion_final(), liqfin.getId_idliquidacion_final()));
        txtidliquidacion_final.setText(String.valueOf(idliquidacion_final));
        cmbmoneda_cambio.setSelectedIndex(3);
        txtmonto_factura.setText("0");
        txtmonto_seguro.setText("0");
        txtmonto_flete.setText("0");
        txtmonto_ajuste.setText("0");
        txtmonto_adelanto.setText("0");
        txtobservacion.setText("NINGUNA");
        txtdespacho_numero.setText(null);
        txtlp_contenedor_nro.setText(null);
        txtfactura_numero.setText(null);
        cmbdespachozona.setSelectedIndex(0);
        txtbuscar_mercaderia.setText(null);
        txtbuscar_destino.setText(null);
        txtbuscar_importador.setText(null);
        txtbuscar_exportador.setText(null);
        txtbuscar_aduana.setText(null);
        txtbuscar_transportadora.setText(null);
        txtbuscar_regimen.setText(null);
        txtregimen_descripcion.setText(null);
        txtbuscar_incoterm.setText(null);
        txtincoterm_descripcion.setText(null);
        txtruc_importador.setText(null);
        txtruc_exportador.setText(null);
        txtimportador_rubro.setText(null);
        txtbuscar_despachante.setText(null);
        txtruc_despachante.setText(null);
        txtotros_nombre.setText("OTROS");
        txtotro_monto.setText("0");
        jFimportador_saldo.setValue(0);
        cmbvia_transporte.setSelectedIndex(0);
        cmbcontenedor_tipo.setSelectedIndex(0);
        setFk_idtc_mercaderia(0);
        setFk_idtercero_ciudad(0);
        setFk_idtercero_importador(0);
        setFk_idtercero_exportador(0);
        setFk_idaduana(0);
        setFk_idtransporte_empresa(0);
        setFk_idregimen(0);
        setFk_idincoterms(0);
        vbus.limpiar_variables_buscar();
        eveJtab.limpiar_tabla_datos(model_item_liquidacion);
        monto_factura = 0;
        monto_seguro = 0;
        monto_flete = 0;
        monto_ajuste_incluir = 0;
        monto_cif = 0;
        monto_imponible = 0;
        jFmonto_total.setValue(0);
        jFmonto_imponible.setValue(0);
        jFmonto_cif.setValue(0);
        txtfecha_despacho.setText(evefec.getString_formato_fecha());
        orden_item = 0;
        jRimportacion.setSelected(true);
        DAOliqfin.actualizar_tabla_liquidacion_final(conn, tblliquidacion, "");
        sumar_item_liquidacion_final();
        cargar_colores_impor_export();
    }

    boolean validar_item_liquidacion_final() {
        if (evejtf.getBoo_JTextField_vacio(txtbucar_comprobante, "DEBE CARGAR UNA DESCRIPCION")) {
            return false;
        }
        if (evejtf.getBoo_JTextField_vacio(txtdespacho_numero_item, "DEBE CARGAR UNA NUMERO COMPROBANTE")) {
            return false;
        }
        if (evejtf.getBoo_JTextField_vacio(txtmonto_guarani_item, "DEBE CARGAR UN MONTO")) {
            return false;
        }
        if (fk_idtipo_comprobante == 0) {
            JOptionPane.showMessageDialog(null, "NO SE CARGO NINGUN COMPROBANTE");
            return false;
        }
        return true;
    }

    void boton_cargar_item_liquidacion_final() {
        if (validar_item_liquidacion_final()) {
            cargar_item_liquidacion_final();
            reestableser_item_liquidacion();
        }
    }

    void reestableser_item_liquidacion() {
        fk_idtipo_comprobante = 0;
        txtbucar_comprobante.setText(null);
        txtdespacho_numero_item.setText(null);
        txtmonto_guarani_item.setText(null);
        txtbucar_comprobante.grabFocus();
    }

    private void cargar_item_liquidacion_final() {
        orden_item++;
        String ord = String.valueOf(orden_item);
        String descripcion = txtbucar_comprobante.getText();
        String comprobante = txtdespacho_numero_item.getText();
        String total = txtmonto_guarani_item.getText();
        String idliqui = String.valueOf(idliquidacion_final);
        String idcompro = String.valueOf(fk_idtipo_comprobante);
        String dato[] = {ord, descripcion, comprobante, total, idliqui, idcompro};
        eveJtab.cargar_tabla_datos(tblitem_liquidacion_final, model_item_liquidacion, dato);
        ancho_item_liquidacion_final();
        sumar_item_liquidacion_final();
    }

    void sumar_item_liquidacion_final() {
        double monto = eveJtab.getDouble_sumar_tabla(tblitem_liquidacion_final, 3);
        double suma_sin_iva = eveJtab.getDouble_sumar_tabla(tblitem_liquidacion_final, 4);
        double suma_solo_iva = eveJtab.getDouble_sumar_tabla(tblitem_liquidacion_final, 5);
        monto_total_despacho = monto;
        if (txtmonto_adelanto.getText().trim().length() > 0) {
            monto_adelanto = Double.parseDouble(txtmonto_adelanto.getText());
        } else {
            monto_adelanto = 0;
        }
        monto_pagar = (monto_total_despacho - monto_adelanto);
        jFmonto_total_despacho.setValue(monto_total_despacho);
        jFmonto_pagar.setValue(monto_pagar);
        monto_letra = nl.Convertir(String.valueOf((int) monto_pagar), true);
        txtmonto_letra.setText(monto_letra);
        jFsuma_sin_iva.setValue(suma_sin_iva);
        jFsuma_solo_iva.setValue(suma_solo_iva);
    }

    void boton_eliminar_item_liquidacion_final() {
        if (!eveJtab.getBoolean_validar_select(tblitem_liquidacion_final)) {
            if (evemen.MensajeGeneral_warning("DESEAS ELIMINAR ESTE FILA", "ELIMINAR", "ACEPTAR", "CANCELAR")) {
                if (eveJtab.getBoolean_Eliminar_Fila(tblitem_liquidacion_final, model_item_liquidacion)) {
                    sumar_item_liquidacion_final();
                }
            }
        }
    }

    boolean validar_liquidacion_final() {
        if (evejtf.getBoo_JTextField_vacio(txtdespacho_numero, "SE DEBE CARGAR UN NUMERO DE DESPACHO")) {
            eveJtab.mostrar_JTabbedPane(jTab_liquidacion, 0);
            return false;
        }
        if (evecomb.getBoo_JCombobox_seleccionar(cmbdespachozona, "SE DEBE CARGAR UNA ZONA")) {
            eveJtab.mostrar_JTabbedPane(jTab_liquidacion, 0);
            return false;
        }
        if (evejtf.getBoo_JTextField_vacio(txtfecha_despacho, "SE DEBE CARGAR LA FECHA DE DESPACHO")) {
            eveJtab.mostrar_JTabbedPane(jTab_liquidacion, 0);
            return false;
        }
        if (getFk_idtercero_importador() == 0) {
            eveJtab.mostrar_JTabbedPane(jTab_liquidacion, 0);
            JOptionPane.showMessageDialog(null, "SE DEBE CARGAR UN IMPORTADOR", "ERROR", JOptionPane.ERROR_MESSAGE);
            txtbuscar_importador.grabFocus();
            return false;
        }
        if (getFk_idtercero_exportador() == 0) {
            eveJtab.mostrar_JTabbedPane(jTab_liquidacion, 0);
            JOptionPane.showMessageDialog(null, "SE DEBE CARGAR UN EXPORTADOR", "ERROR", JOptionPane.ERROR_MESSAGE);
            txtbuscar_exportador.grabFocus();
            return false;
        }
        if (getFk_idtercero_despachante() == 0) {
            eveJtab.mostrar_JTabbedPane(jTab_liquidacion, 0);
            JOptionPane.showMessageDialog(null, "SE DEBE CARGAR UN DESPACHANTE", "ERROR", JOptionPane.ERROR_MESSAGE);
            txtbuscar_despachante.grabFocus();
            return false;
        }
        if (evejtf.getBoo_JTextField_vacio(txtlp_contenedor_nro, "SE DEBE CARGAR UN NUMERO DE CONTENEDOR")) {
            eveJtab.mostrar_JTabbedPane(jTab_liquidacion, 0);
            return false;
        }
        if (getFk_idtc_mercaderia() == 0) {
            eveJtab.mostrar_JTabbedPane(jTab_liquidacion, 0);
            JOptionPane.showMessageDialog(null, "SE DEBE CARGAR UNA MERCADERIA", "ERROR", JOptionPane.ERROR_MESSAGE);
            txtbuscar_mercaderia.grabFocus();
            return false;
        }
        if (getFk_idtercero_ciudad() == 0) {
            eveJtab.mostrar_JTabbedPane(jTab_liquidacion, 0);
            JOptionPane.showMessageDialog(null, "SE DEBE CARGAR UN DESTINO", "ERROR", JOptionPane.ERROR_MESSAGE);
            txtbuscar_destino.grabFocus();
            return false;
        }
        if (evecomb.getBoo_JCombobox_seleccionar(cmbvia_transporte, "SE DEBE CARGAR UNA VIA")) {
            eveJtab.mostrar_JTabbedPane(jTab_liquidacion, 0);
            return false;
        }
        if (getFk_idaduana() == 0) {
            eveJtab.mostrar_JTabbedPane(jTab_liquidacion, 0);
            JOptionPane.showMessageDialog(null, "SE DEBE CARGAR UNA ADUANA", "ERROR", JOptionPane.ERROR_MESSAGE);
            txtbuscar_aduana.grabFocus();
            return false;
        }
        if (getFk_idtransporte_empresa() == 0) {
            eveJtab.mostrar_JTabbedPane(jTab_liquidacion, 0);
            JOptionPane.showMessageDialog(null, "SE DEBE CARGAR UNA TRANSPORTADORA EMPRESA", "ERROR", JOptionPane.ERROR_MESSAGE);
            txtbuscar_transportadora.grabFocus();
            return false;
        }
        if (evecomb.getBoo_JCombobox_seleccionar(cmbcontenedor_tipo, "SE DEBE CARGAR UNA TIPO CONTENEDOR")) {
            eveJtab.mostrar_JTabbedPane(jTab_liquidacion, 0);
            return false;
        }
        if (getFk_idregimen() == 0) {
            eveJtab.mostrar_JTabbedPane(jTab_liquidacion, 0);
            JOptionPane.showMessageDialog(null, "SE DEBE CARGAR UN REGIMEN", "ERROR", JOptionPane.ERROR_MESSAGE);
            txtbuscar_regimen.grabFocus();
            return false;
        }
        if (getFk_idincoterms() == 0) {
            eveJtab.mostrar_JTabbedPane(jTab_liquidacion, 0);
            JOptionPane.showMessageDialog(null, "SE DEBE CARGAR UN INCOTERMS", "ERROR", JOptionPane.ERROR_MESSAGE);
            txtbuscar_incoterm.grabFocus();
            return false;
        }
        if (evecomb.getBoo_JCombobox_seleccionar(cmbmoneda_cambio, "SE DEBE CARGAR UNA MONEDA CAMBIO")) {
            eveJtab.mostrar_JTabbedPane(jTab_liquidacion, 0);
            return false;
        }
        if (evejtf.getBoo_JTextField_vacio(txtfactura_numero, "SE DEBE CARGAR UN NUMERO DE FACTURA")) {
            eveJtab.mostrar_JTabbedPane(jTab_liquidacion, 0);
            return false;
        }
        if (evejtf.getBoo_JTextField_vacio(txtmonto_factura, "SE DEBE CARGAR UN MONTO DE FACTURA")) {
            eveJtab.mostrar_JTabbedPane(jTab_liquidacion, 0);
            return false;
        }
        if (evejtf.getBoo_JTextField_vacio(txtmonto_flete, "SE DEBE CARGAR UN MONTO FLETE")) {
            eveJtab.mostrar_JTabbedPane(jTab_liquidacion, 0);
            return false;
        }
        if (evejtf.getBoo_JTextField_vacio(txtmonto_seguro, "SE DEBE CARGAR UN MONTO SEGURO")) {
            eveJtab.mostrar_JTabbedPane(jTab_liquidacion, 0);
            return false;
        }
        if (evejtf.getBoo_JTextField_vacio(txtmonto_ajuste, "SE DEBE CARGAR UN MONTO DE AJUSTE")) {
            eveJtab.mostrar_JTabbedPane(jTab_liquidacion, 0);
            return false;
        }
        if (evejtf.getBoo_JTextarea_vacio(txtobservacion, "SE DEBE CARGAR UNA OBSERVACION")) {
            eveJtab.mostrar_JTabbedPane(jTab_liquidacion, 0);
            return false;
        }
        if (monto_imponible == 0) {
            JOptionPane.showMessageDialog(null, "NO SE ENCONTRO NINGUN VALOR IMPONIBLE CARGADO");
            eveJtab.mostrar_JTabbedPane(jTab_liquidacion, 0);
            return false;
        }
        if (eveJtab.getBoolean_validar_cant_cargado(tblitem_liquidacion_final)) {
            cargar_pre_item_liquidacion();
        }
        if (monto_pagar == 0) {
            JOptionPane.showMessageDialog(null, "NO SE ENCONTRO NINGUN VALOR PAGA PAGAR");
            eveJtab.mostrar_JTabbedPane(jTab_liquidacion, 1);
            return false;
        }
        if (eveJtab.getBoolean_validar_cant_cargado(tblitem_liquidacion_final)) {
            eveJtab.mostrar_JTabbedPane(jTab_liquidacion, 1);
            return false;
        }
        if (evejtf.getBoo_JTextField_vacio(txtotros_nombre, "SE DEBE CARGAR UN nombre")) {
            eveJtab.mostrar_JTabbedPane(jTab_liquidacion, 1);
            return false;
        }
        if (evejtf.getBoo_JTextField_vacio(txtotro_monto, "SE DEBE CARGAR UN MONTO")) {
            eveJtab.mostrar_JTabbedPane(jTab_liquidacion, 1);
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

    private void settipo_liquidacion(String liquidacion) {
        if (liquidacion.equals(liquidacion_impor)) {
            jRimportacion.setSelected(true);
        }
        if (liquidacion.equals(liquidacion_espor)) {
            jRexportacion.setSelected(true);
        }
    }

    private void cargar_dato_caja_detalle() {
        DAOcaja.vaciar_caja_detalle(ENTcaja);
        ENTcaja.setC3creado_por(creado_por);
        ENTcaja.setC4descripcion("DESPACHO: " + txtdespacho_numero.getText());
        ENTcaja.setC5estado(estado_emitido);
        ENTcaja.setC6monto_liquidacion_credito(monto_pagar);
        ENTcaja.setC13fk_idliquidacion_final(idliquidacion_final);
        ENTcaja.setC10fk_idusuario(ENTusu.getGlobal_idusuario());
    }

    void cargar_dato_liquidacion_final() {
        liqfin.setC3creado_por(creado_por);
        liqfin.setC4fecha_despacho(txtfecha_despacho.getText());
        liqfin.setC5despacho_numero(txtdespacho_numero.getText());
        liqfin.setC6tipo_liquidacion(gettipo_liquidacion());
        liqfin.setC7estado(estado_emitido);
        liqfin.setC8observacion(txtobservacion.getText());
        liqfin.setC9contenedor_nro(txtlp_contenedor_nro.getText());
        liqfin.setC10contenedor_tipo(cmbcontenedor_tipo.getSelectedItem().toString());
        liqfin.setC11via_transporte(cmbvia_transporte.getSelectedItem().toString());
        liqfin.setC12transporte_condicion(cmbcontenedor_tipo.getSelectedItem().toString());
        liqfin.setC13monto_imponible(monto_imponible);
        liqfin.setC14monto_ajuste_incluir(monto_ajuste_incluir);
        liqfin.setC15monto_factura(monto_factura);
        liqfin.setC16monto_flete(monto_flete);
        liqfin.setC17monto_seguro(monto_seguro);
        liqfin.setC18monto_cif(monto_cif);
        liqfin.setC19monto_total_despacho(monto_total_despacho);
        liqfin.setC20monto_adelanto(monto_adelanto);
        liqfin.setC21monto_pagar(monto_pagar);
        liqfin.setC22tasa_cambio_aduana(tasa_cambio_aduana);
        liqfin.setC23tasa_cambio_mercado(tasa_cambio_mercado);
        liqfin.setC24tipo_tasa_cambio(tipo_tasa_cambio);
        liqfin.setC25factura_numero(txtfactura_numero.getText());
        liqfin.setC26monto_letra(monto_letra);
        liqfin.setC27fk_idtipo_comprobante(getFk_idtc_mercaderia());
        liqfin.setC28fk_idtercero_ciudad(getFk_idtercero_ciudad());
        liqfin.setC29fk_idaduana(getFk_idaduana());
        liqfin.setC30fk_iddespacho_zona(getFk_iddespacho_zona());
        liqfin.setC31fk_idtransporte_empresa(getFk_idtransporte_empresa());
        liqfin.setC32fk_idtercero_importador(getFk_idtercero_importador());
        liqfin.setC33fk_idtercero_transportadora(getFk_idtercero_exportador());
        liqfin.setC34fk_idmoneda_cambio(fk_idmoneda_cambio);
        liqfin.setC35fk_idregimen(getFk_idregimen());
        liqfin.setC36fk_idincoterms(getFk_idincoterms());
        liqfin.setC38monto_pagado(0);
        liqfin.setC39otro_nombre(txtotros_nombre.getText());
        liqfin.setC40otro_monto(Double.parseDouble(txtotro_monto.getText()));
        liqfin.setC41fk_idtercero_despachante(getFk_idtercero_despachante());
        liqfin.setC42fk_idrecibo_pago_tercero(0);
    }

    void cargar_dato_select_liquidacion_final() {

    }

    private void cargar_credito_cliente() {
        if (true) {
            double monto_credito = monto_pagar;
            gccDAO.cargar_grupo_credito_cliente_id(conn, gcc, getFk_idtercero_importador());
            cclie.setC3descripcion("Nro: " + txtdespacho_numero.getText());
            cclie.setC4estado(estado_emitido);
            cclie.setC5monto_contado(0);
            cclie.setC6monto_credito(monto_credito);
            cclie.setC7tabla_origen(tabla_origen);
            cclie.setC8fk_idgrupo_credito_cliente(gcc.getC1idgrupo_credito_cliente());
            cclie.setC9fk_idsaldo_credito_cliente(0);
            cclie.setC10fk_idrecibo_pago_cliente(0);
            cclie.setC11fk_idventa_alquiler(idliquidacion_final);
            cclie.setC12vence(true);
            cclie.setC13fecha_vence(evefec.getString_formato_fecha_hora_zona());
            ENTter.setC1idtercero(getFk_idtercero_importador());
        }
    }

    void boton_guardar_liquidacion_final() {
        if (validar_liquidacion_final()) {
            cargar_dato_liquidacion_final();
            cargar_credito_cliente();
            cargar_dato_caja_detalle();
            if (BOliqfin.getBoolean_insertar_liquidacion_final(liqfin, tblitem_liquidacion_final, cclie, ENTter, ENTcaja)) {
                DAOliqfin.imprimir_rep_liquidacion_final(conn, idliquidacion_final);
                reestableser_liquidacion();
            }
        }
    }

    void seleccionar_liquidacion() {
        idliquidacion_final_select = eveJtab.getInt_select_id(tblliquidacion);
        String estado = eveJtab.getString_select(tblliquidacion, 12);
        if (estado.equals(estado_pagado)) {
            btnanular.setEnabled(false);
            btnrecargar_liquidacion.setEnabled(false);
        }
        if (estado.equals(estado_emitido)) {
            btnanular.setEnabled(true);
            btnrecargar_liquidacion.setEnabled(false);
        }
        if (estado.equals(estado_anulado)) {
            btnanular.setEnabled(false);
            btnrecargar_liquidacion.setEnabled(true);
        }
        DAOifin.actualizar_tabla_item_liquidacion_final_por_id(conn, tblitem_liquidacion_final_id, idliquidacion_final_select);
    }

    void boton_recargar_liquidacion_final() {
        if (!eveJtab.getBoolean_validar_select(tblliquidacion)) {
            idliquidacion_final_select = eveJtab.getInt_select_id(tblliquidacion);
            DAOliqfin.cargar_liquidacion_final(conn, liqfin, idliquidacion_final_select);
            txtdespacho_numero.setText(liqfin.getC5despacho_numero());
            setFk_idtercero_importador(liqfin.getC32fk_idtercero_importador());
            DAOter.cargar_tercero(conn, ENTter, liqfin.getC32fk_idtercero_importador());
            txtbuscar_importador.setText(ENTter.getC4nombre());
            txtruc_importador.setText(ENTter.getC5ruc());
            DAOtr.cargar_tercero_rubro(conn, ENTtr, ENTter.getC18fk_idtercero_rubro());
            txtimportador_rubro.setText(ENTtr.getC2nombre());
            jFimportador_saldo.setValue(ENTter.getC17saldo_credito());
            setFk_idtercero_exportador(liqfin.getC33fk_idtercero_transportadora());
            DAOter.cargar_tercero(conn, ENTter, liqfin.getC33fk_idtercero_transportadora());
            txtbuscar_exportador.setText(ENTter.getC4nombre());
            txtruc_exportador.setText(ENTter.getC5ruc());
            txtlp_contenedor_nro.setText(liqfin.getC9contenedor_nro());
            setFk_idtc_mercaderia(liqfin.getC27fk_idtipo_comprobante());
            DAOtc.cargar_tipo_comprobante(conn, ENTtc, liqfin.getC27fk_idtipo_comprobante());
            txtbuscar_mercaderia.setText(ENTtc.getC2descripcion());
            setFk_idtercero_ciudad(liqfin.getC28fk_idtercero_ciudad());
            DAOciu.cargar_tercero_ciudad(conn, ENTciu, liqfin.getC28fk_idtercero_ciudad());
            txtbuscar_destino.setText(ENTciu.getC2nombre());
            setFk_idaduana(liqfin.getC29fk_idaduana());
            DAOadu.cargar_aduana(conn, ENTadu, liqfin.getC29fk_idaduana());
            txtbuscar_aduana.setText(ENTadu.getC2nombre());
            txtsigla_aduana.setText(ENTadu.getC5sigla());
            setFk_idtransporte_empresa(liqfin.getC31fk_idtransporte_empresa());
            DAOemp.cargar_transporte_empresa(conn, ENTemp, liqfin.getC31fk_idtransporte_empresa());
            txtbuscar_transportadora.setText(ENTemp.getC2nombre());
            setFk_idregimen(liqfin.getC35fk_idregimen());
            DAOre.cargar_regimen(conn, ENTre, liqfin.getC35fk_idregimen());
            txtbuscar_regimen.setText(ENTre.getC3sigla());
            txtregimen_descripcion.setText(ENTre.getC2nombre());
            setFk_idincoterms(liqfin.getC36fk_idincoterms());
            DAOinc.cargar_incoterms(conn, ENTinc, liqfin.getC36fk_idincoterms());
            txtbuscar_incoterm.setText(ENTinc.getC3sigla());
            txtincoterm_descripcion.setText(ENTinc.getC2nombre());
            setFk_iddespacho_zona(liqfin.getC30fk_iddespacho_zona());
            DAOdz.cargar_despacho_zona(conn, ENTdz, liqfin.getC30fk_iddespacho_zona());
            evecomb.setSeleccionarCombobox(cmbdespachozona, liqfin.getC30fk_iddespacho_zona(), ENTdz.getC2nombre());
            cmbvia_transporte.setSelectedItem(liqfin.getC11via_transporte());
            cmbcontenedor_tipo.setSelectedItem(liqfin.getC10contenedor_tipo());
            txtobservacion.setText(liqfin.getC8observacion());
            txtfecha_despacho.setText(liqfin.getC4fecha_despacho());
            txtfactura_numero.setText(liqfin.getC25factura_numero());
            txtmonto_factura.setText(String.valueOf(liqfin.getC15monto_factura()));
            txtmonto_flete.setText(String.valueOf(liqfin.getC16monto_flete()));
            txtmonto_seguro.setText(String.valueOf(liqfin.getC17monto_seguro()));
            txtmonto_ajuste.setText(String.valueOf(liqfin.getC14monto_ajuste_incluir()));
            txtmonto_adelanto.setText(String.valueOf(liqfin.getC20monto_adelanto()));
            cargar_montos();
            settipo_liquidacion(liqfin.getC6tipo_liquidacion());
            cargar_colores_impor_export();
            txtotros_nombre.setText(liqfin.getC39otro_nombre());
            txtotro_monto.setText(String.valueOf(liqfin.getC40otro_monto()));
            reacargar_pre_item_liquidacion(idliquidacion_final_select);
        }
    }

    public void reacargar_pre_item_liquidacion(int idliquidacion_final_select) {
        idliquidacion_final = (eveconn.getInt_ultimoID_mas_uno(conn, liqfin.getTb_liquidacion_final(), liqfin.getId_idliquidacion_final()));
        String titulo = "cargar_pre_item_liquidacion";
        String sql = "select ilf.orden, ilf.descripcion,ilf.comprobante_nro,"
                + "ilf.total_guarani as total, \n"
                + "ilf.sin_iva as sin_iva, \n"
                + "ilf.solo_iva as solo_iva,ilf.fk_idcomprobante_liquidacion \n"
                + "from item_liquidacion_final ilf \n"
                + "where ilf.fk_idliquidacion_final=" + idliquidacion_final_select
                + " order by 1 asc;";
        eveJtab.limpiar_tabla_datos(model_item_liquidacion);
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql, titulo);
            while (rs.next()) {
                String idcomprobante_liquidacion = rs.getString("fk_idcomprobante_liquidacion");
                String orden = rs.getString("orden");
                String descripcion = rs.getString("descripcion");
                String idliqui = String.valueOf(idliquidacion_final);
                String comprobante = rs.getString("comprobante_nro");
                String total = rs.getString("total");
                String por_iva = rs.getString("sin_iva");
                String tipo_iva = rs.getString("solo_iva");
                String dato[] = {orden, descripcion, comprobante, total, por_iva, tipo_iva, idliqui, idcomprobante_liquidacion};
                eveJtab.cargar_tabla_datos(tblitem_liquidacion_final, model_item_liquidacion, dato);
            }
            ancho_item_liquidacion_final();
            sumar_item_liquidacion_final();
        } catch (Exception e) {
            evemen.mensaje_error(e, sql, titulo);
        }
    }

    private void boton_imprimir_liquidacion_final() {
        if (dao_usu.getBoolean_hab_evento_mensaje_error(conn, "24")) {
            if (!eveJtab.getBoolean_validar_select(tblliquidacion)) {
                DAOliqfin.imprimir_rep_liquidacion_final(conn, idliquidacion_final_select);
            }
        }
    }

    private void boton_anular_liquidacion_final() {
        if (dao_usu.getBoolean_hab_evento_mensaje_error(conn, "24")) {
            if (!eveJtab.getBoolean_validar_select(tblliquidacion)) {
                liqfin.setC7estado(estado_anulado);
                liqfin.setC1idliquidacion_final(idliquidacion_final_select);
                int idliquidacion_final = eveJtab.getInt_select_id(tblliquidacion);
                int fk_idtercero = eveJtab.getInt_select(tblliquidacion, 1);
                cclie.setC4estado(estado_anulado);
                cclie.setC11fk_idventa_alquiler(idliquidacion_final);
                ENTter.setC1idtercero(fk_idtercero);
                ENTcaja.setC5estado(estado_anulado);
                ENTcaja.setNom_campo_todos("fk_idliquidacion_final");
                ENTcaja.setFk_idtodos(idliquidacion_final);
                if (BOliqfin.getBoolean_update_venta_alquiler_anular(liqfin, cclie, ENTter, ENTcaja)) {
                    DAOliqfin.actualizar_tabla_liquidacion_final(conn, tblliquidacion, "");
                }
            }
        }
    }

    private void crear_item_liquidacion_final() {
        String dato[] = {"ord", "descripcion", "comprobante", "total", "sin_iva", "solo_iva", "idliqui", "idcompro"};
        eveJtab.crear_tabla_datos(tblitem_liquidacion_final, model_item_liquidacion, dato);
        eveJtab.ocultar_columna(tblitem_liquidacion_final, 6);
        eveJtab.ocultar_columna(tblitem_liquidacion_final, 7);
    }

    void ancho_item_liquidacion_final() {
        int Ancho[] = {8, 40, 20, 10, 10, 10, 1, 1};
        eveJtab.setAnchoColumnaJtable(tblitem_liquidacion_final, Ancho);
    }

    public void cargar_pre_item_liquidacion() {

        if (txtdespacho_numero.getText().trim().length() > 0) {
            String titulo = "cargar_pre_item_liquidacion";
            String sql = "select cl.idcomprobante_liquidacion, \n"
                    + "pilf.orden,cl.descripcion,cl.por_iva,cl.tipo_iva,cl.nro_despacho \n"
                    + "from pre_item_liquidacion_final pilf,comprobante_liquidacion cl \n"
                    + "where pilf.fk_idcomprobante_liquidacion=cl.idcomprobante_liquidacion \n"
                    + "order by pilf.orden asc; ";
            eveJtab.limpiar_tabla_datos(model_item_liquidacion);
            try {
                ResultSet rs = eveconn.getResulsetSQL(conn, sql, titulo);
                while (rs.next()) {
                    String idcomprobante_liquidacion = rs.getString("idcomprobante_liquidacion");
                    String orden = rs.getString("orden");
                    String descripcion = rs.getString("descripcion");
                    boolean nro_despacho = rs.getBoolean("nro_despacho");
                    String idliqui = String.valueOf(idliquidacion_final);
                    String comprobante = "x";
                    if (nro_despacho) {
                        comprobante = txtdespacho_numero.getText();
                    }
                    String total = "0";
                    String por_iva = "0";
                    String tipo_iva = "0";
                    String dato[] = {orden, descripcion, comprobante, total, por_iva, tipo_iva, idliqui, idcomprobante_liquidacion};
                    eveJtab.cargar_tabla_datos(tblitem_liquidacion_final, model_item_liquidacion, dato);
                }
                ancho_item_liquidacion_final();
                sumar_item_liquidacion_final();
            } catch (Exception e) {
                evemen.mensaje_error(e, sql, titulo);
            }
        } else {
            JOptionPane.showMessageDialog(null, "CARGAR UN NUMERO DE DESPACHO", "ERROR", JOptionPane.ERROR_MESSAGE);
            eveJtab.mostrar_JTabbedPane(jTab_liquidacion, 0);
            txtdespacho_numero.grabFocus();
        }
    }

    boolean validar_item_pre_item_liquidacion() {
        if (tblitem_liquidacion_final.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(null, "SELECCIONE EN ITEM");
            return false;
        }
        if (evejtf.getBoo_JTextField_vacio(txtdespacho_numero_item, "SE DEBE CARGAR UN NUMERO COMPROBANTE")) {
            return false;
        }
        if (evejtf.getBoo_JTextField_vacio(txtbucar_comprobante, "SE DEBE CARGAR UN NOMBRE COMPROBANTE")) {
            return false;
        }

        return true;
    }

    void calcular_iva_desdemonto() {
        if (txtmonto_guarani_item.getText().trim().length() > 0) {
            String total = txtmonto_guarani_item.getText();
            lbltipo_iva.setText(ENTcl.getC4tipo_iva());
            double por_iva = 0;
            double Itotal = 0;
            double Isolo_iva = 0;
            double Isin_iva = 0;
            if (ENTcl.getC3por_iva() == 10) {
                por_iva = 11;
            }
            if (ENTcl.getC3por_iva() == 5) {
                por_iva = 21;
            }
            sin_iva = "0";
            solo_iva = "0";
            if (ENTcl.getC4tipo_iva().equals(tipo_SIN_IVA)) {
                sin_iva = total;
            }
            if (ENTcl.getC4tipo_iva().equals(tipo_SOLO_IVA)) {
                solo_iva = total;
            }
            if (ENTcl.getC4tipo_iva().equals(tipo_SIN_Y_SOLO_IVA)) {
                Itotal = Double.parseDouble(total);
                Isolo_iva = Itotal / por_iva;
                Isin_iva = Itotal - Isolo_iva;
                sin_iva = String.valueOf((int) Isin_iva);
                solo_iva = String.valueOf((int) Isolo_iva);
            }
            jFsin_iva.setValue(Integer.parseInt(sin_iva));
            jFsolo_iva.setValue(Integer.parseInt(solo_iva));
        }
    }

    private void update_item_pre_item_liquidacion() {
        if (validar_item_pre_item_liquidacion()) {
            if (txtmonto_guarani_item.getText().trim().length() == 0) {
                txtmonto_guarani_item.setText("0");
            }
            int row = tblitem_liquidacion_final.getSelectedRow();
            int idcomprobante_liquidacion = eveJtab.getInt_select(tblitem_liquidacion_final, 7);
            DAOcl.cargar_comprobante_liquidacion(conn, ENTcl, idcomprobante_liquidacion);
            fila_select_item_orden_lab++;
            tblitem_liquidacion_final.requestFocus();
            System.out.println("fila_select_item_orden_lab:" + fila_select_item_orden_lab);
            if (fila_select_item_orden_lab >= tblitem_liquidacion_final.getRowCount()) {
                fila_select_item_orden_lab = 0;
            }
            tblitem_liquidacion_final.changeSelection(fila_select_item_orden_lab, 0, false, false);
            String total = txtmonto_guarani_item.getText();
            String comprobante = txtdespacho_numero_item.getText();
            String descripcion = txtbucar_comprobante.getText();
            calcular_iva_desdemonto();
            model_item_liquidacion.setValueAt(solo_iva, row, 5);
            model_item_liquidacion.setValueAt(sin_iva, row, 4);
            model_item_liquidacion.setValueAt(total, row, 3);
            model_item_liquidacion.setValueAt(comprobante, row, 2);
            model_item_liquidacion.setValueAt(descripcion, row, 1);
            seleccionar_pre_item_liquidacion();
            sumar_item_liquidacion_final();
        }
    }

    void seleccionar_pre_item_liquidacion() {
        if (tblitem_liquidacion_final.getSelectedRow() >= 0) {
            fila_select_item_orden_lab = tblitem_liquidacion_final.getSelectedRow();
            String descripcion = eveJtab.getString_select(tblitem_liquidacion_final, 1);
            String comprobante = eveJtab.getString_select(tblitem_liquidacion_final, 2);
            String monto = eveJtab.getString_select(tblitem_liquidacion_final, 3);
            String sin_iva = eveJtab.getString_select(tblitem_liquidacion_final, 4);
            String solo_iva = eveJtab.getString_select(tblitem_liquidacion_final, 5);
            int idcomprobante_liquidacion = eveJtab.getInt_select(tblitem_liquidacion_final, 7);
            lblidcomprobante.setText(String.valueOf(idcomprobante_liquidacion));
            DAOcl.cargar_comprobante_liquidacion(conn, ENTcl, idcomprobante_liquidacion);
            lbltipo_iva.setText(ENTcl.getC4tipo_iva());
            txtbucar_comprobante.setText(descripcion);
            txtdespacho_numero_item.setText(comprobante);
            if (monto.equals("0")) {
                txtmonto_guarani_item.setText(null);
            } else {
                txtmonto_guarani_item.setText(monto);
            }
            jFsin_iva.setValue(Integer.parseInt(sin_iva));
            jFsolo_iva.setValue(Integer.parseInt(solo_iva));
            txtmonto_guarani_item.grabFocus();
        }
    }

    private void buscar_nro_despacho_separar() {
        if (txtdespacho_numero.getText().trim().length() > 0) {
            String despacho_numero = txtdespacho_numero.getText();
            String ano = despacho_numero.substring(0, 2);
            String sigla_aduana = despacho_numero.substring(2, 5);
            String regimen = despacho_numero.substring(5, 9);
            String numero = despacho_numero.substring(9, despacho_numero.length());
            System.out.println("despacho_numero:" + despacho_numero);
            System.out.println("ano:" + ano);
            System.out.println("sigla_aduana:" + sigla_aduana);
            System.out.println("regimen:" + regimen);
            System.out.println("numero:" + numero);
            buscar_por_despacho_aduana(sigla_aduana);
            buscar_por_despacho_regimen(regimen);
        }
    }

    private void abrir_buscar(int tipo, String nombre, JTextField txtbuscar) {
        vbus.setTipo_tabla(tipo);
        vbus.setNombre_tabla(nombre);
        vbus.setPre_busqueda(txtbuscar.getText());
        JDiaBuscarDosColumnas frm = new JDiaBuscarDosColumnas(null, true);
        frm.setVisible(true);
    }

    void buscar_por_despacho_aduana(String sigla_aduana) {
        String titulo = "buscar_por_despacho_aduana";
        String sql = "select idaduana,nombre,sigla  \n"
                + "from aduana   \n"
                + "where  sigla='" + sigla_aduana + "';";
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql, titulo);
            if (rs.next()) {
                int idaduana = rs.getInt("idaduana");
                String nombre = rs.getString("nombre");
                String sigla = rs.getString("sigla");
                txtbuscar_aduana.setText(nombre);
                txtsigla_aduana.setText(sigla);
                setFk_idaduana(idaduana);
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql, titulo);
        }
    }
void buscar_por_despacho_regimen(String regimen) {
        String titulo = "buscar_por_despacho_regimen";
        String sql = "select idregimen,nombre,sigla  \n"
                + "from regimen   \n"
                + "where  sigla='" + regimen + "';";
        try {
            ResultSet rs = eveconn.getResulsetSQL(conn, sql, titulo);
            if (rs.next()) {
                int idregimen = rs.getInt("idregimen");
                String nombre = rs.getString("nombre");
                String sigla = rs.getString("sigla");
                txtbuscar_regimen.setText(sigla);
                txtregimen_descripcion.setText(nombre);
                setFk_idregimen(idregimen);
            }
        } catch (Exception e) {
            evemen.mensaje_error(e, sql, titulo);
        }
    }
    public FrmLiquidacion_final() {
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

        gru_impexp = new javax.swing.ButtonGroup();
        jTab_liquidacion = new javax.swing.JTabbedPane();
        panel_encabezado = new javax.swing.JPanel();
        jRimportacion = new javax.swing.JRadioButton();
        jRexportacion = new javax.swing.JRadioButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtruc_exportador = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        txtfactura_numero = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtmonto_factura = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtmonto_flete = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtmonto_seguro = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtmonto_ajuste = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jFmonto_imponible = new javax.swing.JFormattedTextField();
        jFmonto_cif = new javax.swing.JFormattedTextField();
        jLabel27 = new javax.swing.JLabel();
        jFmonto_total = new javax.swing.JFormattedTextField();
        lblmoneda1 = new javax.swing.JLabel();
        lblmoneda2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtlp_contenedor_nro = new javax.swing.JTextField();
        lblmercaderia = new javax.swing.JLabel();
        lbldestino = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cmbvia_transporte = new javax.swing.JComboBox<>();
        lbladuana = new javax.swing.JLabel();
        lbltransportadora_empresa = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cmbcontenedor_tipo = new javax.swing.JComboBox<>();
        lblregimen = new javax.swing.JLabel();
        lblincoterms = new javax.swing.JLabel();
        btnbuscar_mercaderia = new javax.swing.JButton();
        txtbuscar_mercaderia = new javax.swing.JTextField();
        txtbuscar_destino = new javax.swing.JTextField();
        txtbuscar_aduana = new javax.swing.JTextField();
        txtbuscar_transportadora = new javax.swing.JTextField();
        txtbuscar_regimen = new javax.swing.JTextField();
        txtbuscar_incoterm = new javax.swing.JTextField();
        txtregimen_descripcion = new javax.swing.JTextField();
        txtincoterm_descripcion = new javax.swing.JTextField();
        btnbuscar_destino = new javax.swing.JButton();
        btnbuscar_aduana = new javax.swing.JButton();
        txtsigla_aduana = new javax.swing.JTextField();
        btnbuscar_regimen = new javax.swing.JButton();
        btnbuscar_incoterm = new javax.swing.JButton();
        btnbuscar_transportadora = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtlp_tasa_cambio_aduana = new javax.swing.JTextField();
        txtlp_tasa_cambio_mercado = new javax.swing.JTextField();
        cmbmoneda_cambio = new javax.swing.JComboBox<>();
        lblmoneda = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtidliquidacion_final = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtfecha_despacho = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtobservacion = new javax.swing.JTextArea();
        jLabel12 = new javax.swing.JLabel();
        txtdespacho_numero = new javax.swing.JTextField();
        lbldespacho_zona = new javax.swing.JLabel();
        cmbdespachozona = new javax.swing.JComboBox<>();
        btnguardar1 = new javax.swing.JButton();
        btnnuevo1 = new javax.swing.JButton();
        txtbuscar_exportador = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        btnbuscar_exportador = new javax.swing.JButton();
        panel_cliente = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtbuscar_importador = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtruc_importador = new javax.swing.JTextField();
        btnbuscar_importador = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        txtimportador_rubro = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jFimportador_saldo = new javax.swing.JFormattedTextField();
        jLabel35 = new javax.swing.JLabel();
        txtbuscar_despachante = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        txtruc_despachante = new javax.swing.JTextField();
        btnbuscar_despachante = new javax.swing.JButton();
        panel_item_liquidacion = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblitem_liquidacion_final = new javax.swing.JTable();
        panel_cargar_item = new javax.swing.JPanel();
        lblcomprobante = new javax.swing.JLabel();
        txtbucar_comprobante = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        txtdespacho_numero_item = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        txtmonto_guarani_item = new javax.swing.JTextField();
        btnnro_despacho = new javax.swing.JButton();
        lbltipo_iva = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jFsin_iva = new javax.swing.JFormattedTextField();
        jLabel16 = new javax.swing.JLabel();
        jFsolo_iva = new javax.swing.JFormattedTextField();
        lblidcomprobante = new javax.swing.JLabel();
        btneditar_item_comprobante = new javax.swing.JButton();
        btneliminar_item_liquidacion = new javax.swing.JButton();
        btncargar_pre_item = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jFsuma_solo_iva = new javax.swing.JFormattedTextField();
        jFsuma_sin_iva = new javax.swing.JFormattedTextField();
        jLabel18 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jFmonto_total_despacho = new javax.swing.JFormattedTextField();
        jFmonto_pagar = new javax.swing.JFormattedTextField();
        txtmonto_adelanto = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        txtmonto_letra = new javax.swing.JTextField();
        btnnuevo = new javax.swing.JButton();
        btnguardar = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
        txtotros_nombre = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        txtotro_monto = new javax.swing.JTextField();
        panel_filtro_liquidacion = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblliquidacion = new javax.swing.JTable();
        btnimprimir_liquidacion = new javax.swing.JButton();
        btnanular = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblitem_liquidacion_final_id = new javax.swing.JTable();
        btnrecargar_liquidacion = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
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

        gru_impexp.add(jRimportacion);
        jRimportacion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jRimportacion.setForeground(new java.awt.Color(0, 0, 102));
        jRimportacion.setSelected(true);
        jRimportacion.setText("IMPORTACION");
        jRimportacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRimportacionActionPerformed(evt);
            }
        });

        gru_impexp.add(jRexportacion);
        jRexportacion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jRexportacion.setForeground(new java.awt.Color(102, 0, 0));
        jRexportacion.setText("EXPORTACION");
        jRexportacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRexportacionActionPerformed(evt);
            }
        });

        jLabel10.setText("EXPORTADOR:");

        jLabel11.setText("RUC:");

        txtruc_exportador.setEditable(false);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("MONTOS"));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("FACTURA NRO:");

        txtfactura_numero.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtfactura_numero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtfactura_numeroKeyPressed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("M. FACTURA:");

        txtmonto_factura.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtmonto_factura.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtmonto_factura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtmonto_facturaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtmonto_facturaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtmonto_facturaKeyTyped(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setText("M. FLETE:");

        txtmonto_flete.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtmonto_flete.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtmonto_flete.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtmonto_fleteKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtmonto_fleteKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtmonto_fleteKeyTyped(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel20.setText("M. SEGURO:");

        txtmonto_seguro.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtmonto_seguro.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtmonto_seguro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtmonto_seguroKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtmonto_seguroKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtmonto_seguroKeyTyped(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel21.setText("AJUSTE:");

        txtmonto_ajuste.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtmonto_ajuste.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtmonto_ajuste.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtmonto_ajusteKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtmonto_ajusteKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtmonto_ajusteKeyTyped(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel22.setText("C. I. F.:");

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel23.setText("V. Imponible:");

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel24.setText("GUARANI");

        jFmonto_imponible.setEditable(false);
        jFmonto_imponible.setBackground(new java.awt.Color(204, 204, 204));
        jFmonto_imponible.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jFmonto_imponible.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFmonto_imponible.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jFmonto_cif.setEditable(false);
        jFmonto_cif.setBackground(new java.awt.Color(204, 204, 204));
        jFmonto_cif.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jFmonto_cif.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFmonto_cif.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel27.setText("TOTAL:");

        jFmonto_total.setEditable(false);
        jFmonto_total.setBackground(new java.awt.Color(204, 204, 204));
        jFmonto_total.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        jFmonto_total.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFmonto_total.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        lblmoneda1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblmoneda1.setText("moneda");

        lblmoneda2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblmoneda2.setText("moneda");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtmonto_seguro, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                            .addComponent(txtmonto_factura))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addComponent(jLabel21))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtmonto_ajuste, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                            .addComponent(txtmonto_flete)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtfactura_numero, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23)
                            .addComponent(jLabel27)
                            .addComponent(jLabel22))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jFmonto_cif, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jFmonto_total, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jFmonto_imponible, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblmoneda1)
                            .addComponent(lblmoneda2)
                            .addComponent(jLabel24))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtfactura_numero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtmonto_factura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(txtmonto_flete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtmonto_seguro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(txtmonto_ajuste, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jFmonto_cif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblmoneda1))
                .addGap(4, 4, 4)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(jFmonto_total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblmoneda2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel23)
                    .addComponent(jLabel24)
                    .addComponent(jFmonto_imponible, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("CONTENEDOR"));

        jLabel3.setText("NUMERO:");

        txtlp_contenedor_nro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtlp_contenedor_nroKeyPressed(evt);
            }
        });

        lblmercaderia.setText("MERCADERIA:");
        lblmercaderia.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblmercaderiaMouseMoved(evt);
            }
        });
        lblmercaderia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblmercaderiaMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblmercaderiaMouseExited(evt);
            }
        });

        lbldestino.setText("DESTINO:");
        lbldestino.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lbldestinoMouseMoved(evt);
            }
        });
        lbldestino.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbldestinoMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbldestinoMouseExited(evt);
            }
        });

        jLabel5.setText("VIA DE T.:");

        cmbvia_transporte.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-SELECCIONAR-", "AEREO", "FLUVIAL", "MARITIMO", "TERRESTRE", "MULTI-MODAL" }));

        lbladuana.setText("ADUANA:");
        lbladuana.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lbladuanaMouseMoved(evt);
            }
        });
        lbladuana.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbladuanaMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbladuanaMouseExited(evt);
            }
        });

        lbltransportadora_empresa.setText("TRANSPOR.:");
        lbltransportadora_empresa.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lbltransportadora_empresaMouseMoved(evt);
            }
        });
        lbltransportadora_empresa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbltransportadora_empresaMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbltransportadora_empresaMouseExited(evt);
            }
        });

        jLabel4.setText("MODO TRANSPORT.:");

        cmbcontenedor_tipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-SELECCIONAR-", "SIN-TIPO", "L.C.L.", "F.C.L." }));

        lblregimen.setText("REGIMEN:");
        lblregimen.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblregimenMouseMoved(evt);
            }
        });
        lblregimen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblregimenMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblregimenMouseExited(evt);
            }
        });

        lblincoterms.setText("INCOTERM:");
        lblincoterms.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblincotermsMouseMoved(evt);
            }
        });
        lblincoterms.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblincotermsMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblincotermsMouseExited(evt);
            }
        });

        btnbuscar_mercaderia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/ABM/mini_lupa.png"))); // NOI18N
        btnbuscar_mercaderia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscar_mercaderiaActionPerformed(evt);
            }
        });

        txtbuscar_mercaderia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtbuscar_mercaderiaMouseEntered(evt);
            }
        });
        txtbuscar_mercaderia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtbuscar_mercaderiaKeyPressed(evt);
            }
        });

        txtbuscar_destino.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtbuscar_destinoMouseEntered(evt);
            }
        });
        txtbuscar_destino.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtbuscar_destinoKeyPressed(evt);
            }
        });

        txtbuscar_aduana.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtbuscar_aduanaMouseEntered(evt);
            }
        });
        txtbuscar_aduana.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtbuscar_aduanaKeyPressed(evt);
            }
        });

        txtbuscar_transportadora.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtbuscar_transportadoraKeyPressed(evt);
            }
        });

        txtbuscar_regimen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtbuscar_regimenKeyPressed(evt);
            }
        });

        txtbuscar_incoterm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtbuscar_incotermKeyPressed(evt);
            }
        });

        txtregimen_descripcion.setEditable(false);

        txtincoterm_descripcion.setEditable(false);

        btnbuscar_destino.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/ABM/mini_lupa.png"))); // NOI18N
        btnbuscar_destino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscar_destinoActionPerformed(evt);
            }
        });

        btnbuscar_aduana.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/ABM/mini_lupa.png"))); // NOI18N
        btnbuscar_aduana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscar_aduanaActionPerformed(evt);
            }
        });

        txtsigla_aduana.setEditable(false);

        btnbuscar_regimen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/ABM/mini_lupa.png"))); // NOI18N
        btnbuscar_regimen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscar_regimenActionPerformed(evt);
            }
        });

        btnbuscar_incoterm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/ABM/mini_lupa.png"))); // NOI18N
        btnbuscar_incoterm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscar_incotermActionPerformed(evt);
            }
        });

        btnbuscar_transportadora.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/ABM/mini_lupa.png"))); // NOI18N
        btnbuscar_transportadora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscar_transportadoraActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblincoterms)
                            .addComponent(lblregimen, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbltransportadora_empresa)
                            .addComponent(lbladuana)
                            .addComponent(lbldestino)
                            .addComponent(lblmercaderia)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(txtbuscar_aduana)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtsigla_aduana, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtbuscar_destino)
                                    .addComponent(txtbuscar_mercaderia, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnbuscar_destino, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(btnbuscar_mercaderia, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnbuscar_aduana, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtbuscar_regimen, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                                    .addComponent(txtbuscar_incoterm))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtregimen_descripcion, javax.swing.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)
                                    .addComponent(txtincoterm_descripcion))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnbuscar_regimen, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(btnbuscar_incoterm, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txtlp_contenedor_nro, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txtbuscar_transportadora, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnbuscar_transportadora, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(cmbvia_transporte, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbcontenedor_tipo, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtlp_contenedor_nro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnbuscar_mercaderia, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblmercaderia)
                                        .addComponent(txtbuscar_mercaderia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lbldestino)
                                    .addComponent(txtbuscar_destino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(btnbuscar_destino, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbladuana)
                            .addComponent(txtbuscar_aduana, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtsigla_aduana, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnbuscar_aduana, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbltransportadora_empresa)
                            .addComponent(txtbuscar_transportadora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblregimen)
                                        .addComponent(txtbuscar_regimen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtregimen_descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(btnbuscar_regimen, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblincoterms)
                                    .addComponent(txtbuscar_incoterm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtincoterm_descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(btnbuscar_incoterm, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel5)
                            .addComponent(cmbvia_transporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(cmbcontenedor_tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnbuscar_transportadora, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("TASA DE CAMBIO"));

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

        lblmoneda.setText("MONEDA:");
        lblmoneda.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblmonedaMouseMoved(evt);
            }
        });
        lblmoneda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblmonedaMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblmonedaMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(txtlp_tasa_cambio_aduana, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtlp_tasa_cambio_mercado)))
                .addGap(9, 9, 9))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(lblmoneda)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbmoneda_cambio, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbmoneda_cambio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblmoneda))
                .addGap(5, 5, 5)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtlp_tasa_cambio_aduana, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtlp_tasa_cambio_mercado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("ID:");

        txtidliquidacion_final.setBackground(new java.awt.Color(255, 255, 51));
        txtidliquidacion_final.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtidliquidacion_final.setForeground(new java.awt.Color(0, 0, 204));
        txtidliquidacion_final.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("FEC. DESPACHO:");

        txtfecha_despacho.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txtobservacion.setColumns(20);
        txtobservacion.setRows(5);
        txtobservacion.setBorder(javax.swing.BorderFactory.createTitledBorder("OBSERVACION"));
        jScrollPane1.setViewportView(txtobservacion);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("DESPACHO NUMERO:");

        txtdespacho_numero.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtdespacho_numero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtdespacho_numeroKeyPressed(evt);
            }
        });

        lbldespacho_zona.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbldespacho_zona.setText("ZONA:");
        lbldespacho_zona.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lbldespacho_zonaMouseMoved(evt);
            }
        });
        lbldespacho_zona.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbldespacho_zonaMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbldespacho_zonaMouseExited(evt);
            }
        });

        cmbdespachozona.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbdespachozona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbdespachozonaActionPerformed(evt);
            }
        });

        btnguardar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/ABM/guardar.png"))); // NOI18N
        btnguardar1.setText("GUARDAR");
        btnguardar1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnguardar1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnguardar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardar1ActionPerformed(evt);
            }
        });

        btnnuevo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/ABM/nuevo.png"))); // NOI18N
        btnnuevo1.setText("NUEVO");
        btnnuevo1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnnuevo1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnnuevo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnuevo1ActionPerformed(evt);
            }
        });

        txtbuscar_exportador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtbuscar_exportadorKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbuscar_exportadorKeyReleased(evt);
            }
        });

        jLabel25.setText("aaaa-MM-dd");

        btnbuscar_exportador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/ABM/mini_lupa.png"))); // NOI18N
        btnbuscar_exportador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscar_exportadorActionPerformed(evt);
            }
        });

        panel_cliente.setBorder(javax.swing.BorderFactory.createTitledBorder("CLIENTE / IMPORTADOR"));

        jLabel8.setText("IMPORTADOR:");

        txtbuscar_importador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtbuscar_importadorKeyPressed(evt);
            }
        });

        jLabel9.setText("RUC:");

        txtruc_importador.setEditable(false);

        btnbuscar_importador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/ABM/mini_lupa.png"))); // NOI18N
        btnbuscar_importador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscar_importadorActionPerformed(evt);
            }
        });

        jLabel26.setText("RUBRO:");

        jLabel28.setText("SALDO:");

        javax.swing.GroupLayout panel_clienteLayout = new javax.swing.GroupLayout(panel_cliente);
        panel_cliente.setLayout(panel_clienteLayout);
        panel_clienteLayout.setHorizontalGroup(
            panel_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_clienteLayout.createSequentialGroup()
                .addGroup(panel_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel26))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtbuscar_importador)
                    .addComponent(txtimportador_rubro))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panel_clienteLayout.createSequentialGroup()
                        .addComponent(txtruc_importador, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnbuscar_importador, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jFimportador_saldo)))
        );
        panel_clienteLayout.setVerticalGroup(
            panel_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_clienteLayout.createSequentialGroup()
                .addGroup(panel_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(txtbuscar_importador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtruc_importador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9))
                    .addComponent(btnbuscar_importador, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(txtimportador_rubro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFimportador_saldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel35.setText("DESPACHANTE:");

        txtbuscar_despachante.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtbuscar_despachanteKeyPressed(evt);
            }
        });

        jLabel36.setText("RUC:");

        txtruc_despachante.setEditable(false);

        btnbuscar_despachante.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/ABM/mini_lupa.png"))); // NOI18N
        btnbuscar_despachante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscar_despachanteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_encabezadoLayout = new javax.swing.GroupLayout(panel_encabezado);
        panel_encabezado.setLayout(panel_encabezadoLayout);
        panel_encabezadoLayout.setHorizontalGroup(
            panel_encabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_encabezadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_encabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_encabezadoLayout.createSequentialGroup()
                        .addGroup(panel_encabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_encabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panel_encabezadoLayout.createSequentialGroup()
                                    .addComponent(jLabel10)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtbuscar_exportador, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel11)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtruc_exportador, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnbuscar_exportador, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(panel_cliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING))
                            .addGroup(panel_encabezadoLayout.createSequentialGroup()
                                .addComponent(jLabel35)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtbuscar_despachante, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel36)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtruc_despachante, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnbuscar_despachante, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(71, 71, 71)
                        .addGroup(panel_encabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panel_encabezadoLayout.createSequentialGroup()
                                .addGroup(panel_encabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panel_encabezadoLayout.createSequentialGroup()
                                        .addComponent(btnnuevo1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnguardar1))
                                    .addGroup(panel_encabezadoLayout.createSequentialGroup()
                                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(panel_encabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addComponent(txtfecha_despacho, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel25))))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(14, 14, 14))
                    .addGroup(panel_encabezadoLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtdespacho_numero, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbldespacho_zona)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbdespachozona, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtidliquidacion_final, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRimportacion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRexportacion)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        panel_encabezadoLayout.setVerticalGroup(
            panel_encabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_encabezadoLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(panel_encabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel12)
                    .addComponent(txtdespacho_numero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbldespacho_zona)
                    .addComponent(cmbdespachozona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel_encabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txtidliquidacion_final, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jRimportacion)
                        .addComponent(jRexportacion)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_encabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_encabezadoLayout.createSequentialGroup()
                        .addGroup(panel_encabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panel_encabezadoLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtfecha_despacho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel25)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_encabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnnuevo1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnguardar1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panel_encabezadoLayout.createSequentialGroup()
                        .addComponent(panel_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_encabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_encabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel10)
                                .addComponent(jLabel11)
                                .addComponent(txtruc_exportador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtbuscar_exportador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnbuscar_exportador, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_encabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_encabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel35)
                                .addComponent(jLabel36)
                                .addComponent(txtruc_despachante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtbuscar_despachante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnbuscar_despachante, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jTab_liquidacion.addTab("ENCABEZADO LIQUIDACION", panel_encabezado);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("TABLA ITEM"));

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
        jScrollPane2.setViewportView(tblitem_liquidacion_final);

        panel_cargar_item.setBorder(javax.swing.BorderFactory.createTitledBorder("CARGAR ITEM"));

        lblcomprobante.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
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

        txtbucar_comprobante.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtbucar_comprobante.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtbucar_comprobanteKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbucar_comprobanteKeyReleased(evt);
            }
        });

        jLabel30.setText("COMPROBANTE NRO:");

        txtdespacho_numero_item.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtdespacho_numero_item.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtdespacho_numero_itemKeyPressed(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 0, 0));
        jLabel31.setText("( F1 = cargar Nro despacho)");

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel32.setText("TOTAL EN GUARANI:");

        txtmonto_guarani_item.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtmonto_guarani_item.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtmonto_guarani_item.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtmonto_guarani_itemKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtmonto_guarani_itemKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtmonto_guarani_itemKeyTyped(evt);
            }
        });

        btnnro_despacho.setText("NRO");
        btnnro_despacho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnro_despachoActionPerformed(evt);
            }
        });

        lbltipo_iva.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbltipo_iva.setForeground(new java.awt.Color(0, 0, 204));
        lbltipo_iva.setText("jLabel13");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("SIN IVA:");

        jFsin_iva.setEditable(false);
        jFsin_iva.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0"))));
        jFsin_iva.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFsin_iva.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setText("SOLO IVA:");

        jFsolo_iva.setEditable(false);
        jFsolo_iva.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0"))));
        jFsolo_iva.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFsolo_iva.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        lblidcomprobante.setForeground(new java.awt.Color(0, 0, 204));
        lblidcomprobante.setText("jLabel17");

        btneditar_item_comprobante.setText("EDITAR ITEM COMPROBANTE");
        btneditar_item_comprobante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditar_item_comprobanteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_cargar_itemLayout = new javax.swing.GroupLayout(panel_cargar_item);
        panel_cargar_item.setLayout(panel_cargar_itemLayout);
        panel_cargar_itemLayout.setHorizontalGroup(
            panel_cargar_itemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_cargar_itemLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_cargar_itemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_cargar_itemLayout.createSequentialGroup()
                        .addGroup(panel_cargar_itemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbltipo_iva, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtmonto_guarani_item)
                            .addGroup(panel_cargar_itemLayout.createSequentialGroup()
                                .addComponent(txtdespacho_numero_item, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnnro_despacho))
                            .addGroup(panel_cargar_itemLayout.createSequentialGroup()
                                .addComponent(jLabel30)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel31))
                            .addComponent(txtbucar_comprobante)
                            .addGroup(panel_cargar_itemLayout.createSequentialGroup()
                                .addComponent(lblcomprobante)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblidcomprobante)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panel_cargar_itemLayout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel16)
                        .addGap(94, 94, 94))
                    .addGroup(panel_cargar_itemLayout.createSequentialGroup()
                        .addComponent(jFsin_iva, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jFsolo_iva, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(panel_cargar_itemLayout.createSequentialGroup()
                        .addComponent(btneditar_item_comprobante)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        panel_cargar_itemLayout.setVerticalGroup(
            panel_cargar_itemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_cargar_itemLayout.createSequentialGroup()
                .addGroup(panel_cargar_itemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblcomprobante)
                    .addComponent(lblidcomprobante))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtbucar_comprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_cargar_itemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_cargar_itemLayout.createSequentialGroup()
                        .addGroup(panel_cargar_itemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel30)
                            .addComponent(jLabel31))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtdespacho_numero_item, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnnro_despacho, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel32)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtmonto_guarani_item, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbltipo_iva, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_cargar_itemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_cargar_itemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFsin_iva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFsolo_iva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btneditar_item_comprobante)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        btneliminar_item_liquidacion.setText("ELIMINAR ITEM");
        btneliminar_item_liquidacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminar_item_liquidacionActionPerformed(evt);
            }
        });

        btncargar_pre_item.setText("CARGAR PRE ITEM");
        btncargar_pre_item.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncargar_pre_itemActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setText("SUMA SIN IVA:");

        jFsuma_solo_iva.setEditable(false);
        jFsuma_solo_iva.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0"))));
        jFsuma_solo_iva.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFsuma_solo_iva.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jFsuma_sin_iva.setEditable(false);
        jFsuma_sin_iva.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0"))));
        jFsuma_sin_iva.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFsuma_sin_iva.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setText("SUMA SOLO IVA:");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addComponent(panel_cargar_item, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(btneliminar_item_liquidacion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btncargar_pre_item)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFsuma_sin_iva, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFsuma_solo_iva, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel_cargar_item, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btneliminar_item_liquidacion)
                            .addComponent(btncargar_pre_item)
                            .addComponent(jLabel17)
                            .addComponent(jFsuma_solo_iva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFsuma_sin_iva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18))))
                .addContainerGap())
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("SUMA DESPACHO"));

        jFmonto_total_despacho.setEditable(false);
        jFmonto_total_despacho.setBorder(javax.swing.BorderFactory.createTitledBorder("TOTAL-DESPACHO"));
        jFmonto_total_despacho.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat(""))));
        jFmonto_total_despacho.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFmonto_total_despacho.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jFmonto_pagar.setEditable(false);
        jFmonto_pagar.setBorder(javax.swing.BorderFactory.createTitledBorder("TOTAL-PAGAR"));
        jFmonto_pagar.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat(""))));
        jFmonto_pagar.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFmonto_pagar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        txtmonto_adelanto.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtmonto_adelanto.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtmonto_adelanto.setBorder(javax.swing.BorderFactory.createTitledBorder("ADELANTO"));
        txtmonto_adelanto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtmonto_adelantoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtmonto_adelantoKeyTyped(evt);
            }
        });

        jLabel33.setText("MONTO LETRA:");

        txtmonto_letra.setEditable(false);
        txtmonto_letra.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 51, Short.MAX_VALUE)
                .addComponent(jFmonto_total_despacho, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtmonto_adelanto, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jFmonto_pagar, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtmonto_letra)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel33)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jFmonto_total_despacho, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtmonto_adelanto, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jFmonto_pagar, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtmonto_letra, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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

        jLabel29.setText("OTRO:");

        jLabel34.setText("MONTO:");

        txtotro_monto.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtotro_monto.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtotro_monto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtotro_montoKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout panel_item_liquidacionLayout = new javax.swing.GroupLayout(panel_item_liquidacion);
        panel_item_liquidacion.setLayout(panel_item_liquidacionLayout);
        panel_item_liquidacionLayout.setHorizontalGroup(
            panel_item_liquidacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_item_liquidacionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnnuevo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnguardar)
                .addGap(18, 18, 18)
                .addGroup(panel_item_liquidacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29)
                    .addComponent(jLabel34))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_item_liquidacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtotro_monto, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtotros_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panel_item_liquidacionLayout.setVerticalGroup(
            panel_item_liquidacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_item_liquidacionLayout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_item_liquidacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnnuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel_item_liquidacionLayout.createSequentialGroup()
                        .addGroup(panel_item_liquidacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel29)
                            .addComponent(txtotros_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_item_liquidacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel34)
                            .addComponent(txtotro_monto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 24, Short.MAX_VALUE))
        );

        jTab_liquidacion.addTab("ITEM LIQUIDACION", panel_item_liquidacion);

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("LIQUIDACION"));

        tblliquidacion.setModel(new javax.swing.table.DefaultTableModel(
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
        tblliquidacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblliquidacionMouseReleased(evt);
            }
        });
        jScrollPane3.setViewportView(tblliquidacion);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1127, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
        );

        btnimprimir_liquidacion.setText("IMPRIMIR LIQUIDACION");
        btnimprimir_liquidacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnimprimir_liquidacionActionPerformed(evt);
            }
        });

        btnanular.setText("ANULAR");
        btnanular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnanularActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("ITEM LIQUIDACION"));

        tblitem_liquidacion_final_id.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(tblitem_liquidacion_final_id);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 817, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
        );

        btnrecargar_liquidacion.setText("RE-CARGAR");
        btnrecargar_liquidacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrecargar_liquidacionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_filtro_liquidacionLayout = new javax.swing.GroupLayout(panel_filtro_liquidacion);
        panel_filtro_liquidacion.setLayout(panel_filtro_liquidacionLayout);
        panel_filtro_liquidacionLayout.setHorizontalGroup(
            panel_filtro_liquidacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panel_filtro_liquidacionLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panel_filtro_liquidacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnimprimir_liquidacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnanular, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnrecargar_liquidacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panel_filtro_liquidacionLayout.setVerticalGroup(
            panel_filtro_liquidacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_filtro_liquidacionLayout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_filtro_liquidacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_filtro_liquidacionLayout.createSequentialGroup()
                        .addComponent(btnimprimir_liquidacion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnanular)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnrecargar_liquidacion))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(78, 78, 78))
        );

        jTab_liquidacion.addTab("FILTRO LIQUIDACION", panel_filtro_liquidacion);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTab_liquidacion)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTab_liquidacion)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtlp_tasa_cambio_aduanaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtlp_tasa_cambio_aduanaKeyReleased
        // TODO add your handling code here:
//        evejtf.getString_format_nro_entero(txtlp_tasa_cambio_aduana);
        cargar_montos();
    }//GEN-LAST:event_txtlp_tasa_cambio_aduanaKeyReleased

    private void txtlp_tasa_cambio_aduanaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtlp_tasa_cambio_aduanaKeyTyped
        // TODO add your handling code here:
        evejtf.soloNumero(evt);
    }//GEN-LAST:event_txtlp_tasa_cambio_aduanaKeyTyped

    private void txtlp_tasa_cambio_mercadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtlp_tasa_cambio_mercadoKeyReleased
        // TODO add your handling code here:
        evejtf.getString_format_nro_entero(txtlp_tasa_cambio_mercado);
    }//GEN-LAST:event_txtlp_tasa_cambio_mercadoKeyReleased

    private void txtlp_tasa_cambio_mercadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtlp_tasa_cambio_mercadoKeyTyped
        // TODO add your handling code here:
        evejtf.soloNumero(evt);
    }//GEN-LAST:event_txtlp_tasa_cambio_mercadoKeyTyped

    private void cmbmoneda_cambioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbmoneda_cambioActionPerformed
        // TODO add your handling code here:
        if (hab_cargar_moneda_cambio) {
            fk_idmoneda_cambio = evecomb.getInt_seleccionar_COMBOBOX(conn, cmbmoneda_cambio, "idmoneda_cambio", "moneda", "moneda_cambio");
            cargar_dato_select_moneda_cambio(fk_idmoneda_cambio, false);
            cargar_montos();
        }
    }//GEN-LAST:event_cmbmoneda_cambioActionPerformed

    private void cmbdespachozonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbdespachozonaActionPerformed
        // TODO add your handling code here:
        if (hab_cargar_despachozona) {
            fk_iddespacho_zona = evecomb.getInt_seleccionar_COMBOBOX(conn, cmbdespachozona, "iddespacho_zona", "nombre", "despacho_zona");
            setFk_iddespacho_zona(fk_iddespacho_zona);
        }
    }//GEN-LAST:event_cmbdespachozonaActionPerformed

    private void txtmonto_facturaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmonto_facturaKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtmonto_flete.grabFocus();
            cargar_montos();
        }
    }//GEN-LAST:event_txtmonto_facturaKeyPressed

    private void txtmonto_fleteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmonto_fleteKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtmonto_seguro.grabFocus();
            cargar_montos();
        }
    }//GEN-LAST:event_txtmonto_fleteKeyPressed

    private void txtmonto_seguroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmonto_seguroKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtmonto_ajuste.grabFocus();
            cargar_montos();
        }
    }//GEN-LAST:event_txtmonto_seguroKeyPressed

    private void txtmonto_ajusteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmonto_ajusteKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtmonto_factura.grabFocus();
            cargar_montos();
        }
    }//GEN-LAST:event_txtmonto_ajusteKeyPressed

    private void txtmonto_facturaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmonto_facturaKeyReleased
        // TODO add your handling code here:
        cargar_montos();
    }//GEN-LAST:event_txtmonto_facturaKeyReleased

    private void txtmonto_fleteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmonto_fleteKeyReleased
        // TODO add your handling code here:
        cargar_montos();
    }//GEN-LAST:event_txtmonto_fleteKeyReleased

    private void txtmonto_seguroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmonto_seguroKeyReleased
        // TODO add your handling code here:
        cargar_montos();
    }//GEN-LAST:event_txtmonto_seguroKeyReleased

    private void txtmonto_ajusteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmonto_ajusteKeyReleased
        // TODO add your handling code here:
        cargar_montos();
    }//GEN-LAST:event_txtmonto_ajusteKeyReleased

    private void txtmonto_facturaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmonto_facturaKeyTyped
        // TODO add your handling code here:
        evejtf.soloNumero(evt);
    }//GEN-LAST:event_txtmonto_facturaKeyTyped

    private void txtmonto_fleteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmonto_fleteKeyTyped
        // TODO add your handling code here:
        evejtf.soloNumero(evt);
    }//GEN-LAST:event_txtmonto_fleteKeyTyped

    private void txtmonto_seguroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmonto_seguroKeyTyped
        // TODO add your handling code here:
        evejtf.soloNumero(evt);
    }//GEN-LAST:event_txtmonto_seguroKeyTyped

    private void txtmonto_ajusteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmonto_ajusteKeyTyped
        // TODO add your handling code here:
        evejtf.soloNumero(evt);
    }//GEN-LAST:event_txtmonto_ajusteKeyTyped

    private void jRimportacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRimportacionActionPerformed
        // TODO add your handling code here:
        cargar_colores_impor_export();
    }//GEN-LAST:event_jRimportacionActionPerformed

    private void jRexportacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRexportacionActionPerformed
        // TODO add your handling code here:
        cargar_colores_impor_export();
    }//GEN-LAST:event_jRexportacionActionPerformed

    private void txtbucar_comprobanteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbucar_comprobanteKeyPressed
        // TODO add your handling code here:
//        if (txtbucar_comprobante.getText().trim().length() > 1) {
//            evejtf.seleccionar_lista(evt, jList_comprobante);
//        }
    }//GEN-LAST:event_txtbucar_comprobanteKeyPressed

    private void txtbucar_comprobanteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbucar_comprobanteKeyReleased
        // TODO add your handling code here:
//        if (txtbucar_comprobante.getText().trim().length() > 1) {
//            eveconn.buscar_cargar_condicion_Jlista(conn, txtbucar_comprobante, jList_comprobante, "tipo_comprobante", "descripcion", "descripcion", "");
//        }
    }//GEN-LAST:event_txtbucar_comprobanteKeyReleased

    private void txtdespacho_numero_itemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdespacho_numero_itemKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_F1) {
            if (txtdespacho_numero.getText().trim().length() == 0) {
                eveJtab.mostrar_JTabbedPane(jTab_liquidacion, 0);
                txtdespacho_numero.grabFocus();
            } else {
                txtdespacho_numero_item.setText(txtdespacho_numero.getText());
                txtmonto_guarani_item.grabFocus();
            }
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtmonto_guarani_item.grabFocus();
        }
    }//GEN-LAST:event_txtdespacho_numero_itemKeyPressed

    private void txtmonto_guarani_itemKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmonto_guarani_itemKeyTyped
        // TODO add your handling code here:
        evejtf.soloNumero(evt);
    }//GEN-LAST:event_txtmonto_guarani_itemKeyTyped

    private void txtmonto_guarani_itemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmonto_guarani_itemKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
//            boton_cargar_item_liquidacion_final();
            update_item_pre_item_liquidacion();
        }
    }//GEN-LAST:event_txtmonto_guarani_itemKeyPressed

    private void txtmonto_adelantoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmonto_adelantoKeyReleased
        // TODO add your handling code here:
        sumar_item_liquidacion_final();
    }//GEN-LAST:event_txtmonto_adelantoKeyReleased

    private void btneliminar_item_liquidacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminar_item_liquidacionActionPerformed
        // TODO add your handling code here:
        boton_eliminar_item_liquidacion_final();
    }//GEN-LAST:event_btneliminar_item_liquidacionActionPerformed

    private void txtmonto_adelantoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmonto_adelantoKeyTyped
        // TODO add your handling code here:
        evejtf.soloNumero(evt);
    }//GEN-LAST:event_txtmonto_adelantoKeyTyped

    private void btnnuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnuevoActionPerformed
        // TODO add your handling code here:
        reestableser_liquidacion();
    }//GEN-LAST:event_btnnuevoActionPerformed

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
        // TODO add your handling code here:
        boton_guardar_liquidacion_final();
    }//GEN-LAST:event_btnguardarActionPerformed

    private void btnguardar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardar1ActionPerformed
        // TODO add your handling code here:
        boton_guardar_liquidacion_final();
    }//GEN-LAST:event_btnguardar1ActionPerformed

    private void btnnuevo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnuevo1ActionPerformed
        // TODO add your handling code here:
        reestableser_liquidacion();
    }//GEN-LAST:event_btnnuevo1ActionPerformed

    private void lbldespacho_zonaMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbldespacho_zonaMouseMoved
        // TODO add your handling code here:
        evelbl.evento_MouseMoved(lbldespacho_zona);
    }//GEN-LAST:event_lbldespacho_zonaMouseMoved

    private void lbldespacho_zonaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbldespacho_zonaMouseExited
        // TODO add your handling code here:
        evelbl.evento_MouseExited(lbldespacho_zona);
    }//GEN-LAST:event_lbldespacho_zonaMouseExited

    private void lbldespacho_zonaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbldespacho_zonaMouseClicked
        // TODO add your handling code here:
        evetbl.abrir_TablaJinternal(new FrmDespacho_zona());
    }//GEN-LAST:event_lbldespacho_zonaMouseClicked

    private void lblmercaderiaMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblmercaderiaMouseMoved
        // TODO add your handling code here:
        evelbl.evento_MouseMoved(lblmercaderia);
    }//GEN-LAST:event_lblmercaderiaMouseMoved

    private void lblmercaderiaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblmercaderiaMouseExited
        // TODO add your handling code here:
        evelbl.evento_MouseExited(lblmercaderia);
    }//GEN-LAST:event_lblmercaderiaMouseExited

    private void lblmercaderiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblmercaderiaMouseClicked
        // TODO add your handling code here:
        ENTtc.desactivar_todos();
        ENTtc.setConfig_inicio(true);
        ENTtc.setIni_mercaderia(true);
        evetbl.abrir_TablaJinternal(new FrmTipo_comprobante());
    }//GEN-LAST:event_lblmercaderiaMouseClicked

    private void lbldestinoMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbldestinoMouseMoved
        // TODO add your handling code here:
        evelbl.evento_MouseMoved(lbldestino);
    }//GEN-LAST:event_lbldestinoMouseMoved

    private void lbldestinoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbldestinoMouseExited
        // TODO add your handling code here:
        evelbl.evento_MouseExited(lbldestino);
    }//GEN-LAST:event_lbldestinoMouseExited

    private void lbldestinoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbldestinoMouseClicked
        // TODO add your handling code here:
        evetbl.abrir_TablaJinternal(new FrmTercero_ciudad());
    }//GEN-LAST:event_lbldestinoMouseClicked

    private void lbladuanaMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbladuanaMouseMoved
        // TODO add your handling code here:
        evelbl.evento_MouseMoved(lbladuana);
    }//GEN-LAST:event_lbladuanaMouseMoved

    private void lbladuanaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbladuanaMouseExited
        // TODO add your handling code here:
        evelbl.evento_MouseExited(lbladuana);
    }//GEN-LAST:event_lbladuanaMouseExited

    private void lbladuanaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbladuanaMouseClicked
        // TODO add your handling code here:
        evetbl.abrir_TablaJinternal(new FrmAduana());
    }//GEN-LAST:event_lbladuanaMouseClicked

    private void lbltransportadora_empresaMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbltransportadora_empresaMouseMoved
        // TODO add your handling code here:
        evelbl.evento_MouseMoved(lbltransportadora_empresa);
    }//GEN-LAST:event_lbltransportadora_empresaMouseMoved

    private void lbltransportadora_empresaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbltransportadora_empresaMouseExited
        // TODO add your handling code here:
        evelbl.evento_MouseExited(lbltransportadora_empresa);
    }//GEN-LAST:event_lbltransportadora_empresaMouseExited

    private void lbltransportadora_empresaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbltransportadora_empresaMouseClicked
        // TODO add your handling code here:
        evetbl.abrir_TablaJinternal(new FrmTransporte_empresa());
    }//GEN-LAST:event_lbltransportadora_empresaMouseClicked

    private void lblregimenMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblregimenMouseMoved
        // TODO add your handling code here:
        evelbl.evento_MouseMoved(lblregimen);
    }//GEN-LAST:event_lblregimenMouseMoved

    private void lblregimenMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblregimenMouseExited
        // TODO add your handling code here:
        evelbl.evento_MouseExited(lblregimen);
    }//GEN-LAST:event_lblregimenMouseExited

    private void lblregimenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblregimenMouseClicked
        // TODO add your handling code here:
        evetbl.abrir_TablaJinternal(new FrmRegimen());
    }//GEN-LAST:event_lblregimenMouseClicked

    private void lblincotermsMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblincotermsMouseMoved
        // TODO add your handling code here:
        evelbl.evento_MouseMoved(lblincoterms);
    }//GEN-LAST:event_lblincotermsMouseMoved

    private void lblincotermsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblincotermsMouseExited
        // TODO add your handling code here:
        evelbl.evento_MouseExited(lblincoterms);
    }//GEN-LAST:event_lblincotermsMouseExited

    private void lblincotermsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblincotermsMouseClicked
        // TODO add your handling code here:
        evetbl.abrir_TablaJinternal(new FrmIncoterms());
    }//GEN-LAST:event_lblincotermsMouseClicked

    private void lblmonedaMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblmonedaMouseMoved
        // TODO add your handling code here:
        evelbl.evento_MouseMoved(lblmoneda);
    }//GEN-LAST:event_lblmonedaMouseMoved

    private void lblmonedaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblmonedaMouseExited
        // TODO add your handling code here:
        evelbl.evento_MouseExited(lblmoneda);
    }//GEN-LAST:event_lblmonedaMouseExited

    private void lblmonedaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblmonedaMouseClicked
        // TODO add your handling code here:
        evetbl.abrir_TablaJinternal(new FrmMoneda_cambio());
    }//GEN-LAST:event_lblmonedaMouseClicked

    private void lblcomprobanteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblcomprobanteMouseClicked
        // TODO add your handling code here:
        ENTtc.desactivar_todos();
        ENTtc.setConfig_inicio(true);
        ENTtc.setIni_con_comprobante(true);
        ENTtc.setIni_sin_comprobante(true);
        ENTtc.setIni_boleta_despachante(true);
        evetbl.abrir_TablaJinternal(new FrmTipo_comprobante());
    }//GEN-LAST:event_lblcomprobanteMouseClicked

    private void lblcomprobanteMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblcomprobanteMouseMoved
        // TODO add your handling code here:
        evelbl.evento_MouseMoved(lblcomprobante);
    }//GEN-LAST:event_lblcomprobanteMouseMoved

    private void lblcomprobanteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblcomprobanteMouseExited
        // TODO add your handling code here:
        evelbl.evento_MouseExited(lblcomprobante);
    }//GEN-LAST:event_lblcomprobanteMouseExited

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        // TODO add your handling code here:
        DAOliqfin.ancho_tabla_liquidacion_final(tblliquidacion);
    }//GEN-LAST:event_formInternalFrameOpened

    private void tblliquidacionMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblliquidacionMouseReleased
        // TODO add your handling code here:
        seleccionar_liquidacion();
    }//GEN-LAST:event_tblliquidacionMouseReleased

    private void btnimprimir_liquidacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnimprimir_liquidacionActionPerformed
        // TODO add your handling code here:
        boton_imprimir_liquidacion_final();
    }//GEN-LAST:event_btnimprimir_liquidacionActionPerformed

    private void btnanularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnanularActionPerformed
        // TODO add your handling code here:
        boton_anular_liquidacion_final();
    }//GEN-LAST:event_btnanularActionPerformed

    private void btnnro_despachoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnro_despachoActionPerformed
        // TODO add your handling code here:
        if (txtdespacho_numero.getText().trim().length() == 0) {
            eveJtab.mostrar_JTabbedPane(jTab_liquidacion, 0);
            txtdespacho_numero.grabFocus();
        } else {
            txtdespacho_numero_item.setText(txtdespacho_numero.getText());
            txtmonto_guarani_item.grabFocus();
        }
    }//GEN-LAST:event_btnnro_despachoActionPerformed

    private void btncargar_pre_itemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncargar_pre_itemActionPerformed
        // TODO add your handling code here:
        if (monto_total_despacho > 0) {
            if (evemen.MensajeGeneral_warning("ESTAS SEGURO DE LIMPIAR POR QUE YA TIENE MONTO\nTOTAL MONTO:" + monto_total_despacho, "ITEM CARGADO", "RE-CARGAR", "CANCELAR")) {
                cargar_pre_item_liquidacion();
            }
        } else {
            cargar_pre_item_liquidacion();
        }

    }//GEN-LAST:event_btncargar_pre_itemActionPerformed

    private void tblitem_liquidacion_finalMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblitem_liquidacion_finalMouseReleased
        // TODO add your handling code here:
        seleccionar_pre_item_liquidacion();
    }//GEN-LAST:event_tblitem_liquidacion_finalMouseReleased

    private void txtmonto_guarani_itemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmonto_guarani_itemKeyReleased
        // TODO add your handling code here:
        calcular_iva_desdemonto();
    }//GEN-LAST:event_txtmonto_guarani_itemKeyReleased

    private void btnbuscar_mercaderiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscar_mercaderiaActionPerformed
        // TODO add your handling code here:
        abrir_buscar(1, "MERCADERIA", txtbuscar_mercaderia);
    }//GEN-LAST:event_btnbuscar_mercaderiaActionPerformed

    private void txtbuscar_mercaderiaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtbuscar_mercaderiaMouseEntered
        // TODO add your handling code here:

    }//GEN-LAST:event_txtbuscar_mercaderiaMouseEntered

    private void txtbuscar_mercaderiaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscar_mercaderiaKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            abrir_buscar(1, "MERCADERIA", txtbuscar_mercaderia);
        }
    }//GEN-LAST:event_txtbuscar_mercaderiaKeyPressed

    private void txtbuscar_destinoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtbuscar_destinoMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbuscar_destinoMouseEntered

    private void txtbuscar_destinoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscar_destinoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            abrir_buscar(2, "DESTINO", txtbuscar_destino);
        }
    }//GEN-LAST:event_txtbuscar_destinoKeyPressed

    private void txtbuscar_importadorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscar_importadorKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            abrir_buscar(3, "IMPORTADOR", txtbuscar_importador);
        }
    }//GEN-LAST:event_txtbuscar_importadorKeyPressed

    private void txtbuscar_exportadorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscar_exportadorKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            abrir_buscar(4, "EXPORTADOR", txtbuscar_exportador);
        }
    }//GEN-LAST:event_txtbuscar_exportadorKeyPressed

    private void txtbuscar_aduanaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtbuscar_aduanaMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbuscar_aduanaMouseEntered

    private void txtbuscar_aduanaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscar_aduanaKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            abrir_buscar(5, "ADUANA", txtbuscar_aduana);
        }
    }//GEN-LAST:event_txtbuscar_aduanaKeyPressed

    private void txtbuscar_transportadoraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscar_transportadoraKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            abrir_buscar(6, "TRANSPORTADORA", txtbuscar_transportadora);
        }
    }//GEN-LAST:event_txtbuscar_transportadoraKeyPressed

    private void txtbuscar_regimenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscar_regimenKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            abrir_buscar(7, "REGIMEN", txtbuscar_regimen);
        }
    }//GEN-LAST:event_txtbuscar_regimenKeyPressed

    private void txtbuscar_incotermKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscar_incotermKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            abrir_buscar(8, "INCOTERMS", txtbuscar_incoterm);
        }
    }//GEN-LAST:event_txtbuscar_incotermKeyPressed

    private void txtlp_contenedor_nroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtlp_contenedor_nroKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtbuscar_mercaderia.grabFocus();
        }
    }//GEN-LAST:event_txtlp_contenedor_nroKeyPressed

    private void txtfactura_numeroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfactura_numeroKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtmonto_factura.grabFocus();
        }
    }//GEN-LAST:event_txtfactura_numeroKeyPressed

    private void btnbuscar_destinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscar_destinoActionPerformed
        // TODO add your handling code here:
        abrir_buscar(2, "DESTINO", txtbuscar_destino);
    }//GEN-LAST:event_btnbuscar_destinoActionPerformed

    private void btnbuscar_aduanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscar_aduanaActionPerformed
        // TODO add your handling code here:
        abrir_buscar(5, "ADUANA", txtbuscar_aduana);
    }//GEN-LAST:event_btnbuscar_aduanaActionPerformed

    private void btnbuscar_regimenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscar_regimenActionPerformed
        // TODO add your handling code here:
        abrir_buscar(7, "REGIMEN", txtbuscar_regimen);
    }//GEN-LAST:event_btnbuscar_regimenActionPerformed

    private void btnbuscar_incotermActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscar_incotermActionPerformed
        // TODO add your handling code here:
        abrir_buscar(8, "INCOTERMS", txtbuscar_incoterm);
    }//GEN-LAST:event_btnbuscar_incotermActionPerformed

    private void btnbuscar_transportadoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscar_transportadoraActionPerformed
        // TODO add your handling code here:
        abrir_buscar(6, "TRANSPORTADORA", txtbuscar_transportadora);
    }//GEN-LAST:event_btnbuscar_transportadoraActionPerformed

    private void btnbuscar_exportadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscar_exportadorActionPerformed
        // TODO add your handling code here:
        abrir_buscar(4, "EXPORTADOR", txtbuscar_exportador);
    }//GEN-LAST:event_btnbuscar_exportadorActionPerformed

    private void btnbuscar_importadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscar_importadorActionPerformed
        // TODO add your handling code here:
        abrir_buscar(3, "IMPORTADOR", txtbuscar_importador);
    }//GEN-LAST:event_btnbuscar_importadorActionPerformed

    private void txtotro_montoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtotro_montoKeyTyped
        // TODO add your handling code here:
        evejtf.soloNumero(evt);
    }//GEN-LAST:event_txtotro_montoKeyTyped

    private void btnrecargar_liquidacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrecargar_liquidacionActionPerformed
        // TODO add your handling code here:
        boton_recargar_liquidacion_final();
    }//GEN-LAST:event_btnrecargar_liquidacionActionPerformed

    private void btneditar_item_comprobanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditar_item_comprobanteActionPerformed
        // TODO add your handling code here:
        evetbl.abrir_TablaJinternal(new FrmPre_item_liquidacion_final());
    }//GEN-LAST:event_btneditar_item_comprobanteActionPerformed

    private void txtdespacho_numeroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdespacho_numeroKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            buscar_nro_despacho_separar();
            txtbuscar_importador.grabFocus();
        }
    }//GEN-LAST:event_txtdespacho_numeroKeyPressed

    private void txtbuscar_despachanteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscar_despachanteKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            abrir_buscar(11, "DESPACHANTE", txtbuscar_despachante);
        }
    }//GEN-LAST:event_txtbuscar_despachanteKeyPressed

    private void btnbuscar_despachanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscar_despachanteActionPerformed
        // TODO add your handling code here:
        abrir_buscar(11, "DESPACHANTE", txtbuscar_despachante);
    }//GEN-LAST:event_btnbuscar_despachanteActionPerformed

    private void txtbuscar_exportadorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscar_exportadorKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbuscar_exportadorKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnanular;
    private javax.swing.JButton btnbuscar_aduana;
    private javax.swing.JButton btnbuscar_despachante;
    private javax.swing.JButton btnbuscar_destino;
    private javax.swing.JButton btnbuscar_exportador;
    private javax.swing.JButton btnbuscar_importador;
    private javax.swing.JButton btnbuscar_incoterm;
    private javax.swing.JButton btnbuscar_mercaderia;
    private javax.swing.JButton btnbuscar_regimen;
    private javax.swing.JButton btnbuscar_transportadora;
    private javax.swing.JButton btncargar_pre_item;
    private javax.swing.JButton btneditar_item_comprobante;
    private javax.swing.JButton btneliminar_item_liquidacion;
    private javax.swing.JButton btnguardar;
    private javax.swing.JButton btnguardar1;
    private javax.swing.JButton btnimprimir_liquidacion;
    private javax.swing.JButton btnnro_despacho;
    private javax.swing.JButton btnnuevo;
    private javax.swing.JButton btnnuevo1;
    private javax.swing.JButton btnrecargar_liquidacion;
    private javax.swing.JComboBox<String> cmbcontenedor_tipo;
    private javax.swing.JComboBox<String> cmbdespachozona;
    private javax.swing.JComboBox<String> cmbmoneda_cambio;
    private javax.swing.JComboBox<String> cmbvia_transporte;
    private javax.swing.ButtonGroup gru_impexp;
    public static javax.swing.JFormattedTextField jFimportador_saldo;
    private javax.swing.JFormattedTextField jFmonto_cif;
    private javax.swing.JFormattedTextField jFmonto_imponible;
    private javax.swing.JFormattedTextField jFmonto_pagar;
    private javax.swing.JFormattedTextField jFmonto_total;
    private javax.swing.JFormattedTextField jFmonto_total_despacho;
    private javax.swing.JFormattedTextField jFsin_iva;
    private javax.swing.JFormattedTextField jFsolo_iva;
    private javax.swing.JFormattedTextField jFsuma_sin_iva;
    private javax.swing.JFormattedTextField jFsuma_solo_iva;
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
    private javax.swing.JPanel jPanel7;
    private javax.swing.JRadioButton jRexportacion;
    private javax.swing.JRadioButton jRimportacion;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTab_liquidacion;
    private javax.swing.JLabel lbladuana;
    private javax.swing.JLabel lblcomprobante;
    private javax.swing.JLabel lbldespacho_zona;
    private javax.swing.JLabel lbldestino;
    private javax.swing.JLabel lblidcomprobante;
    private javax.swing.JLabel lblincoterms;
    private javax.swing.JLabel lblmercaderia;
    private javax.swing.JLabel lblmoneda;
    private javax.swing.JLabel lblmoneda1;
    private javax.swing.JLabel lblmoneda2;
    private javax.swing.JLabel lblregimen;
    private javax.swing.JLabel lbltipo_iva;
    private javax.swing.JLabel lbltransportadora_empresa;
    private javax.swing.JPanel panel_cargar_item;
    private javax.swing.JPanel panel_cliente;
    private javax.swing.JPanel panel_encabezado;
    private javax.swing.JPanel panel_filtro_liquidacion;
    private javax.swing.JPanel panel_item_liquidacion;
    private javax.swing.JTable tblitem_liquidacion_final;
    private javax.swing.JTable tblitem_liquidacion_final_id;
    private javax.swing.JTable tblliquidacion;
    private javax.swing.JTextField txtbucar_comprobante;
    public static javax.swing.JTextField txtbuscar_aduana;
    public static javax.swing.JTextField txtbuscar_despachante;
    public static javax.swing.JTextField txtbuscar_destino;
    public static javax.swing.JTextField txtbuscar_exportador;
    public static javax.swing.JTextField txtbuscar_importador;
    public static javax.swing.JTextField txtbuscar_incoterm;
    public static javax.swing.JTextField txtbuscar_mercaderia;
    public static javax.swing.JTextField txtbuscar_regimen;
    public static javax.swing.JTextField txtbuscar_transportadora;
    private javax.swing.JTextField txtdespacho_numero;
    private javax.swing.JTextField txtdespacho_numero_item;
    private javax.swing.JTextField txtfactura_numero;
    private javax.swing.JTextField txtfecha_despacho;
    private javax.swing.JTextField txtidliquidacion_final;
    public static javax.swing.JTextField txtimportador_rubro;
    public static javax.swing.JTextField txtincoterm_descripcion;
    public static javax.swing.JTextField txtlp_contenedor_nro;
    private javax.swing.JTextField txtlp_tasa_cambio_aduana;
    private javax.swing.JTextField txtlp_tasa_cambio_mercado;
    private javax.swing.JTextField txtmonto_adelanto;
    private javax.swing.JTextField txtmonto_ajuste;
    private javax.swing.JTextField txtmonto_factura;
    private javax.swing.JTextField txtmonto_flete;
    private javax.swing.JTextField txtmonto_guarani_item;
    private javax.swing.JTextField txtmonto_letra;
    private javax.swing.JTextField txtmonto_seguro;
    private javax.swing.JTextArea txtobservacion;
    private javax.swing.JTextField txtotro_monto;
    private javax.swing.JTextField txtotros_nombre;
    public static javax.swing.JTextField txtregimen_descripcion;
    public static javax.swing.JTextField txtruc_despachante;
    public static javax.swing.JTextField txtruc_exportador;
    public static javax.swing.JTextField txtruc_importador;
    public static javax.swing.JTextField txtsigla_aduana;
    // End of variables declaration//GEN-END:variables
}
