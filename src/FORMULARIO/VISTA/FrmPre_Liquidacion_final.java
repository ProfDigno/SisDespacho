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
import CONFIGURACION.EveVarGlobal;
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
import FILTRO.ClaAuxFiltroVenta;
import FORMULARIO.BO.BO_item_liquidacion_final;
import FORMULARIO.BO.BO_liquidacion_final;
import FORMULARIO.BO.BO_pre_liquidacion;
import FORMULARIO.DAO.DAO_comprobante_liquidacion;
import FORMULARIO.DAO.DAO_item_liquidacion_final;
import FORMULARIO.DAO.DAO_item_pre_liquidacion;
import FORMULARIO.DAO.DAO_liquidacion_final;
import FORMULARIO.DAO.DAO_moneda_cambio;
import FORMULARIO.DAO.DAO_pre_liquidacion;
import FORMULARIO.DAO.DAO_tercero;
import FORMULARIO.DAO.DAO_tercero_rubro;
import FORMULARIO.DAO.DAO_tipo_comprobante;
import FORMULARIO.DAO.dao_usuario;
import FORMULARIO.ENTIDAD.comprobante_liquidacion;
import FORMULARIO.ENTIDAD.entidad_usuario;
import FORMULARIO.ENTIDAD.item_liquidacion_final;
import FORMULARIO.ENTIDAD.item_pre_liquidacion;
import FORMULARIO.ENTIDAD.liquidacion_final;
import FORMULARIO.ENTIDAD.moneda_cambio;
import FORMULARIO.ENTIDAD.pre_liquidacion;
import FORMULARIO.ENTIDAD.tercero;
import FORMULARIO.ENTIDAD.tercero_rubro;
import FORMULARIO.ENTIDAD.tipo_comprobante;
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
public class FrmPre_Liquidacion_final extends javax.swing.JInternalFrame {

    EvenJFRAME evetbl = new EvenJFRAME();
    EvenJtable eveJtab = new EvenJtable();
    EvenCombobox evecomb = new EvenCombobox();
    EventoImagen eveimg = new EventoImagen();
    EvenConexion eveconn = new EvenConexion();
    EvenMensajeJoptionpane evemen = new EvenMensajeJoptionpane();
    EvenJasperReport rep = new EvenJasperReport();
    EvenJLabel evelbl = new EvenJLabel();
    CargaDirectoCombobox carcbm = new CargaDirectoCombobox();
    private EveVarGlobal varglo=new EveVarGlobal();
    private ClaAuxFiltroVenta auxfilto = new ClaAuxFiltroVenta();
    private ClaVarBuscar vbus = new ClaVarBuscar();
    private EvenJTextField evejtf = new EvenJTextField();
    private EvenFecha evefec = new EvenFecha();
    private dao_usuario dao_usu = new dao_usuario();//dao_usuario
    Connection conn = ConnPostgres.getConnPosgres();
    private cla_color_palete clacolor = new cla_color_palete();
    private tercero ENTter = new tercero();
    private DAO_tercero DAOter = new DAO_tercero();
    private entidad_usuario ENTusu = new entidad_usuario();
    private moneda_cambio ENTmc = new moneda_cambio();
    private DAO_moneda_cambio DAOmc = new DAO_moneda_cambio();
    private item_liquidacion_final ENTifin = new item_liquidacion_final();
    private DAO_item_liquidacion_final DAOifin = new DAO_item_liquidacion_final();
    private tipo_comprobante ENTtc = new tipo_comprobante();
    private DAO_tipo_comprobante DAOtc = new DAO_tipo_comprobante();
    private String creado_por = ENTusu.getGlobal_idusuario() + "-" + ENTusu.getGlobal_nombre();
    private DefaultTableModel model_item_liquidacion = new DefaultTableModel();
    private DefaultTableModel model_texte = new DefaultTableModel();
    private pre_liquidacion ENTpreli = new pre_liquidacion();
    private DAO_pre_liquidacion DAOpreli = new DAO_pre_liquidacion();
    private BO_pre_liquidacion BOpreli = new BO_pre_liquidacion();
    private EvenNumero_a_Letra nl = new EvenNumero_a_Letra();
    private comprobante_liquidacion ENTcl = new comprobante_liquidacion();
    private DAO_comprobante_liquidacion DAOcl = new DAO_comprobante_liquidacion();
    private tercero_rubro ENTtr = new tercero_rubro();
    private DAO_tercero_rubro DAOtr = new DAO_tercero_rubro();
    private item_pre_liquidacion ENTipl=new item_pre_liquidacion();
    private DAO_item_pre_liquidacion DAOipl=new DAO_item_pre_liquidacion();
    private static int fk_idtercero_importador;
    private static int fk_idtercero_exportador;
    private double tasa_cambio_aduana = 0;
    private double tasa_cambio_mercado = 0;
    boolean cargado_inicial = true;
    private int fk_idtipo_comprobante;
    private int idliquidacion_final;
    private int orden_item;
    private double monto_total_despacho;
    private double monto_adelanto;
    private double monto_pagar;
    private String liquidacion_impor = varglo.getLiq_Importacion();
    private String liquidacion_espor = varglo.getLiq_Exportacion();
    private String liquidacion_profor = varglo.getLiq_proforma();
    private String estado_emitido = varglo.getEst_Emitido();
    private String estado_anulado = varglo.getEst_Anulado();
    private String estado_pagado = varglo.getEst_Pagado();
    private String estado_proforma = "PROFORMA";
    private int idliquidacion_final_select;
    private String tabla_origen = "LIQUIDACIÓN";
    
