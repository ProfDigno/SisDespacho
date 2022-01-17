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
import Evento.JLabel.EvenJLabel;
import Evento.JTextField.EvenJTextField;
import Evento.JasperReport.EvenJasperReport;
import Evento.Jframe.EvenJFRAME;
import Evento.Jtable.EvenJtable;
import Evento.Mensaje.EvenMensajeJoptionpane;
import Evento.Utilitario.EvenNumero_a_Letra;
import FORMULARIO.BO.BO_item_liquidacion_final;
import FORMULARIO.BO.BO_liquidacion_final;
import FORMULARIO.BO.BO_liquidacion_proforma;
import FORMULARIO.DAO.DAO_aduana;
import FORMULARIO.DAO.DAO_honorario_despacho;
import FORMULARIO.DAO.DAO_item_liquidacion_final;
import FORMULARIO.DAO.DAO_liquidacion_final;
import FORMULARIO.DAO.DAO_liquidacion_proforma;
import FORMULARIO.DAO.DAO_moneda_cambio;
import FORMULARIO.DAO.DAO_tercero;
import FORMULARIO.DAO.DAO_tipo_comprobante;
import FORMULARIO.DAO.dao_usuario;
import FORMULARIO.ENTIDAD.aduana;
import FORMULARIO.ENTIDAD.entidad_usuario;
import FORMULARIO.ENTIDAD.honorario_despacho;
import FORMULARIO.ENTIDAD.item_liquidacion_final;
import FORMULARIO.ENTIDAD.liquidacion_final;
import FORMULARIO.ENTIDAD.liquidacion_proforma;
import FORMULARIO.ENTIDAD.moneda_cambio;
import FORMULARIO.ENTIDAD.tercero;
import FORMULARIO.ENTIDAD.tipo_comprobante;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import javax.swing.JOptionPane;
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
//    private entidad_usuario ENTusu = new entidad_usuario();
//    private tercero ENTter = new tercero();
//    private DAO_tercero DAOter = new DAO_tercero();
    private aduana ENTadu = new aduana();
    private DAO_aduana DAOadu = new DAO_aduana();
    private liquidacion_proforma ENTlp = new liquidacion_proforma();
    private BO_liquidacion_proforma BOlp = new BO_liquidacion_proforma();
    private DAO_liquidacion_proforma DAOlp = new DAO_liquidacion_proforma();
    private moneda_cambio ENTmc = new moneda_cambio();
    private DAO_moneda_cambio DAOmc = new DAO_moneda_cambio();
    private item_liquidacion_final ENTifin = new item_liquidacion_final();
    private DAO_item_liquidacion_final DAOifin = new DAO_item_liquidacion_final();