    private String tipo_SIN_IVA = "SIN_IVA";
    private String tipo_SOLO_IVA = "SOLO_IVA";
    private String tipo_SIN_Y_SOLO_IVA = "SIN_Y_SOLO_IVA";
    private int fila_select_item_orden_lab;
    private String sin_iva;
    private String solo_iva;

  

    public static int getFk_idtercero_importador() {
        return fk_idtercero_importador;
    }

    public static void setFk_idtercero_importador(int fk_idtercero_importador) {
        FrmPre_Liquidacion_final.fk_idtercero_importador = fk_idtercero_importador;
    }

    public static int getFk_idtercero_exportador() {
        return fk_idtercero_exportador;
    }

    public static void setFk_idtercero_exportador(int fk_idtercero_exportador) {
        FrmPre_Liquidacion_final.fk_idtercero_exportador = fk_idtercero_exportador;
    }

    private boolean esImporExpor;
    private String estado_liquidacion;
    private String tipo_liquidacion;

    

    private void abrir_formulario() {
        this.setTitle("PRE LIQUIDACION FINAL");
        evetbl.centrar_formulario_internalframa(this);
        if (cargado_inicial) {
            crear_item_liquidacion_final();
            reestableser_liquidacion();
            cargado_inicial = false;
        }
    }

    private void grupo_panel_color(Color color_panel) {
        panel_encabezado.setBackground(color_panel);
        panel_item_liquidacion.setBackground(color_panel);
        panel_cargar_item.setBackground(color_panel);
        panel_filtro_liquidacion.setBackground(color_panel);
    }

    private void cargar_colores_impor_export(boolean limpiar) {
        if (limpiar) {
            txtbuscar_importador.setText(null);
            txtimportador_rubro.setText(null);
            txtruc_importador.setText(null);
            jFimportador_saldo.setValue(0);
            txtbuscar_exportador.setText(null);
            txtruc_exportador.setText(null);
            FrmPre_Liquidacion_final.setFk_idtercero_exportador(0);
            FrmPre_Liquidacion_final.setFk_idtercero_importador(0);
        }
        if (jRimportacion.isSelected()) {
            grupo_panel_color(clacolor.getColor_importacion());
            txttipo_impexppro.setText(liquidacion_impor);
            txttipo_impexppro1.setText(liquidacion_impor);
            lblimp_exp_1.setText("IMPORTADOR:");
            lblimp_exp_2.setText("EXPORTADOR:");
        }
        if (jRexportacion.isSelected()) {
            grupo_panel_color(clacolor.getColor_exportacion());
            txttipo_impexppro.setText(liquidacion_espor);
            txttipo_impexppro1.setText(liquidacion_espor);
            lblimp_exp_2.setText("IMPORTADOR:");
            lblimp_exp_1.setText("EXPORTADOR:");
        }
    }

    private void reestableser_liquidacion() {
        evelbl.evento_MouseMoved_inicio(lblcomprobante);
        eveJtab.mostrar_JTabbedPane(jTab_liquidacion, 0);
        idliquidacion_final = (eveconn.getInt_ultimoID_mas_uno(conn, ENTpreli.getTb_pre_liquidacion(), ENTpreli.getId_idpre_liquidacion()));
        txtidliquidacion_final.setText(String.valueOf(idliquidacion_final));
        txtmonto_factura.setText("0");
        txtdescripcion.setText(null);
        txtobservacion.setText("NINGUNA");
        txtnumero_invoice.setText(null);
        txtnumero_factura.setText(null);
        txtbuscar_importador.setText(null);
        txtbuscar_exportador.setText(null);
        txtruc_importador.setText(null);
        txtruc_exportador.setText(null);
        txtimportador_rubro.setText(null);
        txtexportador_rubro.setText(null);
        jFimportador_saldo.setValue(0);
        jFexportador_saldo.setValue(0);
        setFk_idtercero_importador(0);
        setFk_idtercero_exportador(0);
        vbus.limpiar_variables_buscar();
        eveJtab.limpiar_tabla_datos(model_item_liquidacion);
        txtfecha_factura.setText(evefec.getString_formato_fecha());
        txtfecha_llegada.setText(evefec.getString_formato_fecha());
        orden_item = 0;
        jRimportacion.setSelected(true);
        evefec.cargar_combobox_intervalo_fecha(cmbfecha_vale);
        DAOpreli.actualizar_tabla_pre_liquidacion(conn, tblliquidacion,"");
//        DAOliqfin.actualizar_tabla_liquidacion_final(conn, tblliquidacion, filtro_est_anulado());
//        DAOliqfin.actualizar_tabla_liquidacion_final(conn, tblliquidacion_filtro, "");
        sumar_item_liquidacion_final();
        cargar_colores_impor_export(true);
    }

    private String filtro_est_anulado() {
        String filtro = "";
        if (!jCmostrarAnulado.isSelected()) {
            filtro = " and lf.estado!='" + estado_anulado + "' ";
        }
        return filtro;
    }

    private boolean validar_item_liquidacion_final() {
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

    private void boton_cargar_item_liquidacion_final() {
        if (validar_item_liquidacion_final()) {
            cargar_item_liquidacion_final();
            reestableser_item_liquidacion();
        }
    }

    private void reestableser_item_liquidacion() {
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

    private void sumar_item_liquidacion_final() {
        double monto = eveJtab.getDouble_sumar_tabla(tblitem_liquidacion_final, 3);
        double suma_sin_iva = eveJtab.getDouble_sumar_tabla(tblitem_liquidacion_final, 4);
        double suma_solo_iva = eveJtab.getDouble_sumar_tabla(tblitem_liquidacion_final, 5);
        monto_total_despacho = monto;
        monto_pagar = (monto_total_despacho - monto_adelanto);
        jFmonto_total_despacho.setValue(monto_total_despacho);
        jFsuma_sin_iva.setValue(suma_sin_iva);
        jFsuma_solo_iva.setValue(suma_solo_iva);
    }

    private void boton_eliminar_item_liquidacion_final() {
        if (!eveJtab.getBoolean_validar_select(tblitem_liquidacion_final)) {
            if (evemen.MensajeGeneral_warning("DESEAS ELIMINAR ESTE FILA", "ELIMINAR", "ACEPTAR", "CANCELAR")) {
                if (eveJtab.getBoolean_Eliminar_Fila(tblitem_liquidacion_final, model_item_liquidacion)) {
                    sumar_item_liquidacion_final();
                }
            }
        }
    }

    boolean validar_liquidacion_final() {
        if (evejtf.getBoo_JTextField_vacio(txtfecha_factura, "SE DEBE CARGAR LA FECHA DE DESPACHO")) {
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
        if (evejtf.getBoo_JTextField_vacio(txtnumero_factura, "SE DEBE CARGAR UN NUMERO DE FACTURA")) {
            eveJtab.mostrar_JTabbedPane(jTab_liquidacion, 0);
            return false;
        }
        if (evejtf.getBoo_JTextField_vacio(txtmonto_factura, "SE DEBE CARGAR UN MONTO DE FACTURA")) {
            eveJtab.mostrar_JTabbedPane(jTab_liquidacion, 0);
            return false;
        }
        if (evejtf.getBoo_JTextarea_vacio(txtobservacion, "SE DEBE CARGAR UNA OBSERVACION")) {
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
        return true;
    }

    private void select_tipo_liquidacion() {
        tipo_liquidacion = "error";
        estado_liquidacion = "error";
        esImporExpor = false;
        if (jRimportacion.isSelected()) {
            tipo_liquidacion = liquidacion_impor;
            estado_liquidacion = estado_emitido;
            esImporExpor = true;
        }
        if (jRexportacion.isSelected()) {
            tipo_liquidacion = liquidacion_espor;
            estado_liquidacion = estado_emitido;
            esImporExpor = true;
        }
    }

    private void settipo_liquidacion(String liquidacion) {
        if (liquidacion.equals(liquidacion_impor)) {
            jRimportacion.setSelected(true);
        }
        if (liquidacion.equals(liquidacion_espor)) {
            jRexportacion.setSelected(true);
        }
    }

    private void cargar_dato_liquidacion_final() {
        select_tipo_liquidacion();
        ENTpreli.setC3creado_por(creado_por);
        ENTpreli.setC4fecha_factura(evefec.getString_validar_fecha(txtfecha_factura.getText()));
        ENTpreli.setC5fecha_llegada(evefec.getString_validar_fecha(txtfecha_llegada.getText()));
        ENTpreli.setC6numero_factura(txtnumero_factura.getText());
        ENTpreli.setC7numero_invoice(txtnumero_invoice.getText());
        ENTpreli.setC8monto_factura(evejtf.getDouble_format_nro_entero(txtmonto_factura));
        ENTpreli.setC9descripcion(txtdescripcion.getText());
        ENTpreli.setC10observacion(txtobservacion.getText());
        ENTpreli.setC11tipo_liquidacion(tipo_liquidacion);
        ENTpreli.setC12estado(estado_liquidacion);
        ENTpreli.setC13fk_idtercero_importador(getFk_idtercero_importador());
        ENTpreli.setC14fk_idtercero_exportador(getFk_idtercero_exportador());
        BOpreli.insertar_pre_liquidacion(ENTpreli, tblitem_liquidacion_final);
        reestableser_liquidacion();
    }


    private void boton_guardar_liquidacion_final() {
        if (validar_liquidacion_final()) {
            cargar_dato_liquidacion_final();
        }
    }

    private void seleccionar_liquidacion() {
        idliquidacion_final_select = eveJtab.getInt_select_id(tblliquidacion);
        String estado = eveJtab.getString_select(tblliquidacion, 10);
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
        if (estado.equals(estado_proforma)) {
            btnanular.setEnabled(false);
            btnrecargar_liquidacion.setEnabled(true);
        }
//        DAOifin.actualizar_tabla_item_liquidacion_final_por_id(conn, tblitem_liquidacion_final_id, idliquidacion_final_select);
        DAOipl.actualizar_tabla_item_pre_liquidacion_por_id(conn, tblitem_liquidacion_final_id, idliquidacion_final_select);
    }


    public void reacargar_pre_item_liquidacion(int idliquidacion_final_select) {
        idliquidacion_final = (eveconn.getInt_ultimoID_mas_uno(conn, ENTpreli.getTb_pre_liquidacion(), ENTpreli.getId_idpre_liquidacion()));
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


    private String getfiltro_liquidacion_reporte() {
        String filtro = "";
        String estado = auxfilto.filtro_liquidacion(jCliq_emitido, jCliq_pagado, jCliq_proforma, jCliq_anulado);
        String fecha = evefec.getIntervalo_fecha_combobox(cmbfecha_vale, " lf.fecha_despacho ");
        filtro = filtro + estado;
        filtro = filtro + fecha;
        return filtro;
    }

    private void actualizar_liquidacion_filtro() {
//        DAOliqfin.actualizar_tabla_liquidacion_final(conn, tblliquidacion_filtro, getfiltro_liquidacion_reporte());
    }

    private void crear_item_liquidacion_final() {
        String dato[] = {"ord", "descripcion", "comprobante", "total", "sin_iva", "solo_iva", "idliqui", "idcompro"};
        eveJtab.crear_tabla_datos(tblitem_liquidacion_final, model_item_liquidacion, dato);
        eveJtab.ocultar_columna(tblitem_liquidacion_final, 6);
        eveJtab.ocultar_columna(tblitem_liquidacion_final, 7);
    }

    private void ancho_item_liquidacion_final() {
        int Ancho[] = {8, 40, 20, 10, 10, 10, 1, 1};
        eveJtab.setAnchoColumnaJtable(tblitem_liquidacion_final, Ancho);
    }

    public void cargar_pre_item_liquidacion() {

        if (true) {
            String titulo = "cargar_pre_item_liquidacion";
            String sql = "select cl.idcomprobante_liquidacion, \n"
                    + "pilf.orden,cl.descripcion,cl.por_iva,cl.tipo_iva,cl.nro_despacho \n"
                    + "from pre_item_liquidacion_final pilf,comprobante_liquidacion cl \n"
                    + "where pilf.fk_idcomprobante_liquidacion=cl.idcomprobante_liquidacion \n"
                    + "and pilf.eliminado=false "
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
        }
    }

    private boolean validar_item_pre_item_liquidacion() {
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

    private void calcular_iva_desdemonto() {
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

    private void seleccionar_pre_item_liquidacion() {
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

    
    private void abrir_buscar(int tipo, String nombre, JTextField txtbuscar) {
        vbus.setTipo_tabla(tipo);
        vbus.setNombre_tabla(nombre);
        vbus.setPre_busqueda(txtbuscar.getText());
        JDiaBuscarDosColumnas frm = new JDiaBuscarDosColumnas(null, true);
        frm.setVisible(true);
    }

    

    public FrmPre_Liquidacion_final() {
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
        jLabel1 = new javax.swing.JLabel();
        txtidliquidacion_final = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtobservacion = new javax.swing.JTextArea();
        btnguardar1 = new javax.swing.JButton();
        btnnuevo1 = new javax.swing.JButton();
        panel_cliente = new javax.swing.JPanel();
        lblimp_exp_1 = new javax.swing.JLabel();
        txtbuscar_importador = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtruc_importador = new javax.swing.JTextField();
        btnbuscar_importador = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        txtimportador_rubro = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jFimportador_saldo = new javax.swing.JFormattedTextField();
        txttipo_impexppro = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtdescripcion = new javax.swing.JTextArea();
        jPanel11 = new javax.swing.JPanel();
        lblimp_exp_2 = new javax.swing.JLabel();
        txtbuscar_exportador = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtruc_exportador = new javax.swing.JTextField();
        btnbuscar_exportador = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        txtexportador_rubro = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jFexportador_saldo = new javax.swing.JFormattedTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtfecha_factura = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txtfecha_llegada = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        txtnumero_factura = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtmonto_factura = new javax.swing.JTextField();
        txtnumero_invoice = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
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
        btnnuevo = new javax.swing.JButton();
        btnguardar = new javax.swing.JButton();
        txttipo_impexppro1 = new javax.swing.JTextField();
        jFmonto_total_despacho = new javax.swing.JFormattedTextField();
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
        jCmostrarAnulado = new javax.swing.JCheckBox();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblliquidacion_filtro = new javax.swing.JTable();
        btnimprimir_reporte_filtro = new javax.swing.JButton();
        jLabel37 = new javax.swing.JLabel();
        cmbfecha_vale = new javax.swing.JComboBox<>();
        jPanel10 = new javax.swing.JPanel();
        jCliq_emitido = new javax.swing.JCheckBox();
        jCliq_pagado = new javax.swing.JCheckBox();
        jCliq_proforma = new javax.swing.JCheckBox();
        jCliq_anulado = new javax.swing.JCheckBox();

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
        jRimportacion.setForeground(new java.awt.Color(255, 51, 0));
        jRimportacion.setSelected(true);
        jRimportacion.setText("IMPORTACION");
        jRimportacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRimportacionActionPerformed(evt);
            }
        });

        gru_impexp.add(jRexportacion);
        jRexportacion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jRexportacion.setForeground(new java.awt.Color(0, 153, 0));
        jRexportacion.setText("EXPORTACION");
        jRexportacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRexportacionActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("ID:");

        txtidliquidacion_final.setBackground(new java.awt.Color(255, 255, 51));
        txtidliquidacion_final.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtidliquidacion_final.setForeground(new java.awt.Color(0, 0, 204));
        txtidliquidacion_final.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtobservacion.setColumns(20);
        txtobservacion.setRows(5);
        txtobservacion.setBorder(javax.swing.BorderFactory.createTitledBorder("OBSERVACION"));
        jScrollPane1.setViewportView(txtobservacion);

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

        panel_cliente.setBorder(javax.swing.BorderFactory.createTitledBorder("IMPORTADOR"));

        lblimp_exp_1.setText("IMPORTADOR:");

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
                    .addComponent(lblimp_exp_1)
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
                        .addComponent(lblimp_exp_1)
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

        txttipo_impexppro.setEditable(false);
        txttipo_impexppro.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        txttipo_impexppro.setForeground(new java.awt.Color(204, 0, 0));
        txttipo_impexppro.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txttipo_impexppro.setText("jTextField1");

        txtdescripcion.setColumns(20);
        txtdescripcion.setRows(5);
        txtdescripcion.setBorder(javax.swing.BorderFactory.createTitledBorder("DESCRIPCION"));
        jScrollPane6.setViewportView(txtdescripcion);

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder("EXPORTADOR"));

        lblimp_exp_2.setText("EXPORTADOR:");

        txtbuscar_exportador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtbuscar_exportadorKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbuscar_exportadorKeyReleased(evt);
            }
        });

        jLabel11.setText("RUC:");

        txtruc_exportador.setEditable(false);

        btnbuscar_exportador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/ABM/mini_lupa.png"))); // NOI18N
        btnbuscar_exportador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscar_exportadorActionPerformed(evt);
            }
        });

        jLabel27.setText("RUBRO:");

        jLabel35.setText("SALDO:");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblimp_exp_2)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel27)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(txtbuscar_exportador, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(txtexportador_rubro, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel35)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel11Layout.createSequentialGroup()
                        .addComponent(txtruc_exportador, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnbuscar_exportador, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jFexportador_saldo))
                .addGap(0, 3, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblimp_exp_2)
                        .addComponent(txtbuscar_exportador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11)
                        .addComponent(txtruc_exportador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnbuscar_exportador, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtexportador_rubro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27)
                    .addComponent(jLabel35)
                    .addComponent(jFexportador_saldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 9, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("FECHA"));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("FEC. FACTURA:");

        txtfecha_factura.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel25.setText("aaaa-MM-dd");

        txtfecha_llegada.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("FEC. LLEGADA:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(txtfecha_factura, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(txtfecha_llegada, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtfecha_factura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtfecha_llegada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel25))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("FACTURA"));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("FACTURA NRO:");

        txtnumero_factura.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtnumero_factura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtnumero_facturaKeyPressed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("MONTO FACTURA:");

        txtmonto_factura.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
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

        txtnumero_invoice.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtnumero_invoice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtnumero_invoiceKeyPressed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setText("INVOICE NRO:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel14)
                    .addComponent(txtnumero_factura, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                    .addComponent(jLabel15)
                    .addComponent(txtmonto_factura))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel19)
                    .addComponent(txtnumero_invoice, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 45, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtnumero_factura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtnumero_invoice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtmonto_factura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panel_encabezadoLayout = new javax.swing.GroupLayout(panel_encabezado);
        panel_encabezado.setLayout(panel_encabezadoLayout);
        panel_encabezadoLayout.setHorizontalGroup(
            panel_encabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_encabezadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_encabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panel_encabezadoLayout.createSequentialGroup()
                        .addComponent(btnnuevo1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnguardar1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txttipo_impexppro))
                    .addGroup(panel_encabezadoLayout.createSequentialGroup()
                        .addComponent(jRimportacion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRexportacion))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panel_cliente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_encabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panel_encabezadoLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtidliquidacion_final, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(135, Short.MAX_VALUE))
        );
        panel_encabezadoLayout.setVerticalGroup(
            panel_encabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_encabezadoLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(panel_encabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel1)
                    .addComponent(txtidliquidacion_final, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRimportacion)
                    .addComponent(jRexportacion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_encabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_encabezadoLayout.createSequentialGroup()
                        .addComponent(panel_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_encabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnnuevo1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnguardar1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txttipo_impexppro, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(109, 109, 109))
                    .addGroup(panel_encabezadoLayout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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
                            .addComponent(txtdespacho_numero_item, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addGroup(panel_cargar_itemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(jLabel31))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtdespacho_numero_item, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 107, Short.MAX_VALUE)
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

        txttipo_impexppro1.setEditable(false);
        txttipo_impexppro1.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        txttipo_impexppro1.setForeground(new java.awt.Color(255, 0, 0));
        txttipo_impexppro1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txttipo_impexppro1.setText("jTextField1");

        jFmonto_total_despacho.setEditable(false);
        jFmonto_total_despacho.setBorder(javax.swing.BorderFactory.createTitledBorder("TOTAL-DESPACHO"));
        jFmonto_total_despacho.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat(""))));
        jFmonto_total_despacho.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFmonto_total_despacho.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txttipo_impexppro1, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jFmonto_total_despacho, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panel_item_liquidacionLayout.setVerticalGroup(
            panel_item_liquidacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_item_liquidacionLayout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_item_liquidacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnnuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFmonto_total_despacho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txttipo_impexppro1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 95, Short.MAX_VALUE))
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
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1139, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
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
            .addComponent(jScrollPane4)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
        );

        btnrecargar_liquidacion.setText("RE-CARGAR");
        btnrecargar_liquidacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrecargar_liquidacionActionPerformed(evt);
            }
        });

        jCmostrarAnulado.setText("MOSTRAR ANULADO");
        jCmostrarAnulado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCmostrarAnuladoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_filtro_liquidacionLayout = new javax.swing.GroupLayout(panel_filtro_liquidacion);
        panel_filtro_liquidacion.setLayout(panel_filtro_liquidacionLayout);
        panel_filtro_liquidacionLayout.setHorizontalGroup(
            panel_filtro_liquidacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panel_filtro_liquidacionLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_filtro_liquidacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_filtro_liquidacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnimprimir_liquidacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnanular, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnrecargar_liquidacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jCmostrarAnulado, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        panel_filtro_liquidacionLayout.setVerticalGroup(
            panel_filtro_liquidacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_filtro_liquidacionLayout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_filtro_liquidacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panel_filtro_liquidacionLayout.createSequentialGroup()
                        .addComponent(btnimprimir_liquidacion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnanular)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnrecargar_liquidacion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jCmostrarAnulado)
                        .addContainerGap())
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jTab_liquidacion.addTab("FILTRO LIQUIDACION", panel_filtro_liquidacion);

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("LIQUIDACION"));

        tblliquidacion_filtro.setModel(new javax.swing.table.DefaultTableModel(
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
        tblliquidacion_filtro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblliquidacion_filtroMouseReleased(evt);
            }
        });
        jScrollPane5.setViewportView(tblliquidacion_filtro);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 1139, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)
        );

        btnimprimir_reporte_filtro.setText("IMPRIMIR REPORTE");
        btnimprimir_reporte_filtro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnimprimir_reporte_filtroActionPerformed(evt);
            }
        });

        jLabel37.setText("Fecha:");

        cmbfecha_vale.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbfecha_valeItemStateChanged(evt);
            }
        });

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("ESTADO"));

        jCliq_emitido.setText("EMITIDO");
        jCliq_emitido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCliq_emitidoActionPerformed(evt);
            }
        });

        jCliq_pagado.setText("PAGADO");
        jCliq_pagado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCliq_pagadoActionPerformed(evt);
            }
        });

        jCliq_proforma.setText("PROFORMA");
        jCliq_proforma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCliq_proformaActionPerformed(evt);
            }
        });

        jCliq_anulado.setText("ANULADO");
        jCliq_anulado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCliq_anuladoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jCliq_emitido)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCliq_pagado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCliq_proforma, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCliq_anulado)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCliq_emitido)
                    .addComponent(jCliq_pagado)
                    .addComponent(jCliq_proforma)
                    .addComponent(jCliq_anulado))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel37)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbfecha_vale, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnimprimir_reporte_filtro, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(cmbfecha_vale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnimprimir_reporte_filtro, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(69, Short.MAX_VALUE))
        );

        jTab_liquidacion.addTab("FILTRO LIQUIDACION REPORTE", jPanel8);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTab_liquidacion)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTab_liquidacion, javax.swing.GroupLayout.PREFERRED_SIZE, 594, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtmonto_facturaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmonto_facturaKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        }
    }//GEN-LAST:event_txtmonto_facturaKeyPressed

    private void txtmonto_facturaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmonto_facturaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmonto_facturaKeyReleased

    private void txtmonto_facturaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmonto_facturaKeyTyped
        // TODO add your handling code here:
        evejtf.soloNumero(evt);
    }//GEN-LAST:event_txtmonto_facturaKeyTyped

    private void jRimportacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRimportacionActionPerformed
        // TODO add your handling code here:
        cargar_colores_impor_export(true);
    }//GEN-LAST:event_jRimportacionActionPerformed

    private void jRexportacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRexportacionActionPerformed
        // TODO add your handling code here:
        cargar_colores_impor_export(true);
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

    private void btneliminar_item_liquidacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminar_item_liquidacionActionPerformed
        // TODO add your handling code here:
        boton_eliminar_item_liquidacion_final();
    }//GEN-LAST:event_btneliminar_item_liquidacionActionPerformed

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
        DAOpreli.ancho_tabla_pre_liquidacion(tblliquidacion);