//    private BO_item_liquidacion_final BOifin = new BO_item_liquidacion_final();
    private tipo_comprobante ENTcb = new tipo_comprobante();
    private DAO_tipo_comprobante DAOcb = new DAO_tipo_comprobante();
    private String creado_por = ENTusu.getGlobal_idusuario() + "-" + ENTusu.getGlobal_nombre();
    private DefaultTableModel model_item_liquidacion = new DefaultTableModel();
    private liquidacion_final liqfin = new liquidacion_final();
    private DAO_liquidacion_final DAOliqfin = new DAO_liquidacion_final();
    private BO_liquidacion_final BOliqfin = new BO_liquidacion_final();
    private tipo_comprobante ENTtc = new tipo_comprobante();
    private EvenNumero_a_Letra nl = new EvenNumero_a_Letra();
    private boolean hab_cargar_tercero_importador;
    private int fk_idtercero_importador;
    private boolean hab_cargar_tercero_exportador;
    private int fk_idtercero_exportador;
    private boolean hab_cargar_moneda_cambio;
    private int fk_idmoneda_cambio;
    private boolean hab_cargar_aduana;
    private int fk_idaduana;
    private boolean hab_cargar_transporte_empresa;
    private int fk_idtransporte_empresa;
    private boolean hab_cargar_despachozona;
    private int fk_iddespacho_zona;
    private boolean hab_cargar_tc_mercaderia;
    private int fk_idtc_mercaderia;
    private boolean hab_combo_ciudad;
    private int fk_idtercero_ciudad;
    private boolean hab_combo_regimen;
    private boolean hab_combo_incoterms;
    private int fk_idregimen;
    private int fk_idincoterms;
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
    private void abrir_formulario() {
        this.setTitle("LIQUIDACION FINAL");
        evetbl.centrar_formulario_internalframa(this);
        if (cargado_inicial) {
            carcbm.cargarCombobox_via_transporte(cmbvia_transporte);
            carcbm.cargarCombobox_modo_transporte(cmbcontenedor_tipo);
            cargar_tercero_importador();
            cargar_tercero_exportador();
            cargar_aduana();
            cargar_moneda_cambio();
            cargar_transporte_empresa();
            cargar_despacho_zona();
            cargar_tc_mercaderia();
            cargar_ciudad();
            cargar_regimen();
            cargar_incoterms();
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

    private void cargar_tercero_importador() {
        evecomb.cargarCombobox(conn, cmbtercero_importador, "idtercero", "nombre", "tercero", "where importador=true ");
        hab_cargar_tercero_importador = true;
    }

    private void cargar_tercero_exportador() {
        evecomb.cargarCombobox(conn, cmbtercero_exportador, "idtercero", "nombre", "tercero", "where importador=true ");
        hab_cargar_tercero_exportador = true;
    }

    private void cargar_aduana() {
        evecomb.cargarCombobox(conn, cmbaduana, "idaduana", "nombre", "aduana", "");
        hab_cargar_aduana = true;
    }

    private void cargar_transporte_empresa() {
        evecomb.cargarCombobox(conn, cmbtransporte_empresa, "idtransporte_empresa", "nombre", "transporte_empresa", "");
        hab_cargar_transporte_empresa = true;
    }

    private void cargar_moneda_cambio() {
        evecomb.cargarCombobox(conn, cmbmoneda_cambio, "idmoneda_cambio", "moneda", "moneda_cambio", "");
        hab_cargar_moneda_cambio = true;
    }

    private void cargar_despacho_zona() {
        evecomb.cargarCombobox(conn, cmbdespachozona, "iddespacho_zona", "nombre", "despacho_zona", "");
        hab_cargar_despachozona = true;
    }

    private void cargar_tc_mercaderia() {
        evecomb.cargarCombobox(conn, cmbtc_mercaderia, "idtipo_comprobante", "descripcion", "tipo_comprobante", "where mercaderia=true");
        hab_cargar_tc_mercaderia = true;
    }

    private void cargar_ciudad() {
        evecomb.cargarCombobox(conn, cmbdestino, "idtercero_ciudad", "nombre", "tercero_ciudad", "");
        hab_combo_ciudad = true;
    }

    private void cargar_regimen() {
        evecomb.cargarCombobox(conn, cmbregimen, "idregimen", "nombre", "regimen", "");
        hab_combo_regimen = true;
    }

    private void cargar_incoterms() {
        evecomb.cargarCombobox(conn, cmbincoterms, "idincoterms", "nombre", "incoterms", "");
        hab_combo_incoterms = true;
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
        cmbtercero_importador.setSelectedIndex(0);
        cmbtercero_exportador.setSelectedIndex(0);
        cmbtc_mercaderia.setSelectedIndex(0);
        cmbdestino.setSelectedIndex(0);
        cmbvia_transporte.setSelectedIndex(0);
        cmbaduana.setSelectedIndex(0);
        cmbtransporte_empresa.setSelectedIndex(0);
        cmbcontenedor_tipo.setSelectedIndex(0);
        cmbregimen.setSelectedIndex(0);
        cmbincoterms.setSelectedIndex(0);
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

    void seleccionar_cargar_comprobante() {
        fk_idtipo_comprobante = eveconn.getInt_Solo_seleccionar_JLista(conn, jList_comprobante, "tipo_comprobante", "descripcion", "idtipo_comprobante", true);
        DAOcb.cargar_tipo_comprobante(conn, ENTcb, fk_idtipo_comprobante);
        System.out.println("fk_idtipo_comprobante:" + fk_idtipo_comprobante);
        System.out.println("ENTcb.getC2descripcion():" + ENTifin.getC5descripcion());
        txtbucar_comprobante.setText(ENTcb.getC2descripcion());
        txtdespacho_numero_item.grabFocus();
    }

    private void crear_item_liquidacion_final() {
        String dato[] = {"ord", "descripcion", "comprobante", "total", "idliqui", "idcompro"};
        eveJtab.crear_tabla_datos(tblitem_liquidacion_final, model_item_liquidacion, dato);
        eveJtab.ocultar_columna(tblitem_liquidacion_final, 4);
        eveJtab.ocultar_columna(tblitem_liquidacion_final, 5);
    }

    void ancho_item_liquidacion_final() {
        int Ancho[] = {10, 40, 30, 20, 1, 1};
        eveJtab.setAnchoColumnaJtable(tblitem_liquidacion_final, Ancho);
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
        monto_total_despacho = monto;
        if (txtmonto_adelanto.getText().trim().length() > 0) {
            monto_adelanto = Double.parseDouble(txtmonto_adelanto.getText());
        } else {
            monto_adelanto = 0;
        }
        monto_pagar = (monto_total_despacho - monto_adelanto);
        jFmonto_total_despacho.setValue(monto_total_despacho);
        jFmonto_pagar.setValue(monto_pagar);
        monto_letra = nl.Convertir(String.valueOf((int)monto_pagar), true);
        txtmonto_letra.setText(monto_letra);
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
        if (evecomb.getBoo_JCombobox_seleccionar(cmbtercero_importador, "SE DEBE CARGAR UN IMPORTADOR")) {
            eveJtab.mostrar_JTabbedPane(jTab_liquidacion, 0);
            return false;
        }
        if (evecomb.getBoo_JCombobox_seleccionar(cmbtercero_exportador, "SE DEBE CARGAR UN EXPORTADOR")) {
            eveJtab.mostrar_JTabbedPane(jTab_liquidacion, 0);
            return false;
        }
        if (evejtf.getBoo_JTextField_vacio(txtlp_contenedor_nro, "SE DEBE CARGAR UN NUMERO DE CONTENEDOR")) {
            eveJtab.mostrar_JTabbedPane(jTab_liquidacion, 0);
            return false;
        }
        if (evecomb.getBoo_JCombobox_seleccionar(cmbtc_mercaderia, "SE DEBE CARGAR UNA MERCADERIA")) {
            eveJtab.mostrar_JTabbedPane(jTab_liquidacion, 0);
            return false;
        }
        if (evecomb.getBoo_JCombobox_seleccionar(cmbdestino, "SE DEBE CARGAR UN DESTINO")) {
            eveJtab.mostrar_JTabbedPane(jTab_liquidacion, 0);
            return false;
        }
        if (evecomb.getBoo_JCombobox_seleccionar(cmbvia_transporte, "SE DEBE CARGAR UNA VIA")) {
            eveJtab.mostrar_JTabbedPane(jTab_liquidacion, 0);
            return false;
        }
        if (evecomb.getBoo_JCombobox_seleccionar(cmbaduana, "SE DEBE CARGAR UNA ADUANA")) {
            eveJtab.mostrar_JTabbedPane(jTab_liquidacion, 0);
            return false;
        }
        if (evecomb.getBoo_JCombobox_seleccionar(cmbtransporte_empresa, "SE DEBE CARGAR UNA TRANSPORTADORA EMPRESA")) {
            eveJtab.mostrar_JTabbedPane(jTab_liquidacion, 0);
            return false;
        }
        if (evecomb.getBoo_JCombobox_seleccionar(cmbcontenedor_tipo, "SE DEBE CARGAR UNA TIPO CONTENEDOR")) {
            eveJtab.mostrar_JTabbedPane(jTab_liquidacion, 0);
            return false;
        }
        if (evecomb.getBoo_JCombobox_seleccionar(cmbregimen, "SE DEBE CARGAR UN REGIMEN")) {
            eveJtab.mostrar_JTabbedPane(jTab_liquidacion, 0);
            return false;
        }
        if (evecomb.getBoo_JCombobox_seleccionar(cmbincoterms, "SE DEBE CARGAR UN INCOTERMS")) {
            eveJtab.mostrar_JTabbedPane(jTab_liquidacion, 0);
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
        if(monto_imponible==0){
            JOptionPane.showMessageDialog(null,"NO SE ENCONTRO NINGUN VALOR IMPONIBLE CARGADO");
            eveJtab.mostrar_JTabbedPane(jTab_liquidacion, 0);
            return false;
        }
        if (eveJtab.getBoolean_validar_cant_cargado(tblitem_liquidacion_final)) {
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
    void cargar_dato_liquidacion_final(){
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
        liqfin.setC27fk_idtipo_comprobante(fk_idtc_mercaderia);
        liqfin.setC28fk_idtercero_ciudad(fk_idtercero_ciudad);
        liqfin.setC29fk_idaduana(fk_idaduana);
        liqfin.setC30fk_iddespacho_zona(fk_iddespacho_zona);
        liqfin.setC31fk_idtransporte_empresa(fk_idtransporte_empresa);
        liqfin.setC32fk_idtercero_importador(fk_idtercero_importador);
        liqfin.setC33fk_idtercero_transportadora(fk_idtercero_exportador);
        liqfin.setC34fk_idmoneda_cambio(fk_idmoneda_cambio);
        liqfin.setC35fk_idregimen(fk_idregimen);
        liqfin.setC36fk_idincoterms(fk_idincoterms);
    }
    void boton_guardar_liquidacion_final() {
        if (validar_liquidacion_final()) {
            cargar_dato_liquidacion_final();
            if(BOliqfin.getBoolean_insertar_liquidacion_final(liqfin, tblitem_liquidacion_final)){
                DAOliqfin.imprimir_rep_liquidacion_final(conn, idliquidacion_final);
                reestableser_liquidacion();
            }
        }
    }
    void seleccionar_liquidacion(){
        idliquidacion_final_select = eveJtab.getInt_select_id(tblliquidacion);
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
                BOliqfin.anular_update_liquidacion_final(liqfin);
                DAOliqfin.actualizar_tabla_liquidacion_final(conn, tblliquidacion, "");
            }
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
        jLabel8 = new javax.swing.JLabel();
        cmbtercero_importador = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        txtruc_importador = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        cmbtercero_exportador = new javax.swing.JComboBox<>();
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
        btncalcular_monto = new javax.swing.JButton();
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
        cmbtc_mercaderia = new javax.swing.JComboBox<>();
        lbldestino = new javax.swing.JLabel();
        cmbdestino = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        cmbvia_transporte = new javax.swing.JComboBox<>();
        lbladuana = new javax.swing.JLabel();
        cmbaduana = new javax.swing.JComboBox<>();
        lbltransportadora_empresa = new javax.swing.JLabel();
        cmbtransporte_empresa = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        cmbcontenedor_tipo = new javax.swing.JComboBox<>();
        lblregimen = new javax.swing.JLabel();
        cmbregimen = new javax.swing.JComboBox<>();
        cmbincoterms = new javax.swing.JComboBox<>();
        lblincoterms = new javax.swing.JLabel();
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
        panel_item_liquidacion = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblitem_liquidacion_final = new javax.swing.JTable();
        panel_cargar_item = new javax.swing.JPanel();
        jList_comprobante = new javax.swing.JList<>();
        lblcomprobante = new javax.swing.JLabel();
        txtbucar_comprobante = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        txtdespacho_numero_item = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        txtmonto_guarani_item = new javax.swing.JTextField();
        btneliminar_item_liquidacion = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jFmonto_total_despacho = new javax.swing.JFormattedTextField();
        jFmonto_pagar = new javax.swing.JFormattedTextField();
        txtmonto_adelanto = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        txtmonto_letra = new javax.swing.JTextField();
        btnnuevo = new javax.swing.JButton();
        btnguardar = new javax.swing.JButton();
        panel_filtro_liquidacion = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblliquidacion = new javax.swing.JTable();
        btnimprimir_liquidacion = new javax.swing.JButton();
        btnanular = new javax.swing.JButton();

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

        jLabel8.setText("IMPORTADOR:");

        cmbtercero_importador.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbtercero_importador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbtercero_importadorActionPerformed(evt);
            }
        });

        jLabel9.setText("RUC:");

        jLabel10.setText("EXPORTADOR:");

        cmbtercero_exportador.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbtercero_exportador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbtercero_exportadorActionPerformed(evt);
            }
        });

        jLabel11.setText("RUC:");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("MONTOS"));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("FACTURA NRO:");

        txtfactura_numero.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

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

        btncalcular_monto.setText("CALCULAR");
        btncalcular_monto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncalcular_montoActionPerformed(evt);
            }
        });

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
                            .addComponent(txtmonto_seguro)
                            .addComponent(txtmonto_factura, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addComponent(jLabel21))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtmonto_flete, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtmonto_ajuste)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtfactura_numero, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btncalcular_monto))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23)
                            .addComponent(jLabel27)
                            .addComponent(jLabel22))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jFmonto_cif, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jFmonto_total, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jFmonto_imponible))
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
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14)
                        .addComponent(txtfactura_numero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btncalcular_monto))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addGap(30, 30, 30))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("CONTENEDOR"));

        jLabel3.setText("NUMERO:");

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

        cmbtc_mercaderia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbtc_mercaderia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbtc_mercaderiaActionPerformed(evt);
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

        cmbdestino.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbdestino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbdestinoActionPerformed(evt);
            }
        });

        jLabel5.setText("VIA DE T.:");

        cmbvia_transporte.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-SELECCIONAR-", "AEREO", "FLUVIAL", "MARITIMO", "TERRESTRE" }));

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

        cmbaduana.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbaduana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbaduanaActionPerformed(evt);
            }
        });

        lbltransportadora_empresa.setText("TRANSPORTADORA:");
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

        cmbtransporte_empresa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-SELECCIONAR-", "AEREO", "FLUVIAL", "MARITIMO", "TERRESTRE" }));
        cmbtransporte_empresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbtransporte_empresaActionPerformed(evt);
            }
        });

        jLabel4.setText("MODO TRANSPORT.:");

        cmbcontenedor_tipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-SELECCIONAR-", "SIN-TIPO", "CONSOLIDADA o LCL", "COMPLETA o FCL" }));

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

        cmbregimen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbregimen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbregimenActionPerformed(evt);
            }
        });

        cmbincoterms.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbincoterms.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbincotermsActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lblregimen, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbregimen, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblincoterms)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbincoterms, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbltransportadora_empresa)
                            .addComponent(jLabel4))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbtransporte_empresa, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbcontenedor_tipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblmercaderia)
                            .addComponent(lbldestino)
                            .addComponent(jLabel5)
                            .addComponent(lbladuana)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbaduana, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbvia_transporte, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbtc_mercaderia, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbdestino, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txtlp_contenedor_nro, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(105, 105, 105))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtlp_contenedor_nro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblmercaderia)
                    .addComponent(cmbtc_mercaderia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lbldestino)
                    .addComponent(cmbdestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel5)
                    .addComponent(cmbvia_transporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lbladuana)
                    .addComponent(cmbaduana, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbltransportadora_empresa)
                    .addComponent(cmbtransporte_empresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel4)
                    .addComponent(cmbcontenedor_tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbregimen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblregimen)
                    .addComponent(cmbincoterms, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblincoterms))
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

        jLabel1.setText("ID:");

        jLabel2.setText("FEC. DESPACHO:");

        txtobservacion.setColumns(20);
        txtobservacion.setRows(5);
        txtobservacion.setBorder(javax.swing.BorderFactory.createTitledBorder("OBSERVACION"));
        jScrollPane1.setViewportView(txtobservacion);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("DESPACHO NUMERO:");

        txtdespacho_numero.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

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

        javax.swing.GroupLayout panel_encabezadoLayout = new javax.swing.GroupLayout(panel_encabezado);
        panel_encabezado.setLayout(panel_encabezadoLayout);
        panel_encabezadoLayout.setHorizontalGroup(
            panel_encabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_encabezadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_encabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panel_encabezadoLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtdespacho_numero, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbldespacho_zona)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbdespachozona, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(295, 295, 295))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panel_encabezadoLayout.createSequentialGroup()
                        .addGroup(panel_encabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_encabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panel_encabezadoLayout.createSequentialGroup()
                                    .addGroup(panel_encabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panel_encabezadoLayout.createSequentialGroup()
                                            .addComponent(jLabel10)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(cmbtercero_exportador, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panel_encabezadoLayout.createSequentialGroup()
                                            .addComponent(jLabel8)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(cmbtercero_importador, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(panel_encabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panel_encabezadoLayout.createSequentialGroup()
                                            .addComponent(jLabel11)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtruc_exportador, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(panel_encabezadoLayout.createSequentialGroup()
                                            .addComponent(jLabel9)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtruc_importador, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panel_encabezadoLayout.createSequentialGroup()
                                    .addComponent(jRimportacion)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jRexportacion)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtidliquidacion_final, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtfecha_despacho, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jScrollPane1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_encabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panel_encabezadoLayout.createSequentialGroup()
                                .addGroup(panel_encabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panel_encabezadoLayout.createSequentialGroup()
                                        .addComponent(btnnuevo1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnguardar1)))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(14, 14, 14))
        );
        panel_encabezadoLayout.setVerticalGroup(
            panel_encabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_encabezadoLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(panel_encabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel12)
                    .addComponent(txtdespacho_numero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbldespacho_zona)
                    .addComponent(cmbdespachozona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(panel_encabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panel_encabezadoLayout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(panel_encabezadoLayout.createSequentialGroup()
                        .addGroup(panel_encabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRimportacion)
                            .addComponent(jRexportacion)
                            .addComponent(jLabel1)
                            .addComponent(txtidliquidacion_final, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(txtfecha_despacho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_encabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(cmbtercero_importador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(txtruc_importador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_encabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(cmbtercero_exportador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)
                            .addComponent(txtruc_exportador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_encabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                    .addGroup(panel_encabezadoLayout.createSequentialGroup()
                        .addGroup(panel_encabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnnuevo1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnguardar1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
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
        jScrollPane2.setViewportView(tblitem_liquidacion_final);

        panel_cargar_item.setBorder(javax.swing.BorderFactory.createTitledBorder("CARGAR ITEM"));

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

        lblcomprobante.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
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

        jLabel32.setText("TOTAL EN GUARANI:");

        txtmonto_guarani_item.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtmonto_guarani_item.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtmonto_guarani_item.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtmonto_guarani_itemKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtmonto_guarani_itemKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout panel_cargar_itemLayout = new javax.swing.GroupLayout(panel_cargar_item);
        panel_cargar_item.setLayout(panel_cargar_itemLayout);
        panel_cargar_itemLayout.setHorizontalGroup(
            panel_cargar_itemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_cargar_itemLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_cargar_itemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_cargar_itemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jList_comprobante, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panel_cargar_itemLayout.createSequentialGroup()
                            .addComponent(lblcomprobante)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtbucar_comprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panel_cargar_itemLayout.createSequentialGroup()
                        .addComponent(jLabel30)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel31))
                    .addComponent(txtdespacho_numero_item, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32)
                    .addComponent(txtmonto_guarani_item, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        panel_cargar_itemLayout.setVerticalGroup(
            panel_cargar_itemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_cargar_itemLayout.createSequentialGroup()
                .addGroup(panel_cargar_itemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblcomprobante)
                    .addComponent(txtbucar_comprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jList_comprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_cargar_itemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(jLabel31))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtdespacho_numero_item, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel32)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtmonto_guarani_item, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        btneliminar_item_liquidacion.setText("ELIMINAR ITEM");
        btneliminar_item_liquidacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminar_item_liquidacionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addComponent(panel_cargar_item, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 618, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btneliminar_item_liquidacion, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel_cargar_item, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btneliminar_item_liquidacion)))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(jLabel33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtmonto_letra, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panel_item_liquidacionLayout.setVerticalGroup(
            panel_item_liquidacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_item_liquidacionLayout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_item_liquidacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panel_item_liquidacionLayout.createSequentialGroup()
                        .addGroup(panel_item_liquidacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnnuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
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
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 994, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        javax.swing.GroupLayout panel_filtro_liquidacionLayout = new javax.swing.GroupLayout(panel_filtro_liquidacion);
        panel_filtro_liquidacion.setLayout(panel_filtro_liquidacionLayout);
        panel_filtro_liquidacionLayout.setHorizontalGroup(
            panel_filtro_liquidacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panel_filtro_liquidacionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnimprimir_liquidacion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnanular, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel_filtro_liquidacionLayout.setVerticalGroup(
            panel_filtro_liquidacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_filtro_liquidacionLayout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_filtro_liquidacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnimprimir_liquidacion)
                    .addComponent(btnanular))
                .addGap(0, 148, Short.MAX_VALUE))
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

    private void cmbtercero_importadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbtercero_importadorActionPerformed
        // TODO add your handling code here:
        if (hab_cargar_tercero_importador) {
            fk_idtercero_importador = evecomb.getInt_seleccionar_COMBOBOX(conn, cmbtercero_importador, "idtercero", "nombre", "tercero");
            DAOter.cargar_tercero(conn, ENTter, fk_idtercero_importador);
            txtruc_importador.setText(ENTter.getC5ruc());
        }
    }//GEN-LAST:event_cmbtercero_importadorActionPerformed

    private void cmbtercero_exportadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbtercero_exportadorActionPerformed
        // TODO add your handling code here:
        if (hab_cargar_tercero_exportador) {
            fk_idtercero_exportador = evecomb.getInt_seleccionar_COMBOBOX(conn, cmbtercero_exportador, "idtercero", "nombre", "tercero");
            DAOter.cargar_tercero(conn, ENTter, fk_idtercero_exportador);
            txtruc_exportador.setText(ENTter.getC5ruc());
        }
    }//GEN-LAST:event_cmbtercero_exportadorActionPerformed

    private void txtlp_tasa_cambio_aduanaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtlp_tasa_cambio_aduanaKeyReleased
        // TODO add your handling code here:
        evejtf.getString_format_nro_entero(txtlp_tasa_cambio_aduana);
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

    private void cmbaduanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbaduanaActionPerformed
        // TODO add your handling code here:
        if (hab_cargar_aduana) {
            fk_idaduana = evecomb.getInt_seleccionar_COMBOBOX(conn, cmbaduana, "idaduana", "nombre", "aduana");
        }
    }//GEN-LAST:event_cmbaduanaActionPerformed

    private void cmbtransporte_empresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbtransporte_empresaActionPerformed
        // TODO add your handling code here:
        if (hab_cargar_transporte_empresa) {
            fk_idtransporte_empresa = evecomb.getInt_seleccionar_COMBOBOX(conn, cmbtransporte_empresa, "idtransporte_empresa", "nombre", "transporte_empresa");
        }
    }//GEN-LAST:event_cmbtransporte_empresaActionPerformed

    private void cmbdespachozonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbdespachozonaActionPerformed
        // TODO add your handling code here:
        if (hab_cargar_despachozona) {
            fk_iddespacho_zona = evecomb.getInt_seleccionar_COMBOBOX(conn, cmbdespachozona, "iddespacho_zona", "nombre", "despacho_zona");
        }
    }//GEN-LAST:event_cmbdespachozonaActionPerformed

    private void cmbtc_mercaderiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbtc_mercaderiaActionPerformed
        // TODO add your handling code here:
        if (hab_cargar_tc_mercaderia) {
            fk_idtc_mercaderia = evecomb.getInt_seleccionar_COMBOBOX(conn, cmbtc_mercaderia, "idtipo_comprobante", "descripcion", "tipo_comprobante");
        }
    }//GEN-LAST:event_cmbtc_mercaderiaActionPerformed

    private void cmbdestinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbdestinoActionPerformed
        // TODO add your handling code here:
        if (hab_combo_ciudad) {
            fk_idtercero_ciudad = evecomb.getInt_seleccionar_COMBOBOX(conn, cmbdestino, "idtercero_ciudad", "nombre", "tercero_ciudad");
        }
    }//GEN-LAST:event_cmbdestinoActionPerformed

    private void cmbregimenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbregimenActionPerformed
        // TODO add your handling code here:
        if (hab_combo_regimen) {
            fk_idregimen = evecomb.getInt_seleccionar_COMBOBOX(conn, cmbregimen, "idregimen", "nombre", "regimen");
        }
    }//GEN-LAST:event_cmbregimenActionPerformed

    private void cmbincotermsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbincotermsActionPerformed
        // TODO add your handling code here:
        if (hab_combo_incoterms) {
            fk_idincoterms = evecomb.getInt_seleccionar_COMBOBOX(conn, cmbincoterms, "idincoterms", "nombre", "incoterms");
        }
    }//GEN-LAST:event_cmbincotermsActionPerformed

    private void btncalcular_montoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncalcular_montoActionPerformed
        // TODO add your handling code here:
        cargar_montos();
    }//GEN-LAST:event_btncalcular_montoActionPerformed

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

    private void txtbucar_comprobanteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbucar_comprobanteKeyPressed
        // TODO add your handling code here:
        if (txtbucar_comprobante.getText().trim().length() > 1) {
            evejtf.seleccionar_lista(evt, jList_comprobante);
        }
    }//GEN-LAST:event_txtbucar_comprobanteKeyPressed

    private void txtbucar_comprobanteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbucar_comprobanteKeyReleased
        // TODO add your handling code here:
        if (txtbucar_comprobante.getText().trim().length() > 1) {
            eveconn.buscar_cargar_condicion_Jlista(conn, txtbucar_comprobante, jList_comprobante, "tipo_comprobante", "descripcion", "descripcion", "");
        }
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
    }//GEN-LAST:event_txtdespacho_numero_itemKeyPressed

    private void txtmonto_guarani_itemKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmonto_guarani_itemKeyTyped
        // TODO add your handling code here:
        evejtf.soloNumero(evt);
    }//GEN-LAST:event_txtmonto_guarani_itemKeyTyped

    private void txtmonto_guarani_itemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmonto_guarani_itemKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            boton_cargar_item_liquidacion_final();
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnanular;
    private javax.swing.JButton btncalcular_monto;
    private javax.swing.JButton btneliminar_item_liquidacion;
    private javax.swing.JButton btnguardar;
    private javax.swing.JButton btnguardar1;
    private javax.swing.JButton btnimprimir_liquidacion;
    private javax.swing.JButton btnnuevo;
    private javax.swing.JButton btnnuevo1;
    private javax.swing.JComboBox<String> cmbaduana;
    private javax.swing.JComboBox<String> cmbcontenedor_tipo;
    private javax.swing.JComboBox<String> cmbdespachozona;
    private javax.swing.JComboBox<String> cmbdestino;
    private javax.swing.JComboBox<String> cmbincoterms;
    private javax.swing.JComboBox<String> cmbmoneda_cambio;
    private javax.swing.JComboBox<String> cmbregimen;
    private javax.swing.JComboBox<String> cmbtc_mercaderia;
    private javax.swing.JComboBox<String> cmbtercero_exportador;
    private javax.swing.JComboBox<String> cmbtercero_importador;
    private javax.swing.JComboBox<String> cmbtransporte_empresa;
    private javax.swing.JComboBox<String> cmbvia_transporte;
    private javax.swing.ButtonGroup gru_impexp;
    private javax.swing.JFormattedTextField jFmonto_cif;
    private javax.swing.JFormattedTextField jFmonto_imponible;
    private javax.swing.JFormattedTextField jFmonto_pagar;
    private javax.swing.JFormattedTextField jFmonto_total;
    private javax.swing.JFormattedTextField jFmonto_total_despacho;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList_comprobante;
    private javax.swing.JPanel jPanel1;
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
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTab_liquidacion;
    private javax.swing.JLabel lbladuana;
    private javax.swing.JLabel lblcomprobante;
    private javax.swing.JLabel lbldespacho_zona;
    private javax.swing.JLabel lbldestino;
    private javax.swing.JLabel lblincoterms;
    private javax.swing.JLabel lblmercaderia;
    private javax.swing.JLabel lblmoneda;
    private javax.swing.JLabel lblmoneda1;
    private javax.swing.JLabel lblmoneda2;
    private javax.swing.JLabel lblregimen;
    private javax.swing.JLabel lbltransportadora_empresa;
    private javax.swing.JPanel panel_cargar_item;
    private javax.swing.JPanel panel_encabezado;
    private javax.swing.JPanel panel_filtro_liquidacion;
    private javax.swing.JPanel panel_item_liquidacion;
    private javax.swing.JTable tblitem_liquidacion_final;
    private javax.swing.JTable tblliquidacion;
    private javax.swing.JTextField txtbucar_comprobante;
    private javax.swing.JTextField txtdespacho_numero;
    private javax.swing.JTextField txtdespacho_numero_item;
    private javax.swing.JTextField txtfactura_numero;
    private javax.swing.JTextField txtfecha_despacho;
    private javax.swing.JTextField txtidliquidacion_final;
    private javax.swing.JTextField txtlp_contenedor_nro;
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
    private javax.swing.JTextField txtruc_exportador;
    private javax.swing.JTextField txtruc_importador;
    // End of variables declaration//GEN-END:variables
}