//        DAOliqfin.ancho_tabla_liquidacion_final(tblliquidacion);
//        DAOliqfin.ancho_tabla_liquidacion_final(tblliquidacion_filtro);
    }//GEN-LAST:event_formInternalFrameOpened

    private void tblliquidacionMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblliquidacionMouseReleased
        // TODO add your handling code here:
        seleccionar_liquidacion();
    }//GEN-LAST:event_tblliquidacionMouseReleased

    private void btnimprimir_liquidacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnimprimir_liquidacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnimprimir_liquidacionActionPerformed

    private void btnanularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnanularActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnanularActionPerformed

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

    private void txtbuscar_importadorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscar_importadorKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            abrir_buscar(14, "IMPORTADOR", txtbuscar_importador);
        }
    }//GEN-LAST:event_txtbuscar_importadorKeyPressed

    private void txtbuscar_exportadorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscar_exportadorKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            abrir_buscar(15, "EXPORTADOR", txtbuscar_exportador);
        }
    }//GEN-LAST:event_txtbuscar_exportadorKeyPressed

    private void txtnumero_facturaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnumero_facturaKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtmonto_factura.grabFocus();
        }
    }//GEN-LAST:event_txtnumero_facturaKeyPressed

    private void btnbuscar_exportadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscar_exportadorActionPerformed
        // TODO add your handling code here:
//        if (jRimportacion.isSelected()) {
            abrir_buscar(15, "EXPORTADOR", txtbuscar_exportador);
//        }
    }//GEN-LAST:event_btnbuscar_exportadorActionPerformed

    private void btnbuscar_importadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscar_importadorActionPerformed
        // TODO add your handling code here:
//        if (jRimportacion.isSelected()) {
            abrir_buscar(14, "IMPORTADOR", txtbuscar_importador);
//        }
    }//GEN-LAST:event_btnbuscar_importadorActionPerformed

    private void btnrecargar_liquidacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrecargar_liquidacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnrecargar_liquidacionActionPerformed

    private void btneditar_item_comprobanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditar_item_comprobanteActionPerformed
        // TODO add your handling code here:
        evetbl.abrir_TablaJinternal(new FrmPre_item_liquidacion_final());
    }//GEN-LAST:event_btneditar_item_comprobanteActionPerformed

    private void txtbuscar_exportadorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscar_exportadorKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbuscar_exportadorKeyReleased

    private void tblliquidacion_filtroMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblliquidacion_filtroMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tblliquidacion_filtroMouseReleased

    private void btnimprimir_reporte_filtroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnimprimir_reporte_filtroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnimprimir_reporte_filtroActionPerformed

    private void cmbfecha_valeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbfecha_valeItemStateChanged
        // TODO add your handling code here:
        actualizar_liquidacion_filtro();
    }//GEN-LAST:event_cmbfecha_valeItemStateChanged

    private void jCliq_emitidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCliq_emitidoActionPerformed
        // TODO add your handling code here:
        actualizar_liquidacion_filtro();
    }//GEN-LAST:event_jCliq_emitidoActionPerformed

    private void jCliq_pagadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCliq_pagadoActionPerformed
        // TODO add your handling code here:
        actualizar_liquidacion_filtro();
    }//GEN-LAST:event_jCliq_pagadoActionPerformed

    private void jCliq_proformaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCliq_proformaActionPerformed
        // TODO add your handling code here:
        actualizar_liquidacion_filtro();
    }//GEN-LAST:event_jCliq_proformaActionPerformed

    private void jCliq_anuladoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCliq_anuladoActionPerformed
        // TODO add your handling code here:
        actualizar_liquidacion_filtro();
    }//GEN-LAST:event_jCliq_anuladoActionPerformed

    private void jCmostrarAnuladoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCmostrarAnuladoActionPerformed
        // TODO add your handling code here:
//        DAOliqfin.actualizar_tabla_liquidacion_final(conn, tblliquidacion, filtro_est_anulado());
    }//GEN-LAST:event_jCmostrarAnuladoActionPerformed

    private void txtnumero_invoiceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnumero_invoiceKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnumero_invoiceKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnanular;
    private javax.swing.JButton btnbuscar_exportador;
    private javax.swing.JButton btnbuscar_importador;
    private javax.swing.JButton btncargar_pre_item;
    private javax.swing.JButton btneditar_item_comprobante;
    private javax.swing.JButton btneliminar_item_liquidacion;
    private javax.swing.JButton btnguardar;
    private javax.swing.JButton btnguardar1;
    private javax.swing.JButton btnimprimir_liquidacion;
    private javax.swing.JButton btnimprimir_reporte_filtro;
    private javax.swing.JButton btnnuevo;
    private javax.swing.JButton btnnuevo1;
    private javax.swing.JButton btnrecargar_liquidacion;
    private javax.swing.JComboBox<String> cmbfecha_vale;
    private javax.swing.ButtonGroup gru_impexp;
    private javax.swing.JCheckBox jCliq_anulado;
    private javax.swing.JCheckBox jCliq_emitido;
    private javax.swing.JCheckBox jCliq_pagado;
    private javax.swing.JCheckBox jCliq_proforma;
    private javax.swing.JCheckBox jCmostrarAnulado;
    public static javax.swing.JFormattedTextField jFexportador_saldo;
    public static javax.swing.JFormattedTextField jFimportador_saldo;
    private javax.swing.JFormattedTextField jFmonto_total_despacho;
    private javax.swing.JFormattedTextField jFsin_iva;
    private javax.swing.JFormattedTextField jFsolo_iva;
    private javax.swing.JFormattedTextField jFsuma_sin_iva;
    private javax.swing.JFormattedTextField jFsuma_solo_iva;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
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
    private javax.swing.JTabbedPane jTab_liquidacion;
    private javax.swing.JLabel lblcomprobante;
    private javax.swing.JLabel lblidcomprobante;
    private javax.swing.JLabel lblimp_exp_1;
    private javax.swing.JLabel lblimp_exp_2;
    private javax.swing.JLabel lbltipo_iva;
    private javax.swing.JPanel panel_cargar_item;
    private javax.swing.JPanel panel_cliente;
    private javax.swing.JPanel panel_encabezado;
    private javax.swing.JPanel panel_filtro_liquidacion;
    private javax.swing.JPanel panel_item_liquidacion;
    private javax.swing.JTable tblitem_liquidacion_final;
    private javax.swing.JTable tblitem_liquidacion_final_id;
    private javax.swing.JTable tblliquidacion;
    private javax.swing.JTable tblliquidacion_filtro;
    private javax.swing.JTextField txtbucar_comprobante;
    public static javax.swing.JTextField txtbuscar_exportador;
    public static javax.swing.JTextField txtbuscar_importador;
    public static javax.swing.JTextArea txtdescripcion;
    private javax.swing.JTextField txtdespacho_numero_item;
    public static javax.swing.JTextField txtexportador_rubro;
    private javax.swing.JTextField txtfecha_factura;
    private javax.swing.JTextField txtfecha_llegada;
    private javax.swing.JTextField txtidliquidacion_final;
    public static javax.swing.JTextField txtimportador_rubro;
    private javax.swing.JTextField txtmonto_factura;
    private javax.swing.JTextField txtmonto_guarani_item;
    private javax.swing.JTextField txtnumero_factura;
    private javax.swing.JTextField txtnumero_invoice;
    private javax.swing.JTextArea txtobservacion;
    public static javax.swing.JTextField txtruc_exportador;
    public static javax.swing.JTextField txtruc_importador;
    private javax.swing.JTextField txttipo_impexppro;
    private javax.swing.JTextField txttipo_impexppro1;
    // End of variables declaration//GEN-END:variables
}
